package com.werwolv.api.event.quest;

import com.werwolv.api.event.Event;
import com.werwolv.quest.Quest;

public class QuestFinishedEvent extends Event {

    private final Quest quest;

    public QuestFinishedEvent(Quest quest) {
        super();
        this.quest = quest;
    }

    public Quest getQuest() {
        return quest;
    }
}
