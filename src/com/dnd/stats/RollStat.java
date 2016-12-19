package com.dnd.stats;

import com.dnd.dice.DiceGroup;

public class RollStat {
	private DiceGroup dice;
	private double meanNormal;
	private double medianNormal;
	private int modeNormal;
	private double successNormal;
	private double failureNormal;
	private double meanAdvantage;
	private double medianAdvantage;
	private int modeAdvantage;
	private double successAdvantage;
	private double failureAdvantage;
	private double meanDisadvantage;
	private double medianDisadvantage;
	private int modeDisadvantage;
	private double successDisadvantage;
	private double failureDisadvantage;
	private double meanOverall;
	private double medianOverall;
	private int modeOverall;
	private double successOverall;
	private double failureOverall;
	
	public RollStat (DiceGroup dice) {
		this.dice = dice;
	}
}
