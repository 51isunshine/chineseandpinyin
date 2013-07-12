package org.dylan.chinesepinyin.test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dylan.chinesepinyin.dict.ResourceConfig;
import org.dylan.chinesepinyin.dict.ResourceTool;
import org.dylan.chinesepinyin.dict.ResourceType;
import org.dylan.chinesepinyin.util.Utils;
import org.junit.Test;

/**
 * Just for Test
 * 
 * @author dylan.zhang (Dylan.zhangzhi@gmail.com)
 * 
 */
public class DictTest {
	ResourceConfig dHelp = ResourceTool.getInstance();
	ResourceTool resourceTool = ResourceTool.getInstance();
	Utils utils = Utils.getInstance();

	@Test
	public void loadResource() {
		// System.out.println(dHelp.loadAllDataOfSoundMark());
		System.out.println(dHelp.loadResource(ResourceType.LOADPINYINMAP));
	}

	@Test
	public void getPinYinMap() {
		resourceTool.getPinYinSource();
	}

	@Test
	public void findSimplyPinYinWithHanZi() throws UnsupportedEncodingException {
		System.out.println(resourceTool.findSingtonPinYinByHanZi("祖國",
				ResourceType.OutPutStyle.YUNMU));
		System.out.println(resourceTool.findSingtonPinYinByHanZi(
				"天 朝,hello World", null));
		System.out.println(resourceTool.findSingtonPinYinByHanZi(
				"今天天气真心不错！ Happy!", ResourceType.OutPutStyle.NOTHING));
		System.out.println(resourceTool.findSingtonPinYinByHanZi(
				"今天天气真心不错！ Happy!", ResourceType.OutPutStyle.SHENGMU));
		System.out.println(resourceTool.findSingtonPinYinByHanZi("情绪是条河流！",
				ResourceType.OutPutStyle.WITHTONE));
		System.out.println(resourceTool.findSingtonPinYinByHanZi(
				"12345，上山打老虎！", ResourceType.OutPutStyle.NUMBER));

		/**
		 * Don't do this: new String("天 朝,hello World".getBytes("utf-8")); it
		 * will output "chan ?lian ?hello World" not "tian  zhao ,hello World";
		 * 
		 * Please see @{code : loadResource}
		 */
	}

	@Test
	public void handleType() {
		System.out.println("helloworld,guo".replaceAll("[0-9]", ""));
		System.out.println(resourceTool.handleType("guó ",
				ResourceType.OutPutStyle.NUMBER));
	}

	@Test
	public void toPinYinWithMap() {
//		Map<String, String[]> map = resourceTool.toPinYinWithMap(
//				"天气真不错，真不错，happy!", ResourceType.OutPutStyle.WITHTONE);
		Map<String, String[]> map = resourceTool.toPinYinWithMapMixModul(
				"天气真不错，真不错，happy!");
		Collection<String[]> value = map.values();
		Iterator<String[]> it = value.iterator();
		for (; it.hasNext();) {
			for (String temp : it.next()) {
				System.out.print(temp + "   ");
			}

		}
		System.out.println();
		Set<String> setKey = map.keySet();
		Iterator<String> keyIt = setKey.iterator();
		while(keyIt.hasNext()){
			String key= keyIt.next();
			String[] value1 = map.get(key);
			System.out.println(key+" ");
			for(String flag:value1){
				System.out.println("   "+flag);
			}
		}
	}
	
	@Test
	public void toStringWith(){
		String str1="今天天气真心不错！ Happy!";
		System.out.println(resourceTool.toStringWith(str1,"_"));
		
		String[] arr1 = { "张三", "李四", "王二", "麻子", "Android", "10086", "@%~*&^#$", "hello world", "怡情" }; 
		System.out.println(resourceTool.toStringWith(arr1));  
		
		List<String> list = Arrays.asList(arr1); 
		System.out.println(resourceTool.toStringWith(list)); 
	}
	@Test public void toPinYinWithStringArray(){
		String[] arr1 = { "张三", "李四", "王二", "麻子", "Android", "10086", "@%~*&^#$", "hello world", "怡情" }; 
		System.out.println(resourceTool.toPinYinWithStringArray('张')[0]);  
	}
}
