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
<body>
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
			<div class="page-header">
				<div class="page-block">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="page-header-title">
								<h5 class="m-b-10">Transaction Detail</h5>
							</div>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
									<li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/merchant/transactionHistory/view">Transaction History</a></li>
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}/merchant/transactionHistory/detail/${transTableId}/${transactionId}">Transaction Detail</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- [ breadcrumb ] end -->
			<!-- [ Main Content ] start -->
			<div class="row">
				<!-- [ Form Validation ] start -->
				<div class="col-sm-12">
					  <div class="transactionDetail" id="transactionDetail">
	                     <div class="card">
	                        <div class="card-header center">
	                            <h5>Transaction Detail - ${transactionId}</h5>
	                        </div>
	                        <div class="card-body task-details">
	                        	<c:if test="${!empty(transactionDetail)}">
		                            <table class="table table-striped table-bordered table-whitespace">
		                                <tbody>
		                                	<tr>
		                                        <td class="center"><strong>Customer Name</strong></td>
		                                        <td class="center">${transactionDetail.firstName} ${transactionDetail.middleName} ${transactionDetail.lastName}</td>
		                                    </tr>
		                                    <tr>
		                                        <td class="center"><strong>Customer Id</strong></td>
		                                        <td class="center">${customerIdPrefix}${transactionDetail.customerId}</td>
		                                    </tr>
		                                    <tr>
		                                        <td class="center"><strong>Merchant</strong></td>
		                                        <td class="center">${transactionDetail.merchantName}</td>
		                                    </tr>
		                                    <tr>
		                                        <td class="center"><strong>Merchant Id</strong></td>
		                                        <td class="center">${merchantIdPrefix}${transactionDetail.merchantId}</td>
		                                    </tr>
		                                    <tr>
		                                        <td class="center"><strong>TransactionId</strong></td>
		                                        <td class="center">${transactionDetail.transactionId}</td>
		                                    </tr>
		                                    <tr>
		                                        <td class="center"><strong>Transaction Time</strong></td>
		                                        <td class="center"><fmt:formatDate pattern = "dd MMM, YYYY HH:MM" value = "${transactionDetail.createdDate}" /></td>
		                                    </tr>
		                                    <tr>
		                                        <td class="center"><strong>Transaction Type</strong></td>
		                                        <td class="center">
													<c:choose>
														<c:when test="${transactionDetail.transactionType == 'PAY'}">
														    SALE
														</c:when>
														<c:otherwise>
														    ${transactionDetail.transactionType}
														</c:otherwise>
													</c:choose>
												</td>
		                                    </tr>
		                                    <tr>
		                                        <td class="center"><strong>Transaction Amount</strong></td>
		                                        <td class="center"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${transactionDetail.transactionValue}" /></td>
		                                    </tr>
		                                    <tr>
		                                        <td class="center"><strong>Indicator</strong></td>
		                                        <td class="center">
													<c:if test="${empty(transactionDetail.indicator)}">
														- 
							                        </c:if>
													<c:if test="${!empty(transactionDetail.indicator) && transactionDetail.indicator == 'C'}">
														<strong>CR</strong>
													</c:if>
													<c:if test="${!empty(transactionDetail.indicator) && transactionDetail.indicator == 'D'}">
														<strong>DR</strong>
													</c:if>
												</td>
		                                    </tr>
		                                    <tr>
		                                        <td class="center"><strong>Pay Type</strong></td>
		                                        <td class="center">${transactionDetail.payType}</td>
		                                    </tr>
		                                    <tr>
		                                        <td class="center"><strong>Status</strong></td>
		                                        <td class="center">
													<c:if test="${transactionDetail.status == 'S'}">
														<button type="button" class="btn bg-c-green border border-green rounded btnWhite"><i class="feather icon-thumbs-up"></i></button>
													</c:if>
													<c:if test="${transactionDetail.status == 'F'}">
														<button type="button" class="btn bg-c-red border border-red rounded btnWhite"><i class="feather icon-thumbs-down"></i></button>
													</c:if>
													<c:if test="${transactionDetail.status == 'P'}">
														<button type="button" class="btn bg-c-yellow border border-yellow rounded btnWhite"><i class="feather icon-clock"></i></button>
													</c:if>
													<c:if test="${transactionDetail.status == 'R'}">
													    <button type="button" class="btn bg-c-purple border border-purple rounded btnWhite">R</button>
												    </c:if>
												</td>
		                                    </tr>
		                                    <c:if test="${transactionDetail.transactionType == 'PAY'}">
			                                    <tr>
			                                        <td class="center"><strong>Offer Code</strong></td>
			                                        <td class="center">${transactionDetail.offerCode}</td>
			                                    </tr>
			                                    <tr>
			                                        <td class="center"><strong>Cash back Amount</strong></td>
		                                       		<td class="center"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${transactionDetail.cashbackAmt}" /></td>
			                                    </tr>
			                                </c:if>
		                                    <c:if test="${transactionDetail.status == 'S' && (transactionDetail.transactionType == 'PAY')}">
			                                    <tr>
			                                        <td class="center" colspan="2"><a href="javascript:refundTransaction('${transactionDetail.id}', '${transactionDetail.transactionId}', '${transactionDetail.transactionType}')">Refund Transaction</a></td>
			                                    </tr>
			                                </c:if>
		                                </tbody>
		                            </table>
		                        </c:if>
		                        <c:if test="${empty(transactionDetail)}">
		                              <div class="alert alert-danger alertMessage" role="alert">
										   <strong>Oh snap!</strong> Transaction Detail Not Found!!
									  </div>
		                        </c:if>
	                        </div>
	                    </div>
	                </div>
				</div>
				<!-- [ Form Validation ] end -->
			</div>
			<%-- [ Main Content ] end --%>
</div>
<%@include file="jspincludes/footer.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/customerTransaction.js" defer onload="loadPageScript()"></script>
</body>
</html>
