package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.ICategoryDao;
import com.cratas.mpls.service.ICategoryService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.web.dto.CategoryDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */

@Service
public class CategoryServiceImpl implements ICategoryService{

		@Autowired
	    private ICategoryDao categoryDao;
		
		@Autowired
		private SaveUserNotificationThread saveUserNotificationThread;
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
		
		@Autowired
		private IMPLSService mplsService;
		
		public List<CategoryDTO> getCategory() {
			   return categoryDao.getCategory();
		}
	
		public int saveCategory(CategoryDTO categoryDTO, UserDTO userDTO) {
			   int isInsert = categoryDao.saveCategory(categoryDTO);
			   if(isInsert == 1 && userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
				    CategoryDTO category = categoryDao.getCategoryByCode(categoryDTO.getCategoryCode());
					NotificationDTO notification = mplsService.createUserNotificationObj(String.valueOf(category.getId()), categoryDTO.getMenuName(), userDTO);
					mplsService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
					saveUserNotificationThread.setData(notification,userDTO);
					taskExecutor.execute(saveUserNotificationThread);
					return isInsert;
				}
				return isInsert;
		}
	
		public int updateCategory(CategoryDTO categoryDTO) {
			   return categoryDao.updateCategory(categoryDTO);
		}
	
		public int deleteCategory(CategoryDTO categoryDTO) {
			   return categoryDao.deleteCategory(categoryDTO);
		}
	
		public CategoryDTO getCategoryByCode(String CategoryCode) {
			   return categoryDao.getCategoryByCode(CategoryCode);
		}
	
		public CategoryDTO getCategoryById(int id) {
			   return categoryDao.getCategoryById(id);
		}

}
