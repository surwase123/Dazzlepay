package com.cratas.mpls.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.dao.IUserTrackingDao;
import com.cratas.mpls.service.IUserTrackingService;
import com.cratas.mpls.web.dto.UserTrackingRequestDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class UserTrackingServiceImpl implements IUserTrackingService{

		@Autowired
		IUserTrackingDao userTrackingDao;
		
		public int addUserTrackingDetail(UserTrackingRequestDTO userTrackingDTO){
			   return userTrackingDao.addUserTrackingDetail(userTrackingDTO);
		}
		
		public int addUserCompleteTrackingDetail(UserTrackingRequestDTO userTrackingDTO){
			   return userTrackingDao.addUserCompleteTrackingDetail(userTrackingDTO);
		}
}
