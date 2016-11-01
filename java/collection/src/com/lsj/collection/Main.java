package com.lsj.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void test1(){
		Collection c = new ArrayList();
		c.add(1);				//�Զ�װ��
		c.add(3.14);
		c.add(new Object());
		c.add(new Customer("JACK", 20));
		System.out.println(c.size());
		
		//loop1
		for(Object e : c.toArray()){
			System.out.println(e);
		}
		
		//loop2
		for(Iterator itr = c.iterator(); itr.hasNext(); ){
			Object o = itr.next();
			System.out.println(o);
		}
		c.clear();
		System.out.println(c.size());
	}
	
	public static void test2(){
		List list = new ArrayList();
		
		list.add(100);
		list.add(99);
		
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}
	
	public static void main(String[] args) {
//		test1();
		test2();
		System.out.println("Done!");
	}

}


class Customer{
	String name;
	int age;
	
	public Customer(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString(){
		return "Customer[name="+name+",age="+age+"]";
	}
}