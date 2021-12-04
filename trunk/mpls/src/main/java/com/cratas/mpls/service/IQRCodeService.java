package com.cratas.mpls.service;

import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IQRCodeService {
	
	   String createQRCode(MerchantTransactionDTO merchantTransaction, CustomerTransactionDTO customerTransaction);

}
