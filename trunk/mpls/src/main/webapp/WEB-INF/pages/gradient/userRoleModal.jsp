<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="userRoleModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="userRoleModalTitle" style="display: block; padding-right: 15px;" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="userRoleModalTitle">Group Privilege</h5>
 			</div>
			<div class="modal-body">
				<div class="dt-responsive table-responsive" id="userRoleTable">
	                    <table id="multi-colum-dt" class="table table-striped table-bordered nowrap">
	                        <thead>
	                            <tr>
	                                <th>#</th>
	                                <th>Menu Name</th>
	                                <th>SubMenu Name</th>
	                                <th>IsAdd?</th>
	                                <th>IsUpdate?</th>
	                                <th>IsDelete?</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                             <c:forEach items="${userRoleList}" var="userRole" varStatus="index">
	                                <tr>
	                                    <td>${index.count}</td>
	                                    <td>${userRole.menuName}</td>
	                                    <td>${userRole.subMenuName}</td>
	                                    <td class="center">
	                                    	<c:if test="${userRole.isAdd == 1}"><i class="feather icon-check"></i></c:if>
	                                    	<c:if test="${userRole.isAdd == 0}"><i class="feather icon-x-circle"></i></c:if>
	                                    </td>
	                                    <td class="center">
	                                    	<c:if test="${userRole.isUpdate == 1}"><i class="feather icon-check"></i></c:if>
	                                    	<c:if test="${userRole.isUpdate == 0}"><i class="feather icon-x-circle"></i></c:if>
	                                    </td>
	                                    <td class="center">
	                                    	<c:if test="${userRole.isDelete == 1}"><i class="feather icon-check"></i></c:if>
	                                    	<c:if test="${userRole.isDelete == 0}"><i class="feather icon-x-circle"></i></c:if>
	                                    </td>
	                                </tr>
	                            </c:forEach>
	                        </tbody>
	                    </table>
	                </div>
			    </div>
			<div class="modal-footer">
				<button type="button" class="btn  btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>