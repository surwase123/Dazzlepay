package com.cratas.mpls.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PushNotificationMSGDTO;
import com.cratas.mpls.dao.ILoyaltyCardDao;
import com.cratas.mpls.dao.IMerchantDao;
import com.cratas.mpls.dao.IRequestLoyaltyCardDao;
import com.cratas.mpls.service.IPushNotificationService;
import com.cratas.mpls.service.IRequestLoyaltyCardService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.LoyaltyNumberDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;

@Service
public class RequestLoyaltyCardServiceImpl implements IRequestLoyaltyCardService {
	
	@Autowired
    private IRequestLoyaltyCardDao requestLoyaltyCardDao;
	
	@Autowired
    private ILoyaltyCardDao loyaltyCardDao;
	
	@Autowired
	private IMerchantDao merchantDao;
	@Autowired
    private IPushNotificationService pushNotificationService;

	@Override
	public int saveRequest(RequestLoyaltyCardDTO requestDto) {
		MerchantDTO merchant=this.getMerchantById(requestDto.getmId());
		requestDto.setMerchantName(merchant.getFirstName()+" "+merchant.getLastName());
		requestDto.setMerchantId(merchant.getMerchantId());
	String name=requestDto.getMerchantName();
	int qty=requestDto.getQuantityOfCards();
	String message=name+Constants.SPACE+PushNotificationMSGDTO.CARD_REQUEST.replace("number",String.valueOf(qty));
	requestDto.setMessage(message);
	int response= requestLoyaltyCardDao.saveRequest(requestDto);
	pushNotificationService.sendRequestLoyaltyCardNotification(requestDto);
	return response;
	}

	@Override
	public int  approveRequest(RequestLoyaltyCardDTO requestDto) {
		requestDto.setStatus(1);
		RequestLoyaltyCardDTO requestData=getRequestByRequestId(requestDto.getId());
		int validationSatus	=requestDataValidate(requestData);
		if(validationSatus==0)
		{
			int response=requestLoyaltyCardDao.updateRequestStatusWithResonById(requestDto);
			if(response==1) {
				RequestLoyaltyCardDTO request=new RequestLoyaltyCardDTO();
				request=this.getRequestByRequestId(requestDto.getId());
				MerchantDTO merchant=this.getMerchantById(request.getmId());
			}
			return 0;
		}
		
		return 1;
	}
	
	@Override
	public int rejectRequest(RequestLoyaltyCardDTO requestDto) {
			requestDto.setStatus(2);
		int response=requestLoyaltyCardDao.updateRequestStatusWithResonById(requestDto);
		if(response==1) {
			RequestLoyaltyCardDTO request=new RequestLoyaltyCardDTO();
			request=this.getRequestByRequestId(requestDto.getId());
			MerchantDTO merchant=this.getMerchantById(request.getmId());			
			this.sendNotification(merchant, request, Constants.REJECT_REQUEST_NOTIFICATION);
		}
	return response;
	}
	
	public int requestDataValidate(RequestLoyaltyCardDTO requestData) {
		List<LoyaltyNumberDTO> availableList= loyaltyCardDao.getNotAllocatedLoyaltyNumbers();
		if(availableList!=null && availableList.size()>0)
		{
			int requestdQuantity=requestData.getQuantityOfCards();
			int availableCards=availableList.size();
			int newAvailableCards=availableCards-requestdQuantity;
			if(newAvailableCards>=0)
			{
				return 0;
			}
			else {
				return 1;
			}
		}
		
		return 1;
		
	}
	
	@Override
	public   RequestLoyaltyCardDTO getRequestByRequestId(int id)
	{
		return requestLoyaltyCardDao.getRequestByRequestId(id);
	}

	@Override
	public List<RequestLoyaltyCardDTO> getAllRequestByStatus() {
		// TODO Auto-generated method stub
		return requestLoyaltyCardDao.getAllRequestByStatus();
	}
	
	@Override
	public MerchantDTO getMerchantById(int id) {
		return merchantDao.getMerchantById(id);
	}
	
	private void sendNotification(MerchantDTO merchant,RequestLoyaltyCardDTO loyaltiCardDTO,String Status)
	{
		
//		loyaltiCardDTO.setmId(merchant.getId());
		pushNotificationService.sendLoyaltyCardNotification(loyaltiCardDTO,Status);
	}

	@Override
	public List<RequestLoyaltyCardDTO> getAllRequestByStatus(int status) {
		return requestLoyaltyCardDao.getAllRequestByStatus(status);
	}
	@Override
	public List<LoyaltyNumberDTO> getAllLoyaltyCardNumbersByRequestIdMId(String status,int mId,int requestId) {
		
		return loyaltyCardDao.getLoyaltyNumbersByMidAndStatus(status,mId,requestId);
	}
	
	
	@Scheduled(cron = "0 */1 * * * *")
    public void myJob() {
		this.allocateLoyaltyNumbers();
    }
	
	public void allocateLoyaltyNumbers() {
		List<RequestLoyaltyCardDTO> requestList=this.getAllRequestByStatus(1);
		if(requestList!=null)
		{
			for(RequestLoyaltyCardDTO request:requestList)
			{
				Set<LoyaltyNumberDTO> listofUpdateNumber=new HashSet();
				List<LoyaltyNumberDTO> listOfNumbers=loyaltyCardDao.getLoyaltyNumberForAllocation("NA",0,request.getQuantityOfCards());
				if(listOfNumbers!=null)
				{
					for(LoyaltyNumberDTO number:listOfNumbers)
					{
						number.setStatus("AL");
						number.setRequestId(request.getId());
						number.setmId(request.getmId());
						listofUpdateNumber.add(number);
					}
					loyaltyCardDao.updateLoyaltyNumbers(listofUpdateNumber);
					request.setStatus(3);
					requestLoyaltyCardDao.updateRequestStatusById(request);
					this.sendNotification(null, request, Constants.ACCEPT_REQUEST_NOTIFICATION);
				}
			}
		}
	}

	public List<RequestLoyaltyCardDTO> getAllRequestByMerchantIdAndStatus(int merchantId) {
		return requestLoyaltyCardDao.getAllRequestByMerchantIdAndStatus(merchantId);
	}
	public List<RequestLoyaltyCardDTO> getAllPendingAndAccepetedRequestByMerchantId(int merchantId) {
		return requestLoyaltyCardDao.getAllPendingAndAccepetedRequestByMerchantId( merchantId);
	}
	
}
