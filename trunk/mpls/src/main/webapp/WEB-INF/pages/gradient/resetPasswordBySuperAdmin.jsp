<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="/WEB-INF/tools.tld" prefix="r"%>
<head>
    <title>MPLS - Merchant Prepaid & Loyalty Solution</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
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
</head>`
<body>
	<%-- [ Pre-loader ] start --%>
	<div class="loader-bg">
		<div class="loader-track">
			<div class="loader-fill"></div>
		</div>
	</div>
	<%-- [ Pre-loader ] End --%>
	<%-- [ navigation menu ] start --%>
	<%@include file="jspincludes/menu.jsp" %> 
	<%-- [ navigation menu ] end --%>
	<%-- [ Header ] start --%>
	<%@include file="jspincludes/header.jsp" %> 
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
                                <h5 class="m-b-10">Reset Password</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/resetPassword/view">Reset Password</a></li>
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
                            <h5>Reset Password</h5>
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
                            <form id="resetPassword-form" action="${applicationScope['baseUrl']}/resetPassword/searchingDetails" method="post">
                                    <div class="col-md-12">
                                        <label class="form-label">User Type</label>
                                        <div class="form-group">
                                            <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="merchant" name="userType" class="custom-control-input" value="merchant" <c:if test="${resetPassword.userType == 'merchant'}">checked</c:if> <c:if test="${empty(resetPassword.userType)}">checked</c:if>>
                                                    <label class="custom-control-label" for="merchant">Merchant</label>
                                            </div>
                                            <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="customer" name="userType" class="custom-control-input" value="customer" <c:if test="${resetPassword.userType == 'customer'}">checked</c:if>>
                                                    <label class="custom-control-label" for="customer">Customer</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12 merchant">
										<div class="form-group">
									    	<label class="form-label">Mobile Number</label>
									    	<input type="text" class="form-control" name="mobileNumber" id="mobileNumber"  placeholder="Mobile Number" value = "${resetPassword.mobileNumber}">
									    </div>
									 </div>
                                    <div class="col-md-12">
                                       <button type="submit" class="btn  btn-primary">Search</button>
                                       <button class="btn btn-danger" type="reset">Reset</button>
                                    </div>
                            </form>
                        </div>
                    </div>
                    <c:if test="${!empty(resetPassword) && empty(merchant) && empty(customer)}">
                        <div class="card">
                            <div class="card-header">
                                <h5>User Info</h5>
                            </div>
                            <div class="card-body">
                                <div class="alert alert-danger" role="alert">
                                    <strong>Oh snap!</strong> No Records Found!!
                                </div>
                            </div>
                        </div>
                    </c:if>
                   <c:if test="${!empty(merchant)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>Merchant Info</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>Merchant Id</th>
                                            <th>Merchant Name</th>
                                            <th>Email Id</th>
                                            <th>Mobile Number</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>${merchant.merchantId}</td>
                                            <td>${merchant.merchantName}</td>
                                            <td>${merchant.emailId}</td>
                                            <td>${merchant.mobileNumber}</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <c:if test="${!empty(message)}">
									<div class="form-group text-left mt-2 loginMessage">${message}</div>
								</c:if>
								<c:if test="${empty(message)}">
						      		<div class="form-group text-left mt-2 loginMessage"></div>
								</c:if>
                                <div class="col-md-12">
                                       <button type="button" class="btn  btn-primary" onclick="resetPassword('${merchant.loginId}')">Reset password</button>
                                 </div>
                            </div>
                        </div>
                    </div>
                 </c:if>
                 <c:if test="${!empty(customer)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>Customer Info</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>Customer Id</th>
											<th>Name</th>
											<th>Email Id</th>
											<th>Mobile Number</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>${customer.customerId}</td>
											<td>${customer.firstName} ${customer.middleName} ${customer.lastName}</td>
											<td>${customer.emailId}</td>
											<td>${customer.mobileNumber}</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <c:if test="${!empty(message)}">
									<div class="form-group text-left mt-2 loginMessage">${message}</div>
								</c:if>
								<c:if test="${empty(message)}">
						      		<div class="form-group text-left mt-2 loginMessage"></div>
								</c:if>
                                <div class="col-md-12">
                                       <button type="button" class="btn  btn-primary" onclick="resetPassword('${customer.loginId}')">Reset password</button>
                                 </div>
                            </div>
                        </div>
                    </div>
                 </c:if>
                 
                </div>
                <!-- [ Form Validation ] end -->
        </div>
        <%-- [ Main Content ] end --%>
</div>
<%@include file="jspincludes/footer.jsp" %> 
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/resetPassword.js" defer onload="loadPageScript()"></script>
</body>
</html>