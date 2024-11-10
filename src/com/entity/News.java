package com.entity;

import java.util.*;

public class News {
	private int id;
	private String title;
	private String filename;
	private String content;
	private String savetime;
	private String ms;
	
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSavetime() {
		return savetime;
	}
	public void setSavetime(String savetime) {
		this.savetime = savetime;
	}
	@Override
	public String toString() {
		return "News [content=" + content + ", filename=" + filename + ", id="
				+ id + ", savetime=" + savetime + ", title=" + title + "]";
	}
	
	
	
	
	
}
