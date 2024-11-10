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
import com.util.Saveobject;

import java.util.*;

@Controller
public class MessageController extends BaseController {
	@Resource
	MessageDAO messageDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	ProductDAO productDAO;
	@Resource
	CategoryDAO categoryDAO;
	@Resource
	Saveobject saveobject;
	
	//前台留言列表
	@RequestMapping("message_List")
	public String message_List(HttpServletRequest request){
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		HashMap map = new HashMap();
		List<Message> list = messageDAO.selectAll(map);
		for(int i=0;i<list.size();i++){
			Member m = memberDAO.findById(list.get(i).getMemberid());
			list.get(i).setMember(m);
		}
		if(suc.equals("error")){
			request.setAttribute("suc", "请先登录");
		}
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		request.setAttribute("list", list);
		return "messagelist";
	}
	
	
	//前台留言添加
	@RequestMapping("messageAdd")
	public String messageAdd(HttpServletRequest request){
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		String content = request.getParameter("content");
		if(member!=null){
			Message message = new Message();
			message.setContent(content);
			message.setMemberid(member.getId());
			message.setSavetime(Info.getDateStr());
			messageDAO.add(message);
			return "redirect:message_List.do";
		}else{
			return "redirect:message_List.do?suc=error";
		}
	}
	
	//后台留言列表
	@RequestMapping("/admin/messageList")
	public String messageList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		HashMap map = new HashMap();
		map.put("key", key);
		List<Message> list = messageDAO.selectAll(map);
		for(int i=0;i<list.size();i++){
			Member m = memberDAO.findById(list.get(i).getMemberid());
			list.get(i).setMember(m);
		}
		PageInfo<Message> pageInfo =  new PageInfo<Message>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("key", key);
		return "admin/messagelist";
	}
	
	//后台搜索留言
	@RequestMapping("/admin/searchMessage")
	public String searchMessage(String key, HttpServletRequest request) {
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Message> list = messageDAO.searchMessage(key);
		for(int i=0;i<list.size();i++){
			Member m = memberDAO.findById(list.get(i).getMemberid());
			list.get(i).setMember(m);
		}
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/messagesearchlist";
	}
	
	//留言回复
	@RequestMapping("/admin/updateMessage")
	public String updateMessage(Message message,HttpServletRequest request) {
		messageDAO.update(message);
		return "redirect:messageList.do";
	}
	
	//删除留言
	@RequestMapping("/admin/messageDelAll")
	public String updateMessage(HttpServletRequest request) {
		String vals = request.getParameter("vals");
		String[] val = vals.split(",");
		for(int i=0;i<val.length;i++){
			messageDAO.delete(Integer.parseInt(val[i]));
		}
		return "redirect:messageList.do";
	}
	
	//删除留言
	@RequestMapping("/admin/messageDel")
	public String messageDel(int id,HttpServletRequest request) {
		messageDAO.delete(id);
		return "redirect:messageList.do";
	}

}
