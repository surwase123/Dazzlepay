<div id="payMoneyModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="payMoneyModalTitle" aria-modal="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="row">
                <div class="col-sm-12">
                    <div class="card-header">
                        <h5 class="modal-title" id="payMoneyModalTitle">Pay money</h5>
                        <hr/>
                    </div>
                    <div class="col-md-12">
                         <div class="form-group">
                             <label class="form-label">Amount</label>
                             <input type="text" class="form-control decimal" name="payWalletBal" id="payWalletBal" placeholder="Amount">
                         </div>
                    </div>
                     <div class="form-row">
                            <div class="form-group col-md-10 MarginLeft7">
                                <label for="firstName">Coupon Code</label>
                                <input type="text" class="form-control" id="offerCode" name="offerCode" value="" placeholder="Merchant Offer Code" readonly="" /> 
                            </div>
                            <div class="form-group col-md-1 MarginTop27 OfferCardDiv">
                               <button type="button" class="btn bg-c-yellow border border-green btnWhite pointer OfferCard"  data-html="true" data-placement="top" data-poload="${applicationScope['baseUrl']}/merchant/loyaltyCashback/getMerchantOffers/${sessionScope.loginDTO.merchant.id}?requestType=payBills"><i class="fa fa-gift"></i></button>
                            </div>
                            <div class="form-group col-md-1 MarginTop27 DispNone offerRemove">
                               <button type="button" class="btn bg-c-red border border-red btnWhite pointer" onclick="removeOffer('offerCode')"><i class="fas fa-window-close"></i></button>
                            </div>
                    </div>
                    <div class="form-group row MarginLeft7">
                        <div class="col-sm-9">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" type="walletBalanceCheckBox" id="walletBalanceCheckBox" checked="" disabled="">
                                <label class="form-check-label" for="walletBalance">Wallet Balance</label>
                            </div>
                            <div class="MarginLeft7 offerText">Available Balance <span id="availableWalletBalance"></span></div>
                        </div>
                    </div>
                    
                    <input type="hidden" name="payCId" id="payCId">
                    <input type="hidden" name="payMId" id="payMId">
                    <input type="hidden" name="payTransactionAmountId" id="payTransactionAmountId">
                    <div class="alert alert-danger msgPayResponse DispNone" role="alert"></div>
                     <div class="alert alert-danger offerMsgPayResponse DispNone" role="alert"></div>
                    <div class="col-md-12 text-center">
                         <button type="button" class="btn btn-danger" onclick="generateQRCode('payWalletBal','payCId','payMId','payMoneyModal','PAY', 'payTransactionAmountId', 'offerCode')">QR Code</button>
                         <button type="button" class="btn btn-primary" onclick="payMoneyWallet('payWalletBal','payCId','payMId','payMoneyModal','PAY', 'payTransactionAmountId', 'offerCode')">Pay Bill</button>
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