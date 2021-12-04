<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="communicationTemplateModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="communicationTemplateModalTitle" style="display: block; padding-right: 15px;" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="communicationTemplateModalTitle"></h5>
 			</div>
			<div class="modal-body">
				${commMessageTemplate}
			</div>
			<div class="modal-footer">
				<button type="button" class="btn  btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
