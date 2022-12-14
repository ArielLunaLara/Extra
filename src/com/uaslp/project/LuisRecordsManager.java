package com.uaslp.project;

import java.util.List;

import static sun.java2d.Disposer.records;

public class LuisRecordsManager {
    private int maxRecordsInFile;

    public LuisRecordsManager (String)

    public LuisRecordsManager(String record, int maxRecordsInFile) {
        this.maxRecordsInFile = maxRecordsInFile;
    }
    public List<GameRecord> getRecords(){
        List<GameRecord> records;
        return records;
    }
}