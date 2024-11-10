package com.dao;
import com.entity.*;

import java.util.*;
public interface OrdermsgdetailsDAO {
	void add(Ordermsgdetails ordermsgdetails);
	List<Ordermsgdetails> selectAll(HashMap map);
	void update(Ordermsgdetails ordermsgdetails);
	List<Ordermsgdetails> selectSale(int productid);
	List<Ordermsgdetails> selectSale(HashMap map);
	List<Ordermsgdetails> selectPh(HashMap map);
	
}
