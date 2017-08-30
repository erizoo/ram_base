package by.boiko.crm.dao;


import by.boiko.crm.model.Goods;

import java.util.List;

public interface GoodsDao {

    List loadGoods(int category);

    void save(List<Goods> goods);

    int getCount(int category);
}
