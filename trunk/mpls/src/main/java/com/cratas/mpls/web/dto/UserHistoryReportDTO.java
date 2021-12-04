/**
 * 
 */
package com.cratas.mpls.web.dto;

/**
 * @author bhupendra
 *
 */
public class UserHistoryReportDTO {
	
	private String systemId;
    private String processorId;
	private String institutionId;
	private String reconProcessId;
	
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getProcessorId() {
		return processorId;
	}
	public void setProcessorId(String processorId) {
		this.processorId = processorId;
	}
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	public String getReconProcessId() {
		return reconProcessId;
	}
	public void setReconProcessId(String reconProcessId) {
		this.reconProcessId = reconProcessId;
	}
	
	
}
