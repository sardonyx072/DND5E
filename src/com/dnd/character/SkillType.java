package com.dnd.character;

public enum SkillType {
	ACROBATICS(StatType.DEXTERITY),
	ANIMAL_HANDLING(StatType.WISDOM),
	ARCANA(StatType.INTELLIGENCE),
	ATHLETICS(StatType.STRENGTH),
	CONCENTRATION(StatType.CONSTITUTION),
	DECEPTION(StatType.CHARISMA),
	HISTORY(StatType.INTELLIGENCE),
	INSIGHT(StatType.WISDOM),
	INTIMIDATION(StatType.CHARISMA),
	INVESTIGATION(StatType.INTELLIGENCE),
	MEDICINE(StatType.INTELLIGENCE),
	NATURE(StatType.INTELLIGENCE),
	PERCEPTION(StatType.WISDOM),
	PERFORMANCE(StatType.CHARISMA),
	PERSUASION(StatType.CHARISMA),
	RELIGION(StatType.INTELLIGENCE),
	SLEIGHT_OF_HAND(StatType.DEXTERITY),
	STEALTH(StatType.DEXTERITY),
	SURVIVAL(StatType.WISDOM);
	
	private StatType type;
	
	private SkillType(StatType type) {
		this.type = type;
	}
	
	public StatType getType() {
		return this.type;
	}
}
