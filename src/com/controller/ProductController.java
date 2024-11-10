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
import com.util.CommDAO;
import com.util.Info;
import com.util.Saveobject;

import java.util.*;

@Controller
public class ProductController extends BaseController {
	@Resource
	ProductDAO productDAO;
	@Resource
	CategoryDAO categoryDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	CommentDAO commentDAO;
	@Resource
	OrdermsgDAO ordermsgDAO;
	@Resource
	Saveobject saveobject;
	@Resource
	UserDAO userDAO;
	
	
	
	//积分商品添加
	@RequestMapping("/admin/fubiAdd")
	public String fubiAdd(Product product,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("admin");
		String productno = Info.getAutoId();
		product.setProductno(productno);
		product.setSaver(String.valueOf(user.getId()));
		product.setIssj("no");
		product.setIstj("no");
		product.setProductid("-1");
		product.setDelstatus("0");
		productDAO.add(product);
		return "redirect:fubiList.do";
	}
	
	//后台查询积分商品列表
	@RequestMapping("/admin/fubiList")
	public String fubiList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
	    HashMap map = new HashMap();
		map.put("key", key);
		map.put("productid", "-1");
		map.put("leibie", "积分商品");
		PageHelper.startPage(pageNum,10);
		List<Product> list = productDAO.selectAll(map);
		PageInfo<Product> pageInfo =  new PageInfo<Product>(list);
		request.setAttribute("key", key);
		request.setAttribute("pageInfo", pageInfo);
		return "admin/fubilist";
	}
	
	//后台查询商品列表
	@RequestMapping("/admin/productList")
	public String productList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request) {
		String key = request.getParameter("key");
		String fid = request.getParameter("fid")==null?"":request.getParameter("fid");
		String sid = request.getParameter("sid")==null?"":request.getParameter("sid");
	    HashMap map = new HashMap();
	   	List<Category> fcategorylist = categoryDAO.selectFcategory(map);
	   	if(!fid.equals("")){
	   	List<Category> scategorylist = categoryDAO.selectScategory(Integer.parseInt(fid));
	   	request.setAttribute("scategorylist", scategorylist);
	   	}
		map.put("key", key);
		map.put("fid", fid);
		map.put("sid", sid);
		map.put("productid", "-1");
		map.put("leibie", "购买商品");
		PageHelper.startPage(pageNum,10);
		List<Product> list = productDAO.selectAll(map);
		for(Product product:list){
			Category fcategory = categoryDAO.findById(Integer.parseInt(product.getFid()));
			Category scategory = categoryDAO.findById(Integer.parseInt(product.getSid()));
			product.setFcategory(fcategory);
			product.setScategory(scategory);
		}
		PageInfo<Product> pageInfo =  new PageInfo<Product>(list);
		request.setAttribute("key", key);
		request.setAttribute("fid", fid);
		request.setAttribute("sid", sid);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("fcategorylist", fcategorylist);
		saveobject.getCategoryObject(request);
		return "admin/productlist";
	}
	
	
	//查询商品类别
	@RequestMapping("/admin/categorySelect")
	public String categorySelect(HttpServletRequest request){
		HashMap map = new HashMap();
		List<Category> list = categoryDAO.selectFcategory(map);
		for(Category category:list){
			List<Category> scategorylist = categoryDAO.selectScategory(category.getId());
			category.setScategorylist(scategorylist);
		}
		request.setAttribute("list", list);
		return "admin/productadd";
	}
	
	//商品添加
	@RequestMapping("admin/productAdd")
	public String productAdd(Product product,HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("admin");
		String productno = Info.getAutoId();
		product.setProductno(productno);
		product.setSaver(String.valueOf(user.getId()));
		product.setIssj("no");
		product.setIstj("no");
		product.setProductid("-1");
		product.setDelstatus("0");
		productDAO.add(product);
		return "redirect:productList.do";
	}
	
	@RequestMapping("admin/showFubi")
	public String showFubi(int id,HttpServletRequest request){
		Product product =  productDAO.findById(id);
		request.setAttribute("fubi", product);
		return "admin/fubiedit";
	}
	
	
	//后台商品查询
	@RequestMapping("/admin/showProduct")
	public String showproduct(int id,HttpServletRequest request){
		Product product =  productDAO.findById(id);
		Category fcategory = categoryDAO.findById(Integer.parseInt(product.getFid()));
		Category scategory = categoryDAO.findById(Integer.parseInt(product.getSid()));
		product.setFcategory(fcategory);
		product.setScategory(scategory);
		
		HashMap map = new HashMap();
		List<Category> fcategorylist = categoryDAO.selectFcategory(map);
		List<Category> scategorylist = categoryDAO.selectScategory(Integer.parseInt(product.getFid()));
		request.setAttribute("fcategorylist", fcategorylist);
		request.setAttribute("scategorylist", scategorylist);
		request.setAttribute("product", product);
		return "admin/productedit";
	}
	
	//前台商品查询
	@RequestMapping("productDetails")
	public String productDetails(int id,HttpServletRequest request){
		
		String plsuc = request.getParameter("plsuc");
		String plerr = request.getParameter("plerr");
		String msg = request.getParameter("msg");
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		Product product =  productDAO.findById(id);
		Category fcategory = categoryDAO.findById(Integer.parseInt(product.getFid()));
		Category scategory = categoryDAO.findById(Integer.parseInt(product.getSid()));
		User shop = userDAO.findById(Integer.parseInt(product.getSaver()));
		product.setFcategory(fcategory);
		product.setShop(shop);
		product.setScategory(scategory);
		
		if(msg!=null){
			msg = "请先登录";
			request.setAttribute("msg", msg);
		}
		if(suc.equals("suc")){
			request.setAttribute("suc", "评论成功");
		}else if(suc.equals("error")){
			request.setAttribute("suc", "请先登录");
		}else if(suc.equals("err")){
			request.setAttribute("suc", "没有购买记录,评论失败");
		}
		
		
		//评论
		HashMap mmm = new HashMap();
		mmm.put("productid", product.getId());
		List<Comment> commentlist = commentDAO.selectAll(mmm);
		for(Comment comment:commentlist){
			Member member = memberDAO.findById(Integer.parseInt(comment.getMemberid()));
			comment.setMember(member);
		}
		
		request.setAttribute("product", product);
		request.setAttribute("commentlist", commentlist);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		
		//相似商品
		HashMap map = new HashMap();
		map.put("sid", product.getSid());
		map.put("issj", "yes");
		List<Product> xslist = productDAO.selectAll(map);
		request.setAttribute("xslist", xslist);
		return "productdetails";
	}
	
	
	@RequestMapping("fubix")
	public String fubix(int id,HttpServletRequest request){
		
		String msg = request.getParameter("msg");
		String suc = request.getParameter("suc")==null?"":request.getParameter("suc");
		Product product =  productDAO.findById(id);
		product.setJf(new Double(product.getPrice()).intValue());
		if(msg!=null){
			msg = "请先登录";
			request.setAttribute("msg", msg);
		}
		if(suc.equals("suc")){
			request.setAttribute("suc", "评论成功");
		}else if(suc.equals("error")){
			request.setAttribute("suc", "请先登录");
		}else if(suc.equals("err")){
			request.setAttribute("suc", "没有购买记录,评论失败");
		}
		
		
		//评论
		HashMap mmm = new HashMap();
		mmm.put("productid", product.getId());
		List<Comment> commentlist = commentDAO.selectAll(mmm);
		for(Comment comment:commentlist){
			Member member = memberDAO.findById(Integer.parseInt(comment.getMemberid()));
			comment.setMember(member);
		}
		request.setAttribute("product", product);
		request.setAttribute("commentlist", commentlist);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "fubix";
	}
	
	
	//跳转到预订页面
	@RequestMapping("skipZycar")
	public String skipZycar(int id,HttpServletRequest request){
		Member member = (Member)request.getSession().getAttribute("sessionmember");
		if(member!=null){
		Product product =  productDAO.findById(id);
		//商品类别
		List<Category> ctlist = categoryDAO.selectAll();
		request.setAttribute("ctlist", ctlist);
		request.setAttribute("product", product);
		String err = request.getParameter("err");
		if(err!=null){
		request.setAttribute("msg", "帐户余额不足");	
		}
		return "zycar";
		}else{
	    return "redirect:productDetails.do?id="+id+"&msg=msg";
		}
	}
	
	//商品编辑
	@RequestMapping("/admin/productEdit")
	public String productEdit(Product product,HttpServletRequest request){
		productDAO.update(product);
		request.setAttribute("product", product);
		return "redirect:productList.do";
	}
	
	@RequestMapping("/admin/fubiEdit")
	public String fubiEdit(Product product,HttpServletRequest request){
		productDAO.update(product);
		request.setAttribute("product", product);
		return "redirect:fubiList.do";
	}
	
	//商品删除
	@RequestMapping("admin/productDelAll")
	public String productDelAll(int id,HttpServletRequest request,HttpServletResponse response){
			productDAO.delete(id);
		return "redirect:productList.do";
	}
	
	@RequestMapping("admin/fubiDel")
	public String fubiDel(int id,HttpServletRequest request,HttpServletResponse response){
		productDAO.delete(id);
		return "redirect:fubiList.do";
	}
	
	
	
	
	//显示商品列表页
	@RequestMapping("productLb")
	public String productLb(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request){
		String fid = request.getParameter("fid");
		String sid = request.getParameter("sid");
		String ph = request.getParameter("ph");
		String saver = request.getParameter("saver");
		//显示商品列表
   	    HashMap map = new HashMap();
   	    map.put("fid", fid);
   	    map.put("sid", sid);
	    map.put("issj", "yes");
	    map.put("ph", ph);
	    map.put("saver", saver);
	    List<Product> nlist = productDAO.selectAll(map);
	    PageHelper.startPage(pageNum,10);
		List<Product> list = productDAO.selectAll(map);
		PageInfo<Product> pageInfo =  new PageInfo<Product>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("nlist", nlist);
		request.setAttribute("sid", sid);
		request.setAttribute("fid", fid);
		request.setAttribute("saver", saver);
		
		
		String categroystr = "";
		if(sid!=null && !sid.equals("")){
			Category scategory = categoryDAO.findById(Integer.parseInt(sid));
			categroystr=scategory.getName();
			request.setAttribute("categroystr", categroystr);
		}
		if(fid!=null && !fid.equals("")){
			Category fcategory = categoryDAO.findById(Integer.parseInt(fid));
			categroystr=fcategory.getName();
			request.setAttribute("categroystr", categroystr);
		}
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "productlb";
	}
	
	
	
	@RequestMapping("shopproductLb")
	public String shopproductLb(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request){
		String fid = request.getParameter("fid");
		String sid = request.getParameter("sid");
		String ph = request.getParameter("ph");
		String saver = request.getParameter("saver");
		User shop = userDAO.findById(Integer.parseInt(saver));
		//显示商品列表
   	    HashMap map = new HashMap();
   	    map.put("fid", fid);
   	    map.put("sid", sid);
	    map.put("issj", "yes");
	    map.put("ph", ph);
	    map.put("saver", saver);
	    List<Product> nlist = productDAO.selectAll(map);
	    PageHelper.startPage(pageNum,10);
		List<Product> list = productDAO.selectAll(map);
		PageInfo<Product> pageInfo =  new PageInfo<Product>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("nlist", nlist);
		request.setAttribute("sid", sid);
		request.setAttribute("fid", fid);
		request.setAttribute("saver", saver);
		request.setAttribute("shop", shop);
		request.setAttribute("shopid", saver);
		
		
		String categroystr = "";
		if(sid!=null && !sid.equals("")){
			Category scategory = categoryDAO.findById(Integer.parseInt(sid));
			categroystr=scategory.getName();
			request.setAttribute("categroystr", categroystr);
		}
		if(fid!=null && !fid.equals("")){
			Category fcategory = categoryDAO.findById(Integer.parseInt(fid));
			categroystr=fcategory.getName();
			request.setAttribute("categroystr", categroystr);
		}
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "shop";
	}
	
	
	
	@RequestMapping("fubiLb")
	public String fubiLb(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request){
		//显示商品列表
   	    HashMap map = new HashMap();
	    map.put("issj", "yes");
	    map.put("leibie", "积分商品");
	    List<Product> nlist = productDAO.selectAll(map);
	    request.setAttribute("nlist", nlist);
	    PageHelper.startPage(pageNum,10);
		List<Product> list = productDAO.selectAll(map);
		PageInfo<Product> pageInfo =  new PageInfo<Product>(list);
		request.setAttribute("pageInfo", pageInfo);
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "fubilb";
	}
	
	
	
	//推荐商品
	@RequestMapping("admin/udateIstj")
	public String udateIstj(HttpServletRequest request){
		String id = request.getParameter("id");
		String istj = request.getParameter("type");
		Product product = productDAO.findById(Integer.parseInt(id));
		product.setIstj(istj);
		productDAO.update(product);
		return "redirect:productList.do";
	}
	
	
	//商品上下架
	@RequestMapping("admin/udateIssj")
	public String udateIssj(HttpServletRequest request){
		String id = request.getParameter("id");
		String shop = request.getParameter("shop");
		Product product = productDAO.findById(Integer.parseInt(id));
		if(product.getIssj().equals("yes")){
			product.setIssj("no");
		}else{
			product.setIssj("yes");
		}
		productDAO.update(product);
		return "redirect:productList.do";
	}
	
	
	@RequestMapping("admin/updatefbIssj")
	public String updatefbIssj(HttpServletRequest request){
		String id = request.getParameter("id");
		Product product = productDAO.findById(Integer.parseInt(id));
		if(product.getIssj().equals("yes")){
			product.setIssj("no");
		}else{
			product.setIssj("yes");
		}
		productDAO.update(product);
		return "redirect:fubiList.do";
	}
	
	//特价页面
	@RequestMapping("admin/skiptjprice")
	public String skiptjprice(HttpServletRequest request){
		String id = request.getParameter("id");
		Product product = productDAO.findById(Integer.parseInt(id));
		request.setAttribute("product", product);
		return "admin/tjprice";
	}
	
	//更新特价
	@RequestMapping("admin/tjpriceUpdate")
	public String tjpriceUpdate(Product product,HttpServletRequest request){
		productDAO.updateTprice(product);
			return "redirect:productList.do";
	}
	
	
	//
	@RequestMapping("shopGoods")
	public String shopGoods(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpServletRequest request){
		String fid = request.getParameter("fid");
		String sid = request.getParameter("sid");
		String shopid = request.getParameter("shopid");
		String ph = request.getParameter("ph");
		User shop = userDAO.findById(Integer.parseInt(shopid));
		//显示商品列表
   	    HashMap map = new HashMap();
   	    map.put("fid", fid);
   	    map.put("sid", sid);
	    map.put("issj", "yes");
	    map.put("ph", ph);
	    map.put("saver", shopid);
	    map.put("leibie", "购买商品");
	    List<Product> nlist = productDAO.selectAll(map);
	    PageHelper.startPage(pageNum,8);
		List<Product> list = productDAO.selectAll(map);
		PageInfo<Product> pageInfo =  new PageInfo<Product>(list);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("nlist", nlist);
		request.setAttribute("sid", sid);
		request.setAttribute("fid", fid);
		request.setAttribute("shopid", shopid);
		request.setAttribute("shop", shop);
		
		
		String categroystr = "";
		if(sid!=null && !sid.equals("")){
			Category scategory = categoryDAO.findById(Integer.parseInt(sid));
			categroystr=scategory.getName();
			request.setAttribute("categroystr", categroystr);
		}
		if(fid!=null && !fid.equals("")){
			Category fcategory = categoryDAO.findById(Integer.parseInt(fid));
			categroystr=fcategory.getName();
			request.setAttribute("categroystr", categroystr);
		}
		saveobject.getCart(request);
		saveobject.getCategoryObject(request);
		return "shop";
	}
	

}
