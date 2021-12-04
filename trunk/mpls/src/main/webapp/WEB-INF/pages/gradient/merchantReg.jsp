<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="/WEB-INF/tools.tld" prefix="r"%>
<head>

	<title>Merchant Register | MPLS</title>
	<%-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries --%>
	<%-- WARNING: Respond.js doesn't work if you view the page via file:// --%>
	<%--[if lt IE 11]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]--%>
	<%-- Meta --%>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="description" content="" />
	<meta name="keywords" content="">
	<meta name="author" content="cratas" />
	<%-- Favicon icon --%>
	<link rel="icon" href="${applicationScope['baseUrl']}/resources/gradient/images/favicon.ico" type="image/x-icon">
	<%-- vendor css --%>
	<link rel="stylesheet" href="${applicationScope['cssBaseUrl']}/css/vendor.css">
	<%-- vendor css --%>
	<link rel="stylesheet" href="${applicationScope['cssBaseUrl']}/gradient/css/style.css">
</head>
<!-- [ auth-signup ] start -->
<div class="auth-wrapper transparentImageBG">
	<div class="auth-content auth-width">
		<div class="card">
			<div class="row">
				<div class="col-md-12">
					<div class="card-body">
						    <div class="align-items-center text-center">
						    	<a href="${applicationScope['baseUrl']}"><img src="${applicationScope['baseUrl']}/resources/gradient/images/DP-black.png" alt="" class="img-fluid mb-4"></a>
						    </div>
						    <h5 class="mb-3 f-w-400 text-center">Merchant Registration</h5>
                            <hr>
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
							<form id="merchantRegistration-form" method="post" action="${applicationScope['baseUrl']}/merchant/register" onsubmit="return validateMerchantForm()">
								<div class="col-md-12">
									<div class="form-group">
										<label class="form-label">Merchant Name<b class="mandetory">*</b></label> 
										<input type="text" class="form-control capital-letter" name="merchantName" id="merchantName" placeholder="Merchant Name" value="${merchant.merchantName}">
									</div>
								</div>
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
                                        <div class="form-group col-md-5">
                                            <label for="firstName">Email Id<b class="mandetory">*</b></label>
                                            <input type="text" class="form-control" id="emailId" name="emailId" value="${merchant.emailId}" placeholder="Email Id" onblur="isExistsEmailId('emailId', '');verifyEmailAddress('emailId')" <c:if test="${action == 'update'}">readonly</c:if>/> 
                                        </div>
                                        <div class="form-group col-md-1 VerifyEmailBtn" id= "VerifyMobileBtn">
                                        	<button type="button" class="btn btn-success verifyEmailBtn" onclick="sendVerificationCode('emailId', 'merchant', 'Email Id', 'verificationModal')" disabled="">Verify</button>
                                        </div>
                                        <div class="form-group col-md-5">
                                            <label for="lastName">Mobile Number<b class="mandetory">*</b></label>
                                            <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" value="${merchant.mobileNumber}" placeholder="Mobile Number" onblur="isExistsMobileNumber('mobileNumber', '');verifyMobileNumber('mobileNumber')"/>
                                        </div>
                                        <div class="form-group col-md-1 VerifyMobileBtn" id= "VerifyMobileBtn">
                                        	<button type="button" class="btn btn-success verifyMobileBtn" onclick="sendMobileVerificationCode('mobileNumber', 'merchant', 'Mobile Number', 'mobileVerificationModal')" disabled="">Verify</button>
                                        </div>
                                </div>

								<%-- <h5 class="mt-5">Address</h5>
                                <hr>
								<div class="col-md-12 column">
									<table class="table table-bordered table-hover" id="merchantAddressTable">
										<thead>
											<tr>
												<th class="text-center width6">#</th>
												<th class="text-center width24">State</th>
												<th class="text-center width24">City</th>
												<th class="text-center width24">Area</th>
												<th class="text-center width22">Pincode</th>
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
									</div> --%>
									<div class="form-group">
										<div class="checkbox checkbox-primary d-inline">
											<input type="checkbox" name="termsCondition" id="termsCondition">
											<label for="termsCondition" class="cr">I agree to the <a class="linkcolor-blue" href="javascript:openTermConditionModal('termsConditionModal')"> Terms and Condition</a></label>
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
									<input type="hidden" name="verifiedId" id="verifiedId" value="${merchant.verifiedId}">
									<div class="alert alert-danger imageERRMsg DispNone" role="alert">
                                        
                                    </div>
									<div class="col-md-12 text-center">
											<button type="submit" class="btn  btn-success MerchantRegBtn">
												<c:if test="${action != 'update'}">Submit</c:if>
												<c:if test="${action == 'update'}">Update</c:if>
											</button>
											<button class="btn btn-danger" type="reset" formnovalidate="formnovalidate">Reset</button>
								    </div>
							</form>
						</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- [ auth-signup ] end -->
<%-- Required Js --%>
<%@include file="termscondition.jsp"%>
<%@include file="verificationModal.jsp"%>
<%@include file="mobileVerificationModal.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/vendor-all.min.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/aes/aes.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/additional-methods.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/merchantRegistration.js" defer onload="loadPageScript()"></script>
<script type="text/javascript">
	var basepath = "${applicationScope['baseUrl']}";var action = "${action}";var isSuccess = "${is_success}";var sessionId = "${r:toHexDecimal(pageContext.session.id)}";var ks = "${aes.keySize}";var ic = "${aes.iterationCount}";
	var pageScript=[],pageLoaded=!1,addPageScript=function(o){"function"==typeof o&&(pageLoaded?o():pageScript.push(o))},loadPageScript=function(){pageLoaded=!0;for(var o in pageScript)pageScript[o]()},windowLoadScript=[],windowLoaded=!1,addWindowLoadScript=function(o){"function"==typeof o&&(windowLoaded?o():windowLoadScript.push(o))};window.onload=function(){windowLoaded=!0;for(var o in windowLoadScript)windowLoadScript[o]()};
</script>
</body>
</html>
