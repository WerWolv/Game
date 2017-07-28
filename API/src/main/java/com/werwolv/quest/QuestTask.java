package com.werwolv.quest;

import java.io.Serializable;

public class QuestTask implements Serializable {

    private String taskName = "";
    private String taskDescription = "";

    public QuestTask(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

}
