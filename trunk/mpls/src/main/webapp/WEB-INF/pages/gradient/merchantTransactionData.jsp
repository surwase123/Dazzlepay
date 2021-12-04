<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="dt-responsive table-responsive">
	<table id="merchant-transaction-table" class="table nowrap search-store">
		<thead>
			<tr>
				<th>Customer</th>
				<th>Merchant</th>
				<th>TransactionId</th>
				<th>Transaction Time</th>
				<th>Transaction Type</th>
				<th>Offer Code</th>
				<th>Transaction Amount</th>
				<th>Indicator</th>
				<th>Pay Type</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${merchantTransactionList}" var="merchantTransaction" varStatus="index">
				<tr onclick="redirectToTransDetail('${merchantTransaction.transactionId}', '${merchantTransaction.id}')">
					<td>
						<div class="d-inline-block align-middle">
							<c:if test="${!empty(merchantTransaction.customerId)}">
								<div class="d-inline-block">
									<p class="m-b-0 lineHight30">${merchantTransaction.firstName} ${merchantTransaction.middleName} ${merchantTransaction.lastName}</p>
									<p class="m-b-0 lineHight22">${customerIdPrefix}${merchantTransaction.customerId}</p>
								</div>
							</c:if>
						</div>
					</td>
					<td>
						<div class="d-inline-block align-middle">
							<div class="d-inline-block">
								<p class="m-b-0 lineHight30"><strong>${merchantTransaction.merchantName}</strong></p>
								<p class="m-b-0 lineHight22">${merchantIdPrefix}${merchantTransaction.merchantId}</p>
							</div>
						</div>
					</td>
					<td>${merchantTransaction.transactionId}</td>
					<td>
						<fmt:formatDate type="both" dateStyle="long" timeStyle="long" value = "${merchantTransaction.createdDate}" />
					</td>
					<td>
						<c:choose>
							<c:when test="${merchantTransaction.transactionType == 'PAY'}">
							    SALE
							</c:when>
							<c:otherwise>
							    ${merchantTransaction.transactionType}
							</c:otherwise>
						</c:choose>
					</td>
					<td>${merchantTransaction.offerCode} </td>
					<td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${merchantTransaction.transactionValue}" /></td>
					<td>
				        <c:if test="${empty(merchantTransaction.indicator)}">
							    -
				        </c:if>
				        <c:if test="${!empty(merchantTransaction.indicator) && merchantTransaction.indicator == 'C'}">
                           <strong>CR</strong>
				        </c:if>
				         <c:if test="${!empty(merchantTransaction.indicator) && merchantTransaction.indicator == 'D'}">
                           <strong>DR</strong>
				        </c:if>
				    </td>
                    <td>${merchantTransaction.payType}</td>
					<td>
					    <c:if test="${merchantTransaction.status == 'S'}">
                               <button type="button" class="btn bg-c-green border border-green rounded btnWhite"><i class="feather icon-thumbs-up"></i></button>
					    </c:if>
					    <c:if test="${merchantTransaction.status == 'F'}">
                               <button type="button" class="btn bg-c-red border border-red rounded btnWhite"><i class="feather icon-thumbs-down"></i></button>
					    </c:if>
					    <c:if test="${merchantTransaction.status == 'P'}">
	                           <button type="button" class="btn bg-c-yellow border border-yellow rounded btnWhite"><i class="feather icon-clock"></i></button>
					    </c:if>
					    <c:if test="${merchantTransaction.status == 'R'}">
	                           <button type="button" class="btn bg-c-purple border border-purple rounded btnWhite">R</button>
					    </c:if>		
				    </td>
				</tr>
		    </c:forEach>
		</tbody>
	</table>
</div>