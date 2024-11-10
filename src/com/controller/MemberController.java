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
public class MemberController extends BaseController {
	@Resource
	MemberDAO memberDAO;
	@Resource
	CategoryDAO categoryDAO;
	@Resource
	UserDAO userDAO;
	@Resource
	Saveobject saveobject;
	
	
	
	
	//登录
	@RequestMapping("Login")
	public String Login(String uname,String upass, HttpServletRequest request){
		HashMap map = new HashMap();
		map.put("uname", uname);
		map.put("upass", upass);
		List<Member> list = memberDAO.selectAll(map);
		if(list.size()==0){
			return "redirect:skipLogin.do?suc=suc";
		}else{
			request.getSession().setAttribute("sessionmember", list.get(0));
			return "redirect:index.do";	
		}
	}
	
	//注册
	@RequestMapping("Register")
	public String Register(Member member, HttpServletRequest request){
		member.setUtype("普通用户");
		member.setDelstatus("0");
		member.setSavetime(Info.getDateStr());
		member.setJf(0);
		memberDAO.add(member);
		return "redirect:skipReg.do?suc=suc";
	}
	
	//检查用户名的唯一性
	@RequestMapping("checkUname")
	public void checkUname(String uname, HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			HashMap map = new HashMap();
			map.put("uname", uname);
			List<Member> list = memberDAO.selectAll(map);
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
	
	
	//修改个人信息
	@RequestMapping("memberEdit")
	public String memberEdit(Member member, HttpServletRequest request){
		memberDAO.update(member);
		return "redirect:memberHome.do?suc=suc";
	}
	
	//退出
	@RequestMapping("memberExit")
	public String memberExit(HttpServletRequest request){
		request.getSession().removeAttribute("sessionmember");
		return "redirect:index.do";
	}
	
	
	//后台查询用户列表
	@RequestMapping("/admin/memberList")
	public String memberList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		HashMap map = new HashMap();
		map.put("key", key);
		PageHelper.startPage(pageNum, 10);
		List<Member> list = memberDAO.selectAll(map);
		PageInfo<Member> pageInfo =  new PageInfo<Member>(list);
		request.setAttribute("key",key);
		request.setAttribute("pageInfo",pageInfo);
		return "admin/memberlist";
	}
	
	
	//删除用户
	@RequestMapping("admin/memberDelAll")
	public String memberDelAll(int id, HttpServletRequest request){
		Member member = memberDAO.findById(id);
		member.setDelstatus("1");
		memberDAO.update(member);
		return "redirect:memberList.do";
	}
	
	//查看用户帐户
	@RequestMapping("Account")
	public String Account( HttpServletRequest request){
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		Member mm = memberDAO.findById(member.getId());
		request.setAttribute("member", mm);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "myaccount";
	}
	
	//充值
	@RequestMapping("updateMoney")
	public String updateMoney(Member member, HttpServletRequest request){
		Member mm = memberDAO.findById(member.getId());
		double monye = mm.getMoney()+member.getMoney();
		member.setMoney(monye);
		memberDAO.update(member);
		return "redirect:Account.do";
	}
	
	//我的主页
	@RequestMapping("memberHome")
	public String memberHome(HttpServletRequest request){
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		Member mmm = memberDAO.findById(member.getId());
		request.setAttribute("member", mmm);
		saveobject.getCategoryObject(request);
		if(!suc.equals("")){
			request.setAttribute("suc", "操作成功");
		}
		return "memberhome";
	}
	
	//充值页面
	@RequestMapping("skipChongzhi")
	public String skipChongzhi(HttpServletRequest request){
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		Member mmm = memberDAO.findById(member.getId());
		request.setAttribute("member", mmm);
		saveobject.getCategoryObject(request);
		if(!suc.equals("")){
			request.setAttribute("suc", "充值成功");
		}
		return "yue";
	}
	
	//充值
	@RequestMapping("chongzhi")
	public String chongzhi(Member member,HttpServletRequest request){
		Member mmm = memberDAO.findById(member.getId());
		double yue = mmm.getMoney();
		double money = yue+member.getMoney();
		mmm.setMoney(money);
		memberDAO.updateYue(mmm);
		return "redirect:skipChongzhi.do?suc=suc";
	}
	
	//修改密码页面
	@RequestMapping("upassShow")
	public String upassShow(Member member,HttpServletRequest request){
		Member mmm = (Member)request.getSession().getAttribute("sessionmember");
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		request.setAttribute("member", mmm);
		return "upassedit";
	}
	
	
	
	
}
