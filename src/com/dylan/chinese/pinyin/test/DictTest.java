package com.dylan.chinese.pinyin.test;

import net.sf.json.JSONObject;

import org.junit.Ignore;
import org.junit.Test;

import com.dylan.chinese.pinyin.dict.ResourceConfig;
import com.dylan.chinese.pinyin.dict.ResourceTool;
import com.dylan.chinese.pinyin.dict.ResourceType;
import com.dylan.chinese.pinyin.util.Utils;

public class DictTest {
	ResourceConfig dHelp = ResourceTool.getInstance();
	 ResourceTool resourceTool=ResourceTool.getInstance();
	Utils utils= Utils.getInstance();
	@Test public void loadResource(){
		//System.out.println(dHelp.loadAllDataOfSoundMark());
		System.out.println(dHelp.loadResource(ResourceType.LOADPINYINMAP));
	}
	@Test
	@Deprecated
	@Ignore
	public void findPinYinWithHanZi(){
		System.out.println(utils.findPinYinWithHanZi("祖國"));
	}
	
	@Test
	public void getPinYinMap(){
		JSONObject jsonObject=resourceTool.getPinYinMap();
		System.out.println(jsonObject.containsKey("祖")+" "+jsonObject.getString("祖"));
	}
	@Test public void findSimplyPinYinWithHanZi(){
		System.out.println(resourceTool.findSimplyPinYinWithHanZi("祖國",null));
		System.out.println(resourceTool.findSimplyPinYinWithHanZi("祖國,hello World",ResourceType.OutPutStyle.YUNMU));
	}
	@Test public void handleType(){
		System.out.println("helloworld,guo".replaceAll("[0-9]", ""));
		System.out.println(resourceTool.handleType("guó ",ResourceType.OutPutStyle.NUMBER));
	}
}
