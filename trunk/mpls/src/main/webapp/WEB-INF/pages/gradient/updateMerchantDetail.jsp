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
								<h5 class="m-b-10">Merchant Details</h5>
							</div>
							<%-- <ul class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}"><i
										class="feather icon-home"></i></a></li>
								<li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/merchant/updateMerchantDetail">Merchant Details</a></li>
							</ul> --%>
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
							<h5>Merchant Details</h5>
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
							<c:set value="${applicationScope['baseUrl']}/merchant/saveMerchantDetail" var="actionUrl" />
							<form id="updateMerchantDetail-form" method="post" action="${actionUrl}">
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
									    	<c:set var="merchantAddressList" value="${merchant.mAddressList}"/>
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
									<c:if test="${pageRole.subMenuId == 0}">
										<input type="hidden" name="menuName" value="${pageRole.menuName}">
									</c:if>
									<c:if test="${pageRole.subMenuId != 0}">
										<input type="hidden" name="menuName" value="${pageRole.subMenuName}">
									</c:if>
									<input type="hidden" name="updatedBy" value="${sessionScope.loginDTO.loginId}">
									<input type="hidden" id="loginId" name="loginId" value="${sessionScope.loginDTO.loginId}">
									<input type="hidden" name="id" value="${sessionScope.loginDTO.merchant.id}">
									<div class="alert alert-danger imageERRMsg DispNone" role="alert">
                                    </div>
									<div class="col-md-12">
										<button type="submit" class="btn  btn-primary">Submit</button>
										<%-- <button class="btn btn-danger" type="reset" onclick="updateStatus('loginId');redirectToUrl('${applicationScope['baseUrl']}/merchant/dashboard/merchantDashboard')">Skip</button> --%>
									</div>
							</form>
						</div>
					</div>
		</div>
<%@include file="jspincludes/footer.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/additional-methods.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/updateMerchantDetail.js" defer onload="loadPageScript()"></script>
<script type="text/javascript">var fieldLength = "${fn:length(merchantAddressList)}";</script>
<script type="text/javascript">
	var path = "${applicationScope['baseUrl']}/merchant/dashboard/merchantDashboard";var isSuccess = "${is_success}";
	if(isSuccess == 'success'){ setTimeout(function(){ location.href = path}, 3000);}
</script>
</body>
</html>

