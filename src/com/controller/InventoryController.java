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
public class InventoryController extends BaseController {
	@Resource
	CategoryDAO categoryDAO;
	@Resource
	InventoryDAO inventoryDAO;
	@Resource
	ProductDAO productDAO;
	@Resource
	Saveobject saveobject;
	@Resource
	UserDAO userDAO;
	
	
	
	//跳转页面
	@RequestMapping("/admin/skipinventoryadd")
	public String skipinventoryadd(HttpServletRequest request) {
		String productid = request.getParameter("productid");
		request.setAttribute("productid", productid);
		return "admin/inventoryadd";
	}
	
	//库存
	@RequestMapping("/admin/inventoryList")
	public String inventoryList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		String fid = request.getParameter("fid")==null?"":request.getParameter("fid");
		String sid = request.getParameter("sid")==null?"":request.getParameter("sid");
		HashMap map = new HashMap();
		map.put("key", key);
		map.put("fid", fid);
		map.put("sid", sid);
		map.put("leibie", "购买商品");
		PageHelper.startPage(pageNum, 10);
		List<Product> list = productDAO.selectAll(map);
		for(Product product:list){
			Category fcategory = categoryDAO.findById(Integer.parseInt(product.getFid()));
			Category scategory = categoryDAO.findById(Integer.parseInt(product.getSid()));
			product.setFcategory(fcategory);
			product.setScategory(scategory);
			int totalnum = saveobject.getInvertory(product.getId(), request);
			product.setKc(totalnum);
		}
		if(!fid.equals("")){
		   	List<Category> scategorylist = categoryDAO.selectScategory(Integer.parseInt(fid));
		   	request.setAttribute("scategorylist", scategorylist);
		}
		PageInfo<Product> pageInfo =  new PageInfo<Product>(list);
		request.setAttribute("key", key);
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("pageInfo", pageInfo);
		saveobject.getCategoryObject(request);
		return "admin/inventorylist";
	}
	
	
	//采购记录
	@RequestMapping("admin/inventoryJl")
	public String inventoryJl(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		String savetime = request.getParameter("savetime");
		HashMap map = new HashMap();
		map.put("key", key);
		map.put("type", "in");
		map.put("savetime", savetime);
		map.put("delstatus", "0");
		PageHelper.startPage(pageNum, 10);
		List<Inventory> list = inventoryDAO.selectAll(map);
		for(Inventory inventory:list){
			Product product = productDAO.findById(Integer.parseInt(inventory.getProductid()));
			inventory.setProduct(product);
		}
		PageInfo<Inventory> pageInfo =  new PageInfo<Inventory>(list);
		request.setAttribute("key", key);
		request.setAttribute("savetime", savetime);
		request.setAttribute("pageInfo", pageInfo);
		return "admin/inventoryjl";
	}
	
	
	//入库
	@RequestMapping("/admin/inventoryAdd")
	public String inventoryAdd(Inventory inventory, HttpServletRequest request) {
		inventory.setSavetime(Info.getDateStr().substring(0,10));
		inventory.setDelstatus("0");
		inventoryDAO.add(inventory);
		return "redirect:inventoryList.do";
	}
	
	
	//删除记录
	@RequestMapping("admin/inventoryDel")
	public String inventoryDel(int id, HttpServletRequest request) {
		Inventory inventory = inventoryDAO.findById(id);
		inventory.setDelstatus("1");
		inventoryDAO.update(inventory);
		return "redirect:inventoryJl.do";
	}
	
	
	
	
	

}
