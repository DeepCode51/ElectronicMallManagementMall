package com.dao;

import java.util.*;

import com.entity.Address;
public interface AddressDAO {
	void add(Address address);
	Address findById(int id);
	void update(Address address);
	List<Address> selectAll(HashMap map);
}
