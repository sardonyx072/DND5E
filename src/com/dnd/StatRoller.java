package com.dnd;

import com.dnd.dice.*;

public class StatRoller {
	public static void main (String[] args) {
		DiceGroup d = new DiceGroup();
		d.add(new Die(6));
		d.add(new Die(6));
		d.add(new Die(6));
		d.add(new Die(6));
		for (int i = 0; i < 6; i++) {
			d.roll();
			System.out.println(d.getValueDropLowest() + " " + d);
		}
	}
}
