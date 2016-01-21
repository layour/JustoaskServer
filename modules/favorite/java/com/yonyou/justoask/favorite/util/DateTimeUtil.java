package com.yonyou.justoask.favorite.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	public static String getDateTime(){
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置显示格式
		return df.format(date);
	}
	
	public static String getDate(){
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置显示格式
		return df.format(date);
	}
	
	public static String getTime(){
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");//设置显示格式
		return df.format(date);
	}
	
	public static void main(String[] args) {
		System.out.println(getDateTime());
	}
}
