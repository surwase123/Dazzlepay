package com.cratas.mpls.common.utility;

import java.util.Comparator;

import com.cratas.mpls.web.dto.MenuDTO;

/**
 * 
 * @author mukesh
 *
 */
public class MenuComparator implements Comparator<MenuDTO>{  
	
	   public int compare(MenuDTO m1, MenuDTO m2){  
		      if(m1.getOrderSequence() == m2.getOrderSequence())  
		    	  return 0;  
		      else if(m1.getOrderSequence() > m2.getOrderSequence())  
		    	  return 1;  
		      else  
		    	  return -1;  
	   }

}
