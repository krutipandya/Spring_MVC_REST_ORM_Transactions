package com.cmpe275.lab2.dao.impl;


import com.cmpe275.lab2.dao.FriendshipDao;
import com.cmpe275.lab2.model.Frienship;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendshipDaoImpl implements FriendshipDao{

	
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Frienship friend) {
		session.getCurrentSession().save(friend);
	}

	@Override
	public void delete(Frienship friend) {
	Query query = session.getCurrentSession().createQuery("delete Frienship where id = :ID");
		query.setParameter("ID", friend.getId());
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Frienship search(Integer id1, Integer id2) {
		Criteria c=session.getCurrentSession().createCriteria(Frienship.class, "o");
		c.add(Restrictions.eq("o.person1.id", id1));
		c.add(Restrictions.eq("o.person2.id", id2));
		List<Frienship> listFriends=c.list();
		if(listFriends!=null && listFriends.size()>0){
			return listFriends.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getAllFriends(int personId) {
		Criteria c=session.getCurrentSession().createCriteria(Frienship.class, "f");
		c.add(Restrictions.eq("f.person1.id", personId));
		if(!c.list().isEmpty() && c.list().size()>0)
		{
			List<Frienship> listFriends = (ArrayList<Frienship>)c.list();
			if(!listFriends.isEmpty())
			{
				List<Integer> friendList = new ArrayList<Integer>();
				for(Frienship friend : listFriends){
					friendList.add(friend.getPerson2().getId());
			}
			return friendList;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
		
}

