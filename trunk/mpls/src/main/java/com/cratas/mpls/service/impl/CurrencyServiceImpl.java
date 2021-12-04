package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.ICurrencyDao;
import com.cratas.mpls.service.ICurrencyService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.web.dto.CurrencyDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class CurrencyServiceImpl implements ICurrencyService {
	
	
	    @Autowired
	    private ICurrencyDao currencyDao;
	    
	    @Autowired
		private SaveUserNotificationThread saveUserNotificationThread;
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
		
		@Autowired
		private IMPLSService reconService;
	    
	    public int saveCurrency(CurrencyDTO currencyDTO, UserDTO userDTO){
	    	   int isInsert = currencyDao.saveCurrency(currencyDTO);
	    	   if(isInsert == 1 && userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
	    		    CurrencyDTO currency = currencyDao.getCurrencyByCode(currencyDTO.getCurrencyCode());
					NotificationDTO notification = reconService.createUserNotificationObj(String.valueOf(currency.getId()), currencyDTO.getMenuName(), userDTO);
					reconService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
					saveUserNotificationThread.setData(notification,userDTO);
					taskExecutor.execute(saveUserNotificationThread);
					return isInsert;
				}
				return isInsert;	
	    }
		   
	    public int updateCurrency(CurrencyDTO currencyDTO){
			   return currencyDao.updateCurrency(currencyDTO);
		}
		   
	    public int deleteCurrency(CurrencyDTO currencyDTO){
	    	   return currencyDao.deleteCurrency(currencyDTO);
	    }
		   
	    public List<CurrencyDTO> getCurrency(){
	    	   return currencyDao.getCurrency();
	    }
	    
	    public CurrencyDTO getCurrencyByCode(int countryCode){
	    	   return currencyDao.getCurrencyByCode(countryCode);
	    }

		public List<CurrencyDTO> getCurrencyList(String countryName) {
			   return currencyDao.getCurrencyList(countryName);
		}


}
