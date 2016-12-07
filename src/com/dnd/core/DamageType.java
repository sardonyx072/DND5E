package com.dnd.core;

public enum DamageType {
	ACID,
	BLUDGEONING,
	COLD,
	FIRE,
	FORCE,
	NECROTIC,
	PIERCING,
	POISON,
	PSYCHIC,
	RADIANT,
	SLASHING;
	
	public String getShortName() {
		return this.name().substring(0,1).toUpperCase().concat(this.name().substring(1).toLowerCase());
	}
	
	public String getAbbreviation() {
		return this.name().substring(0,3).toUpperCase();
	}
}
