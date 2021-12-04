package com.cratas.mpls.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cratas.mpls.common.query.CityQuery;
import com.cratas.mpls.common.query.CountryQuery;
import com.cratas.mpls.common.query.CustomerQuery;
import com.cratas.mpls.common.query.LoyaltyCardQuery;
import com.cratas.mpls.dao.ILoyaltyCardDao;
import com.cratas.mpls.mapper.CityMapper;
import com.cratas.mpls.mapper.CountryMapper;
import com.cratas.mpls.mapper.GeneratedLoyaltyNumberMapper;
import com.cratas.mpls.mapper.LoyaltyNumberMapper;
import com.cratas.mpls.web.dto.CityDTO;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.LoyaltyNumberDTO;
import com.cratas.mpls.web.dto.LoyaltyNumberGenerationRequestDTO;
import com.cratas.mpls.web.dto.MerchantPlanDTO;

@Repository
public class LoyaltyCardDaoImpl implements ILoyaltyCardDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final int INSERT_BATCH_SIZE = 2;
	private int fileId;
	
	@Override
	public int insertLoyaltyNumbers(Set<LoyaltyNumberDTO> numberSet) { // TODO Auto-generated method stub
	 List<Object[]>	batchArgList = new ArrayList<Object[]>();
		 StopWatch timer = new StopWatch();
		 LoyaltyNumberGenerationRequestDTO loyaltyNumberFileDTO=new LoyaltyNumberGenerationRequestDTO();
		
		 timer.start();
		for (LoyaltyNumberDTO number : numberSet) {
			fileId=number.getGenrationId();
			Object[] objectArray = { number.getLoyaltyNumber(),number.getGenrationId() };
			batchArgList.add(objectArray);
		}
		 loyaltyNumberFileDTO.setId(fileId);
		 loyaltyNumberFileDTO.setStatus("P");
		 this.updateFileStatus(loyaltyNumberFileDTO);
		int count[] = jdbcTemplate.batchUpdate(LoyaltyCardQuery.INSERT_LOYALTYNUMBERS, batchArgList);
		timer.stop(); 
		return count.length;
	}
	
	@Override
	public int updateLoyaltyNumbers(Set<LoyaltyNumberDTO> numberSet) { 
	 List<Object[]>	batchArgList = new ArrayList<Object[]>();
		 StopWatch timer = new StopWatch();
		 LoyaltyNumberGenerationRequestDTO loyaltyNumberFileDTO=new LoyaltyNumberGenerationRequestDTO();
		
		 timer.start();
		for (LoyaltyNumberDTO number : numberSet) {
			Object[] objectArray = { number.getmId(),number.getRequestId(),number.getStatus(),number.getIsActive(),number.getId()};
			batchArgList.add(objectArray);
		}
		int count[] = jdbcTemplate.batchUpdate(LoyaltyCardQuery.ALLOCATE_LOYALTYNUMBERS_TO_MERCHANT, batchArgList);
		timer.stop(); 
		return count.length;
	}
	
	public LoyaltyNumberDTO checkLoyaltyNumberExist(String number) {
		Object args[] = {number};
		   List<LoyaltyNumberDTO> numberList = jdbcTemplate.query(LoyaltyCardQuery.CHECK_LOYALTYNUMBER, args,new BeanPropertyRowMapper<LoyaltyNumberDTO>(LoyaltyNumberDTO.class) );
		   if (!numberList.isEmpty()) {
				return numberList.get(0);
		   }
		   return null;
	}

	public int saveLoyaltyFile(LoyaltyNumberGenerationRequestDTO loyaltyNumberRequest) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 jdbcTemplate.update(connection -> {
			 PreparedStatement ps = connection.prepareStatement(LoyaltyCardQuery.INSERT_LOYALTYNUMBERFILE, Statement.RETURN_GENERATED_KEYS);
			 ps.setInt(1, loyaltyNumberRequest.getQuantity());
			  return ps;
         }, keyHolder); 
         return keyHolder.getKey().intValue();
		 
//		    Object []args= {file.getFileName()};
//		      return jdbcTemplate.update(LoyaltyCardQuery.INSERT_LOYALTYNUMBERFILE, args);
	}

	@Override
	public int updateFileStatus(LoyaltyNumberGenerationRequestDTO loyaltyNumberRequest) {
		 Object []args= {loyaltyNumberRequest.getStatus(),loyaltyNumberRequest.getReason(),loyaltyNumberRequest.getId()};
	      return jdbcTemplate.update(LoyaltyCardQuery.UPDATE_LOYALTYFILESTATUS, args);
	}

	@Override
	public List<Long> getAllLoyaltyNumbers() {
		
		return jdbcTemplate.queryForList(LoyaltyCardQuery.GET_LOYALTYNUMBERS,Long.class);
	}
	@Override
	public List<LoyaltyNumberGenerationRequestDTO> getAllGeneratedLoyaltyNumber() {
		return jdbcTemplate.query(LoyaltyCardQuery.GET_LOYALTY_NUMBER_ALL_REQUEST, new GeneratedLoyaltyNumberMapper());
	}
	
	@Override
	public List<LoyaltyNumberDTO> getAllGeneratedLoyaltyNumberByGenrationId(int id) {
		Object[] args= {id};
		return jdbcTemplate.query(LoyaltyCardQuery.GET_LOYALTY_NUMBER_BY_ID, args,new LoyaltyNumberMapper());
	}
	
	@Override
	public List<LoyaltyNumberDTO> getNotAllocatedLoyaltyNumbers() {
		return jdbcTemplate.query(LoyaltyCardQuery.GET_NOT_ALLOCATED_LOYALTYCARDNUMBERS,new LoyaltyNumberMapper());
	}
	@Override
	public List<LoyaltyNumberDTO> getLoyaltyNumber() {
		// TODO Auto-generated method stub
		 return jdbcTemplate.query(LoyaltyCardQuery.GET_ALL_LOYALTYNUMBER, new LoyaltyNumberMapper());
	}
	@Override
	public List<LoyaltyNumberDTO> getAllAssignedLoyaltyNumber() {
		// TODO Auto-generated method stub
		 return jdbcTemplate.query(LoyaltyCardQuery.GET_ALL_ASSIGNED_LOYALTY_NUMBER, new LoyaltyNumberMapper());
	}
	
	@Override
	public List<LoyaltyNumberDTO> getAllAssignedLoyaltyNumberByMid(int mId) {
		// TODO Auto-generated method stub
		Object []args= {mId	};
		 return jdbcTemplate.query(LoyaltyCardQuery.GET_ALL_ASSIGNED_LOYALTY_NUMBER_BY_MID,args, new LoyaltyNumberMapper());
	}
	
	@Override
	public List<LoyaltyNumberDTO> getAllAvailableLoyaltyNumberByMid(int mId) {
		// TODO Auto-generated method stub
		Object []args= {mId	};
		 return jdbcTemplate.query(LoyaltyCardQuery.GET_ALL_AVAILABLE_LOYALTY_NUMBER_BY_MID,args, new LoyaltyNumberMapper());
	}
	
	@Override
	public LoyaltyNumberDTO getLastLoyaltyNumber() {
		List<LoyaltyNumberDTO> loyaltyNumberList=  jdbcTemplate.query(LoyaltyCardQuery.GET_LAST_LOYALTYNUMBER, new LoyaltyNumberMapper());
		  if(!loyaltyNumberList.isEmpty()){
			  return loyaltyNumberList.get(0);
		   }
		  return null;
	}
	
	@Override
	public List<LoyaltyNumberDTO> getLoyaltyNumbersByMidAndStatus(String status,int mId,int requestId) {
		Object[] args= {status,mId,requestId};
		List<LoyaltyNumberDTO> loyaltyNumberList= jdbcTemplate.query(LoyaltyCardQuery.GET_LOYALTYNUMBERS_BY_MID_AND_REQUESTID,args, new LoyaltyNumberMapper());
		  if(!loyaltyNumberList.isEmpty()){
			  return loyaltyNumberList;
		   }
		  return null;
	}
	
	@Override
	public List<LoyaltyNumberDTO> getLoyaltyNumberForAllocation(String status,int isActive,int limit) {
		Object[] args= {status,isActive,limit};
		List<LoyaltyNumberDTO> loyaltyNumberList=jdbcTemplate.query(LoyaltyCardQuery.GET_LOYALTYNUMBERs_FOR_ALLOCATION,args, new LoyaltyNumberMapper());
		  if(!loyaltyNumberList.isEmpty()){
			  return loyaltyNumberList;
		   }
		  return null;
	}

	@Override
	public List<LoyaltyNumberDTO> getAllGeneratedLoyaltyNumberById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateLoyaltyCardNumber(int mId,int cId,String loyaltyNumber,String status,int isActive)
	{
		Object []args= {cId,status,isActive,mId,loyaltyNumber};
		jdbcTemplate.update(LoyaltyCardQuery.UPDATE_LOYALTYCARDNUMBER,args);
	}

	@Override
	public LoyaltyNumberDTO validateLoyatyNumber(String loyaltyNumber)
	{
		Object []args= {loyaltyNumber};
		List<LoyaltyNumberDTO> loyaltyNumberList=jdbcTemplate.query(LoyaltyCardQuery.VALIDATE_LOYALTY_NUMBER,args, new LoyaltyNumberMapper());
		  if(!loyaltyNumberList.isEmpty()){
			  return loyaltyNumberList.get(0);
		   }
		  return null;
	}
	@Override
	public LoyaltyNumberDTO validateLoyatyNumberByCid(int cid)
	{
		Object []args= {cid};
		List<LoyaltyNumberDTO> loyaltyNumberList=jdbcTemplate.query(LoyaltyCardQuery.VALIDATE_LOYALTY_NUMBER_WITH_CID,args, new LoyaltyNumberMapper());
		  if(!loyaltyNumberList.isEmpty()){
			  return loyaltyNumberList.get(0);
		   }
		  return null;
	}
	
	@Override
	public int activateLoyaltyCardNumber(int mId, int cId, int isActive) {
		Object []args= {isActive,mId,cId};
		jdbcTemplate.update(LoyaltyCardQuery.ACTIVATE_LOYALTYCARDNUMBER,args);
		return 0;
	}
	@Override
	public int blockLoyaltyCardNumber(int mId, int cId, int isActive,String status) {
		Object []args= {isActive,status,mId,cId};
		jdbcTemplate.update(LoyaltyCardQuery.BLOCK_LOYALTYCARDNUMBER,args);
		return 0;
	}
	
//	@Override
//	public int insertLoyaltyNumbers(Set<LoyaltyNumberDTO> numberSet) {
//		Connection conn=null;	
//		try {
//			conn = jdbcTemplate.getDataSource().getConnection();
//			 StopWatch timer = new StopWatch();
//			 timer.start();
//			PreparedStatement ps = conn.prepareStatement(LoyaltyCardQuery.INSERT_LOYALTYNUMBERS);
//			for(LoyaltyNumberDTO number:numberSet)
//			{	
//				ps.setString(1, number.getLoyaltyNumber());
//				ps.setInt(2,number.getFileId());
//				ps.addBatch();
//			}
//			ps.executeBatch();
//			timer.stop();
//			ps.close();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {}
//			}
//		}
//		return INSERT_BATCH_SIZE;
//		
//	}
}
