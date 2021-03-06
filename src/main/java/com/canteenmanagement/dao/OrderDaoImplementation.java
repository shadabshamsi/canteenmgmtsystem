package com.canteenmanagement.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.canteenmanagement.pojos.Order;

@Repository
public class OrderDaoImplementation extends CanteenDaoImplementation<Order> implements OrderDao {
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public Integer add(Order order) {
		return (Integer) sessionFactory.getCurrentSession().save(order);
	}

	@Override
	public Order update(Order order) {
		sessionFactory.getCurrentSession().update(order);
		return order;
	}

	@Override
	public Order delete(Order order) {
		sessionFactory.getCurrentSession().delete(order);
		return order;
	}

	@Override
	public Order delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Order order = (Order) session.get(Order.class, id);
		session.delete(order);	
		return order;
	}
	
	@Override
	public List<Order> get() {
		String jpql = "SELECT o from Order o";
		Session session = sessionFactory.getCurrentSession();
		List<Order> list = session.createQuery(jpql, Order.class).getResultList();
		return list;
	}

	@Override
	public Order get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Order order = (Order) session.get(Order.class, id);
		return order;
	}

	@Override
	public Order getOrderByCoupon(String couponCode) {
		String jpql = "Select o from Order o where o.couponCode=:couponCode";
		Order order;
		try {
			order = sessionFactory.getCurrentSession().createQuery(jpql,Order.class).setParameter("couponCode", couponCode).getSingleResult();
		}
		catch(NoResultException e) {
			order = null;
		}
		return order;
	}
}
