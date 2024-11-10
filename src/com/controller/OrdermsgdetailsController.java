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
public class OrdermsgdetailsController extends BaseController {
	@Resource
	OrdermsgdetailsDAO ordermsgdetailsDAO;
	@Resource
	ProductDAO productDAO;
	@Resource
	Saveobject saveobject;
	
	
	//前台订单列表
	@RequestMapping("ordermsgDetailsLb")
	public String ordermsgDetailsLb(HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute(
		"sessionmember");
		String ddno = request.getParameter("ddno");
		HashMap map = new HashMap();
		map.put("ddno",ddno);
		List<Ordermsgdetails> list = ordermsgdetailsDAO.selectAll(map);
		for(Ordermsgdetails ordermsgdetails:list){
			Product product = productDAO.findById(Integer.parseInt(ordermsgdetails.getProductid()));
			ordermsgdetails.setProduct(product);
		}
		request.setAttribute("list",list);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "ordermsgdetailslb";
	}
	
	
	//后台订单详情列表
	@RequestMapping("admin/ordermsgdetailsList")
	public String ordermsgdetailsList(HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute(
		"sessionmember");
		String ddno = request.getParameter("ddno");
		HashMap map = new HashMap();
		map.put("ddno",ddno);
		List<Ordermsgdetails> list = ordermsgdetailsDAO.selectAll(map);
		for(Ordermsgdetails ordermsgdetails:list){
			Product product = productDAO.findById(Integer.parseInt(ordermsgdetails.getProductid()));
			ordermsgdetails.setProduct(product);
			
			double sjprice = product.getPrice();
			if(product.getTprice()>0){
				sjprice = product.getTprice();
			}
			double total = sjprice*ordermsgdetails.getNum();
			ordermsgdetails.setTotal(total);
		}
		request.setAttribute("list",list);
		return "admin/orderdetails";
	}
	
	
	@RequestMapping("/admin/ordermsgXq")
	public String ordermsgXq(HttpServletRequest request) {
		String ddno = request.getParameter("ddno");
		HashMap map = new HashMap();
		map.put("ddno",ddno);
		List<Ordermsgdetails> list = ordermsgdetailsDAO.selectAll(map);
		for(Ordermsgdetails ordermsgdetails:list){
			Product product = productDAO.findById(Integer.parseInt(ordermsgdetails.getProductid()));
			ordermsgdetails.setProduct(product);
		}
		request.setAttribute("list",list);
		return "admin/orderdetails";
	}
	
	//订单详情列表
	@RequestMapping("orderdetailsLb")
	public String orderdetailsLb(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String ddno = request.getParameter("ddno");
		HashMap map = new HashMap();
		map.put("ddno", ddno);
		List<Ordermsgdetails> list = ordermsgdetailsDAO.selectAll(map);
		for(Ordermsgdetails ordermsgdetails:list){
			Product product = productDAO.findById(Integer.parseInt(ordermsgdetails.getProductid()));
			ordermsgdetails.setProduct(product);
			double sjprice = product.getPrice();
			if(product.getTprice()>0){
				sjprice = product.getTprice();
			}
			double total = sjprice*ordermsgdetails.getNum();
			ordermsgdetails.setTotal(total);
		}
		PageInfo<Ordermsgdetails> pageInfo =  new PageInfo<Ordermsgdetails>(list);
		request.setAttribute("ddno",ddno);
		request.setAttribute("pageInfo",pageInfo);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "orderdetailslb";
	}

}
