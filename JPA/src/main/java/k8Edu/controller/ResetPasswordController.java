package k8Edu.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import k8Edu.service.UserService;
import k8Edu.service.UserServiceImpl;

import java.io.IOException;

@WebServlet("/resetPassword")
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false); // Không tạo session mới nếu chưa có
        if (session == null || session.getAttribute("resetUserId") == null) {
            request.setAttribute("alert", "Phiên đặt lại mật khẩu không hợp lệ hoặc đã hết hạn. Vui lòng thử lại.");
            response.sendRedirect(request.getContextPath() + "/forgotPassword");
            return;
        }

        request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("resetUserId") == null) {
            request.setAttribute("alert", "Phiên đặt lại mật khẩu không hợp lệ hoặc đã hết hạn. Vui lòng thử lại.");
            response.sendRedirect(request.getContextPath() + "/forgotPassword");
            return;
        }

        Long userId = null;
        Object userIdObj = session.getAttribute("resetUserId");
        if (userIdObj instanceof Long) {
            userId = (Long) userIdObj;
        } else if (userIdObj instanceof Integer) {
            userId = ((Integer) userIdObj).longValue();
        } else if (userIdObj instanceof String) {
            userId = Long.valueOf((String) userIdObj);
        }
        // Nếu vẫn null, báo lỗi
        if (userId == null) {
            request.setAttribute("alert", "Lỗi hệ thống: Không xác định user cần đặt lại mật khẩu.");
            request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
            return;
        }

        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (newPassword == null || newPassword.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            request.setAttribute("alert", "Vui lòng nhập mật khẩu mới và xác nhận mật khẩu.");
            request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("alert", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
            request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
            return;
        }

        boolean success = userService.resetPasswordWithoutToken(userId, newPassword);

        if (success) {
            session.removeAttribute("resetUserId");
            request.setAttribute("success", "Mật khẩu của bạn đã được đặt lại thành công! Vui lòng đăng nhập với mật khẩu mới.");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            request.setAttribute("alert", "Có lỗi xảy ra khi đặt lại mật khẩu. Vui lòng thử lại.");
            request.getRequestDispatcher("/resetPassword.jsp").forward(request, response);
        }
    }
}