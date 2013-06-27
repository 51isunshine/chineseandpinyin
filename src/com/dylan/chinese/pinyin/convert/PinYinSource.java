package com.dylan.chinese.pinyin.convert;

import java.io.ObjectOutputStream.PutField;
import java.util.HashMap;
import java.util.Map;

public class PinYinSource {
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
	
}
