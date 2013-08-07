package com.blastedstudios.gdxworld.plugin.mode.quest;

import net.xeoh.plugins.base.Plugin;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public interface IQuestComponent extends Plugin{
	public Class<? extends Table> getTableClass();
	public Class<?> getComponentClass();
	public String getBoxText();
	public Object getDefault();
	public Table createTable(Skin skin, Object object);

	public interface IQuestComponentManifestation extends IQuestComponent{}
	public interface IQuestComponentTrigger extends IQuestComponent{}
}