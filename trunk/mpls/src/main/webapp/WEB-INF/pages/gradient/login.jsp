<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="/WEB-INF/tools.tld" prefix="r"%>
<head>

	<title>MPLS Login | MPLS Dashboard</title>
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
	<link rel="stylesheet" href="${applicationScope['cssBaseUrl']}/gradient/css/style.css">
</head>

<%-- [ signin-img ] start --%>
<div class="auth-wrapper align-items-stretch aut-bg-img">
	<div class="flex-grow-1">
		<div class="h-100 d-md-flex align-items-center auth-side-img">
			<div class="col-sm-10 auth-content w-auto">
				<img src="${applicationScope['baseUrl']}/resources/gradient/images/DP-White.png" alt="" class="img-fluid">
				<h1 class="text-white my-4">Welcome Back!</h1>
				<h4 class="text-white font-weight-normal">Signin to your account and get explore the MPLS Dashboard.</h4>
			</div>
		</div>
		<div class="auth-side-form">
			<div class=" auth-content">
				<img src="${applicationScope['baseUrl']}/resources/gradient/images/DP-black.png" alt="" class="img-fluid mb-4 d-block d-xl-none d-lg-none">
				<h3 class="mb-4 f-w-400">MPLS Signin</h3>
				<form action="${applicationScope['baseUrl']}/user/login" method="POST" id="loginForm">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fad fa-mobile-alt"></i></span>
							</div>
							<input type="text" class="form-control" placeholder="Mobile Number" name="loginId" id="loginId" maxlength="10"/>
						</div>
						<div class="input-group mb-4">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="feather icon-lock"></i></span>
							</div>
							<input type="password" class="form-control" placeholder="Password" id="password" name="password"/>
						</div>
						<div class="form-group text-left mt-2">
							<div class="checkbox checkbox-primary d-inline">
								<input type="checkbox" id="isSaveCredentials" name="isSaveCredentials">
								<label for="isSaveCredentials" class="cr">Save credentials</label>
							</div>
						</div>
						<c:if test="${!empty(message)}">
						      <div class="form-group text-left mt-2 loginMessage">${message}</div>
						</c:if>
						<c:if test="${empty(message)}">
						      <div class="form-group text-left mt-2 loginMessage"></div>
						</c:if>
						<button class="btn btn-block btn-primary mb-0 LoginBtn" type="button" onclick="validateUserCredential()">Signin</button>
                </form>
				<div class="text-center">
					<div class="saprator my-4"><span>OR</span></div>
					<p class="mb-0 mt-4 text-muted"><a href="${applicationScope['baseUrl']}/user/reset-password" class="f-w-400">Forgot password</a></p>
					<p class="mb-0 mt-4 text-muted"><a href="${applicationScope['baseUrl']}/user/merchant/register" class="f-w-400">New Merchant Registration</a></p>
					<p class="mb-0 mt-4 text-muted"><a href="${applicationScope['baseUrl']}/customer/register" class="f-w-400">New Customer Registration</a></p>
				</div>
			</div>
		</div>
	</div>
</div>
<%-- [ signin-img ] end --%>
<%-- Required Js --%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/vendor-all.min.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/aes/aes.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/dashboard-main.js" defer onload="loadPageScript()"></script>
<script type="text/javascript">
	var basepath = "${applicationScope['baseUrl']}";var sessionId = "${r:toHexDecimal(pageContext.session.id)}";var ks = "${aes.keySize}";var ic = "${aes.iterationCount}";
	var pageScript=[],pageLoaded=!1,addPageScript=function(o){"function"==typeof o&&(pageLoaded?o():pageScript.push(o))},loadPageScript=function(){pageLoaded=!0;for(var o in pageScript)pageScript[o]()},windowLoadScript=[],windowLoaded=!1,addWindowLoadScript=function(o){"function"==typeof o&&(windowLoaded?o():windowLoadScript.push(o))};window.onload=function(){windowLoaded=!0;for(var o in windowLoadScript)windowLoadScript[o]()};
</script>
</body>
</html>
