package org.dylan.chinesepinyin.test;

import org.dylan.chinesepinyin.dict.ResourceConfig;
import org.dylan.chinesepinyin.dict.ResourceTool;
import org.dylan.chinesepinyin.dict.ResourceType;
import org.dylan.chinesepinyin.util.Utils;
import org.junit.Ignore;
import org.junit.Test;


/**
 * Just for Test
 * @author dylan.zhang (Dylan.zhangzhi@gmail.com)
 *
 */
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
		resourceTool.getPinYinMap();	
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
