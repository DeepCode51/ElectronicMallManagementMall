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
import com.util.CheckCode;
import com.util.Info;
import com.util.Saveobject;

import java.util.*;

@Controller
public class OrdermsgController extends BaseController {
	@Resource
	CartDAO cartDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	ProductDAO productDAO;
	@Resource
	Saveobject saveobject;
	@Resource
	OrdermsgdetailsDAO ordermsgdetailsDAO;
	@Resource
	OrdermsgDAO ordermsgDAO;
	@Resource
	InventoryDAO inventoryDAO;
	@Resource
	TicketDAO ticketDAO;
	@Resource
	UserDAO userDAO;
	@Resource
	AddressDAO addressDAO;
	
	
	
	
	

	//创建订单
	@RequestMapping("createOrder")
	public String createOrder(HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		HashMap map = new HashMap();
		map.put("memberid", member.getId());
		List<Ticket> ticketlist = ticketDAO.selectAll(map);
		request.setAttribute("ticketlist", ticketlist);
		return "createorder";
	}
	
	//付款页面
	@RequestMapping("skipFukuan")
	public String skipFukuan(HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		String id = request.getParameter("id");
		Ordermsg ordermsg = ordermsgDAO.findById(Integer.parseInt(id));
		
		HashMap ppp = new HashMap();
		ppp.put("memberid", member.getId());
		String addrid="";
		List<Address> addresslist = addressDAO.selectAll(ppp);
		for(Address address:addresslist){
			if(address.getIsmr().equals("yes")){
				addrid=String.valueOf(address.getId());
			}
		}
		
		HashMap map = new HashMap();
		map.put("ddno", ordermsg.getDdno());
		List<Ordermsgdetails> ordermsgdetailslist = ordermsgdetailsDAO.selectAll(map);
		for(Ordermsgdetails ordermsgdetails:ordermsgdetailslist){
			Product product = productDAO.findById(Integer.parseInt(ordermsgdetails.getProductid()));
			ordermsgdetails.setProduct(product);
			
			double sjprice = product.getPrice();
			if(product.getTprice()>0){
				sjprice = product.getTprice();
			}
			double total = sjprice*ordermsgdetails.getNum();
			ordermsgdetails.setTotal(total);
		}
		ordermsg.setOrdermsglist(ordermsgdetailslist);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		map.put("memberid", member.getId());
		List<Ticket> ticketlist = ticketDAO.selectAll(map);
		request.setAttribute("ticketlist", ticketlist);
		request.setAttribute("addresslist", addresslist);
		request.setAttribute("ordermsg", ordermsg);
		double sjtotal = ordermsg.getTotal();
		request.setAttribute("sjtotal", sjtotal);
		request.setAttribute("addrid", addrid);
		return "fukuan";
	}
	
	//创建订单
	@RequestMapping("createDd")
	public String createDd(HttpServletRequest request) {
		
		String totalstr = request.getParameter("totalstr");
		Member member = (Member) request.getSession().getAttribute(
				"sessionmember");
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		String memberid = String.valueOf(member.getId());
		
		CheckCode cc = new CheckCode();
		String ddno = cc.getCheckCode();
		double total = Double.parseDouble(totalstr) ;
		String fkstatus = "待付款";
		String savetime = Info.getDateStr();
		
		HashMap map = new HashMap();
		map.put("memberid", member.getId());
		List<Cart> list = cartDAO.selectAll(map);
		if(list.size()!=0){
		for(Cart cart:list){
			Product product = productDAO.findById(cart.getProductid());
			double sjprice = 0D;
			double doublesubtotal = 0D;
			if(product.getTprice()>0){
				sjprice = product.getTprice();
				doublesubtotal = Double.parseDouble(String.valueOf(cart.getNum()))*sjprice;
			}else{
				sjprice = product.getPrice();
				doublesubtotal = Double.parseDouble(String.valueOf(cart.getNum()))*sjprice;
			}
			cart.setSubtotal(String.format("%.2f", doublesubtotal));
			
			Ordermsgdetails ordermsgdetails = new Ordermsgdetails();
			ordermsgdetails.setDdno(ddno);
			ordermsgdetails.setNum(cart.getNum());
			ordermsgdetails.setMemberid(String.valueOf(member.getId()));
			ordermsgdetails.setProductid(String.valueOf(cart.getProductid()));
			ordermsgdetails.setStatus(fkstatus);
			ordermsgdetails.setFid(product.getFid());
			ordermsgdetails.setLeibie("购买商品");
			
			ordermsgdetailsDAO.add(ordermsgdetails);
			cartDAO.delCart(cart.getId());
		}
		
		Ordermsg ordermsg = new Ordermsg();
		ordermsg.setDdno(ddno);
		ordermsg.setMemberid(memberid);
		ordermsg.setTotal(total);
		ordermsg.setGoodstype("购买商品");
		ordermsg.setFkstatus(fkstatus);
		ordermsg.setDelstatus("0");
		ordermsg.setSavetime(savetime);
		ordermsgDAO.add(ordermsg);
		
		return "redirect:cartList.do?suc=suc";
		}else{
			return "redirect:cartList.do";
		}
	}
	
	//前台订单列表
	@RequestMapping("orderLb")
	public String orderLb(HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute(
		"sessionmember");
		String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
		HashMap map = new HashMap();
		map.put("memberid", member.getId());
		map.put("delstatus", "0");
		map.put("goodstype", "购买商品");
		List<Ordermsg> list = ordermsgDAO.selectAll(map);
		for(Ordermsg ordermsg:list){
			if(ordermsg.getAddrid()!=null&&!ordermsg.getAddrid().equals("")){
				Address address = addressDAO.findById(Integer.parseInt(ordermsg.getAddrid()));
				ordermsg.setAddress(address);
			}
		}
		request.setAttribute("list",list);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		if(!msg.equals("")){
			request.setAttribute("msg", "恭喜你抽中了&nbsp;"+msg+"优惠券");
		}
		return "orderlb";
	}
	
	@RequestMapping("fubiOrder")
	public String fubiOrder(HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute(
		"sessionmember");
		String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
		HashMap map = new HashMap();
		map.put("memberid", member.getId());
		map.put("delstatus", "0");
		map.put("goodstype", "积分商品");
		List<Ordermsg> list = ordermsgDAO.selectAll(map);
		for(Ordermsg ordermsg:list){
			HashMap mmm = new HashMap();
			mmm.put("ddno", ordermsg.getDdno());
			Ordermsgdetails ordermsgdetails = ordermsgdetailsDAO.selectAll(mmm).get(0);
			Product product = productDAO.findById(Integer.parseInt(ordermsgdetails.getProductid()));
			ordermsgdetails.setProduct(product);
			ordermsg.setOrdermsgdetails(ordermsgdetails);
		}
		request.setAttribute("list",list);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "fubiorder";
	}
	
	
	
	//后台查询订单列表
	@RequestMapping("admin/orderList")
	public String orderList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		User admin = (User)request.getSession().getAttribute("admin");
		String key = request.getParameter("key");
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		HashMap map = new HashMap();
		map.put("ddno", key);
		map.put("delstatus", "0");
		map.put("goodstype", "购买商品");
		List<Ordermsg> list = ordermsgDAO.selectAll(map);
		for(Ordermsg ordermsg:list){
			Member member = memberDAO.findById(Integer.parseInt(ordermsg.getMemberid()));
			ordermsg.setMember(member);
			
				if(ordermsg.getAddrid()!=null&&!ordermsg.getAddrid().equals("")){
					Address address = addressDAO.findById(Integer.parseInt(ordermsg.getAddrid()));
					ordermsg.setAddress(address);
				}
		}
		PageInfo<Ordermsg> pageInfo =  new PageInfo<Ordermsg>(list);
		request.setAttribute("key",key);
		request.setAttribute("pageInfo",pageInfo);
		if(suc.equals("suc")){
			request.setAttribute("suc","操作成功");
		}else if(suc.equals("error")){
			request.setAttribute("suc","库存不足");
		}
		
		return "admin/orderlist";
	}
	
	
	@RequestMapping("/admin/fubiorderList")
	public String fubiorderList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		User admin = (User)request.getSession().getAttribute("admin");
		String key = request.getParameter("key");
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		HashMap map = new HashMap();
		map.put("ddno", key);
		map.put("delstatus", "0");
		map.put("saver", admin.getId());
		map.put("fkstatus", "待付款");
		map.put("goodstype", "积分商品");
		List<Ordermsg> list = ordermsgDAO.selectAll(map);
		for(Ordermsg ordermsg:list){
			Member member = memberDAO.findById(Integer.parseInt(ordermsg.getMemberid()));
			ordermsg.setMember(member);
			HashMap mmm = new HashMap();
			mmm.put("ddno", ordermsg.getDdno());
			Ordermsgdetails ordermsgdetails = ordermsgdetailsDAO.selectAll(mmm).get(0);
			Product product = productDAO.findById(Integer.parseInt(ordermsgdetails.getProductid()));
			ordermsgdetails.setProduct(product);
			ordermsg.setOrdermsgdetails(ordermsgdetails);
		}
		PageInfo<Ordermsg> pageInfo =  new PageInfo<Ordermsg>(list);
		request.setAttribute("key",key);
		request.setAttribute("pageInfo",pageInfo);
		if(suc.equals("suc")){
			request.setAttribute("suc","操作成功");
		}else if(suc.equals("error")){
			request.setAttribute("suc","库存不足");
		}
		
		return "admin/fubiorder";
	}
	
	//发货
	@RequestMapping("/admin/orderFh")
	public String orderFh(int id,HttpServletRequest request) {
		boolean flag = true;
		Ordermsg ordermsg = ordermsgDAO.findById(id);
		HashMap map = new HashMap();
		map.put("ddno", ordermsg.getDdno());
		List<Ordermsgdetails> list = ordermsgdetailsDAO.selectAll(map);
		for(Ordermsgdetails ordermsgdetails:list){
			int kc = saveobject.getInvertory(Integer.parseInt(ordermsgdetails.getProductid()), request);
			if(kc<ordermsgdetails.getNum()){
				flag = false;
				break;
			}
		}
		if(flag==true){
				ordermsg.setFkstatus("已发货");
				ordermsgDAO.update(ordermsg);
				
				for(Ordermsgdetails ddd:list){
				Inventory inv = new Inventory();
				inv.setNum(ddd.getNum());
				inv.setProductid(ddd.getProductid());
				inv.setType("out");
				inventoryDAO.add(inv);
				}
			return "redirect:orderList.do?suc=suc";
		}else{
			return "redirect:orderList.do?suc=error";
		}
	}
	
	
	//发货
	@RequestMapping("/admin/fubiorderFh")
	public String fubiorderFh(int id,HttpServletRequest request) {
		Ordermsg ordermsg = ordermsgDAO.findById(id);
		ordermsg.setFkstatus("已发货");
		ordermsgDAO.update(ordermsg);
				
		return "redirect:fubiorderList.do?suc=suc";
	}
	
	//收货
	@RequestMapping("qianShou")
	public String qianShou(int id,HttpServletRequest request){
		Ordermsg ordermsg = ordermsgDAO.findById(id);
		HashMap mmm = new HashMap();
		mmm.put("ddno", ordermsg.getDdno());
		List<Ordermsgdetails> list = ordermsgdetailsDAO.selectAll(mmm);
		for(Ordermsgdetails ordermsgdetails:list){
			ordermsgdetails.setStatus("交易完成");
			ordermsgdetailsDAO.update(ordermsgdetails);
		}
		ordermsg.setFkstatus("交易完成");
		ordermsgDAO.update(ordermsg);
		return "redirect:orderLb.do";
	}
	
	@RequestMapping("fubiqianshou")
	public String qs(int id,HttpServletRequest request){
		Ordermsg ordermsg = ordermsgDAO.findById(id);
		ordermsg.setFkstatus("交易完成");
		ordermsgDAO.update(ordermsg);
		return "redirect:fubiOrder.do";
	}
	
	@RequestMapping("fubiorderSc")
	public String fubiorderSc(int id,HttpServletRequest request){
		Ordermsg ordermsg = ordermsgDAO.findById(id);
		ordermsg.setDelstatus("1");
		ordermsgDAO.update(ordermsg);
		return "redirect:fubiOrder.do";
	}
	
	//删除订单
		@RequestMapping("orderSc")
		public String orderSc(int id,HttpServletRequest request){
			Ordermsg ordermsg = ordermsgDAO.findById(id);
			ordermsg.setDelstatus("1");
			ordermsgDAO.update(ordermsg);
			return "redirect:orderLb.do";
		}
		
		//删除订单
		@RequestMapping("admin/orderDel")
		public String orderDel(int id,HttpServletRequest request){
			Ordermsg ordermsg = ordermsgDAO.findById(id);
			ordermsg.setDelstatus("1");
			ordermsgDAO.update(ordermsg);
			return "redirect:orderList.do";
		}
		
		
		//删除订单
		@RequestMapping("admin/fubiorderDel")
		public String fubiorderDel(int id,HttpServletRequest request){
			Ordermsg ordermsg = ordermsgDAO.findById(id);
			ordermsg.setDelstatus("1");
			ordermsgDAO.update(ordermsg);
			return "redirect:fubiorderList.do";
		}
	
	
	//使用优惠券
		@RequestMapping("updateYhtotal")
		public void updateYhtotal(HttpServletRequest request, HttpServletResponse response) {
			PrintWriter out;
			try {
				out = response.getWriter();
				String id = request.getParameter("id");
				String ordermsgid = request.getParameter("ordermsgid");
				Ticket ticket = ticketDAO.findById(Integer.parseInt(id));
				Ordermsg ordermsg = ordermsgDAO.findById(Integer.parseInt(ordermsgid));
				int money = ticket.getMoney();
				saveobject.getCart(request);
				double total = (Double)ordermsg.getTotal();
				if(total>money){
					double yhtotal = total-money;
					String yhtotalstr = String.format("%.2f", yhtotal);
					out.println(yhtotalstr);
				}else{
					out.println("-1");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
     //支付方式页面
	@RequestMapping("skipZffs")
	public String skipZffs(HttpServletRequest request){
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		String id = request.getParameter("id");
		String totalstr = request.getParameter("totalstr");
		String ticketid = request.getParameter("ticketid");
		String addrid = request.getParameter("addrid");
		request.setAttribute("id", id);
		request.setAttribute("sjtotal", totalstr);
		request.setAttribute("ticketid", ticketid);
		request.setAttribute("addrid", addrid);
		saveobject.getCategoryObject(request);
		if(!suc.equals("")&&suc.equals("error")){
		request.setAttribute("suc", "余额不足");
		}
		if(!suc.equals("")&&suc.equals("suc")){
			request.setAttribute("suc", "支付成功");
		}
		
		return "zffs";
	}
	
	//订单付款
	@RequestMapping("fukuan")
	public String fukuan(HttpServletRequest request){
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		Member mmm = memberDAO.findById(member.getId());
		String id = request.getParameter("id");
		String addrid = request.getParameter("addrid");
		String sjtotal = request.getParameter("sjtotal");
		String ticketid = request.getParameter("ticketid");
		String zffs = request.getParameter("zffs");
		double yue = mmm.getMoney();
		double doublesjtotal = Double.parseDouble(sjtotal);
		//boolean flag = true;
		double sxyue = yue-doublesjtotal;
		
		if(sxyue>=0){
			Ordermsg ordermsg = ordermsgDAO.findById(Integer.parseInt(id));
			
			HashMap map = new HashMap();
			map.put("ddno",ordermsg.getDdno());
			List<Ordermsgdetails> ordermsglist = ordermsgdetailsDAO.selectAll(map);
			for(Ordermsgdetails ordermsgdetails:ordermsglist){
				Product product = productDAO.findById(Integer.parseInt(ordermsgdetails.getProductid()));
				ordermsgdetails.setProduct(product);
			}
			ordermsg.setZffs(zffs);
			ordermsg.setAddrid(addrid);
			ordermsg.setFkstatus("已付款");
			ordermsgDAO.update(ordermsg);
			ordermsg.setOrdermsglist(ordermsglist);
			request.setAttribute("ordermsg", ordermsg);
			if(ticketid!=null&&!ticketid.equals("")){
			ticketDAO.delete(Integer.parseInt(ticketid));
			}
			
			mmm.setMoney(sxyue);
			memberDAO.updateYue(mmm);
			
			double xftotal = mmm.getXftotal()+doublesjtotal;
			mmm.setXftotal(xftotal);
			memberDAO.updateXftotal(mmm);
			
			int jf = mmm.getJf()+new Double(doublesjtotal/10).intValue();
			mmm.setJf(jf);
			memberDAO.updateJf(mmm);
			
			saveobject.getCategoryObject(request);
			return "success";
		}else{
			
			request.setAttribute("id", id);
			request.setAttribute("sjtotal", sjtotal);
			request.setAttribute("ticketid", ticketid);
			return "redirect:skipZffs.do?suc=error&totalstr="+sjtotal+"&ticketid="+ticketid+"&id="+id;
		}
		
		
		
		
	}
	
	//取消订单
	@RequestMapping("qxOrdermsg")
	public String qxOrdermsg(int id,HttpServletRequest request){
		Ordermsg ordermsg = ordermsgDAO.findById(id);
		ordermsg.setFkstatus("已取消");
		ordermsgDAO.update(ordermsg);
		return "redirect:orderLb.do";
	}
	
	//
	@RequestMapping("addOrder")
	public void addOrder(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			Member member = (Member) request.getSession().getAttribute(
					"sessionmember");
			if (member != null) {
				Member mmm = memberDAO.findById(member.getId());
				String productid = request.getParameter("productid");
				String num = request.getParameter("num");
				Product product = productDAO.findById(Integer.parseInt(productid));
				double total = Double.parseDouble(num)*product.getPrice();
				int intotal = new Double(total).intValue();
				if(intotal<=mmm.getJf()){
				int yjf = mmm.getJf()-intotal;
				CheckCode cc = new CheckCode();
				String ddno = cc.getCheckCode();
				Ordermsg ordermsg = new Ordermsg();
				ordermsg.setDdno(ddno);
				ordermsg.setMemberid(String.valueOf(member.getId()));
				ordermsg.setTotal(intotal);
				ordermsg.setFkstatus("已付款");
				ordermsg.setSavetime(Info.getDateStr());
				ordermsg.setDelstatus("0");
				ordermsg.setGoodstype("积分商品");
				ordermsgDAO.add(ordermsg);
				
				Ordermsgdetails ordermsgdetails = new Ordermsgdetails();
				ordermsgdetails.setDdno(ddno);
				ordermsgdetails.setNum(Integer.parseInt(num));
				ordermsgdetails.setMemberid(String.valueOf(member.getId()));
				ordermsgdetails.setProductid(String.valueOf(productid));
				ordermsgdetails.setStatus("交易完成");
				ordermsgdetails.setLeibie("积分商品");
				ordermsgdetailsDAO.add(ordermsgdetails);
				
				mmm.setJf(yjf);
				memberDAO.updateJf(mmm);
				out.print("1");
				}else{
				out.print("2");//积分不足
				}
			} else {
				out.println("0");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
}
