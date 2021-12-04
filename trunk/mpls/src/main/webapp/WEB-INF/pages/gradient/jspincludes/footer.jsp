<script src="${applicationScope['jsBaseUrl']}/gradient/js/vendor-all.min.js" defer></script>
<script type="text/javascript">
	var basepath = "${applicationScope['baseUrl']}";
	var logoUrl = "${applicationScope['baseUrl']}/resources/gradient/images/auth/logo-small.png";
	var pageScript=[],pageLoaded=!1,addPageScript=function(o){"function"==typeof o&&(pageLoaded?o():pageScript.push(o))},loadPageScript=function(){pageLoaded=!0;for(var o in pageScript)pageScript[o]()},windowLoadScript=[],windowLoaded=!1,addWindowLoadScript=function(o){"function"==typeof o&&(windowLoaded?o():windowLoadScript.push(o))};window.onload=function(){windowLoaded=!0;for(var o in windowLoadScript)windowLoadScript[o]()};
</script>