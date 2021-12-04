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
                                <h5 class="m-b-10">System</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/system/view">System</a></li>
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
                            <h5>System</h5>
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
                                <c:set value="${applicationScope['baseUrl']}/system/add" var="actionUrl"/>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/system/edit" var="actionUrl"/>
                            </c:if>
                            <form id="system-form" action="${actionUrl}" method="post">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">System Id</label>
                                            <input type="text" class="form-control capital-letter" name="systemId" id="systemId" placeholder="System Id" value="${system.systemId}" <c:if test="${action == 'update'}">readonly</c:if>>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">System Name</label>
                                            <input type="text" class="form-control" name="systemName" id="systemName" placeholder="System Name" value="${system.systemName}">
                                        </div>
                                    </div>
                                    <h5 class="mt-5">Address Details</h5>
                                    <hr>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Address 1</label>
                                            <input type="text" class="form-control" name="addressLine1" id="addressLine1" placeholder="Address 1" value="${system.addressLine1}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Address 2</label>
                                            <input type="text" class="form-control" name="addressLine2" id="addressLine2" placeholder="Address 2" value="${system.addressLine2}">
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label class="form-label">City</label>
                                            <input type="text" class="form-control" name="city" id="city" placeholder="City" value="${system.city}">
                                        </div>
                                        <div class="form-group col-md-3">
                                            <label class="form-label">State</label>
                                            <input type="text" class="form-control" name="state" id="state" placeholder="State" value="${system.state}">
                                        </div>
                                        <div class="form-group col-md-3">
                                            <label class="form-label">Postal Code</label>
                                            <input type="text" class="form-control" name="postalCode" id="postalCode" placeholder="Postal Code" value="${system.postalCode}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Country</label>
                                            <select class="form-control" name="country" id="country">
                                                <option value="">--Select Country--</option>
                                                <c:forEach items="${countryList}" var="country" varStatus="index">
                                                      <option value="${country.countryName}" <c:if test="${country.countryName == system.country}">selected</c:if>>${country.countryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <h5 class="mt-5">Contact Details</h5>
                                    <hr>
                                    <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="firstName">First Name</label>
                                                <input type="text" class="form-control" id="firstName" name="firstName" value="${system.firstName}" placeholder="First Name"/> 
                                            </div>
                                            <div class="form-group col-md-2">
                                                <label for="middleName">Middle Name</label>
                                                <input type="text" class="form-control" id="middleName" name="middleName" value="${system.middleName}" placeholder="Middle Name"/> 
                                            </div>
                                            <div class="form-group col-md-4">
                                                <label for="lastName">Last Name</label>
                                                <input type="text" class="form-control" id="lastName" name="lastName" value="${system.lastName}" placeholder="Last Name"/>
                                            </div>
                                    </div>
                                    <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="emailId">Email Id</label>
                                                <input type="email" class="form-control" id="emailId" name="emailId" value="${system.emailId}" placeholder="Email Id">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="phoneNumber">Phone Number</label>
                                                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${system.phoneNumber}" placeholder="Phone Number">
                                            </div>
                                    </div>
                                    <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="mobileNumber">Mobile Number</label>
                                                <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" value="${system.mobileNumber}" placeholder="Mobile Number">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label for="faxNo">Fax Number</label>
                                                <input type="text" class="form-control" id="faxNo" name="faxNo" value="${system.faxNo}" placeholder="Fax Number">
                                            </div>
                                    </div>

                                    <h5 class="mt-5">Status</h5>
                                    <hr/>
                                    <div class="col-md-12">
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" id="active" name="status" class="custom-control-input" value="1" <c:if test="${system.status != '0'}">checked</c:if>>
                                            <label class="custom-control-label" for="active">Active</label>
                                        </div>
                                        <div class="custom-control custom-radio custom-control-inline">
                                            <input type="radio" id="inActive" name="status" class="custom-control-input" value="0" <c:if test="${system.status == '0'}">checked</c:if>>
                                            <label class="custom-control-label" for="inActive">InActive</label>
                                        </div>
                                    </div>
                                    <hr/>
                                    <c:if test="${pageRole.subMenuId == 0}">
                                        <input type="hidden" name="menuName" value="${pageRole.menuName}">
                                    </c:if>
                                     <c:if test="${pageRole.subMenuId != 0}">
                                        <input type="hidden" name="menuName" value="${pageRole.subMenuName}">
                                    </c:if>
                                    <input type="hidden" name="createdBy" value="${sessionScope.loginDTO.loginId}">
                                    <input type="hidden" name="updatedBy" value="${sessionScope.loginDTO.loginId}">
                                    <input type="hidden" name="id" value="<c:if test='${!empty(system.id)}'>${system.id}</c:if><c:if test='${empty(system.id)}'>0</c:if>">
                                    <c:if test="${pageRole.isAdd == 1}">
                                        <div class="col-md-12">
                                           <button type="submit" class="btn btn-primary"><c:if test="${action != 'update'}">Submit</c:if><c:if test="${action == 'update'}">Update</c:if></button>
                                           <button class="btn btn-danger" type="reset">Reset</button>
                                        </div>
                                    </c:if>
                            </form>
                        </div>
                    </div>
                   <c:if test="${!empty(systemList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>System</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>System Id</th>
                                            <th>System Name</th>
                                            <th>Address</th>
                                            <th>City</th>
                                            <th>State</th>
                                            <th>Postal Code</th>
                                            <th>Country</th>
                                            <th>Contact Name</th>
                                            <th>Email Id</th>
                                            <th>Phone Number</th>
                                            <th>Mobile Number</th>
                                            <th>Fax Number</th>
                                            <th>Status</th>
                                            <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                               <th>Action</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${systemList}" var="system" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${system.systemId}</td>
                                                <td>${system.systemName}</td>
                                                <td>${system.addressLine1} ${system.addressLine2}</td>
                                                <td>${system.city}</td>
                                                <td>${system.state}</td>
                                                <td>${system.postalCode}</td>
                                                <td>${system.country}</td>
                                                <td>${system.firstName} ${system.middleName} ${system.lastName}</td>
                                                <td>${system.emailId}</td>
                                                <td>${system.phoneNumber}</td>
                                                <td>${system.mobileNumber}</td>
                                                <td>${system.faxNo}</td>
                                                <c:choose>
													<c:when test="${system.status == '1'}">
														<td>Active</td>
													</c:when>
													<c:otherwise>
														<td >In Active</td>
													</c:otherwise>
												</c:choose>	
                                                <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                    <td>
                                                        <c:if test="${pageRole.isUpdate == 1}">
                                                           <a href="javascript:updateSystem('${system.systemId}')"><i class="feather icon-edit"></i></a>
                                                        </c:if>
                                                        <c:if test="${pageRole.isDelete == 1}">
                                                           <a href="javascript:deleteSystem('${system.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
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
<script src="${applicationScope['jsBaseUrl']}/javascript/system.js" defer onload="loadPageScript()"></script>
</body>
</html>
