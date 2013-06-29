package com.dylan.chinese.pinyin.dict;

import java.util.Set;

import com.dylan.chinese.pinyin.util.ChinesePinYinException;
import net.sf.json.JSONObject;

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

	public String findSimplyPinYinWithHanZi(String inputSource,
			ResourceType.OutPutStyle styles) {

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
