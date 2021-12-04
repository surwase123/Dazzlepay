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
                                <h5 class="m-b-10">Event Parameter</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/eventParameter/view">Event Parameter</a></li>
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
                            <h5>Event Parameter</h5>
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
                            
                            <c:set value="${requestScope['javax.servlet.forward.request_uri']}" var="uri"/>
                            <c:set value="${sessionScope.loginDTO.loginId}" var="loginId"/>
                            <c:set value="${r:pageRoleObj(loginId, uri)}" var="pageRole"/>
                            
                            <c:if test="${action != 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/eventParameter/add" var="actionUrl"/>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/eventParameter/edit" var="actionUrl"/>
                            </c:if>
                            <form id="eventParameter-form" action="${actionUrl}" method="post">
                            	<c:if test="${action != 'update'}">
	                            	<div class="col-md-12">
		                                 <div class="form-group">
		                                    <label class="form-label">Event Source</label>
		                                    <select class="form-control js-example-basic-single" name="eventSourceId" id="eventSourceId" onchange="fillEventList('eventSourceId', 'eventId')">
		                                        <option value="">--Select Event Source--</option>
		                                        <c:forEach items="${eventSourceList}" var="eventSource" varStatus="index">
		                                              <option value="${eventSource.id}" <c:if test="${eventSource.id == eventParameter.eventSourceId}">selected</c:if>>${eventSource.sourceName}</option>
		                                        </c:forEach>
		                                    </select>
		                                 </div>
	                                </div>
	                            </c:if>
	                                <div class="col-md-12">
	                                    <div class="form-group">
	                                        <label class="form-label">Event</label>
	                                        <select class="form-control js-example-basic-single" name="eventId" id="eventId">
	                                            <option value="">--Select Event--</option>
	                                            <c:forEach items="${eventList}" var="event" varStatus="index">
	                                                  <option value="${event.id}"<c:if test="${event.id == eventParameter.eventId}">selected</c:if>>${event.eventName}</option>
	                                            </c:forEach>
	                                        </select>
	                                    </div>
	                                </div>
	                                <div class="col-md-12 column">
									<table class="table table-bordered table-hover table-layout" id="eventParameterTbl">
										<thead>
											<tr>
												<th class="text-center width2">#</th>
												<th class="text-center width20">Entity</th>
												<th class="text-center width20">Entity Parameter</th>
												<th class="text-center width20">Variable Name</th>
												<th class="text-center width38">Description</th>
											</tr>
										</thead>
									    <tbody>
									    	<c:set var="eventParameterDetailList" value="${eventParameterDetailList}"/>
									    	<c:if test="${empty(eventParameterDetailList)}">
										        <input type="hidden" name="rowNumber" id="rowNumber" value="0">
	                                            <tr id='eventParameter0'>
	                                                <td class="width2">1</td>
	                                                <td class="width20">
														<div class="form-group col-md-12">
															<select class="form-control entityId js-example-basic-single" id="entityId0" name="entityId0" onchange="fillEntityParameter('entityId0','entityParameterName0')">
																<option value="">--Entity--</option>
																<c:forEach items="${entityList}" var="entity" varStatus="index">
			                                                      	<option value="${entity.id}">${entity.entityName}</option>
			                                               		</c:forEach>
															</select>
														</div>
												    </td>
													<td class="width20">		
														<div class="col-md-12">
						                                    <div class="form-group">
						                                        <select class="form-control entityParameterName js-example-basic-single" name="entityParameterName0" id="entityParameterName0" onchange="setEventVariableName('entityParameterName0', 'variableName0')">
						                                            <option value="">--Entity Parameter--</option>
						                                            <c:forEach items="${entityParameterList}" var="entityParameter" varStatus="index">
						                                                  <option value="${entityParameter}">${entityParameter}</option>
						                                            </c:forEach>
						                                        </select>
						                                    </div>
	                                					</div>
													</td>
													<td class="width20">		
														<div class="col-md-12">
						                                    <div class="form-group ">
						                                        <input type="text" name="variableName0" id="variableName0" class="form-control variableName" placeholder="Variable Name" readonly="" />
						                                    </div>
                                						</div>
													</td>
													<td class="width38">		
														<div class="form-group col-md-12">
								                            <textarea name="description0" id="description0" class="width description"></textarea>
				                       					</div>
													</td>
												</tr>
												<tr id='eventParameter1'></tr>
											</c:if>
											<c:if test="${!empty(eventParameterDetailList)}">
														<input type="hidden" name="rowNumber" id="rowNumber" value="${fn:length(eventParameterDetailList)}">
			                                            <c:forEach items="${eventParameterDetailList}" var="eventParameter" varStatus="index">
			                                                <c:set var="count" value="${index.count - 1}"/>
			                                                <tr id='eventParameter${count}'>
			                                                    <td class="width2">${count + 1}</td>
			                                                    <td class="width20">
																	<div class="form-group col-md-12">
																		<select class="form-control entityId js-example-basic-single" id="entityId${count}" name="entityId${count}" onchange="fillEntityParameter('entityId${count}','entityParameterName${count}')">
																			<option value="">--Entity--</option>
																			<c:forEach items="${entityList}" var="entity" varStatus="index">
	                                                 							 <option value="${entity.id}"<c:if test="${entity.id == eventParameter.entityId}">selected</c:if>>${entity.entityName}</option>
	                                            							</c:forEach>
																		</select>
																	</div>
															    </td>
																<td class="width20">		
																	<div class="form-group col-md-12">
																		<select class="form-control entityParameterName js-example-basic-single" id="entityParameterName${count}" name="entityParameterName${count}" onchange="setEventVariableName('entityParameterName${count}', 'variableName${count}')">
																			<option value="">--Entity Parameter--</option>
																			<c:forEach items="${eventParameter.entityParameterList}" var="entityParameter" varStatus="index">
	                                                  							<option value="${entityParameter}"<c:if test="${entityParameter == eventParameter.entityParameterName}">selected</c:if>>${entityParameter}</option>
	                                            							</c:forEach>	
																		</select>
																	</div>
																</td>
																<td class="width20">		
																	<div class="form-group variableName">
	                                            					    <input type="text" name="variableName${count}" id="variableName${count}" value="${eventParameter.variableName}" class="form-control" placeholder="Variable Name" readonly="" />
	                                    							</div>
																</td>
																<td class="width38">		
																	<div class="form-group col-md-12">
				                            							<textarea name="description${count}" id="description${count}" class="width description">${(eventParameter.description)}</textarea>
																	</div>
																</td>
													    </tr>
													</c:forEach>
													<tr id='eventParameter${fn:length(eventParameterDetailList)}'></tr>
											    </c:if>
                                            </tbody>
                                        </table>
									    <div class="Bottom100">
                                                <button type="button" id="add_row" class="btn btn-default pull-left">Add Row</button><button type="button" id='delete_row' class="pull-right btn btn-default">Delete Row</a>
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
                                    <div class="alert alert-danger imageERRMsg DispNone" role="alert"></div>
                                    <input type="hidden" name="id" value="<c:if test='${!empty(eventParameter.id)}'>${eventParameter.id}</c:if><c:if test='${empty(eventParameter.id)}'>0</c:if>">
                                    <c:if test="${pageRole.isAdd == 1}">
                                        <div class="col-md-12">
                                           <button type="submit" class="btn  btn-primary"><c:if test="${action != 'update'}">Submit</c:if><c:if test="${action == 'update'}">Update</c:if></button>
                                           <button class="btn btn-danger" type="reset">Reset</button>
                                        </div>
                                    </c:if>
                            </form>
                        </div>
                    </div>
                   <c:if test="${!empty(eventParameterList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>Event Parameter</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Event Source</th>
                                            <th>Event Name</th>
                                            <th>Event Parameter Info</th>
                                            <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                               <th>Action</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${eventParameterList}" var="eventParameter" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${eventParameter.eventSourceName}</td>
                                                <td>${eventParameter.eventName}</td>
                                                <td><a href="javascript:eventParameterDetail('${eventParameter.id}', 'Event Parameters')">Details</a></td>
                                                <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                    <td>
                                                        <c:if test="${pageRole.isUpdate == 1}">
                                                            <a href="javascript:updateEventParameter('${eventParameter.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-edit"></i></a>
                                                        </c:if>
                                                        <c:if test="${pageRole.isDelete == 1}">
                                                            <a href="javascript:deleteEventParameter('${eventParameter.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
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
<div id="eventParameterDetailModaldiv" class="DisNone"></div>
</div>
<%@include file="../jspincludes/footer.jsp" %> 
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/event/eventParameter.js" defer onload="loadPageScript()"></script>
<script type="text/javascript">var fieldLength = "${fn:length(eventParameterDetailList)}";</script>
</body>
</html>
