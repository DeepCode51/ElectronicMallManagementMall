package com.dao;
import com.entity.*;

import java.util.*;
public interface PictureDAO {
	List<Picture> selectAll();
	void add(Picture picture);
	Picture findById(int id);
	void update(Picture picture);
	void delete(int id);
}
