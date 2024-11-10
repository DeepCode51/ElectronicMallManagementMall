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
import com.dao.*;
import com.entity.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.*;

@Controller
public class PictureController extends BaseController {
	@Resource
	PictureDAO pictureDAO;
	
	@RequestMapping("/admin/pictureList")
	public String pictureList(HttpServletRequest request) {
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Picture> list = pictureDAO.selectAll();
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/picturelist";
	}
	
	
	
	@RequestMapping("/admin/pictureAdd")
	public String pictureAdd(Picture picture,HttpServletRequest request){
		pictureDAO.add(picture);
		return "redirect:pictureList.do";
	}
	
	@RequestMapping("/admin/showPicture")
	public String showpicture(int id,HttpServletRequest request){
		Picture picture =  pictureDAO.findById(id);
		request.setAttribute("Picture", picture);
		return "admin/pictureedit";
	}
	
	@RequestMapping("/admin/pictureEdit")
	public String pictureEdit(Picture picture,HttpServletRequest request){
		pictureDAO.update(picture);
		request.setAttribute("picture", picture);
		return "redirect:pictureList.do";
	}
	
	@RequestMapping("admin/pictureDelAll")
	public String pictureDelAll(int id, HttpServletRequest request,HttpServletResponse response){
			pictureDAO.delete(id);
		return "redirect:pictureList.do";
	}
	
	
	
	

}
