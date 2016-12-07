package com.dnd.dice;

public interface Rollable {
	public int getValue();
	
	public int getMinValue();
	
	public int getMaxValue();
	
	public void setValueToMin();
	
	public void setValueToMax();
	
	public int roll();
	
	public int rollWithAdvantage();
	
	public int rollWithDisadvantage();
	
	public String name();
}
