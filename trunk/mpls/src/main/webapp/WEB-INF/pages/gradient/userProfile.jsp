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
                                <h5 class="m-b-10">User Profile</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/add/user/profile">User Profile</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- [ breadcrumb ] end -->
            <!-- [ Main Content ] start -->
            <div class="row">
                <!-- [ Form Validation ] start -->
                <div class="col-sm-12 userDetails" id="userProfile">
                    <div class="card">
                        <div class="card-header center">
                            <h5>User Profile</h5>
                            <c:if test="${sessionScope.loginDTO.groupDTO.groupType == 'customer'}">
                                 <button type="button" onclick="updateUserProfile('${sessionScope.loginDTO.id}','${sessionScope.loginDTO.groupDTO.groupType}')" class="btn btn-primary btn-sm rounded m-0 float-right"><i class="feather icon-edit"></i>
    							</button>
                            </c:if>
                        </div>
                        <div class="card-body task-details">
                            <table class="table table-striped table-bordered table-whitespace">
                                <tbody>
                                    <c:if test="${sessionScope.loginDTO.groupDTO.groupId == 'merchant' && !empty(sessionScope.loginDTO.merchant)}">
                                        <tr>
                                            <td class="center"><strong>Merchant Id</strong></td>
                                            <td class="center">${merchantIdPrefix}${sessionScope.loginDTO.merchant.merchantId}</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${sessionScope.loginDTO.groupDTO.groupId == 'customer' && !empty(sessionScope.loginDTO.customer)}">
                                        <tr>
                                            <td class="center"><strong>Customer Id</strong></td>
                                            <td class="center">${customerIdPrefix}${sessionScope.loginDTO.customer.customerId}</td>
                                        </tr>
                                    </c:if>
                                    <tr>
                                        <td class="center"><strong>Name</strong></td>
                                        <td class="center">${sessionScope.loginDTO.firstName} ${sessionScope.loginDTO.lastName}</td>
                                    </tr>
                                    <tr>
                                        <td class="center"><strong>LoginId</strong></td>
                                        <td class="center">${sessionScope.loginDTO.loginId}</td>
                                    </tr>
                                    <tr>
                                        <td class="center"><strong>EmailId</strong></td>
                                        <td class="center">${sessionScope.loginDTO.emailId}</td>
                                    </tr>
                                    <c:if test="${sessionScope.loginDTO.groupDTO.groupId == 'customer' && !empty(sessionScope.loginDTO.customer)}">
                                        <tr>
                                            <td class="center"><strong>Mobile Number</strong></td>
                                            <td class="center">${sessionScope.loginDTO.customer.mobileNumber}</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${sessionScope.loginDTO.groupDTO.groupId == 'merchant' && !empty(sessionScope.loginDTO.merchantEmployee)}">
                                        <tr>
                                            <td class="center"><strong>Mobile Number</strong></td>
                                            <td class="center">${sessionScope.loginDTO.merchantEmployee.mobileNumber}</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${!empty(sessionScope.loginDTO.managerId)}">
                                        <tr>
                                            <td class="center"><strong>Manager Id</strong></td>
                                            <td class="center">${sessionScope.loginDTO.managerId}</td>
                                        </tr>
                                    </c:if>

                                    <%-- <tr>
                                        <td class="center"><strong>Group Name</strong></td>
                                        <td class="center">${sessionScope.loginDTO.groupDTO.groupName}</td>
                                    </tr>
                                    <tr>
                                        <td class="center"><strong>User Privilege</strong></td>
                                        <td class="center"><a href="javascript:userRoleDeail('${sessionScope.loginDTO.groupId}', '${sessionScope.loginDTO.groupDTO.groupName}')">User Privilege</a></td>
                                    </tr> --%>
                                    <c:if test="${!empty(sessionScope.loginDTO.lastLoggedOn) && sessionScope.loginDTO.lastLoggedOn != '0000-00-00 00:00:00'}">
                                        <tr>
                                            <td class="center"><strong>Last Logged On</strong></td>
                                            <td class="center"><fmt:formatDate pattern = "dd MMM, YYYY HH:MM" value = "${sessionScope.loginDTO.lastLoggedOn}" /></td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${!empty(sessionScope.loginDTO.passwordChangedDate) && sessionScope.loginDTO.passwordChangedDate != '0000-00-00 00:00:00'}">
                                        <tr>
                                            <td class="center"><strong>Password Changed Date</strong></td>
                                            <td class="center"><fmt:formatDate pattern = "dd MMM, YYYY HH:MM" value = "${sessionScope.loginDTO.passwordChangedDate}" /></td>
                                        </tr>
                                    </c:if>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- [ Form Validation ] end -->
                
        
					<div class="col-sm-12 DispNone" id="customerEdit">
					</div>
				
                
                <div ></div>
        </div>
        <%-- Customer overview start --%>
        <%-- <div class="row">
            <div class="col-md-12">
                <div class="card table-card">
                    <div class="card-header">
                        <h5>User Snapshot</h5>
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
                                <a class="nav-link active" id="pills-profile-tab" data-toggle="pill" href="#pills-history" role="tab" aria-controls="pills-history" aria-selected="false"><i class="feather icon-file-text m-r-5"></i> History</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-message" role="tab" aria-controls="pills-message" aria-selected="false"><i class="feather icon-mail m-r-5"></i> Message</a>
                            </li>
                            <c:if test="${!empty(sessionScope.loginDTO.notificationList)}">
                                <li class="nav-item">
                                    <a class="nav-link" id="pills-notification-tab" data-toggle="pill" href="#pills-notification" role="tab" aria-controls="pills-notification" aria-selected="false"><i class="feather icon-mail m-r-5"></i> Notification</a>
                                </li>  
                            </c:if>                         
                        </ul>
                    </div>
                    <div class="card-body p-0">
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane fade show active" id="pills-history" role="tabpanel" aria-labelledby="pills-history-tab">
                                <div class="table-responsive">
                                    <div class="customer-scroll" style="height:362px;position:relative;">
                                        <table class="table table-hover m-b-0">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Date</th>
                                                    <th>Request Url</th>
                                                    <th>Session Id</th>
                                                    <th>Source</th>
                                                    <th>Ip Address</th>
                                                    <th>OS</th>
                                                    <th>Browser</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                               <c:forEach items="${userHistoryList}" var="userHistory" varStatus="index" begin="0" end="99">
                                                    <tr>
                                                        <td>${index.count}</td>
                                                        <td>
                                                            <fmt:formatDate pattern = "dd MMM, YYYY HH:MM" value = "${userHistory.date}"/>
                                                            <div class="progress mt-1" style="height:4px;">
                                                                <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            ${userHistory.request_url}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-primary rounded" role="progressbar" style="width: 50%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            ${userHistory.session_id}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-warning rounded" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                           ${userHistory.source}
                                                           <div class="progress mt-1" style="height:4px;">
                                                                 <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                           ${userHistory.ip_address}
                                                           <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                           ${userHistory.os}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 40%;" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            ${userHistory.browser}
                                                            <div class="progress mt-1" style="height:4px;">
                                                                <div class="progress-bar bg-warning rounded" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="pills-message" role="tabpanel" aria-labelledby="pills-message-tab">
                                <div class="table-responsive">
                                    <div class="customer-scroll1" style="height:362px;position:relative;">
                                        <table class="table table-hover m-b-0">
                                            <thead>
                                               <tr>
                                                    <th>#</th>
                                                    <th>Maker Id</th>
                                                    <th>Checker Id</th>
                                                    <th><span>Insert Date <a class="help" data-toggle="popover" title="Creation date" data-content="Record Creation Date(Created By Maker)" data-original-title="Popover title" aria-describedby="popover706420"><i class="feather icon-help-circle f-16"></i></a></span></th>
                                                    <th>Status</th>
                                                    <th>message</th>
                                                    <th><span>Update Date <a class="help" data-toggle="popover" title="Update Date" data-content="Record Approved Date(Updated By Checker)" data-original-title="Popover title" aria-describedby="popover737154"><i class="feather icon-help-circle f-16"></i></a></span></th>
                                                    <th>comment</th>
                                               </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${sessionScope.loginDTO.makerNotificationList}" var="notification" varStatus="index" begin="0" end="50">
                                                    <tr>
                                                        <td>${index.count}</td>
                                                        <td>
                                                            ${notification.creatorId}
                                                            <div class="progress mt-1" style="height:4px;">
                                                                <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            ${notification.checkerId}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-primary rounded" role="progressbar" style="width: 50%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <fmt:formatDate pattern = "dd MMM, YYYY HH:MM" value = "${notification.insertDate}"/>
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-warning rounded" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <c:choose>
                                                            <c:when
                                                                test="${notification.status == '0' && notification.recordId != '0'}">
                                                                <td>
                                                                    Pending For Approval
                                                                    <div class="progress mt-1" style="height:4px;">
                                                                       <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                                    </div>
                                                                </td>
                                                            </c:when>
                                                            <c:when
                                                                test="${notification.status == '1' && notification.recordId != '0'}">
                                                                <td>
                                                                    Approved
                                                                    <div class="progress mt-1" style="height:4px;">
                                                                       <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                                    </div>
                                                                </td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td>
                                                                    Rejected
                                                                    <div class="progress mt-1" style="height:4px;">
                                                                       <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                                    </div>
                                                                </td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <td>
                                                            ${notification.message}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <c:if test="${!empty(notification.updateDate) && notification.updateDate != '0000-00-00 00:00:00'}">
                                                                    <fmt:formatDate pattern = "dd MMM, YYYY HH:MM" value = "${notification.updateDate}"/>
                                                                    <div class="progress mt-1" style="height:4px;">
                                                                       <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 40%;" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                                                                    </div>
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            ${notification.comments}
                                                            <div class="progress mt-1" style="height:4px;">
                                                                <div class="progress-bar bg-warning rounded" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="pills-notification" role="tabpanel" aria-labelledby="pills-notification-tab">
                                <div class="table-responsive">
                                    <div class="customer-scroll2" style="height:362px;position:relative;">
                                        <table class="table table-hover m-b-0">
                                            <thead>
                                               <tr>
                                                    <th>#</th>
                                                    <th>Maker Id</th>
                                                    <th>Checker Id</th>
                                                    <th><span>Insert Date <a class="help" data-toggle="popover" title="Creation date" data-content="Record Creation Date(Created By Maker)" data-original-title="Popover title" aria-describedby="popover706420"><i class="feather icon-help-circle f-16"></i></a></span></th>
                                                    <th>Status</th>
                                                    <th>message</th>
                                                    <c:if test="${notification.recordId != '0'}">
                                                        <th>Action</th>
                                                    </c:if>
                                               </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${sessionScope.loginDTO.notificationList}" var="notification" varStatus="index">
                                                    <tr>
                                                       <td>${index.count}</td>
                                                       <td>
                                                           ${notification.creatorId}
                                                           <div class="progress mt-1" style="height:4px;">
                                                                <div class="progress-bar bg-danger rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                       </td>
                                                        <td>
                                                            ${notification.checkerId}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-primary rounded" role="progressbar" style="width: 50%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <fmt:formatDate pattern = "dd MMM, YYYY HH:MM" value = "${notification.insertDate}"/>
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-warning rounded" role="progressbar" style="width: 70%;" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <c:choose>
                                                            <c:when test="${notification.status == '0' && notification.recordId != '0'}">
                                                                <td>
                                                                    Pending For Approval
                                                                    <div class="progress mt-1" style="height:4px;">
                                                                        <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                                    </div>
                                                                </td>
                                                               
                                                            </c:when>
                                                            <c:when test="${notification.status == '1' && notification.recordId != '0'}">
                                                                <td>
                                                                    Approved
                                                                    <div class="progress mt-1" style="height:4px;">
                                                                       <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                                    </div>
                                                                </td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td>
                                                                    Rejected
                                                                    <div class="progress mt-1" style="height:4px;">
                                                                       <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                                    </div>
                                                                </td>
                                                            </c:otherwise>
                                                        </c:choose> 
                                                        <td>
                                                            ${notification.message}
                                                            <div class="progress mt-1" style="height:4px;">
                                                               <div class="progress-bar bg-success rounded" role="progressbar" style="width: 60%;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                        <c:if test="${notification.recordId  != '0'}">
                                                            <a href="javascript:approveUserNotification('${notification.recordId}','${notification.tableName}', '${notification.menuName}', '${notification.id}', '${notification.creatorId}')" title="Approve
                                                            "><i class="fa fa-check FontSize24"></i></a>
                                                            <a href="javascript:approveUserNotification('${notification.recordId}','${notification.tableName}', '${notification.menuName}', '${notification.id}','${notification.creatorId}')" title="Reject
                                                            "><i class="fa fa-ban FontSize24"></i></a>
                                                            <a href="javascript:trashNotification('remove', '${notification.id}','${notification.tableName}', '${notification.menuName}', '${notification.recordId}','${notification.creatorId}')" title="Trash
                                                            "><i class="feather icon-trash-2 FontSize24"></i></a>
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
        </div> --%>
        <%-- Customer overview end --%>
        <%-- [ Main Content ] end --%>
<div id="UserRoleDetailTable" class="DispNone"></div>
<div id="userProfileDetailTable" class="DispNone"></div>
<div id="notificationDetailTable"></div>

</div>
<%@include file="jspincludes/footer.jsp" %> 
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer onload="loadPageScript()"></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/userNotification.js" defer onload="loadPageScript()"></script>
<script type="text/javascript">
    var recordId = "${userNotification.recordId}"; var tableName = "${userNotification.tableName}";var id = "${userNotification.id}"; var menuName = "${userNotification.menuName}"; var creatorId = "${userNotification.creatorId}";
</script>
</body>
</html>
