var AesUtil=function(t,e){this.keySize=t/32,this.iterationCount=e};AesUtil.prototype.generateKey=function(t,e){return CryptoJS.PBKDF2(e,CryptoJS.enc.Hex.parse(t),{keySize:this.keySize,iterations:this.iterationCount})},AesUtil.prototype.encrypt=function(t,e,r,i){var n=this.generateKey(t,r);return CryptoJS.AES.encrypt(i,n,{iv:CryptoJS.enc.Hex.parse(e)}).ciphertext.toString(CryptoJS.enc.Base64)},AesUtil.prototype.decrypt=function(t,e,r,i){var n=this.generateKey(t,r),o=CryptoJS.lib.CipherParams.create({ciphertext:CryptoJS.enc.Base64.parse(i)});return CryptoJS.AES.decrypt(o,n,{iv:CryptoJS.enc.Hex.parse(e)}).toString(CryptoJS.enc.Utf8)};

var aesUtil = undefined;
addPageScript(function(){
        if(ks != undefined && ic != undefined){
             aesUtil = new AesUtil(ks, ic);
        }
        sessionId = getSessionId(sessionId);
});


'use strict';
addPageScript(function(){
    $(function() {
            $('#user-password-form').validate({
                ignore: '.ignore, .select2-input',
                focusInvalid: false,
                rules: {
                    'password': {
                        required: true
                    },
                    'confirmPassword': {
                        required: true,
                        equalTo: 'input[name="password"]'
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
                },
            });
        });
});


function validatePassword(){
       var password = $("#password").val();
       if(password.length > 0){
           var loginId = $("#loginId").val();
           var id = $("#id").val();
           var salt = sessionId;
           var iv = sessionId;
           var ciphertext = aesUtil.encrypt(salt, iv, loginId, password);  

           var response = $.ajax({type: 'POST', url: basepath+"/user/rest/validate/password", data: { loginId: loginId, id: id, password: ciphertext }, async: false}).responseText;
           if(response != undefined && response == "success"){
                    $(".errorMessage").removeClass("alert-danger").addClass("alert-success");
                    $(".errorMessage").html('<strong>Well done!</strong> Valid Password!!');
                    $(".errorMessage").show();
           }else if(response != undefined && response != "success"){
                    $("#password").val("");
                    $(".errorMessage").removeClass("alert-success").addClass("alert-danger");
                    $(".errorMessage").html("<strong>Oh snap!</strong> " + response);
                    $(".errorMessage").show();
                    $("#password").focus();
           }
       }

}

function changePassword(){
       var password = $("#password").val();
       var loginId = $("#loginId").val();
       var salt = sessionId;
       var iv = sessionId;
       var ciphertext = aesUtil.encrypt(salt, iv, loginId, password);  
       $("#password").val(ciphertext);
       $("#confirmPassword").val(ciphertext);
       return true;
}

function getSessionId(sessionId){
    if(sessionId != undefined && sessionId.length > 32){
       var id = sessionId.substr(0,32);
       return id;
    }
    return sessionId;
}
