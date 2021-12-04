
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
<link rel="icon"
	href="${applicationScope['baseUrl']}/resources/gradient/images/favicon.ico"
	type="image/x-icon">
<%-- vendor css --%>
<link rel="stylesheet"
	href="${applicationScope['cssBaseUrl']}/css/vendor.css">
<%-- common css --%>
<link rel="stylesheet"
	href="${applicationScope['cssBaseUrl']}/gradient/css/style.css">
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
								<h5 class="m-b-10">Merchant Employee</h5>
							</div>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}"><i
										class="feather icon-home"></i></a></li>
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}/merchant/employee/view">Merchant Employee</a></li>
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
					<div class="card">
						<div class="card-header">
							<h5>Merchant Employee</h5>
							<hr>
						</div>
						<div class="card-body">
							<c:if test="${is_success == 'success'}">
								<div class="alert alert-success alertMessage" role="alert">
									<strong>Well done!</strong> ${message}
								</div>
							</c:if>
							<c:if test="${is_success == 'failure'}">
								<div class="alert alert-danger alertMessage" role="alert">
									<strong>Oh snap!</strong> ${message}
								</div>
							</c:if>
							<c:if test="${action != 'update'}">
								<c:set value="${applicationScope['baseUrl']}/merchant/employee/add"
									var="actionUrl" />
							</c:if>
							<c:if test="${action == 'update'}">
								<c:set value="${applicationScope['baseUrl']}/merchant/employee/edit"
									var="actionUrl" />
							</c:if>
							<form id="merchantEmployee-form" action="${actionUrl}" method="post">

								<div class="form-row">
	                                        <div class="form-group col-md-4">
	                                            <label for="firstName">First Name</label>
	                                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" value="${merchantEmployee.firstName}"/> 
	                                        </div>
	                                        <div class="form-group col-md-4">
	                                            <label for="middleName">Middle Name</label>
	                                            <input type="text" class="form-control" id="middleName" name="middleName" placeholder="Middle Name" value="${merchantEmployee.middleName}"/> 
	                                        </div>
	                                        <div class="form-group col-md-4">
	                                            <label for="lastName">Last Name</label>
	                                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" value="${merchantEmployee.lastName}"/>
	                                        </div>
	                            </div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="form-label">Email Id</label>
										<input type="text" class="form-control" name="emailId" id="emailId" placeholder="Email Id" value="${merchantEmployee.emailId}" onblur="isExistsMerchantEmailId('emailId', '${merchantEmployee.emailId}')">
									</div>
							    </div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="form-label">Mobile Number</label>
										<input type="text" class="form-control" name="mobileNumber" id="mobileNumber" placeholder="Mobile Number" value="${merchantEmployee.mobileNumber}" onblur="isExistsMerchantMobileNumber('mobileNumber', '${merchantEmployee.mobileNumber}')">
									</div>
								</div>

								<c:if test="${pageRole.subMenuId == 0}">
									<input type="hidden" name="menuName" value="${pageRole.menuName}">
								</c:if>
								<c:if test="${pageRole.subMenuId != 0}">
									<input type="hidden" name="menuName" value="${pageRole.subMenuName}">
								</c:if>
								<input type="hidden" name="createdBy" value="${sessionScope.loginDTO.loginId}">
								<input type="hidden" name="updatedBy" value="${sessionScope.loginDTO.loginId}"> 
								<input type="hidden" name="loginId" id="loginId" value="${merchantEmployee.mobileNumber}">
								<input type="hidden" name="id" value="<c:if test='${!empty(merchantEmployee.id)}'>${merchantEmployee.id}</c:if><c:if test='${empty(merchantEmployee.id)}'>0</c:if>">
								<input type="hidden" name="mId" value="<c:if test='${!empty(merchantEmployee.mId)}'>${merchantEmployee.mId}</c:if><c:if test='${empty(merchantEmployee.mId)}'>0</c:if>">
								<input type="hidden" name="userId" value="<c:if test='${!empty(merchantEmployee.userId)}'>${merchantEmployee.userId}</c:if><c:if test='${empty(merchantEmployee.userId)}'>0</c:if>">
								<input type="hidden" name="isOwner" value="<c:if test='${!empty(merchantEmployee.isOwner)}'>${merchantEmployee.isOwner}</c:if><c:if test='${empty(merchantEmployee.isOwner)}'>0</c:if>">
								<c:if test="${pageRole.isAdd == 1 && empty(isPrivileges)}">
									<div class="col-md-12">
										<button type="submit" class="btn  btn-primary">
											<c:if test="${action != 'update'}">Submit</c:if>
											<c:if test="${action == 'update'}">Update</c:if>
										</button>
										<button class="btn btn-danger" type="reset">Reset</button>
									</div>
								</c:if>
							</form>
						</div>
					</div>
					<c:if test="${!empty(merchantEmployeeList)}">
						<div class="card">
							<div class="card-header">
								<h5>Merchant Employee</h5>
							</div>
							<div class="card-body">
								<div class="dt-responsive table-responsive">
									<table id="multi-colum-dt"
										class="table table-striped table-bordered nowrap">
										<thead>
											<tr>
												<th>#</th>
												<th>Login Id</th>
												<th>First Name</th>
												<th>Middle Name</th>
												<th>Last Name</th>
												<th>Email Id</th>
												<th>Mobile Number</th>
												<th>Is Owner?</th>
												<c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
													<th>Action</th>
												</c:if>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${merchantEmployeeList}" var="merchantEmployee" varStatus="index">
												<tr>
													<td>${index.count}</td>
													<td>${merchantEmployee.loginId}</td>
													<td>${merchantEmployee.firstName}</td>
													<td>${merchantEmployee.middleName}</td>
													<td>${merchantEmployee.lastName}</td>
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
													<c:if test="${!empty(sessionScope.loginDTO.merchantEmployee) && sessionScope.loginDTO.merchantEmployee.isOwner == 1 && (pageRole.isUpdate == 1 || pageRole.isDelete == 1)}">
														<td>
															<c:if test="${pageRole.isUpdate == 1}">
																<a href="javascript:updateMerchantEmployee('${merchantEmployee.userId}', '${sessionScope.loginDTO.loginId}')"><i
																	class="feather icon-edit"></i></a>
															</c:if> <c:if test="${pageRole.isDelete == 1 && merchantEmployee.isOwner != 1}">
																<a href="javascript:deleteMerchantEmployee('${merchantEmployee.userId}', '${sessionScope.loginDTO.loginId}')"><i
																	class="feather icon-trash-2"></i></a>
															</c:if>
														</td>
													</c:if>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:if>
				</div>
				<!-- [ Form Validation ] end -->
			</div>
			<%-- [ Main Content ] end --%>
		</div>
		<%@include file="jspincludes/footer.jsp"%>
		<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
		<script src="${applicationScope['jsBaseUrl']}/javascript/merchantEmployee.js" defer onload="loadPageScript()"></script>
