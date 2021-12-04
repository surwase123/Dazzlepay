<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<head>
    <title>MPLS Dashboard</title>
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
</head>

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
    <div class="pcoded-content">

        <div class="">
            <div class="page-block">
                <div class="row align-items-center">
                    <c:if test="${sessionScope.loginDTO.groupDTO.groupType == 'admin'}">
                        <div class="col-md-12">
                            <div id="reportrange" class="form-control col-md-3 row-dropDown marginBottom25 AdminDateRangePicker" onchange="getAdminDashaboardData()">
                                    <i class="feather icon-calendar featherIcon"></i>&nbsp;
                                    <span></span> <i class="feather icon-chevron-down featherIcon"></i>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

      <div id="adminDashboardData">
        <%-- [ Main Content ] start --%>
        <div class="row">
            <%-- order-card start --%>
            <div class="col-md-6 col-xl-3">
		        <div class="card bg-c-blue order-card">
		            <div class="card-body merchantDashboardCard">
		                 <h6 class="text-white" style="font-size:16px">Merchant</h6><br>
		                 <p class="m-b-0" style="font-size:14px">Total Merchant<span class="float-right">${adminDashboardData.totalMerchant}</span></p><br>
		                 <p class="m-b-0" style="font-size:14px">Active Merchant<span class="float-right">${adminDashboardData.activeMerchantCount}</span></p>
		            </div>
		        </div>
		    </div>
		    <div class="col-md-6 col-xl-3">
		        <div class="card bg-c-green order-card">
		            <div class="card-body merchantDashboardCard">
		                <h5 class="text-white" style="font-size:16px">Customer</h5><br>
		                <p class="m-b-0" style="font-size:14px">Total Customer<span class="float-right">${adminDashboardData.totalCustomer}</span></p><br>
		                <p class="m-b-0" style="font-size:14px">Active Customer<span class="float-right">${adminDashboardData.activeCustomerCount}</span></p>
		            </div>
		        </div>
		    </div>
		    <div class="col-md-6 col-xl-3">
		        <div class="card bg-c-yellow order-card">
		            <div class="card-body merchantDashboardCard">
		                <h5 class="text-white" style="font-size:16px">New Registrations</h5><br>
		                <p class="m-b-0" style="font-size:14px">New Merchant<span class="float-right">${adminDashboardData.newMerchant}</span></p><br>
		                <p class="m-b-0" style="font-size:14px">New Customer<span class="float-right">${adminDashboardData.newCustomer}</span></p>
		            </div>
		        </div>
		    </div>
		    <div class="col-md-6 col-xl-3">
		        <div class="card bg-c-red order-card">
		            <div class="card-body merchantDashboardCard">
		                <h5 class="text-white" style="font-size:16px"> Wallet Balance</h5>
		                <p class="m-b-0" style="font-size:14px">Merchant Limit<span class="float-right"><i class="fas fa-rupee-sign fontSize15"></i> <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${adminDashboardData.merchantWalletBal}" /></span></p><br>
		            	<p class="m-b-0" style="font-size:14px">Customer Bal<span class="float-right"><i class="fas fa-rupee-sign fontSize15"></i> <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${adminDashboardData.customerBalance}" /></span></p><br>
		            	<p class="m-b-0" style="font-size:14px">Daily Trans<span class="float-right"><i class="fas fa-rupee-sign fontSize15"></i> <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${adminDashboardData.totalTransValue}" /></span></p>
		            </div>
		        </div>
		    </div>
            <%-- order-card end --%>
        </div>

         <%-- Customer overview start --%>
        <div class="row">
            <div class="col-md-12">
                <div class="card table-card">
                    <div class="card-header">
                        <h5>Recent Merchant Snapshot</h5>
                        <div class="card-header-right">
                            <div class="btn-group card-option">
                                <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="feather icon-more-horizontal"></i>
                                </button>
                                <ul class="list-unstyled card-option dropdown-menu dropdown-menu-right">
                                    <li class="dropdown-item full-card"><a href="#!"><span><i class="feather icon-maximize"></i> maximize</span><span style="display:none"><i class="feather icon-minimize"></i> Restore</span></a></li>
                                    <li class="dropdown-item minimize-card"><a href="#!"><span><i class="feather icon-minus"></i> collapse</span><span style="display:none"><i class="feather icon-plus"></i> expand</span></a></li>
                                    <li class="dropdown-item reload-card"><a href="#!"><i class="feather icon-refresh-cw"></i> reload</a></li>
                                    <li class="dropdown-item close-card"><a href="#!"><i class="feather icon-trash"></i> remove</a></li>
                                </ul>
                            </div>
                        </div>
                        <ul class="nav nav-pills nav-fill mt-3 border-bottom pb-3" id="pills-tab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="pills-profile-tab" data-toggle="pill" href="#pills-history" role="tab" aria-controls="pills-history" aria-selected="false"><i class="feather icon-users m-r-5"></i>Customer</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-message" role="tab" aria-controls="pills-message" aria-selected="false"><i class="fas fa-money-bill m-r-5"></i> Transaction</a>
                            </li>                       
                        </ul>
                    </div>
                    <div class="card-body p-0">
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane fade show active" id="pills-history" role="tabpanel" aria-labelledby="pills-history-tab">
                                <div class="table-responsive">
                                    <div class="customer-scroll MarginMerchantSnapShot">
                                        <table class="table table-hover m-b-0 table-striped table-bordered nowrap" id="multi-colum-dt-customer">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Customer Id</th>
                                                    <th>Name</th>
                                                    <th>Email Id</th>
                                                    <th>Mobile Number</th>
                                                    <th>Wallet Balance</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                               <c:forEach items="${customerList}" var="customer" varStatus="index" begin="0" end="19">
                                                    <tr>
                                                        <td>${index.count}</td>
                                                        <td>
                                                            ${customerIdPrefix}${customer.customerId}
                                                            <div class="progress mt-1" style="height:4px;">
                                                                <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                           ${customer.firstName} ${customer.middleName} ${customer.lastName}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-warning rounded" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                           ${customer.emailId}
                                                           <div class="progress mt-1" style="height:4px;">
                                                                 <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                           ${customer.mobileNumber}
                                                           <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customer.walletBal}" />
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 40%;" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <%-- <td>
                                                            <fmt:formatDate type="both" dateStyle="long" timeStyle="long" value = "${customer.createdDate}" />
                                                            <div class="progress mt-1" style="height:4px;">
                                                                <div class="progress-bar bg-warning rounded" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td> --%>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="pills-message" role="tabpanel" aria-labelledby="pills-message-tab">
                                <div class="table-responsive">
                                    <div class="customer-scroll1 MarginMerchantSnapShot">
                                        <table class="table table-hover m-b-0 table-striped table-bordered nowrap" id="multi-colum-dt-transaction">
                                            <thead>
                                               <tr>
                                                    <th>#</th>
                                                    <th>Customer</th>
                                                    <th>Merchant</th>
                                                    <th>TransactionId</th>
                                                    <th>Transaction Time</th>
                                                    <th>Transaction Type</th>
                                                    <th>Transaction Amount</th>
                                                    <th>Pay Type</th>
                                                    <th>Status</th>
                                               </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${merchantTransactionList}" var="merchantTransaction" varStatus="index" begin="0" end="19">
                                                    <tr>
                                                        <td>${index.count}</td>
                                                        <td>
                                                            <c:if test="${!empty(merchantTransaction.customerId)}">
                                                                ${merchantTransaction.firstName} ${merchantTransaction.middleName} ${merchantTransaction.lastName} - ${customerIdPrefix}${merchantTransaction.customerId}
                                                                <div class="progress mt-1" style="height:4px;">
                                                                    <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                                </div>
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                           ${merchantTransaction.merchantName} - ${merchantIdPrefix}${merchantTransaction.merchantId}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-primary rounded" role="progressbar" style="width: 50%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            ${merchantTransaction.transactionId}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-warning rounded" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <fmt:formatDate type="both" dateStyle="long" timeStyle="long" value = "${merchantTransaction.createdDate}" />
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <c:choose>
																<c:when test="${merchantTransaction.transactionType == 'PAY'}">
																    SALE
																</c:when>
																<c:otherwise>
																    ${merchantTransaction.transactionType}
																</c:otherwise>
															</c:choose>
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 40%;" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${merchantTransaction.transactionValue}" />
                                                            <div class="progress mt-1" style="height:4px;">
                                                                <div class="progress-bar bg-warning rounded" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                           ${merchantTransaction.payType}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-primary rounded" role="progressbar" style="width: 50%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                             <c:if test="${merchantTransaction.status == 'S'}">
                                                                  <button type="button" class="btn bg-c-green border border-green rounded btnWhite"><i class="feather icon-thumbs-up"></i></button>
                                                            </c:if>
                                                            <c:if test="${merchantTransaction.status == 'F'}">
                                                                   <button type="button" class="btn bg-c-red border border-red rounded btnWhite"><i class="feather icon-thumbs-down"></i></button>
                                                            </c:if>
                                                            <c:if test="${merchantTransaction.status == 'P'}">
                                                                  <button type="button" class="btn bg-c-yellow border border-yellow rounded btnWhite"><i class="feather icon-clock"></i></button>
                                                            </c:if> 
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
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
    </div>
        <%-- [ Main Content ] end --%>
    </div>
</div>
<%@include file="jspincludes/footer.jsp" %> 
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/daterangepicker.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/adminDashboard.js" defer onload="loadPageScript()"></script>
</body>
</html>
