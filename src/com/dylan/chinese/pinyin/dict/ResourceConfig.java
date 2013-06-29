package com.dylan.chinese.pinyin.dict;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.sf.json.JSONObject;

import com.dylan.chinese.pinyin.util.ChinesePinYinException;
import com.dylan.chinese.pinyin.util.UnicodeUtils;

public abstract class ResourceConfig {
	private  UnicodeUtils utils = UnicodeUtils.getInstance();
	private final int BYTE_SIZE=1024;
	protected final  String SOUND_MARK_PATH = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator
			+ utils.getPackagePath(ResourceConfig.class) + File.separator
			+ "sound_mark.js";
	protected final  String PINYIN_DICT_MAP = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator
			+ utils.getPackagePath(ResourceConfig.class) + File.separator
			+ "pinyin_dict_map.js";
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
	 * 
	 * @return
	 */
	public String loadResource(ResourceType  resourceType) {
		String dataPath=null;
		switch (resourceType) {
		case LOADSOUNMARK:
			dataPath = SOUND_MARK_PATH;
			break;
		case LOADPINYINMAP:
			dataPath = PINYIN_DICT_MAP;
			break;
		default:
			throw new ChinesePinYinException("Nonsupport 'ResourceType !'");
		}	
		File dict_mapFile = new File(dataPath);
		if (!(dict_mapFile.exists() & dict_mapFile.isFile())) {
			return ResourceType.LOADRESOURCEFAIL.getStringType();
		}
		StringBuilder builder = new StringBuilder();
		FileInputStream in;
		try {
			in = new FileInputStream(dict_mapFile);
			BufferedInputStream buf = new BufferedInputStream(in);
			byte[] by = new byte[BYTE_SIZE];
			int len = 0;
			while ((len = buf.read(by)) > 0) {
				builder.append(new String(by, 0, len));
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
