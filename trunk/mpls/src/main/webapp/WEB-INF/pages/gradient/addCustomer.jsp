<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:if test="${empty customer}">
		<div class="alert alert-danger NoRecordMsg" role="alert">
                <strong>Oh snap!</strong> No Record Found!!
        </div>
	</c:if>
	<div class="form-row">
	     <div class="form-group col-md-4">
	         <label for="firstName">First Name<b class="mandetory">*</b></label>
	         <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" value="${customer.firstName}"/> 
	     </div>
	     <div class="form-group col-md-4">
	         <label for="middleName">Middle Name</label>
	         <input type="text" class="form-control" id="middleName" name="middleName" placeholder="Middle Name" value="${customer.middleName}"/> 
	     </div>
	     <div class="form-group col-md-4">
	         <label for="lastName">Last Name<b class="mandetory">*</b></label>
	         <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" value="${customer.lastName}"/>
	     </div>
	</div>
	<c:if test="${!empty customer}">
		<div class="form-row">
		     <div class="col-md-8">
				<div class="form-group">
					<label class="form-label">Mobile Number<b class="mandetory">*</b></label>
					<input type="text" class="form-control" name="mobileNumber" id="mobileNumber" placeholder="Mobile Number" value="${customer.mobileNumber}" onblur="isExistsMobileNumber('mobileNumber', '${customer.mobileNumber}');verifyMobileNumber('mobileNumber', '${customer.mobileNumber}')" readonly="readonly">
				</div>
			</div>
		</div>
		<input type="hidden" name="verifiedId" id="verifiedId" value="1">
	</c:if>
	<c:if test="${empty customer}">
		<div class="form-row">
		     <div class="col-md-8">
				<div class="form-group">
					<label class="form-label">Mobile Number<b class="mandetory">*</b></label>
					<input type="text" class="form-control" name="mobileNumber" id="mobileNumber" placeholder="Mobile Number" value="${customer.mobileNumber}" onblur="isExistsMobileNumber('mobileNumber', '${customer.mobileNumber}');verifyMobileNumber('mobileNumber', '${customer.mobileNumber}')">
				</div>
			</div>
			<!-- <div class="form-group col-md-1 VerifyMobileBtn">
		         <button type="button" class="btn btn-success verifyMobileBtn" onclick="sendMobileVerificationCode('mobileNumber', 'customer', 'Mobile Number', 'mobileVerificationModal')" disabled="">Verify</button>
		    </div> -->
		</div>
	</c:if>
	<div class="col-md-12">
		<div class="form-group">
			<label class="form-label">Email Id<b class="mandetory">*</b></label>
			<input type="text" class="form-control" name="emailId" id="emailId" placeholder="Email Id" value="${customer.emailId}" onblur="isExistsEmailId('emailId', '${customer.emailId}')" >
		</div>
	</div>
	<div class="col-md-12">
		 <div class="form-group">
				    <label class="form-label">Loyalty Number</label>
					<input type="number" class="form-control" name="loyaltyCardNumber" id="loyaltyCardNumber" placeholder="Loyalty Number" minlength="16" maxlength="16" value="" >
		</div>
	</div>
<input type="hidden" name="verifiedId" id="verifiedId" value="${customer.verifiedId}">