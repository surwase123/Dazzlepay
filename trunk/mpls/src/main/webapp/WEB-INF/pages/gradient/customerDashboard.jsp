<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

    <link rel="stylesheet" href="${applicationScope['cssBaseUrl']}/gradient/css/plugins/css-stars.css">

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
        <div class="pcoded-content">
            <%-- [ Main Content ] start --%>
            <div class="card">
                <div class="card-header">
                    <h3 class="merchName">Hi ${sessionScope.loginDTO.firstName} ${sessionScope.loginDTO.middleName} ${sessionScope.loginDTO.lastName}!</h3>
                    <p class="wallet">Total Wallet Balance</p>
                    <span class="walletbal"><i class="fas fa-rupee-sign"></i>
                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${sessionScope.loginDTO.customer.walletBal}" /></span>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 col-xl-3 marginTop pointer" onclick="redirectToUrl('${applicationScope['baseUrl']}/search/store/view')">
                    <div class="card bg-c-red notification-card">
                        <div class="card-body">
                            <div class="row align-items-center">
                                <div class="col-4 notify-icon">
                                    <i class="feather icon-search"></i>
                                </div>
                                <div class="col-8 notify-cont">
                                    <p><a class="text-white" href="${applicationScope['baseUrl']}/search/store/view">Search Store List</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-xl-3 marginTop pointer" onclick="redirectToUrl('${applicationScope['baseUrl']}/customer/merchant/view')">
                    <div class="card bg-c-blue notification-card">
                        <div class="card-body">
                            <div class="row align-items-center">
                                <div class="col-4 notify-icon">
                                    <i class="fas fa-wallet"></i>
                                </div>
                                <div class="col-8 notify-cont">
                                    <p><a class="text-white" href="${applicationScope['baseUrl']}/customer/merchant/view">View Wallet</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-xl-3 marginTop pointer" onclick="redirectToUrl('${applicationScope['baseUrl']}/add/user/profile')">
                    <div class="card bg-c-green notification-card">
                        <div class="card-body">
                            <div class="row align-items-center">
                                <div class="col-4 notify-icon">
                                    <i class="fas fa-user"></i>
                                </div>
                                <div class="col-8 notify-cont">
                                    <p><a class="text-white" href="${applicationScope['baseUrl']}/add/user/profile">View Profile</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 col-xl-3 marginTop pointer" onclick="redirectToUrl('${applicationScope['baseUrl']}/customer/transactionHistory/view')">
                    <div class="card bg-c-yellow notification-card">
                        <div class="card-body">
                            <div class="row align-items-center">
                                <div class="col-4 notify-icon">
                                    <i class="fas fa-clock"></i>
                                </div>
                                <div class="col-8 notify-cont">
                                    <p><a class="text-white" href="${applicationScope['baseUrl']}/customer/transactionHistory/view">Transaction History</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <c:forEach items="${merchantList}" var="merchant" varStatus="index" begin="0" end="3">
                    <div class="col-md-6 col-xl-3 marginBottom25 ">
                        <c:set var="color" value="bg-c-purple" />
                        <c:if test="${index.count == 2}">
                            <c:set var="color" value="bg-c-green" />
                        </c:if>
                        <c:if test="${index.count == 3}">
                            <c:set var="color" value="bg-c-yellow" />
                        </c:if>
                        <c:if test="${index.count == 4}">
                            <c:set var="color" value="bg-c-red" />
                        </c:if>
                        <div class="card ${color} order-card">
                            <div class="card-body height130">
                                <span style="font-size: 17px;font-weight:Bold" class="text-white">
                                    <c:if test="${fn:length(merchant.merchantName) gt 16}">
                                        ${fn:substring(merchant.merchantName,0,15)}. .
                                    </c:if>
                                    <c:if test="${fn:length(merchant.merchantName) le 16}">
                                        ${merchant.merchantName}
                                    </c:if>
                                </span>
                                <p style="font-size: 14px" class="totalOrder">${merchantIdPrefix}${merchant.merchantId}
                                <span class="float-right"><i class="fas fa-rupee-sign fontSize15"></i>&nbsp;
                                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${merchant.walletBal}" /></span></p>
                                        <p><span><i class="fa fa-credit-card fontSize15"></i> ${merchant.loyaltyCardNumber}</span> </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h4>Recent Transaction</h4>
                        </div>
                        <div class="card-body">
                        	<div class="dt-responsive table-responsive">
	                            <div class="customer-scroll" style="height:400px;position:relative;">
	                                <table id="multi-colum-dt" class="table table-hover m-b-0 table-striped table-bordered nowrap">
	                                    <thead>
	                                        <tr>
	                                            <th>#</th>
	                                            <th>Merchant</th>
	                                            <th>TransactionId</th>
	                                            <th>Transaction Time</th>
	                                            <th>Transaction Type</th>
	                                            <th>Transaction Amount</th>
	                                            <th>Indicator</th>
	                                            <th>Status</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                        <c:forEach items="${customerTransactionList}" var="customerTransaction" varStatus="index" begin="0" end="9">
	                                            <tr>
	                                                <td>${index.count}</td>
	                                                <td>
	                                                    <div class="d-inline-block align-middle">
	                                                        <div class="d-inline-block">
	                                                            <p class="m-b-0 lineHight30"><strong>${customerTransaction.merchantName}</strong></p>
	                                                            <p class="m-b-0 lineHight22">${merchantIdPrefix}${customerTransaction.merchantId}</p>
	                                                        </div>
	                                                    </div>
	                                                    <div class="progress mt-1" style="height:4px;">
	                                                        <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 60%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
	                                                    </div>
	                                                </td>
	                                                <td>
	                                                    ${customerTransaction.transactionId}
	                                                    <div class="progress mt-1" style="height:4px;">
	                                                        <div class="progress-bar bg-primary rounded" role="progressbar" style="width: 50%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
	                                                    </div>
	                                                </td>
	                                                <td>
	                                                    <fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${customerTransaction.createdDate}" />
	                                                    <div class="progress mt-1" style="height:4px;">
	                                                        <div class="progress-bar bg-warning rounded" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
	                                                    </div>
	                                                </td>
	                                                <td>
	                                                    <c:choose>
															<c:when test="${customerTransaction.transactionType == 'PAY'}">
															    SALE
															</c:when>
															<c:otherwise>
															    ${customerTransaction.transactionType}
															</c:otherwise>
														</c:choose>
	                                                    <div class="progress mt-1" style="height:4px;">
	                                                        <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
	                                                    </div>
	                                                </td>
	                                                <td>
	                                                    <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customerTransaction.transactionValue}" />
	                                                    <div class="progress mt-1" style="height:4px;">
	                                                        <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
	                                                    </div>
	                                                </td>
	                                                <td>
	                                                    <c:if test="${empty(customerTransaction.indicator)}">
	                                                        -
	                                                        <div class="progress mt-1" style="height:4px;">
	                                                            <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 40%;" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
	                                                        </div>
	                                                    </c:if>
	                                                    <c:if test="${!empty(customerTransaction.indicator)}">
	                                                        <strong>${customerTransaction.indicator}</strong>
	                                                        <div class="progress mt-1" style="height:4px;">
	                                                            <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 40%;" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
	                                                        </div>
	                                                    </c:if>
	                                                </td>
	                                                <td>
	                                                    <c:if test="${customerTransaction.status == 'S'}">
	                                                        <button type="button" class="btn bg-c-green border border-green rounded btnWhite"><i class="feather icon-thumbs-up"></i></button>
	                                                    </c:if>
	                                                    <c:if test="${customerTransaction.status == 'F'}">
	                                                        <button type="button" class="btn bg-c-red border border-red rounded btnWhite"><i class="feather icon-thumbs-down"></i></button>
	                                                    </c:if>
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
    <%-- Required Js --%>
    <%@include file="jspincludes/footer.jsp"%>
    <script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
    <script src="${applicationScope['jsBaseUrl']}/javascript/customerDashboard.js" defer onload="loadPageScript()"></script>
</body>

</html>