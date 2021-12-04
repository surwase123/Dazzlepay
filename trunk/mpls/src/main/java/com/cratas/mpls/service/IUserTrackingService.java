package com.cratas.mpls.service;

import com.cratas.mpls.web.dto.UserTrackingRequestDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IUserTrackingService {

		int addUserTrackingDetail(UserTrackingRequestDTO userTrackingDTO);
		int addUserCompleteTrackingDetail(UserTrackingRequestDTO userTrackingDTO);

}
