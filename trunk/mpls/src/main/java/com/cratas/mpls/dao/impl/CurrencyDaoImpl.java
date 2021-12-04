package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.CurrencyQuery;
import com.cratas.mpls.dao.ICurrencyDao;
import com.cratas.mpls.mapper.CurrencyMapper;
import com.cratas.mpls.web.dto.CurrencyDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class CurrencyDaoImpl implements ICurrencyDao {
	
	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	    
	    public int saveCurrency(CurrencyDTO currencyDTO) {
	    	  if(getCurrencyByCode(currencyDTO.getCurrencyCode()) == null){
			      Object[] args = { currencyDTO.getCurrencyCode(), currencyDTO.getCountryName(), currencyDTO.getCurrencyCodeAlpha(), currencyDTO.getCurrencyName(), currencyDTO.getExponent(), currencyDTO.getCreatedBy() };
			      return jdbcTemplate.update(CurrencyQuery.INSERT_CURRENCY, args);
	    	  }
	    	  return 2;
        }
	    
	    public int updateCurrency(CurrencyDTO currencyDTO) {
	    	   Object[] args = { currencyDTO.getCurrencyCode(), currencyDTO.getCountryName(), currencyDTO.getCurrencyCodeAlpha(), currencyDTO.getCurrencyName(), currencyDTO.getExponent(), currencyDTO.getUpdatedby(), currencyDTO.getId() };
		       return jdbcTemplate.update(CurrencyQuery.UPDATE_CURRENCY, args);
	    }
	    
	    public int deleteCurrency(CurrencyDTO currencyDTO) {
			  Object[] args = { currencyDTO.getUpdatedby(), currencyDTO.getId() };
		      return jdbcTemplate.update(CurrencyQuery.DELETE_CURRENCY, args);
	    }
	    
	    public List<CurrencyDTO> getCurrency() {
		      return jdbcTemplate.query(CurrencyQuery.SELECT_CURRENCY, new CurrencyMapper());
	    }
	    
	    public CurrencyDTO getCurrencyByCode(int currencyCode) {
	    	   Object args[] = { currencyCode };
		       List<CurrencyDTO> currencyList = jdbcTemplate.query(CurrencyQuery.SELECT_CURRENCY_BY_COUNTRY_CODE, args, new CurrencyMapper());
		       if(!currencyList.isEmpty()){
		    	   return currencyList.get(0);
		       }
		       return null;
	    }

		public List<CurrencyDTO> getCurrencyList(String countryName) {
			   Object args[] = { countryName };
			   List<CurrencyDTO> currencyList = jdbcTemplate.query(CurrencyQuery.CURRENCY_LIST, args, new CurrencyMapper());
			   return currencyList;
		}
 
}
