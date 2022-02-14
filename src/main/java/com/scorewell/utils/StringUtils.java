package com.scorewell.utils;

import java.security.MessageDigest;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scorewell.dto.QuestionSet;

public class StringUtils {
	
	private static Logger logger = LoggerFactory.getLogger(StringUtils.class);
	
	public static String getShareUrlToken()
	{
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	public static String combinePath(String parentPath, String filename){
		String name = "";
		if(filename!=null && !filename.trim().equals("")){
			name = filename.trim();
		}
		String path = "";
		if(!StringUtils.isEmpty(parentPath)){
			path = parentPath;
			if(!path.endsWith("/")){
				path += "/";
			}
		}
		path += name;
		if(path.startsWith("/")){
			path = path.substring(0);
		}
		if(path.endsWith("/")&&path.length()>1){
			path = path.substring(0, path.length()-2);
		}
		return path;
	}
	
	public static boolean isEmpty(String s){
		return s==null||s.trim().equals("");
	}
	
	private final static char[] NUM_TO_CHAR_ARR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String getMD5Hash(String s) {
        try {
        	if(s==null){
        		return null;
        	}
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte[] bs = digest.digest();
            String hexString = getHexString(bs);
            return hexString;
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static String getHexString(byte[] barr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < barr.length; i++) {
            byte b = barr[i];
            sb.append(NUM_TO_CHAR_ARR[(b >> 4) & 0x0f]);
            sb.append(NUM_TO_CHAR_ARR[b & 0x0f]);
        }
        return sb.toString();
    }
    
    public static String formatDate(long timeInMillis, String format){
    	try{
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    		String dateStr = sdf.format(new Date(timeInMillis));
    		return dateStr;
    	}catch(Exception e){
    		logger.error(e.getMessage(), e);
    	}
    	return "";
    }
    
    public static Date strToDate(String strDate, String format){
    	try{
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    		return sdf.parse(strDate);
    	}catch(Exception e){
    		logger.error(e.getMessage(), e);
    	}
    	return new Date();
    }
    
    public static long addYear(long effectiveDate, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(effectiveDate));
		cal.add(Calendar.YEAR, year);
		return cal.getTimeInMillis();
	}
    
    public static long getTimeDiffDays(long dateOne, long dateTwo) {
    	if(dateOne > dateTwo){
    		return 0;
    	}
    	long timeDiff = dateOne - dateTwo;
    	long diffInDays = timeDiff / (24 * 60 * 60 * 1000);
    	return diffInDays;
    }
    
    public static boolean isNumeric(String str)
    {
      return str.matches("[+-]?\\d*(\\.\\d+)?");
    }
    
    public static <T> String currencyFormat(T t)
    {
    	NumberFormat currency=NumberFormat.getCurrencyInstance(Locale.US); 
    	if(isNumeric(String.valueOf(t)) && t!=null){
        	if(Double.parseDouble(String.valueOf(t))<0){
        		String regex = "\\(|\\)";
        		return "-"+(currency.format(t)).replaceAll(regex, "");
        	}
    		return currency.format(t);
    	}
		return currency.format(0);
    }
    
    public static int generateSetNumber(List<QuestionSet> questionSets) {
    	if(questionSets == null)
    		return 1;
    	List<Integer> setCount = new ArrayList<Integer>();
		for (QuestionSet questionSet : questionSets) {
			setCount.add(Integer.parseInt(questionSet.getSetName()
					.substring(questionSet.getSetName().lastIndexOf('_') + 1, questionSet.getSetName().length())));
		}
    	
		return Collections.max(setCount)+1;
//    	return Integer.parseInt(setName.substring(setName.lastIndexOf('_')+1, setName.length()))+1;
    	
    }
    
//    public static void main(String[] ags) {
//    	
//    	StringUtils stringUtils = new StringUtils();
//    	System.out.println(stringUtils.generateSetNumber("test_12"));
//    }
}