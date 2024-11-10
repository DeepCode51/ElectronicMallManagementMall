package com.dao;
import com.entity.*;

import java.util.*;
public interface CartDAO {
	List<Cart> selectAll(HashMap map);
	void add(Cart cart);
	void update(Cart cart);
	void delCart(int id);
	void updateNum(Cart cart);
}
