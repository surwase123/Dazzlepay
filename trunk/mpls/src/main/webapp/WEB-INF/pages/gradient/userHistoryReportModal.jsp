<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div id="userHistoryModal" class="modal fade show" tabindex="-1" role="dialog" aria-labelledby="userHistoryModalTitle" style="display: block; padding-right: 15px;" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="userHistoryModalTitle"></h5>
 			</div>
			<div class="modal-body">
				<div class="dt-responsive table-responsive" id="userHistoryReportTable">
	                    <table id="multi-colum-dt-detail" class="table table-striped table-bordered nowrap">
	                        <thead>
	                            <tr>
	                                <th>#</th>
	                                <th>Login Id</th>
	                                <th>Request Url</th>
	                                <th>Session Id</th>
	                                <th>Source</th>
	                                <th>Ip Address</th>
	                                <th>OS</th>
	                                <th>Browser</th>
	                                <th>Timestamp</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                             <c:forEach items="${userHistoryList}" var="userHistory" varStatus="index">
	                                <tr>
	                                    <td>${index.count}</td>
	                                    <td>${userHistory.loginId}</td>
	                                    <td>${userHistory.request_url}</td>
	                                   	<td>${userHistory.session_id}</td>
	                                   	<td>${userHistory.source}</td>
	                                   	<td>${userHistory.ip_address}</td>
	                                   	<td>${userHistory.os}</td>
	                                   	<td>${userHistory.browser}</td>
	                                   	<td><fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${userHistory.date}"/></td>
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