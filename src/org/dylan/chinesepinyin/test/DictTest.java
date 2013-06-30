package org.dylan.chinesepinyin.test;

import java.io.UnsupportedEncodingException;

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
	@Test public void findSimplyPinYinWithHanZi() throws UnsupportedEncodingException{
		System.out.println(resourceTool.findSimplyPinYinWithHanZi("祖國",ResourceType.OutPutStyle.YUNMU));
		System.out.println(resourceTool.findSimplyPinYinWithHanZi("天 朝,hello World",null));
		System.out.println(resourceTool.findSimplyPinYinWithHanZi("今天天气真心不错！ Happy!",ResourceType.OutPutStyle.NOTHING));
		System.out.println(resourceTool.findSimplyPinYinWithHanZi("今天天气真心不错！ Happy!",ResourceType.OutPutStyle.SHENGMU));
		System.out.println(resourceTool.findSimplyPinYinWithHanZi("情绪是条河流！",ResourceType.OutPutStyle.WITHTONE));
		System.out.println(resourceTool.findSimplyPinYinWithHanZi("12345，上山打老虎！",ResourceType.OutPutStyle.NUMBER));
		
		/**
		 * Don't do this: new String("天 朝,hello World".getBytes("utf-8"));
		 * it will output "chan ?lian ?hello World" not "tian  zhao ,hello World";
		 * 
		 * Please see @{code : loadResource}
		 */
	}
	@Test public void handleType(){
		System.out.println("helloworld,guo".replaceAll("[0-9]", ""));
		System.out.println(resourceTool.handleType("guó ",ResourceType.OutPutStyle.NUMBER));
	}
}
