package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;

import doNotModify.Song;
import doNotModify.SongNode;
import questions.PlayQueue;

@TestMethodOrder(OrderAnnotation.class)
public class PlayQueueTest {
    private static int mark;
    private static ArrayList<String> testOutcomes;
    private static Song[] testSongsA;
    private static Song[] testSongsB;

    @BeforeAll
    static void setup() {
        mark = 0;
        testOutcomes = new ArrayList<>(9);

        testSongsA = new Song[]{
            new Song("Anti-Hero", "Taylor Swift"),
            new Song("Love Story", "Taylor Swift"),
            new Song("Blank Space", "Taylor Swift"),
            new Song("Shake It Off", "Taylor Swift"),
            new Song("Delicate", "Taylor Swift"),
            new Song("All Too Well", "Taylor Swift"),
            new Song("Red", "Taylor Swift"),
            new Song("Style", "Taylor Swift"),
            new Song("22", "Taylor Swift"),
            new Song("Enchanted", "Taylor Swift"),
            new Song("White Horse", "Taylor Swift"),
        };

        testSongsB = new Song[]{
            new Song("DDU-DU DDU-DU", "BLACKPINK"),
            new Song("Kill This Love", "BLACKPINK"),
            new Song("Lovesick Girls", "BLACKPINK"),
            new Song("How You Like That", "BLACKPINK"),
            new Song("BOOMBAYAH", "BLACKPINK"),
            new Song("Dynamite", "BTS"),
            new Song("Black Swan", "BTS"),
            new Song("Dope", "BTS"),
            new Song("Fire", "BTS"),
            new Song("Photograph", "Ed Sheeran"),
            new Song("Castle on the Hill", "Ed Sheeran"),
            new Song("The A Team", "Ed Sheeran"),
            new Song("Lego House", "Ed Sheeran"),
            new Song("Thinking Out Loud", "Ed Sheeran"),
            new Song("TT", "TWICE"),
            new Song("Likely", "TWICE"),
            new Song("Fancy", "TWICE"),
            new Song("Cheer Up", "TWICE"),
            new Song("Set Me Free", "TWICE"),
            new Song("Celebrate", "TWICE"),
            new Song("Bad Guy", "Billie Eilish"),
            new Song("When The Party's Over", "Billie Eilish"),
            new Song("Bury A Friend", "Billie Eilish"),
            new Song("Therefore I Am", "Billie Eilish"),
            new Song("Copycat", "Billie Eilish")
        };
    }

    @Test
    @Order(1)
    public void testAddSong() {
        PlayQueueTest.testOutcomes.add("testAddSong() failed: 0 marks awarded");

        PlayQueue queue = new PlayQueue();
        assertNull(queue.start);
        assertNull(queue.end);

        queue.addSong(testSongsA[0]);
        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[0], queue.end.song);
        assertNull(queue.start.previous);
        assertNull(queue.start.next);

        queue.addSong(testSongsA[1]);
        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[1], queue.end.song);
        assertEquals(testSongsA[1], queue.start.next.song);

        queue.addSong(testSongsA[2]);
        queue.addSong(testSongsA[3]);
        queue.addSong(testSongsA[4]);

        assertNull(queue.start.previous);
        assertNull(queue.end.next);

        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[1], queue.start.next.song);
        assertEquals(testSongsA[2], queue.start.next.next.song);
        assertEquals(testSongsA[3], queue.start.next.next.next.song);
        assertEquals(testSongsA[4], queue.start.next.next.next.next.song);
        assertNull(queue.start.next.next.next.next.next);

        assertEquals(testSongsA[4], queue.end.song);
        assertEquals(testSongsA[3], queue.end.previous.song);
        assertEquals(testSongsA[2], queue.end.previous.previous.song);
        assertEquals(testSongsA[1], queue.end.previous.previous.previous.song);
        assertEquals(testSongsA[0], queue.end.previous.previous.previous.previous.song);
        assertNull(queue.end.previous.previous.previous.previous.previous);

        PlayQueueTest.mark += 10;
        PlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testAddSong() passed: 10 marks awarded");
    }

    @Test
    @Order(2)
    public void testRemoveSongSong() {
        PlayQueueTest.testOutcomes.add("testRemoveSongSong() failed: 0 marks awarded");

        Song notInQueue = testSongsA[5];

        PlayQueue queue = new PlayQueue();
        assertFalse(queue.removeSong(testSongsA[0]));
        assertNull(queue.start);
        assertNull(queue.end);

        queue.addSong(testSongsA[0]);
        queue.addSong(testSongsA[1]);
        queue.addSong(testSongsA[2]);
        queue.addSong(testSongsA[3]);
        queue.addSong(testSongsA[4]);
        assertEquals(testSongsA[0], queue.start.song);          // refer to testAddSong
        assertEquals(testSongsA[1], queue.start.next.song);     // refer to testAddSong
        assertEquals(testSongsA[3], queue.end.previous.song);   // refer to testAddSong
        assertEquals(testSongsA[4], queue.end.song);            // refer to testAddSong

        assertFalse(queue.removeSong(notInQueue));

        assertTrue(queue.removeSong(testSongsA[1]));
        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[2], queue.start.next.song);
        assertEquals(testSongsA[0], queue.end.previous.previous.previous.song);

        assertTrue(queue.removeSong(testSongsA[0]));
        assertEquals(testSongsA[2], queue.start.song);
        assertEquals(testSongsA[2], queue.end.previous.previous.song);
        assertNull(queue.end.previous.previous.previous);

        PlayQueueTest.mark += 10;
        PlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testRemoveSongSong() passed: 10 marks awarded");
    }

    @Test
    @Order(3)
    public void testRemoveSongIndex() {
        PlayQueueTest.testOutcomes.add("testRemoveSongIndex() failed: 0 marks awarded");

        PlayQueue queue = new PlayQueue();
        assertNull(queue.removeSong(0));
        assertNull(queue.start);
        assertNull(queue.end);

        queue.addSong(testSongsA[0]);
        queue.addSong(testSongsA[1]);
        queue.addSong(testSongsA[2]);
        queue.addSong(testSongsA[3]);
        queue.addSong(testSongsA[4]);
        queue.addSong(testSongsA[5]);
        assertEquals(testSongsA[0], queue.start.song);          // refer to testAddSong
        assertEquals(testSongsA[1], queue.start.next.song);     // refer to testAddSong
        assertEquals(testSongsA[4], queue.end.previous.song);   // refer to testAddSong
        assertEquals(testSongsA[5], queue.end.song);            // refer to testAddSong

        assertEquals(testSongsA[0], queue.removeSong(0));
        assertEquals(testSongsA[1], queue.start.song);
        assertEquals(testSongsA[1], queue.end.previous.previous.previous.previous.song);
        assertNull(queue.end.previous.previous.previous.previous.previous);

        assertEquals(testSongsA[3], queue.removeSong(2));
        assertEquals(testSongsA[1], queue.start.song);
        assertEquals(testSongsA[1], queue.end.previous.previous.previous.song);
        assertEquals(testSongsA[2], queue.start.next.song);
        assertEquals(testSongsA[2], queue.end.previous.previous.song);
        assertEquals(testSongsA[4], queue.start.next.next.song);
        assertEquals(testSongsA[4], queue.end.previous.song);

        assertEquals(testSongsA[5], queue.removeSong(3));
        assertEquals(testSongsA[4], queue.end.song);
        assertEquals(testSongsA[4], queue.start.next.next.song);
        assertEquals(testSongsA[2], queue.start.next.song);
        assertEquals(testSongsA[2], queue.end.previous.song);
        assertEquals(testSongsA[1], queue.start.song);
        assertEquals(testSongsA[1], queue.end.previous.previous.song);

        assertNull(queue.start.previous);
        assertNull(queue.end.next);

        PlayQueueTest.mark += 10;
        PlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testRemoveSongIndex() passed: 10 marks awarded");
    }

    @Test
    @Order(4)
    void testSize() {
        PlayQueueTest.testOutcomes.add("testSize() failed: 0 marks awarded");
        PlayQueue empty = new PlayQueue();
        assertEquals(0, empty.size());

        PlayQueue singleSong = new PlayQueue();
        singleSong.addSong(testSongsB[2]);
        assertEquals(1, singleSong.size());

        PlayQueue fewSongs = new PlayQueue();
        fewSongs.addSong(testSongsB[5]);
        fewSongs.addSong(testSongsB[6]);
        fewSongs.addSong(testSongsB[7]);
        assertEquals(3, fewSongs.size());

        PlayQueue oneRemoved = new PlayQueue();
        oneRemoved.addSong(testSongsA[0]);
        oneRemoved.addSong(testSongsA[1]);
        oneRemoved.addSong(testSongsB[0]);
        oneRemoved.addSong(testSongsB[1]);
        oneRemoved.removeSong(2);
        assertEquals(3, oneRemoved.size());

        PlayQueue someRemoved = new PlayQueue();
        someRemoved.addSong(testSongsB[6]);
        someRemoved.addSong(testSongsB[10]);
        someRemoved.addSong(testSongsB[12]);
        someRemoved.addSong(testSongsA[3]);
        someRemoved.addSong(testSongsA[4]);
        someRemoved.addSong(testSongsA[6]);
        someRemoved.addSong(testSongsB[3]);
        someRemoved.addSong(testSongsB[0]);
        someRemoved.removeSong(0);
        someRemoved.removeSong(1);
        someRemoved.removeSong(2);
        someRemoved.removeSong(4);
        assertEquals(4, someRemoved.size());

        PlayQueue someRemovedAgain = new PlayQueue();
        someRemovedAgain.addSong(testSongsA[0]);
        someRemovedAgain.addSong(testSongsA[1]);
        someRemovedAgain.addSong(testSongsA[2]);
        someRemovedAgain.addSong(testSongsA[3]);
        someRemovedAgain.addSong(testSongsA[4]);
        someRemovedAgain.addSong(testSongsA[5]);

        someRemovedAgain.removeSong(testSongsA[0]);
        someRemovedAgain.removeSong(testSongsA[2]);
        someRemovedAgain.removeSong(testSongsA[5]);
        assertEquals(3, someRemovedAgain.size());

        PlayQueue nothingRemoved = new PlayQueue();
        nothingRemoved.addSong(testSongsA[0]);
        nothingRemoved.addSong(testSongsA[1]);
        nothingRemoved.addSong(testSongsA[2]);
        nothingRemoved.addSong(testSongsA[3]);
        nothingRemoved.addSong(testSongsA[4]);
        nothingRemoved.removeSong(-1);
        nothingRemoved.removeSong(5);
        nothingRemoved.removeSong(null);
        nothingRemoved.removeSong(testSongsA[5]);
        assertEquals(5, nothingRemoved.size());

        PlayQueueTest.mark += 10;
        PlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testSize() passed: 10 marks awarded");
    }

    @Test
    @Order(5)
    void testReverseQueue() {
        PlayQueueTest.testOutcomes.add("testReverseQueue() failed: 0 marks awarded");
        PlayQueue empty = new PlayQueue();
        assertNull(empty.start);
        assertNull(empty.end);
        empty.reverseQueue();
        assertNull(empty.start);
        assertNull(empty.end);

        PlayQueue single = new PlayQueue();
        single.addSong(testSongsB[10]);
        single.reverseQueue();
        assertEquals(testSongsB[10], single.start.song);
        assertEquals(testSongsB[10], single.end.song);

        PlayQueue three = new PlayQueue();
        three.addSong(testSongsA[3]);
        three.addSong(testSongsA[5]);
        three.addSong(testSongsA[7]);
        three.reverseQueue();
        
        assertEquals(testSongsA[7], three.start.song);
        assertEquals(testSongsA[5], three.start.next.song);
        assertEquals(testSongsA[3], three.start.next.next.song);
        assertEquals(testSongsA[7], three.end.previous.previous.song);
        assertEquals(testSongsA[5], three.end.previous.song);
        assertEquals(testSongsA[3], three.end.song);

        PlayQueue eight = new PlayQueue();
        eight.addSong(testSongsA[2]);
        eight.addSong(testSongsB[3]);
        eight.addSong(testSongsA[4]);
        eight.addSong(testSongsB[5]);
        eight.addSong(testSongsA[6]);
        eight.addSong(testSongsB[7]);
        eight.addSong(testSongsA[8]);
        eight.addSong(testSongsB[9]);
        eight.reverseQueue();
        assertEquals(testSongsB[9], eight.start.song);
        assertEquals(testSongsA[8], eight.start.next.song);
        assertEquals(testSongsB[7], eight.start.next.next.song);
        assertEquals(testSongsA[6], eight.start.next.next.next.song);
        assertEquals(testSongsB[5], eight.start.next.next.next.next.song);
        assertEquals(testSongsA[4], eight.start.next.next.next.next.next.song);
        assertEquals(testSongsB[3], eight.start.next.next.next.next.next.next.song);
        assertEquals(testSongsA[2], eight.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[9], eight.end.previous.previous.previous.previous.previous.previous.previous.song);
        assertEquals(testSongsA[8], eight.end.previous.previous.previous.previous.previous.previous.song);
        assertEquals(testSongsB[7], eight.end.previous.previous.previous.previous.previous.song);
        assertEquals(testSongsA[6], eight.end.previous.previous.previous.previous.song);
        assertEquals(testSongsB[5], eight.end.previous.previous.previous.song);
        assertEquals(testSongsA[4], eight.end.previous.previous.song);
        assertEquals(testSongsB[3], eight.end.previous.song);
        assertEquals(testSongsA[2], eight.end.song);

        PlayQueueTest.mark += 10;
        PlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testReverseQueue() passed: 10 marks awarded");
    }

    @Test
    @Order(6)
    void testMoveSong() {
        PlayQueueTest.testOutcomes.add("testMoveSong() failed: 0 marks awarded");
        PlayQueue queue = new PlayQueue();
        queue.addSong(testSongsA[0]);
        queue.addSong(testSongsA[1]);
        queue.addSong(testSongsA[2]);
        queue.addSong(testSongsA[3]);
        queue.addSong(testSongsA[4]);
        queue.addSong(testSongsA[5]);

        assertEquals(testSongsA[0], queue.start.song);          // refer to testAddSong
        assertEquals(testSongsA[1], queue.start.next.song);     // refer to testAddSong
        assertEquals(testSongsA[4], queue.end.previous.song);   // refer to testAddSong
        assertEquals(testSongsA[5], queue.end.song);            // refer to testAddSong

        queue.moveSong(0, 0);
        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[1], queue.start.next.song);
        
        queue.moveSong(-1, 1);
        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[1], queue.start.next.song);

        SongNode songNodeA = queue.start;
        SongNode songNodeB = queue.start.next;
        SongNode songNodeC = queue.start.next.next;
        SongNode songNodeD = queue.start.next.next.next;
        SongNode songNodeE = queue.start.next.next.next.next;
        SongNode songNodeF = queue.start.next.next.next.next.next;

        queue.moveSong(0, 2);
        assertEquals(testSongsA[1], queue.start.song);
        assertEquals(testSongsA[2], queue.start.next.song);
        assertEquals(testSongsA[0], queue.start.next.next.song);
        assertEquals(testSongsA[3], queue.start.next.next.next.song);
        assertEquals(testSongsA[0], queue.end.previous.previous.previous.song);

        assertEquals(songNodeB, queue.start);
        assertEquals(songNodeC, queue.start.next);
        assertEquals(songNodeA, queue.start.next.next);

        queue.moveSong(2, -2);
        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[1], queue.start.next.song);
        assertEquals(testSongsA[2], queue.start.next.next.song);
        assertEquals(testSongsA[3], queue.start.next.next.next.song);
        assertEquals(testSongsA[2], queue.end.previous.previous.previous.song);
        assertEquals(testSongsA[0], queue.end.previous.previous.previous.previous.previous.song);

        assertEquals(songNodeA, queue.start);
        assertEquals(songNodeB, queue.start.next);
        assertEquals(songNodeC, queue.start.next.next);
        
        assertEquals(testSongsA[5], queue.start.next.next.next.next.next.song);
        assertEquals(testSongsA[5], queue.end.song);

        queue.moveSong(5, -2);
        assertEquals(testSongsA[4], queue.end.song);
        assertEquals(testSongsA[3], queue.end.previous.song);
        assertEquals(testSongsA[5], queue.end.previous.previous.song);
        assertEquals(testSongsA[2], queue.end.previous.previous.previous.song);
        assertEquals(testSongsA[4], queue.start.next.next.next.next.next.song);
        assertEquals(testSongsA[3], queue.start.next.next.next.next.song);
        assertEquals(testSongsA[5], queue.start.next.next.next.song);
        assertEquals(testSongsA[2], queue.start.next.next.song);

        assertEquals(songNodeE, queue.end);
        assertEquals(songNodeF, queue.end.previous.previous);

        queue.moveSong(2, 1);
        assertEquals(testSongsA[1], queue.start.next.song);
        assertEquals(testSongsA[5], queue.start.next.next.song);
        assertEquals(testSongsA[2], queue.start.next.next.next.song);
        assertEquals(testSongsA[3], queue.start.next.next.next.next.song);

        assertEquals(testSongsA[1], queue.end.previous.previous.previous.previous.song);
        assertEquals(testSongsA[5], queue.end.previous.previous.previous.song);
        assertEquals(testSongsA[2], queue.end.previous.previous.song);
        assertEquals(testSongsA[3], queue.end.previous.song);

        queue.moveSong(0, 5);
        assertEquals(testSongsA[1], queue.start.song);
        assertEquals(testSongsA[5], queue.start.next.song);
        assertEquals(testSongsA[4], queue.start.next.next.next.next.song);
        assertEquals(testSongsA[0], queue.start.next.next.next.next.next.song);
        assertNull(queue.start.next.next.next.next.next.next);

        assertEquals(testSongsA[1], queue.end.previous.previous.previous.previous.previous.song);
        assertEquals(testSongsA[5], queue.end.previous.previous.previous.previous.song);
        assertEquals(testSongsA[4], queue.end.previous.song);
        assertEquals(testSongsA[0], queue.end.song);
        assertNull(queue.end.previous.previous.previous.previous.previous.previous);

        assertEquals(songNodeB, queue.start);
        assertEquals(songNodeA, queue.end);

        queue.moveSong(5, -5);
        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[1], queue.start.next.song);
        assertEquals(testSongsA[3], queue.start.next.next.next.next.song);
        assertEquals(testSongsA[4], queue.start.next.next.next.next.next.song);

        assertEquals(testSongsA[0], queue.end.previous.previous.previous.previous.previous.song);
        assertEquals(testSongsA[1], queue.end.previous.previous.previous.previous.song);
        assertEquals(testSongsA[3], queue.end.previous.song);
        assertEquals(testSongsA[4], queue.end.song);

        assertEquals(songNodeA, queue.start);
        assertEquals(songNodeD, queue.end.previous);
        assertEquals(songNodeE, queue.end);

        PlayQueueTest.mark += 10;
        PlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testMoveSong() passed: 10 marks awarded");
    }

    @Test
    @Order(7)
    void testSwapSongs() {
        PlayQueueTest.testOutcomes.add("testSwapSongs() failed: 0 marks awarded");
        PlayQueue queue = new PlayQueue();
        queue.addSong(testSongsA[0]);
        queue.addSong(testSongsA[1]);
        queue.addSong(testSongsA[2]);
        queue.addSong(testSongsA[3]);
        queue.addSong(testSongsA[4]);
        queue.addSong(testSongsA[5]);
        queue.addSong(testSongsA[6]);

        assertEquals(testSongsA[0], queue.start.song);          // refer to testAddSong
        assertEquals(testSongsA[1], queue.start.next.song);     // refer to testAddSong
        assertEquals(testSongsA[5], queue.end.previous.song);   // refer to testAddSong
        assertEquals(testSongsA[6], queue.end.song);            // refer to testAddSong

        SongNode songNodeA = queue.start;
        SongNode songNodeB = queue.start.next;
        SongNode songNodeC = queue.start.next.next;
        SongNode songNodeD = queue.start.next.next.next;
        SongNode songNodeE = queue.start.next.next.next.next;
        SongNode songNodeF = queue.start.next.next.next.next.next;
        SongNode songNodeG = queue.start.next.next.next.next.next.next;

        queue.swapSongs(-1, 500);
        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[1], queue.start.next.song);
        assertEquals(testSongsA[5], queue.end.previous.song);
        assertEquals(testSongsA[6], queue.end.song);

        queue.swapSongs(1, 1);
        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[1], queue.start.next.song);
        assertEquals(testSongsA[2], queue.start.next.next.song);

        queue.swapSongs(1, 4);
        assertEquals(testSongsA[0], queue.start.song);
        assertEquals(testSongsA[4], queue.start.next.song);
        assertEquals(testSongsA[2], queue.start.next.next.song);
        assertEquals(testSongsA[3], queue.start.next.next.next.song);
        assertEquals(testSongsA[1], queue.start.next.next.next.next.song);
        assertEquals(testSongsA[5], queue.start.next.next.next.next.next.song);
        assertEquals(testSongsA[6], queue.start.next.next.next.next.next.next.song);
        
        assertEquals(testSongsA[0], queue.end.previous.previous.previous.previous.previous.previous.song);
        assertEquals(testSongsA[4], queue.end.previous.previous.previous.previous.previous.song);
        assertEquals(testSongsA[2], queue.end.previous.previous.previous.previous.song);
        assertEquals(testSongsA[3], queue.end.previous.previous.previous.song);
        assertEquals(testSongsA[1], queue.end.previous.previous.song);
        assertEquals(testSongsA[5], queue.end.previous.song);
        assertEquals(testSongsA[6], queue.end.song);

        assertEquals(songNodeE, queue.start.next);
        assertEquals(songNodeB, queue.start.next.next.next.next);

        queue.swapSongs(0, 2);
        assertEquals(testSongsA[2], queue.start.song);
        assertEquals(testSongsA[4], queue.start.next.song);
        assertEquals(testSongsA[0], queue.start.next.next.song);
        assertEquals(testSongsA[3], queue.start.next.next.next.song);
        assertNull(queue.start.previous);
        
        assertEquals(testSongsA[2], queue.end.previous.previous.previous.previous.previous.previous.song);
        assertEquals(testSongsA[4], queue.end.previous.previous.previous.previous.previous.song);
        assertEquals(testSongsA[0], queue.end.previous.previous.previous.previous.song);
        assertEquals(testSongsA[3], queue.end.previous.previous.previous.song);
        
        assertEquals(songNodeC, queue.start);
        assertEquals(songNodeA, queue.start.next.next);

        queue.swapSongs(3, 6);
        assertEquals(testSongsA[6], queue.start.next.next.next.song);
        assertEquals(testSongsA[0], queue.start.next.next.song);
        assertEquals(testSongsA[1], queue.start.next.next.next.next.song);

        assertEquals(testSongsA[3], queue.start.next.next.next.next.next.next.song);
        assertEquals(testSongsA[5], queue.start.next.next.next.next.next.song);
        assertNull(queue.start.next.next.next.next.next.next.next);

        assertEquals(testSongsA[3], queue.end.song);

        assertEquals(songNodeG, queue.start.next.next.next);
        assertEquals(songNodeA, queue.start.next.next);
        assertEquals(songNodeB, queue.start.next.next.next.next);

        assertEquals(songNodeD, queue.end);
        assertEquals(songNodeF, queue.end.previous);

        queue.swapSongs(6, 0);
        assertEquals(testSongsA[3], queue.start.song);
        assertNull(queue.start.previous);
        assertEquals(testSongsA[4], queue.start.next.song);
        assertEquals(testSongsA[2], queue.start.next.next.next.next.next.next.song);

        assertEquals(testSongsA[2], queue.end.song);
        assertEquals(testSongsA[3], queue.end.previous.previous.previous.previous.previous.previous.song);
        assertNull(queue.end.next);
        assertEquals(testSongsA[5], queue.end.previous.song);

        assertEquals(songNodeD, queue.start);
        assertEquals(songNodeC, queue.end);

        PlayQueueTest.mark += 10;
        PlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testSwapSongs() passed: 10 marks awarded");
    }

    @Test
    @Order(8)
    void testHasCycle() {
        PlayQueueTest.testOutcomes.add("testHasCycle() failed: 0 marks awarded");
        PlayQueue empty = new PlayQueue();
        assertFalse(empty.hasCycle());

        PlayQueue noCycle = new PlayQueue();
        noCycle.addSong(testSongsA[0]);
        noCycle.addSong(testSongsA[1]);
        noCycle.addSong(testSongsA[2]);
        noCycle.addSong(testSongsA[3]);
        noCycle.addSong(testSongsA[4]);
        noCycle.addSong(testSongsA[5]);
        noCycle.addSong(testSongsA[6]);
        assertFalse(noCycle.hasCycle());

        PlayQueue simpleCycleOne = new PlayQueue();
        simpleCycleOne.addSong(testSongsA[0]);
        simpleCycleOne.start.next = simpleCycleOne.start;
        assertTrue(simpleCycleOne.hasCycle());

        PlayQueue simpleCycleTwo = new PlayQueue();
        simpleCycleTwo.addSong(testSongsA[0]);
        simpleCycleTwo.start.previous = simpleCycleTwo.start;
        assertTrue(simpleCycleTwo.hasCycle());

        PlayQueue smallCycleOne = new PlayQueue();
        smallCycleOne.addSong(testSongsA[0]);
        smallCycleOne.addSong(testSongsA[1]);
        smallCycleOne.addSong(testSongsA[2]);
        smallCycleOne.end.next = smallCycleOne.start;
        assertTrue(smallCycleOne.hasCycle());

        PlayQueue smallCycleTwo = new PlayQueue();
        smallCycleTwo.addSong(testSongsA[0]);
        smallCycleTwo.addSong(testSongsA[1]);
        smallCycleTwo.addSong(testSongsA[2]);
        smallCycleTwo.start.previous = smallCycleTwo.end;
        assertTrue(smallCycleTwo.hasCycle());

        PlayQueue partWayOne = new PlayQueue();
        partWayOne.addSong(testSongsA[0]);
        partWayOne.addSong(testSongsA[1]);
        partWayOne.addSong(testSongsA[2]);
        partWayOne.addSong(testSongsA[3]);
        partWayOne.start.next.next.next = partWayOne.start;
        assertTrue(partWayOne.hasCycle());
        
        PlayQueue partWayTwo = new PlayQueue();
        partWayTwo.addSong(testSongsA[0]);
        partWayTwo.addSong(testSongsA[1]);
        partWayTwo.addSong(testSongsA[2]);
        partWayTwo.addSong(testSongsA[3]);
        partWayTwo.start.next.previous = partWayTwo.start.next.next;
        assertTrue(partWayTwo.hasCycle());

        PlayQueue invalidButNoCycleOne = new PlayQueue();
        invalidButNoCycleOne.addSong(testSongsA[0]);
        invalidButNoCycleOne.addSong(testSongsA[1]);
        invalidButNoCycleOne.addSong(testSongsA[2]);
        invalidButNoCycleOne.addSong(testSongsA[3]);
        invalidButNoCycleOne.addSong(testSongsA[4]);
        invalidButNoCycleOne.start.next.next.previous = invalidButNoCycleOne.start;
        invalidButNoCycleOne.start.next = invalidButNoCycleOne.start.next.next.next;
        assertFalse(invalidButNoCycleOne.hasCycle());

        PlayQueue invalidButNoCycleTwo = new PlayQueue();
        invalidButNoCycleTwo.addSong(testSongsA[0]);
        invalidButNoCycleTwo.addSong(testSongsA[1]);
        invalidButNoCycleTwo.addSong(testSongsA[2]);
        invalidButNoCycleTwo.addSong(testSongsA[3]);
        invalidButNoCycleTwo.addSong(testSongsA[4]);
        invalidButNoCycleTwo.addSong(testSongsA[5]);
        invalidButNoCycleTwo.addSong(testSongsA[6]);
        invalidButNoCycleTwo.end.previous = invalidButNoCycleTwo.end.previous.previous.previous;
        invalidButNoCycleTwo.start.next = invalidButNoCycleTwo.start.next.next.next;
        invalidButNoCycleTwo.end = invalidButNoCycleTwo.start.next.next;
        assertFalse(invalidButNoCycleTwo.hasCycle());

        PlayQueueTest.mark += 5;
        PlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testHasCycle() passed: 5 marks awarded");
    }

    @Test
    @Order(9)
    void testShuffle() {
        PlayQueueTest.testOutcomes.add("testShuffle() failed: 0 marks awarded");
        PlayQueue empty = new PlayQueue();
        PlayQueue emptyShuffled = empty.shuffledQueue(29, 2);
        assertEquals(0, emptyShuffled.size());
        assertNull(emptyShuffled.start);

        PlayQueue one = new PlayQueue();
        one.addSong(testSongsA[4]);
        PlayQueue oneShuffled = one.shuffledQueue(5, 5);
        assertEquals(1, oneShuffled.size());
        assertEquals(one.start.song, oneShuffled.start.song);

        PlayQueue two = new PlayQueue();
        two.addSong(testSongsA[2]);
        two.addSong(testSongsA[5]);
        PlayQueue twoShuffled = two.shuffledQueue(3, 7);
        assertEquals(2, twoShuffled.size());
        assertEquals(two.start.song, twoShuffled.start.song);
        assertEquals(two.start.next.song, twoShuffled.start.next.song);
        assertEquals(two.end.song, twoShuffled.end.song);

        PlayQueue small = new PlayQueue();
        for (int i = 0; i < testSongsA.length; i++) {
            small.addSong(testSongsA[i]);
        }

        PlayQueue smallShuffled7 = small.shuffledQueue(7, 1);
        assertEquals(small.size(), smallShuffled7.size());
        assertEquals(testSongsA[0], smallShuffled7.start.song);
        assertEquals(testSongsA[1], smallShuffled7.start.next.song);
        assertEquals(testSongsA[2], smallShuffled7.start.next.next.song);
        assertEquals(testSongsA[5], smallShuffled7.start.next.next.next.song);
        assertEquals(testSongsA[3], smallShuffled7.start.next.next.next.next.song);
        assertEquals(testSongsA[4], smallShuffled7.start.next.next.next.next.next.song);
        assertEquals(testSongsA[6], smallShuffled7.start.next.next.next.next.next.next.song);
        assertEquals(testSongsA[7], smallShuffled7.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[8], smallShuffled7.start.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[9], smallShuffled7.start.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[10], smallShuffled7.start.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[10], smallShuffled7.end.song);

        PlayQueue smallShuffled11 = small.shuffledQueue(11, 1);
        assertEquals(small.size(), smallShuffled11.size());
        assertEquals(testSongsA[0], smallShuffled11.start.song);
        assertEquals(testSongsA[1], smallShuffled11.start.next.song);
        assertEquals(testSongsA[2], smallShuffled11.start.next.next.song);
        assertEquals(testSongsA[5], smallShuffled11.start.next.next.next.song);
        assertEquals(testSongsA[4], smallShuffled11.start.next.next.next.next.song);
        assertEquals(testSongsA[6], smallShuffled11.start.next.next.next.next.next.song);
        assertEquals(testSongsA[3], smallShuffled11.start.next.next.next.next.next.next.song);
        assertEquals(testSongsA[7], smallShuffled11.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[8], smallShuffled11.start.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[9], smallShuffled11.start.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[10], smallShuffled11.start.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[10], smallShuffled11.end.song);

        PlayQueue smallShuffled13 = small.shuffledQueue(13, 1);
        assertEquals(small.size(), smallShuffled13.size());
        assertEquals(testSongsA[0], smallShuffled13.start.song);
        assertEquals(testSongsA[1], smallShuffled13.start.next.song);
        assertEquals(testSongsA[2], smallShuffled13.start.next.next.song);
        assertEquals(testSongsA[5], smallShuffled13.start.next.next.next.song);
        assertEquals(testSongsA[3], smallShuffled13.start.next.next.next.next.song);
        assertEquals(testSongsA[4], smallShuffled13.start.next.next.next.next.next.song);
        assertEquals(testSongsA[6], smallShuffled13.start.next.next.next.next.next.next.song);
        assertEquals(testSongsA[7], smallShuffled13.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[8], smallShuffled13.start.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[9], smallShuffled13.start.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[10], smallShuffled13.start.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[10], smallShuffled13.end.song);

        PlayQueue smallShuffled87Seed13 = small.shuffledQueue(87, 13);
        assertEquals(small.size(), smallShuffled87Seed13.size());
        assertEquals(testSongsA[0], smallShuffled87Seed13.start.song);
        assertEquals(testSongsA[2], smallShuffled87Seed13.start.next.song);
        assertEquals(testSongsA[10], smallShuffled87Seed13.start.next.next.song);
        assertEquals(testSongsA[6], smallShuffled87Seed13.start.next.next.next.song);
        assertEquals(testSongsA[8], smallShuffled87Seed13.start.next.next.next.next.song);
        assertEquals(testSongsA[9], smallShuffled87Seed13.start.next.next.next.next.next.song);
        assertEquals(testSongsA[1], smallShuffled87Seed13.start.next.next.next.next.next.next.song);
        assertEquals(testSongsA[3], smallShuffled87Seed13.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[4], smallShuffled87Seed13.start.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[5], smallShuffled87Seed13.start.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[7], smallShuffled87Seed13.start.next.next.next.next.next.next.next.next.next.next.song);

        PlayQueue smallShuffled23Seed29 = small.shuffledQueue(23, 29);
        assertEquals(small.size(), smallShuffled23Seed29.size());
        assertEquals(testSongsA[0], smallShuffled23Seed29.start.song);
        assertEquals(testSongsA[7], smallShuffled23Seed29.start.next.song);
        assertEquals(testSongsA[6], smallShuffled23Seed29.start.next.next.song);
        assertEquals(testSongsA[10], smallShuffled23Seed29.start.next.next.next.song);
        assertEquals(testSongsA[8], smallShuffled23Seed29.start.next.next.next.next.song);
        assertEquals(testSongsA[1], smallShuffled23Seed29.start.next.next.next.next.next.song);
        assertEquals(testSongsA[3], smallShuffled23Seed29.start.next.next.next.next.next.next.song);
        assertEquals(testSongsA[4], smallShuffled23Seed29.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[9], smallShuffled23Seed29.start.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[2], smallShuffled23Seed29.start.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[5], smallShuffled23Seed29.start.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsA[5], smallShuffled23Seed29.end.song);


        PlayQueue medium = new PlayQueue();
        for (int i = 3; i < 21; i++) {
            medium.addSong(testSongsB[i]);
        }

        PlayQueue mediumShuffled43Seed53 = medium.shuffledQueue(43, 53);
        assertEquals(medium.size(), mediumShuffled43Seed53.size());
        assertEquals(testSongsB[3], mediumShuffled43Seed53.start.song);
        assertEquals(testSongsB[20], mediumShuffled43Seed53.start.next.song);
        assertEquals(testSongsB[7], mediumShuffled43Seed53.start.next.next.song);
        assertEquals(testSongsB[4], mediumShuffled43Seed53.start.next.next.next.song);
        assertEquals(testSongsB[19], mediumShuffled43Seed53.start.next.next.next.next.song);
        assertEquals(testSongsB[15], mediumShuffled43Seed53.start.next.next.next.next.next.song);
        assertEquals(testSongsB[5], mediumShuffled43Seed53.start.next.next.next.next.next.next.song);
        assertEquals(testSongsB[16], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[6], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[8], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[9], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[10], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[11], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[12], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[13], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[14], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[17], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[18], mediumShuffled43Seed53.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[18], mediumShuffled43Seed53.end.song);

        PlayQueue large = new PlayQueue();
        for (int i = 0; i < testSongsB.length; i++) {
            large.addSong(testSongsB[i]);
        }

        PlayQueue largeShuffle17 = large.shuffledQueue(17, 1);
        assertEquals(large.size(), largeShuffle17.size());
        assertEquals(testSongsB[0], largeShuffle17.start.song);
        assertEquals(testSongsB[1], largeShuffle17.start.next.song);
        assertEquals(testSongsB[2], largeShuffle17.start.next.next.song);
        assertEquals(testSongsB[5], largeShuffle17.start.next.next.next.song);
        assertEquals(testSongsB[9], largeShuffle17.start.next.next.next.next.song);
        assertEquals(testSongsB[14], largeShuffle17.start.next.next.next.next.next.song);
        assertEquals(testSongsB[10], largeShuffle17.start.next.next.next.next.next.next.song);
        assertEquals(testSongsB[16], largeShuffle17.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[3], largeShuffle17.start.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[4], largeShuffle17.start.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[6], largeShuffle17.start.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[7], largeShuffle17.start.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[8], largeShuffle17.start.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[11], largeShuffle17.start.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[12], largeShuffle17.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[13], largeShuffle17.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[15], largeShuffle17.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[17], largeShuffle17.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[18], largeShuffle17.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[22], largeShuffle17.end.previous.previous.song);
        assertEquals(testSongsB[23], largeShuffle17.end.previous.song);
        assertEquals(testSongsB[24], largeShuffle17.end.song);

        PlayQueue largeShuffled29 = large.shuffledQueue(29, 1);
        assertEquals(large.size(), largeShuffled29.size());
        assertEquals(testSongsB[0], largeShuffled29.start.song);
        assertEquals(testSongsB[1], largeShuffled29.start.next.song);
        assertEquals(testSongsB[2], largeShuffled29.start.next.next.song);
        assertEquals(testSongsB[5], largeShuffled29.start.next.next.next.song);
        assertEquals(testSongsB[3], largeShuffled29.start.next.next.next.next.song);
        assertEquals(testSongsB[4], largeShuffled29.start.next.next.next.next.next.song);
        assertEquals(testSongsB[6], largeShuffled29.start.next.next.next.next.next.next.song);
        assertEquals(testSongsB[7], largeShuffled29.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[8], largeShuffled29.start.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[9], largeShuffled29.start.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[10], largeShuffled29.start.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[11], largeShuffled29.start.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[12], largeShuffled29.start.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[13], largeShuffled29.start.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[14], largeShuffled29.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[21], largeShuffled29.end.previous.previous.previous.song);
        assertEquals(testSongsB[22], largeShuffled29.end.previous.previous.song);
        assertEquals(testSongsB[23], largeShuffled29.end.previous.song);
        assertEquals(testSongsB[24], largeShuffled29.end.song);

        PlayQueue largeShuffled97Seed17 = large.shuffledQueue(97, 17);
        assertEquals(large.size(), largeShuffled97Seed17.size());
        assertEquals(testSongsB[0], largeShuffled97Seed17.start.song);
        assertEquals(testSongsB[17], largeShuffled97Seed17.start.next.song);
        assertEquals(testSongsB[7], largeShuffled97Seed17.start.next.next.song);
        assertEquals(testSongsB[1], largeShuffled97Seed17.start.next.next.next.song);
        assertEquals(testSongsB[2], largeShuffled97Seed17.start.next.next.next.next.song);
        assertEquals(testSongsB[3], largeShuffled97Seed17.start.next.next.next.next.next.song);
        assertEquals(testSongsB[4], largeShuffled97Seed17.start.next.next.next.next.next.next.song);
        assertEquals(testSongsB[5], largeShuffled97Seed17.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[6], largeShuffled97Seed17.start.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[8], largeShuffled97Seed17.start.next.next.next.next.next.next.next.next.next.song);

        PlayQueue largeShuffled83Seed87 = large.shuffledQueue(83, 87);
        assertEquals(large.size(), largeShuffled83Seed87.size());
        assertEquals(testSongsB[0], largeShuffled83Seed87.start.song);
        assertEquals(testSongsB[12], largeShuffled83Seed87.start.next.song);
        assertEquals(testSongsB[19], largeShuffled83Seed87.start.next.next.song);
        assertEquals(testSongsB[10], largeShuffled83Seed87.start.next.next.next.song);
        assertEquals(testSongsB[16], largeShuffled83Seed87.start.next.next.next.next.song);
        assertEquals(testSongsB[21], largeShuffled83Seed87.start.next.next.next.next.next.song);
        assertEquals(testSongsB[24], largeShuffled83Seed87.start.next.next.next.next.next.next.song);
        assertEquals(testSongsB[23], largeShuffled83Seed87.start.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[9], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[1], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[2], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[3], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[4], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[5], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[6], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[7], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[8], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[11], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[13], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[14], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[15], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[17], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[18], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[20], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[22], largeShuffled83Seed87.start.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.song);
        assertEquals(testSongsB[22], largeShuffled83Seed87.end.song);

        PlayQueueTest.mark += 8;
        PlayQueueTest.testOutcomes.set(testOutcomes.size() - 1, "testShuffle() passed: 8 marks awarded");
    }

    @AfterAll
    static void printMark() {
        System.out.println();
        PlayQueueTest.testOutcomes.forEach(outcome -> System.out.println(outcome));
        System.out.println();
        System.out.println(String.format("Untimed tests mark: %d", PlayQueueTest.mark));
    }

}
