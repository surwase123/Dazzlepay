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
                                <h5 class="m-b-10">Allotted Loyalty Cards Number</h5>
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
					<div class="card-body">
					<div class="card-header">
					
					<div><h5>Allotted Loyalty Cards Number</h5></div>
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
										<th></th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${allotedLoyaltyCardNumber}" var="allotedLoyaltyCardNumber" varStatus="index">
										<tr>
										<c:if test="${allotedLoyaltyCardNumber.status  == 3 }">
											<td>${index.count}</td>
											
	 									    <td>${allotedLoyaltyCardNumber.id}</td>
											<td>${allotedLoyaltyCardNumber.quantityOfCards}</td>
											<td>${allotedLoyaltyCardNumber.costPerUnit}</td>
											<td>${allotedLoyaltyCardNumber.totalCost}</td>
											<td>${allotedLoyaltyCardNumber.cardType}</td>
											<td>${allotedLoyaltyCardNumber.paymentMode}</td>
											<td>
										      <c:if test="${allotedLoyaltyCardNumber.status  == 3 }">
										      Allocatted
											  </c:if>
											  </td>
											      <td> <fmt:formatDate pattern = "dd MMM, YYYY" value = "${allotedLoyaltyCardNumber.insertTimeStamp}"/></td>
<!-- 											<td></td> -->
											<td style="align:center"> <button type="submit" class="btn btn-primary" onclick="location.href='${applicationScope['baseUrl']}/loyaltyCard/downloadcsvbyrid?id=${allotedLoyaltyCardNumber.id }&mId=${sessionScope.loginDTO.merchant.id}'">Export CSV</button></td>
										</c:if>
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