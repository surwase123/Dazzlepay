package com.cratas.mpls.common.utility;

import java.util.Comparator;

import com.cratas.mpls.web.dto.SubMenuDTO;

/**
 * 
 * @author mukesh
 *
 */
public class SubMenuComparator implements Comparator<SubMenuDTO> {

	   public int compare(SubMenuDTO s1, SubMenuDTO s2) {
	          if (null != s1 || null != s2) {
	             if (s1.getOrderSequence() == s2.getOrderSequence())
	                 return 0;
	             else if (s1.getOrderSequence() > s2.getOrderSequence())
	                 return 1;
	             else
	                 return -1;
	         }
	         return 0;
	   }

}
