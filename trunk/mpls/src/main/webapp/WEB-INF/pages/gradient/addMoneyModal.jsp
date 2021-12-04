<div id="addMoneyModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="addMoneyModalTitle" aria-modal="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="row">
                <div class="col-sm-12">
                    <div class="card-header">
                       <h5 class="modal-title" id="addMoneyModalTitle">Add Money</h5>
                       <hr/>
                    </div>
                    <div class="col-md-12">
                         <div class="form-group">
                             <label class="form-label">Amount</label>
                             <input type="text" class="form-control decimal" name="addWalletBal" id="addWalletBal" placeholder="Amount">
                         </div>
                    </div>
                    <div class="form-group row MarginLeft7">
                        <div class="col-sm-9">
                            <div class="MarginLeft7 offerText">Available Balance <span id="availableWalletBalance-Add"></span></div>
                        </div>
                    </div>
                    <input type="hidden" name="addCId" id="addCId">
                    <input type="hidden" name="addMId" id="addMId">
                    <input type="hidden" name="addTransactionAmountId" id="addTransactionAmountId">
                    <div class="alert alert-danger msgResponse DispNone" role="alert"></div>
                    <div class="col-md-12 text-center">
                         <button type="button" class="btn  btn-primary" onclick="addMoneyWallet('addWalletBal','addCId','addMId', 'addMoneyModal','TOPUP', 'addTransactionAmountId')">Add Money</button>
                    </div>
                </div>
		        <div class="card-body">
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>	
		</div>	
	</div>
</div>