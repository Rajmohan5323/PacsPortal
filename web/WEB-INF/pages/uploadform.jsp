<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
 <head>
 <title>Image File Upload</title>
    <script language="JavaScript">
    function SaveFunction(flag)
    {
        if(flag=="process")
         document.forms[0].action='/Spring/savefile';
        else
        {
          document.forms[0].action='/Spring/savefile';
          var tranFile=document.forms[0].file.value;
          if(tranFile.length<1)
          {
              alert("You can't upload without selecting the file." );
              return false;
          }
        }
        
        document.forms[0].submit();
        //onclick=" return SaveFunction('validate')"
        // modelAttribute="formUpload" action="${doUploadURL}"
    }
</script>
 
 </head>
    <body>
    <sec:authorize access="hasRole('ROLE_MANAGER')">  
      <h1>File Upload</h1>

      <h3 style="color:red">${filesuccess}</h3>
      <form:form method="post" action="savefile" enctype="multipart/form-data">
           <p><label for="image">Choose File</label></p>
           <p><input name="file" property="file" id="fileToUpload" type="file" /></p>
           <p><input type="button" value="Upload" onclick="SaveFunction('validate')"></p>

      </form:form>
      </sec:authorize>     
    </body>
</html>