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
<link rel="stylesheet"
	href="${applicationScope['cssBaseUrl']}/css/vendor.css">
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
			 <c:if test="${sessionScope.loginDTO.groupDTO.groupType == 'admin'}">
				<div>
                     <div class="form-group col-md-4 row-dropDown">
                         <select class="form-control js-example-basic-single" name="customerName" id="customerName" onchange="getCustomerDetails('customerName')">
                             <c:forEach items="${customerList}" var="customer" varStatus="index">
                                   <option value="${customer.id}">${customer.firstName}${customer.lastName}</option>
                             </c:forEach>
                         </select>
                     </div>
                </div>
            </c:if>
			<!-- [ breadcrumb ] end -->
		<div id="customerDetails">	
		<div class="user-profile user-card mb-4">
			<div class="card-header border-0 p-0 pb-0">
				<div >
					<img src="${applicationScope['jsBaseUrl']}/gradient/images/background.jpg" class="img-fluid MProfile">
				</div>
			</div>

			<div class="card-body py-0">
				<div class="user-about-block m-0">
					<div class="row">
						<div class="col-md-4 text-center mt-n5">
								<div class="dropdown w-auto d-inline-block">
										<div class="pro-dp"></div>
								</div>
							<h5 class="mb-1">${customer.firstName} ${customer.lastName}</h5>
						</div>
						<div class="col-md-8 mt-md-4">
							<div class="row">
								<div class="col-md-6">
									<a href="mailto:${customer.emailId}" class="mb-1 text-muted d-flex align-items-end text-h-primary"><i class="feather icon-mail mr-2 f-18"></i>${customer.emailId}</a>
									<div class="clearfix"></div>
									<a href="#!" class="mb-1 text-muted d-flex align-items-end text-h-primary"><i class="feather icon-phone mr-2 f-18"></i>${customer.mobileNumber}</a>
								</div>
							</div>
							<ul class="nav nav-tabs profile-tabs nav-fill" id="myTab" role="tablist">
								<li class="nav-item">
									<a class="nav-link text-reset active" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="true"><i class="feather icon-user mr-2"></i>Profile</a>
								</li>
								<li class="nav-item">
									<a class="nav-link text-reset" id="contact-tab" data-toggle="tab" href="#merchant" role="tab" aria-controls="merchant" aria-selected="false"><i class="fas fa-users mr-2"></i>Merchant</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- profile header end -->

		<!-- profile body start -->
		<div class="row">
			<div class="col-md-12 order-md-2">
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="profile" role="tabpanel" aria-labelledby="profile-tab">
						<div class="card">
							<div class="card-body d-flex align-items-center justify-content-between">
								<h5 class="mb-0">Personal details</h5>
							</div>
								<div class="card-body border-top pro-det-edit collapse show" id="pro-det-edit-1">
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Customer Id</label>
										<div class="col-sm-9">${customerIdPrefix}${customer.customerId}</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Customer Name</label>
										<div class="col-sm-9">${customer.firstName} ${customer.lastName}</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Email</label>
										<div class="col-sm-9">${customer.emailId}</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Mobile Number</label>
										<div class="col-sm-9">${customer.mobileNumber}</div>
									</div>
								</div>
						</div>
					</div>
					<div class="tab-pane fade" id="merchant" role="tabpanel" aria-labelledby="merchant-tab">
						<div class="row">
							<div class="col-md-12 order-md-2">
								<div class="card">
									<div class="card-header">
										<h5>Merchant</h5>
									</div>
									<div class="card-body">
										<div class="dt-responsive table-responsive">
											<table id="multi-colum-merchant" class="table table-striped table-bordered nowrap">
												<thead>
													<tr>
														<th>#</th>
														<th>Merchant Id</th>
														<th>Merchant Name</th>
														<th>Email Id</th>
														<th>Mobile Number</th>
														<th>Wallet Balance</th>
														
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${merchantList}" var="merchant" varStatus="index">
														<tr>
															<td>${index.count}</td>
															<td>${merchant.merchantId}</td>
															<td>${merchant.merchantName}</td>
															<td>${merchant.emailId}</td>
															<td>${merchant.mobileNumber}</td>
															<td>${merchant.walletBal}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		<!-- profile body end -->
		</div>
	</div>
 </div>
</div>
	
<%@include file="jspincludes/footer.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/exportReport.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/customerProfile.js" defer onload="loadPageScript()"></script>
</body>
</html>
