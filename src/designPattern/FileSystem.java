package designPattern;

import java.util.ArrayList;

public abstract class FileSystem{
	protected Directory parent;
	protected long created;
	protected long lastUpdated;
	protected long lastAccessed;
	protected String name;
	
	public FileSystem(String n, Directory p) {
		name = n;
		parent = p;
		created= System.currentTimeMillis();
		lastUpdated = System.currentTimeMillis();
		lastAccessed = System.currentTimeMillis();  
	}
	
	public boolean delete() {
		if(parent==null)
			return false;
		return parent.deleteEntry(this);
	}
	
	public abstract int size();
	
	public String getFullPath() {
		if(parent==null)
			return name;
		else
			return parent.getFullPath()+"/"+name;
	}
	
	public long getcreationTime() { return created; }
	public long getLastUpdatedTime() { return lastUpdated;}
	public long getLastAccessedTime() { return lastAccessed; }
	public void setName(String n) {name = n;}
	public String getName() { return name; }
}


 class File extends FileSystem{
	 private String content;
	 private int size;
	 
	 public File(String n, Directory p, int sz) {
		 super(n,p);
		 size = sz;
	 }
	 
	 public int size() { return size; }
	 public String getContents() { return content;}
	 public void setContents(String c) {content = c;}
	 
}
 
 class Directory extends FileSystem{
	 protected ArrayList<FileSystem> contents;
	 
	 public Directory(String n, Directory p) {
		 super(n, p);
		 contents = new ArrayList<>();
	 }
	 
	 public int size() {
		 int size = 0;
		 for(FileSystem f: contents) {
			 size += f.size();
		 }
		 return size;
	 }
	 
	 public int numOfFiles() {
		 int cnt = 0;
		 for(FileSystem f: contents) {
			 if(f instanceof Directory) {
				 cnt++;
				 Directory d = (Directory) f;
				 cnt += d.numOfFiles();
			 }
			 else if(f instanceof File) {
				 cnt++;
			 }
		 }
		 return cnt;
	 }
	 
	 public boolean deleteEntry(FileSystem entry) {
		 return contents.remove(entry);
	 }
	 
	 public void addEntry(FileSystem entry) {
		 contents.add(entry);
	 }
	 
	 public ArrayList<FileSystem> getContents(){
		 return contents;
	 }
 }
 
 
 
 
 
 
 
 
 