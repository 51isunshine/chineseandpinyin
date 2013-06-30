package org.dylan.chinesepinyin.test;

import org.dylan.chinesepinyin.util.UnicodeUtils;
import org.junit.Test;

public class TestHanZiLength {
	UnicodeUtils util = UnicodeUtils.getInstance();

	/**
	 * 汉字在String的length中也是占一个长度单位
	 */
	@Test
	public void hanziLenght() {
		System.out.println("张Dylan".length());
		System.out.println("张DylanTest".length());
		System.out.println("张Dylan Test #$".length());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getHanZiUnicode() {
		String string = util.hanZiToUnicode("中国福建厦门");
		System.out.println(string);
		String withoutU = util.hanZiToUnicodeWithoutU("中国福建厦门");
		System.out.println(util.hexToHanZi(withoutU));
		System.out.println(util.decodeWithSeparator(string,
				UnicodeUtils.SeparatorMode.UNICODEMODE.getMode()));

		String str = "make in \\u4e2d\\u56fd";
		System.out.println(str);
		String demoString = "\u4e2d\u56fd\u798f\u5efa\u53a6\u95e8";
		System.out.println(demoString);

		String count[] = util.hanZiToHex("你好，很高兴见到你！！！ Hello",
				UnicodeUtils.SeparatorMode.UNICODEMODE);
		System.out.println(count[0]);
		System.out.println(util.decodeWithSeparator(count[0], count[1]));

		String[] encodeString = util.encodeWithSeparator("你好，很高兴见到你！！！ Hello",
				UnicodeUtils.SeparatorMode.STANDLINE);
		System.out.println("encodeString[0]:" + encodeString[0]);
		System.out.println("encodeWithoutSeparator:"
				+ util.encodeWithOutSeparator("你好，很高兴见到你！！！")[2]);
		System.out.println(util.decodeWithSeparator(encodeString[0],
				encodeString[1]));
		System.out
				.println(util.decodeFilterChineseWithOutSepa(util
						.encodeWithOutSeparator("你好，很高兴见到你！！！Hello, nice to meet you !")));
		System.out.println(util.decode(util.decodeWithOutSeparator(util
				.encodeWithOutSeparator("你好，很高兴见到你！！！"))));
	}

	@Test
	public void hasChinese() {
		System.out.println(util.hasChinese("helloworld"));
		System.out.println(util.hasChinese("hello  world"));
		System.out.println(util.hasChinese("hello 我 world ？￥￥￥ 我的中学"));
		System.out.println(util.hasChinese("###  %?"));

		System.out.println(util.onlyChinese("大学好hello 我 world v￥￥￥ 我的中学?你好吗"));
	}

	@Test
	public void testFileChinese() {
		String fileter = "65";
		int num = Integer.parseInt(fileter, 16);
		System.out.println((char) num);
	}
}
