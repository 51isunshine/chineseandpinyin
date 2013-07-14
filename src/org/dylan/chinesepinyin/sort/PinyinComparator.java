package org.dylan.chinesepinyin.sort;

import java.util.Comparator;
import org.dylan.chinesepinyin.dict.ResourceTool;
import org.dylan.chinesepinyin.util.Utils;

public class PinyinComparator implements Comparator<String> {
	ResourceTool resourceTool = ResourceTool.getInstance();
	
	@Override
	public int compare(String str1, String str2) {
		return comparator(str1, str2);
	}
	
	private int comparator(String str1,String str2){
		int len1=str1.length(),len2=str2.length(); 
		 for (int i = 0; i < len1 && i < len2; i++) {
	            int codePoint1 = str1.charAt(i);
	            int codePoint2 = str2.charAt(i);
	            if (Character.isSupplementaryCodePoint(codePoint1)
	                    || Character.isSupplementaryCodePoint(codePoint2)) {
	                i++;
	            }
	            if (codePoint1 != codePoint2) {
	                if (Character.isSupplementaryCodePoint(codePoint1)
	                        || Character.isSupplementaryCodePoint(codePoint2)) {
	                    return codePoint1 - codePoint2;
	                }
	                String pinyin1 = resourceTool.toPinYinWithString((char) codePoint1,Utils.PinYinStyles.COMPLETE);
	                String pinyin2 = resourceTool.toPinYinWithString((char) codePoint2,Utils.PinYinStyles.COMPLETE);
	                if (pinyin1 != null && pinyin2 != null) { 
	                    if (!pinyin1.equals(pinyin2)) {
	                        return pinyin1.compareTo(pinyin2);
	                    }
	                } else {
	                    return codePoint1 - codePoint2;
	                }
	            }
	        }
	        return len1 - len2;
	}
}
