package com.blastedstudios.gdxworld.plugin.quest.manifestation.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.blastedstudios.gdxworld.world.quest.QuestStatus.CompletionEnum;
import com.blastedstudios.gdxworld.world.quest.manifestation.AbstractQuestManifestation;

public class PhysicsManifestation extends AbstractQuestManifestation{
	private static final long serialVersionUID = 1L;
	public static PhysicsManifestation DEFAULT = new PhysicsManifestation("Name",new Vector2(),BodyType.StaticBody,0);
	/**
	 * Name of physics object on which we execute tweaks
	 */
	private String name;
	/**
	 * Impulse to be executed on named physics object
	 */
	private Vector2 impulse;
	/**
	 * Changing body type, for instance, unlocking door
	 */
	private BodyType type;
	/**
	 * Provide torque, likely to turn a wheel or similar
	 */
	private float torque;
	
	public PhysicsManifestation(){}
	
	public PhysicsManifestation(String name, Vector2 impulse, BodyType type, float torque){
		this.name = name;
		this.impulse = impulse;
		this.type = type;
		this.torque = torque;
	}

	@Override public CompletionEnum execute() {
		Body body = executor.getPhysicsObject(name); 
		if(body == null){
			Gdx.app.error("PhysicsManifestation.execute", "Can't find physics object " + name);
			return CompletionEnum.COMPLETED;
		}
		body.applyLinearImpulse(impulse, body.getPosition(),true);
		body.setType(type);
		body.applyTorque(torque,true);
		return CompletionEnum.COMPLETED;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector2 getImpulse() {
		return impulse;
	}

	public void setImpulse(Vector2 impulse) {
		this.impulse = impulse;
	}

	public BodyType getType() {
		return type;
	}

	public void setType(BodyType type) {
		this.type = type;
	}

	public float getTorque() {
		return torque;
	}

	public void setTorque(float torque) {
		this.torque = torque;
	}

	@Override public AbstractQuestManifestation clone() {
		return new PhysicsManifestation(name, impulse, type, torque);
	}

	@Override public String toString() {
		return "[PhysicsManifestation: name:" + name + " impulse:" + impulse + 
				" type:" + type + " torque:" + torque + "]";
	}
}