package com.entity;

import java.io.Serializable;
import java.util.*;

public class Ordermsg implements Serializable {
	private int id;
	private String ddno;
	private String memberid;
	private double total;
	private String fkstatus;
	private String addrid;
	private String savetime;
	private String delstatus;
	private String zffs;
	private Product product;
	private String goodstype;
	private Ordermsgdetails ordermsgdetails;
	private List<Ordermsgdetails> ordermsglist;

	private Member member;
	private Address address;

	

	
	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public String getAddrid() {
		return addrid;
	}


	public void setAddrid(String addrid) {
		this.addrid = addrid;
	}


	public Ordermsgdetails getOrdermsgdetails() {
		return ordermsgdetails;
	}


	public void setOrdermsgdetails(Ordermsgdetails ordermsgdetails) {
		this.ordermsgdetails = ordermsgdetails;
	}


	public List<Ordermsgdetails> getOrdermsglist() {
		return ordermsglist;
	}


	public void setOrdermsglist(List<Ordermsgdetails> ordermsglist) {
		this.ordermsglist = ordermsglist;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDdno() {
		return ddno;
	}


	public void setDdno(String ddno) {
		this.ddno = ddno;
	}


	public String getMemberid() {
		return memberid;
	}


	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}




	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public String getFkstatus() {
		return fkstatus;
	}


	public void setFkstatus(String fkstatus) {
		this.fkstatus = fkstatus;
	}


	

	public String getSavetime() {
		return savetime;
	}


	public void setSavetime(String savetime) {
		this.savetime = savetime;
	}


	public String getDelstatus() {
		return delstatus;
	}


	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}


	public String getZffs() {
		return zffs;
	}


	public void setZffs(String zffs) {
		this.zffs = zffs;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public String getGoodstype() {
		return goodstype;
	}


	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}




	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}
	

	
	

	
	

	

}
