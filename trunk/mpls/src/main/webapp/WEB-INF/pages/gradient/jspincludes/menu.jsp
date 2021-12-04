<nav class="pcoded-navbar menupos-fixed menu-light ">
	<div class="navbar-wrapper  ">
		<div class="navbar-content scroll-div " >
			<ul class="nav pcoded-inner-navbar ">
				<li class="nav-item pcoded-menu-caption">
					<label>Navigation</label>
				</li>
				
                <c:forEach items="${sessionScope.loginDTO.menuList}" var="leftMenu" varStatus="index">
                    <c:if test="${!empty(leftMenu.userRoleMenuList)}">
	                    <li class="nav-item pcoded-menu-caption">
							<label>${leftMenu.menuName}</label>
						</li>
	                    <li class="nav-item pcoded-hasmenu">
						        <a href="#!" class="nav-link "><span class="pcoded-micon fontColor"><i class="${leftMenu.menuIcon}"></i></span><span class="pcoded-mtext fontColor">${leftMenu.menuName}</span></a>
						        <c:if test="${!empty(leftMenu.userRoleMenuList)}">
								   <ul class="pcoded-submenu">
					                    <c:forEach items="${leftMenu.userRoleMenuList}" var="leftSubMenu" varStatus="index">
					                           <li><a href="${applicationScope['baseUrl']}/${leftSubMenu.action}"><span class="subMenufontColor">${leftSubMenu.subMenuName}</span></a></li>
					                    </c:forEach>
			                       </ul>
			                    </c:if>
	                    </li>
	                </c:if>
	                <c:if test="${empty(leftMenu.userRoleMenuList)}">
	                    <li class="nav-item pcoded-hasmenu">
							<a href="${applicationScope['baseUrl']}/${leftMenu.action}" class="nav-link "><span class="pcoded-micon fontColor"><i class="${leftMenu.menuIcon}"></i></span><span class="pcoded-mtext fontColor">${leftMenu.menuName}</span></a>
						</li>
	                </c:if>
                </c:forEach>
			</ul>
			
			<%--<div class="card text-center">
				<div class="card-block">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					<i class="feather icon-sunset f-40"></i>
					<h6 class="mt-3">Help?</h6>
					<p>Please contact us on our email for need any support</p>
					<a href="#!" target="_blank" class="btn btn-primary btn-sm text-white m-0">Support</a>
				</div>
			</div>--%>
			
		</div>
	</div>
</nav>
