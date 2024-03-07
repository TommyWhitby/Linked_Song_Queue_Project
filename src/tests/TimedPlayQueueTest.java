package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import doNotModify.Song;
import questions.PlayQueue;

@TestMethodOrder(OrderAnnotation.class)
public class TimedPlayQueueTest {

    private static PlayQueue testTimedQueue;
    private static int mark;
    private static ArrayList<String> testOutcomes;

    @BeforeAll
    static void setup() {
        testTimedQueue = new PlayQueue();
        Song s = new Song("timed", "timed");
        for (int i = 0; i < 25_000_000; i++) {
            testTimedQueue.addSong(s);
        }

        mark = 0;
        testOutcomes = new ArrayList<>(9);
    }

    @Test
    @Order(1)
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void testSizeTimed() {
        TimedPlayQueueTest.testOutcomes.add("testSizeTimed() failed: 0 marks awarded");

        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());
        assertEquals(25_000_000, testTimedQueue.size());

        TimedPlayQueueTest.mark += 10;
        TimedPlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testSizeTimed() passed: 10 marks awarded");
    }

    @Test

    @Order(2)
    @Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
    void testHasCycleTimed() {
        TimedPlayQueueTest.testOutcomes.add("testHasCycleTimed() failed: 0 marks awarded");

        assertEquals(false, testTimedQueue.hasCycle());
        testTimedQueue.start.previous = testTimedQueue.end;
        assertEquals(true, testTimedQueue.hasCycle());
        testTimedQueue.end.next = testTimedQueue.start;
        assertEquals(true, testTimedQueue.hasCycle());
        testTimedQueue.start.previous = null;
        assertEquals(true, testTimedQueue.hasCycle());
        testTimedQueue.end.next = null;
        assertEquals(false, testTimedQueue.hasCycle());

        testTimedQueue.start.next.next.next.previous = testTimedQueue.start.next.next.next.next.next;
        assertEquals(true, testTimedQueue.hasCycle());
        testTimedQueue.start.next.next.next.previous = testTimedQueue.start.next.next;
        assertEquals(false, testTimedQueue.hasCycle());

        testTimedQueue.end.previous.previous.next = testTimedQueue.end.previous.previous.previous.previous.next;
        assertEquals(true, testTimedQueue.hasCycle());
        testTimedQueue.end.previous.previous.next = testTimedQueue.end;
        assertEquals(false, testTimedQueue.hasCycle());

        testTimedQueue.start.next.next.next.previous = testTimedQueue.end.previous.previous.previous.previous;
        assertEquals(true, testTimedQueue.hasCycle());
        
        TimedPlayQueueTest.mark += 7;
        TimedPlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testHasCycleTimed() passed: 7 marks awarded");
    }

    @AfterAll
    public static void printMark() {
        System.out.println();
        TimedPlayQueueTest.testOutcomes.forEach(outcome -> System.out.println(outcome));
        System.out.println();
        System.out.println(String.format("Timed tests mark: %d", TimedPlayQueueTest.mark));
    }
}
