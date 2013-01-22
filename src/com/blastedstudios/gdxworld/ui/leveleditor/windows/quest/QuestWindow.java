package com.blastedstudios.gdxworld.ui.leveleditor.windows.quest;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.blastedstudios.gdxworld.ui.GDXWindow;
import com.blastedstudios.gdxworld.ui.leveleditor.mousemode.QuestMouseMode;
import com.blastedstudios.gdxworld.world.quest.GDXQuest;

public class QuestWindow extends GDXWindow {
	private final Skin skin;
	private final Table questTable;
	private final List<GDXQuest> quests;
	private static int questCount = 0;
	
	public QuestWindow(final Skin skin, final List<GDXQuest> quests, 
			final QuestMouseMode mouseMode) {
		super("Quest Editor", skin);
		this.skin = skin;
		this.quests = quests;
		questTable = new Table(skin);
		ScrollPane scrollPane = new ScrollPane(questTable);
		Button clearButton = new TextButton("Clear", skin);
		Button addButton = new TextButton("Add", skin);
		for(GDXQuest quest : quests){
			questTable.add(createQuestTable(quest));
			questTable.row();
		}
		clearButton.addListener(new ClickListener() {
			@Override public void clicked(InputEvent event, float x, float y) {
				quests.clear();
				questTable.clear();
			}
		});
		addButton.addListener(new ClickListener() {
			@Override public void clicked(InputEvent event, float x, float y) {
				GDXQuest quest = new GDXQuest();
				quest.setName("newQuest-"+questCount++);
				quests.add(quest);
				questTable.add(createQuestTable(quest));
			}
		});
		add(scrollPane).colspan(3);
		row();
		add(addButton);
		add(clearButton);
		setMovable(false);
		setHeight(400);
		setWidth(400);
	}
	
	private QuestTable createQuestTable(GDXQuest quest){
		return new QuestTable(skin, quest.getName(), quest, new QuestTable.QuestRemoveListener() {
			@Override public void remove(GDXQuest quest) {
				quests.remove(quest);
				questTable.clear();
				for(GDXQuest addQuest : quests){
					questTable.add(createQuestTable(addQuest));
					questTable.row();
				}
			}
		});
	}
}