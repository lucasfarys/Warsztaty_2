package programmingSchool;

import org.mindrot.jbcrypt.BCrypt;
import programmingSchoolDao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private int user_group_id;
    private List<User> users = new ArrayList<>();

    public User() {
    }

    public User(String userName, String email, String password, int user_group_id) {
        this.userName = userName;
        this.email = email;
        this.hashPassword(password);
        this.user_group_id = user_group_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(int user_group_id) {
        this.user_group_id = user_group_id;
    }

    public void findAllByGroupId(String group_name){
        UserDao userDao = new UserDao();
        users = userDao.findAllByGroupId(group_name);
    }
}