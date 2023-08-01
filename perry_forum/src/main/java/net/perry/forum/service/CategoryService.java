package net.perry.forum.service;

import java.util.List;

import net.perry.forum.domain.Category;

public interface CategoryService {
    /**
     * 全部分类
     * @return
     */
    List<Category> list();
}
