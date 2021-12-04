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
								<h5 class="m-b-10">Customer Registration</h5>
							</div>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}"><i
										class="feather icon-home"></i></a></li>
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}/customer/view">Customer
										Registration</a></li>
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
                      		<h5>Search Customer</h5>
                     		<hr>
                  		</div>
                     	<div class="card-body">
							<div class="col-md-12 merchant">
								<div class="form-group">
							    	<label class="form-label">Mobile Number</label>
							    	<input type="text" class="form-control" name="mobileNumber" id="mobile"  placeholder="Mobile Number">
							    </div>
							</div>
							<div class="alert alert-danger mobileERRMsg DispNone" role="alert">
                        	</div>
                            <div class="col-md-12">
                                <button type="button" class="btn  btn-primary" onclick="searchCustomer('mobile')">Search</button>
                                <button class="btn btn-danger" type="reset">Reset</button>
                            </div>	
                        </div>
                    </div> 
				<div class="card">
				<div class="card-header">
					<h5>Customer Registration</h5>
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
						<c:set value="${applicationScope['baseUrl']}/customer/admin/add" var="actionUrl" />
					</c:if>
					<c:if test="${action == 'update'}">
						<c:set value="${applicationScope['baseUrl']}/customer/edit" var="actionUrl" />
					</c:if>
					<form id="customerReg-form" method="post" action="${actionUrl}">
						<div id="customerData">
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
                            </div>
                            <div class="col-md-12">
								<div class="form-group">
									<label class="form-label">Email Id<b class="mandetory">*</b></label>
									<input type="text" class="form-control" name="emailId" id="emailId" placeholder="Email Id" value="${customer.emailId}" onblur="isExistsEmailId('emailId', '${customer.emailId}')" >
								</div>
							</div>
							<div class="col-md-12">
										<div class="form-group">
											<label class="form-label">Loyalty Number</label>
											<input type="number" class="form-control" name="loyaltyCardNumber" id="loyaltyCardNumber" placeholder="Loyalty Number" minlength="16" maxlength="16"  >
								</div>
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
						<input type="hidden" name="mId" value="${sessionScope.loginDTO.merchant.id}">
						<input type="hidden" name="verifiedId" id="verifiedId" value="${customer.verifiedId}">
						<input type="hidden" name="merchantId" id="merchantId" value="${sessionScope.loginDTO.merchant.merchantId}">
						<input type="hidden" name="id" value="<c:if test='${!empty(customer.id)}'>${customer.id}</c:if><c:if test='${empty(customer.id)}'>0</c:if>">
						<div class="alert alert-danger imageERRMsg DispNone" role="alert">
                        </div>
						<c:if test="${pageRole.isAdd == 1 && empty(isPrivileges)}">
							<div class="col-md-12">
								<button type="submit" class="btn  btn-primary">
									<c:if test="${action != 'update'}">Submit</c:if>
									<c:if test="${action == 'update'}">Update</c:if>
								</button>
								<button class="btn btn-danger" type="reset" formnovalidate="formnovalidate">Reset</button>
							</div>
						</c:if>
					</form>
				</div>
			</div>
			<c:if test="${!empty(customerList)}">
				<div class="card">
					<div class="card-header">
						<h5>Customers</h5>
					</div>
					<div class="card-body">
						<div class="dt-responsive table-responsive">
							<table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
								<thead>
									<tr>
										<th>#</th>
										<th>CUstomer Id</th>
										<th>Login Id</th>
										<th>Name</th>
										<th>Email Id</th>
										<th>Mobile Number</th>
										<th>Wallet Balance</th>
										<th>Loyalty Number</th>
										<th>Asign</th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${customerList}" var="merchantMapping" varStatus="index">
										<tr>
											<td>${index.count}</td>
											<td>${customerIdPrefix}${merchantMapping.customerId}</td>
											<td>${merchantMapping.loginId}</td>
											<td>${merchantMapping.firstName} ${merchantMapping.middleName} ${merchantMapping.lastName}</td>
											<td>${merchantMapping.emailId}</td>
											<td>${merchantMapping.mobileNumber}</td>
											<td><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${merchantMapping.walletBal}" /></td>
										     <td>${merchantMapping.loyaltyCardNumber}</td>
										    <td>  <c:if test="${empty(merchantMapping.loyaltyCardNumber)}">
										     <button type="submit" class="btn  btn-primary" data-toggle="modal" data-target="#exampleModalCenter"onclick="popup('${customerIdPrefix}${merchantMapping.customerId}','${merchantMapping.loginId}','${merchantMapping.firstName} ${merchantMapping.middleName} ${merchantMapping.lastName}','${merchantMapping.emailId}','${merchantMapping.mobileNumber}','${merchantMapping.walletBal}','${merchantMapping.cId}')">Asign</button>
										</c:if>
<%-- 										<c:if test="${!empty(merchantMapping.loyaltyCardNumber)}"> --%>
<!-- 										     <i class="fa fa-eye" style="font-size:24px"></i> -->
<%-- 										</c:if> --%>
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
		
<!-- 		--------------------------- -->

<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Loyalty Numbers</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
<form action="javascript:acceptNotification('loyaltyCardNumberA','mID')">
      <div class="modal-body">

                                    <div class="row"><div class="col-md-4"><h6>Customer Id :-</h6></div><div class="col-md-4" id="customerId"> </div></div>
                                       <hr/>
                                        <div class="row"><div class="col-md-4"><h6>Login Id :-</h6></div><div class="col-md-4" id="loginId"></div></div>
                                       <hr/>
                                       <div class="row"><div class="col-md-4"><h6>Name :-</h6></div><div class="col-md-4" id="name"></div></div>
<!--                                        <hr/> -->
<!--                                         <div class="row"><div class="col-md-4"><h6 style="font-size:12.8px;">Email Id :-</h6></div><div class="col-md-4" id="emailId"></div></div> -->
<!--                                        <hr/> -->
<!--                                        <div class="row"><div class="col-md-4"><h6 style="font-size:12.8px;">Mobile Number :-</h6></div><div class="col-md-4" id="mobileNumber"></div></div> -->
                                       <hr/>
                                       <div class="row"><div class="col-md-4"><h6 style="font-size:12.8px;">Wallet Balance :-</h6></div><div class="col-md-4" id="walletBalance"></div></div>
<!--                                        <hr/> -->
<!--                                        <input style="width:100%;" type="number" name="loyaltyCardNumberA" id="loyaltyCardNumberA" placeholder="Enter Loyalty Card Number" minlength="16" maxlength="16" required /> -->
                                       
                                       <hr/>
                                       <div class="col-md-12">
										
											<input type="number" class="form-control" name="loyaltyCardNumberA" id="loyaltyCardNumberA" placeholder="Enter Loyalty Card Number" minlength="16" maxlength="16" required>
                                       </div>
						    <hr/>

						               <input type="hidden" name="mID" id="mID" value="${sessionScope.loginDTO.merchant.id}"> 
						               <input type="hidden" name="cId" id="cId" value="${sessionScope.loginDTO.merchant.id}"> 
                                    <div><h5 style="color:red">Do you really want to proceed..</h5></div>
                                      
      </div>
     

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
     	<button type="submit" class="btn btn-primary">Ok</button>
      </div>
     </form>

    </div>
  </div>
</div>
<!-- ------------------------------------- -->
		
		
	<!-- [ Form Validation ] end -->
	</div>
	<%-- [ Main Content ] end --%>
</div>
<%@include file="mobileVerificationModal.jsp"%>
<%@include file="jspincludes/footer.jsp"%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/aes/aes.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/customerReg.js" defer onload="loadPageScript()"></script>
<script type="text/javascript">
var basepath = "${applicationScope['baseUrl']}";var sessionId = "${r:toHexDecimal(pageContext.session.id)}";var isSuccess = "";var ks = "${aes.keySize}";var ic = "${aes.iterationCount}";
</script>
</body>
</html>

