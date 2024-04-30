package com.onward.security;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class SerialObjectClass
{
  private static FileOutputStream fos;
  private static ObjectInputStream ois;
  private static ObjectOutputStream oos;
  private static FileInputStream fis;

  public static void writeObjIntoFile(String filepath, Map resultMap)
    throws FileNotFoundException, IOException, ClassNotFoundException, EOFException
  {
    try
    {
      File file = new File(filepath);
      boolean exists = file.exists();
      if (exists) {
        file.delete();
        file.createNewFile();
        FileInputStream fis = new FileInputStream(filepath);
        if (fis.available() > 0) {
          ois = new ObjectInputStream(fis);
          resultMap = (Map)ois.readObject();
        }
      }

      if ((resultMap == null) || (resultMap.size() <= 0)) {
        resultMap = new HashMap();
      }

      fos = new FileOutputStream(filepath);
      oos = new ObjectOutputStream(fos);

      oos.writeObject(resultMap);
    }
    finally {
      ois = null;

      fos.flush();
      fos.close();

      oos.flush();
      oos.close();
    }
  }

  public static Object readObjFromFile(String filepath)
    throws IOException, ClassNotFoundException, EOFException
  {
    Object object = null;
    Map resultMap = null;
    try {
      File file = new File(filepath);
      boolean exists = file.exists();
      if (exists) {
        FileInputStream fis = new FileInputStream(filepath);

        ObjectInputStream ois = new ObjectInputStream(fis);

        object = (Map)ois.readObject();
        }
    } finally {
      fis = null;
      ois = null;
    }
    return object;
  }

  public static void writeObjIntoFile(String filepath, HashMap obj)
    throws FileNotFoundException, IOException, ClassNotFoundException, EOFException
  {
    try
    {
      fos = new FileOutputStream(filepath);
      oos = new ObjectOutputStream(fos);
      oos.writeObject(obj);
    } finally {
      ois = null;

      fos.flush();
      fos.close();

      oos.flush();
      oos.close();
    }
  }
}