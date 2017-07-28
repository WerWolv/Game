package com.werwolv.api.event.quest;

import com.werwolv.api.event.Event;
import com.werwolv.quest.Quest;
import com.werwolv.quest.QuestTask;

public class QuestTaskFinishedEvent extends Event {

    private final Quest quest;
    private final QuestTask finishedTask;

    public QuestTaskFinishedEvent(Quest quest, QuestTask finishedTask) {
        this.quest = quest;
        this.finishedTask = finishedTask;
    }

    public Quest getQuest() {
        return quest;
    }

    public QuestTask getFinishedTask() {
        return finishedTask;
    }
}
