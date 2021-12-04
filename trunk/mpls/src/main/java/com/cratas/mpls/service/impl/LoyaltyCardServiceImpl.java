package com.cratas.mpls.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.dao.ICategoryDao;
import com.cratas.mpls.dao.ILoyaltyCardDao;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ILoyaltyCardService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IRequestLoyaltyCardService;
import com.cratas.mpls.service.IVerificationService;
import com.cratas.mpls.web.dto.CountryDTO;
import com.cratas.mpls.web.dto.CsvFileDTO;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.LoyaltyCardAvailabilityDTO;
import com.cratas.mpls.web.dto.LoyaltyNumberDTO;
import com.cratas.mpls.web.dto.LoyaltyNumberGenerationRequestDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.VerificationCodeDTO;
import com.cratas.mpls.web.dto.VerificationLogDTO;


@Service
public class LoyaltyCardServiceImpl implements ILoyaltyCardService{
	@Autowired
    private ILoyaltyCardDao loyaltyCardDao;
	@Autowired
	private IRequestLoyaltyCardService  requestLoyaltyCardService;
	
	@Autowired
	private IMerchantMappingService merchantMappingService;
	
	@Autowired
	private ICustomerService  customerService;
	
	 @Autowired
	 private IVerificationService verificationService;
	
	@Autowired
    private Environment env;
	
//	@Override
//	public int saveFile(MultipartFile file)  {	
//		 Boolean databaseFlag=true;
//		 Boolean flag=true;
//		 try {
//    	 BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
//    			 CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
//    			 Set<String> store = new HashSet(); 
//    			 Set<LoyaltyNumberDTO> uniqueNumbers=new HashSet(); 
//    			 Set<String> duplicatNumbers = new HashSet(); 
//    			 Set<Long> oldLoyaltyNumbers=new  HashSet(loyaltyCardDao.getAllLoyaltyNumbers());
//    			 LoyaltyNumberFileDTO loyaltyNumberFileDTO=new LoyaltyNumberFileDTO();
//    			 loyaltyNumberFileDTO.setFileName(file.getOriginalFilename());
//    			 Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//    			 int id=loyaltyCardDao.saveLoyaltyFile( loyaltyNumberFileDTO);
//    			 for (CSVRecord csvRecord : csvRecords) {
//    				 LoyaltyNumberDTO loyaltyNumberDTO=new LoyaltyNumberDTO();
//    				 loyaltyNumberDTO.setLoyaltyNumber(csvRecord.get(0));
//    				 loyaltyNumberDTO.setFileId(id);
//    				 if (store.add(csvRecord.get(0)) == false) {
//    					 loyaltyNumberFileDTO.setId(id);
//    					 loyaltyNumberFileDTO.setStatus("F");
//    					 loyaltyNumberFileDTO.setReason("found a duplicate element in File ");
//    					 loyaltyCardDao.updateFileStatus(loyaltyNumberFileDTO);
//    					 flag=false;
//    					 return 1;
//    				 }
//    				 else {
//    					 uniqueNumbers.add(loyaltyNumberDTO);
//    				 }
//    				 if(oldLoyaltyNumbers.add(new Long(csvRecord.get(0))) == false)
//    				 {
//    					 flag=false;
//    					 loyaltyNumberFileDTO.setId(id);
//    					 loyaltyNumberFileDTO.setStatus("F");
//    					 loyaltyNumberFileDTO.setReason("Number already Exist");
//    					 loyaltyCardDao.updateFileStatus(loyaltyNumberFileDTO);
//    					 return 2;
//    				 }
//    			 }
//    			 if(flag)
//    			 { 
//    				 loyaltyCardDao.insertLoyaltyNumbers(uniqueNumbers);
//    				 loyaltyNumberFileDTO.setId(id);
//					 loyaltyNumberFileDTO.setStatus("D");
//					 loyaltyNumberFileDTO.setReason(null);
//					 loyaltyCardDao.updateFileStatus(loyaltyNumberFileDTO);
//    				
//    			 }
//    			
//		 }
//		 catch(IOException e)
//		 {
//			
//		 }
//		return 0;
//	}
//	
	@Override
	public int saveFile(int quantity)  {
		LoyaltyNumberGenerationRequestDTO loyaltyNumberDTO=new LoyaltyNumberGenerationRequestDTO();
		loyaltyNumberDTO.setQuantity(quantity);
		int id=loyaltyCardDao.saveLoyaltyFile( loyaltyNumberDTO);
		String prefix=env.getProperty(PropertyKeyConstants.LoyaltyCardNumberProperties.LOYALTY_NUMBER_PREFIX);
		Set<LoyaltyNumberDTO> numberSet=new HashSet();
		int startingNumber,i, numberOfQuantity;
		LoyaltyNumberDTO loyaltyNumberdt=new LoyaltyNumberDTO();
		loyaltyNumberdt=loyaltyCardDao.getLastLoyaltyNumber();
		String lastNumberString;
		if(loyaltyNumberdt!=null) {
			lastNumberString=loyaltyNumberdt.getLoyaltyNumber();
			startingNumber=Integer.parseInt(lastNumberString.substring(9));
			numberOfQuantity=(startingNumber+quantity);
			startingNumber++;
			if(String.valueOf(numberOfQuantity).length()>7) {
				loyaltyNumberDTO.setId(id);
				loyaltyNumberDTO.setStatus("F");
				loyaltyNumberDTO.setReason("Exceed Number Limit");
				loyaltyCardDao.updateFileStatus(loyaltyNumberDTO);
				return 1;
			}
		}
		else {
			startingNumber=1;
			numberOfQuantity=quantity;
		}
		String numberFormat="%0".concat(env.getProperty(PropertyKeyConstants.LoyaltyCardNumberProperties.LOYALTY_NUMBER_LAST_DIGITS)).concat("d");
		String loyaltyNumber;
		try {
				int length=String.valueOf(startingNumber).length();
				for( i=startingNumber;i<=numberOfQuantity;i++)
				{
					String str = String.format(numberFormat, i);
					loyaltyNumber=prefix.concat(str);
					 LoyaltyNumberDTO loyaltyNumberdto=new LoyaltyNumberDTO();
					 loyaltyNumberdto.setLoyaltyNumber(loyaltyNumber);
					 loyaltyNumberdto.setGenrationId(id);
					numberSet.add(loyaltyNumberdto);			
				}
				loyaltyCardDao.insertLoyaltyNumbers(numberSet);
				loyaltyNumberDTO.setId(id);
				loyaltyNumberDTO.setStatus("D");
				loyaltyNumberDTO.setReason(null);
				loyaltyCardDao.updateFileStatus(loyaltyNumberDTO);
		}catch(Exception e){
			loyaltyNumberDTO.setId(id);
			loyaltyNumberDTO.setStatus("F");
			loyaltyNumberDTO.setReason("Error in Code");
			loyaltyCardDao.updateFileStatus(loyaltyNumberDTO);
		}	
		return 0;
	}
	public List<LoyaltyNumberDTO> getLoyaltyNumber() {
		return loyaltyCardDao.getLoyaltyNumber();
		
	}
	
	@Override
	public void genrateCsv(int id,HttpServletResponse response) throws IOException {
		
		List<LoyaltyNumberDTO> list=loyaltyCardDao.getAllGeneratedLoyaltyNumberByGenrationId(id);
		LoyaltyNumberDTO numberdto=list.get(0);
		String csvFileName = "CSV_FILE_"+numberdto.getGenrationId()+".csv";
		List<CsvFileDTO> csvDataList=new ArrayList<CsvFileDTO>();
		int index=1;
		for(LoyaltyNumberDTO number : list) {
			CsvFileDTO csvDto=new CsvFileDTO();
			csvDto.setSerialNumber(index++);
			csvDto.setLoyaltyNumber(number.getLoyaltyNumber());
			csvDto.setStatus(statusChecker(number.getStatus()));
			csvDataList.add(csvDto);
		}
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
 
        String[] header = {"SerialNumber","LoyaltyNumber", "Status" };
        
        csvWriter.writeHeader(header);
 
        for (CsvFileDTO csvData : csvDataList) {
            csvWriter.write(csvData, header);
        }
 
        csvWriter.close();
	}
	
	public String statusChecker(String status) {
		String statusString="";
		switch(status) {
		case "NA":statusString="NOT ALLOCATED";
				break;
		case "AL":statusString="ALLOCATED";
			break;
		}
		return statusString;
	}
	@Override
	public void genrateCsvByMerchantIdRquestId(int mId,int requestId,HttpServletResponse response) throws IOException {
		
		List<LoyaltyNumberDTO> list=requestLoyaltyCardService.getAllLoyaltyCardNumbersByRequestIdMId("AL",mId,requestId);
		List<CsvFileDTO> csvDataList=new ArrayList<CsvFileDTO>();
		LoyaltyNumberDTO numberdto=list.get(0);
		int index=1;
		for(LoyaltyNumberDTO number : list) {
			CsvFileDTO csvDto=new CsvFileDTO();
			csvDto.setSerialNumber(index++);
			csvDto.setLoyaltyNumber(number.getLoyaltyNumber());
			csvDto.setStatus(statusChecker(number.getStatus()));
			csvDataList.add(csvDto);
		}
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		String csvFileName = now.toString()+".csv";
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
 
        String[] header = {"SerialNumber","LoyaltyNumber", "Status" };
 
        csvWriter.writeHeader(header);
 
        for (CsvFileDTO csvData : csvDataList) {
            csvWriter.write(csvData, header);
        }
        csvWriter.close();
	}

	public List<LoyaltyNumberGenerationRequestDTO> getAllGeneratedLoyaltyNumber() {
		return loyaltyCardDao.getAllGeneratedLoyaltyNumber();
	}

	public List<LoyaltyNumberDTO> getAllGeneratedLoyaltyNumberById(int id) {
		return loyaltyCardDao.getAllGeneratedLoyaltyNumberById(id);

	}
	@Override
	public void updateLoyaltyCardNumberByMid(int mId, int cId, String loyaltyNumber, String Status,int isActive) {
		merchantMappingService.updateMerchantMappingWithLoyaltyNumber(cId,mId,loyaltyNumber,isActive);
		loyaltyCardDao.updateLoyaltyCardNumber(mId,cId, loyaltyNumber, Status, isActive);
	}
	
	public int  validateLoyaltyNumber(int mId, int cId, String loyaltyNumber, String Status,int isActive)
	{
		 	String regex = "[0-9]+";
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(loyaltyNumber);
	        if(m.matches()) {
	        	LoyaltyNumberDTO alreadyAssigned=	loyaltyCardDao.validateLoyatyNumberByCid(cId);
	        	if(alreadyAssigned==null) {
	        		LoyaltyNumberDTO numbers= loyaltyCardDao.validateLoyatyNumber(loyaltyNumber);
		        	if(numbers!=null)
		        	{
		        		int merchantID=numbers.getmId();
		        		if(merchantID==mId)
			    		{
			    			
			    			if(numbers.getStatus().equalsIgnoreCase("AL"))
			    			{
			    				return 0;
			    			}
			    			else
			    			{
			    				return 1;
			    			}
			    		}
			    		return 2;
		        	}
	        	}
	        	else {
	        		return 1;
	        	}
	        	
	        }
	        return 2;
	}
	@Override
	public int validateReasignedLoyaltyNumber(int mId, int cId, String loyaltyNumber, String Status,int isActive) {
		String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(loyaltyNumber);
        if(m.matches()) {
        	
        		LoyaltyNumberDTO numbers= loyaltyCardDao.validateLoyatyNumber(loyaltyNumber);
	        	if(numbers!=null)
	        	{
	        		int merchantID=numbers.getmId();
	        		if(merchantID==mId)
		    		{
		    			
		    			if(numbers.getStatus().equalsIgnoreCase("AL"))
		    			{
		    				return 0;
		    			}
		    			else
		    			{
		    				return 1;
		    			}
		    		}
		    		return 2;
	        	}
        }
        return 2;
	}
	@Override
	public String verifyMobileNumberbyCid(String mobileNo,int cId) {
		CustomerDTO customer= customerService.getCustomerByCId(cId);
		if(customer!=null)
		{
			if(customer.getMobileNumber().equals(mobileNo))
			{
				VerificationLogDTO verifyDto=new VerificationLogDTO();
				verifyDto.setMobileNumber(mobileNo);
				verifyDto.setUserType(Constants.CUSTOMER);
				return verificationService.sendMobileVerificationCode(verifyDto);
			}
		}
		
		 return Constants.INVALID_MOBILE_NUMBER;
	}
	
	@Override
	public int activateLoyaltyCardNumber(int mId, int cId,int isActive) {
		return loyaltyCardDao.activateLoyaltyCardNumber(mId,cId, isActive);
	}
	
	@Override
	public int blockLoyaltyCardNumber(int mId, int cId,int isActive,String status) {
		return loyaltyCardDao.blockLoyaltyCardNumber(mId,cId, isActive,status);
	}
	
	@Override
	public LoyaltyCardAvailabilityDTO getAllAvailableLoylatyNumberSuperAdmin() {
		List<LoyaltyNumberDTO> allLoyaltyNumberList=loyaltyCardDao.getLoyaltyNumber();		
		List<LoyaltyNumberDTO> allAllocatedLoyaltyNumberList=loyaltyCardDao.getAllAssignedLoyaltyNumber();	
		LoyaltyCardAvailabilityDTO loyaltyCardAvailable=new LoyaltyCardAvailabilityDTO();
		loyaltyCardAvailable.setTotal(allLoyaltyNumberList.size());
		loyaltyCardAvailable.setAllocated(allAllocatedLoyaltyNumberList.size());		
		int allAvailableNumber=allLoyaltyNumberList.size()-allAllocatedLoyaltyNumberList.size();
		loyaltyCardAvailable.setAvailble(allAvailableNumber);
		return loyaltyCardAvailable;
	}
	
	
	
	@Override
	public LoyaltyCardAvailabilityDTO getAllAvailableLoylatyNumberMerchant(int mId) {
		List<LoyaltyNumberDTO> allLoyaltyNumberList=loyaltyCardDao.getAllAvailableLoyaltyNumberByMid(mId);		
		List<LoyaltyNumberDTO> allAllocatedLoyaltyNumberList=loyaltyCardDao.getAllAssignedLoyaltyNumberByMid(mId);	
		LoyaltyCardAvailabilityDTO loyaltyCardAvailable=new LoyaltyCardAvailabilityDTO();
		loyaltyCardAvailable.setTotal(allLoyaltyNumberList.size());
		loyaltyCardAvailable.setAllocated(allAllocatedLoyaltyNumberList.size());		
		int allAvailableNumber=allLoyaltyNumberList.size()-allAllocatedLoyaltyNumberList.size();
		loyaltyCardAvailable.setAvailble(allAvailableNumber);
		return loyaltyCardAvailable;
	}
}
