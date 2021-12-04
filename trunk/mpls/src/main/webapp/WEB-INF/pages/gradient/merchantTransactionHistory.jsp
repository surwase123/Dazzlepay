<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<title>MPLS - Merchant Prepaid & Loyalty Solution</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="" />
<meta name="keywords" content="">
<meta name="author" content="Cratas" />
<%-- Favicon icon --%>
<link rel="icon" href="${applicationScope['baseUrl']}/resources/gradient/images/favicon.ico" type="image/x-icon">
<%-- vendor css --%>
<link rel="stylesheet" href="${applicationScope['cssBaseUrl']}/css/vendor.css">
<%-- common css --%>
<link rel="stylesheet" href="${applicationScope['cssBaseUrl']}/gradient/css/style.css">
</head>
<body class="">
<%-- [ Pre-loader ] start --%>
<div class="loader-bg">
	<div class="loader-track">
		<div class="loader-fill"></div>
	</div>
</div>
<%-- [ Pre-loader ] End --%>
<%-- [ navigation menu ] start --%>
<%@include file="jspincludes/menu.jsp"%>
<%-- [ navigation menu ] end --%>
<%-- [ Header ] start --%>
<%@include file="jspincludes/header.jsp"%>
<%-- [ Header ] end --%>
<%-- [ Main Content ] start --%>
<div class="pcoded-main-container">
	<%-- [ Main Content ] start --%>
	<div class="pcoded-content">
		<!-- [ breadcrumb ] start -->
		
		<div class="">
			<div class="page-block">
				<div class="row align-items-center">
					<input type="hidden" name="merchantId" id="merchantId" value="${sessionScope.loginDTO.merchant.id}" />
					<c:if test="${sessionScope.loginDTO.groupDTO.groupType == 'admin'}">
						<div class="col-md-12">
							<div class="form-group col-md-3 row-dropDown">
		                         <select class="form-control js-example-basic-single" name="mId" id="mId" onchange="getMerchantTransReport()">
		                                <c:forEach items="${merchantList}" var="merchant" varStatus="index">
			                                <option value="${merchant.id}">${merchant.merchantName}</option>
			                            </c:forEach>
		                         </select>
	                        </div>
						</div>
						<div class="col-md-12">
						    <div id="reportrange" class="form-control col-md-3 row-dropDown marginBottom25 AdminDateRangePicker">
									<i class="feather icon-calendar featherIcon"></i>&nbsp;
									<span></span> <i class="feather icon-chevron-down featherIcon"></i>
							</div>
						</div>
					</c:if>

					<c:if test="${sessionScope.loginDTO.groupDTO.groupType == 'merchant'}">
					    <div class="col-md-12">
						    <div id="reportrange" class="form-control col-md-3 row-dropDown marginBottom25">
									<i class="feather icon-calendar featherIcon"></i>&nbsp;
									<span></span> <i class="feather icon-chevron-down featherIcon"></i>
							</div>
					    </div>
				    </c:if>
				</div>
			</div>
		</div>
		<!-- [ breadcrumb ] end -->
		<!-- [ Main Content ] start -->
		<div class="row">
			<!-- [ Form Validation ] start -->
			<div class="col-sm-12">
				<div class="card">
					<div class="card user-profile-list">
						<div class="card-body merchantTransactionList">
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
															<p class="m-b-0 lineHight30">${merchantTransaction.merchantName}</p>
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
						</div>
					</div>
				</div>
			</div>
			<!-- [ Form Validation ] end -->
		</div>
		<%-- [ Main Content ] end --%>
	</div>
</div>
<%@include file="jspincludes/footer.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/daterangepicker.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/exportReport.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/merchantTransactionHistory.js" defer onload="loadPageScript()"></script>		
</body>
</html>



