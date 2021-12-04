<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="dt-responsive table-responsive">
		<table id="search-store-table" class="table nowrap search-store">
			<thead>
				<tr>
					<th class="width35">Merchant Name</th>
					<th class="width22">Category</th>
					<th class="width12">Address</th>
					<th class="width12">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${filterStoreList}" var="merchant" varStatus="index">
					<tr>
						<td class="width35">
							<div class="d-inline-block align-middle">
								<img data-gsll-src="${merchant.icon}" class="align-top m-r-15" width="162" height="128">
								<div class="d-inline-block">
									<h5 class="m-b-0 lineHight30">${merchant.merchantName}</h5>
								</div>
							</div>
						</td>
						<td class="width22">
							<div class="d-inline-block">
								<p class="m-b-0 lineHight30">${merchant.categoryCode}</p>
								<p class="m-b-0 lineHight22 searchStoreCategory"> ${merchant.categoryName}</p>
							</div>
						</td>
						<td class="addressStore width6"><a href="javascript:displayMerchantAddress('${merchant.id}', '${merchant.merchantName}(${merchantIdPrefix}${merchant.merchantId}) - Addresses')">Address</a></td>
						<td class="width6">
							<c:if test="${merchant.offerCount > 0}">
							    <button type="button" class="btn bg-c-yellow border border-green btnWhite pointer OfferCard" onclick="getMerchantOffers('${merchant.id}', '${merchant.merchantName} - Offers')"><i class="fa fa-gift"></i></button>
							</c:if>
							<c:if test="${merchant.isAddedMerchant == 0}">
							    <button type="button" class="MarginLeft13 btn bg-c-green border border-green btnWhite pointer" onclick="mappingMerchant('${merchant.merchantId}')"><i class="feather icon-plus-square"></i></button>
							</c:if>
							<c:if test="${merchant.isAddedMerchant == 2}">
							    <button type="button" class="MarginLeft13 btn bg-c-green border border-green btnWhite pointer" onclick="isAlreadyRequested()"><i class="feather icon-minus-circle"></i></button>
							</c:if>
							<%-- <c:if test="${merchant.isAddedMerchant == 1}">
							    <button type="button" class="MarginLeft13 btn bg-c-red border border-red btnWhite pointer" onclick="deleteMerchant('${merchant.id}', '${sessionScope.loginDTO.customer.id}', 'searchStore')"><i class="feather icon-minus-circle"></i></button>
							</c:if> --%>
						</td>
					</tr>
			    </c:forEach>
			</tbody>
		</table>
	</div>