package com.dnd;

//import java.text.DecimalFormat;

import com.dnd.dice.*;

public class RollAverageMeasurer {
	public static void main(String[] args) {
		DiceGroup d1 = new DiceGroup(), d2 = new DiceGroup();
		d1.add(new Die(20));
		d2.add(new Die(10));
		d2.add(new Die(10));
		double movingMean1 = 0, movingMean2 = 0;
		//final DecimalFormat format = new DecimalFormat("0.00");
		for (int i = 0; i < 100000; i++) {
			//mean = (roll + (rollnum*mean))/numrolls
			movingMean1 = ((d1.roll()) + (i*movingMean1))/(i+1);
			movingMean2 = ((d2.roll()) + (i*movingMean2))/(i+1);
			//System.out.println("roll " + (i+1) + ": " + d.getValue() + "\t(mean=" + format.format(movingMean) + ")");
		}
		System.out.println(movingMean1); //average 1d20
		System.out.println(movingMean2); //average 2d10
	}
}
