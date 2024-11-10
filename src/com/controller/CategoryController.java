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
import com.google.gson.Gson;

import java.util.*;

@Controller
public class CategoryController extends BaseController {
	@Resource
	CategoryDAO categoryDAO;
	
	@RequestMapping("/admin/categoryList")
	public String categoryList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		HashMap map = new HashMap();
		map.put("key", key);
		PageHelper.startPage(pageNum, 10);
		List<Category> fcategorylist = categoryDAO.selectFcategory(map);
		for(Category category:fcategorylist){
			List<Category> scategorylist = categoryDAO.selectScategory(category.getId());
			category.setScategorylist(scategorylist);
		}
		
		PageInfo<Category> pageInfo =  new PageInfo<Category>(fcategorylist);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("key", key);
		return "admin/categorylist";
	}
	
	
	@RequestMapping("/admin/categoryAdd")
	public String dingdanAdd(Category ct,HttpServletRequest request){
		categoryDAO.add(ct);
		return "redirect:categoryList.do";
	}
	
	@RequestMapping("/admin/showCategory")
	public String showCategory(int id,HttpServletRequest request){
		Category category =  categoryDAO.findById(id);
		request.setAttribute("category", category);
		return "admin/categoryedit";
	}
	
	@RequestMapping("/admin/categoryEdit")
	public String categoryEdit(Category category,HttpServletRequest request){
		categoryDAO.update(category);
		request.setAttribute("category", category);
		return "redirect:categoryList.do";
	}
	
	@RequestMapping("admin/categoryDelAll")
	public String categoryDelAll(int id, HttpServletRequest request,HttpServletResponse response){
		categoryDAO.delete(id);
		return "redirect:categoryList.do";
	}
	
	
	//类别二级联动
	@RequestMapping("admin/searchCtype")
	public void searchCtype(HttpServletRequest request,HttpServletResponse response){
		String fatherid = request.getParameter("fatherid")==null?"":request.getParameter("fatherid");
		String xml = "";
		if(!fatherid.equals("")){
	    List<Category> list = categoryDAO.selectScategory(Integer.parseInt(fatherid));
		if(list.size()>0){
		    for(Category category:list){
		    	xml += "<option value='"+category.getId()+"'>"+category.getName()+"</option>";
			}
		}else{
			xml ="<option value=''>请选择上一级目录</opion>";
		}
		}else{
			xml ="<option value=''>请选择上一级目录</opion>";
		}
 		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
 		PrintWriter out;
		try {
			out = response.getWriter();
			Gson gson = new Gson();
			String flag = gson.toJson(xml);
			out.write(flag);
	 		out.flush();
	 		out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
