package com.dao;
import com.entity.*;

import java.util.*;
public interface CategoryDAO {
	List<Category> selectAll();
	void add(Category ct);
	Category findById(int id);
	void update(Category category);
	void delete(int id);
	List<Category> search(String key);
	List<Category> selectFcategory(HashMap map);
	List<Category> selectScategory(int fatherid);
	List<Category> findAll(HashMap map);
}
