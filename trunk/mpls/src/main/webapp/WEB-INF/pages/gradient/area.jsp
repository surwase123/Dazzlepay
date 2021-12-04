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
                                <h5 class="m-b-10">Area</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}/area/view">Area</a></li>
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
                            <h5>Area</h5>
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
                                <c:set value="${applicationScope['baseUrl']}/area/add" var="actionUrl"/>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/area/edit" var="actionUrl"/>
                            </c:if>
                            <form id="area-form" action="${actionUrl}" method="post">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Country</label>
                                            <select class="form-control js-example-basic-single" name="countryId" id="countryId" onchange="fillState('countryId', 'stateId')">
                                                <option value="">--Select Country--</option>
                                                <c:forEach items="${countryList}" var="country" varStatus="index">
                                                      <option value="${country.id}"<c:if test="${area.countryId == country.id}">selected</c:if>>${country.countryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">State</label>
                                            <select class="form-control js-example-basic-single" name="stateId" id="stateId" onchange="fillCity('stateId','cityId')">
                                                <option value="">--Select State--</option>
                                                <c:forEach items="${stateList}" var="state" varStatus="index">
                                                      <option value="${state.id}"<c:if test="${area.stateId == state.id}">selected</c:if>>${state.stateName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">City</label>
                                            <select class="form-control js-example-basic-single" name="cityId" id="cityId">
                                                <option value="">--Select City--</option>
                                                <c:forEach items="${cityList}" var="city" varStatus="index">
                                                      <option value="${city.id}"<c:if test="${area.cityId == city.id}">selected</c:if>>${city.cityName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Area Code</label>
                                            <input type="text" class="form-control capital-letter" name="areaCode" id="areaCode" placeholder="Area Code" value="${area.areaCode}" <c:if test="${action == 'update'}">readonly</c:if>>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Area Name</label>
                                            <input type="text" class="form-control" name="areaName" id="areaName" placeholder="Area Name" value="${area.areaName}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Pin Code</label>
                                            <input type="text" class="form-control" name="pincode" id="pincode" placeholder="Pincode" value="${area.pincode}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Latitude</label>
                                            <input type="text" class="form-control" name="latitude" id="latitude" placeholder="Latitude" value="${area.latitude}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Longitude</label>
                                            <input type="text" class="form-control" name="longitude" id="longitude" placeholder="Longitude" value="${area.longitude}">
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
                                    <input type="hidden" name="id" value="<c:if test='${!empty(area.id)}'>${area.id}</c:if><c:if test='${empty(area.id)}'>0</c:if>">
                                    <c:if test="${pageRole.isAdd == 1}">
                                        <div class="col-md-12">
                                           <button type="submit" class="btn  btn-primary"><c:if test="${action != 'update'}">Submit</c:if><c:if test="${action == 'update'}">Update</c:if></button>
                                           <button class="btn btn-danger" type="reset">Reset</button>
                                        </div>
                                    </c:if>
                            </form>
                        </div>
                    </div>
                   <c:if test="${!empty(areaList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>Area</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Country</th>
                                            <th>State</th>
                                            <th>City</th>
                                            <th>Area Code</th>
                                            <th>Area Name</th>
                                            <th>Pincode</th>
                                            <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                               <th>Action</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach items="${areaList}" var="area" varStatus="index">
                                            <tr>
                                                <td>${index.count}</td>
                                                <td>${area.countryName}</td>
                                                <td>${area.stateName}</td>
                                                <td>${area.cityName}</td>
                                                <td>${area.areaCode}</td>
                                                <td>${area.areaName}</td>
                                                <td>${area.pincode}</td>
                                                <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                    <td>
                                                        <c:if test="${pageRole.isUpdate == 1}">
                                                            <a href="javascript:updateArea('${area.id}')"><i class="feather icon-edit"></i></a>
                                                        </c:if>
                                                        <c:if test="${pageRole.isDelete == 1}">
                                                            <a href="javascript:deleteArea('${area.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
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
<script src="${applicationScope['jsBaseUrl']}/javascript/area.js" defer onload="loadPageScript()"></script>
</body>
</html>
