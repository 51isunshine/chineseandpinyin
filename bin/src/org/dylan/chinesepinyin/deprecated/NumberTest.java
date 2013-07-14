package org.dylan.chinesepinyin.deprecated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NumberTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Number> list = new ArrayList<Number>( Arrays.asList(1,32.4,54,23,45,2,55));
		Number max=Collections.max(list,new Comparator<Number>() {

			@Override
			public int compare(Number one,
					Number two) {
				// TODO Auto-generated method stub
				return (one.intValue()-two.intValue());
			}

			
		});
		System.out.println(max.toString());
	}
	<T extends Number> void test(T	num){
	
	@SuppressWarnings("unchecked")
	List<T> list = new ArrayList<T>((Collection<? extends T>) Arrays.asList(1,32.4,54,23,45,2));
		Collections.sort(list, new Comparator<T>() {

			@Override
			public int compare(T lhs, T rhs) {
				// TODO Auto-generated method stub
				return 0;
			}
		}) ;
	Collections.max(list,new Comparator<T>() {

		@Override
		public int compare(T lhs, T rhs) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	});	
	
	};
	
		
}
