//package by.boiko.crm;
//
//import by.boiko.crm.model.User;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class AppMain {
//
//    public static void main(String[] args) {
//        try {
//            URL url = new URL("http://ram.by/test/market_top_skus/pricelabs-popular-models-0.xls");
//            InputStream in = url.openStream();
//            POIFSFileSystem fs = new POIFSFileSystem(in);
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//            String nameWorkbook = wb.getSheetName(0);
//            System.out.println(nameWorkbook);
//            int numberOfSheets = wb.getNumberOfSheets();
////            List<String> lines = new ArrayList<>();
////            for (int i = 0; i <= 2 - 1; i++) {
////                HSSFSheet sheet = wb.getSheetAt(i);
////                HSSFRow removingRow = sheet.getRow(0);
////                sheet.removeRow(removingRow);
////                for (Row currentRow : sheet) {
////                    String nameCategory = currentRow.getCell(0).getStringCellValue();
////                    String numberPopular = null;
////                    if (currentRow.getCell(1).getCellTypeEnum() == CellType.STRING) {
////                        numberPopular = currentRow.getCell(1).getStringCellValue();
////                    } else if (currentRow.getCell(1).getCellTypeEnum() == CellType.NUMERIC) {
////                        numberPopular = String.valueOf(currentRow.getCell(1).getNumericCellValue());
////                    }
////                    String nameManufacturer;
////                    try {
////                        nameManufacturer = currentRow.getCell(2).getStringCellValue();
////                    } catch (Exception e) {
////                        nameManufacturer = "null";
////                    }
////                    String nameModel = currentRow.getCell(3).getStringCellValue();
////                    StringBuilder stringBuilder = new StringBuilder(nameModel);
////                    nameModel = stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1).toString();
////                    String skuMarketFromExcel = currentRow.getCell(4).getStringCellValue();
////                    String[] item_one = skuMarketFromExcel.split("/");
////                    String[] item_two = Arrays.toString(new String[]{item_one[4]}).split("hid");
////                    String item_three = item_two[0];
////                    String skuMarket = item_three.substring(1, item_three.length() - 2);
////                    String skuRamFromExcel;
////                    try {
////                        skuRamFromExcel = currentRow.getCell(5).getStringCellValue();
////                    } catch (Exception e) {
////                        skuRamFromExcel = "null";
////                    }
////                    String[] item_market = skuRamFromExcel.split("=");
////                    String skuRam = item_market[item_market.length - 1];
////                    lines.add(nameCategory + " " + String.valueOf(numberPopular) + " " + nameManufacturer + " " + nameModel + " " + skuMarket + " " + skuRam);
////                    Path file = Paths.get("the-file-name.txt");
////                    Files.write(file, lines, Charset.forName("UTF-8"));
////                }
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
