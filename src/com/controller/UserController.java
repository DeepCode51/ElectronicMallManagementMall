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

import java.util.*;

@Controller
public class UserController extends BaseController {
	@Resource
	UserDAO userDAO;
	@RequestMapping("/admin/login")
	public String login(User user ,HttpServletRequest request) {
		    HashMap map = new HashMap();
		    map.put("username", user.getUsername());
		    map.put("userpassword", user.getUserpassword());
		    List<User> list = userDAO.selectAll(map);
		    if(list.size()==0){
		    	request.setAttribute("error", "用户名或密码错误");
		    	return "admin/login";
		    }else{
		    	request.getSession().setAttribute("admin", list.get(0));
		    	return "redirect:index.do";
		    }

	}
	
	@RequestMapping("/admin/grInfo")
	public String findById(HttpServletRequest request){
		User u = (User)request.getSession().getAttribute("admin");
		User user = userDAO.findById(u.getId());
		request.setAttribute("user", user);
		
		String suc = request.getParameter("suc");
		if(suc!=null){
			request.setAttribute("suc", "编辑成功");
		}
		return "admin/grinfo";
	}
	
	@RequestMapping("/admin/updateGrinfo")
	public String update(User u, HttpServletRequest request){
		userDAO.update(u);
		User admin = (User)userDAO.findById(u.getId());
		request.getSession().setAttribute("admin", admin);
		return "redirect:grInfo.do?suc=suc";
	}
	
	@RequestMapping("/admin/updatepwd")
	public String updatepwd(int id,String userpassword, HttpServletRequest request) {
		String oldpassword = request.getParameter("oldpassword");
		User user = userDAO.findById(id);
		if(oldpassword.equals(user.getUserpassword())){
			user.setUserpassword(userpassword);
			userDAO.update(user);
			request.setAttribute("suc", "操作成功");
		}else{
			request.setAttribute("error", "原密码错误");
		}
		return "admin/updatepwd";
	}
	
	@RequestMapping("/admin/userList")
	public String selectAll(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request){
		String key = request.getParameter("key");
		HashMap map = new HashMap();
		map.put("key", key);
		map.put("usertype", "商家");
		map.put("shstatus", "通过审核");
		PageHelper.startPage(pageNum, 10);
		List<User> list = userDAO.selectAll(map);
		PageInfo<User> pageInfo =  new PageInfo<User>(list);
		request.setAttribute("key",key);
		request.setAttribute("pageInfo",pageInfo);
		return "admin/userlist";
	}
	
	
	@RequestMapping("admin/userAdd")
	public String userAdd(User user, HttpServletRequest request){
		userDAO.add(user);
		return "redirect:userList.do";
	}
	
	//检查用户名的唯一性
	@RequestMapping("admin/checkUsername")
	public void checkUsername(String username, HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			HashMap map  = new HashMap();
			map.put("username", username);
			List<User> list = userDAO.selectAll(map);
			if(list.size()==0){
				out.print(0);
			}else{
				out.print(1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/admin/showUser")
	public String showUser(int id, HttpServletRequest request){
		User user = userDAO.findById(id);
		request.setAttribute("user", user);
		return "admin/useredit";
	}
	
	@RequestMapping("/admin/userEdit")
	public String userEdit(User u, HttpServletRequest request){
		userDAO.update(u);
		return "redirect:userList.do";
	}
	
	
	@RequestMapping("/admin/userDel")
	public String userDel(int id,HttpServletRequest request){
		User user = userDAO.findById(id);
		user.setDelstatus("1");
		userDAO.update(user);
		return "redirect:userList.do";
	}
	
	

}
