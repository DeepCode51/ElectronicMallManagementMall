package com.entity;

import java.util.*;

public class Message {
	private int id;
	private int memberid;
	private String content;
	private String savetime;
	private String replycontent;
	private String replysavetime;
	private Member member;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
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
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public String getReplysavetime() {
		return replysavetime;
	}
	public void setReplysavetime(String replysavetime) {
		this.replysavetime = replysavetime;
	}
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "Message [content=" + content + ", id=" + id + ", memberid="
				+ memberid + ", replycontent=" + replycontent
				+ ", replysavetime=" + replysavetime + ", savetime=" + savetime
				+ "]";
	}
	
	
	
}
