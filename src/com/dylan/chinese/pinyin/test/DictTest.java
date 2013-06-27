package com.dylan.chinese.pinyin.test;

import org.junit.Test;

import com.dylan.chinese.pinyin.dict.ResourceConfig;
import com.dylan.chinese.pinyin.dict.ResourceTool;
import com.dylan.chinese.pinyin.dict.ResourceType;

public class DictTest {
	ResourceConfig dHelp = ResourceTool.getInstance();
	@Test public void loadMapData(){
		//System.out.println(dHelp.loadAllDataOfSoundMark());
		System.out.println(dHelp.loadResource(ResourceType.LOADPINYINMAP));
	}
}
