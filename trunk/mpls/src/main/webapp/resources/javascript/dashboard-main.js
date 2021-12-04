var AesUtil=function(t,e){this.keySize=t/32,this.iterationCount=e};AesUtil.prototype.generateKey=function(t,e){return CryptoJS.PBKDF2(e,CryptoJS.enc.Hex.parse(t),{keySize:this.keySize,iterations:this.iterationCount})},AesUtil.prototype.encrypt=function(t,e,r,i){var n=this.generateKey(t,r);return CryptoJS.AES.encrypt(i,n,{iv:CryptoJS.enc.Hex.parse(e)}).ciphertext.toString(CryptoJS.enc.Base64)},AesUtil.prototype.decrypt=function(t,e,r,i){var n=this.generateKey(t,r),o=CryptoJS.lib.CipherParams.create({ciphertext:CryptoJS.enc.Base64.parse(i)});return CryptoJS.AES.decrypt(o,n,{iv:CryptoJS.enc.Hex.parse(e)}).toString(CryptoJS.enc.Utf8)};

!function(e){"function"==typeof define&&define.amd?define(["jquery"],e):"object"==typeof exports?e(require("jquery")):e(jQuery)}(function(e){var n=/\+/g;function o(e){return t.raw?e:encodeURIComponent(e)}function i(e){return o(t.json?JSON.stringify(e):String(e))}function r(o,i){var r=t.raw?o:function(e){0===e.indexOf('"')&&(e=e.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,"\\"));try{return e=decodeURIComponent(e.replace(n," ")),t.json?JSON.parse(e):e}catch(e){}}(o);return e.isFunction(i)?i(r):r}var t=e.cookie=function(n,c,u){if(void 0!==c&&!e.isFunction(c)){if("number"==typeof(u=e.extend({},t.defaults,u)).expires){var a=u.expires,d=u.expires=new Date;d.setTime(+d+864e5*a)}return document.cookie=[o(n),"=",i(c),u.expires?"; expires="+u.expires.toUTCString():"",u.path?"; path="+u.path:"",u.domain?"; domain="+u.domain:"",u.secure?"; secure":""].join("")}for(var f,p=n?void 0:{},s=document.cookie?document.cookie.split("; "):[],m=0,v=s.length;m<v;m++){var x=s[m].split("="),k=(f=x.shift(),t.raw?f:decodeURIComponent(f)),l=x.join("=");if(n&&n===k){p=r(l,c);break}n||void 0===(l=r(l))||(p[k]=l)}return p};t.defaults={},e.removeCookie=function(n,o){return void 0!==e.cookie(n)&&(e.cookie(n,"",e.extend({},o,{expires:-1})),!e.cookie(n))}});

var aesUtil = undefined;
var verifiedId='';
addPageScript(function(){
		if(ks != undefined && ic != undefined){
		     aesUtil = new AesUtil(ks, ic);
		}
		sessionId = getSessionId(sessionId);

		$(document).keypress(function(event){
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				 $(".LoginBtn").click();
			}
		});

		$('input[type="text"]#mobileNumber').val('');
		
		fillLoginData();
		hideDiv();
});

function validateUserCredential(){
	   var loginId = $("#loginId").val();
	   var password = $("#password").val();

	   if(loginId == undefined || loginId == ""){
	   	     $(".loginMessage").html("Please Enter Mobile Number");
	   	     $(".loginMessage").show();
	   	     setTimeout(function(){$(".loginMessage").hide();}, 1500);
	   	     return;
	   }

	   if(password == undefined || password == ""){
	   	     $(".loginMessage").html("Please Enter Password");
	   	     $(".loginMessage").show();
	   	     setTimeout(function(){$(".loginMessage").hide();}, 1500);
	   	     return;
	   }

	   if(password != undefined){
            var ciphertext = aesUtil.encrypt(sessionId, sessionId, loginId, password);  
	   	    $("#password").val(ciphertext);
	   }

	   if($('#isSaveCredentials').is(":checked")){
           rememberMe(loginId, password);
	   }else{
           resetLoginData();
	   }
      
       $("#loginForm").submit();
}

function resetPassword(){
	   var loginId = $("#loginId").val();

	   if(loginId == undefined || loginId == ""){
	   	     $(".loginMessage").html("Please Enter Mobile Number");
	   	     $(".loginMessage").show();
	   	     setTimeout(function(){$(".loginMessage").hide();}, 1500);
	   	     return;
	   }
      
       var response = $.ajax({type: 'POST', url: basepath+"/user/rest/reset-password-mail?loginId="+loginId, async: false}).responseText;
       if(response != undefined && response == "success"){
       	    $(".loginMessage").css("color", "green");
            $(".loginMessage").html("Reset password Email Sent Successfully");
       }else if(response != undefined && response == "isNotExist"){
       	    $(".loginMessage").css("color", "red");
            $(".loginMessage").html("User does not exist. Please Enter valid Mobile Number");
            $(".loginMessage").show();
	   	    setTimeout(function(){$(".loginMessage").hide();}, 1500);
       }else{
       	    $(".loginMessage").css("color", "red");
       	    $(".loginMessage").html("Error in Reset Password Mail");
       	    $(".loginMessage").show();
       	    setTimeout(function(){$(".loginMessage").hide();}, 1500);
       }
}

function changePassword(){
	   var loginId = $("#loginId").val();
	   var id = $("#id").val();
	   var password = $("#password").val();
	   var confirmPassword = $("#confirmPassword").val();

	   if(password == undefined || password == ""){
	   	     $(".loginMessage").html("Please Enter Password");
	   	     $(".loginMessage").show();
	   	     setTimeout(function(){$(".loginMessage").hide();}, 1500);
	   	     return;
	   }

	   if(confirmPassword == undefined || confirmPassword == ""){
	   	     $(".loginMessage").html("Please Enter Confirm Password");
	   	     $(".loginMessage").show();
	   	     setTimeout(function(){$(".loginMessage").hide();}, 1500);
	   	     return;
	   }

	   if(password != confirmPassword){
	   	     $(".loginMessage").html("Password and Confirm password must match.");
	   	     $(".loginMessage").show();
	   	     setTimeout(function(){$(".loginMessage").hide();}, 1500);
	   	     return;
	   }

       var ciphertext = aesUtil.encrypt(sessionId, sessionId, id, password);  
       console.log("ciphertext == "+ciphertext);

       var isValidate = $.ajax({type: 'POST', url: basepath+"/user/rest/validate/password", data: { loginId: loginId, id: id, password: ciphertext }, async: false}).responseText;
	   if(isValidate != undefined && isValidate != "success"){
            $(".loginMessage").html(isValidate);
            $(".loginMessage").show();
            setTimeout(function(){$(".loginMessage").hide();}, 1500);
            return;
       }

       var response = $.ajax({type: 'POST', url: basepath+"/user/rest/update-password", data: { loginId: loginId, id: id, password: ciphertext }, async: false}).responseText;
       if(response != undefined && response == "success"){
	       	    $(".loginMessage").css("color", "green");
	            $(".loginMessage").html("Password Updated Successfully.");
	            $(".loginMessage").show();
	            $("#password").val('');
		        $("#confirmPassword").val('');
		        setTimeout(function(){ location.href = basepath; }, 1000);
       }else if(response != undefined && response == "failure"){
	            $(".loginMessage").html("Unable to Set New Password!!");
	            $("#password").val('');
		        $("#confirmPassword").val('');
       }else{
       	    $(".loginMessage").html("Session Expired!! Please refresh Url & try again");
       	    $("#password").val('');
	        $("#confirmPassword").val('');
       }

}

function getSessionId(sessionId){
	    if(sessionId != undefined && sessionId.length > 32){
	   	    var id = sessionId.substr(0,32);
	   	    return id;
	    }
	    return sessionId;
}


function rememberMe(loginId, password){
		if(loginId != undefined && loginId != "" && password != undefined && password != ""){
			 $.cookie('password', password);
	         $.cookie('loginId', loginId);
	         $.cookie('isSaveCredentials', 'true');
	    }
}

function fillLoginData(){
	    if(!!$.cookie('isSaveCredentials')){
	    	$('#isSaveCredentials').attr("checked", true);
	    }
	    if(!!$.cookie('loginId') && ($('#loginId').val() == undefined || $('#loginId').val() == "")){
	        $('#loginId').val($.cookie('loginId'));
	    }
	    if(!!$.cookie('password')){
	        $('#password').val($.cookie('password'));
	    }
}

function resetLoginData(){
	   $.cookie('loginId', '');
	   $.cookie('password', '');
	   $.cookie('isSaveCredentials', '');
}

function hideDiv(){
	$('#passwordDiv').hide();
}

function sendMobileVerificationCode(mobileNumber, type, modalId){
	console.log("TTTTTTTTT")
    var mobileNumber = $("#"+mobileNumber).val();
    if(mobileNumber != undefined && mobileNumber != ""){
    	$("#verificationModalTitle").html(type + " Verification");
    	$('#mobileVerificationCode').val('');
    	$('#'+modalId).modal({
              show: true
        });
	    $.ajax({
		    type: "POST",
			contentType: "application/json; charset=utf-8",	
		    url: basepath+"/rest/ajax/sendMobileVerificationCode?mobileNumber="+mobileNumber,				    
			success: function (data) {
				console.log("MMMMMMMMMMMMMMMMMMMMM")
		          if(data != undefined && data != ""){
		          	  verifiedId = data;
					console.log(data,"kktttttttttttttttttk")
		          } 
		    }
		});
	}
}

function verifyCode(verifiedInputId, verificationCode, msgId, modalId){
    var verificationCodeValue = $("#"+verificationCode).val();

console.log( $("#"+verifiedInputId).val(),verificationCode,verifiedId)
    if(verifiedId != undefined && verifiedId != "" && verificationCodeValue != undefined && verificationCodeValue != ""){
        var ciphertext = aesUtil.encrypt(sessionId, sessionId, verifiedId, verificationCodeValue); 
	    $.ajax({
		    type: "POST",
		    url: basepath+"/rest/ajax/verifyCode",
		    data: {id : verifiedId, verificationCode : ciphertext},
		    success: function (data) {
	            if(data != undefined && data == "true"){
	            	 $("#"+verifiedInputId).val(verifiedId);
	            	 $("#"+msgId).html("Mobile Number Verified Successfully");
	            	 $('#mobileNumberDiv').hide();
	            	 $('input[type="password"]#password').val('');
	            	 $('#passwordDiv').show();
	          	     setTimeout(function(){ $("#"+modalId).modal("hide"); }, 600);
	            }else{
                     $("#"+verificationCode).css("border", "1px solid #FF5370");
                     $("."+msgId).html("Please Enter Valid Verification Code");
		             $("."+msgId).show();
		             setTimeout(function(){$("."+msgId).hide();}, 3000);
	            }
		    }
		});
	}else{
		$("."+msgId).html("Please Enter Verification Code");
        $("."+msgId).show();
        setTimeout(function(){$("."+msgId).hide();}, 3000);
		$("#"+verificationCode).css("border", "1px solid #FF5370");
	}
}

function verifyMobileNumber(mobileNumber){
    var mobile = $("#"+mobileNumber).val();
    if(mobile != undefined &&  mobile != "" && validateMobile(mobile)){
    	  $("#verifiedId").val("");
	  	  $(".verifyMobileBtn").attr("disabled", false);
          return true;
	  }else{
		  $(".verifyMobileBtn").attr("disabled", true);
          $("#verifiedId").val("1");
          $(".loginMessage").css("color", "red");
          $(".loginMessage").html("Please Enter Valid Mobile Number");
          $(".loginMessage").show();
          setTimeout(function(){$(".loginMessage").hide();}, 1500);
          return false;
	  }
}

function validateMobile($mobile) {
    var emailReg = /^[0][1-9]\d{9}$|^[1-9]\d{9}$/;
    return emailReg.test( $mobile );
}