package by.boiko.crm.dao.impl;


import by.boiko.crm.dao.GoodsDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsDaoImpl implements GoodsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List loadGoods() {
        return sessionFactory.getCurrentSession().createQuery("from Goods").list();
    }
}
