<%@ taglib prefix="tag" uri="/WEB-INF/tag.tld"%>
<html>
<head>
    <%@ include file="../style/login-admin-user.css"%>
    <title>Administration</title>
</head>
<body>
<div class="AdminName">${firstName} ${lastName}</div>
<div class="AddRef">
    <a href="/add">Add new user</a>
</div>
<div class="LogoutRef">
    <a href="/logout">Logout</a>
</div>
<tag:users_table users="${usersTable}"/>
</body>
</html>