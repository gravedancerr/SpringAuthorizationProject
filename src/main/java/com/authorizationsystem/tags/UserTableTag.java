package com.authorizationsystem.tags;

import com.authorizationsystem.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UserTableTag implements Tag {
    public static final Logger LOG = LogManager.getLogger(UserTableTag.class);
    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    private Tag parent;
    private PageContext pageContext;

    @Override
    public void setParent(Tag parent) {
        this.parent = parent;
    }

    @Override
    public Tag getParent() {
        return parent;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            doTag();
        } catch (IOException e) {
            LOG.error("IO operation was interrupted");
            throw new RuntimeException("IO operation was interrupted");
        }
        return SKIP_BODY;
    }

    @Override
    public void release() {
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        try {
            writer.print("</tbody></table>");
        } catch (IOException e) {
            LOG.error("IO operation was interrupted", e);
            throw new RuntimeException("IO operation was interrupted", e);
        }
        return EVAL_PAGE;
    }

    public long getAge(Date birthday) {
        return ChronoUnit.YEARS.between(birthday.toLocalDate(),
                LocalDate.now());
    }

    public void doTag() throws IOException {
        JspWriter writer = pageContext.getOut();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>").append("<tr>").append("<th>Login</th>")
                .append("<th>First Name</th>").append("<th>Last Name</th>")
                .append("<th>Age</th>").append("<th>Role</th>")
                .append("<th>Actions</th>").append("</tr>");
        for (User user : users) {
            stringBuilder.append("<tbody><tr><td>").append(user.getLogin())
                    .append("</td><td>").append(user.getFirstName())
                    .append("</td><td>").append(user.getLastName())
                    .append("</td><td>").append(getAge(user.getBirthday()))
                    .append("</td><td>")
                    .append(user.getRole().getName())
                    .append("</td><td>").append("<a href=\"/edit?id=")
                    .append(user.getId()).append("\">Edit</a>")
                    .append("<a href=\"/delete?id=").append(user.getId())
                    .append("\" onclick=\"return confirm('Delete user ")
                    .append(user.getFirstName()).append(" ")
                    .append(user.getLastName()).append("?')\">    Delete</a>")
                    .append("</td></tr>");
        }
        try {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            LOG.error("IO operation was interrupted", e);
            throw new RuntimeException("IO operation was interrupted", e);
        }
    }
}
