package demo;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<? extends Fruit> flist = new ArrayList<Apple>();
		
	}

}


class Fruit {}
class Apple extends Fruit {}
class Orange extends Fruit{}
