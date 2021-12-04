package com.cratas.mpls.dao;

import com.cratas.mpls.web.dto.UserTrackingRequestDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IUserTrackingDao {

		int addUserTrackingDetail(UserTrackingRequestDTO userTrackingDTO);
		int addUserCompleteTrackingDetail(UserTrackingRequestDTO userTrackingDTO);
}
