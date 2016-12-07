package com.dnd.character;

public enum StatType {
	STRENGTH,
	DEXTERITY,
	CONSTITUTION,
	INTELLIGENCE,
	WISDOM,
	CHARISMA;
	
	public String getName() {
		return this.name().substring(0,1).toUpperCase().concat(this.name().substring(1).toLowerCase());
	}
	
	public String getAbbreviation() {
		return this.name().substring(0,3).toUpperCase();
	}
}
