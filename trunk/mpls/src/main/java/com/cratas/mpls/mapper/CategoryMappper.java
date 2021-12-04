package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.CategoryDTO;

/**
 * 
 * @author bhupendra
 *
 */

public class CategoryMappper implements RowMapper<CategoryDTO>{

		@Override
		public CategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			   CategoryDTO categoryDTO = new CategoryDTO();
			   categoryDTO.setId(rs.getInt("id"));
			   categoryDTO.setCategoryCode(rs.getString("categoryCode"));
			   categoryDTO.setCategoryName(rs.getString("categoryName"));
			   categoryDTO.setDescription(rs.getString("description"));
			   categoryDTO.setCreatedBy(rs.getString("createdBy"));
			   categoryDTO.setUpdatedby(rs.getString("updatedBy"));
			   return categoryDTO;
		}

}
