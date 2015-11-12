package com.cmpe275.lab2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.lab2.dao.FriendshipDao;
import com.cmpe275.lab2.model.Frienship;
import com.cmpe275.lab2.service.FriendshipService;
@Service
public class FriendshipServiceImpl implements FriendshipService{

	@Autowired 
	FriendshipDao friendDao; 
	
	@Transactional
	public void add(Frienship friend) {
		friendDao.add(friend);
	}

	@Transactional
	public void delete(Frienship friend) {
		friendDao.delete(friend);
	}

	@Transactional
	public Frienship search(Integer id1, Integer id2) {
		return friendDao.search(id1, id2);
	}

	@Transactional
	public List<Integer> getAllFriends(int personId) {
		return friendDao.getAllFriends(personId);
	}
}
