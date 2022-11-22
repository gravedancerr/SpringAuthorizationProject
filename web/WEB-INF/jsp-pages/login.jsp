<html>
<head>
    <%@ include file="../style/login-admin-user.css"%>
    <title>Log in</title>
</head>
<body>
<form method="post" action="/login">
    <div class="LoginForm">
        <h1>Log in to your account</h1>
        <div id="message">
            <span>${message}</span>
        </div>
        <div>
            <label for="login">Login</label>
            <input type="text" id="login" name="username" required
                   placeholder="enter your login">
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required
                   placeholder="enter your password">
        </div>
        <div>
            <input type="submit" value="Sign in">
        </div>
        <div class="CreateAcc">
            <a href="/register" class="RegisterRef">Create account</a>
        </div>
    </div>
</form>
</body>
</html>