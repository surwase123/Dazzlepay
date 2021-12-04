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
		<!-- [ Main Content ] start -->
		<div class="row">
			<!-- [ Form Validation ] start -->
			<div class="col-sm-12">
				<div class="card">
					<div class="card user-profile-list">
						<div class="card-body storeList">
							<div class="dt-responsive table-responsive">
								<table id="customer-merchant-table" class="table nowrap search-store">
									<thead>
										<tr>
											<th class="width35">Merchant Name</th>
											<th class="width35">Industry Type</th>
											<!-- <th class="width12">GSTIN</th> -->
											<th class="width12">Address</th>
											<th class="width12">Wallet Balance</th>
											<!-- <th class="width6">Action</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${customerMerchantMappingList}" var="merchant" varStatus="index">
											<tr>
												<td class="width35">
													<div class="d-inline-block align-middle">
														<c:if test="${merchant.icon.length() > 0}">
															<img data-gsll-src="${merchant.icon}" alt="user image" class="align-top m-r-15" width="162" height="128">
														</c:if>
														<div class="d-inline-block">
															<h5 class="m-b-0 lineHight30">${merchant.merchantName}</h5>
															<%-- <p class="m-b-0 lineHight22">${merchantIdPrefix}${merchant.merchantId}</p>
															<p class="m-b-0 lineHight22"><i class="feather icon-mail"></i> <a href = "mailto: ${merchant.emailId}">${merchant.emailId}</a></p> --%>
															<p class="m-b-0 lineHight22"><i class="feather icon-phone-call"></i> <a href="tel: ${merchant.mobileNumber}"> ${merchant.mobileNumber}</a></p>
															<%-- <p class="m-b-0 lineHight22">PAN :  ${merchant.PANNumber}</p> --%>
														</div>
													</div>
												</td>
												<td class="width35">
													<div class="d-inline-block">
														<p class="m-b-0 lineHight30">${merchant.categoryCode}</p>
														<p class="m-b-0 lineHight22 searchStoreCategory"> ${merchant.categoryName}</p>
													</div>
												</td>
												<%-- <td class="width12">${merchant.GSTIN}</td> --%>
												<td class="addressStore width12"><a href="javascript:displayMerchantAddress('${merchant.id}', '${merchant.merchantName}(${merchantIdPrefix}${merchant.merchantId}) - Addresses')">Address</a></td>
												<td class="width12"><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${merchant.walletBal}" /></td>
												<%-- <td class="width6">
													<button type="button" class="btn bg-c-red border border-green rounded btnWhite" onclick="deleteMerchant('${merchant.id}', '${merchant.cId}', 'customerMerchant')"><i class="fas fa-trash"></i></button>
												</td>  --%>
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
	<div id="mMerchantAddressModalDiv" class="DispNone"></div>
</div>
<%@include file="jspincludes/footer.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/customerMerchant.js" defer onload="loadPageScript()"></script>
		
</body>
</html>



