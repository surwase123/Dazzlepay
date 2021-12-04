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
                                <h5 class="m-b-10">Recon Source</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/reconSource/view">Recon Source</a></li>
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
                            <h5>Recon Source</h5>
                            <hr/>
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
                                <c:set value="${applicationScope['baseUrl']}/reconSource/add" var="actionUrl"/>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/reconSource/edit" var="actionUrl"/>
                            </c:if>
                            <form id="source-form" action="${actionUrl}" method="post">
                                <c:if test="${empty(sessionScope.loginDTO.systemId)}">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">System</label>
                                            <select class="form-control" name="systemId" id="systemId" onchange="fillProcessorDropDown('systemId', 'processorId')">
                                                <option value="">--Select System--</option>
                                                <c:forEach items="${systemList}" var="system" varStatus="index">
                                                      <option value="${system.systemId}" <c:if test="${reconSource.systemId == system.systemId}">selected</c:if>>${system.systemName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${!empty(sessionScope.loginDTO.systemId)}">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="form-label">System</label>
                                                <input type="text" name="systemId" id="systemId" value="<c:if test='${empty(reconSource.systemId)}'>${sessionScope.loginDTO.systemId}</c:if><c:if test='${!empty(reconSource.systemId)}'>${reconSource.systemId}</c:if>" class="form-control capital-letter" readonly>
                                            </div>
                                        </div>
                                </c:if>
                                <c:if test="${empty(sessionScope.loginDTO.processorId)}">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Processor</label>
                                            <select class="form-control" name="processorId" id="processorId" onchange="fillInstitutionDropDown('processorId', 'institutionId')">
                                                <option value="">--Select Processor--</option>
                                                <c:forEach items="${processorList}" var="processor" varStatus="index">
                                                      <option value="${processor.processorId}" <c:if test="${reconSource.processorId == processor.processorId}">selected</c:if>>${processor.processorName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${!empty(sessionScope.loginDTO.processorId)}">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                                <label class="form-label">Processor</label>
                                                <input type="text" name="processorId" id="processorId" value="<c:if test='${empty(reconSource.processorId)}'>${sessionScope.loginDTO.processorId}</c:if><c:if test='${!empty(reconSource.processorId)}'>${reconSource.processorId}</c:if>" class="form-control capital-letter" readonly>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${empty(sessionScope.loginDTO.institutionId)}">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Institution</label>
                                            <select class="form-control" name="institutionId" id="institutionId" onchange="fillReconProcessDropDown('systemId', 'processorId', 'institutionId', 'reconProcessId')">
                                                <option value="">--Select Institution--</option>
                                                <c:forEach items="${institutionList}" var="institution" varStatus="index">
                                                      <option value="${institution.institutionId}"<c:if test="${institution.institutionId == reconSource.institutionId}">selected</c:if>>${institution.institutionName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${!empty(sessionScope.loginDTO.institutionId)}">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Institution</label>
                                            <input type="text" name="institutionId" id="institutionId" value="<c:if test='${empty(reconSource.institutionId)}'>${sessionScope.loginDTO.institutionId}</c:if><c:if test='${!empty(reconSource.institutionId)}'>${reconSource.institutionId}</c:if>" class="form-control capital-letter" readonly>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="form-label">Recon Process</label>
                                        <select class="form-control" name="reconProcessId" id="reconProcessId">
                                            <option value="">--Select Recon Process--</option>
                                            <c:forEach items="${reconProcessList}" var="reconProcess" varStatus="index">
                                                  <option value="${reconProcess.reconProcessId}"<c:if test="${reconProcess.reconProcessId == reconSource.reconProcessId}">selected</c:if>>${reconProcess.processName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="form-label">Recon Source</label>
                                        <select class="form-control" name="sourceName" id="sourceName" onchange="getReconOthersSource('sourceName', 'sourceOtherNameDiv')">
                                            <option value="">--Select Recon Source--</option>
                                            <c:forEach items="${sourceMasterList}" var="reconSourceMaster" varStatus="index">
                                                  <option value="${reconSourceMaster.sourceName}" <c:if test="${reconSourceMaster.sourceName == reconSource.sourceName}">selected</c:if>>${reconSourceMaster.sourceDisplayName}</option>
                                            </c:forEach>
                                            <option value="others">Others</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-12 DispNone sourceOtherNameDiv">
                                        <div class="form-group">
                                            <label class="form-label">Source Name</label>
                                            <input type="text" class="form-control" name="sourceDisplayName" id="sourceDisplayName" placeholder="Source Name" value="${reconSource.sourceDisplayName}">
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
                                <input type="hidden" name="id" value="<c:if test='${!empty(reconSource.id)}'>${reconSource.id}</c:if><c:if test='${empty(reconSource.id)}'>0</c:if>">
                                <c:if test="${pageRole.isAdd == 1 || pageRole.isUpdate == 1}">
                                    <div class="col-md-12">
                                       <button type="submit" class="btn btn-primary"><c:if test="${action != 'update'}">Submit</c:if><c:if test="${action == 'update'}">Update</c:if></button>
                                       <button class="btn btn-danger" type="reset">Reset</button>
                                    </div>
                                </c:if>
                            </form>
                        </div>
                    </div>
                    <c:if test="${!empty(reconSourceList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>Recon Sources</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Institution</th>
                                            <th>Process Name</th>
                                            <th>Source Name</th>
                                            <th>Source Table</th>
                                            <th>Source Detail Table</th>
                                            <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                               <th>Action</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${reconSourceList}" var="reconSources" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${reconSources.institutionId}</td>
                                                <td>${reconSources.processName}</td>
                                                <td>${reconSources.sourceDisplayName}</td>
                                                <td>${reconSources.sourceTableName}</td>
                                                <td>${reconSources.transDetailTableName}</td>
                                                <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                    <td>
                                                        <c:if test="${pageRole.isUpdate == 1}">
                                                            <a href="javascript:updateReconSource('${reconSources.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-edit"></i></a>
                                                        </c:if>
                                                        <c:if test="${pageRole.isDelete == 1}">
                                                            <a href="javascript:deleteReconSource('${reconSources.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
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
        </div>
        <%-- [ Main Content ] end --%>
</div>
<%@include file="jspincludes/footer.jsp" %> 
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/source.js" defer onload="loadPageScript()"></script>
<script type="text/javascript">var action = "${action}"</script>
</body>
</html>
