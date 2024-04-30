<%-- 
    Document   : GenerateQuery
    Created on : Dec 5, 2013, 4:41:10 AM
    Author     : ADMIN
--%>

<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Download AMC Encryption</title>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
          <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
          <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
          
          <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
          <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
          <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" type="text/css" />
          <script>
            function Optionss() {
                 var sdscode = document.enc.sdscode.value;
                 var option = document.enc.licenseversion.value;
                 
                 if (sdscode==null || sdscode == ""  ) {
                       alert("Please Enter SDS Code ");
                                return false;
                   } 
                 else if(option== null || option=="" || option == "select" ) {
                                alert("Please Select Build Version");
                                return false;
                         }
                      else if (sdscode.length < 5 ) {
                       alert("SDS Code must have five digites");
                                return false;
                   }  
                    
                    return true;
                    
                   }
                 
               function reloadThePage(){
                window.location.href = window.location.href;
                location.reload(true);
                 }  

         
               $(document).ready(function(){
                   //alert('Jqurey is inter');
                    $("#get_mesg").click(function(){
                        //alert('Ajex Calll');
                         $.ajax({
                           url : 'get_enmsg',
                           success : function(data){
                           $("#mesg").html(data);  
                           }
                         })
                      });
                   });
                           
                           $(document).ready(function(){
                              //alert('Jqurey is inter');
                              $("#get_mesg").click(function(){
                                  //alert('Ajex Calll');
                                  $.ajax({
                                      url : 'get_enStatus',
                                      success : function(data){
                                      if (data == 'Enable') {
                                      $('#get_mesg').attr('disabled',false);
                                       }
                                        else  {
                                            //alert("inside else");
                                           $('#get_mesg').attr('disabled',true);
                                           }                     
                                         }                                     
                                  })
                              });                              
                           });                           
        
         </script>
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
    <body class="bodycolor" >
        <%@include file="header.jsp" %>
          <div class="container spacing" >
           <h4 style="margin-top:20px; margin-bottom: 20px;border-bottom:1px solid #c4c4c4; padding-bottom: 10px;">Download AMC Encryption</h4>  
                
           
                <sec:authorize access="hasRole('ROLE_USER')">
                    
                <center>
                   <p id="mesg">${mesg} </p>
                    <br>
                    <form method="GET" class="form-horizontal" name="enc" action="${pageContext.request.contextPath}/downloadEncryptions">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="">SDS Code</label>
                           <div class="col-md-4">
                              <input class="form-control" id="sdscode" name="sdscode" placeholder="SDS Code"  maxlength="5" type="text">
                            </div>
                    <label class="control-label col-sm-2" for="">Build Version</label>
                    <div class="col-md-4">
                        <select name="licenseversion" id="salutation" class="form-control">
                            <option value="select">---Select---</option>
                                   <option value="V3.10">Version 3.10</option>
                                   <option value="V4.2">Version 4.2</option>
                                   <option value="V4.3">Version 4.3</option>
                                   <option value="V5.0">Version 5.0</option>
                                   <option value="V5.1">Version 5.1</option>
                                   <option value="V5.2">Version 5.2</option>
                                   <option value="V5.3.1">Version 5.3.1</option>
                                   <option value="V5.4">Version 5.4</option>
                                   <option value="V5.5">Version 5.5</option>
                                   <option value="V5.6">Version 5.6</option>
                                   <option value="V5.6.1">Version 5.6.1</option>
                                   <option value="V5.8">Version 5.8</option>
                                   <option value="V5.9">Version 5.9</option>
                                   <option value="V6.0">Version 6.0</option>
                        </select>
                    </div>
                  </div> 
                   <div class="row" style="text-align: center;">
                       <button style="margin-bottom: 50px;" type="submit" id="get_mesg" class="btn btn-primary" onclick="return Optionss()" >Download Encyption</button>
                    </div>     
                </form>
                </center>
                    
                </sec:authorize>
             </div>   
         <%@include file="footer.jsp" %>  
                
    </body>

</html>
