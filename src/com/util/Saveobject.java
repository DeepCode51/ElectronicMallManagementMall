package com.util;

import org.springframework.stereotype.Component;

import com.dao.CartDAO;
import com.dao.CategoryDAO;
import com.dao.InventoryDAO;
import com.dao.MemberDAO;
import com.dao.OrdermsgDAO;
import com.dao.OrdermsgdetailsDAO;
import com.dao.ProductDAO;
import com.entity.Cart;
import com.entity.Category;
import com.entity.Inventory;
import com.entity.Member;
import com.entity.Ordermsg;
import com.entity.Ordermsgdetails;
import com.entity.Product;
import com.entity.User;
import com.jspsmart.upload.Request;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class Saveobject {
	@Resource
	CategoryDAO categoryDao;
	@Resource
	ProductDAO productDAO;
	@Resource
	CartDAO cartDao;
	@Resource
	MemberDAO memberDAO;
	@Resource
	InventoryDAO inventoryDAO;
	@Resource
	OrdermsgDAO ordermsgDAO;
	@Resource
	OrdermsgdetailsDAO ordermsgdetailsDAO;
	
	
	
	
	

	public void getCategoryObject(HttpServletRequest request) {
		HashMap map = new HashMap();
		List<Category> fcategorylist = categoryDao.selectFcategory(map);// 一级分类
		for (Category fcategory : fcategorylist) {
			List<Category> scategorylist = categoryDao
					.selectScategory(fcategory.getId());
			fcategory.setScategorylist(scategorylist);
		}
		request.setAttribute("ctlist", fcategorylist);
	}

	public void getCart(HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute(
		"sessionmember");
		if (member != null) {
			HashMap bbb = new HashMap();
			bbb.put("memberid", member.getId());
			List<Cart> cartlist = cartDao.selectAll(bbb);
			String totalstr = "";
			double total = 0.0;
			for(Cart cart:cartlist){
				Member m = memberDAO.findById(cart.getMemberid());
				Product product = productDAO.findById(cart.getProductid());
				cart.setMember(m);
				cart.setProduct(product);
				double sjprice = 0D;
				double doublesubtotal = 0D;
				if(product.getTprice()>0){
					sjprice = product.getTprice();
					total += Double.parseDouble(String.valueOf(cart.getNum()))* sjprice;
					doublesubtotal = Double.parseDouble(String.valueOf(cart.getNum()))*sjprice;
				}else{
					sjprice = product.getPrice();
					total += Double.parseDouble(String.valueOf(cart.getNum()))* sjprice;
					doublesubtotal = Double.parseDouble(String.valueOf(cart.getNum()))*sjprice;
				}
				cart.setSubtotal(String.format("%.2f", doublesubtotal));
			}
			request.setAttribute("total", total);
			totalstr = String.format("%.2f", total);
			request.setAttribute("totaldoubel", Double.parseDouble(totalstr));
			request.setAttribute("cartlist", cartlist);
			request.setAttribute("totalstr", totalstr);
		}

	}
	
	
	
	public int getInvertory(int productid,HttpServletRequest request){
		User admin = (User)request.getSession().getAttribute("admin");
		int kc = 0;
		HashMap map = new HashMap();
		map.put("productid", productid);
		map.put("type", "in");
		int innum = 0;
		int outnum = 0;
		List<Inventory> inlist = inventoryDAO.selectAll(map);
		for(Inventory inventory:inlist){
			innum+=inventory.getNum();
		}
		map.put("type", "out");
		List<Inventory> outlist = inventoryDAO.selectAll(map);
		for(Inventory inv:outlist){
			outnum+=inv.getNum();
		}
		if(innum>outnum){
			kc=innum-outnum;
		}
		return kc;
	}
	
	
	public int getfdInvertory(int productid,String saver,HttpServletRequest request){
		int kc = 0;
		HashMap map = new HashMap();
		map.put("goodsid", productid);
		map.put("saver",saver);
		map.put("type", "in");
		int innum = 0;
		int outnum = 0;
		List<Inventory> inlist = inventoryDAO.selectAll(map);
		for(Inventory inventory:inlist){
			innum+=inventory.getNum();
		}
		map.put("type", "out");
		List<Inventory> outlist = inventoryDAO.selectAll(map);
		for(Inventory inv:outlist){
			outnum+=inv.getNum();
		}
		if(innum>outnum){
			kc=innum-outnum;
		}
		return kc;
	}
	
	
	//排行
	public List<Ordermsgdetails> getPh(String fid,HttpServletRequest request){
		HashMap map = new HashMap();
		map.put("fid", fid);
		List<Ordermsgdetails> orlist = ordermsgdetailsDAO.selectPh(map);
		for(Ordermsgdetails ordermsgdetails:orlist){
			Product product = productDAO.findById(Integer.parseInt(ordermsgdetails.getProductid()));
			ordermsgdetails.setProduct(product);
		}
		return orlist;
	}
	
	
}
