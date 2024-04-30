package com.onward.dao;

import com.onward.model.User;
import com.onward.security.UserEncrypt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper  implements RowMapper<User>
  
{
    
    @Override
  public User mapRow(ResultSet rs, int i) throws SQLException 
  {
    User user = new User();
    UserEncrypt encrypt = new UserEncrypt();
   try
    { 
    user.setToolversion(rs.getString("toolversion")); 
    user.setT_userid(rs.getString("t_userid"));
    user.setT_password(rs.getString("t_password"));
    user.setUsername(encrypt.decrypt(rs.getString("p_username")));
    user.setP_password(encrypt.decrypt(rs.getString("p_password")));
    user.setExpiredate(rs.getDate("expire"));
    } catch (Exception ex) {
      Logger.getLogger(UserRowMapper.class.getName()).log(Level.SEVERE, null, ex);
    }
    return user;
  }
}