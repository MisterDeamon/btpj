package com.jack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		/*System.out.println( System.getProperty("os.name"));
		System.out.println( System.getProperty("user.name"));
		System.out.println(System.currentTimeMillis());
	    
	    System.out.println(duration(System.currentTimeMillis()));*/

		try {
			FileInputStream fin = new FileInputStream("C:\\Users\\wajiangk\\Desktop\\test.txt");
			StringBuffer buffer = new StringBuffer();
			FileOutputStream outputStream = new FileOutputStream("C:\\Users\\wajiangk\\Desktop\\test_1.txt");
			byte[] buf = new byte[1024*4];
			try {
				while((fin.read(buf))!=-1){
//					buffer.append(new String(buf,"utf-8"));
					String test = new String(buf,"utf-8").replaceAll("(\\u7b2c)([\\u4e00-\\u9fa5]{1,})(\\u7ae0)","  $1$2$3 \n");
//					System.out.println(test);
					outputStream.write(test.getBytes());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			List<String> charachers = new ArrayList<String>();
//			第\d*章\s*.*
//			System.out.println( buffer.toString());
			String[] chars = new String(buffer.toString().getBytes(),"utf-8").split("\\u7b2c[\\u4e00-\\u9fa5]{1,}\\u7ae0");
			System.out.println(chars.length);
			System.out.println("第一章....asdasd123123".replaceAll("(\\u7b2c)([\\u4e00-\\u9fa5]{1,})(\\u7ae0)","$1 $2 $3"));
//			System.out.println(chars[2]);
			for(int i=0;i<chars.length;i++){
				outputStream.write(("第"+(i+1)+"章"+chars[i]).getBytes());
//				System.out.println("i="+i+":"+chars[i]);
			}
//			outputStream.flush();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
