<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="eventParameterDetailModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="eventParameterDetailModalTitle" style="display: block; padding-right: 15px;" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="eventParameterDetailModalTitle"></h5>
 			</div>
			<div class="modal-body">
				<div class="dt-responsive table-responsive">
	                    <table id="multi-colum-dt-detail" class="table table-striped table-bordered nowrap">
	                        <thead>
	                            <tr>
	                                <th>#</th>
	                                <th class="text-center ">Entity</th>
									<th class="text-center">Entity Parameter</th>
									<th class="text-center">Variable Name</th>
									<th class="text-center">Description</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                             <c:forEach items="${eventParameterDetailList}" var="eventParameterDetail" varStatus="index">
	                                <tr>
	                                    <td class="text-center">${index.count}</td>
	                                    <td class="text-center">${eventParameterDetail.entityName}</td>
	                                    <td class="text-center">${eventParameterDetail.entityParameterName}</td>
	                                    <td class="text-center">${eventParameterDetail.variableName}</td>
	                                    <td class="text-center">${eventParameterDetail.description}</td>
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
