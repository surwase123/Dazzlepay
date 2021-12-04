package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class AESDTO {

	    private int keySize;
	    private int iterationCount;
	    private String key;
	    
	   
		public AESDTO(int keySize, int iterationCount, String key) {
			super();
			this.keySize = keySize;
			this.iterationCount = iterationCount;
			this.key = key;
		}
		
		public int getKeySize() {
			return keySize;
		}
		public void setKeySize(int keySize) {
			this.keySize = keySize;
		}
		public int getIterationCount() {
			return iterationCount;
		}
		public void setIterationCount(int iterationCount) {
			this.iterationCount = iterationCount;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
	   
	   
}
