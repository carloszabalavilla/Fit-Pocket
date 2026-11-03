package edu.fpdual.webservice.service;

import edu.fpdual.webservice.model.persistence.connector.MySQLConnector;
import edu.fpdual.webservice.model.persistence.dao.Category;
import edu.fpdual.webservice.model.persistence.manager.CategoryManager;
import edu.fpdual.webservice.model.persistence.manager.impl.CategoryManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryService {
    private final CategoryManager categoryManager;

    //@TODO Añadir javadoc
    public CategoryService(CategoryManagerImpl categoryManager) {

        this.categoryManager = categoryManager;
    }

    public List<Category> findAllCategories() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return categoryManager.findAll(con);
        }
    }
    public List<Category> findAllCategoriesByUserId(int userId) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return categoryManager.findAllById(con, "userId", userId);
        }
    }

    public Category findByCategoryName(String categoryName) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return categoryManager.findBy(con, "categoryName", categoryName);
        }
    }

    public boolean createCategory(Category category) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return categoryManager.create(con, category);
        }
    }

    public boolean updateCategory(Category category) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return categoryManager.update(con, category);
        }
    }

    public boolean deleteCategory(Category category) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return categoryManager.delete(con, category);
        }
    }
}
