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
								<h5 class="m-b-10">Merchant Limit</h5>
							</div>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}"><i
										class="feather icon-home"></i></a></li>
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}/merchant/topUp/view">Merchant Limit</a></li>
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
							<h5>Merchant Limit</h5>
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
								<c:set value="${applicationScope['baseUrl']}/merchant/topUp/wallet" var="actionUrl" />
							</c:if>
							<form id="topUp-form" method="post" action="${actionUrl}">
								    <div class="col-md-12">
										<div class="form-group">
											<label class="form-label">Amount</label>
											<input type="text" class="form-control numeric" name="amount" id="amount" placeholder="Amount">										
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
									<c:if test="${pageRole.isAdd == 1 && empty(isPrivileges)}">
										<div class="col-md-12">
											<button type="submit" class="btn  btn-primary">
												<c:if test="${action != 'update'}">Submit</c:if>
											</button>
											<button class="btn btn-danger" type="reset" formnovalidate="formnovalidate">Reset</button>
										</div>
									</c:if>
							</form>
						</div>
					</div>
				</div>
				<!-- [ Form Validation ] end -->
			</div>
			<%-- [ Main Content ] end --%>
		</div>
		<%@include file="jspincludes/footer.jsp"%>
		<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer>></script>
		<script src="${applicationScope['jsBaseUrl']}/javascript/merchantWalletTopup.js" defer onload="loadPageScript()"></script>		
</body>
</html>

