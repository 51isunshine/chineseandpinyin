/*
 *  This file is part of chinesepinyin and distributed under 
 *  Apache Software Foundation (ASF).
 *  
 *  The chinesepinyin is a free software; you can redistribute it and/or modify 
 *  it under the terms of Apache Software Foundation (ASF).
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.dylan.chinesepinyin.util;

import java.io.File;

import org.dylan.chinesepinyin.dict.ResourceTool;

import net.sf.json.JSONObject;


/**
 * This is a help tool class.
 * @author dylan.zhang (Dylan.zhangzhi@gmail.com)
 *
 */
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
	
	/**
	 * Get the class packpage path
	 * @param classPath
	 * @return 
	 */
	public <T> String getPackagePath(Class<T> classPath){
		String path = classPath.getPackage().getName();
		path=path.replace(".", File.separator);
		return path;
	}
}
