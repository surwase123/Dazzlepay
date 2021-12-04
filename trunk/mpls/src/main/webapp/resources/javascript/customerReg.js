var AesUtil=function(t,e){this.keySize=t/32,this.iterationCount=e};AesUtil.prototype.generateKey=function(t,e){return CryptoJS.PBKDF2(e,CryptoJS.enc.Hex.parse(t),{keySize:this.keySize,iterations:this.iterationCount})},AesUtil.prototype.encrypt=function(t,e,r,i){var n=this.generateKey(t,r);return CryptoJS.AES.encrypt(i,n,{iv:CryptoJS.enc.Hex.parse(e)}).ciphertext.toString(CryptoJS.enc.Base64)},AesUtil.prototype.decrypt=function(t,e,r,i){var n=this.generateKey(t,r),o=CryptoJS.lib.CipherParams.create({ciphertext:CryptoJS.enc.Base64.parse(i)});return CryptoJS.AES.decrypt(o,n,{iv:CryptoJS.enc.Hex.parse(e)}).toString(CryptoJS.enc.Utf8)};

!function(e){"function"==typeof define&&define.amd?define(["jquery"],e):"object"==typeof exports?e(require("jquery")):e(jQuery)}(function(e){var n=/\+/g;function o(e){return t.raw?e:encodeURIComponent(e)}function i(e){return o(t.json?JSON.stringify(e):String(e))}function r(o,i){var r=t.raw?o:function(e){0===e.indexOf('"')&&(e=e.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,"\\"));try{return e=decodeURIComponent(e.replace(n," ")),t.json?JSON.parse(e):e}catch(e){}}(o);return e.isFunction(i)?i(r):r}var t=e.cookie=function(n,c,u){if(void 0!==c&&!e.isFunction(c)){if("number"==typeof(u=e.extend({},t.defaults,u)).expires){var a=u.expires,d=u.expires=new Date;d.setTime(+d+864e5*a)}return document.cookie=[o(n),"=",i(c),u.expires?"; expires="+u.expires.toUTCString():"",u.path?"; path="+u.path:"",u.domain?"; domain="+u.domain:"",u.secure?"; secure":""].join("")}for(var f,p=n?void 0:{},s=document.cookie?document.cookie.split("; "):[],m=0,v=s.length;m<v;m++){var x=s[m].split("="),k=(f=x.shift(),t.raw?f:decodeURIComponent(f)),l=x.join("=");if(n&&n===k){p=r(l,c);break}n||void 0===(l=r(l))||(p[k]=l)}return p};t.defaults={},e.removeCookie=function(n,o){return void 0!==e.cookie(n)&&(e.cookie(n,"",e.extend({},o,{expires:-1})),!e.cookie(n))}});
var customerId;
var verifiedId = '';
var aesUtil = undefined;
'use strict';
addPageScript(function(){

	if(ks != undefined && ic != undefined){
	     aesUtil = new AesUtil(ks, ic);
	}
	sessionId = getSessionId(sessionId);
	isSuccess = getSuccess(isSuccess);
	if(isSuccess == 'success'){
		redirectToLogin();
	}
	
    $(function() {
        	
        $("#customerReg-form").validate({
        	 ignore: '.ignore, .select2-input',
             focusInvalid: false,
             rules: {
                 /*'loginId': {
                    required: true,
                    minlength: 5,
                    maxlength: 11,
                    alphanumeric: true
                },*/
               'firstName': {
                    required: true,
                    maxlength: 30,
                    nameRegex:true
                },
                'lastName': {
                    required: true,
                    maxlength: 30,
                    nameRegex:true
                },
                'middleName': {
                    maxlength: 30,
                    nameRegex:true
                },
                'emailId': {
                    required: true,
                    maxlength: 50,
                    email: true
                },
                'mobileNumber': {
                	required: true,
                    minlength: 10,
                    maxlength: 10,
                    number: true
                },
                 'loyaltyCardNumber':{
	                required:false,
                    minlength: 16,
                    maxlength: 16,
                    number: true
},
             },
             
        	errorPlacement: function errorPlacement(error, element) {

                var $parent = $(element).parents('.form-group');

                // Do not duplicate errors
                if ($parent.find('.jquery-validation-error').length) {
                    return;
                }

                $parent.append(
                    error.addClass('jquery-validation-error small form-text invalid-feedback')
                );
            },
            highlight: function(element) {
                var $el = $(element);
                var $parent = $el.parents('.form-group');

                $el.addClass('is-invalid');

                $el.next().find(".select2-selection").css("border", "1px solid #FF5370");

                // Select2 and Tagsinput
                if ($el.hasClass('select2-hidden-accessible') || $el.attr('data-role') === 'tagsinput') {
                    $el.parent().addClass('is-invalid');
                }
            },
            unhighlight: function(element) {
                $(element).parents('.form-group').find('.is-invalid').removeClass('is-invalid');
            },
        });
        	 
    });
});

function isExistsLoginId(loginInputId, userLoginId){
     var loginId = $("#"+loginInputId).val();
     if(loginId != undefined && loginId != "" && loginId != userLoginId){
         $.ajax({
            type: "POST",
            url: basepath+"/rest/ajax/isExistsLoginId?loginId="+loginId,
            success: function (data) {
                  if(data != undefined && data != "false"){
                      $("#"+loginInputId).val('');
                      showErrMessage('Login Id Already Exists!!', loginInputId);
                  } 
            }
         });
    }
}

function sendMobileVerificationCode(mobileNumber, userType, type, modalId){
    var mobileNumber = $("#"+mobileNumber).val();
    if(mobileNumber != undefined && mobileNumber != ""){
    	$("#mobileVerificationModalTitle").html(type + " Verification");
    	$('#'+modalId).modal({
              show: true
        });
	    $.ajax({
		    type: "POST",
		    url: basepath+"/rest/ajax/sendMobileVerificationCode?mobileNumber="+mobileNumber+"&userType="+userType,
		    success: function (data) {
		          if(data != undefined && data != ""){
		          	  verifiedId = data;
		          } 
		    }
		});
	}
}

function verifyMobileNumber(mobileNumber, mobileValue){
    var mobile = $("#"+mobileNumber).val();
    if(mobile != undefined &&  mobile != "" && validateMobile(mobile)){
    	  if(mobileValue != undefined && mobileValue == mobile){
              $(".verifyMobileBtn").attr("disabled", true);
              $("#mobileVerifiedId").val("1");
              return false;
    	  }else{
    	  	  $("#mobileVerifiedId").val("");
    	  	  $(".verifyMobileBtn").attr("disabled", false);
	          return true;
    	  }
    }
    $(".verifyMobileBtn").attr("disabled", true);
    return false;
}

function validateMobile($mobile) {
    var emailReg = /^[0][1-9]\d{9}$|^[1-9]\d{9}$/;
    return emailReg.test( $mobile );
}

function mobileCodeVerify(verifiedInputId, verificationCode, msgId, modalId){
    var verificationCodeValue = $("#"+verificationCode).val();
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
	            	 $(".VerifyMobileBtn").html("<i class='fas fa-check-double VerifyCheck' aria-hidden='true'></i>");
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

function getSessionId(sessionId){
    if(sessionId != undefined && sessionId.length > 32){
   	   var id = sessionId.substr(0,32);
   	   return id;
    }
    return sessionId;
}

function getSuccess(isSuccess){
    if(isSuccess != undefined){
   	   var success = isSuccess;
   	   return success;
    }
    return isSuccess;
}

function validateCustomerForm(){
    var verifiedId = $("#verifiedId").val();
    if(verifiedId != undefined && verifiedId != ""){
     	 return true;
    }
    $(".imageERRMsg").html("Please Verify Mobile Number");
    $(".imageERRMsg").show();
    setTimeout(function(){$(".imageERRMsg").hide();}, 3000);
    return false;
}

function redirectToLogin(){
    swal({
            title: "Good job!",
            text: "Registration is Successful. Please Check Your Mail for Login Details.",
            icon: "success",
            dangerMode: true,
        })
        .then((willDelete) => {
            if (willDelete) {
            	location.href = basepath;
            }
  });
}

function searchCustomer(mobileNumber){
	var mobileNumber = $("#"+mobileNumber).val();
	if(mobileNumber != undefined && mobileNumber != ""){
	    var response = $.ajax({type: 'POST', url: basepath+"/customer/byMobileNumber?mobileNumber="+mobileNumber, async: false}).responseText;
	    if(response != undefined && response != ""){   
	    	$("#customerData").html(response);
		}else{
			$(".mobileERRMsg").html("Records not found");
		    $(".mobileERRMsg").show();
		    setTimeout(function(){$(".mobileERRMsg").hide();}, 3000);
		}
	}else{
		$(".mobileERRMsg").html("Please Enter Mobile Number");
	    $(".mobileERRMsg").show();
	    setTimeout(function(){$(".mobileERRMsg").hide();}, 3000);
	}
}    


function popup(customerId,loginId,name,emailId,mobileNumber,walletBalance,cId){
	
      this.customerId=cId;
	  $('document').ready(function() {
	           var x = customerId ; // use this or try with static value like var x = "23";
	            var y=loginId;
	            var z=name;
	            var p=emailId;
              var q=mobileNumber;
              var r=walletBalance;
            
	            $("#customerId").text(x);
	            $("#loginId").text(y);
	            $("#name").text(z);
	            $("#emailId").text(p);
	            $("#mobileNumber").text(q);
				$("#walletBalance").text(r);

	
	    });
 }
 function acceptNotification(loyaltyCardNumber,merchantId){
		var loyaltyCardNumber = $("#"+loyaltyCardNumber).val();
		var merchantId = $("#"+merchantId).val();
	    this.customerId
  		var response = $.ajax({type: 'POST', url: basepath+"/customer/assignLoayltyNumber?id="+ this.customerId+"&mId="+merchantId+"&loyaltyCardNumber="+loyaltyCardNumber, async: false}).responseText;
if(response == 'Number Assigned Successfully'){
		         var message = "Number Assigned Successfully!!";
		         successMessage(message);
		    }else if(response == 'Number Already Assigned'){
		    	 var message = "Number Already Assigned!!";
		    	 errorMessage(message);
		    }else if(response == 'Invalid Number'){
		    	 var message = "Invalid Number!!";
		    	 errorMessage(message);
		    }else{
		    	 var message = "Error in LoyaltyCardNumber Assignment!!";
		    	 errorMessage(message);
		    }

	}
function successMessage(message){
    swal({
            title: "Good job!",
            text: message,
            icon: "success",
            dangerMode: true,
        })
        .then((willDelete) => {
            if (willDelete) {
            	location.href = basepath+"/customer/view";
            }
  });
}
function errorMessage(message){
        swal({
                title: "Oh snap!",
                text: message,
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })


}
