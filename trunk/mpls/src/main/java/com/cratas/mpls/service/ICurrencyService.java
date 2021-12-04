package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.CurrencyDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface ICurrencyService {
	
	   int saveCurrency(CurrencyDTO currencyDTO, UserDTO userDTO);
	   
	   int updateCurrency(CurrencyDTO currencyDTO);
	   
	   int deleteCurrency(CurrencyDTO currencyDTO);
	   
	   List<CurrencyDTO> getCurrency();
	   
	   CurrencyDTO getCurrencyByCode(int countryCode);

	   List<CurrencyDTO> getCurrencyList(String countryName);


}
