<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
        <c:url value="/j_spring_security_logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm" style="margin-bottom: 0px;">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }

        </script>
        <c:if test="${pageContext.request.userPrincipal.name != null}">       
<header id="header">
    <div class="top-bar">
        <div class="container" >
            <div class="row">

                <div class="col-sm-2 col-xs-2 col-md-1"  style="padding-right:0px;">
                    <div class="top-number org_logo"><img src="${pageContext.request.contextPath}/images/SVIT90.JPG"  class="img-res1" /></div>
                </div>

                <div class="col-sm-6 col-xs-6  col-md-10" style="padding-left:0px;"> 
                    <h2 class="orgname1">SRI VARI INFORMATION TECHNOLOGY </h2>
                </div>
                <div class="col-sm-4 col-xs-4 col-md-1">
                    <ul class="righttopmenu">
                        <li><a href="${pageContext.request.contextPath}/admin"><i class="fa fa-home" style="font-size:24px" title="Home"></i></a></li>
                        <li><a href="javascript:formSubmit()"><i class="fa fa-sign-out" style="font-size:24px" title="Logout"></i></a></li>
                    </ul>
                    <ul class="righttopmenu" style="height: 10px;width: 400px; ">
                        <li style="margin-left:-345;"><a href="tel:+91-9655455604">Call Us : <span>+91-9655455604 </span></a></li>
                       
                        <li><a href="mailto:ramadasspvt@gmail.com"><i class="fa fa-envelope mr-2 "></i><span> ramadasspvt@gmail.com</span></a></li>
                    </ul>
                    
                </div>
            </div>
        </div>
    </div><!--/.container-->
</div><!--/.top-bar-->

<nav class="navbar navbar-inverse" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse navbar-left">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/admin">Home</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Downloads <i class="fa fa-angle-down"></i> </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/downloadTool">Download Tool </a></li>
                        <li><a href="${pageContext.request.contextPath}/downloadLicense"> Download License </a></li>
                        <li><a href="${pageContext.request.contextPath}/downloadEncryption"> Download AMC Encription </a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" >Qurey Encrypter<i class="fa fa-angle-down"></i></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/generateQuery">Generate Query</a></li>
                        <li><a href="${pageContext.request.contextPath}/uploadform">Qurey Decryption</a></li>
                        <li><a href="${pageContext.request.contextPath}/uploadform">License Decryption</a></li>
                   </ul>
                </li>
            </ul>
        </div>
        <div class="collapse navbar-collapse navbar-right">
            <ul class="righttopmenu">
                <li style="font-size:16px" >
                Welcome : ${pageContext.request.userPrincipal.name} 
              </li>
            </ul>
        </div>
   </div><!--/.container-->
</nav><!--/nav-->
</header>
</c:if>              

