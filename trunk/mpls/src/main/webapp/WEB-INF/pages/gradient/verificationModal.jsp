<div class="modal fade show" id="verificationModal" tabindex="-1" role="dialog" aria-labelledby="verificationModalLabel" aria-modal="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="verificationModalTitle"></h5>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="verificationCode" class="col-form-label">Verification Code:</label>
					<input type="text" class="form-control numeric" id="verificationCode" id="verificationCode" maxlength="6">
				</div>
				<div class="alert alert-danger verificationCodeMsg DispNone" role="alert">
                    <strong>Oh snap!</strong> ${message}
                </div>
                <div class="col-md-12 text-center">
					<button type="button" class="btn btn-primary" onclick="verifyCode('verifiedId', 'verificationCode', 'verificationCodeMsg', 'verificationModal')">Submit</button>
					<button class="btn btn-danger" type="reset" formnovalidate="formnovalidate">Reset</button>
		        </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn  btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>