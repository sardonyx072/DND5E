package com.dnd.test;

import com.dnd.dice.*;

public class Test {
	public static void main(String[] args) {
		DiceGroup x = new DiceGroup(new Die(20),new Die(20),new Die(20),new Die(20));
		System.out.println(x);
		x.roll();
		System.out.println(x);
		System.out.println(x.name());
	}
}
