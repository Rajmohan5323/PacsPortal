package com.onward.controller;

import com.onward.common.DateParser;
import com.onward.dao.AuditDao;
import com.onward.dao.SaveAdminDetailsDao;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;
@Controller

public class MainController
{
    
    @Autowired
    private AuditDao auditDao;
    @Autowired
    private FileDownloadController  fileDownloadController;
    
    @Autowired
    private SaveAdminDetailsDao saveAdminDetailsDao;
    
  @RequestMapping(value={"/", "/welcome**"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView defaultPage()
  {
    ModelAndView model = new ModelAndView();
    model.addObject("title", "Spring");
    model.addObject("message", "This is default page!");
    model.setViewName("hello");
    return model;
  }
  @RequestMapping(value={"/admin**"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView adminPage() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, InvalidKeyException, IOException, BadPaddingException, ParseException
  {
    ModelAndView model = new ModelAndView();
      String licenseExpireDate =  this.auditDao.getExpireDate();
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      java.util.Date licenseExpire = dateFormat.parse(licenseExpireDate);
      String currentDate = DateParser.getToday("dd/MM/yyyy");
      Date todayDate = dateFormat.parse(currentDate);
      if(todayDate.compareTo(licenseExpire) == 1){
           model.addObject("error", "Please contact vendor!!!");
           model.setViewName("login");      
      }
      if(todayDate.compareTo(licenseExpire) == -1){
      model.setViewName("admin");
      }
      return model;
  }

  @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  
  public ModelAndView login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout)
  {
    ModelAndView model = new ModelAndView();
    if (error != null) {
      model.addObject("error", "Invalid username and password!");
    }
    if (logout != null) {
      model.addObject("msg", "You've been logged out successfully.");
    }
    model.setViewName("login");
    return model;
  }
  
  @RequestMapping(value={"/403"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView accesssDenied()
  {
    ModelAndView model = new ModelAndView();

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (!(auth instanceof AnonymousAuthenticationToken)) {
      UserDetails userDetail = (UserDetails)auth.getPrincipal();
      model.addObject("username", userDetail.getUsername());
    }

    model.setViewName("403");
    return model;
  }
  @RequestMapping(value={"/downloadTool"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView downloadTool()
  {
    ModelAndView model = new ModelAndView();
    model.addObject("title", "Spring");
    model.addObject("message", "This is Download page!");
    model.setViewName("downloadTool");
    return model;
  }

  
  @RequestMapping(value={"/downloadLicense"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView downloadLicense() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, IOException
  {
    ModelAndView model = new ModelAndView();
    String expireDate =  this.auditDao.getExpireDate();
    model.addObject("Expire", "License Expiry Date is "+expireDate);
    model.setViewName("downloadLicense");
    return model;
  }
    @RequestMapping(value={"/uploadform"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView uploadform()
  {
    ModelAndView model = new ModelAndView();
    model.addObject("Message", "Message ");
    model.setViewName("uploadform");
    return model;
  }
    @RequestMapping(value={"/downloadEncryption"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView encryptQurey()
  {
    ModelAndView model = new ModelAndView();
    String userid = this.saveAdminDetailsDao.getUser();
    int count = this.auditDao.getDownloadCount(userid);
         if(count >= 10 ){
         model.setViewName("subscriptionPage");
     }
    else if (count <10){
       int remainCount = 10 - count;
        model.addObject("mesg", "You Have Remaining "+remainCount+" Downloads More ");      
        model.setViewName("downloadEncryption");
    }
    return model;
  }
    
     @RequestMapping(value={"/get_enmsg"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
     @ResponseBody
    public String getEnMessage(){
        String userid = this.saveAdminDetailsDao.getUser(); 
        int count = this.auditDao.getDownloadCount(userid);
        int counting = 10 - count;
        StringBuilder sb = new StringBuilder();
        String message = sb.append("You Have Remaining ").append(counting).append(" Downloads More").toString();
               
        return message;
    }
     
     @RequestMapping(value={"/get_enStatus"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
     @ResponseBody
     public String getEnStatus(){
        String msg = "";
        String userid = this.saveAdminDetailsDao.getUser();
        int cnt = this.auditDao.getDownloadCount(userid);
        if(cnt < 10){
          msg = "Enable";   
        }else if(cnt>=10){
          msg = "Disable";  
        }
        
        return msg;
     }
     
     @RequestMapping(value={"/get_qdmsg"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
     @ResponseBody
    public String getQdMessage(){
        String userid = this.saveAdminDetailsDao.getUser(); 
        int count = this.auditDao.getQureyDownloadCount(userid);
        int counting = 10 - count;
        StringBuilder sb = new StringBuilder();
        String message = sb.append("You Have Remaining ").append(counting).append(" Downloads More").toString();
        return message;
    }
     @RequestMapping(value={"/get_qdStatus"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
     @ResponseBody
     public String getQdStatus(){
        String msg = "";
        String userid = this.saveAdminDetailsDao.getUser(); 
        int cnt = this.auditDao.getQureyDownloadCount(userid);
        if(cnt < 10){
          msg = "Enable";   
        }else if(cnt>=10){
          msg = "Disable";  
        }
        
        return msg;
     }
     
    @RequestMapping(value={"/generateQuery"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView generateQuery()
  {
    ModelAndView model = new ModelAndView();
    String userid = this.saveAdminDetailsDao.getUser();
    int count = this.auditDao.getQureyDownloadCount(userid);
       if(count >= 10 ){
       model.setViewName("subscriptionPage");
     }
    else if (count <10){
        int remainCount = 10 - count;
        model.addObject("message", "You Have Remaining "+remainCount+" Downloads More ");      
        model.setViewName("generateQuery");
    }    
    return model;
  }
}