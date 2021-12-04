package com.cratas.mpls.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.PayType;
import com.cratas.mpls.service.IQRCodeService;
import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;
import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class QRCodeServiceImpl implements IQRCodeService {

	   private final static Logger LOGGER = LoggerFactory.getLogger(QRCodeServiceImpl.class);

	   public String createQRCode(MerchantTransactionDTO merchantTransaction, CustomerTransactionDTO customerTransaction) {
		      merchantTransaction = addRequiredParam(merchantTransaction);
				  Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
			      hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			      JsonObject obj = new JsonObject();
			      obj.addProperty(Constants.C_ID, String.valueOf(customerTransaction.getcId()));
			      obj.addProperty(Constants.M_ID, String.valueOf(customerTransaction.getmId()));
			      obj.addProperty(Constants.TRANSACTION_VALUE, customerTransaction.getTransactionValue());
			      obj.addProperty(Constants.TRANSACTION_ID, customerTransaction.getTransactionId());
			      obj.addProperty(Constants.OFFER_CODE, customerTransaction.getOfferCode());
			      obj.addProperty(Constants.WALLET_TRANSACTION_ID, customerTransaction.getWalletTransactionId());
			      obj.addProperty(Constants.TRANSACTION_TYPE, customerTransaction.getTransactionType());
			      String jsonText = obj.toString();
			      try{
				      BitMatrix matrix = new MultiFormatWriter().encode(jsonText,BarcodeFormat.QR_CODE, 200, 200, hintMap);	
				      BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
				      String image = encodeToString(bufferedImage, Constants.PNG);
				      return image;
			      }catch (Exception e) {
			    	  LOGGER.info("Error in Create QR code for transaction -- "+e.getMessage());
			    	  e.printStackTrace();
				  }
			  return Constants.BLANK;    
	   }	
		
	   private String encodeToString(BufferedImage image, String type) {
	          String imageString = null;
	          ByteArrayOutputStream bos = new ByteArrayOutputStream();
	          try {
		           ImageIO.write(image, type, bos);
		           byte[] imageBytes = bos.toByteArray();
		           imageString =  Base64.getEncoder().encodeToString(imageBytes);
		           bos.close();
	          }catch (IOException e) {
	              LOGGER.info("Error in ");
	          }
	          return imageString;
	   }
	   
	   private MerchantTransactionDTO addRequiredParam(MerchantTransactionDTO merchantTransaction){
		       merchantTransaction.setPayType(PayType.QRCODE.getPayType());
		       merchantTransaction.setStatus(Constants.PROCESSING_FLAG);
		       merchantTransaction.setIndicator(Constants.INDICATOR_CREDIT);
		       return merchantTransaction;
	   }
	   
}