<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="/WEB-INF/tools.tld" prefix="r"%>
<head>
    <title>Recon Dashboard</title>
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
	  <%-- <c:if test="${sessionScope.loginDTO.reconTheme.layout == 'dark'}">
        <link rel="stylesheet" class="layout-css" href="${applicationScope['cssBaseUrl']}/gradient/css/layout-dark.css">
      </c:if> --%>
</head>
<%-- <c:set var="boxLayout" value=""/>
<c:if test="${sessionScope.loginDTO.reconTheme.boxLayout == 'Y'}">
    <c:set var="boxLayout" value="box-layout container"/>
</c:if> --%>
<body class="${boxLayout}">
	<%-- [ Pre-loader ] start --%>
	<div class="loader-bg">
		<div class="loader-track">
			<div class="loader-fill"></div>
		</div>
	</div>
	<%-- [ Pre-loader ] End --%>
	<%-- [ navigation menu ] start --%>
	<%@include file="../jspincludes/menu.jsp" %> 
	<%-- [ navigation menu ] end --%>
	<%-- [ Header ] start --%>
	<%@include file="../jspincludes/header.jsp" %> 
	<%-- [ Header ] end --%>

<%-- [ Main Content ] start --%>
<div class="pcoded-main-container">
        <%-- [ Main Content ] start --%>
        <div class="pcoded-content">
             <!-- [ breadcrumb ] start -->
            <div class="page-header ${breadcumbSticky}">
                <div class="page-block">
                    <div class="row align-items-center">
                        <div class="col-md-12">
                            <div class="page-header-title">
                                <h5 class="m-b-10">Event Handler</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/eventHandler/view">Event Handler</a></li>
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
                            <h5>Event Handler</h5>
                            <hr>
                        </div>
                     <div class="card-body">
                           <c:set value="${requestScope['javax.servlet.forward.request_uri']}" var="uri"/>
                           <c:set value="${sessionScope.loginDTO.loginId}" var="loginId"/>
                           <c:set value="${r:pageRoleObj(loginId, uri)}" var="pageRole"/>
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
                                <c:set value="${applicationScope['baseUrl']}/eventHandler/add" var="actionUrl"/>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/eventHandler/edit" var="actionUrl"/>
                            </c:if>
                            <form id="eventHandler-form" action="${actionUrl}" method="post">
								<div class="col-md-12">
	                                <div class="form-group">
	                                    <label class="form-label">Event Handler Name</label>
                                        <select class="form-control js-example-basic-single" name="handlerName" id="handlerName">
                                            <option value="">--Select Event Handler Name--</option>
                                            <c:forEach items="${eventHandlerTypeList}" var="eventHandlerType" varStatus="index">
                                                  <option value="${eventHandlerType}" <c:if test="${eventHandlerType == eventHandler.handlerName}">selected</c:if>>${eventHandlerType}</option>
                                            </c:forEach>
                                        </select>
	                                </div>
                                </div>
								<div class="col-md-12">
									<div class="form-group">
										<label class="form-label">Description</label>
										<textarea name="description" id="description" class="width">${eventHandler.description}</textarea>
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
								<input type="hidden" name="id" value="<c:if test='${!empty(eventHandler.id)}'>${eventHandler.id}</c:if><c:if test='${empty(eventHandler.id)}'>0</c:if>">
								<c:if test="${pageRole.isAdd == 1}">
									<div class="col-md-12">
										<button type="submit" class="btn  btn-primary">
											<c:if test="${action != 'update'}">Submit</c:if>
											<c:if test="${action == 'update'}">Update</c:if>
										</button>
										<button class="btn btn-danger" type="reset">Reset</button>
									</div>
								</c:if>
							</form>
                         </div>
                     </div>
                  <c:if test="${!empty(eventHandlerList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>Event Handler</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Event Handler Name</th>
                                            <th>Description</th>
                                            <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                               <th>Action</th>
                                            </c:if>
                                        </tr>
                                     </thead>
                                  <tbody>
                                        <c:forEach items="${eventHandlerList}" var="eventHandler" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${eventHandler.handlerName}</td>
                                                <td>${eventHandler.description}</td>
                                                <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                	<td>
	                                                     <c:if test="${pageRole.isUpdate == 1}">
	                                                     	<a href="javascript:updateEventHandler('${eventHandler.id}')"><i class="feather icon-edit"></i></a>
	                                                     </c:if>
	                                                     <c:if test="${pageRole.isDelete == 1}">
	                                                     	<a href="javascript:deleteEventHandler('${eventHandler.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
	                                                     </c:if>
                                                 	</td>
                                                </c:if>
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
        </div>
        <%-- [ Main Content ] end --%>
</div>
<%@include file="../jspincludes/footer.jsp" %> 
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/event/eventHandler.js" defer onload="loadPageScript()"></script>
</body>
</html>