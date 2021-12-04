<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="/WEB-INF/tools.tld" prefix="r"%>
<head>

	<title>Set New password | MPLS</title>
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
				<a href="${applicationScope['baseUrl']}"><img src="${applicationScope['baseUrl']}/resources/gradient/images/recon-white-new-bg.png" alt="" class="img-fluid"></a>
				<h1 class="text-white my-4">Change securely!</h1>
				<h4 class="text-white font-weight-normal">Update your account password to maintain the acocunt security.</h4>
			</div>
		</div>
		<div class="auth-side-form">
			<div class=" auth-content">
				<img src="${applicationScope['baseUrl']}/resources/gradient/images/recon-black-new-bg.png" alt="" class="img-fluid mb-4 d-block d-xl-none d-lg-none">
				<h3 class="mb-4 f-w-400">Set New Password</h3>
				<div class="input-group mb-4">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="feather icon-lock"></i></span>
					</div>
					<input type="password" class="form-control" placeholder="New Password" id="password" name="password"/>
				</div>
				<div class="input-group mb-4">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="feather icon-lock"></i></span>
					</div>
					<input type="password" class="form-control" placeholder="Confirm Password" id="confirmPassword" name="confirmPassword" />
				</div>
				<input type="hidden" id="id" name="id" value="${id}" />
				<input type="hidden" id="loginId" name="loginId" value="${encryptLoginId}"/>
				<div class="form-group text-left mt-2 loginMessage"></div>
				<button class="btn btn-block btn-primary mb-4" type="button" onclick="changePassword()">Set password</button>
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
