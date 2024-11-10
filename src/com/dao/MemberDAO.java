package com.dao;
import com.entity.*;

import java.util.*;
public interface MemberDAO {
	void add(Member member);
	Member findById(int id);
	void update(Member member);
	List<Member> selectAll(HashMap map);
	void updateYue(Member member);
	void updateXftotal(Member member);
	void updateJf(Member member);
	
	
	
}
