package net.perry.forum.service.impl;

import java.util.List;

import net.perry.forum.dao.CategoryDao;
import net.perry.forum.domain.Category;
import net.perry.forum.service.CategoryService;

public class CategoryServiceImlp implements CategoryService{

    private CategoryDao categoryDao = new CategoryDao();
    @Override
    public List<Category> list() {
        return categoryDao.list();
    }
    
    
}
