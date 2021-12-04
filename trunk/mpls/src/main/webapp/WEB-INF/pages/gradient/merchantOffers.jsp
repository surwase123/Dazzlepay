<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${!empty(requestType) && requestType == 'payBills'}">
	<div class="list-group">
		<c:if test="${!empty(loyaltyCashbackList)}">
			<c:forEach items="${loyaltyCashbackList}" var="loyaltyCashback" varStatus="index">
			    <div class="offercard card">
				  <div class="card-body">
				  	    <p><a class="pointer OfferLinkColor"><strong>${loyaltyCashback.offerCode}</strong></a> - ${loyaltyCashback.offerName}</p>
					    <p class="offerText">Min. Transaction <strong>-</strong> ${loyaltyCashback.minTransValue}</p>
					    <c:if test="${loyaltyCashback.cashbackType == 'fixed'}">
					        <p class="offerText">Cashback <strong>-</strong> ${loyaltyCashback.fixedCashbackAmt}</p>
			            </c:if>
			            <c:if test="${loyaltyCashback.cashbackType == 'variable'}">
					        <p class="offerText">Cashback <strong>-</strong> ${loyaltyCashback.cashbackPercentage}%</p>
					        <p class="offerText">Max. Cashback <strong>-</strong> ${loyaltyCashback.maxCashbackAmt}</p>
				        </c:if>			  
				   </div>
				</div>
		    </c:forEach>
		</c:if>
	    <c:if test="${empty(loyaltyCashbackList)}">
	            <p class="offerText"> No Offers!!</p>  
	    </c:if>
	</div>
</c:if>
<c:if test="${!empty(requestType) && requestType == 'searchStore'}">
	<div id="merchantOfferPopupModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="merchantOfferPopupModalitle" style="display: block; padding-right: 15px;" aria-modal="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="merchantOfferPopupModalitle"></h5>
	 			</div>
				<div class="modal-body MerchantOfferPopup">
					<div class="list-group ">
						<c:if test="${!empty(loyaltyCashbackList)}">
							<c:forEach items="${loyaltyCashbackList}" var="loyaltyCashback" varStatus="index">
							    <div class="offercard card">
								  <div class="card-body">
								  	    <p><a class="pointer OfferLinkColor"><strong>${loyaltyCashback.offerCode}</strong></a> - ${loyaltyCashback.offerName}</p>
									    <p class="offerText">Min. Transaction <strong>-</strong> ${loyaltyCashback.minTransValue}</p>
									    <c:if test="${loyaltyCashback.cashbackType == 'fixed'}">
									        <p class="offerText">Cashback <strong>-</strong> ${loyaltyCashback.fixedCashbackAmt}</p>
							            </c:if>
							            <c:if test="${loyaltyCashback.cashbackType == 'variable'}">
									        <p class="offerText">Cashback <strong>-</strong> ${loyaltyCashback.cashbackPercentage}%</p>
									        <p class="offerText">Max. Cashback <strong>-</strong> ${loyaltyCashback.maxCashbackAmt}</p>
								        </c:if>			  
								   </div>
								</div>
						    </c:forEach>
						</c:if>
					    <c:if test="${empty(loyaltyCashbackList)}">
					            <p class="offerText"> No Offers!!</p>  
					    </c:if>
					</div>
				 </div>
				<div class="modal-footer">
					<button type="button" class="btn  btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</c:if>