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
							<h5 class="m-b-10">User Profile</h5>
						</div>
						<ul class="breadcrumb">
							<li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
							<li class="breadcrumb-item">Update User Profile</li>
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
					<h5>User Profile</h5>
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
						
						<form id="customerReg-form" method="post" action="${applicationScope['baseUrl']}/customer/edit">
							<%-- <div class="col-md-12">
								<div class="form-group">
									<label class="form-label">Login Id</label> 
									<input type="text" class="form-control capital-letter" name="loginId" id="loginId" placeholder="Login Id" value="${customer.loginId}" onblur="isExistsLoginId('loginId', '${customer.loginId}')">
								</div>
							</div> --%>
							<div class="form-row">
								<div class="form-group col-md-4">
									<label for="firstName">First Name<b class="mandetory">*</b></label> 
									<input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" value="${customer.firstName}" />
								</div>
								<div class="form-group col-md-4">
									<label for="middleName">Middle Name</label> 
									<input type="text" class="form-control" id="middleName" name="middleName" placeholder="Middle Name" value="${customer.middleName}" />
								</div>
								<div class="form-group col-md-4">
									<label for="lastName">Last Name<b class="mandetory">*</b></label>
									<input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" value="${customer.lastName}" />
								</div>
							</div>
							<div class="form-row">
                               <div class="col-md-8">
									<div class="form-group">
										<label class="form-label">Mobile Number<b class="mandetory">*</b></label>
										<input type="text" class="form-control" name="mobileNumber" id="mobileNumber" placeholder="Mobile Number" value="${customer.mobileNumber}" onblur="isExistsMobileNumber('mobileNumber', '${customer.mobileNumber}');verifyMobileNumber('mobileNumber', '${customer.mobileNumber}')" readonly="readonly">
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
							<c:if test="${pageRole.subMenuId == 0}">
								<input type="hidden" name="menuName" value="${pageRole.menuName}">
							</c:if>
							<c:if test="${pageRole.subMenuId != 0}">
								<input type="hidden" name="menuName" value="${pageRole.subMenuName}">
							</c:if>
							<c:if test="${customer.userId!= 0}">
								<input type="hidden" name="userId" id="userId" value="${customer.userId}">
							</c:if>
							<input type="hidden" name="userImage" id="userImage" value="${customer.userImage}">
							<input type="hidden" name="loginId" id="loginId" value="${sessionScope.loginDTO.loginId}">
							<input type="hidden" name="createdBy" value="${sessionScope.loginDTO.loginId}"> 
							<input type="hidden" name="updatedBy" value="${sessionScope.loginDTO.loginId}"> 
							<input type="hidden" name="id" value="<c:if test='${!empty(customer.id)}'>${customer.id}</c:if><c:if test='${empty(customer.id)}'>0</c:if>">
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary">Update</button>
								<button class="btn btn-danger" type="reset" formnovalidate="formnovalidate">Reset</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- [ Form Validation ] end -->
		</div>
		<%-- [ Main Content ] end --%>
		</div>
</div>
<%@include file="jspincludes/footer.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/customerReg.js?v=1" defer onload="loadPageScript()"></script>
<script type="text/javascript">
	var basepath = "${applicationScope['baseUrl']}";var sessionId = "${r:toHexDecimal(pageContext.session.id)}";var ks = "${aes.keySize}";var ic = "${aes.iterationCount}";;
	var pageScript=[],pageLoaded=!1,addPageScript=function(o){"function"==typeof o&&(pageLoaded?o():pageScript.push(o))},loadPageScript=function(){pageLoaded=!0;for(var o in pageScript)pageScript[o]()},windowLoadScript=[],windowLoaded=!1,addWindowLoadScript=function(o){"function"==typeof o&&(windowLoaded?o():windowLoadScript.push(o))};window.onload=function(){windowLoaded=!0;for(var o in windowLoadScript)windowLoadScript[o]()};
</script>
</body>
</html>



