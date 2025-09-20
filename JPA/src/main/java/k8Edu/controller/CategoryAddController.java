package k8Edu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import k8Edu.entity.Category; // Sửa lại import: dùng entity
import k8Edu.service.CategoryService;
import k8Edu.service.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig(
        fileSizeThreshold = 2 * 1024 * 1024,   // 2MB
        maxFileSize = 10 * 1024 * 1024,        // 10MB
        maxRequestSize = 20 * 1024 * 1024      // 20MB
)
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService cateService = new CategoryServiceImpl();

    private static final String UPLOAD_BASE_DIR = "D:/upload";
    private static final String UPLOAD_CATEGORY_DIR = UPLOAD_BASE_DIR + "/category";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/views/admin/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Category category = new Category(); // Dùng entity

        try {
            System.out.println("CategoryAddController: doPost started.");

            String name = req.getParameter("name");
            System.out.println("CategoryAddController: Category name from form = " + name);

            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Category name cannot be empty.");
            }
            category.setName(name);

            Part filePart = req.getPart("icon");

            String uploadedIconPath = null;

            if (filePart != null && filePart.getSize() > 0 && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String ext = "";
                int index = originalFileName.lastIndexOf(".");
                if (index > 0) {
                    ext = originalFileName.substring(index);
                }
                String fileName = System.currentTimeMillis() + ext;

                File uploadDir = new File(UPLOAD_CATEGORY_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                File targetFile = new File(uploadDir, fileName);
                filePart.write(targetFile.getAbsolutePath());
                uploadedIconPath = "category/" + fileName;
            }
            category.setIcon(uploadedIconPath);

            cateService.insert(category);

            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            return;

        } catch (IllegalArgumentException e) {
            req.setAttribute("alert", "Lỗi dữ liệu: " + e.getMessage());
            req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);
        } catch (ServletException e) {
            req.setAttribute("alert", "Lỗi cấu hình Servlet/Upload: " + e.getMessage());
            req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);
        } catch (IOException e) {
            req.setAttribute("alert", "Lỗi I/O khi xử lý file: " + e.getMessage());
            req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("alert", "Lỗi hệ thống không xác định: " + e.getMessage());
            req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);
        }
    }
}