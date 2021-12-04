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
    <link rel="stylesheet" href="${applicationScope['cssBaseUrl']}/gradient/css/plugins/editor.css">
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
                                <h5 class="m-b-10">Communication Template</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/communicationTemplate/view">Communication Template</a></li>
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
                            <h5>Communication Template</h5>
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
                                <c:set value="${applicationScope['baseUrl']}/communicationTemplate/add" var="actionUrl"/>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/communicationTemplate/edit" var="actionUrl"/>
                            </c:if>
                            <form id="commTemplate-form" action="${actionUrl}" method="post" onsubmit="return validateCommunicationTemplate()">
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label class="form-label">Language</label>
                                            <select class="form-control js-example-basic-single" name="commLanguageId" id="commLanguageId">
                                                <option value="">--Language--</option>
                                                <c:forEach items="${commLanguageList}" var="commLanguage" varStatus="index">
                                                      <option value="${commLanguage.id}" <c:if test="${commLanguage.id == commTemplate.commLanguageId}">selected</c:if>>${commLanguage.languageName}</option>
                                                </c:forEach>
                                            </select> 
                                        </div>
                                        <div class="form-group col-md-6">
                                           <label class="form-label">Medium</label>
                                            <select class="form-control js-example-basic-single" name="commMediumId" id="commMediumId">
                                                <option value="">--Medium--</option>
                                                <c:forEach items="${commMediumList}" var="commMedium" varStatus="index">
                                                      <option value="${commMedium.id}" mediumName="${commMedium.mediumName}" <c:if test="${commMedium.id == commTemplate.commMediumId}">selected</c:if>>${commMedium.mediumName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Event</label>
                                            <select class="form-control js-example-basic-single" name="eventId" id="eventId" onchange="getEventParameters('eventId', 'eventParameters')">
                                                <option value="">--Select Event--</option>
                                                <c:forEach items="${eventList}" var="event" varStatus="index">
                                                      <option value="${event.id}"  <c:if test="${event.id == commTemplate.eventId}">selected</c:if>>${event.sourceName} - ${event.eventName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Template Name</label>
                                            <input type="text" name="templateName" id="templateName" value="${commTemplate.templateName}" class="form-control"/>
                                        </div>
                                    </div>
	                                <div class="col-md-12">
				                        <div class="form-group">
				                            <label class="form-label">Description</label>
				                            <textarea name="description" id="description" class="width">${commTemplate.description}</textarea>
				                        </div>
                    				</div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Receipient Groups</label>
                                            <select class="form-control js-example-basic-multiple" name="receipientGroups" id="receipientGroups" multiple="multiple">
                                                <option value="">--Select Receipient Groups--</option>
                                            </select>
                                        </div>
                                    </div>
                                    <%-- <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label class="form-label">Entity Type</label>
                                            <select class="form-control js-example-basic-single" name="entityType" id="entityType" onchange="fillEntityType('entityType', 'entityTypeId')">
                                                <option value="">--Select Entity Type--</option>
                                                <c:forEach items="${entityTypes}" var="entityType" varStatus="index">
                                                      <option value="${entityType}" <c:if test="${entityType == commTemplate.entityType}">selected</c:if>>${entityType}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label class="form-label">Entity Type Id</label>
                                            <select class="form-control js-example-basic-single" name="entityTypeId" id="entityTypeId">
                                                <option value="">--Select Entity Id--</option>
                                            </select>
                                        </div>
                                    </div> --%>
                                    
                                    <div class="form-row">
                                        <textarea name="msgTemplate" id="msgTemplate" class="width DispNone">${commTemplate.msgTemplate}</textarea>
                                        <div class="form-group col-md-10">
                                            <label class="form-label">Message Template</label>
                                            <textarea name="communicationMsgTemplate" id="communicationMsgTemplate" class="width">${commTemplate.msgTemplate}</textarea>
                                        </div>
                                        <div class="form-group col-md-2 draggable">
                                            <label class="form-label">Entity Parameter</label>
                                            <select class="form-control js-example-basic-single" name="eventParameters" id="eventParameters">
                                                <option value="">--Select Entity Parameter--</option>
                                            </select>
                                            <div class="MarginLeft7 entityParameterNoteText">Please Select and Drag the value to the Editor or to Template Name!!</div>
                                        </div>
                                    </div>
                                    <div class="alert templatErrrorMessage alert-danger DispNone" role="alert">
                                    </div>
                                    <c:if test="${pageRole.subMenuId == 0}">
                                        <input type="hidden" name="menuName" value="${pageRole.menuName}">
                                    </c:if>
                                     <c:if test="${pageRole.subMenuId != 0}">
                                        <input type="hidden" name="menuName" value="${pageRole.subMenuName}">
                                    </c:if>
                                    <input type="hidden" name="createdBy" value="${sessionScope.loginDTO.loginId}">
                                    <input type="hidden" name="updatedBy" value="${sessionScope.loginDTO.loginId}">
                                    <input type="hidden" name="id" value="<c:if test='${!empty(commTemplate.id)}'>${commTemplate.id}</c:if><c:if test='${empty(commTemplate.id)}'>0</c:if>">
                                    <c:if test="${pageRole.isAdd == 1}">
                                        <div class="col-md-12">
                                           <button type="submit" class="btn  btn-primary"><c:if test="${action != 'update'}">Submit</c:if><c:if test="${action == 'update'}">Update</c:if></button>
                                           <button class="btn btn-danger" type="reset">Reset</button>
                                        </div>
                                    </c:if>
                            </form>
                        </div>
                    </div>
                   <c:if test="${!empty(commTemplateList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>Communication Template</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Language Name</th>
                                            <th>Medium Name</th>
                                            <th>Event</th>
                                            <th>Template Name</th>
                                            <th>Description</th>
                                            <th>Receipient Groups</th>
                                            <th>Entity</th>
                                            <th>Message Template</th>
                                            <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                               <th>Action</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${commTemplateList}" var="commTemplate" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${commTemplate.languageName}</td>
                                                <td>${commTemplate.mediumName}</td>
                                                <td>${commTemplate.eventName}</td>
                                                <td>${commTemplate.templateName}</td>
                                                <td>${commTemplate.description}</td>
                                                <td>${commTemplate.receipientGroups}</td>
                                                <td>${commTemplate.entityType} - ${commTemplate.entityTypeId}</td>
                                                <td>
                                                    <textarea name="commMsgTemplate${index.count}" id="commMsgTemplate${index.count}" class="width DispNone">${commTemplate.msgTemplate}</textarea>
                                                    <a href="javascript:commMsgTemplate('${commTemplate.id}','${commTemplate.templateName}')">Message Template</a>
                                                </td>
                                                <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                    <td>
                                                        <c:if test="${pageRole.isUpdate == 1}">
                                                            <a href="javascript:updateCommTemplate('${commTemplate.id}')"><i class="feather icon-edit"></i></a>
                                                        </c:if>
                                                        <c:if test="${pageRole.isDelete == 1}">
                                                            <a href="javascript:deleteCommTemplate('${commTemplate.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
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
        <%-- [ Main Content ] end --%>
<div id="communicationTemplateModaldiv" class="DisNone"></div>        
</div>
<%@include file="../jspincludes/footer.jsp" %> 
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/editor.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/communication/communicationTemplate.js" defer onload="loadPageScript()"></script>
<script type="text/javascript">var receipientGroupValue = "${commTemplate.receipientGroups}";var entityTypeId = "${commTemplate.entityTypeId}"; var eventId = "${commTemplate.eventId}";</script>
</body>
</html>
