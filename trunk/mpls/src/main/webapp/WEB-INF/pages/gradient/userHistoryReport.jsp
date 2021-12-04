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
<body class="">
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
                                <h5 class="m-b-10">User History</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/userReport/view">User History</a></li>
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
                            <h5>User History Report</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Login Id</th>
                                            <th>Name</th>
                                            <th>Email Id</th>
                                            <th>Last Session Id</th>
                                            <th>Last Logged On</th>
                                            <th>History</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${userHistoryReportList}" var="userHistoryReport" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${userHistoryReport.loginId}</td>
                                                <td>${userHistoryReport.firstName} ${userHistoryReport.lastName}</td>
                                                <td>${userHistoryReport.emailId}</td>
                                                <td>${userHistoryReport.lastSessionId}</td>
                                                <td><fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${userHistoryReport.lastLoggedOn}"/></td>
                                                <td><a href="javascript:userHistoryDetails('${userHistoryReport.loginId}')">Details</a> </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
        <%-- [ Main Content ] end --%>
        <div id="UserHistoryDetailTable" class="DispNone"></div>
</div>
<%@include file="jspincludes/footer.jsp" %> 
<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/exportReport.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/user.js" defer onload="loadPageScript()"></script>
</body>
</html>
