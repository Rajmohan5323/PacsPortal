package com.onward.common;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class DateParser
  implements Serializable
{
  public static final String Dtformat1 = "dd/MM/yyyy";

  public static String dateToString(java.util.Date date)
  {
    SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy");
    String strDate = "";
    if (date != null) {
      strDate = sdfOutput.format(date);
    }
    return strDate;
  }

  public static String dateToString1(java.util.Date date)
  {
    SimpleDateFormat sdfOutput = new SimpleDateFormat("MM/dd/yyyy");
    String strDate = "";
    if (date != null) {
      strDate = sdfOutput.format(date);
    }
    return strDate;
  }

  public static String timeToString(Time time)
  {
    SimpleDateFormat timeoutput = new SimpleDateFormat("HH:mm:ss");
    String strDate = "";
    if (time != null) {
      strDate = timeoutput.format(time);
    }
    return strDate;
  }

  public static String dateToTime(java.util.Date date)
  {
    SimpleDateFormat sdfOutput = new SimpleDateFormat("HH:mm:ss");
    String strTime = "";
    if (date != null) {
      strTime = sdfOutput.format(date);
    }
    return strTime;
  }

  public static String dateToStringWithTime(java.util.Date date)
  {
    SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
    String strDate = "";
    if (date != null) {
      strDate = sdfOutput.format(date);
    }
    return strDate;
  }

  public static Timestamp postgresDateWithTime(String date)
  {
    Timestamp sqlDate = null;
    try {
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
      java.util.Date badDate = formatter.parse(date);
      sqlDate = new Timestamp(badDate.getTime());
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    return sqlDate;
  }

  public static java.util.Date getUtilDate(String dateFromJsp, String timeFromJsp)
  {
    java.util.Date jspDateAndTime = null;
    try {
      SimpleDateFormat jspFormat = new SimpleDateFormat("dd/mm/yyyy H:mm:ss");
      jspDateAndTime = jspFormat.parse(dateFromJsp + " " + timeFromJsp);
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    return jspDateAndTime;
  }

  public static java.sql.Date postgresDate(String date)
  {
    java.sql.Date sqlDate = null;
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      if ((date != null) && (date.length() > 0)) {
        java.util.Date utilDate = dateFormat.parse(date);
        sqlDate = new java.sql.Date(utilDate.getTime());
      } else {
        sqlDate = null;
      }
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    return sqlDate;
  }

  public static Timestamp postgreSQLDateWithTime(String date)
  {
    Timestamp sqlDate = null;
    try {
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
      java.util.Date badDate = formatter.parse(date);
      sqlDate = new Timestamp(badDate.getTime());
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    return sqlDate;
  }

  public static java.sql.Date postgreSQLDate(String date)
  {
    java.sql.Date sqlDate = null;
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      if ((date != null) && (date.length() > 0)) {
        java.util.Date utilDate = dateFormat.parse(date);
        sqlDate = new java.sql.Date(utilDate.getTime());
      } else {
        sqlDate = null;
      }
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    return sqlDate;
  }

 public static Date postgresDate1( String strDateValue) {
        Date sqlDateValue = null;
        try {
            if (strDateValue != null && !strDateValue.isEmpty() && strDateValue.length() > 0) {
                 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                 java.util.Date utilDate = dateFormat.parse(strDateValue);
                sqlDateValue = new Date(utilDate.getTime());
            }
            else {
                sqlDateValue = null;
            }
        }
        catch (ParseException pe) {
            System.out.println("Parsing Exception in date formatting..." + pe);
        }
        return sqlDateValue;
    }
        
        
  
  public static Timestamp postgresSQLDateTime(String date)
  {
    Timestamp sqlDate = null;
    try {
      if ((date != null) && (date.length() > 10)) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
        java.util.Date utilDate = dateFormat.parse(date);
        sqlDate = new Timestamp(utilDate.getTime());
      } else if ((date != null) && (date.length() > 0)) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = formatter.parse(date);
        sqlDate = new Timestamp(utilDate.getTime());
      } else {
        sqlDate = null;
      }
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    return sqlDate;
  }

  public static String getDateAndTime(String dateFormat)
  {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    System.out.println(sdf.format(cal.getTime()));
    return sdf.format(cal.getTime());
  }

  public static String getToday(String formateType)
  {
    String todaysDate = null;
    try {
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat dateFormat = new SimpleDateFormat(formateType);
      todaysDate = dateFormat.format(cal.getTime());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return todaysDate;
  }

  public static java.util.Date getUtilDate(String date) {
    java.util.Date utilDate = null;
    try {
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      if ((date != null) && (date.length() > 0))
        utilDate = dateFormat.parse(date);
      else
        utilDate = null;
    }
    catch (ParseException pe) {
      pe.printStackTrace();
    }
    
    return utilDate;
  }

  public static String convertTheDateIntoDisplayFormat(String dt)
  {
    String strDate = "";
    try {
      StringTokenizer st = new StringTokenizer(dt, "-");
      String s1 = st.nextToken();
      String s2 = st.nextToken();
      String s3 = st.nextToken();
      strDate = s3 + "/" + s2 + "/" + s1;
    }
    catch (Exception pe) {
      pe.printStackTrace();
    }
    return strDate;
  }

  public static long yearsBetweenDates(String DateString1, String DateString2)
  {
    try
    {
      Calendar c1 = getCalender("dd/MM/yyyy", DateString1);
      Calendar c2 = getCalender("dd/MM/yyyy", DateString2);
      return Math.abs(c2.get(1) - c1.get(1)); } catch (ParseException e) {
    }
    return 0L;
  }

  private static Calendar getCalender(String DateFt, String DateObj)
    throws ParseException
  {
    SimpleDateFormat fm = new SimpleDateFormat(DateFt);
    fm.setLenient(false);
    fm.parse(DateObj);
    return fm.getCalendar();
  }

  public static java.sql.Date nextDate(long today, long noofdays)
  {
    long millisInDay = 86400000L;
    long nextDate = today + noofdays * millisInDay;
    return new java.sql.Date(nextDate);
  }

  public static java.sql.Date prevDate(long today, long noofdays)
  {
    long millisInDay = 86400000L;
    long nextDate = today - noofdays * millisInDay;
    return new java.sql.Date(nextDate);
  }
}