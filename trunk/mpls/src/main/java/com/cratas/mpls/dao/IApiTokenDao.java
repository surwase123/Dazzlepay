package com.cratas.mpls.dao;

import com.cratas.mpls.web.dto.ApiTokenDTO;

/**
 * 
 * @author Sunil
 *
 */

public interface IApiTokenDao {

	   int saveToken(ApiTokenDTO apiTokenDTO);

}
