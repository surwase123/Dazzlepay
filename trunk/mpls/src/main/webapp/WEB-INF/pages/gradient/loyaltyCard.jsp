<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
<link rel="icon"
	href="${applicationScope['baseUrl']}/resources/gradient/images/favicon.ico"
	type="image/x-icon">
<%-- vendor css --%>
<link rel="stylesheet"
	href="${applicationScope['cssBaseUrl']}/css/vendor.css">
<%-- common css --%>
<link rel="stylesheet"
	href="${applicationScope['cssBaseUrl']}/gradient/css/style.css">
        <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/jquery.dataTables.min.js"></script>
<!-- --------------------------- -->

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
								<h5 class="m-b-10">Loyalty Card</h5>
							</div>
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}"><i
										class="feather icon-home"></i></a></li>
								<li class="breadcrumb-item"><a
									href="${applicationScope['baseUrl']}/loyaltyCard/view">Loyalty
										Card</a></li>
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
							<h5>Generate Loyalty Cards</h5>
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

							<form id="LoyaltyNumber-form" class="upload-box" modelAttribute="formUpload"
								action="${applicationScope['baseUrl']}/loyaltyCard/generateNumbers"
								method="post" enctype="multipart/form-data">
							 <div class="col-md-12">
									<div class="form-group">
										<label class="form-label">No of cards generate</label> 
										<input type="number" class="form-control capital-letter" name="noOfCardsGenerate" id="noOfCardsGenerate" placeholder="Enter amount number of cards" >
									</div>
							</div>
								
							
								<div class="col-md-12">
									<button  type="submit" class="btn btn-primary" >Submit</button>
									<button  class="btn btn-danger" type="reset">Reset</button>
                                </div>
                                    </form>	
                                    </div>
                                    </div>
                  <div class="card">
					<div class="card-header">					
						<h5>Generated Loyalty Cards Number</h5>
					</div>
					<div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
								  <thead>
                                                <tr>
                                                    
                                                    <th>#</th>
                                                    <th>Request Id</th>
                                                    <th>Quantity of Number Generate</th>
                                                    <th>Date</th>
<!--                                                     <th>Time</th> -->
                                                    <th></th>
<!--                                                     <th>Action</th> -->
                                                    
                            
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${generatedLoyaltyNumberList}" var="generatedLoyaltyNumberList" varStatus="index">
                                                 <tr>
                                                    
                                                   <td>${ index.count}</td>
											       <td>${generatedLoyaltyNumberList.id } </td>
											       <td>${generatedLoyaltyNumberList.quantity }</td>
											       <td><fmt:formatDate pattern = "dd MMM, YYYY" value = "${generatedLoyaltyNumberList.insertTimeStamp}"/></td>
<!--                                                     <td>10:30 am</td> -->
                                                    <td align="center"><button  type="submit" class="btn btn-primary" onclick="location.href='${applicationScope['baseUrl']}/loyaltyCard/downloadCsv?id=${generatedLoyaltyNumberList.id }'" >Export CSV</button></td>
											                                                       </tr>
                                             
                                              
                                                 </c:forEach>
                                            </tbody>
							</table>
						</div>
						</div>
					
				</div>
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
		        <div id="allotedCardsList"> 
					<div class="card">
					<div class="card-header">
					
					<h5>Allotted Loyalty Cards</h5>
					</div>
					<div class="card-body">
						<div class="dt-responsive table-responsive">
							<table id="multi-colum-dt-1" class="table table-striped table-bordered nowrap">
								<thead>
									<tr>
										<th>#</th>
										<th>Request Id</th>
										<th>Merchant Name</th>
										<th>Merchant Id</th>
										<th>Quantity Of Number</td>
										<th>Address</th>
										<th>Date</th>
										<th></th>
										
<!-- 										<Th>Action</Th> -->										
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pushNotificationList}" var="pushNotificationList" varStatus="index"> 
								<c:if test="${pushNotificationList.status  == 3 }">
										<tr>
											<td>${index.count}</td>
											<td>${pushNotificationList.id}</td>
											<td>${pushNotificationList.name}</td>
											<td>${pushNotificationList.mId}</td>
											<td>${pushNotificationList.quantityOfCards}</td>
											<td>${pushNotificationList.shippingAddress}</td>
											<td><fmt:formatDate pattern = "dd MMM, YYYY" value = "${pushNotificationList.insertTimeStamp}"/></td>
											<td>
											<button  type="submit" class="btn btn-primary" onclick="location.href='${applicationScope['baseUrl']}/loyaltyCard/downloadcsvbyrid?id=${pushNotificationList.id }&mId=${pushNotificationList.mId}'">Export CSV</button>
											<button  type="submit" class="btn btn-danger"  >Dispatch</button>
											</td>
										</tr>
										</c:if>
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
				<!-- [ Form Validation ] end -->
			</div>
			<%-- [ Main Content ] end --%>
		</div>
		<%@include file="jspincludes/footer.jsp"%>
		<script
			src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js"
			defer></script>
<%-- 			<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/exportReport.js" defer></script> --%>
		<script
			src="${applicationScope['jsBaseUrl']}/javascript/loyaltyCard.js" defer
			></script>
			
			
			
			<script>
			$(document).ready(function() {
			    $('#multi-colum-dt').DataTable();
			})
			$(document).ready(function() {
			    $('#multi-colum-dt-1').DataTable();
			})
				$(document).ready(function() {
			    $('#multi-colum-dt-2').DataTable();
			})
          
			</script>
			
		
</body>
</html>