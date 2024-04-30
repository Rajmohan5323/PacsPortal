    <%-- 
    Document   : DownloadLicense
    Created on : Jul 10, 2021, 10:22:55 AM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Download License</title>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
          <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
          <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
          <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" type="text/css" />
          <style type="text/css"> 
                p
                {
                  animation-name: blink-text;
                  animation-duration: 1s;
                  animation-iteration-count: infinite;
                  color: red;
                }
          </style>
          
    </head>
    <body class="bodycolor">
        <%@include file="header.jsp" %>
        <div class="container spacing">
        <h4 style="margin-top:20px; margin-bottom: 20px;border-bottom:1px solid #c4c4c4; padding-bottom: 10px;">Download License</h4>    
        <sec:authorize access="hasRole('ROLE_USER')">
            <center>
                 <c:if test="${not empty Expire}">
                 <p>${Expire} </p>
                 </c:if>
                 <br>
                  <form method="GET" name="login" action="${pageContext.request.contextPath}/downloadLicenses"> 
                  <div class="form-group">
                    
                    <div class="row">  
                        <label class="control-label col-sm-4 col-md-2" style="margin-bottom: 0px; margin-top: 10px;">Tool Version</label>
                        <div class="col-sm-4 col-md-3 form-group">
                              
                             <div class="radio">
                                <label style="padding-right: 140px;" >Version 3.10<input type="radio" name="toolversoinlicense" value="V1.0"></label>
                                
                             </div>
                            </div>  
                        
                         <div class="col-sm-4 col-md-3 form-group">
                              
                             <div class="radio">
                                <label style="padding-right: 150px;" >Version 4.2<input type="radio" name="toolversoinlicense" value="V2.0"></label>
                            </div>
                         </div>
                         <div class="col-sm-4 col-md-3 form-group">
                              
                             <div class="radio">
                                <label style="padding-right: 150px;" >Version 4.3<input type="radio" name="toolversoinlicense" value="V3.0"></label>
                            </div>
                         </div>    
                       </div>
                     </div>   
                    <div class="form-group">
                        <label class="control-label col-sm-2" style="margin-top: 8px;">License Version</label>
                        <div class="col-md-3">
                        <select name="licenseversion" id="functiontype" class="form-control">
                            <option value="select">---Select---</option>
                                   <option value="V3.10">V3.10</option>
                                   <option value="V4.2">V4.2</option>
                                   <option value="V4.3">V4.3</option>
                                   <option value="V5.0">V5.0</option>
                                   <option value="V5.1">V5.1</option>
                                   <option value="V5.2">V5.2</option>
                                   <option value="V5.3.1">V5.3.1</option>
                                   <option value="V5.4">V5.4</option>
                                   <option value="V5.5">V5.5</option>
                                   <option value="V5.6">V5.6</option>
                                   <option value="V5.6.1">V5.6.1</option>
                                   <option value="V5.8">V5.8</option>
                                   <option value="V5.9">V5.9</option>
                                   <option value="V6.0">V6.0</option>
                        </select>
                            <br>
                            <br>
                        <div style="text-align: center;">
                        <button style="margin-bottom: 50px;" type="submit" class="btn btn-lg btn-primary btn-block " value="Download License" onclick="return Optionss()" >Download License</button>
                        </div>    
                    </div>
                    </div>     
                  </div>  
                </form> 
            </center>
            <script>
            function Optionss() {
                 var option = document.login.licenseversion.value;
                 var radio = document.login.toolversoinlicense.value;
                 
                 if (radio==null || radio == "") {
                       alert("Please Select Toolversion ");
                                return false;
                   } 
               else if(option== null || option=="" || option == "select" ) {
                                alert("Please Select LicensesVersion");
                                return false;
                         }   
     
                    return true;
                      }
           </script>
          </sec:authorize>
        <%@include file="footer.jsp" %>
    </body>
</html>
