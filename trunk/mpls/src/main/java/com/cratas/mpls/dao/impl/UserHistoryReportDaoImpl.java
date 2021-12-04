/**
 * 
 */
package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.query.UserHistoryReportQuery;
import com.cratas.mpls.dao.IUserHistoryReportDao;
import com.cratas.mpls.mapper.UserHistoryMapper;
import com.cratas.mpls.mapper.UserMapper;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserHistoryDTO;

/**
 * @author bhupendra
 *
 */
@Repository
public class UserHistoryReportDaoImpl implements IUserHistoryReportDao{
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		public List<UserDTO> getUsers(UserDTO userDTO) {
			   if(userDTO.getGroupDTO().getGroupType().equalsIgnoreCase(Constants.ADMIN)) {
					  Object args[] = { userDTO.getSystemId()};
					  String query = UserHistoryReportQuery.GET_USER_LIST;
				      return jdbcTemplate.query(query, args, new UserMapper());
				}else if(userDTO.getGroupDTO().getGroupType().equals(Constants.CHECKER)) {
					  Object args[] = { userDTO.getSystemId(), userDTO.getLoginId(), userDTO.getLoginId()};
					  String query = UserHistoryReportQuery.GET_USER_LIST + " and managerId = ? or loginId = ?";
				      return jdbcTemplate.query(query, args, new UserMapper());	
				}else if(userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)){
					  Object args[] = { userDTO.getSystemId(), userDTO.getLoginId()};
					  String query = UserHistoryReportQuery.GET_USER_LIST + " and loginId = ?";
				      return jdbcTemplate.query(query, args, new UserMapper());
				}else{
					  Object args[] = { userDTO.getSystemId()};
					  String query = UserHistoryReportQuery.GET_USER_LIST;
				      return jdbcTemplate.query(query, args, new UserMapper());
				}
		}
	
		public List<UserHistoryDTO> getUserHistorydetails(String loginId) {
			   Object args[] = { loginId};
			   return jdbcTemplate.query(UserHistoryReportQuery.USER_HISTORY_DETAILS_BY_ID, args, new UserHistoryMapper());
		}

}
