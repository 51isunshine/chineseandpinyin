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

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.dylan.chinesepinyin.util.ChinesePinYinException;

import net.sf.json.JSONObject;


/**
 * This a abstract class which manager the resource file.
 * More information please see {@link DictTest}
 * @author dylan.zhang (Dylan.zhangzhi@gmail.com)
 */
public abstract class ResourceConfig {
	private final int BYTE_SIZE=1024;
	protected final  String SOUND_MARK_PATH = "sound_mark.js";
	protected final  String PINYIN_DICT_MAP = "pinyin_dict_map.js";
	/**
	 * SHENGMU's length= 23;
	 */
	public final String SHENGMU="b,p,m,f,d,t,n,l,g,k,h,j,q,x,zh,ch,sh,r,z,c,s,y,w";
	/**
	 * YUNMU's length=24;
	 * Change:
	 * 	'ü' to 'v' and 
	 * 	"ün" to "vn" <br />
	 * 
	 */
	public final String YUNMU="ang,eng,ing,ong,an,en,in,un,vn,er,ai,ei,ui,ao,ou,iu,ie,ve,a,o,e,i,u,v";

	/**
	 * load Resource
	 * @return String
	 */
	public String loadResource(ResourceType  resourceType) {
		InputStream dataPath=null;
		switch (resourceType) {
		case LOADSOUNMARK:
			dataPath = getClass().getResourceAsStream(SOUND_MARK_PATH);
			break;
		case LOADPINYINMAP:
			dataPath =  getClass().getResourceAsStream(PINYIN_DICT_MAP);
			break;
		default:
			throw new ChinesePinYinException("Nonsupport 'ResourceType !'");
		}	

		if(dataPath==null){
			return ResourceType.LOADRESOURCEFAIL.getStringType();
		}
		StringBuilder builder = new StringBuilder();
		try {
			BufferedInputStream buf = new BufferedInputStream(dataPath);
			byte[] by = new byte[BYTE_SIZE];
			int len = 0;
			while ((len = buf.read(by)) > 0) {
				builder.append(new String(by, 0, len,"UTF-8"));
			}
		} catch (FileNotFoundException e) {
			return ResourceType.LOADRESOURCEFAIL.getStringType();
		} catch (IOException e) {
			return ResourceType.LOADRESOURCEFAIL.getStringType();
		}
		dataPath = null;
		return builder.toString();

	}

	public abstract JSONObject getPinYinMap();
	public abstract JSONObject getSoundMark();
}
