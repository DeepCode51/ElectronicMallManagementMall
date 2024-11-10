package com.dao;
import com.entity.*;

import java.util.*;
public interface FavDAO {
	List<Fav> selectAll(HashMap map);
	void add(Fav fav);
	Fav findById(int id);
	void delete(int id);
}
