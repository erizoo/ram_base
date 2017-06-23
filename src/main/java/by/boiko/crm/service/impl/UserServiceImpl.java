package by.boiko.crm.service.impl;

import by.boiko.crm.model.Category;
import by.boiko.crm.model.User;
import by.boiko.crm.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class UserServiceImpl implements UserService {

    private HSSFWorkbook book;

    @Override
    public List<Category> getAllFromPage(int page) throws IOException {
        int numberOfSheets = book.getNumberOfSheets();
        List<Category> linesCategories = new ArrayList<>();
        if (page == 1) {
            for (int i = 0; i <= page * 10; i++) {
                linesCategories.add(new Category(i, book.getSheetName(i)));
            }
        } else {
            for (int i = page * 10; i <= page * 10 + 10; i++) {
                linesCategories.add(new Category(i, book.getSheetName(i)));
            }
        }
        return linesCategories;
    }


    @Override
    public List<Category> getAll() throws IOException {
        int numberOfSheets = book.getNumberOfSheets();
        List<Category> linesCategories = new ArrayList<>();
        for (int i = 0; i <= numberOfSheets - 1; i++) {
            linesCategories.add(new Category(i, book.getSheetName(i)));
        }
        return linesCategories;
    }

    @Override
    public List<User> getTop(int number) throws IOException {
        List<User> lines = new ArrayList<>();
        HSSFSheet sheet;
        try{
            sheet = book.getSheetAt(number);
        } catch (Exception e){
            sheet = book.getSheetAt(0);
        }
        int limit = 0;
        for (Row currentRow : sheet) {
            if (limit >= 20) {
                break;
            }
            String nameCategory = currentRow.getCell(0).getStringCellValue();
            String numberPopular = null;
            if (currentRow.getCell(1).getCellTypeEnum() == CellType.STRING) {
                numberPopular = currentRow.getCell(1).getStringCellValue();
            } else if (currentRow.getCell(1).getCellTypeEnum() == CellType.NUMERIC) {
                numberPopular = String.valueOf(currentRow.getCell(1).getNumericCellValue());
            }
            String nameManufacturer;
            try {
                nameManufacturer = currentRow.getCell(2).getStringCellValue();
            } catch (Exception e) {
                nameManufacturer = "null";
            }

            String nameModel = currentRow.getCell(3).getStringCellValue();
            StringBuilder stringBuilder = new StringBuilder(nameModel);
            nameModel = stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1).toString();
            String skuMarketFromExcel;
            String skuMarket;
            try {
                skuMarketFromExcel = currentRow.getCell(4).getStringCellValue();
                String[] item_one = skuMarketFromExcel.split("/");
                String[] item_two = Arrays.toString(new String[]{item_one[4]}).split("hid");
                String item_three = item_two[0];
                skuMarket = item_three.substring(1, item_three.length() - 2);
            } catch (Exception e) {
                skuMarketFromExcel = "null";
                skuMarket = "null";
            }
            String skuRamFromExcel;
            try {
                skuRamFromExcel = currentRow.getCell(5).getStringCellValue();
            } catch (Exception e) {
                skuRamFromExcel = "null";
            }
            String[] item_market = skuRamFromExcel.split("=");
            String skuRam = item_market[item_market.length - 1];
            lines.add(new User(nameCategory, nameManufacturer, nameModel, skuMarket, skuRam, numberPopular, skuMarketFromExcel));
            limit++;
            System.out.println(skuMarketFromExcel);
        }

        return lines;
    }

    @Override
    public int getCount() throws IOException {
        return book.getNumberOfSheets();
    }

    @Override
    public void downloadFile() throws IOException {
        InputStream is = new URL("http://ram.by/test/market_top_skus/pricelabs-popular-models-0.xls").openStream();
        POIFSFileSystem fs = new POIFSFileSystem(is);
        book = new HSSFWorkbook(fs);
        for (int i = 0; i <= book.getNumberOfSheets() - 1; i++){
            book.getSheetAt(i).removeRow( book.getSheetAt(i).getRow(0));
        }
    }

}
