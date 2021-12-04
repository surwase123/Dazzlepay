<div id="searchCustomerView" class="modal fade bd-example-modal-lg show" tabindex="-1" role="dialog" aria-labelledby="searchCustomerViewTitle" aria-modal="true">
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
						    	<input type="text" class="form-control" name="mobileNumber" id="mobileNumber"  placeholder="Mobile Number">
						    </div>
						    <input type="hidden" name="addMId" id="addMId" value="${sessionScope.loginDTO.merchant.id}">
							<div class="alert alert-danger errResponse DispNone" role="alert"></div>
							<div class="col-md-12 text-center">
			                   <button type="submit" class="btn  btn-primary" onclick="customerDetails('addMId','mobileNumber')">Submit</button>
			                </div>
			            </div>   
			            <div id="templateView"></div>
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