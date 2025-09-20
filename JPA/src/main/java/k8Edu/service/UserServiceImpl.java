package k8Edu.service;

import k8Edu.entity.User;
import k8Edu.dao.UserDao;
import k8Edu.dao.UserDaoImpl;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl(); // DAO phải thao tác với entity User

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.get(username); // DAO trả về entity
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        if (userDao.checkExistEmail(email)) {
            return false;
        }
        if (userDao.checkExistPhone(phone)) {
            return false;
        }

        String hashedPassword = hashPassword(password);
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUserName(username);
        newUser.setFullname(fullname);
        newUser.setPassword(hashedPassword);
        newUser.setPhone(phone);

        return userDao.insert(newUser);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public boolean insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean resetPasswordWithoutToken(Long userId, String newPassword) {
        String hashedPassword = hashPassword(newPassword);
        return userDao.updatePassword(userId, hashedPassword);
    }

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}