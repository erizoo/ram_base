package by.boiko.crm.service.impl;

import by.boiko.crm.dao.GoodsDao;
import by.boiko.crm.model.Goods;
import by.boiko.crm.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SalesServiceImpl implements SalesService {

    private final GoodsDao goodsDao;

    @Autowired
    public SalesServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public List<Goods> getGoods(int category) {
        return goodsDao.loadGoods(category);
    }
}
