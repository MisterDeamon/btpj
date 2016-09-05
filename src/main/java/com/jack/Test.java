package com.jack;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		System.out.println( System.getProperty("os.name"));
		System.out.println( System.getProperty("user.name"));
		System.out.println(System.currentTimeMillis());
	    
	    System.out.println(duration(System.currentTimeMillis()));
	}
	
	public static String duration(long timeMs){
		 long tSec = Math.round(timeMs / 1000);
	        long tMin = Math.round(tSec / 60);
	        long tHrs = Math.round(tMin / 60);

	        String fmtTime = null;

	        if (tSec > 0) {
	            timeMs = timeMs - (tSec * 1000);
	        }

	        if (tMin > 0) {
	            tSec = tSec - (tMin * 60);
	        }

	        if (tHrs > 0) {
	            tMin = tMin - (tHrs * 60);
	        }

	        if (tHrs > 0) {
	            fmtTime = (tHrs + "h " + tMin + "m " + tSec + "s " + timeMs + "ms");
	        } else if (tMin > 0) {
	            fmtTime = (tMin + "m " + tSec + "s " + timeMs + "ms");
	        } else if (tSec > 0) {
	            fmtTime = (tSec + "s " + timeMs + "ms");
	        } else {
	            fmtTime = (timeMs + "ms");
	        }
	        return fmtTime;

	}
}
