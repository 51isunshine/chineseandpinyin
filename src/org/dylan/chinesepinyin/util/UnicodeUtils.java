/*
 *  This file is part of chinesesort and distributed under 
 *  Apache Software Foundation (ASF).
 *  
 *  The chinesesort is a free software; you can redistribute it and/or modify 
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

import org.dylan.chinesepinyin.dict.ResourceTool;

import net.sf.json.JSONObject;

/**
 * String util class
 * 
 * @author dylan.zhang (Dylan.zhangzhi@gmail.com)
 * 
 */
@Deprecated class UnicodeUtils {
	private ResourceTool resourceTool = ResourceTool.getInstance();

	private static class StringUtilHolder {
		private static final UnicodeUtils INSTANCE = new UnicodeUtils();
	}

	private UnicodeUtils() {

	}

	public static final UnicodeUtils getInstance() {
		return StringUtilHolder.INSTANCE;
	}

	/**
	 * Have a default separator : \\u <br />
	 * if the param not only include chinese hanzi,It will filter by defalut. <br />
	 * For example
	 * 
	 * <pre>
	 * String str = &quot;中国 福 建 厦门 ,Hello!!&quot;;
	 * String hanZi = filterChinese(str); // Just diplay &quot;中国福建厦门&quot;
	 * </pre>
	 * 
	 * @param String
	 * @return String
	 */
	public String hanZiToUnicode(String hanZi) {
		hanZi = onlyChinese(hanZi);
		separatorMode = SeparatorMode.UNICODEMODE;
		StringBuilder sb = new StringBuilder();
		for (char c : hanZi.toCharArray()) {
			sb.append(separatorMode.getMode()).append(Integer.toHexString(c));
		}
		return sb.toString();
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

	/**
	 * The same as {@code hanZiToUnicode},But does not have separator: \\u <br />
	 * For example: "我姓张" : input : 6211 59d 35f20
	 * 
	 * @see {@link http://www.chi2ko.com/tool/CJK.htm }
	 * @param String
	 *            hanzi
	 * @return String
	 */
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

	public String hanZiToUnicodeWithoutU(String hanZi) {
		hanZi = onlyChinese(hanZi);
		StringBuilder sb = new StringBuilder();
		for (char c : hanZi.toCharArray()) {
			sb.append(Integer.toHexString(c));
		}
		return sb.toString();
	}

	/**
	 * hanZi to hex with separator <br />
	 * 
	 * @param hanZi
	 * @param separator
	 * @return String
	 */
	public String[] hanZiToHex(String hanZi, SeparatorMode separator) {
		hanZi = onlyChinese(hanZi);
		separatorMode = separator;
		separator = null;
		StringBuilder sb = new StringBuilder();
		for (char c : hanZi.toCharArray()) {
			sb.append(separatorMode.getMode()).append(Integer.toHexString(c));
		}
		String[] str = { sb.toString(), separatorMode.getMode() };
		return str;
	}

	/**
	 * Default separator is "\\u"
	 * 
	 * @param source
	 * @param separator
	 * @return String
	 * @return String[]
	 */
	public String[] encodeWithSeparator(String source, SeparatorMode separator) {
		StringBuilder sb = new StringBuilder();
		if (separator == null)
			separatorMode = SeparatorMode.UNICODEMODE;
		else
			separatorMode = separator;
		separator = null;
		source = source.trim();
		for (int i = 0; i < source.length(); i++) {
			sb.append(separatorMode.getMode()).append(
					Integer.toHexString((int) source.charAt(i) & 0xffff));
		}
		String[] string = { sb.toString(), separatorMode.getMode() };
		return string;
	}

	/**
	 * Have a argrument without Separator <br />
	 * 
	 * @param source
	 * @return String[]
	 */
	public String[] encodeWithOutSeparator(String source) {
		source = source.trim();
		String[] ss = new String[source.length()];
		for (int i = 0; i < source.length(); i++) {
			ss[i] = Integer.toHexString((int) source.charAt(i) & 0xffff);
		}
		source = null;
		return ss;
	}

	/**
	 * encode Mix Char : For example <br />
	 * 
	 * <pre >
	 * String char="Test 测试 ！";
	 * </pre>
	 * 
	 * @param String
	 *            : source
	 */
	public char[] decodeFilterChineseWithOutSepa(String[] formEncode) {
		int temp = formEncode.length;
		char[] ch = new char[temp];
		int num = 0;
		for (int i = 0; i < temp; i++) {
			num = Integer.parseInt(formEncode[i], 16);
			if (num > 255) {
				// ch[i] = ((char) Integer.parseInt(formEncode[i], 16));
				// ToDo
			} else {
				ch[i] = (char) num;
			}
		}
		return ch;
	}

	/**
	 * After invoke
	 * {@code encodeWithSeparator(String source, SeparatorMode separator)}
	 * ,please call this function !! <br />
	 * 
	 * @param formEncode
	 * @param separator
	 * @return char[]
	 */
	public char[] decodeWithSeparator(String formEncode, String separator) {
		String split = "\\" + separator;
		// System.out.println(split);
		String[] flag = formEncode.trim().split(split);
		int temp = flag.length;
		char[] ch = new char[flag.length];
		for (int i = 1; i < temp; i++) {
			ch[i] = ((char) Integer.parseInt(flag[i], 16));
		}
		separator = null;
		return ch;
	}

	/**
	 * decode With Out Separator <br />
	 * {@code encodeWithOutSeparator}
	 * 
	 * @param formEncode
	 * @return char[]
	 */
	public char[] decodeWithOutSeparator(String[] formEncode) {
		int temp = formEncode.length;
		char[] ch = new char[temp];
		for (int i = 0; i < temp; i++) {
			ch[i] = ((char) Integer.parseInt(formEncode[i], 16));
		}
		return ch;
	}

	public String decode(char[] ch) {
		return new String(ch);
	}

	/**
	 * encode character: include hanzi plese use {@code encodeToUnicodeWithOutU} <br />
	 * Don't USE
	 * 
	 * @param source
	 * @return
	 */
	@Deprecated
	public String encodeToUnicode(String source) {
		StringBuilder sb = new StringBuilder();
		source = (source == null || source == "") ? "" : source.trim();
		char c;
		int count;
		String str;
		for (int i = 0, length = source.length(); i < length; i++) {
			sb.append("\\u");
			c = source.charAt(i);
			count = c >>> 8;

			str = Integer.toHexString(count);
			if (str.length() == 1)
				sb.append("0").append(str);
			sb.append(str);

			count = (c & 0xff);
			if (str.length() == 1)
				sb.append("0").append(str);
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * argument must be a hex of Chinese charater. Never update,please don't use
	 * it. For example: "6211 59d 35f20" : input : 我姓张
	 * 
	 * @param hexHanzi
	 * @return
	 */
	@Deprecated
	public String hexToHanZi(String hexHanzi) {
		int count = hexHanzi.trim().length() / 4;
		// String[] sb = new String[count];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < count; i++) {
			// sb[i]=hexHanzi.substring(i*4, i*4+4);
			sb.append((char) Integer.parseInt(
					hexHanzi.substring(i * 4, i * 4 + 4), 16));
		}
		return sb.toString();

	}

	public static enum SeparatorMode {
		UNICODEMODE("\\u"), STANDLINE("|"), EQUALSIGN("="), EMPTY(" "), LINEFEED(
				"\n");
		private String mode;

		private SeparatorMode(String mode) {
			// TODO Auto-generated constructor stub
			this.mode = mode;
		}

		public String getMode() {
			return mode;
		}
	}

	private SeparatorMode separatorMode = null;

	public <T> String getPackagePath(Class<T> classPath) {
		String path = classPath.getPackage().getName();
		path = path.replace(".", File.separator);
		return path;
	}

	/* ToDo : Can't use */
	@Deprecated
	public boolean hasHanZi(String inputSource) {
		int source_length = inputSource.length();
		char temp = '0';
		boolean has = false;
		JSONObject pinYin = resourceTool.getPinYinSource();
		for (int i = 0; i < source_length; i++) {
			temp = inputSource.charAt(i);
			has = pinYin.containsKey(temp);
			if (!has) {
				throw new ChinesePinYinException("Don't Support such word:"
						+ temp);
			}
		}
		has = true;
		pinYin = null;
		return has;
	}

	/* ToDo : Can't use */
	@Deprecated
	public String findPinYinWithHanZi(String inputSource) {
		int source_length = inputSource.length();
		hasHanZi(inputSource);
		StringBuilder sb = new StringBuilder();
		String tempString = null;
		JSONObject pinYin = resourceTool.getPinYinSource();
		for (int i = 0; i < source_length; i++) {
			tempString = (String) pinYin.get(inputSource.charAt(i));
			sb.append((tempString.split(",")[0])).append(" ");
		}
		tempString = null;
		return sb.toString();
	}
}
