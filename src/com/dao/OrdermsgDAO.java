package com.dao;
import com.entity.*;

import java.util.*;
public interface OrdermsgDAO {
	void add(Ordermsg ordermsg);
	List<Ordermsg> selectAll(HashMap map);
	void update(Ordermsg ordermsg);
	Ordermsg findById(int id);
	List<Ordermsg> selectSaleMoney(HashMap map);
	List<Ordermsg> selectPh(HashMap map);
	List<Ordermsg> selectZph(HashMap map);
	List<Ordermsg> selectSale(HashMap map);
	
	
	
	
}
