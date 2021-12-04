
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
                         <select class="form-control js-example-basic-single" name="merchantName" id="merchantName" onchange="getMerchantDetails('merchantName')">
                             <c:forEach items="${merchantList}" var="merchant" varStatus="index">
                                   <option value="${merchant.id}">${merchant.merchantName}</option>
                             </c:forEach>
                         </select>
                     </div>
                </div>
            </c:if>
			<!-- [ breadcrumb ] end -->
		<div id="merchantDetails">	
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
							<c:choose>
								<c:when test="${merchant.icon.length() > 0}">	
									<div class="pro-dp">
										<img  src="${merchant.icon}" alt="">
									</div>
								</c:when>
								<c:otherwise>
									<div class="pro-dp"></div>
								</c:otherwise>
							</c:choose>	
							</div>
							<h5 class="mb-1">${merchant.merchantName}</h5>
							<p class="mb-2 text-muted">${merchant.categoryName}</p>
						</div>
						<div class="col-md-8 mt-md-4">
							<div class="row">
								<div class="col-md-6">
									<a href="mailto:${merchant.emailId}" class="mb-1 text-muted d-flex align-items-end text-h-primary"><i class="feather icon-mail mr-2 f-18"></i>${merchant.emailId}</a>
									<div class="clearfix"></div>
									<a href="#!" class="mb-1 text-muted d-flex align-items-end text-h-primary"><i class="feather icon-phone mr-2 f-18"></i>${merchant.mobileNumber}</a>
								</div>
								<div class="col-md-6">
									<div class="media">
										<i class="feather icon-map-pin mr-2 mt-1 f-18"></i>
										<div class="media-body">
											<c:if test="${!empty(merchantAddressList)}">
												<p class="mb-0 text-muted">${merchantAddressList[0].areaName}</p>
												<p class="mb-0 text-muted">${merchantAddressList[0].cityName}  ${merchantAddressList[0].stateName},</p>
												<p class="mb-0 text-muted">${merchantAddressList[0].pincode}</p>
											</c:if>
										</div>
									</div>
								</div>
							</div>
							<ul class="nav nav-tabs profile-tabs nav-fill" id="myTab" role="tablist">
								<li class="nav-item">
									<a class="nav-link text-reset active" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="true"><i class="feather icon-user mr-2"></i>Profile</a>
								</li>
								<li class="nav-item">
									<a class="nav-link text-reset" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false"><i class="feather icon-briefcase mr-2"></i>Employees</a>
								</li>
								<li class="nav-item">
									<a class="nav-link text-reset" id="contact-tab" data-toggle="tab" href="#customer" role="tab" aria-controls="customer" aria-selected="false"><i class="fas fa-users mr-2"></i>Customers</a>
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
								<c:if test="${!empty(sessionScope.loginDTO.merchantEmployee) && sessionScope.loginDTO.merchantEmployee.isOwner == 1}">
									<button type="button" onclick="updateMerchant('${merchant.id}','${sessionScope.loginDTO.loginId}')" class="btn btn-primary btn-sm rounded m-0 float-right">
										<i class="feather icon-edit"></i>
									</button>
								</c:if>
							</div>
								<div class="card-body border-top pro-det-edit collapse show" id="pro-det-edit-1">
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Merchant Id</label>
										<div class="col-sm-9">${merchantIdPrefix}${merchant.merchantId}</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Merchant Name</label>
										<div class="col-sm-9">${merchant.merchantName}</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Login Id</label>
										<div class="col-sm-9">${merchant.loginId}</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Merchant Category</label>
										<div class="col-sm-9">${merchant.categoryName}</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Email</label>
										<div class="col-sm-9">${merchant.emailId}</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Mobile Number</label>
										<div class="col-sm-9">${merchant.mobileNumber}</div>
									</div>
								</div>
						</div>
						<div class="card">
							<div class="card-body d-flex align-items-center justify-content-between">
								<h5 class="mb-0">Merchant Address</h5>
							</div>
							<div class="row">
								<div class="card-body">
                            		<div class="dt-responsive table-responsive">
                                		<table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    		<thead>
                                        		<tr>
                                            		<th>#</th>
													<th>State</th>
													<th>City</th>
													<th>Area</th>
													<th>Pin code</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${merchantAddressList}" var="address" varStatus="index">
	                                            	<tr>
		                                                <td>${index.count}</td>
		                                                <td>${address.stateName}</td>
		                                                <td>${address.cityName}</td>
		                                                <td>${address.areaCode}</td>
		                                                <td>${address.pincode}</td>
	                                                </tr>
                                                </c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="card">
							<div class="card-body d-flex align-items-center justify-content-between">
								<h5 class="mb-0">Other Information</h5>
							</div>
							<div class="card-body border-top pro-wrk-edit collapse show" id="pro-wrk-edit-1">
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">Pan Number</label>
										<div class="col-sm-9">${merchant.PANNumber}</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">GSTIN</label>
										<div class="col-sm-9">${merchant.GSTIN}</div>
									</div>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label font-weight-bolder">About</label>
										<div class="col-sm-9">${merchant.aboutMe}</div>
									</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
						<div class="row">
							<div class="col-md-12 order-md-2">
								<div class="card">
									<div class="card-header">
										<h5>Merchant Employee</h5>
									</div>
									<div class="card-body">
										<div class="dt-responsive table-responsive">
											<table id="multi-colum-employee" class="table table-striped table-bordered nowrap">
												<thead>
													<tr>
														<th>#</th>
														<th>Login Id</th>
														<th>Emp Name</th>
														<th>Email Id</th>
														<th>Mobile Number</th>
														<th>Is Owner?</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${merchantEmployeeList}" var="merchantEmployee" varStatus="index">
														<tr>
															<td>${index.count}</td>
															<td>${merchantEmployee.loginId}</td>
															<td>${merchantEmployee.firstName} ${merchantEmployee.middleName} ${merchantEmployee.lastName}</td>
															<td>${merchantEmployee.emailId}</td>
															<td>${merchantEmployee.mobileNumber}</td>
															<td>
																<c:if test='${merchantEmployee.isOwner == 1}'> 
														        Yes
															    </c:if>
															    <c:if test='${merchantEmployee.isOwner != 1}'> 
																        No
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
					</div>
					<div class="tab-pane fade" id="customer" role="tabpanel" aria-labelledby="customer-tab">
						<div class="row">
							<div class="col-md-12 order-md-2">
								<div class="card">
									<div class="card-header">
										<h5>Customer</h5>
									</div>
									<div class="card-body">
										<div class="dt-responsive table-responsive">
											<table id="multi-colum-customer" class="table table-striped table-bordered nowrap">
												<thead>
													<tr>
														<th>#</th>
														<th>Customer Id</th>
														<th>Login Id</th>
														<th>Name</th>
														<th>Email Id</th>
														<th>Mobile Number</th>
														<th>Wallet Balance</th>
														
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${customerList}" var="customer" varStatus="index">
														<tr>
															<td>${index.count}</td>
															<td>${customer.customerId}</td>
															<td>${customer.loginId}</td>
															<td>${customer.firstName} ${customer.middleName} ${customer.lastName}</td>
															<td>${customer.emailId}</td>
															<td>${customer.mobileNumber}</td>
															<td>${customer.walletBal}</td>
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
<script src="${applicationScope['jsBaseUrl']}/javascript/merchantProfile.js" defer onload="loadPageScript()"></script>
</body>
</html>
