package com.onward.dao;

import com.onward.common.DateParser;
import com.onward.model.User;
import com.onward.security.UserEncrypt;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service

public class AuditDao
{
  @Autowired
  JdbcTemplate jdbcTemplate;

  public String getPatchfile(String licenseVersion,String toolVersion  )
  {
    String patchscript = "";
    String sql = "SELECT toolversion,p_username,p_password,t_userid,t_password,expire from postgrespasswords where licenseversion = '"+ licenseVersion +"' and toolversion = '"+toolVersion+"'";
     List users = new ArrayList();
     users = this.jdbcTemplate.query(sql, new UserRowMapper());
    if (users != null) {
      try {
        User user = (User) users.get(0);
        UserEncrypt encrypt = new UserEncrypt();
        patchscript = user.getToolversion()+ "," + user.getUsername() + "," + user.getP_password() + "," + user.getT_userid() + "," + user.getT_password() + "," + DateParser.dateToString(user.getExpiredate());
         } catch (Exception ex) {
        Logger.getLogger(AuditDao.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return patchscript;
  }

  public int getDownloadCount(String userid)
  {
       int count = 0; 
       String sql = "SELECT encdownloadcount from users where username ='"+ userid +"'";
      try{
         count  = jdbcTemplate.queryForInt(sql);
       
       }catch(Exception e){
      System.out.println("Err--->"+e);
  }  
      return count;
    }
  
  public int getQureyDownloadCount(String userid) {
    int queryDLCount = 0;
    String sql = "SELECT qureydownloadcount from users where username ='"+ userid +"'";
       try{
         queryDLCount  = jdbcTemplate.queryForInt(sql);
       }catch(Exception e){
      System.out.println("Err--->"+e);
  }
      return queryDLCount;
}
  
  public String getExpireDate() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException{
      String sql = "SELECT distinct (expire) from postgrespasswords"; 
      Date expireDate = jdbcTemplate.queryForObject(sql, java.util.Date.class);     
      String dated = DateParser.dateToString(expireDate);
      
      return dated;
  }
 }