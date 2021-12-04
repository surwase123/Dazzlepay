<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<header class="navbar pcoded-header navbar-expand-lg navbar-light headerpos-fixed header-orchidgreen">
		<div class="m-header">
			<a class="mobile-menu" id="mobile-collapse" href="#!"><span></span></a>
			<a href="${applicationScope['baseUrl']}" class="b-brand">
				<img src="${applicationScope['baseUrl']}/resources/gradient/images/DP-White.png" alt="Background Logo" class="logo">
				<img src="${applicationScope['baseUrl']}/resources/gradient/images/DP-White.png" alt="Background Logo" class="logo-thumb">
			</a>
			<a href="#!" class="mob-toggler">
				<i class="feather icon-more-vertical"></i>
			</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<a href="#!" class="full-screen" onclick="javascript:toggleFullScreen()"><i class="feather icon-maximize"></i></a>
				</li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<c:if test="${!empty(sessionScope.loginDTO.merchant)}"> 
					<li class="right-brand-logo">
						<span><strong>Welcome,</strong> 
							<c:if test="${fn:length(sessionScope.loginDTO.merchant.merchantName) gt 16}">
                                ${fn:substring(sessionScope.loginDTO.merchant.merchantName,0,15)}...
                            </c:if>
                            <c:if test="${fn:length(sessionScope.loginDTO.merchant.merchantName) le 16}">
                                ${sessionScope.loginDTO.merchant.merchantName}
                            </c:if>
						</span>
					</li>
				</c:if>
				

<%-- Tushar changes Started--%>  

					<c:if test="${!empty(sessionScope.loginDTO.requestLoyaltyCardsNotificationList) || !empty(sessionScope.loginDTO.notificationList)}"> 
					<li>
						<div class="dropdown">
							<a class="dropdown-toggle" href="#" data-toggle="dropdown"><i class="icon feather icon-bell"></i><span class="badge bg-danger"><span class="sr-only"></span></span></a>
							<div class="dropdown-menu dropdown-menu-right notification">
								<div class="noti-head">
									<h6 class="d-inline-block m-b-0">Notifications</h6>
									<div class="float-right">
										<a href="#!" class="m-r-10">mark as read</a>
										<a href="#!">clear all</a>
									</div>
								</div>
								<c:if test="${!empty(sessionScope.loginDTO.notificationList)}">
								<ul class="noti-body">
									<c:forEach items="${sessionScope.loginDTO.notificationList}" var="list">
										<li onclick="javascript:approveUserNotificationById1('${list.recordId}','${list.tableName}', '${list.id}', '${list.menuName}', '${list.creatorId}')" class="notification">
											<div class="media">
												<div class="media-body">											
													<p><strong>${list.menuName}</strong><span class="n-time text-muted"><i class="icon feather icon-clock m-r-10"></i><fmt:formatDate pattern = "dd MMM, YYYY" value = "${list.insertDate}"/></span></p>
													<p>${list.message}</p>											
		 										</div>
										    </div>
									    </li>
								    </c:forEach>
								</ul>
								</c:if>
								<ul class="noti-body">
									<c:forEach items="${sessionScope.loginDTO.requestLoyaltyCardsNotificationList}" var="list">
										<li class="notification" onclick="javascript:approveNotification1('${list.id}' ,'${list.mId}', '${list.message}')">
											<div class="media">
												<div class="media-body">											
													<p><i class="icon feather icon-clock m-r-10"></i></p>
<%-- 													<strong>${list.toString()}</strong><span class="n-time text-muted"> --%>
													<p>${list.message}</p>											
		 										</div>
										    </div>
									    </li>
								    </c:forEach>
								</ul>
								<div class="noti-footer">
									<a href="${applicationScope['baseUrl']}/loyaltyCard/notificationLoyaltyCard">show all</a>
								</div>
							</div>
						</div>
					</li>
                </c:if>
     <%--Tushar Changes End --%>
     
				<c:if test="${!empty(sessionScope.loginDTO.pushNotificationLogList) || !empty(sessionScope.loginDTO.notificationList)}"> 
					<li>
						<div class="dropdown">
							<a class="dropdown-toggle" href="#" data-toggle="dropdown"><i class="icon feather icon-bell"></i><span class="badge bg-danger"><span class="sr-only"></span></span></a>
							<div class="dropdown-menu dropdown-menu-right notification">
								<div class="noti-head">
									<h6 class="d-inline-block m-b-0">Notifications</h6>
									<div class="float-right">
										<a href="#!" class="m-r-10">mark as read</a>
										<a href="#!">clear all</a>
									</div>
								</div>
								<c:if test="${!empty(sessionScope.loginDTO.notificationList)}">
								<ul class="noti-body">
									<c:forEach items="${sessionScope.loginDTO.notificationList}" var="list">
										<li onclick="javascript:approveUserNotificationById('${list.recordId}','${list.tableName}', '${list.id}', '${list.menuName}', '${list.creatorId}')" class="notification">
											<div class="media">
												<div class="media-body">											
													<p><strong>${list.menuName}</strong><span class="n-time text-muted"><i class="icon feather icon-clock m-r-10"></i><fmt:formatDate pattern = "dd MMM, YYYY" value = "${list.insertDate}"/></span></p>
													<p>${list.message}</p>											
		 										</div>
										    </div>
									    </li>
								    </c:forEach>
								</ul>
								</c:if>
								<ul class="noti-body">
									<c:forEach items="${sessionScope.loginDTO.pushNotificationLogList}" var="list">
										<li class="notification" onclick="javascript:approveNotification('${list.id}' ,'${list.customerNotificationId}', '${list.notificationType}')">
											<div class="media">
												<div class="media-body">											
													<p><strong>${list.notificationType}</strong><span class="n-time text-muted"><i class="icon feather icon-clock m-r-10"></i><fmt:formatDate pattern = "dd MMM, YYYY" value = "${list.insertTimeStamp}"/></span></p>
													<p>${list.message}</p>											
		 										</div>
										    </div>
									    </li>
								    </c:forEach>
								</ul>
								<div class="noti-footer">
									<a href="${applicationScope['baseUrl']}/add/user/pushNotification">show all</a>
								</div>
							</div>
						</div>
					</li>
                </c:if>
                <c:if test="${!empty(sessionScope.loginDTO.makerNotificationList)}"> 
	                <li>
					   <div class="dropdown">
						   <a href="${applicationScope['baseUrl']}/add/user/userRequest" class="displayChatbox dropdown-toggle"><i class="icon feather icon-mail"></i><span class="badge bg-success"><span class="sr-only"></span></span></a>
					   </div>
					</li>
				</c:if>
				<li>
					<div class="dropdown drp-user">
						<a href="#!" class="dropdown-toggle" data-toggle="dropdown">
                           <i class="feather icon-users img-radius wid-40" aria-hidden="true"></i>
                        </a>
						<div class="dropdown-menu dropdown-menu-right profile-notification">
							<div class="pro-head">
								<i class="feather icon-users img-radius" aria-hidden="true"></i>
								<span>${sessionScope.loginDTO.firstName} ${sessionScope.loginDTO.lastName}</span>
								<a href="${applicationScope['baseUrl']}/user/logout.htm" class="dud-logout" title="Logout">
									<i class="feather icon-log-out"></i>
								</a>
							</div>
							<ul class="pro-body">
								<li><a href="${applicationScope['baseUrl']}/add/user/profile" class="dropdown-item"><i class="feather icon-user"></i> Profile</a></li>
								<c:if test="${!empty(sessionScope.loginDTO.makerNotificationList)}"> 
								    <li><a href="${applicationScope['baseUrl']}/add/user/userRequest" class="dropdown-item"><i class="feather icon-mail"></i> My Messages</a></li>
								</c:if>
								<c:if test="${sessionScope.loginDTO.groupDTO.groupType == 'admin'}">
								   <li><a href="${applicationScope['baseUrl']}/userReport/view" class="dropdown-item"><i class="fa fa-history"></i>User History</a></li>
								</c:if>
								<li><a href="${applicationScope['baseUrl']}/add/user/changePassword" class="dropdown-item"><i class="feather icon-edit-2"></i>Change Password</a></li>
								<li><a href="${applicationScope['baseUrl']}/user/logout.htm" class="dropdown-item"><i class="feather icon-lock"></i> Logout</a></li>
							</ul>
						</div>
					</div>
				</li>
			</ul>
		</div>
</header>
