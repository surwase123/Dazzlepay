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
                                <h5 class="m-b-10">Add Group</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/group/view">Add Group</a></li>
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
                            <h5>Add Group</h5>
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
                            <c:if test="${action != 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/group/add" var="actionUrl"/>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/group/edit" var="actionUrl"/>
                            </c:if>
                            <form id="group-form" action="${actionUrl}" method="post">
                                <c:if test="${empty(sessionScope.loginDTO.systemId)}">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">System</label>
                                            <select class="form-control" name="systemId" id="systemId" onchange="fillProcessorDropDown('systemId', 'processorId')">
                                                <option value="">--Select System--</option>
                                                <c:forEach items="${systemList}" var="system" varStatus="index">
                                                      <option value="${system.systemId}" <c:if test="${group.systemId == system.systemId}">selected</c:if>>${system.systemName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${!empty(sessionScope.loginDTO.systemId)}">
                                    <input type="hidden" name="systemId" id="systemId" value="<c:if test='${empty(group.systemId)}'>${sessionScope.loginDTO.systemId}</c:if><c:if test='${!empty(group.systemId)}'>${group.systemId}</c:if>" class="form-control capital-letter" readonly>
                                </c:if>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Group Id</label>
                                            <input type="text" class="form-control capital-letter" name="groupId" id="groupId" placeholder="Group Id" value="${group.groupId}" <c:if test="${action == 'update'}">readonly</c:if>>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Group Name</label>
                                            <input type="text" class="form-control" name="groupName" id="groupName" placeholder="Group Name" value="${group.groupName}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Group Type</label>
                                            <select class="form-control" name="groupType" id="groupType">
                                                <option value="">--Select Group Type--</option>
                                                <c:forEach items="${groupTypeList}" var="groupType" varStatus="index">
                                                      <option value="${groupType.groupType}" <c:if test="${group.groupType == groupType.groupType}">selected</c:if>>${groupType.displayName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Min. Password Length</label>
                                            <input type="text" class="form-control required-one" name="minPassLength" id="minPassLength" placeholder="Min. Password Length" value="${group.minPassLength}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Max. Password Length</label>
                                            <input type="text" class="form-control required-one" name="maxPassLength" id="maxPassLength" placeholder="Max. Password Length" value="${group.maxPassLength}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <label class="form-label">Password Characteristics</label>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox custom-control-inline">
                                                    <input type="checkbox" id="isAlphaPassword" name="passwordCharacteristics" class="custom-control-input passwordCharacteristics" value="A-Y" <c:if test="${group.isAlphaPassword == 'Y'}">checked</c:if>>
                                                    <label class="custom-control-label" for="isAlphaPassword">Alpha</label>
                                            </div>
                                            <div class="custom-control custom-checkbox custom-control-inline">
                                                    <input type="checkbox" id="isNumberPassword" name="passwordCharacteristics" class="custom-control-input passwordCharacteristics" value="N-Y" <c:if test="${group.isNumberPassword == 'Y'}">checked</c:if>> 
                                                    <label class="custom-control-label" for="isNumberPassword">Numeric</label>
                                            </div>
                                            <div class="custom-control custom-checkbox custom-control-inline">
                                                    <input type="checkbox" id="isSpecialSymbolPass" name="passwordCharacteristics" class="custom-control-input passwordCharacteristics" value="S-Y" <c:if test="${group.isSpecialSymbolPass == 'Y'}">checked</c:if>>
                                                    <label class="custom-control-label" for="isSpecialSymbolPass">Special Character</label>
                                            </div>
                                        </div>
                                    </div>
                                   
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Password Expiry Days</label>
                                            <input type="text" class="form-control" name="passExpiryDays" id="passExpiryDays" placeholder="Password Expiry Days" value="${group.passExpiryDays}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Password History Check</label>
                                            <input type="text" class="form-control" name="passHistoryChecks" id="passHistoryChecks" placeholder="Password History Check" value="${group.passHistoryChecks}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Max. Concurrent Logins</label>
                                            <input type="text" class="form-control" name="maxConcurrentLogin" id="maxConcurrentLogin" placeholder="Max. Concurrent Logins" value="${group.maxConcurrentLogin}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Max. Login Retries</label>
                                            <input type="text" class="form-control" name="maxLoginRetries" id="maxLoginRetries" placeholder="Max. Login Retries" value="${group.maxLoginRetries}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <label class="form-label">Status</label>
                                        <div class="form-group">
                                            <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="active" name="status" class="custom-control-input" value="1" <c:if test="${group.status != '0'}">checked</c:if>>
                                                    <label class="custom-control-label" for="active">Active</label>
                                            </div>
                                            <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="inActive" name="status" class="custom-control-input" value="0" <c:if test="${group.status == '0'}">checked</c:if>>
                                                    <label class="custom-control-label" for="inActive">InActive</label>
                                            </div>
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
                                    <input type="hidden" name="id" value="<c:if test='${!empty(group.id)}'>${group.id}</c:if><c:if test='${empty(group.id)}'>0</c:if>">
                                    <c:if test="${pageRole.isAdd == 1}">
                                        <div class="col-md-12">
                                           <button type="submit" class="btn  btn-primary"><c:if test="${action != 'update'}">Submit</c:if><c:if test="${action == 'update'}">Update</c:if></button>
                                           <button class="btn btn-danger" type="reset">Reset</button>
                                        </div>
                                    </c:if>
                            </form>
                        </div>
                    </div>
                   <c:if test="${!empty(groupList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>Group</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Group Id</th>
                                            <th>Group Name</th>
                                            <th>Group Type</th>
                                            <th>Min Password Length</th>
                                            <th>Max Password Length</th>
                                            <th>Password Characteristics</th>
                                            <th>Password Expiry Days</th>
                                            <th>Password History Check</th>
                                            <th>Max Concurrent Logins</th>
                                            <th>Max Login Retries</th>
                                            <th>Status</th>
                                            <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                               <th>Action</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${groupList}" var="group" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${group.groupId}</td>
                                                <td>${group.groupName}</td>
                                                <td>${group.groupType}</td>
                                                <td class="center">${group.minPassLength}</td>
                                                <td class="center">${group.maxPassLength}</td>
                                                <td>
                                                    <c:if test="${group.isAlphaPassword == 'Y'}">Alpha <br/></c:if>
                                                    <c:if test="${group.isNumberPassword == 'Y'}">Numeric <br/></c:if>
                                                    <c:if test="${group.isSpecialSymbolPass == 'Y'}">Special Characters</c:if>
                                                </td>
                                                <td class="center">${group.passExpiryDays}</td>
                                                <td class="center">${group.passHistoryChecks}</td>
                                                <td class="center">${group.maxConcurrentLogin}</td>
                                                <td class="center">${group.maxLoginRetries}</td>
                                                <td><c:if test="${group.status == '0'}">In Active</c:if> <c:if test="${group.status != '0'}">Active</c:if></td>
                                                <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                    <td>
                                                        <c:if test="${pageRole.isUpdate == 1}">
                                                            <a href="javascript:updateGroup('${group.groupId}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-edit"></i></a>
                                                        </c:if>
                                                        <c:if test="${group.isEditable == 1 && pageRole.isDelete == 1}">
                                                             <a href="javascript:deleteGroup('${group.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
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
</div>
<%@include file="jspincludes/footer.jsp" %> 
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/group.js" defer onload="loadPageScript()"></script>
</body>
</html>
