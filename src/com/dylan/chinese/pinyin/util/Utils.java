package com.dylan.chinese.pinyin.util;

import net.sf.json.JSONObject;

import com.dylan.chinese.pinyin.dict.ResourceTool;

public class Utils {
	private ResourceTool resourceTool=ResourceTool.getInstance();
	
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
}
