package k8Edu.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "NVARCHAR(500)")
    private String name;

    @Column(name = "icon", columnDefinition = "NVARCHAR(500)")
    private String icon; // Thêm trường icon để lưu đường dẫn ảnh

    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
}