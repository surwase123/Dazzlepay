package com.cratas.mpls.web.dto;

public class WebServiceResponseDTO {
	
		private String errMsg;
		private String status = "FAILURE";
		private Object data;
				
		public WebServiceResponseDTO(String errMsg) {
			super();
			this.errMsg = errMsg;
		}

		public String getErrMsg() {
			return errMsg;
		}
		
		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}
		
		public String getStatus() {
			return status;
		}
		
		public void setStatus(String status) {
			this.status = status;
		}
		
		public Object getData() {
			return data;
		}
		
		public void setData(Object data) {
			this.data = data;
		}

}
