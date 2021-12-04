<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>

	</head>
<body>
<div class="row">
	<div class="col-md-12 order-md-2">
	<div class="card">
		   
			   <div class="card-header">
					
					<h5>Allotted Loyalty Cards</h5>
				</div>
					<div class="card-body">
						<div class="dt-responsive table-responsive">
							<table id="multi-colum-dt-1" class="table table-striped table-bordered nowrap">
								<thead>
									<tr>
										<th>#</th>
										<th>Request Id</th>
										<th>Merchant Name</th>
										<th>Merchant Id</th>
										<th>Quantity Of Number</td>
										<th>Address</th>
										<th>Date</th>
										<th></th>
										
<!-- 										<Th>Action</Th> -->										
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${customerList}" var="customerList" varStatus="index"> 
					                 <c:if test="${customerList.status  == 3 }">
										<tr>
											<td>${index.count}</td>
											<td>${customerList.id}</td>
											<td>${customerList.name}</td>
											<td>${customerList.merchantId}</td>
											<td>${customerList.quantityOfCards}</td>
											<td>${customerList.shippingAddress}</td>
											<td><fmt:formatDate pattern = "dd MMM, YYYY" value = "${customerList.updateTimeStamp}" /></td>
											<td>
											<button  type="submit" class="btn btn-primary" >Export CSV</button>
											<button  type="submit" class="btn btn-danger"  >Dispatch</button>
											</td>
										</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				</div>
				</div>
				
				<script
			src="${applicationScope['jsBaseUrl']}/gradient/js/pages/form-validation.js"
			defer></script>
<%-- 			<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/exportReport.js" defer></script> --%>
			<script src="${applicationScope['jsBaseUrl']}/javascript/loyaltyCard.js" defer></script>
			
			
			<script>
// 			$(document).ready(function() {
//  		    $('multi-colum-dt-1').DataTable();
// 			})
			
	$(document).ready(function() {
    $('#multi-colum-dt-1').DataTable( {
        order: [[2, 'asc']],
        rowGroup: {
            endRender: function ( rows, group ) {
                var avg = rows
                    .data()
                    .pluck(5)
                    .reduce( function (a, b) {
                        return a + b.replace(/[^\d]/g, '')*1;
                    }, 0) / rows.count();
 
                return 'Average salary in '+group+': '+
                    $.fn.dataTable.render.number(',', '.', 0, '$').display( avg );
            },
            dataSrc: 2
        }
    } );
} );
			
		</script> 
</body>
</html>