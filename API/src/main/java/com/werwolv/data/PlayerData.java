package com.werwolv.data;

import com.werwolv.quest.Quest;

import java.util.HashMap;
import java.util.Map;

public class PlayerData extends SerializableDataObject {

    public Map<String, Quest> notStartedQuests = new HashMap<>();
    public Map<String, Quest> startedQuests = new HashMap<>();
    public Map<String, Quest> finishedQuests = new HashMap<>();

}
