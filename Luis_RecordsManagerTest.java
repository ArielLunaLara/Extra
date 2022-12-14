package com.uaslp.project;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Luis_RecordsManagerTest {

    private static final int MAX_RECORDS_IN_FILE = 4;

    private final Path fileCreated = Paths.get("test.rec");

    @AfterEach
    public void cleanup() throws IOException {
        Files.deleteIfExists(fileCreated);
    }

    @Test
    public void givenAnEmptyRecord_whenGetRecords_thenListIsEmpty() {
        // Given:
        LuisRecordsManager recordsManager = new LuisRecordsManager("test.rec", MAX_RECORDS_IN_FILE);

        // When:
        List<GameRecord> records = recordsManager.getRecords();

        // Then:
        Assertions.assertFalse(Files.exists(fileCreated));
        Assertions.assertTrue(records.isEmpty());
    }

    @Test
    public void givenAnEmptyRecord_whenSave_thenRecordIsSavedSuccessfully() {
        // Given:
        LuisRecordsManager recordsManager = new LuisRecordsManager("test.rec", MAX_RECORDS_IN_FILE);
        GameRecord record = new GameRecord();

        record.setPlayerName("Miguel");
        record.setScore(15);
        record.setTime(100);

        // When:
        recordsManager.save(record);
        List<GameRecord> records = recordsManager.getRecords();

        // Then:

        Assertions.assertTrue(Files.exists(fileCreated));
        Assertions.assertEquals(1, records.size());

        GameRecord stored = records.get(0);

        Assertions.assertEquals("Miguel", stored.getPlayerName());
        Assertions.assertEquals(15, stored.getScore());
        Assertions.assertEquals(100, stored.getTime());

    }

    @Test
    public void givenAnExistentRecord_whenSave_thenRecordIsSavedSuccessfully() {
        // Given:
        LuisRecordsManager recordsManager = new LuisRecordsManager("test.rec", MAX_RECORDS_IN_FILE);

        recordsManager.save(new GameRecord("Ivan", 10, 100));
        recordsManager.save(new GameRecord("Christian", 20, 200));
        recordsManager.save(new GameRecord("Juan", 5, 50));
        recordsManager.save(new GameRecord("Juan", 15, 800));

        // When:
        List<GameRecord> records = recordsManager.getRecords();

        // Then:

        Assertions.assertTrue(Files.exists(fileCreated));
        Assertions.assertEquals(4, records.size());

        Iterator<GameRecord> iterator = records.iterator();

        // Nota que están ordenados por tiempo, no por score

        validateGameRecord(iterator, "Juan", 15, 800);
        validateGameRecord(iterator, "Christian", 20, 200);
        validateGameRecord(iterator, "Ivan", 10, 100);
        validateGameRecord(iterator, "Juan", 5, 50);





    }

    @Test
    public void givenAnFullRecord_whenSave_thenRecordIsReplacedSuccessfully() {
        // Given:
        LuisRecordsManager recordsManager = new LuisRecordsManager("test.rec", MAX_RECORDS_IN_FILE);

        recordsManager.save(new GameRecord("Ivan", 30, 100));
        recordsManager.save(new GameRecord("Jorge", 80, 200));
        recordsManager.save(new GameRecord("Israel", 20, 300));
        recordsManager.save(new GameRecord("Iris", 10, 400));
        recordsManager.save(new GameRecord("Juan", 50, 500));
        recordsManager.save(new GameRecord("Lupe", 30, 600));
        recordsManager.save(new GameRecord("Gabriel", 12, 700));

        // When:
        recordsManager.save(new GameRecord("Maria", 5, 450));
        recordsManager.save(new GameRecord("Mario", 40, 250));
        recordsManager.save(new GameRecord("Mirna", 30, 150));

        // Then:
        List<GameRecord> records = recordsManager.getRecords();
        Assertions.assertTrue(Files.exists(fileCreated));
        Assertions.assertEquals(MAX_RECORDS_IN_FILE, records.size());

        Iterator<GameRecord> iterator = records.iterator();

        validateGameRecord(iterator, "Gabriel", 12, 700);
        validateGameRecord(iterator, "Lupe", 30, 600);
        validateGameRecord(iterator, "Juan", 50, 500);
        validateGameRecord(iterator, "Maria", 5, 450);
    }

    private void validateGameRecord(Iterator<GameRecord> iterator, String playerName, int score, int tiempo) {
        GameRecord record;

        Assertions.assertTrue(iterator.hasNext());

        record = iterator.next();

        Assertions.assertEquals(playerName, record.getPlayerName());
        Assertions.assertEquals(score, record.getScore());
        Assertions.assertEquals(tiempo, record.getTime());
    }

}
