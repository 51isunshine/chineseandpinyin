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
 * More information please @see MainTest
 * @author dylan.zhang (Dylan.zhangzhi@gmail.com)
 */
public abstract class ResourceConfig {
	private final int BYTE_SIZE=1024;
	/** An  constant for save "sound_mark.js "*/
	protected final  String SOUND_MARK_PATH = "sound_mark.js";
	/** An  constant for save "pinyin_dict_map.js "*/
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

	public abstract JSONObject getPinYinSource();
	public abstract JSONObject getSoundMarkSource();
	/**
	 * <pre>
	 * <p>charset-info</p>
	 * 每种语言都是由一系列的字符按照某种规则组合而成的，那么如何在计算机程序中表示这些字符呢？
1. 打包成字符集对其进行编码，于是产生的编码字符集，eg: ASCII,Unicode,GBK.
2. 把字符的代码点直接映射到对应的数字上，eg：ASCII 字母A = 65; 
3. 映射使用与ASCII，因为它只有128个字符，但是GBK,Unicode就不行，因为它们的字符远远不止128个；
     于是出于节省空间与提高使用效率就使用代码单元来表示像Unicode这样的字符集的字符；
4. Unicode: 目标是包括世界上所有语言的文字；ASCII and GBK只是它的一个子集。UTF-8是Unicode编码格式，UTF-8每个
     代码单元是8位，UTF-8兼容ASCII；UTF-8适合传输时的编码格式；
5. Java与Unicode： Java 7支持最新的Unicode 6.0规范；采用UTF-16编码格式，更多是作为字符在系统中的内部表示方式；
   Java平台内部统一使用UTF-16的编码方式；
     乱码问题：很头通的问题，尤其是Web开发；基本解决思路：在程序与外界进行输入与输出的边界严格控制字符的编码格式
     、确保这些字符以正确的UTF-16格式进入到程序内部；但是在Java虚拟机中支持：ASCII，IOS 8859-1,UTF-8,UTF-16；
     非UTF-16编码由UTF-16进行解码与编码，它是中间的实现。
	 * </pre>
	 * <br />
	 * <p>pinyin-info</p>
	 * <pre>
	 * 
	 * 字母表：26;(不解释，大伙都了解首字母为：A)
声母表(Total:23)：
	b p m f d t n l g k h j q x zh ch sh r z c s y w	
韵母表(Total:24)：
	单韵母：a o e i u ü  (Total:6)
	复韵母：ai ei ui ao ou iu ie ue er，an en in un ün （前鼻音），ang eng ing ong (后鼻音）
声调：
	阴平：第一声；阳平：第二声；上声：第三声；	去声：第四声；
	另外还有轻声；
	 * </pre>
	 * 
	 */
	
}
