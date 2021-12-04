<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<head>
    <title>MPLS Dashboard | 404 Error</title>
    <%-- HTML5 Shim and Respond.js IE11 support of HTML5 elements and media queries --%>
    <%-- WARNING: Respond.js doesn't work if you view the page via file:// --%>
    <%--[if lt IE 11]>
    	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    	<![endif]--%>
    <%-- Meta --%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="description" content="" />
    <meta name="keywords" content="">
    <meta name="author" content="Codedthemes" />
    <%-- Favicon icon --%>
    <link rel="icon" href="${applicationScope['baseUrl']}/resources/gradient/images/favicon.ico" type="image/x-icon">
    <%-- vendor css --%>
    <link rel="stylesheet" href="${applicationScope['cssBaseUrl']}/gradient/css/style.css">
</head>
<%-- [ offline-ui ] start --%>
<div class="auth-wrapper maintance">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="text-center">
                    <img src="${applicationScope['baseUrl']}/resources/gradient/images/maintance/error.png" alt="" class="img-fluid">
                    <h5 class="mt-4">Oops! Something went wrong...</h5>
                    <p>The page you're looking for is not available. Please check the URL.</p>
                    <form action="${applicationScope['baseUrl']}">
                        <button class="btn waves-effect waves-light btn-danger mb-4"><i class="feather icon-refresh-ccw mr-2"></i>Reload</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- [ offline-ui ] end --%>
<%-- Required Js --%>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/vendor-all.min.js" defer></script>
<script src="${applicationScope['jsBaseUrl']}/gradient/js/plugins/bootstrap.min.js" defer></script>
<script type="text/javascript">
    var pageScript=[],pageLoaded=!1,addPageScript=function(o){"function"==typeof o&&(pageLoaded?o():pageScript.push(o))},loadPageScript=function(){pageLoaded=!0;for(var o in pageScript)pageScript[o]()},windowLoadScript=[],windowLoaded=!1,addWindowLoadScript=function(o){"function"==typeof o&&(windowLoaded?o():windowLoadScript.push(o))};window.onload=function(){windowLoaded=!0;for(var o in windowLoadScript)windowLoadScript[o]()};
</script>
</body>
</html>
