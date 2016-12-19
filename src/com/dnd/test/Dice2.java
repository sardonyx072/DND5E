package com.dnd.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Dice2 {
	private Die[] dice;
	private String name;
	private int maxValue;
	private int value;
	
	private class Die {
		private int numSides;
		private int value;
		
		public Die(int numSides) {
			this.numSides = numSides;
			this.value = numSides;
		}
		public int getNumSides() {return this.numSides;}
		public int getValue() {return this.value;}
		public void setToMin() {this.value=1;}
		public void setToMax() {this.value=this.numSides;}
		public int roll() {return this.value = (int)(Math.random()*this.numSides)+1;}
		public int rollAdv() {return this.value = Math.max(this.roll(), this.roll());}
		public int rollDisadv() {return this.value = Math.min(this.roll(), this.roll());}
		public String name() {return "d" + this.numSides;}
		public String toString() {return "["+this.value+"/"+this.numSides+"]";}
	}
	
	private class RollStatTracker {
		private int minValue, maxValue;
		private int[] valuesOfRollsNormal;
		private int numOfTotalRollsNormal, numSuccessRollsNormal, numNeutralRollsNormal, numFailureRollsNormal;
		private int[] valuesOfRollsAdvant;
		private int numOfTotalRollsAdvant, numSuccessRollsAdvant, numNeutralRollsAdvant, numFailureRollsAdvant;
		private int[] valuesOfRollsDisadv;
		private int numOfTotalRollsDisadv, numSuccessRollsDisadv, numNeutralRollsDisadv, numFailureRollsDisadv;
		
		public RollStatTracker(int minValue, int maxValue) {
			this.minValue = minValue;
			this.maxValue = maxValue;
			this.valuesOfRollsNormal = new int[this.maxValue-this.minValue+1];
			this.numOfTotalRollsNormal=0;
			this.numSuccessRollsNormal=0;
			this.numNeutralRollsNormal=0;
			this.numFailureRollsNormal=0;
			this.valuesOfRollsAdvant = new int[this.maxValue-this.minValue+1];
			this.numOfTotalRollsAdvant=0;
			this.numSuccessRollsAdvant=0;
			this.numNeutralRollsAdvant=0;
			this.numFailureRollsAdvant=0;
			this.valuesOfRollsDisadv = new int[this.maxValue-this.minValue+1];
			this.numOfTotalRollsDisadv=0;
			this.numSuccessRollsDisadv=0;
			this.numNeutralRollsDisadv=0;
			this.numFailureRollsDisadv=0;
		}
		public void addNormalRoll(int roll) {
			this.numNeutralRollsNormal++;
			this.addNormalRollHelper(roll);
		}
		public void addNormalRoll(int roll, boolean success) {
			if(success)
				this.numSuccessRollsNormal++;
			else
				this.numFailureRollsNormal++;
			this.addNormalRollHelper(roll);
		}
		private void addNormalRollHelper(int roll) {
			this.numOfTotalRollsNormal++;
			this.valuesOfRollsNormal[roll-this.minValue]++;
		}
		public void addAdvantRoll(int roll) {
			this.numNeutralRollsAdvant++;
			this.addAdvantRollHelper(roll);
		}
		public void addAdvantRoll(int roll, boolean success) {
			if(success)
				this.numSuccessRollsAdvant++;
			else
				this.numFailureRollsAdvant++;
			this.addAdvantRollHelper(roll);
		}
		private void addAdvantRollHelper(int roll) {
			this.numOfTotalRollsAdvant++;
			this.valuesOfRollsAdvant[roll-this.minValue]++;
		}
		public void addDisadvRoll(int roll) {
			this.numNeutralRollsDisadv++;
			this.addDisadvRollHelper(roll);
		}
		public void addDisadvRoll(int roll, boolean success) {
			if(success)
				this.numSuccessRollsDisadv++;
			else
				this.numFailureRollsDisadv++;
			this.addDisadvRollHelper(roll);
		}
		private void addDisadvRollHelper(int roll) {
			this.numOfTotalRollsDisadv++;
			this.valuesOfRollsDisadv[roll-this.minValue]++;
		}
		public int[] getValuesOfRollsNormal() {return this.valuesOfRollsNormal;}
		public int getNumOfTotalRollsNormal() {return this.numOfTotalRollsNormal;}
		public int getNumSuccessRollsNormal() {return this.numSuccessRollsNormal;}
		public int getNumNeutralRollsNormal() {return this.numNeutralRollsNormal;}
		public int getNumFailureRollsNormal() {return this.numFailureRollsNormal;}
	}
	
	public Dice2(int...dice) {
		this.dice = new Die[dice.length];
		boolean first = true;
		for(int i = 0; i < dice.length; i++) {
			this.dice[i] = new Die(dice[i]);
			this.maxValue+=dice[i];
			this.value+=dice[i];
			if(first)
				this.name = 1+this.dice[i].name();
			else {
				StringTokenizer tokenizer = new StringTokenizer(this.name,"+");
				this.name = "";
				boolean added = false;
				while(tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();
					int num = Integer.parseInt(token.substring(0,token.indexOf('d')));
					int type = Integer.parseInt(token.substring(token.indexOf('d')+1));
					if(type == dice[i]) {
						num++;
						added=true;
					}
					this.name += num + "d" + type;
				}
				if(!added)
					this.name += 1 + "d" + dice[i];
			}
			first = false;
		}
		if(first)
			this.name = "empty";
	}
	public int getNumDice() {return this.dice.length;}
	public int getMinValue() {return this.getNumDice();}
	public int getMaxValue() {return this.maxValue;}
	public int getValue() {return this.value;}
	public int getNumSides(int index) {return this.dice[index].getNumSides();}
	public int getValue(int index) {return this.dice[index].getValue();}
	public void sortByMaxValue() {
		Arrays.sort(this.dice, new Comparator<Die>() {
			public int compare(Die d1, Die d2) {
				return d1.getNumSides() - d2.getNumSides();
			}
		});
	}
	public void sortByValue() {
		Arrays.sort(this.dice, new Comparator<Die>() {
			public int compare(Die d1, Die d2) {
				return d1.getValue() - d2.getValue();
			}
		});
	}
	public int getValueDropLowest() {return this.getValueDropLowest(1);}
	public int getValueDropLowest(int numToDrop) {
		this.sortByValue();
		int value = this.getValue();
		for (int i = 0; i < numToDrop && i < this.getNumDice(); i++) {
			value -= this.dice[i].getValue();
		}
		return value;
	}
	public int getValueDropHighest() {return this.getValueDropHighest(1);}
	public int getValueDropHighest(int numToDrop) {
		this.sortByValue();
		int value = this.getValue();
		for (int i = 0; i < numToDrop && i < this.getNumDice(); i++) {
			value -= this.dice[this.getNumDice() - 1 - i].getValue();
		}
		return value;
	}
	public int getValueKeepLowest() {return this.getValueKeepLowest(1);}
	public int getValueKeepLowest(int numToKeep) {
		this.sortByValue();
		int value = this.getValue();
		for (int i = 0; i < this.getNumDice() - numToKeep && i < this.getNumDice(); i++) {
			value -= this.dice[this.getNumDice() - 1 - i].getValue();
		}
		return value;
	}
	public int getValueKeepHighest() {return this.getValueKeepHighest(1);}	
	public int getValueKeepHighest(int numToKeep) {
		this.sortByValue();
		int value = this.getValue();
		for (int i = 0; i < this.getNumDice() - numToKeep && i < this.getNumDice(); i++) {
			value -= this.dice[i].getValue();
		}
		return value;
	}
	public void setValue(int value) {
		if(value >= this.getMinValue() && value <= this.getMaxValue())
			this.value = value;
		else
			throw new IllegalArgumentException("Impossible value for dice! (" + this.getMinValue() + "/" + value + "/" + this.getMaxValue() + ")");
	}
	public void setToMin() {
		for(Die d : this.dice)
			d.setToMin();
	}
	public void setToMax() {
		for(Die d : this.dice)
			d.setToMax();
	}
	public int roll() {
		this.value = 0;
		for(Die d : this.dice) {this.value+=d.roll();}
		return this.getValue();
	}
	public boolean roll(int dc) {
		this.roll();
		return this.beatsDC(dc);
	}
	public int rollUntilSuccess(int dc, int limit) {
		int rolls = 0;
		do{rolls++;}while(!this.roll(dc) && rolls < limit);
		return rolls;
	}
	public int rollUntilFail(int dc, int limit) {
		int rolls = 0;
		do{rolls++;}while(this.roll(dc) && rolls < limit);
		return rolls;
	}
	public int rollSingle(int index) {return this.dice[index].roll();}
	public boolean rollSingle(int index, int dc) {
		this.dice[index].roll();
		return this.beatsDC(dc);
	}
	public int rollAdv() {
		this.value = 0;
		for(Die d : this.dice) {this.value+=d.rollAdv();}
		return this.getValue();
	}
	public boolean rollAdv(int dc) {
		this.rollAdv();
		return this.beatsDC(dc);
	}
	public int rollAdvUntilSuccess(int dc, int limit) {
		int rolls = 0;
		do{rolls++;}while(!this.rollAdv(dc) && rolls < limit);
		return rolls;
	}
	public int rollAdvUntilFail(int dc, int limit) {
		int rolls = 0;
		do{rolls++;}while(this.rollAdv(dc) && rolls < limit);
		return rolls;
	}
	public int rollSingleAdv(int index) {return this.dice[index].rollAdv();}
	public boolean rollSingleAdv(int index, int dc) {
		this.dice[index].rollAdv();
		return this.beatsDC(dc);
	}
	public int rollDisadv() {
		this.value = 0;
		for(Die d : this.dice) {this.value+=d.rollDisadv();}
		return this.getValue();
	}
	public boolean rollDisadv(int dc) {
		this.rollDisadv();
		return this.beatsDC(dc);
	}
	public int rollDisadvUntilSuccess(int dc, int limit) {
		int rolls = 0;
		do{rolls++;}while(!this.rollDisadv(dc) && rolls < limit);
		return rolls;
	}
	public int rollDisadvUntilFail(int dc, int limit) {
		int rolls = 0;
		do{rolls++;}while(this.rollDisadv(dc) && rolls < limit);
		return rolls;
	}
	public int rollSingleDisadv(int index) {return this.dice[index].rollDisadv();}
	public boolean rollSingleDisadv(int index, int dc) {
		this.dice[index].rollDisadv();
		return this.beatsDC(dc);
	}
	public boolean beatsDC(int dc) {return this.getValue() >= dc;}
	public String name() {return this.name;}
	public String toString() {
		String str = this.getValue() + "=";
		for(Die d : this.dice)
			str += d;
		return str;
	}
}
