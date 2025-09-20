package k8Edu.service;

import k8Edu.entity.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
    boolean insert(User user);
    boolean register(String username, String password, String email, String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);

    User getUserByEmail(String email);

    boolean resetPasswordWithoutToken(Long userId, String newPassword);

    String hashPassword(String password);
}