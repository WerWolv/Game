package com.werwolv.quest;

import com.werwolv.item.ItemStack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

public abstract class Quest implements Serializable {

    private String questDescription;

    private Queue<QuestTask> questTasks = new LinkedTransferQueue<>();
    private List<String> questTaskNames = new ArrayList<>();

    public Quest() {

        addQuestTasks();
    }

    public abstract void addQuestTasks();

    public QuestTask finishCurrentTask() {
        return questTasks.poll();
    }

    public abstract Difficulty getDifficulty();

    public abstract String getQuestDescription();

    public void addQuestTask(QuestTask questTask) {
        this.questTasks.add(questTask);
        this.questTaskNames.add(questTask.getTaskName());
    }

    public boolean doesQuestHasTask(String taskName) {
        return this.questTaskNames.contains(taskName);
    }

    public QuestTask getCurrentTask() {
        return questTasks.peek();
    }

    public abstract List<Quest> getDependencies();
    public Queue<QuestTask> getLeftOverQuestTasks() {
        return questTasks;
    }

    public enum Difficulty {
        NOVICE,
        INTERMEDIATE,
        EXPERIENCED,
        MASTER,
        GRANDMASTER,
        SPECIAL
    }
}