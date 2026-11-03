package edu.fpdual.webservice.model.persistence.manager;

import edu.fpdual.webservice.model.persistence.dao.Category;

import java.sql.Connection;
import java.util.List;

public interface CategoryManager extends Manager<Category> {

    List<Category> findAllById(Connection con, String fieldName, int value);
}
