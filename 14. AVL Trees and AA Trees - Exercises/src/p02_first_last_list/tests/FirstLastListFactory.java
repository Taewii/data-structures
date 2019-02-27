package p02_first_last_list.tests;

import p02_first_last_list.FirstLastList;
import p02_first_last_list.IFirstLastList;

public class FirstLastListFactory {
    public static <T extends Comparable<T>> IFirstLastList<T> create() {
    	return new FirstLastList<T>();
    }
}
