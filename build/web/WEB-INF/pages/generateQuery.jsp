<%-- 
    Document   : GenerateQuery
    Created on : Dec 5, 2013, 4:41:10 AM
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
        <title>Generate Query</title>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
          <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
          <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
          <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
          <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
          <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" type="text/css" />
          <script>
            function Options() {
                 var sdscode = document.queryGeneration.sdscode.value;
                 var dateof = document.queryGeneration.dateofqueryissued.value;
                 
                 if (sdscode==null || sdscode == ""  ) {
                       alert("Please Enter SDS Code ");
                                return false;
                   } 
               else if(dateof== null || dateof=="" ) {
                                alert("Please Select dateofqueryissued");
                                return false;
                         }
                      else if (sdscode.length < 5 ) {
                       alert("SDS Code must have five digites");
                                return false;
                   }  
                         
     
                    return true;
                      }
                      
              $(document).ready(function(){
                   //alert('Jqurey is inter');
                    $("#submitbtn").click(function(){
                        //alert('Ajex Calll');
                         $.ajax({
                           url : 'get_qdmsg',
                           success : function(data){
                           $("#message").html(data);  
                           }
                         })
                      });
                   });
                           
                           $(document).ready(function(){
                              //alert('Jqurey is inter');
                              $("#submitbtn").click(function(){
                                  //alert('Ajex Calll');
                                  $.ajax({
                                      url : 'get_qdStatus',
                                      success : function(data){
                                      if (data == 'Enable') {
                                      $('#submitbtn').attr('disabled',false);
                                       }
                                        else  {
                                            //alert("inside else");
                                           $('#submitbtn').attr('disabled',true);
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
    <body class="bodycolor">
        <%@include file="header.jsp" %>
        
      <sec:authorize access="hasRole('ROLE_USER')">
        <div class="container spacing">
           <h4 style="margin-top:20px; margin-bottom: 20px;border-bottom:1px solid #c4c4c4; padding-bottom: 10px;">Generate AMC Query</h4>  
               
                    
                <center>
                    <p id="message" >${message}</p>
                    <br>
                    <form method="GET" class="form-horizontal" name="queryGeneration" action="${pageContext.request.contextPath}/encryptquery">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="">SDS Code</label>
                           <div class="col-md-3">
                              <input class="form-control" id="sdscode" name="sdscode" placeholder="SDS Code"  maxlength="5" type="text">
                            </div>
                    <label class="control-label col-sm-3" for="">Date Of Query Issued</label>
                    <div class="col-md-3">
                        <input class="form-control" name="dateofqueryissued" id="applicationdate" value="" placeholder="Date" type="date">
                        <img id="datepicker" src="/Pacs/images/jscalendar.gif" style="margin-left: 247px;">
                    </div>
                       </div>
                     <div class="form-group" >
                           <label class="control-label col-sm-3">Enter Your Qurey Here</label>
                     </div>
                        <div class="commonbox12" id="enquirynotice">
                            <div class="form-group"> 
                               <div class="col-md-10" style="margin-left: 95px;">
                                    <textarea type="text" class="form-control" name="query"  value="" placeholder="Enter Your Qurey Here..." style="margin-bottom: 6px; padding-bottom: 69px; border-bottom-width: 1px;" ></textarea>
                               </div>
                            </div>
                        </div>  
                   <div class="row" style="text-align: center;">
                       <button style="margin-bottom: 50px;" type="submit" id="submitbtn" class="btn btn-primary" onclick="return Options()">Download Query File</button>
                    </div>     
                    </form>
                </center>
                    
                </sec:authorize>
             </div>   
         <%@include file="footer.jsp" %>  
                
    </body>

</html>
