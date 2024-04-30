
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page session="true"%>


<!DOCTYPE html>
<html >
    <head>
        
        <!link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
          <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
          <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" type="text/css" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Login Page</title>
        <style>

                body {
                
                
                background: url('/Pacs/images/home-7.jpg') no-repeat;
                background-size: cover;
                background-position: 10% 0%;
                padding: 200px 0 280px 0;
                position: relative;
                }
                
                    
                    
                
                .form-signin {
                max-width: 330px;
                padding: 15px;
                margin: 0 auto;
                }
                .form-signin .form-signin-heading,
                .form-signin .checkbox {
                margin-bottom: 10px;
                }
                .form-signin .checkbox {
                font-weight: 400;
                }
                .form-signin .form-control {
                position: relative;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
                height: auto;
                padding: 10px;
                font-size: 16px;
                }
                .form-signin .form-control:focus {
                z-index: 2;
                }

                .form-signin input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
                }
                .error {
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                color: #a94442;
                background-color: #f2dede;
                border-color: #ebccd1;
                }

                .msg {
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                color: #31708f;
                background-color: #d9edf7;
                border-color: #bce8f1;
                }

                #login-box {
                width: 300px;
                padding: 20px;
                margin: 100px auto;
                background: #fff;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border: 1px solid #000;
                }
                
        </style>

        
    </head>
    <body onload='document.loginForm.username.focus();'>
        
        <div  class="container" >
            <form class="form-signin" name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
            <h2 class="form-signin-heading">Please sign in</h2>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>
                
            <input type="text" name="username" class="form-control" placeholder="UserName" required>    
            <input type="password" name="password" class="form-control" placeholder="Password" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
           </div>
            
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>
        </div>
            
    </body>
</html>