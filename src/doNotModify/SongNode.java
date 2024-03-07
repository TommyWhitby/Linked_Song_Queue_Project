package doNotModify;

public class SongNode {  // DO NOT MODIFY THIS CLASS
    public Song song;
    public SongNode next;
    public SongNode previous;

    public SongNode(Song s, SongNode n, SongNode p) {
        song = s;
        next = n;
        previous = p;
    }
    
}
