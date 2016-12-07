package com.dnd.dice;

public class Die implements Rollable {
	private final int sides;
	private String name;
	private int value;
	
	public Die (int sides) {
		this.sides = sides;
		this.name = "d" + this.sides;
		this.value = this.roll();
	}

	@Override
	public int getMinValue() {
		return 1;
	}

	@Override
	public int getMaxValue() {
		return this.sides;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public void setValueToMin() {
		this.value = this.getMinValue();
	}

	@Override
	public void setValueToMax() {
		this.value = this.getMaxValue();
	}

	@Override
	public int roll() {
		return this.value = (int)(Math.random()*this.getMaxValue()) + 1;
	}

	@Override
	public int rollWithAdvantage() {
		return this.value = Math.max(this.roll(), this.roll());
	}

	@Override
	public int rollWithDisadvantage() {
		return this.value = Math.min(this.roll(), this.roll());
	}

	@Override
	public String name() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return "["+this.getValue()+"/"+this.getMaxValue()+"]";
	}
}
