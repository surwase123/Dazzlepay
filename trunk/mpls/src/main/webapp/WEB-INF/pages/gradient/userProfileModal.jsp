<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="userProfileModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="userProfileModalTitle" style="display: block; padding-right: 15px;" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">User Info</h5>
 			</div>
			<div class="modal-body task-details">
                    <table class="table">
                        <tbody>
                            <tr>
                                <td class="center"><strong>Name</strong></td>
                                <td class="center">${loginDTO.firstName} ${loginDTO.lastName}</td>
                            </tr>
                            <tr>
                                <td class="center"><strong>LoginId</strong></td>
                                <td class="center">${loginDTO.loginId}</td>
                            </tr>
                            <tr>
                                <td class="center"><strong>EmailId</strong></td>
                                <td class="center">${loginDTO.emailId}</td>
                            </tr>
                            <tr>
                                <td class="center"><strong>Role Type</strong></td>
                                <td class="center">${loginDTO.userRoleType}</td>
                            </tr>
                            <tr>
                                <td class="center"><strong>Group Name</strong></td>
                                <td class="center">${loginDTO.groupDTO.groupName}</td>
                            </tr>
                        </tbody>
                    </table>
			 </div>
			<div class="modal-footer">
				<button type="button" class="btn  btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>