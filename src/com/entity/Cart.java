package com.entity;

import java.io.Serializable;
import java.util.*;

public class Cart implements Serializable{
	private int id;
	private int productid;
	private int memberid;
	private int num;
	private String subtotal;
	private Member member;
	private Product Product;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Product getProduct() {
		return Product;
	}
	public void setProduct(Product product) {
		Product = product;
	}
	
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "Cart [Product=" + Product + ", id=" + id + ", member=" + member
				+ ", memberid=" + memberid + ", num=" + num + ", productid="
				+ productid + "]";
	}
	
}
