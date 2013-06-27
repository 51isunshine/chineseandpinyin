package com.dylan.chinese.pinyin.dict;

import java.util.Set;

import com.dylan.chinese.pinyin.util.ChinesePinYinException;
import net.sf.json.JSONObject;

public class ResourceTool extends ResourceConfig {
	private static class ResourceToolHolder{
		private  static final ResourceTool INSTANCE = new ResourceTool();
	}
	private ResourceTool(){
		
	}
	public static final ResourceTool getInstance(){
		return ResourceToolHolder.INSTANCE;
	}
	@Override
	public JSONObject getPinYinMap() {
		String pinYin = super.loadResource(ResourceType.LOADPINYINMAP);
		if(pinYin.equals(ResourceType.LOADRESOURCEFAIL.name())){
			throw new ChinesePinYinException("LOAD RESOURCE (pinyin_dict) FAIL !");
		}
		JSONObject jsonObject = JSONObject.fromObject(pinYin);
		pinYin = null;
		return jsonObject;
	}

	@Override
	public JSONObject getSoundMark() {
		String soundMark = super.loadResource(ResourceType.LOADSOUNMARK);
		if(soundMark.equals(ResourceType.LOADRESOURCEFAIL.name())){
			throw new ChinesePinYinException("LOAD RESOURCE (sound_mark) FAIL !");
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
	
}
