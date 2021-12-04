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
                                <h5 class="m-b-10">Loyalty Cashback</h5>
                            </div>
                            <ul class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}"><i class="feather icon-home"></i></a></li>
                                <li class="breadcrumb-item"><a href="${applicationScope['baseUrl']}merchant/loyaltyCashback/view">Loyalty Cashback</a></li>
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
                            <h5>Loyalty Cashback</h5>
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
                                <c:set value="${applicationScope['baseUrl']}/merchant/loyaltyCashback/add" var="actionUrl"/>
                            </c:if>
                            <c:if test="${action == 'update'}">
                                <c:set value="${applicationScope['baseUrl']}/merchant/loyaltyCashback/edit" var="actionUrl"/>
                            </c:if>
                            <form id="loyaltyCashback-form" action="${actionUrl}" method="post">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Transaction Cashback Type</label>
                                            <select class="form-control js-example-basic-single" name="transCashbackType" id="transCashbackType">
                                                <option value="">--Select Transaction Cashback--</option>
                                                <c:forEach items="${transCashbackType}" var="transCashback" varStatus="index">
                                                      <option value="${transCashback}"<c:if test="${transCashback == loyaltyCashback.transCashbackType}">selected</c:if>>${transCashback}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Offer Name</label>
                                            <input type="text" class="form-control" name="offerName" id="offerName" placeholder="Offer Name" value="${loyaltyCashback.offerName}">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Offer Code</label>
                                            <input type="text" class="form-control" name="offerCode" id="offerCode" placeholder="Offer Code" value="${loyaltyCashback.offerCode}" <c:if test="${action == 'update'}">readonly</c:if>>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <label class="form-label">Cashback Type</label>
                                        <div class="form-group">
                                            <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="fixed" name="cashbackType" class="custom-control-input" value="fixed" onChange="showCashbackType()" <c:if test="${loyaltyCashback.cashbackType != 'variable'}">checked</c:if>>
                                                    <label class="custom-control-label" for="fixed">Fixed</label>
                                            </div>
                                            <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="variable" name="cashbackType" class="custom-control-input" value="variable" onChange="showCashbackType()" <c:if test="${loyaltyCashback.cashbackType == 'variable'}">checked</c:if>>
                                                    <label class="custom-control-label" for="variable">Variable</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="form-label">Minimum Transaction Value</label>
                                            <input type="text" class="form-control decimal" name="minTransValue" id="minTransValue" placeholder="Minimum Transaction" value="<c:if test='${!empty(loyaltyCashback.minTransValue)}'>${loyaltyCashback.minTransValue}</c:if><c:if test='${empty(loyaltyCashback.minTransValue)}'>0</c:if>">
                                        </div>
                                    </div>
                                    <c:set var="cashbackTypeFixedCSS" value="DispNone"/>
                                    <c:set var="cashbackTypeVariableCSS" value="DispNone"/>
                                    <c:if test="${empty(loyaltyCashback.cashbackType)}">
                                        <c:set var="cashbackTypeFixedCSS" value=""/>
                                        <c:set var="cashbackTypeVariableCSS" value="DispNone"/>
                                    </c:if>
                                    <c:if test="${loyaltyCashback.cashbackType == 'variable'}">
                                        <c:set var="cashbackTypeVariableCSS" value=""/>
                                    </c:if>
                                    <c:if test="${loyaltyCashback.cashbackType == 'fixed'}">
                                          <c:set var="cashbackTypeFixedCSS" value=""/>
                                    </c:if>
                                    <div class="col-md-12 fixed ${cashbackTypeFixedCSS}" >
                                        <div class="form-group">
                                            <label class="form-label">Cashback Amount</label>
                                            <input type="text" class="form-control decimal" name="fixedCashbackAmt" id="fixedCashbackAmt" onblur="validateCashbackAmt('fixedCashbackAmt', 'minTransValue')" placeholder="Cashback Amount" value="<c:if test='${!empty(loyaltyCashback.fixedCashbackAmt)}'>${loyaltyCashback.fixedCashbackAmt}</c:if><c:if test='${empty(loyaltyCashback.fixedCashbackAmt)}'>0</c:if>">
                                        </div>
                                    </div>
                                    <div class="col-md-12 variable ${cashbackTypeVariableCSS}">
                                        <div class="form-group">
                                            <label class="form-label">Cashback Percentage(%)</label>
                                            <input type="text" class="form-control" name="cashbackPercentage" id="cashbackPercentage" placeholder="Cashback Percentage" value="<c:if test='${!empty(loyaltyCashback.cashbackPercentage)}'>${loyaltyCashback.cashbackPercentage}</c:if><c:if test='${empty(loyaltyCashback.cashbackPercentage)}'>0</c:if>">
                                        </div>
                                    </div>
                                    <div class="col-md-12 ${cashbackTypeVariableCSS} variable">
                                        <div class="form-group">
                                            <label class="form-label">Max Cashback Amount</label>
                                            <input type="text" class="form-control decimal" name="maxCashbackAmt" id="maxCashbackAmt" placeholder="Max Cashback Amount" onblur="validateCashbackAmt('maxCashbackAmt', 'minTransValue')" value="<c:if test='${!empty(loyaltyCashback.maxCashbackAmt)}'>${loyaltyCashback.maxCashbackAmt}</c:if><c:if test='${empty(loyaltyCashback.maxCashbackAmt)}'>0</c:if>">
                                        </div>
                                    </div>
                                    <div class="alert alert-danger msgResponse DispNone" role="alert"></div>
                                    <c:if test="${pageRole.subMenuId == 0}">
                                        <input type="hidden" name="menuName" value="${pageRole.menuName}">
                                    </c:if>
                                     <c:if test="${pageRole.subMenuId != 0}">
                                        <input type="hidden" name="menuName" value="${pageRole.subMenuName}">
                                    </c:if>
                                    <input type="hidden" name="mId" value="${sessionScope.loginDTO.merchant.id}">
                                    <input type="hidden" name="createdBy" value="${sessionScope.loginDTO.loginId}">
                                    <input type="hidden" name="updatedBy" value="${sessionScope.loginDTO.loginId}">
                                    <input type="hidden" name="id" value="<c:if test='${!empty(loyaltyCashback.id)}'>${loyaltyCashback.id}</c:if><c:if test='${empty(loyaltyCashback.id)}'>0</c:if>">
                                    <c:if test="${pageRole.isAdd == 1 && !empty(sessionScope.loginDTO.merchant)}">
                                        <div class="col-md-12">
                                           <button type="submit" class="btn  btn-primary"><c:if test="${action != 'update'}">Submit</c:if><c:if test="${action == 'update'}">Update</c:if></button>
                                           <button class="btn btn-danger" type="reset">Reset</button>
                                        </div>
                                    </c:if>
                            </form>
                        </div>
                    </div>
                   <c:if test="${!empty(loyaltyCashbackList)}">
                    <div class="card">
                        <div class="card-header">
                            <h5>Merchant Offers</h5>
                        </div>
                        <div class="card-body">
                            <div class="dt-responsive table-responsive">
                                <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Transaction Cashback Type</th>
                                            <th>Offer Name</th>
                                            <th>Offer Code</th>
                                            <th>Cashback Type</th>
                                            <th>Min. Transaction Value</th>
                                            <th>Fixed CashBack Amount</th>
                                            <th>CashBack Percentage</th>
                                            <th>Max. CashBack Amount</th>
                                            <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                               <th>Action</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                      <c:forEach items="${loyaltyCashbackList}" var="loyaltyCashback" varStatus="index">
                                         <tr>
                                             <td>${index.count}</td>
                                             <td>${loyaltyCashback.transCashbackType}</td>
                                             <td>${loyaltyCashback.offerName}</td>
                                             <td>${loyaltyCashback.offerCode}</td>
                                             <td>${fn:toUpperCase(loyaltyCashback.cashbackType)}</td>
                                             <td>${loyaltyCashback.minTransValue}</td>
                                             <td>${loyaltyCashback.fixedCashbackAmt}</td>
                                             <td>${loyaltyCashback.cashbackPercentage}%</td>
                                             <td>${loyaltyCashback.maxCashbackAmt}</td>
                                             <c:if test="${pageRole.isUpdate == 1 || pageRole.isDelete == 1}">
                                                 <td>
                                                     <c:if test="${pageRole.isUpdate == 1}">
                                                         <a href="javascript:updateLoyaltyCashback('${loyaltyCashback.id}')"><i class="feather icon-edit"></i></a>
                                                     </c:if>
                                                     <c:if test="${pageRole.isDelete == 1}">
                                                         <a href="javascript:deleteLoyaltyCashback('${loyaltyCashback.id}', '${sessionScope.loginDTO.loginId}')"><i class="feather icon-trash-2"></i></a>
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
<script src="${applicationScope['jsBaseUrl']}/javascript/loyaltyCashback.js" defer onload="loadPageScript()"></script>
</body>
</html>
