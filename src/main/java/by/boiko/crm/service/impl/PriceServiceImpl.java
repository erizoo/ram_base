package by.boiko.crm.service.impl;

import by.boiko.crm.dao.GoodsDao;
import by.boiko.crm.model.Goods;
import by.boiko.crm.service.PriceService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    @Autowired
    private GoodsDao goodsDao;

    private static final Logger log = Logger.getLogger(String.valueOf(PriceServiceImpl.class));
    private HSSFWorkbook book;

    @Override
    public void loadPrice() throws IOException {
        InputStream is = new URL("http://ram.by/price/RAM.BY_Price.xls").openStream();
        POIFSFileSystem fs = new POIFSFileSystem(is);
        book = new HSSFWorkbook(fs);
        System.out.println(book.getSheetAt(0).getRow(3).getCell(1));
    }

    @Override
    public List<Goods> parserPrice() throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream("F:\\ram_base\\test.xlsx"));
        XSSFSheet sheet = myExcelBook.getSheetAt(0);
        System.out.println(myExcelBook.getSheetAt(0).getRow(0).getCell(1));
        List<Goods> goodsList = new ArrayList<>();
        int category = 0;
        for (Row row : sheet) {
            int sku = (int) row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            double price = row.getCell(2).getNumericCellValue();;
            if (name.contains("Игровые наборы")) {
                category = 1;
            }
            if (name.contains("Радиоуправляемые модели")) {
                category = 2;
            }
            if (name.contains("Разное")) {
                category = 3;
            }
            if (name.contains("Мягкие игрушки/Куклы")) {
                category = 4;
            }
            if (name.contains("Настольные игры")) {
                category = 5;
            }
            if (name.contains("Конструкторы LEGO")) {
                category = 6;
            }
            goodsList.add(new Goods(sku, name, price, category));
        }
        return goodsList;
    }

    @Override
    public void save(List<Goods> goods) {
        goodsDao.save(goods);
    }


}
