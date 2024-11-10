package com.dao;
import com.entity.*;

import java.util.*;
public interface CommentDAO {
	void add(Comment comment);
	List<Comment> selectAll(HashMap map);
	void delete(int id);
	Comment findById(int id);
	void update(Comment comment);
}
