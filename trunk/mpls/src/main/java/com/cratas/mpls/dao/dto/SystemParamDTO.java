package com.cratas.mpls.dao.dto;

import java.util.Date;

/**
 * 
 * @author mukesh
 *
 */
public class SystemParamDTO {
	
	  private int id;	
	  private String paramName;
	  private String paramValue;
	  private String paramDisplayName;
	  private int length;
	  private String createdBy;
	  private String updatedBy;
	  private Date insertTimeStamp;
	  private Date updateTimeStamp;
	  
	  
	  public int getId() {
		    return id;
	  }
	  public void setId(int id) {
		    this.id = id;
	  }
	  public String getParamName() {
			return paramName;
	  }
	  public void setParamName(String paramName) {
			this.paramName = paramName;
	  }
	  public String getParamValue() {
			return paramValue;
	  }
	  public void setParamValue(String paramValue) {
			this.paramValue = paramValue;
	  }
	  public String getCreatedBy() {
			return createdBy;
	  }
	  public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
	  }
	  public String getUpdatedBy() {
			return updatedBy;
	  }
	  public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
	  }
	  public Date getInsertTimeStamp() {
			return insertTimeStamp;
	  }
	  public void setInsertTimeStamp(Date insertTimeStamp) {
			this.insertTimeStamp = insertTimeStamp;
	  }
	  public Date getUpdateTimeStamp() {
			return updateTimeStamp;
	  }
	  public void setUpdateTimeStamp(Date updateTimeStamp) {
			this.updateTimeStamp = updateTimeStamp;
	  }
	  public String getParamDisplayName() {
		    return paramDisplayName;
	  }
	  public void setParamDisplayName(String paramDisplayName) {
		   this.paramDisplayName = paramDisplayName;
	  }
	  public int getLength() {
		   return length;
	  }
	  public void setLength(int length) {
		   this.length = length;
	  }
	  

}
