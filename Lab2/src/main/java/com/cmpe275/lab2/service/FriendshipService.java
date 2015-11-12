package com.cmpe275.lab2.service;
import java.util.List;
import com.cmpe275.lab2.model.Frienship;

public interface FriendshipService {

		public void add(Frienship friendship);
		public void delete(Frienship friendship);
		public Frienship search(Integer id1, Integer id2);
		public List<Integer> getAllFriends(int id);

}
