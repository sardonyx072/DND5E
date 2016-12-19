package com.dnd.test;

public interface Rollable {
	public int getMinValue();
	public int getMaxValue();
	public int getValue();
	public int roll();
	public boolean roll(int dc);
	public int rollAdvantage();
	public boolean rollAdvantage(int dc);
	public int rollDisadvantage();
	public boolean rollDisadvantage(int dc);
}
