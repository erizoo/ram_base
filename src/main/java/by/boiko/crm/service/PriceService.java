package by.boiko.crm.service;

import by.boiko.crm.model.Goods;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Erizo on 30.08.2017.
 */
public interface PriceService {

    void loadPrice() throws IOException;

    List<Goods> parserPrice() throws IOException;

    void save(List<Goods> goods);
}
