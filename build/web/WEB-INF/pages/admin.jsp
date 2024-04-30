<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
          <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
          <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
          <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
          <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
          <link href="${pageContext.request.contextPath}/css/responsive.css" rel="stylesheet" type="text/css" />
    <script>


$(document).ready(function(){
        $('#tpassword, #ctpassword').on('keyup', function () {
            if ($('#tpassword').val() == $('#ctpassword').val()) {
                $('#message').html('Matching').css('color', 'green');
            } else 
                $('#message').html('Not Matching').css('color', 'red');
        });
        $('#ppassword, #cppassword').on('keyup', function () {
            if ($('#ppassword').val() == $('#cppassword').val()) {
                $('#message1').html('Matching').css('color', 'green');
            } else 
                $('#message1').html('Not Matching').css('color', 'red');
        });
        
        $(".readonly").keydown(function(e){
        e.preventDefault();
        });
    });


function validate()
{
  var a=documents.login.tpassword.value;
  var b=documents.login.ctpassword.value;
  var c=documents.forms["login"]["ppassword"].value;
  var d=documents.forms["login"]["cppassword"].value;

  if(!(a==b))
  {
    alert("both tool passwords are not matching");
    return false;
  }
 if(!(c==d))
  {
    alert("both postgres passwords are not matching");
    return false;
  }  
  return true;
}


function checkForm()
{
    x = document.forms[0].tpassword.value
    if(x!=""){
     if(document.forms[0].tpassword.value.length < 6) {
       alert("Error: Password must contain at least six characters!");
     document.forms[0].currentPassword.value="";
           return false;
                    }
                }
            }

  function showPage(divname){
         if(divname=='querydiv'){
         document.getElementById("querydiv").style.display='';
         }else if(divname=='passwordcreation'){
          document.getElementById("passwordcreation").style.display='';
          document.getElementById("querydiv").style.display='none';
         }
       }



  </script>
<style>
body{background: #eee; font-family: verdana, calibri; margin: 0px; padding: 0px}
#tabscontent {
	-moz-border-radius-topleft: 0px;
	-moz-border-radius-topright: 4px;
	-moz-border-radius-bottomright: 4px;
	-moz-border-radius-bottomleft: 4px;
        
	border-top-left-radius: 0px;
	border-top-right-radius: 4px;
	border-bottom-right-radius: 4px;
	border-bottom-left-radius: 4px; 
	margin:0px;
	color:#333;
}

.lightrow{
    background-color:#7D7D7D;
    height: 40px;
}
.darkrow{
    background-color:#D5E0E4; 
    height: 40px;}
tr{
    height: 20px;
    font-weight: bold;
    text-align: center;
}
input[type="text"],input[type="password"],input[type="date"]{
    width: 100%;
    padding:9px;
    
}

/*---------------------------------------------------------------------------------*/   

</style>
</head>
    <body>
        <%@include file="header.jsp" %> 

            
       <%@include file="footer.jsp" %>        
            
    </body>

</html>