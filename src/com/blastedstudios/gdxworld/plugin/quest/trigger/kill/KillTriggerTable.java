package com.blastedstudios.gdxworld.plugin.quest.trigger.kill;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.blastedstudios.gdxworld.plugin.mode.quest.TriggerTable;
import com.blastedstudios.gdxworld.world.quest.trigger.AbstractQuestTrigger;

public class KillTriggerTable extends TriggerTable{
	private final TextField nameField;
	private final KillTrigger trigger;
	
	public KillTriggerTable(Skin skin, KillTrigger trigger) {
		super(skin);
		this.trigger = trigger;
		nameField = new TextField(trigger.getName(), skin);
		nameField.setMessageText("<dialog text>");
		add(new Label("Name: ", skin));
		add(nameField);
	}

	@Override public AbstractQuestTrigger apply() {
		trigger.setName(nameField.getText());
		return trigger;
	}
}