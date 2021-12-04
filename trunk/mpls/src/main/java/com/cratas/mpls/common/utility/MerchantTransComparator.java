package com.cratas.mpls.common.utility;

import java.util.Comparator;

import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author mukesh
 *
 */
public class MerchantTransComparator implements Comparator<MerchantTransactionDTO> {

	   public int compare(MerchantTransactionDTO m1, MerchantTransactionDTO m2) {
	          if (null != m1 || null != m2) {
	        	  if (m1.getCreatedDate() == null || m2.getCreatedDate() == null)
	        	        return 0;
	        	  return m2.getCreatedDate().compareTo(m1.getCreatedDate());
	          }
	          return 0;
	   }

}
