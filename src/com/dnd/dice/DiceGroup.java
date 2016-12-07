package com.dnd.dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class DiceGroup implements Rollable {
	private List<Die> dice;
	private int minValue;
	private int maxValue;
	private String name;
	private int value;
	
	public DiceGroup(Die...dice) {
		this.dice = new ArrayList<Die>();
		this.minValue = 0;
		this.maxValue = 0;
		this.name = "";
		this.value = 0;
		for(Die d : dice) {
			this.add(d);
		}
	}
	
	public int size() {
		return this.dice.size();
	}
	
	public Die get(int index) {
		return this.dice.get(index);
	}
	
	public void add(Die die) {
		this.dice.add(die);
		this.minValue+=die.getMinValue();
		this.maxValue+=die.getMaxValue();
		StringTokenizer types = new StringTokenizer(this.name,"+");
		this.name = "";
		boolean first = true, added = false;
		while(types.hasMoreTokens()) {
			String token = types.nextToken();
			int num = Integer.parseInt(token.substring(0,token.indexOf('d')));
			int type = Integer.parseInt(token.substring(token.indexOf('d')+1));
			if(die.getMaxValue() == type) {
				num++;
				added = true;
			}
			this.name += (first ? "" : "+") + num + "d" + type;
			first = false;
		}
		if(!added) {
			this.name += (first ? "" : "+") + 1 + "d" + die.getMaxValue();
		}
		this.value+=die.getValue();		
	}

	@Override
	public int getMinValue() {
		return this.minValue;
	}

	@Override
	public int getMaxValue() {
		return this.maxValue;
	}

	@Override
	public int getValue() {
		return this.value;
	}
	
	public int getValueDropLowest() {
		if (this.dice.size() > 0) {
			Collections.sort(this.dice, new Comparator<Die>() {
				public int compare(Die d1, Die d2) {
					return d1.getValue() - d2.getValue();
				}
			});
			return this.getValue()-this.dice.get(this.dice.size()-1).getValue();
		}
		else
			return 0;
	}
	
	@Override
	public void setValueToMin() {
		for (Die d : this.dice) {
			d.setValueToMin();
		}
	}
	
	@Override
	public void setValueToMax() {
		for (Die d : this.dice) {
			d.setValueToMax();
		}
	}

	@Override
	public int roll() {
		this.value=0;
		for (Die d : this.dice) {
			this.value+=d.roll();
		}
		return this.value;
	}

	@Override
	public int rollWithAdvantage() {
		this.value=0;
		for (Die d : this.dice) {
			this.value+=d.rollWithAdvantage();
		}
		return this.value;
	}

	@Override
	public int rollWithDisadvantage() {
		this.value=0;
		for (Die d : this.dice) {
			this.value+=d.rollWithDisadvantage();
		}
		return this.value;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public String toString() {
		if (this.size() == 0) {
			return "{empty}";
		}
		else {
			String name = "{" + this.getMinValue() + "/(" + this.getValue() + ")/" + this.getMaxValue() + "=";
			boolean first = true;
			for (Die d : this.dice) {
				name += (first ? "" : "+") + d;
				first = false;
			}
			name += "}";
			return name;
		}
	}
}
