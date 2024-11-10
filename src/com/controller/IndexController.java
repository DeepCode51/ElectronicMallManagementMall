package com.controller;

import javax.annotation.Resource;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.*;
import com.entity.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.DrawLotteryUtil;
import com.util.Info;
import com.util.Saveobject;

import java.util.*;

@Controller
public class IndexController extends BaseController {
	@Resource
	ProductDAO productDAO;
	@Resource
	CategoryDAO categoryDAO;
	@Resource
	PictureDAO pictureDAO;
	@Resource
	NewsDAO newsDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	OrdermsgDAO ordermsgDAO;
	@Resource
	CartDAO cartDAO;
	@Resource
	Saveobject saveobject;
	@Resource
	TicketDAO ticketDAO;
	//首页
	@RequestMapping("index")
	public  String index(HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		String error = request.getParameter("error")==null?"":request.getParameter("error");
		String jx = request.getParameter("jx")==null?"":request.getParameter("jx");
		List<Picture> pclist = pictureDAO.selectAll();
		request.setAttribute("pclist", pclist);
		saveobject.getCategoryObject(request);
		
		//首页分类商品
		HashMap map = new HashMap();
		List<Category> fcategorylist = (List<Category>)request.getAttribute("ctlist");
		for(Category category:fcategorylist){
		map.put("fatherid", category.getId());
		List<Category> scategorylist = categoryDAO.findAll(map);
		category.setScategorylist(scategorylist);
		map.put("fid", category.getId());
		map.put("issj", "yes");
		map.put("istj", "yes");
		List<Product> ptlist = productDAO.selectAll(map);
		category.setPtlist(ptlist);
		
		//排行
		List<Ordermsgdetails> pphlist = saveobject.getPh(String.valueOf(category.getId()), request);
		category.setPhlist(pphlist);
		
		}
		request.setAttribute("fcategorylist", fcategorylist);
		HashMap  mmm = new HashMap();
		mmm.put("issj", "yes");
		mmm.put("istj", "yes");
		List<Product> phlist = productDAO.selectAll(mmm);//最新商品
		request.setAttribute("phlist", phlist);
		
		//购物车
		saveobject.getCart(request);
		
		//最新活动
		List<News> newslist = newsDAO.selectAll(map);
		request.setAttribute("newslist", newslist);
		
		if(!jx.equals("")){
			String suc = "恭喜你获得了&nbsp;"+jx+"个积分";
			request.setAttribute("suc", suc);
		}
		if(error.equals("error")){
			request.setAttribute("suc", "生日当天才能领取");
		}
		if(error.equals("yl")){
			request.setAttribute("suc", "只能领取一次,明年再来");
		}
		return "index";
	}
	
	//后台index
	@RequestMapping("admin/index")
	public  String adminIndex(HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		HashMap map = new HashMap();
		List<Member> mblist = memberDAO.selectAll(map);
		List<News> newslist = newsDAO.selectAll(map);
		List<Ordermsg> ddlist = ordermsgDAO.selectAll(map);
		double total =0.0;
		
		//其它
		request.setAttribute("ddlist", ddlist);
		request.setAttribute("total", total);
		request.setAttribute("newslist", newslist);
		request.setAttribute("mblist", mblist);
		return "admin/index";
	}
	
	//搜索商品
	@RequestMapping("Search")
	public String Search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request){
		String key = request.getParameter("key");
		String ph = request.getParameter("ph");
		//显示商品列表
   	    HashMap map = new HashMap();
   	    map.put("key", key);
	    map.put("issj", "yes");
	    map.put("ph", ph);
	    List<Product> nlist = productDAO.selectAll(map);
	    PageHelper.startPage(pageNum,8);
		List<Product> list = productDAO.selectAll(map);
		PageInfo<Product> pageInfo =  new PageInfo<Product>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("nlist", nlist);
		request.setAttribute("key", key);
		request.setAttribute("ph", ph);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "search";
	}
	
	//注册页面
	@RequestMapping("skipReg")
	public  String skipReg(HttpServletRequest request) {
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		saveobject.getCategoryObject(request);
		if(!suc.equals("")){
			request.setAttribute("suc", "注册成功");
		}
		return "reg";
	}
	
	//登录页面
	@RequestMapping("skipLogin")
	public  String skipLogin(HttpServletRequest request) {
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		saveobject.getCategoryObject(request);
		if(!suc.equals("")){
			request.setAttribute("suc", "用户名或密码错误");
		}
		return "login";
	}
	
	//抽奖
	@RequestMapping("choujiang")
	public  void choujiang(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out;
		try {
			response.setCharacterEncoding("utf-8");//第一句，设置服务器端编码
			response.setContentType("text/html;charset=utf-8");//第二句，设置浏览器端解码
			out = response.getWriter();
			Member member = (Member)request.getSession().getAttribute("sessionmember");
			Jiangxiang one = new Jiangxiang();
			one.setName("10");
			one.setProb(0.9);
			
			Jiangxiang two = new Jiangxiang();
			two.setName("50");
			two.setProb(0.5);
			
			Jiangxiang three = new Jiangxiang();
			three.setName("100");
			three.setProb(0.3);
			
			Jiangxiang four = new Jiangxiang();
			four.setName("500");
			four.setProb(0.1);
			
			List<Jiangxiang> list = new ArrayList<Jiangxiang>();
	        list.add(one);
	        list.add(two);
	        list.add(three);
	        list.add(four);
			
	        int i = new DrawLotteryUtil().drawGift(list);
	        
	        Jiangxiang jx = list.get(i);
	        Ticket ticket = new Ticket();
	        ticket.setMemberid(String.valueOf(member.getId()));
	        ticket.setMoney(Integer.parseInt(jx.getName()));
	        ticketDAO.add(ticket);
	        String flag = jx.getName();
	        out.print(flag);
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	
	
	

}
