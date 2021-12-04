package com.cratas.mpls.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.VerificationLogQuery;
import com.cratas.mpls.dao.IVerificationDao;
import com.cratas.mpls.web.dto.VerificationLogDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class VerificationDaoImpl implements IVerificationDao {
	
	   @Autowired
	   private JdbcTemplate jdbcTemplate;
	   
	   public int saveVerificationLog(VerificationLogDTO verificationLog) {
		      KeyHolder keyHolder = new GeneratedKeyHolder();
		      jdbcTemplate.update(connection -> {
	                PreparedStatement ps = connection.prepareStatement(VerificationLogQuery.INSERT_VERIFICATION_LOG,Statement.RETURN_GENERATED_KEYS);
	                ps.setString(1, verificationLog.getEmailId());
	                ps.setString(2, verificationLog.getMobileNumber());
	                ps.setString(3, verificationLog.getVerificationCode());
	                ps.setString(4, verificationLog.getUserType());
	                return ps;
	         }, keyHolder); 
	         return keyHolder.getKey().intValue();
	   }
	   
	   public int updateVerificationLog(int id) {
			  Object args[] = { id };
			  return jdbcTemplate.update(VerificationLogQuery.UPDATE_VERIFICATION_LOG, args);
	   }
	   
	   public VerificationLogDTO getVerificationLog(int id) {
		      Object args[] = { id };
			  List<VerificationLogDTO> verificationList = jdbcTemplate.query(VerificationLogQuery.SELECT_VERIFICATION_LOG, args, new BeanPropertyRowMapper<VerificationLogDTO>(VerificationLogDTO.class));
			  if(!verificationList.isEmpty()){
				   return verificationList.get(0);
			  }
			  return null;
	   }

}
