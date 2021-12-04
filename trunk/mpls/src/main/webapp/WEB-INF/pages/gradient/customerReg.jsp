<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="/WEB-INF/tools.tld" prefix="r"%>
<head>

	<title>Customer Register | MPLS</title>
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
	<div class="auth-content cust-auth-width">
		<div class="card">
			<div class="row">
				<div class="col-md-12">
					<div class="card-body">
						    <div class="align-items-center text-center">
						    	<a href="${applicationScope['baseUrl']}"><img src="${applicationScope['baseUrl']}/resources/gradient/images/DP-black.png" alt="" class="img-fluid mb-4"></a>
						    </div>
                            <h5 class="mb-3 f-w-400 text-center">Customer Registration</h5>
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
							<form id="customerReg-form" method="post" action="${applicationScope['baseUrl']}/customer/add" onsubmit="return validateCustomerForm()">
									<%-- <div class="col-md-12">
										<div class="form-group">
											<label class="form-label">Login Id<b class="mandetory">*</b></label>
											<input type="text" class="form-control capital-letter" name="loginId" id="loginId" placeholder="Login Id" value="${customer.loginId}" onblur="isExistsLoginId('loginId', '')">
										</div>
									</div> --%>
									<div class="form-row">
	                                        <div class="form-group col-md-4">
	                                            <label for="firstName">First Name<b class="mandetory">*</b></label>
	                                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" value="${customer.firstName}"/> 
	                                        </div>
	                                        <div class="form-group col-md-4">
	                                            <label for="middleName">Middle Name</label>
	                                            <input type="text" class="form-control" id="middleName" name="middleName" placeholder="Middle Name" value="${customer.middleName}"/> 
	                                        </div>
	                                        <div class="form-group col-md-4">
	                                            <label for="lastName">Last Name<b class="mandetory">*</b></label>
	                                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" value="${customer.lastName}"/>
	                                        </div>
	                                </div>
									<div class="form-row">
		                                <div class="col-md-8">
											<div class="form-group">
												<label class="form-label">Mobile Number<b class="mandetory">*</b></label>
												<input type="text" class="form-control" name="mobileNumber" id="mobileNumber" placeholder="Mobile Number" value="${customer.mobileNumber}" onblur="isExistsMobileNumber('mobileNumber', '${customer.mobileNumber}');verifyMobileNumber('mobileNumber', '${customer.mobileNumber}')">
											</div>
										</div>
										<div class="form-group col-md-1 VerifyMobileBtn">
	                                        	<button type="button" class="btn btn-success verifyMobileBtn" onclick="sendMobileVerificationCode('mobileNumber', 'customer', 'Mobile Number', 'mobileVerificationModal')" disabled="">Verify</button>
	                                    </div>
                                    </div>
                                    <div class="col-md-12">
										<div class="form-group">
											<label class="form-label">Email Id<b class="mandetory">*</b></label>
											<input type="text" class="form-control" name="emailId" id="emailId" placeholder="Email Id" value="${customer.emailId}" onblur="isExistsEmailId('emailId', '')">
										</div>
									</div>
									<input type="hidden" name="createdBy" value="${sessionScope.loginDTO.loginId}">
									<input type="hidden" name="updatedBy" value="${sessionScope.loginDTO.loginId}">
									<input type="hidden" name="verifiedId" id="verifiedId" value="${customer.verifiedId}">
									<div class="alert alert-danger imageERRMsg DispNone" role="alert">
                                    </div>
									<div class="col-md-12 text-center">
											<button type="submit" class="btn btn-success">
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
<%@include file="mobileVerificationModal.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/vendor-all.min.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/aes/aes.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/customerReg.js" defer onload="loadPageScript()"></script>
<script type="text/javascript">
	var basepath = "${applicationScope['baseUrl']}";var isSuccess = "${is_success}";var sessionId = "${r:toHexDecimal(pageContext.session.id)}";var ks = "${aes.keySize}";var ic = "${aes.iterationCount}";
	var pageScript=[],pageLoaded=!1,addPageScript=function(o){"function"==typeof o&&(pageLoaded?o():pageScript.push(o))},loadPageScript=function(){pageLoaded=!0;for(var o in pageScript)pageScript[o]()},windowLoadScript=[],windowLoaded=!1,addWindowLoadScript=function(o){"function"==typeof o&&(windowLoaded?o():windowLoadScript.push(o))};window.onload=function(){windowLoaded=!0;for(var o in windowLoadScript)windowLoadScript[o]()};
</script>
</body>
</html>
