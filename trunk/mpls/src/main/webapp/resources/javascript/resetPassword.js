!function(e){"function"==typeof define&&define.amd?define(["jquery"],e):"object"==typeof exports?e(require("jquery")):e(jQuery)}(function(e){var n=/\+/g;function o(e){return t.raw?e:encodeURIComponent(e)}function i(e){return o(t.json?JSON.stringify(e):String(e))}function r(o,i){var r=t.raw?o:function(e){0===e.indexOf('"')&&(e=e.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,"\\"));try{return e=decodeURIComponent(e.replace(n," ")),t.json?JSON.parse(e):e}catch(e){}}(o);return e.isFunction(i)?i(r):r}var t=e.cookie=function(n,c,u){if(void 0!==c&&!e.isFunction(c)){if("number"==typeof(u=e.extend({},t.defaults,u)).expires){var a=u.expires,d=u.expires=new Date;d.setTime(+d+864e5*a)}return document.cookie=[o(n),"=",i(c),u.expires?"; expires="+u.expires.toUTCString():"",u.path?"; path="+u.path:"",u.domain?"; domain="+u.domain:"",u.secure?"; secure":""].join("")}for(var f,p=n?void 0:{},s=document.cookie?document.cookie.split("; "):[],m=0,v=s.length;m<v;m++){var x=s[m].split("="),k=(f=x.shift(),t.raw?f:decodeURIComponent(f)),l=x.join("=");if(n&&n===k){p=r(l,c);break}n||void 0===(l=r(l))||(p[k]=l)}return p};t.defaults={},e.removeCookie=function(n,o){return void 0!==e.cookie(n)&&(e.cookie(n,"",e.extend({},o,{expires:-1})),!e.cookie(n))}});

'use strict';
addPageScript(function(){

    $(function() {
        $('#resetPassword-form').validate({
            ignore: '.ignore, .select2-input',
            focusInvalid: false,
            rules: {
                'userType': {
                    required: true,
                },
                merchantSearchBy: {required: '#merchant:checked'},
                customerSearchBy: {required: '#customer:checked'},
            'mobileNumber': {
            	required: true,
                minlength: 10,
                maxlength: 10,
                number: true
            },
            },
            // Errors //

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

                // Select2 and Tagsinput
                if ($el.hasClass('select2-hidden-accessible') || $el.attr('data-role') === 'tagsinput') {
                    $el.parent().addClass('is-invalid');
                }
            },
            unhighlight: function(element) {
                $(element).parents('.form-group').find('.is-invalid').removeClass('is-invalid');
            }
        });
    });
});


function resetPassword(loginId){

       var response = $.ajax({type: 'POST', url: basepath+"/user/rest/reset-password-mail?loginId="+loginId, async: false}).responseText;
       if(response != undefined && response == "success"){
       	    $(".loginMessage").css("color", "green");
            $(".loginMessage").html("Reset password Email Sent Successfully");
            redirectToLogin();
            $(".loginMessage").show();
	        setTimeout(function(){$(".loginMessage").hide();}, 1500);
       }else if(response != undefined && response == "isNotExist"){
       	    $(".loginMessage").css("color", "red");
            $(".loginMessage").html("User does not exist. Please Enter valid Login Id");
       }else{
       	    $(".loginMessage").css("color", "red");
       	    $(".loginMessage").html("Error in Reset Password Mail");
       }
}

function redirectToLogin(){
    swal({
            title: "Good job!",
            text: "Reset password Email Sent Successfully on Registered Email Id",
            icon: "success",
            dangerMode: true,
        })
}

