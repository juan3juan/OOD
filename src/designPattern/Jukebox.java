package designPattern;

import java.util.Queue;
import java.util.Set;

public class Jukebox {
	private CDPlayer cdPlayer;
	private User user;
	private Set<CD> cdCollection;
	private SongSelector ts;
	
	public Jukebox(CDPlayer cdPlayer, User user, Set<CD> cdCollection, SongSelector ts) {
		this.cdPlayer = cdPlayer;
		this.cdCollection = cdCollection;
		this.user = user;
		this.ts = ts;
	}
	
	public Song getCurrentSong() {
		return ts.getCurrentSong();
	}

	public void setUser(User u) {
		this.user = u;
	}
}

class CDPlayer{
	private Playlist p;
	private CD c;
	
	public CDPlayer(CD c, Playlist p) { 
		this.c = c;
		this.p = p;
	}
	public CDPlayer(Playlist p) { this.p = p; }
	public CDPlayer(CD c) { this.c = c; }
	
	public Playlist getPlaylist() { return p;}
	public void setPlaylist(Playlist p) { this.p = p; }
	public CD getCD() { return c; }
	public void setCD(CD c) { this.c = c; }
}

class Playlist{
	private Song song;
	private Queue<Song> queue;
	public Playlist(Song song, Queue<Song>queue) {
		this.song = song;
		this.queue = queue;
	}
	public Song getNextToPlay() {
		return queue.peek();
	}
	public void queueUpSong(Song s) {
		queue.add(s);
	}
}

class SongSelector{
	public Song s;
	public Song getCurrentSong(){
		return s;
	}
}
// members
class CD{
	
}

class Song{
	
}

class User{
	
}








