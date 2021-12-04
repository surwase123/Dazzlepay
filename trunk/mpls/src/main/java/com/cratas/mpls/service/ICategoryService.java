package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.CategoryDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */

public interface ICategoryService {
	
		List<CategoryDTO> getCategory();
		
		int saveCategory(CategoryDTO categoryDTO, UserDTO userDTO);
	
		int updateCategory(CategoryDTO categoryDTO);
	
		int deleteCategory(CategoryDTO categoryDTO);
	
		CategoryDTO getCategoryByCode(String CategoryCode);
		
		CategoryDTO getCategoryById(int id);
	
}
