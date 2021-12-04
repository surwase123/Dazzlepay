<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="notificationModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="notificationTitle" style="display: block; padding-right: 15px;" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="notificationTitle"></h5>
 			</div>
 			 <c:if test="${is_success == 'success'}">
                                <div id="success_message" class="alert alert-success alertMessage" role="alert">
                                  <strong>Well done!</strong> ${message}
                                </div>
                            </c:if>
                            <c:if test="${is_success == 'failure'}">
                                <div id="error_message" class="alert alert-danger alertMessage" role="alert">
                                  <strong>Oh snap!</strong> ${message}
                                </div>
                            </c:if>
			<div class="modal-body">
			    <div class="dt-responsive table-responsive">
                    <table class="table">
                        <tbody>
                          <c:forEach items="${headerList}" var="tableHeader">
	                            <tr>
	                                <td class="center"><strong>${tableHeader.headerName}</strong></td>
	                                <td class="center">${notificationDetail[tableHeader.headerValue]}</td>
	                            </tr>
	                       </c:forEach>
                        </tbody>
                    </table>
                </div>
                    <div class="col-md-12">
	                        <div class="form-group">
	                            <label class="form-label"><strong>Comments</strong></label>
	                            <textarea name="comments" id="comments" class="width" required></textarea>
	                        </div>
                    </div>
                     <div class="alert msgResponse DispNone MarginTop20" role="alert">
                                
                     </div>
                     <div class="col-md-12 center">
                            <input type="hidden" id="requestType" name="requestType"/>
                            <input type="hidden" id="notificationId" name="notificationId"/>
                            <input type="hidden" id="tableName" name="tableName"/>
                            <input type="hidden" id="menuName" name="menuName"/>
                            <input type="hidden" id="recordId" name="recordId"/>
                            <input type="hidden" id="creatorId" name="creatorId"/>
                            <button type="button" class="btn  btn-primary" onclick="javascript:approveRecord('approve','notificationId', 'comments','tableName','menuName','recordId','creatorId')">Approve</button>
                            <button type="button" class="btn  btn-primary" onclick="javascript:approveRecord('reject','notificationId', 'comments','tableName','menuName','recordId','creatorId')">Reject</button>
                    </div>
			 </div>
			<div class="modal-footer MarginTop20">
				<button type="button" class="btn  btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

</script>