package questions;

import doNotModify.Song;
import doNotModify.SongNode;

public class PlayQueue {
    public SongNode start; // DO NOT MODIFY
    public SongNode end;   // DO NOT MODIFY
    public int qLength;

    /**
     * Adds a Song to the end of the PlayQueue.
     * <p>
     * Note: This must be completed before moving onto any other method.
     * @param song - The Song to add
     */
    public void addSong(Song song) {
        SongNode newNode = new SongNode(song, null, null);
        if (start == null) {
            start = newNode;
        } else {
            end.next = newNode;
            newNode.previous = end;
        }
        end = newNode;
        qLength++;
    }

    /**
     * Remove the first SongNode with the parameter Song from the PlayQueue.
     * <p>
     * Return true if a SongNode was removed, false otherwise.
     * @param song
     * @return - true if a SongNode was removed, false otherwise.
     */
    public boolean removeSong(Song song) {
        if(start == null) {
        	return false;
        }
        int i = 0;
        SongNode temp = start;
        while(i != this.size()) {
        	if(temp.song.equals(song)) {
        		break;
        	}
        	i++;
        	temp = temp.next;
        }
        if(i == this.size()) {
        	return false;
        }
        removeSong(i);
        return true;
    }

    /**
     * Removes the SongNode at the specified index from the PlayQueue, returning
     * the Song that was removed.
     * <p>
     * Return null if `index` is invalid.
     * @param index
     */
    public Song removeSong(int index) {
    	if(index >= this.size() || index < 0) {
    		return null;
    	}
    	if(start == null) {
        	return null;
        }
    	SongNode temp = start;
    	for(int i = 0; i < index; i++) {
    		temp = temp.next;
    	}
    	if(index == 0) {
    		start = start.next;
    	}
    	if(index == this.size() - 1) {
    		end = end.previous;
    	}
    	SongNode nextNode = temp.next;
    	SongNode prevNode = temp.previous;
    	if(index != this.size() - 1) {
    		nextNode.previous = prevNode;
    	}
    	if(index != 0) {
    		prevNode.next = nextNode;
    	}
    	return temp.song;
    }
    
    /**
     * Return the size (number of SongNodes) in the PlayQueue.
     * @return the size of the PlayQueue
     */
    public int size() {
    	int result = 0;
        SongNode temp = start;
    	while(temp != null) {
    		result++;
    		temp = temp.next;
    	}
    	return result;
    }

    /**
     * Reverse the calling object PlayQueues Song ordering.
     */
    public void reverseQueue() {
        // TODO: To be completed
    }

    /**
     * Move the SongNode from the `fromIndex` index the specified `amount`.
     * 
     * Let the queue be:
     *       start              end
     *         |                 |
     * null <- a <-> b <-> c <-> d -> null
     * 
     * Let fromIndex be 1.
     * The expected queue should be as follows for:
     * amount := 0
     *       start              end
     *         |                 |
     * null <- a <-> b <-> c <-> d -> null
     * 
     * amount := 1
     *       start              end
     *         |                 |
     * null <- a <-> c <-> b <-> d -> null
     * 
     * amount := -1
     *       start              end
     *         |                 |
     * null <- b <-> a <-> c <-> d -> null
     * 
     * amount := 2
     *       start              end
     *         |                 |
     * null <- a <-> c <-> d <-> b -> null
     * <p>
     * Do nothing if either `fromIndex` is invalid, or `amount` is invalid for
     * the given `fromIndex`.
     * <p>
     * Do not create any new SongNode instances.
     * @param fromIndex
     * @param amount
     */
    public void moveSong(int fromIndex, int amount) {
        // TODO: To be completed
    }

    /**
     * Swap the SongNodes at parameter indices.
     * Do nothing if either parameters are invalid.
     * @param firstIndex
     * @param secondIndex
     */
    public void swapSongs(int firstIndex, int secondIndex) {
        // TODO: To be completed
    }

    /**
     * Check the PlayQueue for cycles.
     * <p>
     * There is at most one cycle in the PlayQueue. This may be bi-directional.
     * @return - true if a cycle is detected, false otherwise.
     */
    public boolean hasCycle() {
        return false;  // TODO: To be completed
    }

    /**
     * Create and return a (semi) randomly shuffled PlayQueue from the calling object.
     * <p>
     * A shuffled PlayQueue begins with the same Song as the calling object.
     * For all other Songs in the resulting PlayQueue the following formula is used:
     * <p>
     * (x^2 + 1) % p * s % n
     * <p>
     * where x is the index previously taken from,
     * <p>
     * where p is a prime number,
     * <p>
     * where s is seed number.
     * <p>
     * and n is the length of the PlayQueue
     * <p>
     * You must ensure that you do not go out of bounds, and that when the provided formula
     * creates a cycle that it is no longer used. Then the Songs in all uncovered SongNodes
     * are added in their original order to the resulting PlayQueue.
     * 
     * @param p - prime number
     * @param s - seed number
     * @return the shuffled queue
     */
    public PlayQueue shuffledQueue(int p, int s) {
        return null;  // TODO: To be completed
    }


    @Override
    public String toString() {
        if (start == null) {
            return "null";
        }
        String forward = " forwards :         ";
        SongNode temp = start;
        while (temp.next != null) {
            forward += temp.song.title + " -> ";
            temp = temp.next;
        }
        forward += temp.song.title + " -> null";

        temp = end;
        String backward = "";
        while (temp.previous != null) {
            backward = " <- " + temp.song.title + backward;
            temp = temp.previous;
        }
        backward = "backwards : null <- " + temp.song.title + backward;
        return forward + "\n" + backward;
    }
}
