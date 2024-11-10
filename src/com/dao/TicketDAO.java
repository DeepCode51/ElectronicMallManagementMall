package com.dao;

import java.util.*;

import com.entity.Ticket;
public interface TicketDAO {
	List<Ticket> selectAll(HashMap map);
	void add(Ticket ticket);
	void delete(int id);
	Ticket findById(int id);
}
