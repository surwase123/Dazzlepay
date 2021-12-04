<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="addressDetailsModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="addressDetailsModalTitle" style="display: block; padding-right: 15px;" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addressDetailsModalTitle"></h5>
 			</div>
			<div class="modal-body">
				<div class="dt-responsive table-responsive" id="merchantAddressModalTable">
	                    <table id="multi-colum-dt-detail" class="table table-striped table-bordered nowrap">
	                        <thead>
	                            <tr>
	                                <th>#</th>
                                    <th class="text-center">State</th>
                                    <th class="text-center">City</th>
                                    <th class="text-center">Area</th>
                                    <th class="text-center">PinCode</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                             <c:forEach items="${merchantAddressList}" var="address" varStatus="index">
	                                <tr>
	                                    <td class="text-center">${index.count}</td>
	                                    <td class="text-center">${address.stateName}</td>
	                                    <td class="text-center">${address.cityName}</td>
	                                    <td class="text-center">${address.areaCode}</td>
	                                    <td class="text-center">${address.pincode}</td>
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