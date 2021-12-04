package com.cratas.mpls.dao.dto;

/**
 * 
 * @author mukesh
 *
 */
public class ReconSourceMaster {
	
	    private String sourceName;
	    private String sourceDisplayName;
	    private int isReqDetailTbl;
	   
		public String getSourceName() {
			return sourceName;
		}
		public void setSourceName(String sourceName) {
			this.sourceName = sourceName;
		}
		public String getSourceDisplayName() {
			return sourceDisplayName;
		}
		public void setSourceDisplayName(String sourceDisplayName) {
			this.sourceDisplayName = sourceDisplayName;
		}
		public int getIsReqDetailTbl() {
			return isReqDetailTbl;
		}
		public void setIsReqDetailTbl(int isReqDetailTbl) {
			this.isReqDetailTbl = isReqDetailTbl;
		}	
	   
}
