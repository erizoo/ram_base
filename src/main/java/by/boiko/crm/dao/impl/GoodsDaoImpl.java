package by.boiko.crm.dao.impl;


import by.boiko.crm.dao.GoodsDao;
import by.boiko.crm.model.Goods;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsDaoImpl implements GoodsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("uncheked")
    public List loadGoods(int category) {
        return sessionFactory.getCurrentSession().createQuery("from Goods u where category = :category").setParameter("category", category).list();
    }

    @Override
    public void save(List<Goods> goods) {

        for (Goods list: goods) {
            Goods goodsModel = new Goods();
            goodsModel.setSku(list.getSku());
            goodsModel.setName(list.getName());
            goodsModel.setPrice(list.getPrice());
            goodsModel.setCategory(list.getCategory());
            sessionFactory.getCurrentSession().save(goodsModel);
        }

    }

    @Override
    public int getCount(int category) {
        return Math.toIntExact((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from Goods u where category = :category").setParameter("category", category).uniqueResult());
    }
}
