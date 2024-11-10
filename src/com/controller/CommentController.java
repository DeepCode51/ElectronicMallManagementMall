package com.controller;

import javax.annotation.Resource;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.*;
import com.entity.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.Info;

import java.util.*;

@Controller
public class CommentController extends BaseController {
	@Resource
	CommentDAO commentDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	OrdermsgDAO ordermsgDAO;
	@Resource
	OrdermsgdetailsDAO ordermsgdetailsDAO;
	
	
	
	//添加评论
	@RequestMapping("commentAdd")
	public String commentAdd(Comment comment,HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		if(member!=null){
		HashMap map = new HashMap();
		map.put("memberid", String.valueOf(member.getId()));
		map.put("status","交易完成");
		map.put("productid", comment.getProductid());
		List<Ordermsgdetails> list = ordermsgdetailsDAO.selectAll(map);
		if(list.size()!=0){
			comment.setSavetime(Info.getDateStr());
			comment.setMemberid(String.valueOf(member.getId()));
			commentDAO.add(comment);
			return "redirect:productDetails.do?id="+comment.getProductid()+"&suc=suc";
		}else{
			return "redirect:productDetails.do?id="+comment.getProductid()+"&suc=err";
		}
		}else{
		return "redirect:productDetails.do?id="+comment.getProductid()+"&suc=error";	
		}
	}
	
	//后台查询评论列表
	@RequestMapping("/admin/commentList")
	public String commentList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, HttpServletRequest request) {
   	    String productid = request.getParameter("productid");
   	    String shop = request.getParameter("shop");
		HashMap map = new HashMap();
   	    PageHelper.startPage(pageNum, 10);
   	    map.put("productid", productid);
		List<Comment> list = commentDAO.selectAll(map);
		for(Comment comment:list){
			Member member = memberDAO.findById(Integer.parseInt(comment.getMemberid()));
			comment.setMember(member);
		}
		PageInfo<Comment> pageInfo =  new PageInfo<Comment>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("productid", productid);
		return "admin/commentlist";
	}
	
	
	
	//后台删除评论
	@RequestMapping("/admin/commentDel")
	public String commentDel(int id,HttpServletRequest request) {
		String productid = request.getParameter("productid");
		commentDAO.delete(id);
		return "redirect:commentList.do?productid="+productid;
	}
	
	//评论回复
	@RequestMapping("/admin/commentHf")
	public String commentHf(HttpServletRequest request) {
		String id = request.getParameter("id");
		String hfcontent = request.getParameter("hfcontent");
		Comment comment = commentDAO.findById(Integer.parseInt(id));
		comment.setHfcontent(hfcontent);
		commentDAO.update(comment);
		return "redirect:commentList.do?productid="+comment.getProductid();
	}
	
	

}
