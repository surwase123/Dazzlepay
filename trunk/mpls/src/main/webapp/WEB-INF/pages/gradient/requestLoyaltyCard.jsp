<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<head>
    <title>MPLS - Merchant Request Loyalty Cards Solution</title>
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
<!-- ---------------------------------------- -->
  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/jquery.dataTables.min.js"></script>


<!-- ----------------------------------------------- -->
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
                                <h5 class="m-b-10">Request Loyalty Card</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/loyaltyCard/view">Request Loyalty Card</a></li>
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
                            <h5>Request Loyalty Cards</h5>
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
                           <form action="${applicationScope['baseUrl']}/requestLoyaltyCard/view1" method="post" >
                    
                               <div class="col-md-12">
									<div class="form-group">
										<label class="form-label">Quantity Of Cards</label> 
										<input type="number" class="form-control capital-letter" onblur="myFunction()" name="quantityOfCards" id="quantityOfCards" placeholder="Enter number of cards">
									</div>
								</div>
`								<input type="hidden" name="merchantName" id="merchantName" value="${sessionScope.loginDTO.merchant.merchantName}" >
				                <input type="hidden" name="merchantId" id="mId" value="${sessionScope.loginDTO.merchant.merchantId}" required>
<%-- 				                <input type="hidden" name="merchantId" id="mId" value="${merchant.merchantName}"> --%>
								<div class="col-md-12">
								   <div class="row">
										 <div class="col-md-4">
											<div class="form-group">
												<label class="form-label">Cost Per Unit</label> 
												<input type="number" class="form-control capital-letter" name="costPerUnit" id="costPerUnit" placeholder="Enter Cost per Unit"   onblur="myFunction()" value="totalCost">
											</div>
										</div>
		                                
 						                <div class="col-md-4">
												<div class="form-group">
													<label class="form-label">Card Type</label>
							                         <select class="form-control js-example-basic-single" name="cardType" id="cardType">
					<%-- 		                             <c:forEach items="${merchantList}" var="merchant" varStatus="index"> --%>
							                                   <option value="">--Select Card Type--</option>
							                                   <option value="white plastic">white plastic</option>
							                                   <option value="Design Plastic">Design Plastic</option>
					<%-- 		                             </c:forEach> --%>
							                         </select>
                                                 </div>
		                                  </div> 
		                                  <div class="col-md-4">
												<div class="form-group">
													<label class="form-label">Payment Mode</label>
							                         <select class="form-control js-example-basic-single" name="paymentMode" id="paymentMode" onchange="paymentModeValue()">
							                             <option value="">--Select Payment Mode--</option>
							                                  <option value="UPI">UPI</option>
							                                  <option value="Bank Transfer">Bank Transfer</option>
							                                  <option value="Cash">Cash</option>
				
							                         </select>
						                         </div>
		                                   </div> 
		                           </div>
		                       </div>
		        <div class="col-md-12">
		        <div class="row">
		        
		        <div class="col-md-4">
											<div class="form-group">
												<label class="form-label">Total Cost</label> 
												<input type="number" class="form-control capital-letter" name="totalCost" id="totalCost" placeholder="Enter total cost" readonly="readonly" onblur="myFunction()" value="costPerUnit">
											</div>
										</div>
		        
		                        <div class="col-md-8" id="refNumber" style="display:none;">
									<div class="form-group">
										<label class="form-label">Payment Reference Number</label> 
										<input type="text"  class="form-control capital-letter" name="paymentReferenceNumber" id="paymentReferenceNumber" placeholder="Enter Payment Reference Number">
									</div>
								</div>
								
		        
		        </div>
		        </div>
		        
									<div class="col-md-12">
								<div class="form-group green-border-focus">
								<label class="form-label"> Address</label>
                                 <textarea class="form-control " name="shippingAddress" placeholder="Shipping Address for Dispatch" id="shippingAddress" rows="3" required></textarea>
                                 </div>
                                 </div>
                                 <div class="col-md-12">
                                           <button type="submit" class="btn btn-primary">Submit</button>
                                           <button class="btn btn-danger" type="reset">Reset</button> 
                                 </div>	
                   
                    </form>
                        </div>
                    </div>
                    <div class="card">
					<div class="card-body">
					<div class="card-header">
					
					<div><h5>Loyalty Cards Request Status</h5></div>
					</div>
						<div class="dt-responsive table-responsive">
							<table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
								<thead>
									<tr>
										<th>#</th>
										<th>Request Id</th>
<!-- 										<th></th> -->
										<th>Card Quantity</th>
										<th>Cost/Unit</th>
										<th>Total Cost</th>
										<th>Card Type</th>
										<th>Payment Mode</th>
										<th>Status</th>
										<th>Date</th>
<!-- 										<th>Time</th> -->
										
										
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pendingAcceptedRejectedCardRequest}" var="allotedLoyaltyCardNumber" varStatus="index">
										
										<tr>
											<td>${index.count}</td>
	 									    <td>${allotedLoyaltyCardNumber.id}</td>
											<td>${allotedLoyaltyCardNumber.quantityOfCards}</td>
											<td>${allotedLoyaltyCardNumber.costPerUnit}</td>
											<td>${allotedLoyaltyCardNumber.totalCost}</td>
											<td>${allotedLoyaltyCardNumber.cardType}</td>
											<td>${allotedLoyaltyCardNumber.paymentMode}</td>
											<td>
										      <c:if test="${allotedLoyaltyCardNumber.status  == 0 }">
										      Pending
											  </c:if>
											  <c:if test="${allotedLoyaltyCardNumber.status  == 1 }">
										      Accepted
											  </c:if>
											 </td>
											      <td> <fmt:formatDate pattern = "dd MMM, YYYY" value = "${allotedLoyaltyCardNumber.insertTimeStamp}"/></td>
<!-- 											<td></td> -->
											
										</tr>
										
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

<%--                 <h4> <%=val3%>       </h4>  --%>
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