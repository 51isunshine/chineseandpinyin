package com.dylan.chinese.pinyin.util;

import java.util.Set;

import com.dylan.chinese.pinyin.dict.ResourceTool;
import com.dylan.chinese.pinyin.dict.ResourceType;

public class Utils {
	public void findPinYinWithKey(String inputSource){
		int source_length = inputSource.length();
		char temp='0';
		ResourceTool resourceTool=ResourceTool.getInstance();
		Set<?> findSet = resourceTool.returnPinYinKeys(ResourceType.LOADPINYINMAP);
		for(int i=0;i<source_length;i++){
			temp=inputSource.charAt(i);
			boolean has=findSet.contains(temp);
			if(!has){
				throw new ChinesePinYinException("Don't Support such word:"+temp);
			}
			
			
		}
	}
}
