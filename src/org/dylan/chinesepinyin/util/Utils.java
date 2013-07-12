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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
	/**
	 * filterChinese
	 * 
	 * @param source
	 * @return
	 */
	public String onlyChinese(String source) {
		source = (source == null) ? "" : source.trim();
		String rep = "[\u4E00-\u9FA5]*";
		Pattern pattern = Pattern.compile(rep);
		Matcher matcher = pattern.matcher(source);
		StringBuilder sb = new StringBuilder();

		while (matcher.find()) {
			sb.append(matcher.group());
		}
		return sb.toString().trim();
	}
	/**
	 * if a String include chinese character ,return true,otherwise return false
	 * 
	 * @param source
	 * @return
	 */
	public boolean hasChinese(String source) {
		return source.getBytes().length == source.length() ? false : true;
	}
}
