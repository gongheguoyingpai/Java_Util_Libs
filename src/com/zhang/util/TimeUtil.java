package com.zhang.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	private static final String CMD_FORMAT_STR = "cmd /C dir %s /tc";
	private static final String TIME_FORMAT_STRING = "yyyy/MM/dd HH:mm";
	
	public static Date getCreateTime(String filePath) {
		try {
			String cmd = String.format(CMD_FORMAT_STR, filePath);
		    Process p = Runtime.getRuntime().exec(cmd);  
		    InputStream is = p.getInputStream();  
		    BufferedReader br = new BufferedReader(new InputStreamReader(is));  
		    String result;  
		    String getTime = null;  
		    String fileName = new File(filePath).getName();
		    while ((result = br.readLine()) != null) {  
		        String[] str = result.split(" ");  
		        for (int i = str.length - 1; i >= 0; i--) {  
		            if (str[i].equals(fileName)) {  
		                getTime = str[0] + " " + str[2];  
		            }  
		         }  
		      }  
		    
		      return transToTimestamp(getTime);
		    } catch (java.io.IOException exc) {  
		        exc.printStackTrace();
		        return null;
		    }  
	}
	
	public static Date transToTimestamp(String timeStr) {
		SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_STRING);
		try {
		    Date date = format.parse(timeStr);
		    return date;
		} catch (Exception e) {
		    return null;
		}
	}
	
	public static Date getLastModifyDate(String filePath) {
		File file = new File(filePath);
		long timestamp = file.lastModified();
		Date date = new Date(timestamp);
		return date;
	}
}
