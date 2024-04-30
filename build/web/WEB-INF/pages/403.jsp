<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
       <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Denied</title>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
          <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
          <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
          <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" type="text/css" />
    </head>
    
    <body class="bodycolor">
        <%@include file="header.jsp" %>
    <center>
        <h2 style="color: red">Access is denied</h2>

        <c:choose>
            <c:when test="${empty username}">
                <h4 style="color: red">You do not have permission to access this page!</h4>
            </c:when>
            <c:otherwise>
<!--			<h2>Username : ${username} <br/>You do not have permission to access this page!</h2>-->
                <h4 style="color: red">You do not have permission to access this page!!!</h3>
            </c:otherwise>
        </c:choose>
      </center>          
      <%@include file="footer.jsp" %> 
    </body>
</html>