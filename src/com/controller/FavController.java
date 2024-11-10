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
public class FavController extends BaseController {
	@Resource
	FavDAO favDAO;
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
	
	
	
	
	//查询收藏列表
	@RequestMapping("favLb")
	public String favList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		HashMap map = new HashMap();
		map.put("memberid", member.getId());
		PageHelper.startPage(pageNum, 10);
		List<Fav> list = favDAO.selectAll(map);
		for(Fav fav:list){
			Product product = productDAO.findById(Integer.parseInt(fav.getProductid()));
			fav.setProduct(product);
		}
		PageInfo<Fav> pageInfo =  new PageInfo<Fav>(list);
		request.setAttribute("pageInfo",pageInfo);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "favlb";
	}
	
	
	//删除收藏
	@RequestMapping("favDel")
	public String favDel(int id, HttpServletRequest request,HttpServletResponse response){
		favDAO.delete(id);
		return "redirect:favLb.do";
	}
	
	//添加收藏
	@RequestMapping("addFav")
	public void addFav(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			Member member = (Member) request.getSession().getAttribute(
					"sessionmember");
			if (member != null) {
				String productid = request.getParameter("productid");
				HashMap map = new HashMap();
				map.put("memberid", member.getId());
				map.put("productid", productid);
				List<Fav> list = favDAO.selectAll(map);
				if(list.size()==0){
					Fav fav = new Fav();
					fav.setMemberid(String.valueOf(member.getId()));
					fav.setProductid(productid);
					favDAO.add(fav);
					out.print("1");
				}else{
					out.print("2");
				}
			} else {
				out.print("0");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
