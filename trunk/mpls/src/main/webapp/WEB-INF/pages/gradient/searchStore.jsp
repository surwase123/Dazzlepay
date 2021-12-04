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
					<div class="col-md-12">
						<div class="form-group col-md-4 row-dropDown">
	                         <select class="form-control js-example-basic-single" name="areaId" id="areaId" onchange="storeFilter('areaId')">
	                                <c:forEach items="${availableAreaList}" var="area" varStatus="index">
	                                      <option value="">--Select Area--</option>
                                          <option value="${area.areaCode}">${area.areaCode}, ${area.pincode}</option>
                                    </c:forEach>
	                         </select>
                        </div>
					</div>
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
						<div class="card-body storeList">
							<div class="dt-responsive table-responsive">
								<table id="search-store-table" class="table nowrap search-store">
									<thead>
										<tr>
											<th class="width35">Merchant Name</th>
											<th class="width22">Industry Type</th>
											<th class="width12">Address</th>
											<th class="width12">Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${recentStoreList}" var="merchant" varStatus="index">
											<tr>
												<td class="width35">
													<div class="d-inline-block align-middle">
														<img data-gsll-src="${merchant.icon}"  class="align-top m-r-15" width="162" height="128">
														<div class="d-inline-block">
															<h5 class="m-b-0 lineHight30">${merchant.merchantName}</h5>
														</div>
													</div>
												</td>
												<td class="width22">
													<div class="d-inline-block">
														<p class="m-b-0 lineHight30">${merchant.categoryCode}</p>
														<p class="m-b-0 lineHight22 searchStoreCategory"> ${merchant.categoryName}</p>
													</div>
												</td>
												<td class="addressStore width6"><a href="javascript:displayMerchantAddress('${merchant.id}', '${merchant.merchantName}(${merchantIdPrefix}${merchant.merchantId}) - Addresses')">Address</a></td>
												<td class="width6">
													<c:if test="${merchant.offerCount > 0}">
													    <button type="button" class="btn bg-c-yellow border border-green btnWhite pointer OfferCard"  data-html="true" onclick="getMerchantOffers('${merchant.id}', '${merchant.merchantName} - Offers')"><i class="fa fa-gift"></i></button>
													</c:if>
													<c:if test="${merchant.isAddedMerchant == 0}">
													    <button type="button" class="MarginLeft13 btn bg-c-green border border-green btnWhite pointer" onclick="mappingMerchant('${merchant.merchantId}')"><i class="feather icon-plus-square"></i></button>
													</c:if>
													<c:if test="${merchant.isAddedMerchant == 2}">
													    <button type="button" class="MarginLeft13 btn bg-c-green border border-green btnWhite pointer" onclick="isAlreadyRequested()"><i class="feather icon-minus-circle"></i></button>
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
	<div id="mMerchantAddressModalDiv" class="DispNone"></div>
	<div id="mMerchantOfferModalDiv" class="DispNone"></div>
</div>
<%@include file="jspincludes/footer.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/searchStore.js" defer onload="loadPageScript()"></script>
		
</body>
</html>



