package by.boiko.crm.service.impl;

import by.boiko.crm.model.*;
import by.boiko.crm.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private HSSFWorkbook book;
    private final String MAIL_STORE_TYPE = "pop.gmail.com";
    private final String HOST = "pop3";
    private final String USERNAME = "erizosashka@gmail.com";
    private final String PASSWORD = "Alex20968";
    private List<Email> emailList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    private List<Product> productList = new ArrayList<>();

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
    public List<Order> getEmails() {
        return check(MAIL_STORE_TYPE, MAIL_STORE_TYPE, USERNAME, PASSWORD);
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
        try {
            sheet = book.getSheetAt(number);
        } catch (Exception e) {
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
        for (int i = 0; i <= book.getNumberOfSheets() - 1; i++) {
            book.getSheetAt(i).removeRow(book.getSheetAt(i).getRow(0));
        }
    }

    private List<Order> check(String host, String mail_store_type, String username, String password) {
        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, username, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);
            Object content = null;
            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                int emailNumber = i + 1;
                String emailSubject = message.getSubject();
                String emailFrom = String.valueOf(message.getFrom()[0]);
                String[] arrayString = emailFrom.split("<");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(arrayString[1].substring(0, arrayString[1].length() - 1));
                emailFrom = String.valueOf(stringBuilder);
                content = message.getContent();
                String context = getTextFromMimeMultipart((MimeMultipart) content);
                emailList.add(new Email(emailNumber, emailSubject, emailFrom, context));
                String lines[] = emailList.get(i).getSubject().split("\\r?\\n");
                orderList.add(new Order(nameToFormat(lines[5]), phoneNumberFormat(lines[6]), emailToFormat(lines[7]),
                        addressToFormat(lines[8]), orderToFormat(lines)));
            }
            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    private String nameToFormat(String line) {
        String[] lines = line.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("None").append(" ").append(lines[2]);
        System.out.println(stringBuilder);
        return String.valueOf(stringBuilder);
    }

    private String addressToFormat(String line) {
        String[] lines = line.split(" ");
        final List<String> list = new ArrayList<String>();
        Collections.addAll(list, lines);
        list.remove("Адрес");
        list.remove("доставки:");
        lines = list.toArray(new String[list.size()]);
        String item = Arrays.toString(lines);
        String result = item.substring(1, item.length() - 1);
        System.out.println(result);
        return result;
    }

    private String emailToFormat(String line) {
        String[] lines = line.split(" ");
        System.out.println(lines[1]);
        return lines[1];
    }

    private String priceToFormat(String line) {
        String[] lines = line.split(" ");
        System.out.println(lines[5]);
        return lines[5];
    }

    private List<Product> orderToFormat(String[] line) {
        productList.clear();
        List<String> lines = Arrays.asList(line);
        List<String> list = lines.stream().filter(p -> p.contains("1.") | p.contains("2.") | p.contains("3.") | p.contains("4.")).collect(Collectors.toList());
        String[] items = String.valueOf(list).split(",");
        List<String> nameItemList = new ArrayList<>();
        List<String> priceItemList = new ArrayList<>();
        List<String> amountItemList = new ArrayList<>();
        List<String> nameItemListTest = new ArrayList<>();
        List<String> amountItemListTest = new ArrayList<>();
        List<String> priceItemListTest = new ArrayList<>();
        for (int i = 0; i <= items.length - 1; i++) {
            String[] firstItem = items[i].split("//");
            nameItemList.add(firstItem[0]);
            amountItemList.add(firstItem[1]);
            priceItemList.add(firstItem[2]);
        }
        for (int i = 0; i <= items.length - 1; i++) {
            String[] priceString = priceItemList.get(i).split(" ");
            priceItemListTest.add(priceString[2]);
            String[] amountString = amountItemList.get(i).split(" ");
            amountItemListTest.add(amountString[2]);
            String[] nameString = nameItemList.get(i).split(",");
            String[] nameItem = nameString[0].split("\\.");
            nameItemListTest.add(nameItem[1]);
            productList.add(new Product(nameItemListTest.get(i), priceItemListTest.get(i), amountItemListTest.get(i)));
        }
        return productList;
    }

    private String phoneNumberFormat(String line) {
        String[] lines = line.split(" ");
        String[] item = lines[4].split("-");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lines[2].substring(1, 4)).append(" ").append(lines[3].substring(1, 3)).append(" ").append(item[0]).append(item[1]).append(item[2]);
        System.out.println(stringBuilder);
        return String.valueOf(stringBuilder);
    }

    private String dateToFormat(String line) {
        String[] lines = line.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lines[1]).append("/").append("0").append(LocalDate.now().getMonth().getValue()).append("/").append(lines[3]).append(" ").append(lines[5]);
        System.out.println(stringBuilder);
        return String.valueOf(stringBuilder);
    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append("\n").append(bodyPart.getContent());
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append("\n").append(org.jsoup.Jsoup.parse(html).text());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }

    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

}
