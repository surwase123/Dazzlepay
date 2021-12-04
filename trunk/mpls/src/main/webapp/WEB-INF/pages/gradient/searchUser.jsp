<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
        <%-- [ Main Content ] start --%>
        <div class="pcoded-content">
             <!-- [ breadcrumb ] start -->
            <div class="page-header">
                <div class="page-block">
                    <div class="row align-items-center">
                        <div class="col-md-12">
                            <div class="page-header-title">
                                <h5 class="m-b-10">Search Users</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/searchUser/view">Search Users</a></li>
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
                            <h5>Search Users</h5>
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
                            <form id="blockUnblock-form" action="${applicationScope['baseUrl']}/searchUser/searchDetails" method="post">
                                    <div class="col-md-12">
                                        <label class="form-label">User Type</label>
                                        <div class="form-group">
                                            <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="merchant" name="userType" class="custom-control-input" value="merchant" onChange="showSearchParameter()" <c:if test="${searchUser.userType == 'merchant'}">checked</c:if> <c:if test="${empty(searchUser.userType)}">checked</c:if>>
                                                    <label class="custom-control-label" for="merchant">Merchant</label>
                                            </div>
                                            <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="customer" name="userType" class="custom-control-input" value="customer" onChange="showSearchParameter()" <c:if test="${searchUser.userType == 'customer'}">checked</c:if>>
                                                    <label class="custom-control-label" for="customer">Customer</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12 merchant">
                                        <div class="form-group">
                                            <label class="form-label">Search By</label>
                                            <select class="form-control" name="merchantSearchBy" id="merchantSearchBy">
                                                <option value="">--Search By--</option>
                                                <c:forEach items="${merchantSearchParam}" var="merchantSearch" varStatus="index">
                                                      <option value="${merchantSearch.paramValue}" <c:if test="${merchantSearch.paramValue == searchUser.merchantSearchBy}">selected</c:if>>${merchantSearch.paramDisplayName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12 customer DispNone">
                                        <div class="form-group">
                                            <label class="form-label">Search By</label>
                                            <select class="form-control" name="customerSearchBy" id="customerSearchBy">
                                                <option value="">-- Search By--</option>
                                                <c:forEach items="${customerSearchParam}" var="customerSearch" varStatus="index">
                                                      <option value="${customerSearch.paramValue}" <c:if test="${customerSearch.paramValue == searchUser.customerSearchBy}">selected</c:if>>${customerSearch.paramDisplayName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Search Value</label>
                                            <input type="text" class="form-control" name="searchValue" id="searchValue"  placeholder="Search Value" value="${searchUser.searchValue}">
                                        </div>
                                    </div>
                                    <input type="hidden" name="updatedBy" value="${sessionScope.loginDTO.loginId}">
                                    <div class="col-md-12">
                                       <button type="submit" class="btn  btn-primary">Search</button>
                                       <button class="btn btn-danger" type="reset">Reset</button>
                                    </div>
                            </form>
                        </div>
                    </div>
                    <c:if test="${!empty(searchUser) && empty(merchantList) && empty(customerList)}">
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
                   <c:if test="${!empty(merchantList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>User Info</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Login Id</th>
                                            <th>Merchant Id</th>
                                            <th>Merchant Name</th>
                                            <th>Email Id</th>
                                            <th>Mobile Number</th>
                                            <th>Status</th>
                                            <th class="text-center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${merchantList}" var="merchant" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${merchant.loginId}</td>
                                                <td>${merchantIdPrefix}${merchant.merchantId}</td>
                                                <td>${merchant.merchantName}</td>
                                                <td>${merchant.emailId}</td>
                                                <td>${merchant.mobileNumber}</td>
  												<c:choose>
												  <c:when test="${merchant.isActive == 1}">
												    <td>Active</td>
												  </c:when>
												  <c:otherwise>
												    <td>Block</td>
												  </c:otherwise>
												</c:choose>			                                              
                                                <td class="text-center">
                                                    <c:if test="${merchant.isActive == 1}">
                                                        <button type="button" class="MarginLeft13 btn bg-c-red border border-red btnWhite pointer" onclick="updateMerchant('${merchant.id}','${sessionScope.loginDTO.loginId}', '${merchant.isActive}' , 'Block')" title="Block"><i class="fas fa-user-minus"></i></button>
                                                    </c:if>
                                                    <c:if test="${merchant.isActive == 0}">
                                                         <button type="button" class="MarginLeft13 btn bg-c-green border border-green btnWhite pointer" onclick="updateMerchant('${merchant.id}', '${sessionScope.loginDTO.loginId}', '${merchant.isActive}' , 'UnBlock')" title="UnBlock"><i class="fas fa-user-plus"></i></button>
                                                    </c:if>
                                                    <c:if test="${merchant.isLocked == 1}">
                                                        <button type="button" class="MarginLeft13 btn bg-c-yellow border border-yellow btnWhite pointer" onclick="unLockMerchant('${merchant.id}','${sessionScope.loginDTO.loginId}', '${merchant.isActive}' , 'UnLock')" title="UnLock"><i class="fas fa-lock-open"></i></button>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                 </c:if>
                 <c:if test="${!empty(customerList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>User Info</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Login Id</th>
                                            <th>Customer Id</th>
											<th>Name</th>
											<th>Email Id</th>
											<th>Mobile Number</th>
											<th>Status</th>
                                            <th class="text-center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${customerList}" var="customer" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${customer.loginId}</td>
                                                <td>${customerIdPrefix}${customer.customerId}</td>
												<td>${customer.firstName} ${customer.middleName} ${customer.lastName}</td>
												<td>${customer.emailId}</td>
												<td>${customer.mobileNumber}</td>
												<c:choose>
												  <c:when test="${customer.isActive == 1}">
												    <td>Active</td>
												  </c:when>
												  <c:otherwise>
												    <td>Block</td>
												  </c:otherwise>
												</c:choose>
                                                <td class="text-center">
                                                    <c:if test="${customer.isActive == 1}">
                                                        <button type="button" class="MarginLeft13 btn bg-c-red border border-red btnWhite pointer" onclick="updateCustomer('${customer.id}', '${sessionScope.loginDTO.loginId}','${customer.isActive}', 'Block')" title="Block"><i class="fas fa-user-minus"></i></button>
                                                    </c:if>
                                                    <c:if test="${customer.isActive == 0}">
                                                        <button type="button" class="MarginLeft13 btn bg-c-green border border-green btnWhite pointer" onclick="updateCustomer('${customer.id}', '${sessionScope.loginDTO.loginId}','${customer.isActive}', 'UnBlock')" title="UnBlock"><i class="fas fa-user-plus"></i></button>
                                                    </c:if>
                                                    <c:if test="${customer.isLocked == 1}">
                                                        <button type="button" class="MarginLeft13 btn bg-c-yellow border border-yellow btnWhite pointer" onclick="unLockCustomer('${customer.id}','${sessionScope.loginDTO.loginId}', '${customer.isActive}' , 'UnLock')" title="UnLock"><i class="fas fa-lock-open"></i></button>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
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
<script src="${applicationScope['jsBaseUrl']}/javascript/searchUser.js" defer onload="loadPageScript()"></script>
</body>
</html>
