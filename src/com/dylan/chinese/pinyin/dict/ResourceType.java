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
		NORMAL("NORMAL"),
		/* With Tone */
		STANDARD("STANDARD"),
		/* Without Number */
		NUMBER("STANDARD"),
		/* Reset Statement */
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
