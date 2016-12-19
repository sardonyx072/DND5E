package com.dnd.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.dnd.dice.*;

public class Test {
	public static void main(String[] args) {
		Dice d = new Dice(20,20,20,20);
		System.out.println(d);
		d.roll();
		System.out.println(d);
		d.rollAdv();
		System.out.println(d);
		d.rollDisadv();
		System.out.println(d);
		System.out.println(d.name());
		d.remove(0);
		System.out.println(d);
		System.out.println(d.name());
		d.remove(0);
		System.out.println(d);
		System.out.println(d.name());
		d.remove(0);
		System.out.println(d);
		System.out.println(d.name());
		d.remove(0);
		System.out.println(d);
		System.out.println(d.name());
	}
}
