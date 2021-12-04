package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.CategoryQuery;
import com.cratas.mpls.dao.ICategoryDao;
import com.cratas.mpls.mapper.CategoryMappper;
import com.cratas.mpls.web.dto.CategoryDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Repository
public class CategoryDaoImpl implements ICategoryDao{

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		public List<CategoryDTO> getCategory() {
			   return jdbcTemplate.query(CategoryQuery.SELECT_CATEGORY, new CategoryMappper());
		}
	
		public int saveCategory(CategoryDTO categoryDTO) {
			   if(getCategoryByName(categoryDTO.getCategoryCode(), categoryDTO.getCategoryName()) == null){
			      Object[] args = { categoryDTO.getCategoryCode(), categoryDTO.getCategoryName(), categoryDTO.getDescription(), categoryDTO.getCreatedBy() };
			      return jdbcTemplate.update(CategoryQuery.INSERT_CATEGORY, args);
		  	   }
		  	   return 2;
		}
	
		public int updateCategory(CategoryDTO categoryDTO) {
			   Object[] args = { categoryDTO.getCategoryCode(), categoryDTO.getCategoryName(), categoryDTO.getDescription(), categoryDTO.getUpdatedby(), categoryDTO.getId() };
		       return jdbcTemplate.update(CategoryQuery.UPDATE_CATEGORY, args);
		}
	
		public int deleteCategory(CategoryDTO categoryDTO) {
			   Object[] args = { categoryDTO.getUpdatedby(), categoryDTO.getId() };
		       return jdbcTemplate.update(CategoryQuery.DELETE_CATEGORY, args);
		}
	
		public CategoryDTO getCategoryByCode(String CategoryCode) {
			   Object args[] = { CategoryCode };
		       List<CategoryDTO> categoryList = jdbcTemplate.query(CategoryQuery.SELECT_CATEGORY_BY_CATEGORY_CODE, args, new CategoryMappper());
		       if(!categoryList.isEmpty()){
		    	   return categoryList.get(0);
		       }
		       return null;
		}
		
		private CategoryDTO getCategoryByName(String CategoryCode, String categoryName) {
			   Object args[] = { CategoryCode, categoryName };
		       List<CategoryDTO> categoryList = jdbcTemplate.query(CategoryQuery.SELECT_CATEGORY_BY_CATEGORY_NAME, args, new CategoryMappper());
		       if(!categoryList.isEmpty()){
		    	   return categoryList.get(0);
		       }
		       return null;
		}

		public CategoryDTO getCategoryById(int id) {
			   Object args[] = {id};
			   List<CategoryDTO> categoryList = jdbcTemplate.query(CategoryQuery.SELECT_CATEGORY_BY_ID, args, new CategoryMappper());
		       if(!categoryList.isEmpty()){
		    	   return categoryList.get(0);
		       }
		       return null;
		}

}
