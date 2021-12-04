<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-md-12 order-md-2">
		<div class="card">
			<div class="card-header">
				<h5>Customer</h5>
			</div>
			<div class="card-body">
				<div class="dt-responsive table-responsive">
					<table id="multi-colum-customer" class="table table-striped table-bordered nowrap">
						<thead>
							<tr>
								<th>#</th>
								<th>Customer Id</th>
								<th>Login Id</th>
								<th>Name</th>
								<th>Email Id</th>
								<th>Mobile Number</th>
								<th>Wallet Balance</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${customerList}" var="customer" varStatus="index">
								<tr>
									<td>${index.count}</td>
									<td>${customer.customerId}</td>
									<td>${customer.loginId}</td>
									<td>${customer.firstName} ${customer.middleName} ${customer.lastName}</td>
									<td>${customer.emailId}</td>
									<td>${customer.mobileNumber}</td>
									<td>${customer.walletBal}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
