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
                            <div id="reportrange"  onchange="getAdminDashaboardData()">
<!--                                     <i class="feather icon-calendar featherIcon"></i>&nbsp; -->
                                    <span style="display:none"></span> <i></i>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

  <div class="page-header">
                <div class="page-block">
                    <div class="row align-items-center">
                        <div class="col-md-12">
                            <div class="page-header-title">
                                <h5 class="m-b-10">Notification List</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/add/user/notification">Notification</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        <%-- [ Main Content ] start --%>
        
      
            <div class="">
					<div class="page-block">
						<div class="row align-items-center">
							<div class="col-md-12">
								<div class="form-group col-md-4 row-dropDown">
		                         <select class="form-control js-example-basic-single" name="status" id="status" onchange="getNotificationList('status')">

		                                   <option>--Select Option--</option>
		                                   <option value="3">Accepted</option>
		                                   <option value="0">Pending</option>
		                            
		                         </select>
		                     	</div>
		                	</div>
		               </div> 
		            </div>
		        </div>


         <%-- Customer overview start --%>
                   <div id="notificationListDetails">
        <div class="row">
            <div class="col-md-12">
                <div class="card table-card">
                    <div class="card-body p-0">
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane fade show active" id="pills-history" role="tabpanel" aria-labelledby="pills-history-tab">
                                <div class="table-responsive">
                                    <div class="customer-scroll MarginMerchantSnapShot" id="tab">
                                        <table class="table table-hover m-b-0 table-striped table-bordered nowrap" id="multi-colum-dt-1">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Request Id</th>
                                                    <th>Merchant Name</th>
                                                    <th>Merchant Id</th>
                                                    <th>Cards Quntity</th>
                                                    <th>Cost Per Unit</th>
                                                    <th>Total Cost</th>
                                                    <th>Payment Mode</th>
                                                    <th>Card Type</th> 
                                                    <th>Payment Reference Number</th>
                                                    <th>Notification Date</th>
                                                    <th>
                                                       <c:choose>
                                                            <c:when test="${!empty(sessionScope.loginDTO.notificationsList)}">
                                                                   Status
                                                            </c:when>
                                                            <c:otherwise>
                                                                    Action
                                                            </c:otherwise>
                                                         </c:choose> 
                                                   </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:choose>
                              <c:when test="${!empty(sessionScope.loginDTO.notificationsList)}">
                                    <c:forEach items="${sessionScope.loginDTO.notificationsList}" var="notification" varStatus="index">
                                    
                                             <tr>
                                                <td>${index.count}</td>
                                                <td>${notification.id}</td>
                                                <td>${notification.name}</td>
                                                <td>${notification.merchantId}</td>
                                                <td>${notification.quantityOfCards}</td>
                                                <td>${notification.costPerUnit}</td>
                                                <td>${notification.totalCost}</td>
                                                <td>${notification.paymentMode}</td>
                                                <td>${notification.cardType}</td>
                                                <td>${notification.paymentReferenceNumber}</td>
                                                <td><fmt:formatDate pattern = "dd MMM, YYYY" value = "${notification.insertTimeStamp}"/></td>
                                                <td>approved </td>
                                            </tr>
                                    
                                    
                                    </c:forEach>
    </c:when>
    <c:otherwise>
           <c:forEach items="${sessionScope.loginDTO.requestLoyaltyCardsNotificationList}" var="notificationList" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${notificationList.id}</td>
                                                <td>${notificationList.name}</td>
                                                <td>${notificationList.merchantId}</td>
                                                <td>${notificationList.quantityOfCards}</td>
                                                <td>${notificationList.costPerUnit}</td>
                                                <td>${notificationList.totalCost}</td>
                                                <td>${notificationList.paymentMode}</td>
                                                <td>${notificationList.cardType}</td>
                                                <td>${notificationList.paymentReferenceNumber}</td>
                                                 <td><fmt:formatDate pattern = "dd MMM, YYYY" value = "${notificationList.insertTimeStamp}"/></td>
                                                <td>
                                                 <a href="javascript:popup('${notificationList.id}','${notificationList.name}','${notificationList.merchantId}','${notificationList.quantityOfCards}')">
                                                 <i class="fa fa-check FontSize24" data-toggle="modal" data-target="#exampleModalCenter" ></i>
                                                 </a>
                                                 <a href="javascript:rejectPopup('${notificationList.id}')
                                                 ">
                                                 <i class="fa fa-ban FontSize24" data-toggle="modal" data-target="#exampleModalCenter1"></i>
                                                 </a> 
                                                </td>
                                            </tr>
                                        </c:forEach>
    </c:otherwise>
</c:choose>
<%--                                              <c:if test="${!empty(sessionScope.loginDTO.notificationsList)}"> --%>
                                            
<%--                                                <c:forEach items="${sessionScope.loginDTO.notificationsList}" var="notification" varStatus="index"> --%>
                                    
<!--                                              <tr> -->
<%--                                                 <td>${index.count}</td> --%>
<%--                                                 <td>${notification.id}</td> --%>
<%--                                                 <td>${notification.merchantName}</td> --%>
<%--                                                 <td>${notification.mId}</td> --%>
<%--                                                 <td>${notification.quantityOfCards}</td> --%>
<!--                                                 <td> -->
<!--                                                approved -->
<!--                                             </tr> -->
                                    
                                    
<%--                                     </c:forEach> --%>
<%--                                              </c:if> --%>
                                           <!--         
<!--                                                                     ------------------------------ --> 
<%--                                     <c:forEach items="${sessionScope.loginDTO.notificationsList}" var="notification" varStatus="index"> --%>
                                    
<!--                                      <tr> -->
<%--                                                 <td>${index.count}</td> --%>
<%--                                                 <td>${notification.id}</td> --%>
<%--                                                 <td>${notification.merchantName}</td> --%>
<%--                                                 <td>${notification.mId}</td> --%>
<%--                                                 <td>${notification.quantityOfCards}</td> --%>
<!--                                                 <td> -->
<!--                                                approved -->
<!--                                             </tr> -->
                                    
<%--                                      <h1>${notification.id}</h1>  --%>
<%--                                     </c:forEach> --%>
<!--                                     ----------------------------------- -->
                                          
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
    
<!--     --------------------------------- -->


<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Allocation Info</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

                                    <div class="row"><div class="col-md-4"><h6>Request Id :-</h6></div><div class="col-md-4" id="requestId"> </div></div>
                                       <hr/>
                                        <div class="row"><div class="col-md-4"><h6>Merchant Name :-</h6></div><div class="col-md-4" id="merchantName"></div></div>
                                       <hr/>
                                       <div class="row"><div class="col-md-4"><h6>Merchant Id :-</h6></div><div class="col-md-4" id="merchantId"></div></div>
                                       <hr/>
                                        <div class="row"><div class="col-md-4"><h6 style="font-size:12.8px;">Quantity Of Cards :-</h6></div><div class="col-md-4" id="quantityOfCard"></div></div>
                                       <hr/>
                                    <div><h5 style="color:red">Do you really want to proceed..</h5></div>
                                      
      </div>
     

      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
     	<a href="javascript:acceptNotification()" title="Acceted">
    		<button type="submit" class="btn btn-primary">Ok</button>
     	</a>
      </div>

    </div>
  </div>
</div>
<!-- ------------------------------------------------------------------ -->
<div class="modal fade" id="exampleModalCenter1" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Reason for Reject </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
           <form action="javascript:rejectNotification('reason')">
      <div class="modal-body">
                                
                                  <textarea style="width:100%; " type="text" name="msg" id="reason" placeholder="Enter Reason" required></textarea>
                                       <hr/>
                                        
                                       <hr/>
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


<!---------------------------------------------------------------------------------------------------->
 </div>
    </div>

        <%-- [ Main Content ] end --%>
<%@include file="jspincludes/footer.jsp" %> 
<script>	
$(document).ready(function() {
    $('#multi-colum-dt-1').DataTable();
})</script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/exportReport.js" defer></script>
<%-- <script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/daterangepicker.js" defer></script> --%>
<script src="${applicationScope['jsBaseUrl']}/javascript/notificationLoyaltyCard.js" defer onload="loadPageScript()"></script>

</body>
</html>
