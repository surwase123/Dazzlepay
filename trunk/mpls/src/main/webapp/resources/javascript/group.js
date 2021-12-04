'use strict';
addPageScript(function(){

    $(function() {

        $.validator.addMethod("passwordCharacteristics", function(value, elem, param) {
           return $(".passwordCharacteristics:checkbox:checked").length > 0;
        },"You must select at least one!");

        $('#group-form').validate({
            ignore: '.ignore, .select2-input',
            focusInvalid: false,
            rules: {
                'systemId': {
                    required: true
                },
                'groupId': {
                    required: true,
                    minlength: 3,
                    maxlength: 10,
                    alphanumeric: true
                },
                'groupName': {
                    required: true,
                    maxlength: 20,
                    nameRegex:true
                },
                'groupType': {
                    required: true,
                },
                'minPassLength': {
                    required: true,
                    maxlength: 15,
                    numbers:  true,
                    min: function () {
                        return 1;
                    },
                    max: function () {
                        return 15;
                    }

                },
                'maxPassLength': {
                    required: true,
                    maxlength: 15,
                    numbers:  true,
                    min: function () {
                        return 0;
                    },
                    max: function () {
                        return 30;
                    }
                },
                'passwordCharacteristics': {
                     passwordCharacteristics: true
                },
                'passExpiryDays': {
                    required: true,
                    maxlength: 3,
                    numbers:  true,
                    min: function () {
                        return 0;
                    }
                },
                'passHistoryChecks': {
                    required: true,
                    maxlength: 1,
                    numbers:  true,
                    max: function () {
                        return 5;
                    },
                    min: function () {
                        return 0;
                    }
                },
                'maxConcurrentLogin': {
                    required: true,
                    maxlength: 2,
                    numbers:  true,
                    min: function () {
                        return 0;
                    },
                    max: function () {
                        return 5;
                    }
                },
                'maxLoginRetries': {
                    required: true,
                    maxlength: 2,
                    numbers:  true,
                    min: function () {
                        return 1;
                    },
                    max: function () {
                        return 5;
                    }
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


function deleteGroup(id, deletedUser){
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
	  var url = basepath+"/group/delete";
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


function updateGroup(groupId, processorId, updatedby){
            $("#updatedBy").val(updatedby);
            var url = basepath+"/group/update";
            var jsonParam = [];
            jsonParam.push("groupId");
            var jsonParamValue = [];
            jsonParamValue.push(groupId);
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
