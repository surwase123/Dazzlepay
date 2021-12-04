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
                                <h5 class="m-b-10">Group Privilege</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/user/role/view">Group Privilege</a></li>
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
                            <h5>Group Privilege</h5>
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
                                <c:set value="${applicationScope['baseUrl']}/user/role/add" var="actionUrl"/>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/user/role/edit" var="actionUrl"/>
                            </c:if>
                            <form id="userRole-form" action="${actionUrl}" method="post">
                            		<div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">User Group</label>
                                            <select class="form-control" name="groupId" id="groupId" onchange="getGroupInfo('groupId','accordionExample')">
                                                <option value="">--Select User Group--</option>
                                                <c:forEach items="${groupList}" var="group" varStatus="index">
                                                      <option value="${group.groupId}"<c:if test="${group.groupId == userRole.groupId}">selected</c:if>>${group.groupName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-sm-12">
                                        <h6 class="mb-3">Screen Name</h6>
                                        <hr>
                                        <div class="accordion" id="accordionExample">
                                            <c:forEach items="${menuList}" var="menu" varStatus="index">
                                                <c:set var="ariaExpanded" value="false"/>
                                                <c:set var="show" value=""/>
                                                <c:if test="${index.count == 1}">
                                                      <c:set var="ariaExpanded" value="true"/>
                                                      <c:set var="show" value="show"/>
                                                </c:if>
                                                <c:if test="${!empty(menu.subMenuList)}">
                                                        <div class="card">
                                                            <div class="card-header" id="headingOne${index.count}">
                                                                <h5 class="mb-0"><a href="#!" data-toggle="collapse" data-target="#collapse${index.count}" aria-expanded="${ariaExpanded}" aria-controls="collapse${index.count}" class="">#${menu.menuName}</a></h5>
                                                            </div>
                                                            <div id="collapse${index.count}" class="card-body collapse ${show}" aria-labelledby="headingOne${index.count}" data-parent="#accordionExample">
                                                                <c:forEach items="${menu.subMenuList}" var="subMenu">
                                                                    <div class="col-md-12">
                                                                        <div class="form-group">
                                                                            <div class="switch switch-info d-inline m-r-10">
                                                                                <c:set var="updateKey" value="subMenu${subMenu.id}"/>
                                                                                <c:set value="${groupPrivilege[updateKey]}" var="userRoleDTO"/>
                                                                                <input type="checkbox" id="subMenu${subMenu.id}" name="groupPrivilegeMenu" onchange="removeDisabled('subMenusdiv${subMenu.id}', 'subMenu${subMenu.id}')" value="subMenu${subMenu.id}" <c:if test="${!empty(userRoleDTO)}">checked</c:if>>
                                                                                <label for="subMenu${subMenu.id}" class="cr"></label>
                                                                            </div>
                                                                            <label>${subMenu.subMenuName}</label>
                                                                            <div class="float-right" id="subMenusdiv${subMenu.id}">
                                                                                <c:if test="${subMenu.isAdd == '1'}">
                                                                                    <div class="checkbox d-inline isAddSubMenu${subMenu.id}">
                                                                                        <input type="checkbox" name="isAddSubMenu${subMenu.id}" id="isAddSubMenu${subMenu.id}" value="1" <c:if test="${userRoleDTO.isAdd == 1}">checked</c:if> <c:if test="${empty(userRoleDTO)}">disabled</c:if>>
                                                                                        <label for="isAddSubMenu${subMenu.id}" class="cr">Add</label>
                                                                                    </div>
                                                                                </c:if>
                                                                               
                                                                                <c:if test="${subMenu.isUpdate == '1'}">
                                                                                    <div class="checkbox d-inline isUpdateSubMenu${subMenu.id}">
                                                                                        <input type="checkbox" name="isUpdateSubMenu${subMenu.id}" id="isUpdateSubMenu${subMenu.id}" value="1" <c:if test="${userRoleDTO.isUpdate == 1}">checked</c:if> <c:if test="${empty(userRoleDTO)}">disabled</c:if>>
                                                                                        <label for="isUpdateSubMenu${subMenu.id}" class="cr">Update</label>
                                                                                    </div>
                                                                                </c:if>

                                                                                <c:if test="${subMenu.isDelete == '1'}">
                                                                                    <div class="checkbox d-inline isDeleteSubMenu${subMenu.id}">
                                                                                        <input type="checkbox" name="isDeleteSubMenu${subMenu.id}" id="isDeleteSubMenu${subMenu.id}" value="1" <c:if test="${userRoleDTO.isDelete == 1}">checked</c:if> <c:if test="${empty(userRoleDTO)}">disabled</c:if>>
                                                                                        <label for="isDeleteSubMenu${subMenu.id}" class="cr">Delete</label>
                                                                                    </div>
                                                                                </c:if>

                                                                                <c:if test="${subMenu.isMaskField == '1'}">
                                                                                    <div class="checkbox d-inline isMaskSubMenu${menu.menuId}">
                                                                                        <input type="checkbox" name="isMaskSubMenu${subMenu.id}" id="isMaskSubMenu${subMenu.id}" value="1" <c:if test="${userRoleDTO.isMaskField == 1}">checked</c:if> <c:if test="${empty(userRoleDTO)}">disabled</c:if>>
                                                                                        <label for="isMaskSubMenu${subMenu.id}" class="cr">Mask Field</label>
                                                                                    </div>
                                                                                </c:if>
                                                                            </div>
                                                                            
                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                </c:if>
                                                <c:if test="${empty(menu.subMenuList)}">
                                                        <div class="card">
                                                            <div class="card-header" id="headingOne${index.count}">
                                                                <h5 class="mb-0"><a href="#!" data-toggle="collapse" data-target="#collapse${index.count}" aria-expanded="${ariaExpanded}" aria-controls="collapse${index.count}" class="">#${menu.menuName}</a></h5>
                                                            </div>
                                                            <div id="collapse${index.count}" class="card-body collapse ${show}" aria-labelledby="headingOne${index.count}" data-parent="#accordionExample">
                                                                <div class="col-md-12">
                                                                    <div class="form-group">
                                                                        <div class="switch switch-info d-inline m-r-10">
                                                                            <c:set var="updateKey" value="menu${menu.menuId}"/>
                                                                            <c:set value="${groupPrivilege[updateKey]}" var="userRoleDTO"/>
                                                                            <input type="checkbox" id="menu${menu.menuId}" name="groupPrivilegeMenu" onchange="removeDisabled('menusdiv${menu.menuId}', 'menu${menu.menuId}')" value="${menu.menuId}" <c:if test="${!empty(userRoleDTO)}">checked</c:if>>
                                                                            <label for="menu${menu.menuId}" class="cr"></label>
                                                                        </div>
                                                                        <label>${menu.menuName}</label>
                                                                       
                                                                        <div class="float-right" id="menusdiv${menu.menuId}">
                                                                            <c:if test="${menu.isAdd == '1'}">
                                                                                <div class="checkbox d-inline isAddMenu${menu.menuId}">
                                                                                    <input type="checkbox" name="isAddMenu${menu.menuId}" id="isAddMenu${menu.menuId}" value="1" <c:if test="${userRoleDTO.isAdd == 1}">checked</c:if> <c:if test="${empty(userRoleDTO)}">disabled</c:if>>
                                                                                    <label for="isAddMenu${menu.menuId}" class="cr">Add</label>
                                                                                </div>
                                                                            </c:if>

                                                                            <c:if test="${menu.isUpdate == '1'}">
                                                                                <div class="checkbox d-inline isUpdateMenu${menu.menuId}">
                                                                                    <input type="checkbox" name="isUpdateMenu${menu.menuId}" id="isUpdateMenu${menu.menuId}" value="1" <c:if test="${userRoleDTO.isUpdate == 1}">checked</c:if> <c:if test="${empty(userRoleDTO)}">disabled</c:if>>
                                                                                    <label for="isUpdateMenu${menu.menuId}" class="cr">Update</label>
                                                                                </div>
                                                                            </c:if>

                                                                            <c:if test="${menu.isDelete == '1'}">
                                                                                <div class="checkbox d-inline isDeleteMenu${menu.menuId}">
                                                                                    <input type="checkbox" name="isDeleteMenu${menu.menuId}" id="isDeleteMenu${menu.menuId}" value="1" <c:if test="${userRoleDTO.isDelete == 1}">checked</c:if> <c:if test="${empty(userRoleDTO)}">disabled</c:if>>
                                                                                    <label for="isDeleteMenu${menu.menuId}" class="cr">Delete</label>
                                                                                </div>
                                                                            </c:if>

                                                                            <c:if test="${menu.isMaskField == '1'}">
                                                                                <div class="checkbox d-inline isMaskMenu${menu.menuId}">
                                                                                    <input type="checkbox" name="isMaskMenu${menu.menuId}" id="isMaskMenu${menu.menuId}" value="1" <c:if test="${userRoleDTO.isMaskField == 1}">checked</c:if> <c:if test="${empty(userRoleDTO)}">disabled</c:if>>
                                                                                    <label for="isMaskMenu${menu.menuId}" class="cr">Mask Field</label>
                                                                                </div>
                                                                            </c:if>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <c:if test="${pageRole.subMenuId == 0}">
                                        <input type="hidden" name="screenMenuName" value="${pageRole.menuName}">
                                    </c:if>
                                     <c:if test="${pageRole.subMenuId != 0}">
                                        <input type="hidden" name="screenMenuName" value="${pageRole.subMenuName}">
                                    </c:if>
                                    <input type="hidden" name="createdBy" value="${sessionScope.loginDTO.loginId}">
                                    <input type="hidden" name="updatedBy" value="${sessionScope.loginDTO.loginId}">
                                    <c:if test="${pageRole.isAdd == 1}">
                                        <div class="col-md-12">
                                           <button type="submit" class="btn btn-primary"><c:if test="${action != 'update'}">Submit</c:if><c:if test="${action == 'update'}">Update</c:if></button>
                                           <button class="btn btn-danger" type="reset">Reset</button>
                                        </div>
                                    </c:if>
                            </form>
                        </div>
                    </div>
                    <c:if test="${!empty(userRoleList)}">
                        <div class="card">
                            <div class="card-header">
                                <h5>User Role</h5>
                            </div>
                            <div class="card-body">
                                <div class="dt-responsive table-responsive">
                                    <div class="dt-responsive table-responsive">
                                    <table id="multi-colum-role-table" class="table table-striped table-bordered nowrap">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Group Name</th>
                                                <th>Privileges</th>
                                                <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                   <th>Action</th>
                                                </c:if>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${userRoleList}" var="userRole" varStatus="index">
                                                <tr>
                                                    <td>${index.count}</td>
                                                    <td><a href="javascript:getGroupDetail('${userRole.groupId}', 'User Group Info')">${userRole.groupName}</a></td>
                                                    <td>
                                                        <a href="javascript:userRoleDeail('${userRole.groupId}', '${userRole.groupName}')">Group Privilege</a>
                                                    </td>
                                                    <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                        <td>
                                                            <c:if test="${pageRole.isUpdate == 1}">
                                                                <a href="javascript:updateUserRole('${userRole.groupId}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-edit"></i></a>
                                                            </c:if>
                                                            <c:if test="${pageRole.isDelete == 1}">
                                                                <a href="javascript:deleteUserRole('${userRole.groupId}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
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
<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/exportReport.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/javascript/userRole.js" defer onload="loadPageScript()"></script>
<script type="text/javascript"> var action = "${action}";</script>
</body>
</html>
