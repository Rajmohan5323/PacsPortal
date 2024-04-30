package com.onward.model;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class User
{
  
  private String toolversion;
  private String username;
  private String p_password;
  private String t_password;
  private String enabled;
  private String sdscode;
  private String t_userid;
  private Date expiredate;


    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }
  
  public String getToolversion() {
        return toolversion;
    }

    public void setToolversion(String toolversion) {
        this.toolversion = toolversion;
    }


  public String getT_userid()
  {
    return this.t_userid;
  }

  public void setT_userid(String t_userid) {
    this.t_userid = t_userid;
  }

  public String getEnabled()
  {
    return this.enabled;
  }

  public void setEnabled(String enabled) {
    this.enabled = enabled;
  }
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSdscode() {
    return this.sdscode;
  }

  public void setSdscode(String sdscode) {
    this.sdscode = sdscode;
  }



  public String getP_password() {
    return this.p_password;
  }

  public void setP_password(String p_password) {
      
    this.p_password = p_password;
    
  }

  public String getT_password() {
    return this.t_password;
  }

  public void setT_password(String t_password) {
    this.t_password = t_password;
  }
  public User(){
      
  }
  
}