'use strict';
addPageScript(function(){

     $('#multi-colum-dt-user').DataTable({
            columnDefs: [{
                targets: [0],
                orderData: [0, 1]
            }, {
                targets: [1],
                orderData: [1, 0]
            }, {
                targets: [4],
                orderData: [4, 0]
            }]
    });

    $(function() {

    	$('#groupId').change(function() {
    		displayMerchant($(this).val());
		})
		
		$('#merchantIdDiv').hide();
    	
    	
        $.validator.addMethod("passwordCharacteristics", function(value, elem, param) {
           return $(".passwordCharacteristics:checkbox:checked").length > 0;
        },"You must select at least one!");

        $('#user-form').validate({
            ignore: '.ignore, .select2-input',
            focusInvalid: false,
            rules: {
                'systemId': {
                    required: true
                },
                'groupId': {
                    required: true,
                },
                'loginId': {
                    required: true,
                    minlength: 5,
                    maxlength: 11,
                    alphanumeric: true
                },
                'domainUserName': {
                    minlength: 5,
                    maxlength: 13,
                    alphanumeric: true
                },
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

function deleteUser(id, deletedUser){
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
	var url = basepath+"/add/user/delete";
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


function updateUser(loginId, updatedby){
            $("#updatedBy").val(updatedby);
            var url = basepath+"/add/user/update";
            var jsonParam = [];
            jsonParam.push("loginId");
            var jsonParamValue = [];
            jsonParamValue.push(loginId);
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

function userHistoryDetails(loginId){
      var response = $.ajax({type: 'POST', url: basepath+"/userReport/getUserHistory/"+loginId, async: false}).responseText;
      if(response != undefined){
            $("#UserHistoryDetailTable").html(response);
            $("#UserHistoryDetailTable").show();
            if(loginId != undefined){
                $("#userHistoryModalTitle").html("User History - "+loginId);
            }
            $('#userHistoryModal').modal({
                show: true
            }); 
            exportDataTable('multi-colum-dt-detail');
      }
}

function displayMerchant(groupID) {
	var response = $.ajax({type: 'POST', url: basepath+"/user/rest/checkUserGroupType?groupID="+groupID, async: false}).responseText;
    if(response != undefined && response == "success"){
    	$('#merchantIdDiv').show();
    	
    }else {
    	$('#merchantIdDiv').hide();
    }
}
