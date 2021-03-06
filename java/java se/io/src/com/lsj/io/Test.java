package com.lsj.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

import javax.naming.spi.DirectoryManager;

public class Test {
	
	public static void test1(){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("tmp");
			int i1 = fis.read();
			int i2 = fis.read();
			int i3 = fis.read();
			int i4 = fis.read();
			int i5 = fis.read();
			int i6 = fis.read();
			int i7 = fis.read();
			
			System.out.println(i1);
			System.out.println(i2);
			System.out.println(i3);
			System.out.println(i4);
			System.out.println(i5);
			System.out.println(i6);
			System.out.println(i7);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis == null){
				try {
					fis.close();
				} catch (IOException e) {e.printStackTrace();}
			}
		}
	}
	
	public static void test2(){
		
		try {
			FileInputStream fis = new FileInputStream("tmp");
			int temp=0;
			while((temp=fis.read())!=-1){
				System.out.println(temp);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void test3() throws Exception{
		FileInputStream fis = new FileInputStream("tmp");
		byte[] bytes = new byte[3];	//每一次最多读取3个byte
		
		int i1 = fis.read(bytes);
		System.out.println(i1);
		System.out.println(new String(bytes, 0, i1));
		
		int i2 = fis.read(bytes);
		System.out.println(i2);
		System.out.println(new String(bytes, 0, i2));
		
		int i3 = fis.read(bytes);
		System.out.println(i3);
		System.out.println(new String(bytes, 0, i3));
		
		int i4 = fis.read(bytes);
		System.out.println(i4);
	}
	
	public static void test4() throws Exception{
		FileInputStream fis = new FileInputStream("tmp");
		byte[] bytes = new byte[3];	//每一次最多读取3个byte
		int temp = 0;
		
		while((temp=fis.read(bytes))!=-1){
			System.out.print(new String(bytes, 0, temp));
		}
	}
	
	public static void test5() throws Exception{
		FileInputStream fis = new FileInputStream("tmp");

		System.out.println(fis.available());
		System.out.println(fis.read());
		System.out.println(fis.skip(9));
		System.out.println(fis.read());
		System.out.println(fis.available());
	}
	
	public static void test6() throws Exception{
		FileOutputStream fos = new FileOutputStream("tmp2", true);
		
		String msg = "HelloWorld!";
		fos.write(msg.getBytes());
		
		fos.flush();
		fos.close();
	}
	
	public static void copy1() throws Exception{
		//1).创建输入流
		FileInputStream fis = new FileInputStream("tmp2");
		
		//2).创建输出流\
		FileOutputStream fos = new FileOutputStream("tmp3");
		
		//读写
		byte[] bytes = new byte[1024];
		int temp=0;
		while((temp=fis.read(bytes))!=-1){
			fos.write(bytes, 0, temp);
		}
		
		//关闭
		fos.flush();
		fis.close();
		fos.close();
	}
	
	public static void test7() throws Exception{
		FileReader fr = new FileReader("tmp");
		
		char[] chars = new char[1];
		/*
		int temp = 0;
		while((temp=fr.read(chars))!=-1){
			System.out.print(new String(chars, 0, temp));
		}*/
		fr.read(chars);
		System.out.println(chars);
	}
	
	public static void test8() throws Exception{
		FileWriter fw = new FileWriter("tmp3", true);
		
		fw.write("lsj");
		fw.flush();
		fw.close();
	}
	
	public static void copy2() throws Exception{
		FileReader fr = new FileReader("tmp3");
		FileWriter fw = new FileWriter("tmp4");
		
		char[] chars = new char[512];
		int temp = 0;
		
		while((temp=fr.read(chars))!=-1){
			fw.write(chars, 0, temp);
		}
		
		fw.flush();
		fw.close();
		fr.close();
		
	}
	
	public static void test9() throws Exception{
		//创建一个带有缓冲区的字符输入流
		BufferedReader br = new BufferedReader(new FileReader("tmp"));	//将文件字符输入流，包装成带有缓冲区的输入流
		String temp=null;
		while((temp = br.readLine()) != null){
			System.out.println(temp);
		}
		
		br.close();	//关闭只需要关闭最外层的包装流。
	}
	
	public static void test10() throws Exception{
		//创建一个带有缓冲区的字符输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("tmp")));
		String temp=null;
		while((temp = br.readLine()) != null){
			System.out.print(temp);
		}
		
		br.close();	//关闭只需要关闭最外层的包装流。
	}
	
	public static void test11() throws Exception{
		Scanner s = new Scanner(System.in);		//System.in是标准输入流:InputStream
		String str = s.next();		//接收输入，以空格为分隔符, nextLine()以换行为分割
		System.out.println(str);
	}
	
	public static void test12() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		System.out.println(str);
		br.close();
	}
	
	public static void test13() throws Exception{
		BufferedWriter bw = new BufferedWriter(new FileWriter("tmp5"));
		bw.write("奥运会!");bw.newLine();
		bw.write("么意思");
		bw.flush();
		bw.close();
	}
	
	public static void test14() throws Exception{
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("tmp6"));
		
		dos.writeByte(10);
		dos.writeShort(11);
		dos.writeInt(12);
		dos.writeLong(1000L);
		dos.writeFloat(3.2f);
		dos.writeDouble(2.3);
		
		dos.flush();
		dos.close();
	}
	
	public static void test15() throws Exception{
		DataInputStream dis = new DataInputStream(new FileInputStream("tmp6"));
		
		System.out.println(dis.readByte());
		System.out.println(dis.readShort());
		System.out.println(dis.readInt());
		System.out.println(dis.readLong());
		System.out.println(dis.readFloat());
		System.out.println(dis.readDouble());
		
		dis.close();
	}
	
	public static void test16() throws Exception{
		PrintStream ps = System.out;
		PrintStream out = new PrintStream(new FileOutputStream("tmp7"));
		PrintWriter writer = new PrintWriter(out);
		ps.println("123");
		out.print("ni hao ya !");
		out.flush();
		out.close();
	}
	
	public static void test17() throws Exception{
		User u1 = new User("刘德华");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tmp8"));
		oos.writeObject(u1);
		oos.flush();
		oos.close();
	}
	
	public static void test18() throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tmp8"));
		
		Object o = ois.readObject();
		
		System.out.println(o);
		
		ois.close();
	}
	
	public static void test19() throws Exception{
		File f1 = new File("tmp9");
		
		System.out.println(f1.exists());
		
	}
	
	public static void disfiles(String path, int deep){
		File dir = new File(path);
		
		if(!dir.exists()){
			return;
		}
		
		File[] files = dir.listFiles();
		
		for(File file : files){
			for(int i=0; i<deep; i++){
				System.out.print(" ");
			}
			System.out.println(file.getAbsolutePath());
			if(file.isDirectory()){
				disfiles(file.getAbsolutePath(), deep+1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
//		test1();
//		test2();
//		test3();
//		test4();
//		test5();
//		test6();
//		copy1();
//		test7();
//		test8();
//		copy2();
//		test9();
//		test10();
//		test11();
//		test12();
//		test13();
//		test14();
//		test15();
//		test16();
//		test17();
//		test18();
//		test19();
		disfiles("D:\\DESIGN\\cpp", 0);
		System.out.println("\ndone!");
	}

}


class User implements Serializable{
	String name;
	
	User(String name){
		this.name = name;
	}
	
	public String toString(){
		return "USer[name="+name+"]";
	}
}
