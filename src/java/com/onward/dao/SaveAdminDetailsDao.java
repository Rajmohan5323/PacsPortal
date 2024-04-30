 
package com.onward.dao;

import com.onward.common.DateParser;
import java.io.PrintStream;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SaveAdminDetailsDao
{

  @Autowired
  JdbcTemplate jdbcTemplate;
  
  @Autowired
  private AuditDao auditDao;
  public String saveAdmindetails(String encryptTpassword, String encryptPpassword, String expireDate, String toolusername, String Pusername)
  {
    String message = "";
    try
    {
       String sql = "INSERT INTO maintain ( expiredate, p_password, t_password,t_userid,p_username) VALUES ( '" + DateParser.getDateAndTime(expireDate) + "', '" + encryptPpassword + "', '" + encryptTpassword + "','" + toolusername + "','" + Pusername + "')";
       this.jdbcTemplate.execute(sql);
       message = "Data saved successfully!";
    } catch (Exception e) {
      message = "Failed to update";
   }
    return message;
  }

  public void updateEncHistory(String sdsCode,String buildVersion){
    String userid = this.getUser();
    int count = this.auditDao.getDownloadCount(userid);
    int addcount =++ count;
    String sql = "UPDATE users SET encdownloadcount ='"+addcount+"' WHERE username ='"+userid+"'";
    this.jdbcTemplate.execute(sql); 
    Date currectdate = new Date();
    String sql1 = "INSERT INTO encriptiondownloadhistory (username, sdscode, buildversion,downloaddate) VALUES ( '" + userid + "', '" + sdsCode + "', '" + buildVersion + "','" + DateParser.dateToStringWithTime(currectdate) + "')";
    this.jdbcTemplate.execute(sql1);
   }
  
  public void updateQDHistory(String sdsCode, String qureyDate,String qurey){
    String userid = this.getUser();
    int count = this.auditDao.getQureyDownloadCount(userid);
    int addcount =++ count;
    String sql = "UPDATE users SET qureydownloadcount ='"+addcount+"' WHERE username ='"+userid+"'";
    this.jdbcTemplate.execute(sql); 
    Date currectdate = new Date();
    String sql1 = "INSERT INTO qureydownloadhistory (username, sdscode, qureydate,qurey,downloaddate) VALUES ('" + userid + "','" + sdsCode + "','" + qureyDate + "','" + qurey + "','" + DateParser.dateToStringWithTime(currectdate) + "')";
    this.jdbcTemplate.execute(sql1);  
  }
  
  public void updateLicenseHistory(String licenseVersion,String toolVersion ,String liceseEpiryDate){
    String userid = this.getUser();
    Date currectdate = new Date();
    String sql = "INSERT INTO licensedownloadhistory (username, toolversion, licenseversion,liceseepirydate,downloaddate) VALUES ('" + userid + "','" + toolVersion + "','" + licenseVersion + "','" + DateParser.postgreSQLDate(liceseEpiryDate) + "','" + DateParser.dateToStringWithTime(currectdate) + "')";
    this.jdbcTemplate.execute(sql);        
  }
  
  public void updateToolHistory(String toolVersion){
    String userid = this.getUser();
    Date currectdate = new Date();
    String sql = "INSERT INTO tooldownloadhistory (username, toolversion, downloaddate) VALUES ('" + userid + "','" + toolVersion + "','" + DateParser.dateToStringWithTime(currectdate) + "')";
    this.jdbcTemplate.execute(sql);
  }
  public String getUser(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetail = (UserDetails)auth.getPrincipal();
    String userid = userDetail.getUsername();
    return userid;
  }
}