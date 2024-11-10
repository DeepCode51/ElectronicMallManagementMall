package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Product implements Serializable{
	private int id;
	private String productno;
	private String productname;
	private String filename;
	private double price;
	private double tprice;
	private String fid;
	private String sid;
	private String content;
	private String delstatus;
	private String issj;
	private String istj;
	private String saver;
	private String productid;
	private String leibie;
	private Integer kc;
	private int jf;
	
	
	private Category fcategory;
	private Category scategory;
	private Inventory inventory;
	private User shop;
	
	
	
	public User getShop() {
		return shop;
	}
	public void setShop(User shop) {
		this.shop = shop;
	}
	public int getJf() {
		return jf;
	}
	public void setJf(int jf) {
		this.jf = jf;
	}
	public String getLeibie() {
		return leibie;
	}
	public void setLeibie(String leibie) {
		this.leibie = leibie;
	}
	public Integer getKc() {
		return kc;
	}
	public void setKc(Integer kc) {
		this.kc = kc;
	}
	private List<Product> xxlist;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductno() {
		return productno;
	}
	public void setProductno(String productno) {
		this.productno = productno;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDelstatus() {
		return delstatus;
	}
	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}
	public String getIssj() {
		return issj;
	}
	public void setIssj(String issj) {
		this.issj = issj;
	}
	public Category getFcategory() {
		return fcategory;
	}
	public void setFcategory(Category fcategory) {
		this.fcategory = fcategory;
	}
	public Category getScategory() {
		return scategory;
	}
	public void setScategory(Category scategory) {
		this.scategory = scategory;
	}
	public String getIstj() {
		return istj;
	}
	public void setIstj(String istj) {
		this.istj = istj;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSaver() {
		return saver;
	}
	public void setSaver(String saver) {
		this.saver = saver;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public List<Product> getXxlist() {
		return xxlist;
	}
	public void setXxlist(List<Product> xxlist) {
		this.xxlist = xxlist;
	}
	public double getTprice() {
		return tprice;
	}
	public void setTprice(double tprice) {
		this.tprice = tprice;
	}
	
	
}
