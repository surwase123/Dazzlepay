'use strict';
addPageScript(function(){

    $(function() {

        $('#merchantEmployee-form').validate({
            ignore: '.ignore, .select2-input',
            focusInvalid: false,
            rules: {
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

function deleteMerchantEmployee(id, deletedUser){
        swal({
                title: "Are you sure?",
                text: "Once deleted, you will not be able to recover this record!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
            .then((willDelete) => {
                if (willDelete) {
                     deleteRecords(id, deletedUser);
                }
      });
}

function deleteRecords(id, deletedUser){
	var url = basepath+"/merchant/employee/delete";
      var jsonParam = [];
      jsonParam.push("id");
      jsonParam.push("updatedBy");
      var jsonParamValue = [];
      jsonParamValue.push(id);
      jsonParamValue.push(deletedUser);
      var values = '';
      var d = jsonParam.length;
      for (var b = 0; b < d; b++) {
          if(values == ""){
               values = jsonParam[b]+"="+jsonParamValue[b];
           }else{
                values += "&"+jsonParam[b]+"="+encodeURIComponent(jsonParamValue[b]);
           }
      }
      multipleDataPostRedirect(url, jsonParam, jsonParamValue, "POST");
}


function updateMerchantEmployee(id, updatedby){
            $("#updatedBy").val(updatedby);
            var url = basepath+"/merchant/employee/update";
            var jsonParam = [];
            jsonParam.push("id");
            var jsonParamValue = [];
            jsonParamValue.push(id);
            var values = '';
            var d = jsonParam.length;
            for (var b = 0; b < d; b++) {
                if(values == ""){
                     values = jsonParam[b]+"="+jsonParamValue[b];
                 }else{
                      values += "&"+jsonParam[b]+"="+encodeURIComponent(jsonParamValue[b]);
                 }
            }
            multipleDataPostRedirect(url, jsonParam, jsonParamValue, "GET");
}

function isExistsMerchantMobileNumber(mobileInputId, mobileNumberValue){
     var mobileNumber = $("#"+mobileInputId).val();
     if(mobileNumber != undefined && mobileNumber != "" && mobileNumber != mobileNumberValue){
         $.ajax({
            type: "POST",
            url: basepath+"/merchant/employee/isExistsMobileNumber?mobileNumber="+mobileNumber,
            success: function (data) {
                if(data != undefined && data != "false"){
                     $("#"+mobileInputId).val('');
                     if(data == 'true'){
                        showErrMessage('Mobile Number Already Registered', mobileInputId);
                     }
                }
            }
         });
    }
}


function isExistsMerchantEmailId(emailInputId, emailIdValue){
     var emailId = $("#"+emailInputId).val();
     if(emailId != undefined && emailId != "" && emailId != emailIdValue){
         $.ajax({
            type: "POST",
            url: basepath+"/merchant/employee/isExistsEmailId?emailId="+emailId,
            success: function (data) {
                if(data != undefined && data != "false"){
                   $("#"+emailInputId).val('');
                   if(data == 'true'){
                       showErrMessage('Email Id Already Registered', emailInputId);
                   }
                }
            }
         });
    }
}