<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/tools.tld" prefix="r"%>
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
			<div class="page-header">
				<div class="page-block">
					<div class="row align-items-center">
						<div class="col-md-12">
							<div class="page-header-title">
								<h5 class="m-b-10">Merchant Registration</h5>
							</div>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}"><i
										class="feather icon-home"></i></a></li>
								<li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/merchant/registration/view">Merchant Registration</a></li>
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
							<h5>Merchant Registration</h5>
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
								<c:set value="${applicationScope['baseUrl']}/merchant/add" var="actionUrl" />
							</c:if>
							<c:if test="${action == 'update'}">
								<c:set value="${applicationScope['baseUrl']}/merchant/edit" var="actionUrl" />
							</c:if>
							<form id="merchantRegistration-form" method="post" action="${actionUrl}">
								<c:if test="${action == 'update'}">	
									<div class="col-md-12">
										<div class="form-group">
											<label class="form-label">Industry Type</label> 
											 <select class="form-control js-example-basic-single" name="categoryCode" id="categoryCode">
	                                            <option value="">--Select Industry Type--</option>
												<c:forEach items="${categoryList}" var="category" varStatus="index">
		                                                <option value="${category.categoryCode}" <c:if test="${merchant.categoryCode == category.categoryCode}">selected</c:if>>${category.categoryName}</option>
		                                        </c:forEach>
		                                    </select>
										</div>
									</div>
								</c:if>	
								<div class="col-md-12">
									<div class="form-group">
										<label class="form-label">Merchant Name<b class="mandetory">*</b></label><input type="text" class="form-control capital-letter" name="merchantName" id="merchantName" placeholder="Merchant Name" value="${merchant.merchantName}">
									</div>
								</div>
								<c:if test="${action == 'update'}">
									<div class="form-row">
	                                        <div class="form-group col-md-6">
	                                            <label for="firstName">Country</label>
	                                            <select class="form-control js-example-basic-single" name="countryId" id="countryId" onchange="fillCurrency('countryId', 'currencyCode')">
		                                            <option value="0">--Select Country--</option>
													<c:forEach items="${countryList}" var="country" varStatus="index">
			                                            <option value="${country.id}" <c:if test="${merchant.countryId == country.id}">selected</c:if>>${country.countryName}</option>
			                                        </c:forEach>
		                                        </select>
	                                        </div>
	                                        <div class="form-group col-md-6">
	                                            <label for="lastName">Currency</label>
	                                            <select class="form-control js-example-basic-single" name="currencyCode" id="currencyCode">
		                                            <option value="0">--Select Currency--</option>
													<c:forEach items="${currencyList}" var="currency" varStatus="index">
			                                            <option value="${currency.currencyCode}" <c:if test="${merchant.currencyCode == currency.currencyCode}">selected</c:if>>${currency.currencyName}</option>
			                                        </c:forEach>
		                                        </select>
	                                        </div>
	                                </div>
	                                 <div class="form-row">
	                                        <div class="form-group col-md-6">
	                                            <label for="firstName">PAN Number</label>
	                                            <input type="text" class="form-control" id="PANNumber" name="PANNumber" placeholder="PAN Number" value="${merchant.PANNumber}" onblur="isExistsPANNumber('PANNumber')">
	                                        </div>
	                                        <div class="form-group col-md-6">
	                                            <label for="lastName">GSTIN</label>
	                                            <input type="text" class="form-control" id="GSTIN" name="GSTIN" placeholder="GSTIN" value="${merchant.GSTIN}" onblur="isExistsGSTIN('GSTIN')">
	                                        </div>
	                                </div>
	                                <div class="col-md-12">
	                                        <div class="input-group form-group">
	                                            <div class="input-group-prepend">
	                                                <span class="input-group-text">Image</span>
	                                            </div>
	                                            <c:set var="ignoreInput" value=""/>
	                                            <c:if test="${action == 'update'}">
	                                                <c:set var="ignoreInput" value="ignore"/>
	                                            </c:if>
	                                            <div class="custom-file">
	                                                <input type="file" class="custom-file-input ${ignoreInput}" id="file" name="file">
	                                                <label class="custom-file-label" for="file">Choose file</label>
	                                            </div>
	                                        </div>
	                                        <input type="hidden" id="icon" name="icon" value="${merchant.icon}">
	                                </div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="form-label">About Me</label>
											<textarea class="form-control" id="aboutMe" name="aboutMe" rows="3">${merchant.aboutMe}</textarea>
										</div>
									</div>
								</c:if>	
                                <h5 class="mt-5">Contact Details</h5>
                                <hr>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="firstName">First Name<b class="mandetory">*</b></label>
                                        <input type="text" class="form-control" id="firstName" name="firstName" value="${merchant.firstName}" placeholder="First Name"/> 
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="middleName">Middle Name</label>
                                        <input type="text" class="form-control" id="middleName" name="middleName" value="${merchant.middleName}" placeholder="Middle Name"/> 
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="lastName">Last Name<b class="mandetory">*</b></label>
                                        <input type="text" class="form-control" id="lastName" name="lastName" value="${merchant.lastName}" placeholder="Last Name"/>
                                    </div>
                                </div>
								<div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="firstName">Email Id<b class="mandetory">*</b></label>
                                            <input type="text" class="form-control" id="emailId" name="emailId" value="${merchant.emailId}" placeholder="Email Id" onblur="isExistsEmailId('emailId', '${merchant.emailId}');verifyEmailAddress('emailId', '${merchant.emailId}')"/> 
                                        </div>
                                        <!-- <div class="form-group col-md-1 VerifyEmailBtn">
                                        	<button type="button" class="btn btn-success verifyEmailBtn" onclick="sendVerificationCode('emailId', 'merchant', 'Email Id', 'verificationModal')" disabled="">Verify</button>
                                        </div> -->
                                        <div class="form-group col-md-6">
                                            <label for="lastName">Mobile Number<b class="mandetory">*</b></label>
                                            <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" value="${merchant.mobileNumber}" placeholder="Mobile Number"  <c:if test="${action == 'update'}">readonly</c:if> onblur="isExistsMobileNumber('mobileNumber', '${merchant.mobileNumber}');verifyMobileNumber('mobileNumber', '${merchant.mobileNumber}')"/>
                                        </div>
                                        <!-- <div class="form-group col-md-1 VerifyMobileBtn">
                                        	<button type="button" class="btn btn-success verifyMobileBtn" onclick="sendMobileVerificationCode('mobileNumber', 'merchant', 'Mobile Number', 'mobileVerificationModal')" disabled="">Verify</button>
                                        </div> -->
                                </div>
								<c:if test="${action == 'update'}">
								<h5 class="mt-5">Address</h5>
                                <hr>
								<div class="col-md-12 column">
									<table class="table table-bordered table-hover" id="merchantAddressTable">
										<thead>
											<tr>
												<th class="text-center width6">#</th>
												<th class="text-center width24">State<b class="mandetoryFields">*</b></th>
												<th class="text-center width24">City<b class="mandetoryFields">*</b></th>
												<th class="text-center width24">Area<b class="mandetoryFields">*</b></th>
												<th class="text-center width22">Pincode<b class="mandetoryFields">*</b></th>
											</tr>
										</thead>
									    <tbody>
											<c:if test="${empty(merchantAddressList)}">
										            <input type="hidden" name="rowNumber" id="rowNumber" value="0">
		                                            <tr id='merchantAddress0'>
		                                                <td class="text-center width6">1</td>
		                                                <td class="width24">
															<div class="form-group col-md-12">
																<select class="form-control state js-example-basic-single" id="state0" name="state0" onchange="fillCity('state0','city0')">
																	<option value="">--Select State--</option>
																	<c:forEach items="${stateList}" var="state" varStatus="index">
			                                                      		<option value="${state.id}">${state.stateName}</option>
			                                               			</c:forEach>
																</select>
															</div>
													    </td>
														<td class="width24">		
															<div class="form-group col-md-12">
																<select class="form-control city js-example-basic-single" id="city0" name="city0">
																	<option value="">--Select City--</option>
																</select>
															</div>
														</td>
														<td class="width24">		
															<div class="form-group col-md-12">
								                            	<textarea name="areaCode0" id="areaCode0" class="form-control area"></textarea>
				                       						</div>
														</td>
														<td class="width22">		
															<div class="form-group col-md-12">
																<input type="text" class="form-control pincode" id="pincode0" name="pincode0" placeholder="pincode" />
															</div>
														</td>		
													</tr>
													<tr id='merchantAddress1'></tr>
											</c:if>
											<c:if test="${!empty(merchantAddressList)}">
														<input type="hidden" name="rowNumber" id="rowNumber" value="${fn:length(merchantAddressList)}">
			                                            <c:forEach items="${merchantAddressList}" var="merchantAddress" varStatus="index">
			                                                <c:set var="count" value="${index.count - 1}"/>
			                                                <tr id='merchantAddress${count}'>
			                                                    <td>${count + 1}</td>
			                                                    <td>
																	<div class="form-group col-md-12">
																		<select class="form-control state" id="state${count}" name="state${count}" onchange="fillCity('state${count}','city${count}')">
																			<option value="">--Select State--</option>
																			<c:forEach items="${stateList}" var="state" varStatus="index">
			                                                      				<option value="${state.id}" <c:if test="${state.id == merchantAddress.stateId}">selected</c:if>>${state.stateName}</option>
			                                               					 </c:forEach>
																		</select>
																	</div>
															</td>
															<td>		
																<div class="form-group col-md-12">
																	<select class="form-control city" id="city${count}" name="city${count}">
																		<option value="">--Select City--</option>
																		<c:forEach items="${cityList}" var="city" varStatus="index">
			                                                  				<option value="${city.id}" <c:if test="${city.id == merchantAddress.cityId}">selected</c:if>>${city.cityName}</option>
			                                           					</c:forEach>
																	</select>
																</div>
															</td>
															<td>		
																<div class="form-group col-md-12">
				                            						<textarea name="areaCode${count}" id="areaCode${count}" class="form-control area">${(merchantAddress.areaCode)}</textarea>
																</div>
															</td>
															<td>		
																<div class="form-group col-md-12">
																	<input type="text" class="form-control pincode" id="pincode${count}" name="pincode${count}" placeholder="Pincode" value="${merchantAddress.pincode}"/>
																</div>
															</td>		
													    </tr>
													</c:forEach>
													<tr id='merchantAddress${fn:length(merchantAddressList)}'></tr>
											    </c:if>
                                            </tbody>
                                        </table>
									    <div class="Bottom100">
                                                <button type="button" id="add_row" class="btn btn-default pull-left">Add Row</button><button type="button" id='delete_row' class="pull-right btn btn-default">Delete Row</a>
                                        </div>  
									</div>
									</c:if>
									<div class="form-group">
										<div class="checkbox checkbox-primary d-inline">
											<input type="checkbox" name="termsCondition" id="termsCondition" <c:if test="${action == 'update'}">disabled="" checked="checked"</c:if>>
											<label for="termsCondition" class="cr">I agree to the <a href="javascript:openTermConditionModal('termsConditionModal')"> Terms and Condition</a></label>
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
									<input type="hidden" name="verifiedId" id="verifiedId" <c:if test="${action == 'update'}">value="1"</c:if> <c:if test="${action != 'update'}">value=""</c:if>>
									<input type="hidden" name="id" value="<c:if test='${!empty(merchant.id)}'>${merchant.id}</c:if><c:if test='${empty(merchant.id)}'>0</c:if>">
									<div class="alert alert-danger imageERRMsg DispNone" role="alert">
                                        
                                    </div>
									<c:if test="${pageRole.isAdd == 1}">
										<div class="col-md-12">
											<button type="submit" class="btn  btn-primary">
												<c:if test="${action != 'update'}">Submit</c:if>
												<c:if test="${action == 'update'}">Update</c:if>
											</button>
											<button class="btn btn-danger" type="reset" formnovalidate="formnovalidate">Reset</button>
										</div>
									</c:if>
							</form>
						</div>
					</div>
					<c:if test="${!empty(merchantList) && sessionScope.loginDTO.groupDTO.groupType != 'customer' && sessionScope.loginDTO.groupDTO.groupType != 'merchant'}">
						<div class="card">
							<div class="card-header">
								<h5>Merchant</h5>
							</div>
							<div class="card-body">
								<div class="dt-responsive table-responsive">
									<table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
										<thead>
											<tr>
												<th>#</th>
												<th>MerchantId</th>
												<th>Merchant Name</th>
												<th>Industry Type</th>
												<th>Icon</th>
												<th>Country</th>
												<th>Currency</th>
												<th>PAN Number</th>
												<th>GSTIN</th>
												<th>Email Id</th>
												<th>Mobile No</th>
												<th>Address</th>
												<c:if
													test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
													<th>Action</th>
												</c:if>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${merchantList}" var="merchant" varStatus="index">
												<tr>
													<td>${index.count}</td>
													<td>${merchantIdPrefix}${merchant.merchantId}</td>
													<td>${merchant.merchantName}</td>
													<td>${merchant.categoryCode} - ${merchant.categoryName}</td>
													<td><img data-gsll-src="${merchant.icon}" width="167" height="33"/></td>
													<td>${merchant.countryCode} - ${merchant.countryName}</td>
													<td>${merchant.currencyCodeAlpha} - ${merchant.currencyName}</td>
													<td>${merchant.PANNumber}</td>
													<td>${merchant.GSTIN}</td>
													<td>${merchant.emailId}</td>
													<td>${merchant.mobileNumber}</td>
													<td><a href="javascript:displayMerchantAddress('${merchant.id}', '${merchant.merchantName}(${merchantIdPrefix}${merchant.merchantId}) - Addresses')">Address</a></td>
													<c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
														<td>
															<c:if test="${pageRole.isUpdate == 1}">
																<a href="javascript:updateMerchant('${merchant.id}')"><i class="feather icon-edit"></i></a>
															</c:if> 
															<c:if test="${pageRole.isDelete == 1}">
																<a href="javascript:deleteMerchant('${merchant.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
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
			<div id="mMerchantAddressModalDiv" class="DispNone"></div>
		</div>
		<%@include file="termscondition.jsp"%>
		<%@include file="verificationModal.jsp"%>
		<%@include file="mobileVerificationModal.jsp"%>
		<%@include file="jspincludes/footer.jsp"%>
		<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
		<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/additional-methods.js" defer></script>
		<script src="${applicationScope['jsBaseUrl']}/javascript/aes/aes.js" defer></script>
		<script src="${applicationScope['jsBaseUrl']}/javascript/merchantRegistration.js" defer onload="loadPageScript()"></script>
		<script type="text/javascript">var fieldLength = "${fn:length(merchantAddressList)}";var isSuccess = "";var action = "${action}";var sessionId = "${r:toHexDecimal(pageContext.session.id)}";var ks = "${aes.keySize}";var ic = "${aes.iterationCount}";</script>
</body>
</html>

