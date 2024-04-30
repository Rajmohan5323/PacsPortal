<%-- 
    Document   : subscriptionPage
    Created on : Sep 9, 2022, 10:24:20 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <title>Subscription Page</title>
    </head>
    <body class="bodycolor">
        <%@include file="header.jsp" %>
        <center>
            <br>
            <br>
        <h4 style="color: red" >Your account has expired. Please renew your account to continue this service!!!</h4>
        </center>
        <%@include file="footer.jsp" %>
    </body>
</html>
