<%-- 
    Document   : DownloadTool
    Created on : Jul 5, 2021, 6:06:26 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Download Tool</title>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
          <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
          <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
          <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" type="text/css" />
          <script>
            function Optionss() {
                 var option = document.tool.toolVersion.value;
                 
                 
                  if(option== null || option=="" || option == "select" ) {
                                alert("Please Select Tool Version");
                                return false;
                         }   
     
                    return true;
                      }
           </script>
          
    </head>
    <body class="bodycolor">
        <%@include file="header.jsp" %>
        <div class="container spacing">
           <h4 style="margin-top:20px; margin-bottom: 20px;border-bottom:1px solid #c4c4c4; padding-bottom: 10px;">Download Tool</h4>  
               
                <sec:authorize access="hasRole('ROLE_USER')">
                    
                <center>
                    <form method="GET" class="form-horizontal" name="tool" action="${pageContext.request.contextPath}/download">
                        <div class="form-group">
                            
                         <label class="control-label col-sm-4">Tool Version</label>
                            <div class="col-md-3">
                              <select  name="toolVersion" id="functiontype" class="form-control" required>
                                <option value="select">---Select---</option>
                                <option value="/downloads/PACSTool 3.10.rar">Version 3.10</option>
                                <option value="/downloads/PACSTool 4.2.rar">Version 4.2</option>
                                <option value="/downloads/PACSTool 4.3.rar">Version 4.3</option>
                              </select>
                             </br> 
                            </br>
                        <div style="text-align: center;">
                        <button style="margin-bottom: 50px;" type="submit" class="btn btn-lg btn-primary btn-block " value="Download License" onclick="return Optionss()" >Download Tool</button>
                        </div>  
                            </div>
                        </div> 
                    </form>
                </center>
                </sec:authorize>
             </div>   
         <%@include file="footer.jsp" %>  
    </body>
</html>
