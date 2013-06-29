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
