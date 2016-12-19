package com.dnd.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Dice {
	private static final String EMPTY_NAME = "none";
	
	private List<Die> dice;
	private int minValue;
	private int maxValue;
	private String name;
	private int value;
	
	private class Die {
		private int minValue;
		private int maxValue;
		private int value;
		
		public Die(int numSides) {this(1,numSides);}
		public Die(int minValue, int maxValue) {
			this.minValue = minValue;
			this.maxValue = maxValue;
			this.value = this.maxValue;
		}
		public int getMinValue() {return this.minValue;}
		public int getMaxValue() {return this.maxValue;}
		public int getValue() {return this.value;}
		public void setValue(int value) {
			if(value >= this.getMinValue() && value <= this.getMaxValue())
				this.value = value;
			else
				throw new IllegalArgumentException("Impossible value for die! (" + this.getMinValue() + "/" + value + "/" + this.getMaxValue() + ")");
		}
		public void setValueToMin() {this.setValue(this.getMinValue());}
		public void setValueToMax() {this.setValue(this.getMaxValue());}
		public int roll() {
			this.setValue((int)(Math.random()*this.getMaxValue())+this.getMinValue());
			return this.getValue();
		}
		public boolean roll(int dc) {return this.roll() >= dc;}
		public int rollAdv() {
			this.setValue(Math.max(this.roll(), this.roll()));
			return this.getValue();
		}
		public boolean rollAdv(int dc) {return this.rollAdv() >= dc;}
		public int rollDisadv() {
			this.setValue(Math.min(this.roll(), this.roll()));
			return this.getValue();
		}
		public boolean rollDisadv(int dc) {return this.rollDisadv() >= dc;}
		public String name() {return "d" + (this.getMinValue()!=1 ? "("+this.getMinValue()+":"+this.getMaxValue()+")" : this.getMaxValue());}
		public String toString() {return "["+(this.getMinValue()!=1 ? this.getMinValue()+"/" : "")+this.getValue()+"/"+this.getMaxValue()+"]";}
	}
	
	public Dice(int...dice) {
		this.dice = new ArrayList<Die>();
		this.name = Dice.EMPTY_NAME;
		for (int i : dice) {this.add(i);}
	}
	public int size() {return this.dice.size();}
	public int getMinValue(int index) {return this.dice.get(index).getMinValue();}
	public int getMaxValue(int index) {return this.dice.get(index).getMaxValue();}
	public int getValue(int index) {return this.dice.get(index).getValue();}
	public void add(int sides) {this.add(this.size(),sides);}
	public void add(int index, int sides) {
		this.dice.add(index, new Die(sides));
		this.minValue+=this.dice.get(this.size()-1).getMinValue();
		this.maxValue+=this.dice.get(this.size()-1).getMaxValue();
		this.value+=this.dice.get(this.size()-1).getValue();
		if (this.name.equals(Dice.EMPTY_NAME)) {
			this.name = "1" + this.dice.get(this.size()-1).name();
		}
		else {
			StringTokenizer tokenizer = new StringTokenizer(this.name,"+");
			this.name = "";
			boolean first = true, added = false;
			while(tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				int numThisCategory = Integer.parseInt(token.substring(0,token.indexOf('d')));
				int sidesThisCategory = Integer.parseInt(token.substring(token.indexOf('d')+1));
				if(sidesThisCategory == sides) {
					numThisCategory+=1;
					added = true;
				}
				this.name += (first ? "" : "+") + numThisCategory + "d" + sidesThisCategory;
				first = false;
			}
			if(!added)
				this.name += (first ? "" : "+") + "1" + this.dice.get(this.size()-1).name();
		}
	}
	public void remove(int index) {
		this.minValue-=this.dice.get(index).getMinValue();
		this.maxValue-=this.dice.get(index).getMaxValue();
		this.value-=this.dice.get(index).getValue();
		int sides = this.dice.remove(index).getMaxValue();
		StringTokenizer tokenizer = new StringTokenizer(this.name,"+");
		this.name = "";
		boolean first = true;
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			int numThisCategory = Integer.parseInt(token.substring(0,token.indexOf('d')));
			int sidesThisCategory = Integer.parseInt(token.substring(token.indexOf('d')+1));
			if(sidesThisCategory == sides)
				numThisCategory-=1;
			if(numThisCategory>0) {
				this.name += (first ? "" : "+") + numThisCategory + "d" + sidesThisCategory;
				first = false;
			}
		}
		if (first)
			this.name+=Dice.EMPTY_NAME;
	}
	public void sortByMinValue() {
		Collections.sort(this.dice, new Comparator<Die>() {
			public int compare(Die d1, Die d2) {
				return d1.getMinValue() - d2.getMinValue();
			}
		});
	}
	public void sortByMaxValue() {
		Collections.sort(this.dice, new Comparator<Die>() {
			public int compare(Die d1, Die d2) {
				return d1.getMaxValue() - d2.getMaxValue();
			}
		});
	}
	public void sortByValue() {
		Collections.sort(this.dice, new Comparator<Die>() {
			public int compare(Die d1, Die d2) {
				return d1.getValue() - d2.getValue();
			}
		});
	}
	public int getMinValue() {return this.minValue;}
	public int getMaxValue() {return this.maxValue;}
	public int getValue() {return this.value;}
	public int getValueDropLowest() {return this.getValueDropLowest(1);}
	public int getValueDropLowest(int numToDrop) {
		this.sortByValue();
		int value = this.getValue();
		for (int i = 0; i < numToDrop && i < this.size(); i++) {
			value -= this.getValue(i);
		}
		return value;
	}
	public int getValueDropHighest() {return this.getValueDropHighest(1);}
	public int getValueDropHighest(int numToDrop) {
		this.sortByValue();
		int value = this.getValue();
		for (int i = 0; i < numToDrop && i < this.size(); i++) {
			value -= this.getValue(this.size() - 1 - i);
		}
		return value;
	}
	public int getValueKeepLowest() {return this.getValueKeepLowest(1);}
	public int getValueKeepLowest(int numToKeep) {
		this.sortByValue();
		int value = this.getValue();
		for (int i = 0; i < this.size() - numToKeep && i < this.size(); i++) {
			value -= this.getValue(this.size() - 1 - i);
		}
		return value;
	}
	public int getValueKeepHighest() {return this.getValueKeepHighest(1);}	
	public int getValueKeepHighest(int numToKeep) {
		this.sortByValue();
		int value = this.getValue();
		for (int i = 0; i < this.size() - numToKeep && i < this.size(); i++) {
			value -= this.getValue(i);
		}
		return value;
	}
	public void setValue(int value) {
		if(value >= this.getMinValue() && value <= this.getMaxValue())
			this.value = value;
		else
			throw new IllegalArgumentException("Impossible value for dice! (" + this.getMinValue() + "/" + value + "/" + this.getMaxValue() + ")");
	}
	public void setValueToMin() {
		for(Die d : this.dice)
			d.setValueToMin();
		this.setValue(this.getMinValue());
	}
	public void setValueToMax() {
		for(Die d : this.dice)
			d.setValueToMax();
		this.setValue(this.getMaxValue());
	}
	public int roll() {
		this.value = 0;
		for(Die d : this.dice) {this.value+=d.roll();}
		return this.getValue();
	}
	public boolean roll(int dc) {return this.roll() >= dc;}
	public boolean roll(int dc, int howManyMustSucceed) {
		for(Die d : this.dice)
			if(d.roll(dc))
				howManyMustSucceed--;
		return howManyMustSucceed <= 0;
	}
	public int rollAdv() {
		this.value = 0;
		for(Die d : this.dice) {this.value+=d.rollAdv();}
		return this.getValue();
	}
	public boolean rollAdv(int dc) {return this.rollAdv() >= dc;}
	public boolean rollAdv(int dc, int howManyMustSucceed) {
		for(Die d : this.dice)
			if(d.rollAdv(dc))
				howManyMustSucceed--;
		return howManyMustSucceed <= 0;
	}
	public int rollDisadv() {
		this.value = 0;
		for(Die d : this.dice) {this.value+=d.rollDisadv();}
		return this.getValue();
	}
	public boolean rollDisadv(int dc) {return this.rollDisadv() >= dc;}
	public boolean rollDisadv(int dc, int howManyMustSucceed) {
		for(Die d : this.dice)
			if(d.rollDisadv(dc))
				howManyMustSucceed--;
		return howManyMustSucceed <= 0;
	}
	public void reroll(int index) {this.dice.get(index).roll();}
	public void rerollAdv(int index) {this.dice.get(index).rollAdv();}
	public void rerollDisadv(int index) {this.dice.get(index).rollDisadv();}
	public boolean beatsDC(int dc) {return this.getValue() >= dc;}
	public String name() {return this.name;}
	public String toString() {
		String str = this.getValue() + "=";
		for(Die d : this.dice)
			str += d;
		return str;
	}
}
