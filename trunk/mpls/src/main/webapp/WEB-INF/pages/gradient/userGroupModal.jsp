<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="userGroupModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="userGroupModalTitle" style="display: block; padding-right: 15px;" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="userGroupModalTitle"></h5>
 			</div>
			<div class="modal-body">
			    <div class="dt-responsive table-responsive">
                    <table id="multi-group-detail-report" class="table table-striped table-bordered nowrap">
                        <tbody>
                          <tr>
                            <td class="center"><strong>System Id</strong></td>
                            <td class="center">${group.systemId}</td>
	                      </tr>
	                      <tr>
                            <td class="center"><strong>Group Name</strong></td>
                            <td class="center">${group.groupName}</td>
	                      </tr>
	                      <tr>
                            <td class="center"><strong>Group Type</strong></td>
                            <td class="center capitalize">${group.groupType}</td>
	                      </tr>
	                      <tr>
                            <td class="center"><strong>Min. Password Length</strong></td>
                            <td class="center">${group.minPassLength}</td>
	                      </tr>
	                      <tr>
                            <td class="center"><strong>Max. Password Length</strong></td>
                            <td class="center">${group.maxPassLength}</td>
	                      </tr>
	                      <tr>
                            <td class="center"><strong>Password Characteristics</strong></td>
                            <td class="center">
                            	<c:if test="${group.isAlphaPassword == 'Y'}">Alpha <br/></c:if>
                                <c:if test="${group.isNumberPassword == 'Y'}">Numeric <br/></c:if>
                                <c:if test="${group.isSpecialSymbolPass == 'Y'}">Special Characters</c:if>
                            </td>
	                      </tr>
	                      <tr>
                            <td class="center"><strong>Password Expiry Days</strong></td>
                            <td class="center">${group.passExpiryDays}</td>
	                      </tr>
	                      <tr>
                            <td class="center"><strong>Password History Check</strong></td>
                            <td class="center">${group.passHistoryChecks}</td>
	                      </tr>
	                      <tr>
                            <td class="center"><strong>Password History Check</strong></td>
                            <td class="center">${group.passHistoryChecks}</td>
	                      </tr>
	                      <tr>
                            <td class="center"><strong>Max. Login Retries</strong></td>
                            <td class="center">${group.maxLoginRetries}</td>
	                      </tr>
                        </tbody>
                    </table>
                </div>
			 </div>
			<div class="modal-footer MarginTop20">
				<button type="button" class="btn  btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>