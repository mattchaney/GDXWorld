package com.blastedstudios.gdxworld.world.quest;

import java.io.Serializable;

import com.blastedstudios.gdxworld.plugin.quest.manifestation.dialog.DialogManifestation;
import com.blastedstudios.gdxworld.plugin.quest.trigger.aabb.AABBTrigger;
import com.blastedstudios.gdxworld.world.quest.manifestation.AbstractQuestManifestation;
import com.blastedstudios.gdxworld.world.quest.manifestation.IQuestManifestationExecutor;
import com.blastedstudios.gdxworld.world.quest.trigger.AbstractQuestTrigger;
import com.blastedstudios.gdxworld.world.quest.trigger.IQuestTriggerInformationProvider;

public class GDXQuest implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private String name = "Quest-"+count++, prerequisites = "";
	/**
	 * Quest may be repeatable within the course of gameplay. This may manifest
	 * in several ways. One example:
	 * A level designer wishes the player to go up an elevator. This may be 
	 * accomplished by a when the player is within a certain distance of a
	 * switch in the elevator (AABB trigger) and hits the action button (action
	 * required checkbox). If he wants to go back down, there may be another 
	 * quest doing the reverse. Both would be repeatable, as the player can go
	 * up or down ad infinitum. 
	 */
	private boolean repeatable;
	/**
	 * A trigger is what may activate a quest. After triggering, the quest
	 * manifestation should execute. A trigger might be a distance from an
	 * object, if the player hits a button, a being dies, etc.
	 */ 
	private AbstractQuestTrigger trigger = new AABBTrigger(0, 0, 1, 1);
	/**
	 * A manifestation defines what happens as a result of a trigger. A 
	 * manifestation may be that an enemy is spawned, a door is unlocked,
	 * an impulse is applied on an object, etc.
	 */
	private AbstractQuestManifestation manifestation = (AbstractQuestManifestation) DialogManifestation.DEFAULT.clone();
	
	public GDXQuest initialize(IQuestTriggerInformationProvider provider,
			IQuestManifestationExecutor executor){
		trigger.setProvider(provider);
		manifestation.setExecutor(executor);
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

	public boolean isRepeatable() {
		return repeatable;
	}

	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}

	public AbstractQuestTrigger getTrigger() {
		return trigger;
	}

	public void setTrigger(AbstractQuestTrigger trigger) {
		this.trigger = trigger;
	}

	public AbstractQuestManifestation getManifestation() {
		return manifestation;
	}

	public void setManifestation(AbstractQuestManifestation manifestation) {
		this.manifestation = manifestation;
	}

	@Override public Object clone(){
		GDXQuest quest = new GDXQuest();
		quest.setName(name);
		quest.setManifestation(manifestation.clone());
		quest.setPrerequisites(prerequisites);
		quest.setTrigger(trigger.clone());
		quest.setRepeatable(repeatable);
		return quest;
	}
	
	@Override public String toString(){
		return "[GDXQuest name:" + name + " prereq:" + prerequisites + 
				" manifestation:" + manifestation + " trigger:" + trigger + "]";
	}
}
