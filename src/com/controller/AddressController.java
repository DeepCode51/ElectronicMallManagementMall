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
import com.util.Saveobject;

import java.util.*;

@Controller
public class AddressController extends BaseController {
	@Resource
	AddressDAO addressDAO;
	@Resource
	CategoryDAO categoryDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	Saveobject saveobject;
	
	//添加收货地址页面
	@RequestMapping("skipAddress")
	public String skipAddress(HttpServletRequest request){
		String orderid = request.getParameter("orderid");
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		request.setAttribute("orderid", orderid);
		return "addressadd";
	}
	
	//添加收货地址 
	@RequestMapping("addressAdd")
	public String addressAdd(Address address,HttpServletRequest request){
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		String orderid = request.getParameter("orderid");
		address.setMemberid(String.valueOf(member.getId()));
		HashMap map = new HashMap();
		map.put("memberid",member.getId());
		List<Address> list = addressDAO.selectAll(map);
		if(list.size()==0){
			address.setIsmr("yes");
		}else{
			address.setIsmr("no");
		}
		address.setDelstatus("0");
		addressDAO.add(address);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "redirect:skipFukuan.do?id="+orderid;
	}
	
	//前台查询收货地址
	@RequestMapping("addressShow")
	public String addressShow(int id,HttpServletRequest request){
		Address address =  addressDAO.findById(id);
		String orderid = request.getParameter("orderid");
		request.setAttribute("address", address);
		request.setAttribute("orderid", orderid);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "addressedit";
	}
	
	//编辑收货地址
	@RequestMapping("addressEdit")
	public String addressEdit(Address address,HttpServletRequest request){
		String orderid = request.getParameter("orderid");
		addressDAO.update(address);
		return "redirect:skipFukuan.do?id="+orderid;
	}
	
	//删除收货地址
	@RequestMapping("addressDel")
	public String addressDel(int id, HttpServletRequest request){
		String orderid = request.getParameter("orderid");
		Address address =  addressDAO.findById(id);
		address.setDelstatus("1");
		addressDAO.update(address);
		return "redirect:skipFukuan.do?id="+orderid;
	}
	
	//更新默认
	@RequestMapping("updateIsmr")
	public String updateIsmr(int id, HttpServletRequest request){
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		String orderid = request.getParameter("orderid");
		HashMap map = new HashMap();
		map.put("memberid", member.getId());
		List<Address> list = addressDAO.selectAll(map);
		for(Address address:list){
			address.setIsmr("no");
			addressDAO.update(address);
		}
		Address addr =  addressDAO.findById(id);
		addr.setIsmr("yes");
		addressDAO.update(addr);
		return "redirect:skipFukuan.do?id="+orderid;
	}
	
	
	
	
	

}
