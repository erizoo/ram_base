package by.boiko.crm.service;


import by.boiko.crm.model.Goods;

import java.util.List;

public interface SalesService {

    List<Goods> getGoods(int category);
}
