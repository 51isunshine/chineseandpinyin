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
package org.dylan.chinesepinyin.util;

/**
 * 
 * @author dylan.zhang (Dylan.zhangzhi@gmail.com)
 *
 */
public  class CharacterConvertException extends ChinesePinYinException {
	private static final long serialVersionUID = 1987654321L;

	CharacterConvertException() {
	}

	public CharacterConvertException(String message) {
		super(message);
	}

	public CharacterConvertException(String message, Throwable caseMessage) {
		super(message, caseMessage);
	}
	
}
