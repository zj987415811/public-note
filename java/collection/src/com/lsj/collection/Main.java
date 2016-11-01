package com.lsj.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {

	public static void test1(){
		Collection c = new ArrayList();
		c.add(1);				//自动装箱
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
	
	public static void test3(){
		Set s = new HashSet();
		
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		Iterator it = s.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}
	
	public static void test4(){
		Set es = new HashSet();
		
		Employee e1 = new Employee("1000", "JACK");
		Employee e2 = new Employee("1001", "KING");
		Employee e3 = new Employee("2000", "SCOTT");
		Employee e4 = new Employee("2001", "SUN");
		Employee e5 = new Employee("3000", "JIM");
		Employee e6 = new Employee("3001", "COOK");
		
		es.add(e1);
		es.add(e2);
		es.add(e3);
		es.add(e4);
		es.add(e5);
		es.add(e6);
		
		System.out.println(es.size());
	}
	
	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
		test4();
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

class Employee{
	
	String no;
	String name;
	
	public Employee(String no, String name){
		this.no = no;
		this.name = name;
	}
	
	public int hashCode(){	//以员工编号分组 
		return no.hashCode();
	}
}