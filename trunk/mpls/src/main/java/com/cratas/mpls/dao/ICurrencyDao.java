package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.CurrencyDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface ICurrencyDao {
	
	   int saveCurrency(CurrencyDTO currencyDTO);
	   
	   int updateCurrency(CurrencyDTO currencyDTO);
	   
	   int deleteCurrency(CurrencyDTO currencyDTO);
	   
	   List<CurrencyDTO> getCurrency();
	   
	   CurrencyDTO getCurrencyByCode(int countryCode);

	   List<CurrencyDTO> getCurrencyList(String countryName);

}
