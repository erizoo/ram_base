package by.boiko.crm.service;

import by.boiko.crm.model.Category;
import by.boiko.crm.model.User;

import java.io.IOException;
import java.util.List;


public interface UserService {

    List<Category> getAll() throws IOException;

    List<User> getTop(int number) throws IOException;

    int getCount() throws IOException;

    List<Category> getAllFromPage(int page) throws IOException;

    void downloadFile() throws IOException;
}
