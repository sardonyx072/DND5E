package com.dnd.core;

import com.dnd.dice.DiceGroup;

public class Damage {
	private DiceGroup dice;
	private DamageType type;
	
	public Damage(DiceGroup dice, DamageType type) {
		this.type = type;
		this.dice = dice;
	}
	
	public DiceGroup getDice() {
		return this.dice;
	}
	
	public DamageType getType() {
		return this.type;
	}
	
	public void roll() {
		this.dice.roll();
	}
	
	public void rollAdvantage() {
		this.dice.rollWithAdvantage();
	}
	
	public void rollDisadvantage() {
		this.dice.rollWithDisadvantage();
	}
}
