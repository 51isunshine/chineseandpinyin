package com.dylan.chinese.pinyin.dict;

public  enum ResourceType {
	LOADRESOURCEFAIL("LOADRESOURCEFAIL"), LOADSOUNMARK(), LOADPINYINMAP();
	private String typeString = null;

	private ResourceType(String type) {
		this.typeString = type;
	}

	private ResourceType() {

	}

	public String getStringType() {
		return this.typeString;
	}
	public static enum OutPutStyle{
		/* Without Tone*/
		NOTHING("NOTHING"),
		/* With Tone */
		WITHTONE("WITHTONE"),
		/* With Number */
		NUMBER("NUMBER"),
		/* Reset Statement */
		/* SHENGMU */
		SHENGMU("SHENGMU"),
		/* YUNMU */
		YUNMU("YUNMU"),
		RESET("RESET");
		private String typeString = null;

		private OutPutStyle(String type) {
			this.typeString = type;
		}
		private OutPutStyle() {

		}
		public String getStringType() {
			return this.typeString;
		}
	}
}
