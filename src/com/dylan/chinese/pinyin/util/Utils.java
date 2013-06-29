package com.dylan.chinese.pinyin.util;

import java.io.File;

import net.sf.json.JSONObject;

import com.dylan.chinese.pinyin.dict.ResourceTool;

public class Utils {
	private static class UtilsHolder{
		private  static final Utils INSTANCE = new Utils();
	}
	private Utils(){
		
	}
	public static final Utils getInstance(){
		return UtilsHolder.INSTANCE;
	}
	private ResourceTool resourceTool=ResourceTool.getInstance();
	
	/*ToDo  : Can't use*/
	public boolean hasHanZi(String inputSource){
		int source_length = inputSource.length();
		char temp='0';
		boolean has = false;
		 JSONObject pinYin = resourceTool.getPinYinMap();
		for(int i=0;i<source_length;i++){
			temp=inputSource.charAt(i);
			has=pinYin.containsKey(temp);
			if(!has){
				throw new ChinesePinYinException("Don't Support such word:"+temp);
			}		
		}
		has=true;
		pinYin=null;
		return has;
	}
	/*ToDo  : Can't use*/
	public String findPinYinWithHanZi(String inputSource){
			int source_length = inputSource.length();
			hasHanZi(inputSource);
			StringBuilder sb = new StringBuilder();
			String tempString=null;
			 JSONObject pinYin = resourceTool.getPinYinMap();
			for(int i=0;i<source_length;i++){
				tempString=(String) pinYin.get(inputSource.charAt(i));
				sb.append((tempString.split(",")[0])).append(" ");
			}
			tempString=null;
			return sb.toString();
	}
	
	public <T> String getPackagePath(Class<T> classPath){
		String path = classPath.getPackage().getName();
		path=path.replace(".", File.separator);
		return path;
	}
}
