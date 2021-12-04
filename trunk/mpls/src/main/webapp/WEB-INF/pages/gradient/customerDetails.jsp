<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${!empty customer}">
      <div class="card-header center">
          <h6>Customer Details</h6>
      </div>
      <div class="card-body task-details">
          <table class="table table-striped table-bordered table-whitespace">
              <tbody>
                  <tr>
                      <td class="center"><strong>Customer Id</strong></td>
                      <td class="center">${customer.customerId}</td>
                  </tr>
                  <tr>
                      <td class="center"><strong>Customer Name</strong></td>
                      <td class="center">${customer.firstName} ${customer.lastName}</td>
                  </tr>
                  <tr>
                      <td class="center"><strong>Email</strong></td>
                      <td class="center">${customer.emailId}</td>
                  </tr>
                  <tr>
                          <td class="center"><strong>Mobile Number</strong></td>
                          <td class="center">${customer.mobileNumber}</td>
                      </tr>
                  <tr>
                      <td class="center"><strong>Wallet Balance</strong></td>
                      <td class="center" id="transactionAmount">${customer.walletBal}</td>
                  </tr>
              </tbody>
          </table>
      </div>
      <div class="col-md-12">
	      <div class="center">
	           <h6>Add Money</h6>
	      </div>
	           <div class="form-group">
	                <label class="form-label">Amount</label>
	                <input type="text" class="form-control decimal" name="addWalletBal" id="addWalletBal" placeholder="Amount">
	           </div>
	      </div>
          <div class="form-group row MarginLeft7">
               <div class="col-sm-9">
                  <div class="MarginLeft7 offerText">Available Balance <span id="availableWalletBalance-Add">${customer.walletBal}</span></div>
               </div>
          </div>
	        <input type="hidden" name="addCId" id="addCId" value="${customer.cId}">
	        <input type="hidden" name="addMId" id="addMId" value="${customer.mId}">
	        <input type="hidden" name="addTransactionAmountId" id="addTransactionAmountId">
	        <div class="alert alert-danger msgResponse DispNone" role="alert"></div>
	        <div class="col-md-12 text-center">
	             <button type="button" class="btn  btn-primary" onclick="addMoneyWallet('addWalletBal','addCId','addMId', 'addMoneyModal','TOPUP', 'addTransactionAmountId')">Add Money</button>
	        </div>
	   </c:if>
	   <c:if test="${empty customer}">
	   		<div class="alert alert-danger NoRecordMsg" role="alert">
                <strong>Oh snap!</strong> No Record Found!!
            </div>
	   </c:if>