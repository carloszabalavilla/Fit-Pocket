package edu.fpdual.webapplication.service;

import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.client.CategoryClient;
import edu.fpdual.webapplication.dto.Category;

import java.util.List;

@Model(type = "Service", version = "1.0", date = "01/06/2023")
public class CategoryService {

    private final CategoryClient categoryClient;

    public CategoryService(CategoryClient categoryClient) {
        this.categoryClient = categoryClient;
    }

    /**
     * Proves if the service is available.
     *
     * @return the following string : "Service online"
     */
    public String ping() {
        return categoryClient.ping();
    }

    /**
     * Find all categories existing.
     *
     * @return a {@link List} of {@link Category}.
     */
    public List<Category> getAllCategories() {
        return categoryClient.get();
    }

    /**
     * Find all categories existing by the user id.
     *
     * @param userId the id.
     * @return a {@link List} of {@link Category}.
     */
    public List<Category> getAllCategoriesByUserId(int userId) {
        return categoryClient.getAllBy(userId);
    }


    public Category getCategory(String categoryName) {
        return categoryClient.get(categoryName);
    }

    public boolean updateCategory(Category category) {
        return categoryClient.put(category);
    }

    public boolean createCategory(Category category) {
        return categoryClient.post(category);
    }

    public boolean deleteCategory(Category category) {
        return categoryClient.delete(category);
    }
}
