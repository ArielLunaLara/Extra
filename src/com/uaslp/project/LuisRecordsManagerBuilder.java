package com.uaslp.project;

public class LuisRecordsManagerBuilder {
    private String record;
    private int maxRecordsInFile;

    public LuisRecordsManagerBuilder setRecord(String record) {
        this.record = record;
        return this;
    }

    public LuisRecordsManagerBuilder setMaxRecordsInFile(int maxRecordsInFile) {
        this.maxRecordsInFile = maxRecordsInFile;
        return this;
    }

    public LuisRecordsManager createLuisRecordsManager() {
        return new LuisRecordsManager(record, maxRecordsInFile);
    }
}