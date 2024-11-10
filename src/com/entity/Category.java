package com.entity;

import java.io.Serializable;
import java.util.*;

public class Category implements Serializable{
	private int id;
	private String name;
	private String fatherid;
	private String delstatus;
	
	private List<Category> scategorylist;
	private List<Product> ptlist;
	private List<Ordermsgdetails> phlist;
	
	
	
	public List<Ordermsgdetails> getPhlist() {
		return phlist;
	}
	public void setPhlist(List<Ordermsgdetails> phlist) {
		this.phlist = phlist;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDelstatus() {
		return delstatus;
	}
	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}
	
	
	
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public List<Category> getScategorylist() {
		return scategorylist;
	}
	public void setScategorylist(List<Category> scategorylist) {
		this.scategorylist = scategorylist;
	}
	public List<Product> getPtlist() {
		return ptlist;
	}
	public void setPtlist(List<Product> ptlist) {
		this.ptlist = ptlist;
	}
	
	
	
}
