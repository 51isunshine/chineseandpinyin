package com.dylan.chinese.pinyin.test;

import java.lang.ref.WeakReference;

public class ReferTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeakReference<Person> peReference = new WeakReference<Person>(new Person(12, "Test"));
		//System.gc();
		Person person=(Person)peReference.get();
		System.out.println(person.getId());
	}

}
class Person{
	private int id;
	private String name;
	public Person(int id,String name){
		this.id=id;
		this.name=name;
	}
	
	public int getId(){
		return this.id;
	}
	
}