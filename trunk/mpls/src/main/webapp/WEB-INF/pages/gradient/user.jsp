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
                                <h5 class="m-b-10">User</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/add/user/view">User</a></li>
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
                            <h5>User</h5>
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
                                <c:set value="${applicationScope['baseUrl']}/add/user/add" var="actionUrl"/>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/add/user/edit" var="actionUrl"/>
                            </c:if>
                            <form id="user-form" action="${actionUrl}" method="post">
                                <c:if test="${!empty(sessionScope.loginDTO.systemId)}">
                                         <input type="hidden" name="systemId" id="systemId" value="<c:if test='${empty(user.systemId)}'>${sessionScope.loginDTO.systemId}</c:if><c:if test='${!empty(user.systemId)}'>${user.systemId}</c:if>" class="form-control capital-letter" readonly>
                                </c:if>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Group</label>
                                            <select class="form-control" name="groupId" id="groupId">
                                                <option value="">--Select Group--</option>
                                                <c:forEach items="${groupList}" var="group" varStatus="index">
                                                      <option value="${group.groupId}" <c:if test="${user.groupId == group.groupId}">selected</c:if>>${group.groupName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Login Id</label>
                                            <input type="text" class="form-control capital-letter" name="loginId" id="loginId" placeholder="LoginId Id" value="${user.loginId}" <c:if test="${action == 'update'}">readonly</c:if>>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Domain UserName</label>
                                            <input type="text" class="form-control" name="domainUserName" id="domainUserName" placeholder="Domain User Name" value="${user.domainUserName}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                              <label for="emailId">Email Id</label>
                                              <input type="email" class="form-control" id="emailId" name="emailId" value="${user.emailId}" placeholder="Email Id" value="${user.emailId}">
                                        </div>
                                    </div>
                                    <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="firstName">First Name</label>
                                                <input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}" placeholder="First Name"/> 
                                            </div>
                                            <div class="form-group col-md-2">
                                                <label for="middleName">Middle Name</label>
                                                <input type="text" class="form-control" id="middleName" name="middleName" value="${user.middleName}" placeholder="Middle Name"/> 
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="lastName">Last Name</label>
                                                <input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}" placeholder="Last Name"/>
                                            </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Assigned User</label>
                                            <select class="form-control" name="managerId" id="managerId">
                                                <option value="">--Select Manager--</option>
                                                    <c:forEach items="${selectUserList}" var="selectUser" varStatus="index">
                                                        <c:if test="${selectUser.loginId != user.loginId}">
                                                            <option value="${selectUser.loginId}" <c:if test="${user.managerId == selectUser.loginId}">selected</c:if>>${selectUser.firstName} ${selectUser.lastName}</option>
                                                        </c:if>
                                                    </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <label class="form-label">Status</label>
                                        <div class="form-group">
                                            <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="active" name="status" class="custom-control-input" value="1" <c:if test="${user.status != '0'}">checked</c:if>>
                                                    <label class="custom-control-label" for="active">Active</label>
                                            </div>
                                            <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="inActive" name="status" class="custom-control-input" value="0" <c:if test="${user.status == '0'}">checked</c:if>>
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
                                    <input type="hidden" name="userType" value="1">
                                    <input type="hidden" name="createdBy" value="${sessionScope.loginDTO.loginId}">
                                    <input type="hidden" name="updatedBy" value="${sessionScope.loginDTO.loginId}">
                                    <input type="hidden" name="id" value="<c:if test='${!empty(user.id)}'>${user.id}</c:if><c:if test='${empty(user.id)}'>0</c:if>">
                                    <c:if test="${pageRole.isAdd == 1}">
                                        <div class="col-md-12">
                                           <button type="submit" class="btn  btn-primary"><c:if test="${action != 'update'}">Submit</c:if><c:if test="${action == 'update'}">Update</c:if></button>
                                           <button class="btn btn-danger" type="reset">Reset</button>
                                        </div>
                                    </c:if>
                            </form>
                        </div>
                    </div>
                   <c:if test="${!empty(userList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>User</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt-user" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>LoginId</th>
                                            <th>Email Id</th>
                                            <th>Name</th>
                                            <th>Group Id</th>
                                            <th>Privilege</th>
                                            <th>Status</th>
                                            <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                               <th>Action</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${userList}" var="user" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${user.loginId}</td>
                                                <td>${user.emailId}</td>
                                                <td>${user.firstName} ${user.middleName} ${user.lastName}</td>
                                                <td><a href="javascript:getGroupDetail('${user.groupId}', 'User Group Info')">${user.groupId}</a> </td>
                                                <td><a href="javascript:userRoleDeail('${user.groupId}', 'User Privilege')">User Privilege</a> </td>
                                                <td><c:if test="${group.status == '0'}">In Active</c:if> <c:if test="${group.status != '0'}">Active</c:if></td>
                                                <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                    <td>
                                                        <c:if test="${pageRole.isUpdate == 1}">
                                                             <a href="javascript:updateUser('${user.loginId}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-edit"></i></a>
                                                        </c:if>
                                                        <c:if test="${pageRole.isDelete == 1}">
                                                             <a href="javascript:deleteUser('${user.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
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
<div id="UserRoleDetailTable" class="DispNone"></div>
<div id="UserGroupDetailTable" class="DispNone"></div>
</div>
<%@include file="jspincludes/footer.jsp" %> 
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/user.js" defer onload="loadPageScript()"></script>
</body>
</html>
