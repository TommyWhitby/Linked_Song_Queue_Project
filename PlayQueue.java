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
    
    /* Creates a new SongNode with the song passed into the function and a next and previous == null
     * if start is empty, newNode becomes Start
     * otherwise add newNode to the end of the queue and update end to be newNode
     * increase queue length */

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
        while(i != qLength) {
        	if(temp.song.equals(song)) {
        		break;
        	}
        	i++;
        	temp = temp.next;
        }
        if(i == qLength) {
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
    	if(index >= qLength || index < 0) {
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
    	if(index == qLength - 1) {
    		end = end.previous;
    	}
    	SongNode nextNode = temp.next;
    	SongNode prevNode = temp.previous;
    	if(index != qLength - 1) {
    		nextNode.previous = prevNode;
    	}
    	if(index != 0) {
    		prevNode.next = nextNode;
    	}
    	qLength--;
    	return temp.song;
    }
    
    /**
     * Return the size (number of SongNodes) in the PlayQueue.
     * @return the size of the PlayQueue
     */
    public int size() {
    	if(start == null) {
    		return 0;
    	}
    	return qLength;
    }

    /**
     * Reverse the calling object PlayQueues Song ordering.
     */
    public void reverseQueue() {
    	if (start == null || start == end) {
            return;
        }
        SongNode current = end;
        while(current != null) {
            SongNode temp = current.previous;
            current.previous = current.next;
            current.next = temp;
            current = temp;
        }
        SongNode temp = start;
        start = end;
        end = temp;
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
        if(fromIndex < 0 || fromIndex >= qLength) {
        	return;
        }
        
        SongNode temp = start;
        for(int i = 0; i < fromIndex; i++) {
        	temp = temp.next;
        }
        
        //This for handles the negative amount (it will move temp backwards)
        for(int i = 0; i > amount; i--) {
        	if(temp.previous == null) {
        		break;
        	}
        	SongNode prev = temp.previous;
        	//Change links to move temp in front of its previous node
        	if(temp.next != null) {
        		temp.next.previous = prev;
        	} else {
        		end = prev; //Update the end if temp is the new last node
        	}
        	prev.next = temp.next;
        	temp.next = prev;
        	temp.previous = prev.previous;
        	prev.previous = temp;
        	//Update start if temp was moved in front of it
        	if(temp.previous == null) {
        		start = temp;
        	} else {
        		temp.previous.next = temp;
        	}
        }
        //This for handles amount when it is positive (it will move forwards)
        for(int i = 0; i < amount; i++) {
            if (temp.next == null) {
                break; //Can't move further forwards
            }
            SongNode next = temp.next;
            //Change the links to move temp after its next node
            if(temp.previous != null) {
                temp.previous.next = next;
            } else {
                start = next; //Update start if temp was moved in front of it
            }
            next.previous = temp.previous;
            temp.previous = next;
            temp.next = next.next;
            next.next = temp;
            //Update end if temp was moved after it
            if(temp.next == null) {
                end = temp;
            } else {
                temp.next.previous = temp;
            }
        }
    }

    /**
     * Swap the SongNodes at parameter indices.
     * Do nothing if either parameters are invalid.
     * @param firstIndex
     * @param secondIndex
     */
    public void swapSongs(int firstIndex, int secondIndex) {
    	if(start == null || firstIndex < 0 || firstIndex >= qLength || secondIndex < 0 || secondIndex >= qLength || firstIndex == secondIndex) {
            return;
        }
        SongNode temp = start;
        for(int i = 0; i < firstIndex; i++) {
            temp = temp.next;
        }
        SongNode firstSong = temp;
        SongNode temp2 = start;
        for(int i = 0; i < secondIndex; i++) {
            temp2 = temp2.next;
        }
        SongNode secondSong = temp2;
        SongNode tempPrev = firstSong.previous;
        SongNode tempNext = firstSong.next;
        
        firstSong.previous = secondSong.previous;
        firstSong.next = secondSong.next;
        secondSong.previous = tempPrev;
        secondSong.next = tempNext;
        
        if(firstSong.previous != null) {
        	firstSong.previous.next = firstSong;
        } else {
        	start = firstSong;
        }
        
        if(firstSong.next != null) {
        	firstSong.next.previous = firstSong;
        } else {
        	end = firstSong;
        }
        
        if(secondSong.previous != null) {
        	secondSong.previous.next = secondSong;
        } else {
        	start = secondSong;
        }
        
        if(secondSong.next != null) {
        	secondSong.next.previous = secondSong;
        } else {
        	end = secondSong;
        }
    }

    /**
     * Check the PlayQueue for cycles.
     * <p>
     * There is at most one cycle in the PlayQueue. This may be bi-directional.
     * @return - true if a cycle is detected, false otherwise.
     */
    
    public boolean hasCycle() {
    	if(start == null || end == null) {
    		return false;
    	}
    	
    	SongNode slow = start;
    	SongNode fast = start.next;
    	int forwardLength = 0;
    	
    	SongNode slowB = end;
    	SongNode fastB = end.previous;
    	int backwardLength = 0;
    	
    	while(fast != null && fast.next != null) {
    		if(forwardLength > qLength) {
    			return true;
    		}
    		if(slow == fast || slow == fast.next) {
    			return true;
    		}
    		slow = slow.next;
    		fast = fast.next.next;
    		forwardLength++;
    	}
    	while(fastB != null && fastB.previous != null) {
    		if(backwardLength > qLength) {
    			return true;
    		}
    		if(slowB == fastB || slowB == fastB.previous) {
    			return true;
    		}
    		slowB = slowB.previous;
    		fastB = fastB.previous.previous;
    		backwardLength++;
    	}
    	return false;
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
    	if(start == null) {
    		return new PlayQueue();
    	}
    	
    	PlayQueue shuffledQueue = new PlayQueue();
    	
    	Song[] ogQueue = new Song[qLength];
    	SongNode temp = start;
    	for(int i = 0; i < qLength; i++) {
    		ogQueue[i] = temp.song;
    		temp = temp.next;
    	}
    	
    	for(int i = 1; i < qLength; i++) {
    		System.out.println(ogQueue[i].title + " ------------------[" + i + "]");
    	}
    	
    	int index = 0;
    	for(int i = 1; i < qLength; i++) {
    		int shuffleIndex = ((index * index) + 1) % p * s % qLength;
    		Song tempSong = ogQueue[i];
    		ogQueue[i] = ogQueue[shuffleIndex];
    		ogQueue[shuffleIndex] = tempSong;
    		index = shuffleIndex;
    	}
    	return addShuffled(shuffledQueue, ogQueue);
    }
    
    public PlayQueue addShuffled(PlayQueue shuffledQueue, Song[] arr) {
    	shuffledQueue.addSong(arr[0]);
    	for(int i = 1; i < qLength; i++) {
    		shuffledQueue.addSong(arr[i]);
    		System.out.println(arr[i].title + " addShuffled[" + i + "]");
    	}
    	return shuffledQueue;
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
