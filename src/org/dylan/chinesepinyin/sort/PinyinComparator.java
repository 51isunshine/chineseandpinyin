package org.dylan.chinesepinyin.sort;



import java.util.Arrays;
import java.util.Comparator;

import org.dylan.chinesepinyin.dict.ResourceTool;
import org.dylan.chinesepinyin.dict.ResourceType;
import org.junit.Test;

public class PinyinComparator implements Comparator<Object> {
	ResourceTool resourceTool = ResourceTool.getInstance();
	String one[] = { "adb" };
	String two[] = { "dkf" };

	@Override
	public int compare(Object o1, Object o2) {

		char c1 = ((String) o1).charAt(0);
		char c2 = ((String) o2).charAt(0);
		// System.out.println((String) o1+ " : "+(String) o2+ c1+c2);
//		return concatPinyinStringArray(one).compareTo(
//				concatPinyinStringArray(two));
		String s1= ((String) o1);
		String s2= ((String) o2);
		return s1.compareTo(s2);
	}

	private String concatPinyinStringArray(String[] pinyinArray) {
		StringBuffer pinyinSbf = new StringBuffer();
		if ((pinyinArray != null) && (pinyinArray.length > 0)) {
			for (int i = 0; i < pinyinArray.length; i++) {
				pinyinSbf.append(pinyinArray[i]);
			}
		}
		return pinyinSbf.toString();
	}
	
	
	
	@Test
	public void sort() {
		Comparator<Object> comparator = new PinyinComparator();
		String str = "今天天气真心不错！ Happy!";
		String[] arr = { "张三", "李四", "王二", "麻子", "Android", "10086",
				"@%~*&^#$", "hello world", "怡情" };

		System.out.println(resourceTool.toStringWith(arr));
		//System.out.println(concatPinyinStringArray(arr));
		Arrays.sort(arr,comparator);
		System.out.println(resourceTool.toStringWith(arr));
	}
}
