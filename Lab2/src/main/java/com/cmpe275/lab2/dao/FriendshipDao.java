package com.cmpe275.lab2.dao;

import java.util.List;

import com.cmpe275.lab2.model.Frienship;

public interface FriendshipDao {

	public void add(Frienship friendship);
	public void delete(Frienship friendship);
	public Frienship search(Integer id1, Integer id2);
	public List<Integer> getAllFriends(int id);
}
