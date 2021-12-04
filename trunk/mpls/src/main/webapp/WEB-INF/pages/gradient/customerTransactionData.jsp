<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="dt-responsive table-responsive">
	<table id="customer-transaction-table" class="table nowrap search-store">
		<thead>
			<tr>
				<c:if test="${sessionScope.loginDTO.groupDTO.groupType == 'admin'}">
					<th>Customer</th>
				</c:if>
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
			<c:forEach items="${customerTransactionList}" var="customerTransaction" varStatus="index">
				<tr onclick="redirectToTransDetail('${customerTransaction.transactionId}', '${customerTransaction.id}')">
					<c:if test="${sessionScope.loginDTO.groupDTO.groupType == 'admin'}">
						<td>
							<div class="d-inline-block align-middle">
								<div class="d-inline-block">
									<p class="m-b-0 lineHight30"><strong>${customerTransaction.firstName} ${customerTransaction.middleName} ${customerTransaction.lastName}</strong></p>
									<p class="m-b-0 lineHight22">${customerIdPrefix}${customerTransaction.customerId}</p>
								</div>
							</div>
						</td>
					</c:if>
					<td>
						<div class="d-inline-block align-middle">
							<div class="d-inline-block">
								<p class="m-b-0 lineHight30"><strong>${customerTransaction.merchantName}</strong></p>
								<p class="m-b-0 lineHight22">${merchantIdPrefix}${customerTransaction.merchantId}</p>
							</div>
						</div>
					</td>
					<td>${customerTransaction.transactionId}</td>
					<td><fmt:formatDate type="both" dateStyle="long" timeStyle="long" value = "${customerTransaction.createdDate}" /></td>
					<td>
						<c:choose>
							<c:when test="${customerTransaction.transactionType == 'PAY'}">
							    SALE
							</c:when>
							<c:otherwise>
							    ${customerTransaction.transactionType}
							</c:otherwise>
						</c:choose>
					</td>
					<td>${customerTransaction.offerCode} </td>
					<td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customerTransaction.transactionValue}" /></td>
				    <td>
				        <c:if test="${empty(customerTransaction.indicator)}">
							                           -
				        </c:if>
				        <c:if test="${!empty(customerTransaction.indicator) && customerTransaction.indicator == 'C'}">
                           <strong>CR</strong>
				        </c:if>
				         <c:if test="${!empty(customerTransaction.indicator) && customerTransaction.indicator == 'D'}">
                           <strong>DR</strong>
				        </c:if>
				    </td>
				    <td>${customerTransaction.payType}</td>
					<td>
					    <c:if test="${customerTransaction.status == 'S'}">
	                           <button type="button" class="btn bg-c-green border border-green rounded btnWhite"><i class="feather icon-thumbs-up"></i></button>
					    </c:if>
					    <c:if test="${customerTransaction.status == 'F'}">
	                           <button type="button" class="btn bg-c-red border border-red rounded btnWhite"><i class="feather icon-thumbs-down"></i></button>
					    </c:if>
					    <c:if test="${customerTransaction.status == 'P'}">
	                          <button type="button" class="btn bg-c-yellow border border-yellow rounded btnWhite"><i class="feather icon-clock"></i></button>
					    </c:if>		
					     <c:if test="${customerTransaction.status == 'R'}">
	                           <button type="button" class="btn bg-c-purple border border-purple rounded btnWhite">R</button>
					    </c:if>				    
				    </td>
				    
				</tr>
		    </c:forEach>
		</tbody>
	</table>
</div>