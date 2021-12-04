<div id="qrCodeModal" class="modal fade bd-example-modal-lg show" tabindex="-1" role="dialog" aria-labelledby="qrCodeModalTitle" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="row">
                <div class="col-sm-12">
                    <div class="card-header">
                        <h5 class="modal-title" id="qrCodeModalTitle">QR Code</h5>
                        <hr/>
                    </div>
                    <div class="col-md-12">
                         <div class="form-group">
                             <label class="form-label">Amount</label>
                             <input type="text" class="form-control decimal" name="payWalletBal" id="payWalletBal" placeholder="Amount">
                         </div>
                    </div>
                    <input type="hidden" name="mId" id="mId" value="${sessionScope.loginDTO.merchant.id}">
                    <input type="hidden" name="payTransactionAmountId" id="payTransactionAmountId">
                    <div class="alert alert-danger msgQRResponse DispNone" role="alert"></div>
                    <div class="col-md-12 text-center">
                         <button type="button" class="btn btn-danger" onclick="generateQRCode('payWalletBal','mId','payMoneyModal','PAY', 'payTransactionAmountId')">QR Code</button>
                    </div>
                    <div class="text-center DispNone QRCodeImageRow">
                          <div class="card-body QRCode">
                                      
                          </div>
                    </div>
                </div>
		        <div class="card-body">
					<div class="modal-footer">
						<button type="button" class="btn  btn-secondary" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>	
		</div>	
	</div>
</div>