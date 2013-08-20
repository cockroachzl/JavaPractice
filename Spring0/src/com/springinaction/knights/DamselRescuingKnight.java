package com.springinaction.knights;

public class DamselRescuingKnight implements Knight {
	private RescueDamselQuest quest;
	
	public DamselRescuingKnight() {
		quest = new RescueDamselQuest();
	}
	
	public void embarkOnQuest() throws QuestException {
		
	}
}
