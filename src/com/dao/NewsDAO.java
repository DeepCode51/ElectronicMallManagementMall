package com.dao;
import com.entity.*;

import java.util.*;
public interface NewsDAO {
	List<News> selectAll(HashMap map);
	void add(News news);
	News findById(int id);
	void update(News news);
	void delete(int id);
}
