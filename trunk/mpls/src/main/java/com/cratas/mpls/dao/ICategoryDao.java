package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.CategoryDTO;

/**
 * 
 * @author bhupendra
 *
 */

public interface ICategoryDao {
	
		List<CategoryDTO> getCategory();
	
		int saveCategory(CategoryDTO categoryDTO);
	
		int updateCategory(CategoryDTO categoryDTO);
	
		int deleteCategory(CategoryDTO categoryDTO);
	
		CategoryDTO getCategoryByCode(String CategoryCode);

		CategoryDTO getCategoryById(int id);
	
}
