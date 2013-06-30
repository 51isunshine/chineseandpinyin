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
package org.dylan.chinesepinyin.dict;

import java.util.Set;

import org.dylan.chinesepinyin.util.ChinesePinYinException;

import net.sf.json.JSONObject;

/**
 * Extends {@code ResourceConfig}
 * @author dylan.zhang (Dylan.zhangzhi@gmail.com)
 *
 */
public class ResourceTool extends ResourceConfig {
	private final JSONObject pinYinJsonObject = getPinYinMap();
	private final JSONObject soundMarkJsonObject = getSoundMark();
	private final String[] SHENGMU = super.SHENGMU.split(",");
	//private final String[] YUNMU = super.YUNMU.split(",");

	private static class ResourceToolHolder {
		private static final ResourceTool INSTANCE = new ResourceTool();
	}

	private ResourceTool() {
	}

	public static final ResourceTool getInstance() {
		return ResourceToolHolder.INSTANCE;
	}

	/**
	 * Encapsulation pinyin_dict_map.js
	 * @return JSONObject
	 */
	@Override
	public JSONObject getPinYinMap() {
		String pinYin = super.loadResource(ResourceType.LOADPINYINMAP);
		if (pinYin.equals(ResourceType.LOADRESOURCEFAIL.name())) {
			throw new ChinesePinYinException(
					"LOAD RESOURCE (pinyin_dict) FAIL !");
		}
		JSONObject jsonObject = JSONObject.fromObject(pinYin);
		pinYin = null;
		return jsonObject;
	}

	/**
	 *
	 * Encapsulation sound_mark.js
	 * @return JSONObject
	 */
	@Override
	public JSONObject getSoundMark() {
		String soundMark = super.loadResource(ResourceType.LOADSOUNMARK);
		if (soundMark.equals(ResourceType.LOADRESOURCEFAIL.name())) {
			throw new ChinesePinYinException(
					"LOAD RESOURCE (sound_mark) FAIL !");
		}
		JSONObject jsonObject = JSONObject.fromObject(soundMark);
		soundMark = null;
		return jsonObject;
	}

	/**
	 * Get the Key-Set
	 * @param resourceType
	 * @return the Key-Set
	 */
	public Set<?> returnPinYinKeys(ResourceType resourceType) {
		JSONObject jsonObject = null;
		switch (resourceType) {
		case LOADPINYINMAP:
			jsonObject = getPinYinMap();
			break;
		case LOADSOUNMARK:
			jsonObject = getSoundMark();
			break;
		default:
			throw new ChinesePinYinException("Nonsupport 'ResourceType !'");
		}
		return jsonObject.keySet();
	}

	/**
	 * Most of time,please use this method; 
	 * More details information please see {@link DictTest}
	 * @param inputSource
	 * @param styles
	 * @return 
	 */
	public String findSimplyPinYinWithHanZi(String inputSource,
			ResourceType.OutPutStyle styles) {
//		try {
//			inputSource= new String(inputSource.getBytes("UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		int length = inputSource.length();

		StringBuilder sb = new StringBuilder();
		String tempString = null;
		String toneString = "";

		for (int i = 0; i < length; i++) {
			String c = inputSource.substring(i, i + 1);
			tempString = (String) pinYinJsonObject.get(c);
			if (pinYinJsonObject.containsKey(c)) {
				toneString = tempString.split(",")[0];
				sb.append(handleType(toneString, styles)).append(" ");
			} else {
				sb.append(c);
			}
		}
		String pinYin = sb.toString();
		tempString = null;
		return pinYin;
	}

	/**
	 * A help method for {@code findSimplyPinYinWithHanZi}
	 * @param code
	 * @param styles
	 * @return
	 */
	public String handleType(String code, ResourceType.OutPutStyle styles) {
		StringBuilder sb = new StringBuilder();
		String tone = null;
		String nothing = "";
		if (styles == null) {
			styles = ResourceType.OutPutStyle.NOTHING;
		}
		for (int i = 0, length = code.length(); i < length; i++) {
			String c = code.substring(i, i + 1);
			switch (styles) {
			case SHENGMU:
				int num = 0;
				for (int j = 0, length1 = SHENGMU.length; j < length1; j++) {
					num = code.indexOf(SHENGMU[j]);
					if (num == 0) {
						sb.append(SHENGMU[j]);
					}
				}
				return sb.toString();
			case YUNMU:
				int num1 = 0;
				for (int j = 0, length1 = SHENGMU.length; j < length1; j++) {
					num1 = code.indexOf(SHENGMU[j]);
					if (!(num1 == 0)) {
						sb.append(code.substring(SHENGMU[j].length()));
						break;
					}
				}
				return sb.toString();
			}
			if (soundMarkJsonObject.containsKey(c)) {
				// System.out.println("code[i]="+i+ " "+ c);
				tone = (String) soundMarkJsonObject.get(c);
				// System.out.println("tone="+tone);
				nothing = tone.replaceAll("[0-9]", "");
				switch (styles) {
				/* Without Tone,Without Number */
				case WITHTONE:
					sb.append(c);
					break;
				/* With Number */
				case NUMBER:
					sb.append(tone);
					break;
				/* With Tone */
				case NOTHING:
					sb.append(nothing);
					break;
				default:
					sb.append(tone.replaceAll("[0-9]", ""));
					break;
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
