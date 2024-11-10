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
public class NewsController extends BaseController {
	@Resource
	NewsDAO newsDAO;
	@Resource
	CategoryDAO categoryDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	ProductDAO productDAO;
	@Resource
	Saveobject saveobject;
	@Resource
	OrdermsgDAO ordermsgDAO;
	
	
	
	
	//后台查询活动列表
	@RequestMapping("/admin/newsList")
	public String newsList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		HashMap map = new HashMap();
		map.put("key", key);
		PageHelper.startPage(pageNum, 10);
		List<News> list = newsDAO.selectAll(map);
		PageInfo<News> pageInfo =  new PageInfo<News>(list);
		request.setAttribute("key",key);
		request.setAttribute("pageInfo",pageInfo);
		return "admin/newslist";
	}
	
	
	
	//添加活动 
	@RequestMapping("/admin/newsAdd")
	public String newsAdd(News news,HttpServletRequest request){
		newsDAO.add(news);
		return "redirect:newsList.do";
	}
	
	//后台查询活动
	@RequestMapping("/admin/showNews")
	public String shownews(int id,HttpServletRequest request){
		News news =  newsDAO.findById(id);
		request.setAttribute("news", news);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		
		return "admin/newsedit";
	}
	
	
	//前台查询活动
	@RequestMapping("newsShow")
	public String newsShow(int id,HttpServletRequest request){
		News news =  newsDAO.findById(id);
		request.setAttribute("news", news);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "newsx";
	}
	
	//编辑活动
	@RequestMapping("/admin/newsEdit")
	public String newsEdit(News news,HttpServletRequest request){
		newsDAO.update(news);
		request.setAttribute("news", news);
		return "redirect:newsList.do";
	}
	
	
	//删除活动
	@RequestMapping("admin/newsDel")
	public String newsDel(int id, HttpServletRequest request,HttpServletResponse response){
			newsDAO.delete(id);
		return "redirect:newsList.do";
	}
	
	
	//前台台查询活动列表
	@RequestMapping("newsLb")
	public String newsMsg(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		HashMap map = new HashMap();
		PageHelper.startPage(pageNum, 10);
		List<News> list = newsDAO.selectAll(map);
		for(News news:list){
			String ms = "";
			String str = Info.delHTMLTag(news.getContent().toString());
			if(str.length()>300){
				ms = str.substring(0,160)+"...";
			}else{
				ms = str;
			}
			news.setMs(ms);
		}
		PageInfo<News> pageInfo =  new PageInfo<News>(list);
		
		request.setAttribute("pageInfo", pageInfo);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		
		
		return "newslb";
	}
	
	
	

}
