
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
<link rel="stylesheet"
	href="${applicationScope['cssBaseUrl']}/css/vendor.css">
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
			<c:if test="${sessionScope.loginDTO.groupDTO.groupType == 'admin'}">
				<div class="">
					<div class="page-block">
						<div class="row align-items-center">
							<div class="col-md-12">
								<div class="form-group col-md-4 row-dropDown">
		                         <select class="form-control js-example-basic-single" name="merchantName" id="merchantName" onchange="getCustomerList('merchantName')">
		                             <c:forEach items="${merchantList}" var="merchant" varStatus="index">
		                                   <option value="${merchant.id}">${merchant.merchantName}</option>
		                             </c:forEach>
		                         </select>
		                     	</div>
		                	</div>
		               </div> 
		            </div>
		        </div>   
		    </c:if> 
        <div id="customerListDetails">  
		<div class="row">
			<div class="col-md-12 order-md-2">
				<div class="card">
					<div class="card-header">
						<h5>Customer</h5>
					</div>
					<div class="card-body">
						<div class="dt-responsive table-responsive">
							<table id="multi-colum-customer" class="table table-striped table-bordered nowrap">
								<thead>
									<tr>
										<th>#</th>
										<th>Customer Id</th>
										<th>Login Id</th>
										<th>Name</th>
										<th>Email Id</th>
										<th>Mobile Number</th>
										<th>Wallet Balance</th>
										<th>Insert Time</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${customerList}" var="customer" varStatus="index">
										<tr>
											<td>${index.count}</td>
											<td>${customerIdPrefix}${customer.customerId}</td>
											<td>${customer.loginId}</td>
											<td>${customer.firstName} ${customer.middleName} ${customer.lastName}</td>
											<td>${customer.emailId}</td>
											<td>${customer.mobileNumber}</td>
											<td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customer.walletBal}" /></td>
											<td><fmt:formatDate pattern = "dd MMM, YYYY HH:MM" value = "${customer.createdDate}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> 
	</div>
</div>
	
<%@include file="jspincludes/footer.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/exportReport.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/merchantCustomer.js" defer onload="loadPageScript()"></script>
</body>
</html>
