<div id="searchCustomerForPayBillView" class="modal fade bd-example-modal-lg show" tabindex="-1" role="dialog" aria-labelledby="searchCustomerForPayBillViewTitle" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="row">
			      <div class="col-sm-12">
				        <div class="card-header">
	                       <h5 class="modal-title">Search Customer</h5>
	                       <hr/>
	                    </div>
						<div class="col-md-12">
							<div class="form-group">
						    	<label class="form-label">Mobile Number</label>
						    	<input type="text" class="form-control" name="mobileNumber" id="mobile"  placeholder="Mobile Number">
						    </div>
						      <input type="hidden" name="payMId" id="payMId" value="${sessionScope.loginDTO.merchant.id}">
						      <div class="alert alert-danger errResponse DispNone" role="alert"></div>
							<div class="col-md-12 text-center">
			                   <button type="submit" class="btn  btn-primary" onclick="customerDetailForPayBill('payMId','mobile')">Submit</button>
			                </div>
			            </div>   
			            <div id="templateViewPayBill"></div>
						<div class="card-body">
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
							</div>
						</div>	
				  </div>
			</div>	 
		</div>
	</div>		 		
</div>	