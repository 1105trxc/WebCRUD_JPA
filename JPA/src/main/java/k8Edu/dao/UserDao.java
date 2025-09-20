package k8Edu.dao;

import k8Edu.entity.User;

public interface UserDao {
    User get(String username);
    boolean insert(User user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    User getUserByEmail(String email);
    boolean updatePassword(Long userId, String newHashedPassword);
}