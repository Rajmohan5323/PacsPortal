package com.onward.controller;

import com.jcraft.jsch.Session;
import com.onward.common.DateParser;
import com.onward.dao.AuditDao;

import com.onward.dao.SaveAdminDetailsDao;
import com.onward.security.EncEncrypt;
import com.onward.security.SerialObjectClass;
import com.onward.security.UserEncrypt;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FileDownloadController
{

  @Autowired
  ServletContext context;

  @Autowired
  private AuditDao auditDao;
  private static transient String query1 = "UPDATE branchparameters SET isbranchamc = '";
  private static transient String query2 = "';";
  @Autowired
   SaveAdminDetailsDao saveAdminDetailsDao;
   static final int BUFFER_SIZE = 4096;
   String filePath1 = "/downloads/store.rar";
   String conifgurefile = "/downloads/query.txt";
    
  
  
  @RequestMapping(value={"/download"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public void doDownload(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    String appPath = this.context.getRealPath("");
    String filePath = request.getParameter("toolVersion");
    String fullPath = appPath + filePath;
    File downloadFile = new File(fullPath);
    FileInputStream inputStream = new FileInputStream(downloadFile);
    String mimeType = this.context.getMimeType(fullPath);
    if (mimeType == null)
    {
      mimeType = "application/octet-stream";
    }
    System.out.println("MIME type: " + mimeType);
    response.setContentType(mimeType);
    response.setContentLength((int)downloadFile.length());
    String headerKey = "Content-Disposition";
    String headerValue = String.format("attachment; filename=\"%s\"", new Object[] { downloadFile.getName() });
    response.setHeader(headerKey, headerValue);
    OutputStream outStream = response.getOutputStream();
    byte[] buffer = new byte[4096];
    int bytesRead = -1;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      outStream.write(buffer, 0, bytesRead);
    }
    inputStream.close();
    outStream.close();
    this.saveAdminDetailsDao.updateToolHistory(filePath);
  }

  @RequestMapping(value={"/downloadLicenses"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public void dodownloadLicenses(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
   try {
      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String userName = user.getUsername();
      String licensVersion = request.getParameter("licenseversion");
      String toolVersion = request.getParameter("toolversoinlicense");
      String pathString = this.auditDao.getPatchfile(licensVersion,toolVersion);
      String[] configDetails = pathString.split(",");
      String expiryDate = configDetails[5];
      response.setContentType("text/csv");
      response.setHeader("Content-Disposition", "attachment; filename=\"" + toolVersion +"-toollicense-"+licensVersion+"-"+DateParser.postgreSQLDate(expiryDate)+".txt\"");
      OutputStream outputStream = response.getOutputStream();
      String outputResult = pathString;
      UserEncrypt encrypt = new UserEncrypt();
      String encrypted = encrypt.encrypt(outputResult);
      outputStream.write(encrypted.getBytes());
      outputStream.flush();
      outputStream.close();
      this.saveAdminDetailsDao.updateLicenseHistory(licensVersion, toolVersion, expiryDate);
    }
    catch (Exception e) {
      System.out.println(e.toString());
    }
        
  }

  @RequestMapping(value={"/encryptquery"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public void doGenerateQuery(HttpServletRequest request, HttpServletResponse response)
  {
    UserEncrypt encrypt = new UserEncrypt();
    String query = request.getParameter("query");
    String sdscode = request.getParameter("sdscode");
    String dateofqueryissue = request.getParameter("dateofqueryissued");
    String currentdate = dateofqueryissue;
    String contextPath = request.getContextPath();
    String appPath = this.context.getRealPath("");

    Map resultMap = new HashMap();
    try {
      String encryptedSdscode = encrypt.encrypt("sdscode");
      String encryptedQuery = encrypt.encrypt("query");
      String encryptedCurrentdate = encrypt.encrypt("currentdate");
      resultMap.put(encryptedSdscode, encrypt.encrypt(sdscode));
      resultMap.put(encryptedQuery, encrypt.encrypt(query));
      resultMap.put(encryptedCurrentdate, encrypt.encrypt(currentdate));
      SerialObjectClass.writeObjIntoFile(appPath + this.conifgurefile, resultMap);
    }
    catch (Exception e) {
    }
    File file = new File(appPath + this.conifgurefile);
    try {
      Map reslutmap = (Map)SerialObjectClass.readObjFromFile(appPath + this.conifgurefile);
      Map reslut = new HashMap();
      Iterator it = reslutmap.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        reslut.put(encrypt.decrypt(String.valueOf(pair.getKey())), encrypt.decrypt(String.valueOf(pair.getValue())));
        it.remove();
      }
          }
    catch (Exception e)
    {
    }

    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=\"" + sdscode + "-" + currentdate + "-query.txt\"");
    OutputStream outputStream = null;
    try {
      outputStream = response.getOutputStream();
      outputStream.write(read(file));
      outputStream.flush();
      outputStream.close();
    } catch (IOException ex) {
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
   this.saveAdminDetailsDao.updateQDHistory(sdscode, currentdate, query); 
  }
  
  
  
  @RequestMapping(value={"/downloadEncryptions"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public void downloadEncryption(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
  {    
    UserEncrypt encrypt = new UserEncrypt();
    EncEncrypt encEncript = new EncEncrypt();
    String sdscode = request.getParameter("sdscode");
    String licenseversion = request.getParameter("licenseversion");
    StringBuilder str = new StringBuilder();
    String enc = str.append(sdscode).append("-Y#").append(licenseversion).toString();
    String encryptedEncryption = encEncript.encrypt(enc);
    String query = new StringBuilder().append(query1).append(encryptedEncryption).append(query2).toString(); 
    String contextPath = request.getContextPath();
    String appPath = this.context.getRealPath("");
    Map resultMap = new HashMap();
    try {
    String encryptedSamc = encrypt.encrypt("amc");
    String encryptedCamc = encrypt.encrypt("Amc");
    String encamc = encrypt.encrypt("amcquery");
    String qureyEnc = encrypt.encrypt(query);
    resultMap.put(encrypt.encrypt(sdscode), encrypt.encrypt(sdscode));
    resultMap.put(encryptedSamc, encryptedCamc);
    resultMap.put(encamc, qureyEnc);
    SerialObjectClass.writeObjIntoFile(appPath + this.conifgurefile, resultMap);
    }
    catch (Exception e) {
    }
    File file = new File(appPath + this.conifgurefile);
    try {
      Map reslutmap = (Map)SerialObjectClass.readObjFromFile(appPath + this.conifgurefile);
      Map reslut = new HashMap();
      Iterator it = reslutmap.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        reslut.put(encrypt.decrypt(String.valueOf(pair.getKey())), encrypt.decrypt(String.valueOf(pair.getValue())));
        it.remove();
      }
     }
    catch (Exception e)
    {
        System.out.println("File Enc Exception ");
    }
    
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=\"" + sdscode + "-" + licenseversion + "-Encryption.txt\"");
    
    OutputStream outputStream = null;
    try {
      outputStream = response.getOutputStream();
      outputStream.write(read(file));
      outputStream.flush();
      outputStream.close();
    } catch (IOException ex) {
       
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
      
    }
    this.saveAdminDetailsDao.updateEncHistory(sdscode, licenseversion);
} 
  

  public byte[] read(File file)
    throws IOException
  {
    ByteArrayOutputStream ous = null;
    InputStream ios = null;
    try {
      byte[] buffer = new byte[4096];
      ous = new ByteArrayOutputStream();
      ios = new FileInputStream(file);
      int read = 0;
      while ((read = ios.read(buffer)) != -1)
        ous.write(buffer, 0, read);
    }
    finally {
      try {
        if (ous != null)
          ous.close();
      }
      catch (IOException e)
      {
      }
      try {
        if (ios != null)
          ios.close();
      }
      catch (IOException e) {
      }
    }
    
    return ous.toByteArray();
  }

  @RequestMapping(value={"/passwordCreation"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView passwordCreation(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView model = new ModelAndView();
    try {
      String tusername = request.getParameter("tusername");
      String tpassword = request.getParameter("tpassword");
      String ppassword = request.getParameter("ppassword");
      String dateofexpire = request.getParameter("expiredate");
      String pusername = request.getParameter("Pusername");
      UserEncrypt encrypt = new UserEncrypt();
      String encryptedTpassword = encrypt.encrypt(tpassword);
      String encryptedPpassword = encrypt.encrypt(ppassword);
      String encryptedtusername = encrypt.encrypt(tusername);
      String encryptedpusername = encrypt.encrypt(pusername);
      String message = this.saveAdminDetailsDao.saveAdmindetails(encryptedTpassword, encryptedPpassword, dateofexpire, encryptedtusername, encryptedpusername);
      model.addObject("title", "Spring");
      model.addObject("message1", message);
      model.setViewName("admin");
    }
    catch (NoSuchAlgorithmException ex)
    {
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidKeySpecException ex) {
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchPaddingException ex) {
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidKeyException ex) {
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidAlgorithmParameterException ex) {
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalBlockSizeException ex) {
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (BadPaddingException ex) {
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return model;
  }

  
  public String getExpiryDate(String licenseissuedate) {
    String expiryDate = "";
    try {
      int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
      int s = 0; int flag = 0;
      String[] week = new String[7];
      week[0] = "Sunday";
      week[1] = "Monday";
      week[2] = "Tuesday";
      week[3] = "Wednesday";
      week[4] = "Thursday";
      week[5] = "Friday";
      week[6] = "Saturday";
      String[] splitOut = licenseissuedate.split("/");
      int date1 = Integer.parseInt(splitOut[0]);
      int month = Integer.parseInt(splitOut[1]);
      int year = Integer.parseInt(splitOut[2]);
      if (((year % 100 != 0) && (year % 4 == 0)) || (year % 400 == 0)) {
        flag = 1;
        months[1] = 29;
      }
      for (int i = 0; i < month - 1; i++) {
        s += months[i];
      }
      s += date1 + year + year / 4 - 2;
      s %= 7;
      if (flag == 1) {
        s -= 1;
      }
      if (s != 6)
        s = 6 - s;
      else {
        s = 0;
      }
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      Calendar cal = Calendar.getInstance();
      cal.setTime(sdf.parse(licenseissuedate));
      cal.add(5, s);
      expiryDate = sdf.format(cal.getTime());
      System.out.println("After add" + cal.getTime());
      System.out.println("Time:::" + sdf.format(cal.getTime()));
    } catch (ParseException ex) {
      Logger.getLogger(FileDownloadController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return expiryDate;
  }
}