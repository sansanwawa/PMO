package sands.dao.interfaces;

import model.User;
import java.util.ArrayList;

public interface UserDAO {

    public void save(User user);

    public void delete(User user);

    public ArrayList<User> list(int offset);

    public void orderBy(String field, String Type);

    public void setMaxResults(int i);

    public User getById(long id);
}
