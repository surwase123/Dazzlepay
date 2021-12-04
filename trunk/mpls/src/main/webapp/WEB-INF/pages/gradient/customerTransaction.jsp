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
		<!-- [ breadcrumb ] end -->
		<!-- [ Main Content ] start -->
		<div class="row">
			<!-- [ Form Validation ] start -->
			<div class="col-sm-12">
				<div class="card">
					<div class="card user-profile-list">
						<div class="card-body storeList">
							<div class="dt-responsive table-responsive">
								<table id="search-store-table" class="table nowrap search-store">
									<thead>
										<tr>
											<th>Customer Id</th>
											<th>Customer Name</th>
											<th>Email Id</th>
											<th>mobile Number</th>
											<th>Wallet Balance</th>
											<!-- <th>Transaction</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${customerList}" var="customer" varStatus="index">
											<tr>
												<td>${customerIdPrefix}${customer.customerId}</td>
												<td>${customer.firstName} ${customer.middleName} ${customer.lastName}</td>
												<td>${customer.emailId}</td>
												<td>${customer.mobileNumber}</td>
												<td id="transactionAmount${index.count}">
													&#8377; <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customer.walletBal}" />
                                                </td>
												<%-- <c:if test="${!empty sessionScope.loginDTO.merchant}">
													<td>
														<button type="button" class="btn bg-c-green border border-green rounded btnWhite pointer" title="Add Money" onclick="openAddMoneyModal('${customer.cId}','${customer.mId}','${index.count}', 'Add Money - ${customerIdPrefix}${customer.customerId}', '${customer.walletBal}')"><i class="fas fa-wallet"></i></button>
														<button type="button" class="MarginLeft13 btn bg-c-yellow border border-yellow rounded btnWhite pointer" title="Pay Bills"  onclick="payMoney('${customer.cId}','${customer.mId}','${index.count}', 'Pay Money - ${merchantIdPrefix}${sessionScope.loginDTO.merchant.merchantId}', '${customer.walletBal}')"><i class="fas fa-money-bill"></i></button>
													</td>
													
												</c:if> --%>
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
<input type="hidden" name="updatedBy" id="updatedBy" value="${sessionScope.loginDTO.loginId}">
<%@include file="addMoneyModal.jsp"%>
<%@include file="payMoneyModal.jsp"%>
<%@include file="jspincludes/footer.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/customerTransaction.js" defer onload="loadPageScript()"></script>
		
</body>
</html>



