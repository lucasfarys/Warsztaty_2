package programmingSchoolDao;

import programmingSchool.User;
import programmingSchoolDao.UserDao;

public class App {
    public static void main(String[] args) {
        User user =
                new User("andrzej", "mail", "haslo", 2);

        UserDao userDao = new UserDao();
        User userFromDb = userDao.create(user);

        User user1 = userDao.read(2);

        user1.setUserName("nowe imie");
        userDao.update(user1);

        userDao.delete(23);

    }
}