<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%@ include file="../style/add-edit.css"%>
    <title>Add user</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<a href="/logout">Logout</a>
<h2>Add User</h2>
<form:form method="post" action="/add" modelAttribute="user">
    <div>
        <label for="login">Login</label>
        <span><c:out value="${duplicatedLogin}"/></span>
        <input type="text"
               id="login"
               name="login"
               placeholder="Enter login"
               required>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password"
               id="password"
               name="password"
               placeholder="Enter password"
               minlength="4"
               maxlength="70"
               required>
    </div>
    <div>
        <label for="password2">Confirm password</label>
        <span id="indicator"></span>
        <input type="password"
               id="password2"
               name="password2"
               placeholder="Enter password again"
               minlength="4"
               maxlength="70"
               required>
    </div>
    <div>
        <label for="email">Email</label>
        <span><c:out value="${duplicatedEmail}"/></span>
        <input type="email"
               id="email"
               name="email"
               required
               placeholder="Enter email"
               maxlength="70">
    </div>
    <div>
        <label for="firstName">First name</label>
        <input type="text"
               id="firstName"
               name="firstName"
               required
               placeholder="Enter first name"
               maxlength="70">
    </div>
    <div>
        <label for="lastName">Last name</label>
        <input type="text"
               id="lastName"
               name="lastName"
               required
               placeholder="Enter last name"
               maxlength="70">
    </div>
    <div>
        <label for="birthday">Date of birth</label>
        <span><c:out value="${invalidBirthday}"/></span>
        <input type="date"
               id="birthday"
               name="birthday"
               required>
    </div>
    <div>
        <label for="name">Choose role</label>
        <select id="name" name="name" required>
            <c:forEach items="${roles}" var="role">
                <option value="${role.name}"><c:out value="${role.name}"/></option>
            </c:forEach>
        </select>
    </div>
    <button class="btn1" type="submit" id="submit">Ok</button>
    <button><a class="btn2" href="/administration">Cancel</a></button>
</form:form>
<script>
    $('#password2').keyup(function(){
        const pass = $('#password').val();
        const pass2 = $('#password2').val();
        if(pass !== pass2){
            $('#indicator').attr({class: 'red'});
            $('#submit').attr({disabled: true});
            $('#indicator').html('Passwords do not match')
        }
        else{
            $('#indicator').attr({class: 'green'});
            $('#submit').attr({disabled: false});
            $('#indicator').html('Passwords match')
        }
    });
</script>
</body>
</html>