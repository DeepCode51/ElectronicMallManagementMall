package com.dao;
import com.entity.*;

import java.util.*;
public interface UserDAO {
   User findById(int id);
   void update(User user);
   void add(User user);
   List<User> selectAll(HashMap map);
}
