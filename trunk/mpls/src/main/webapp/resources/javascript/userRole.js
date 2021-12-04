'use strict';
addPageScript(function(){
    if(action == 'update'){
            getGroupInfo('groupId','accordionExample');
    }

    $('#multi-colum-role-table').DataTable({
            columnDefs: [{
                targets: [0],
                orderData: [0, 1]
            }, {
                targets: [1],
                orderData: [1, 0]
            }, {
                targets: [2],
                orderData: [2, 0]
            }]
    });

    $(function() {

        $.validator.addMethod("passwordCharacteristics", function(value, elem, param) {
           return $(".passwordCharacteristics:checkbox:checked").length > 0;
        },"You must select at least one!");

        $('#userRole-form').validate({
            ignore: '.ignore, .select2-input',
            focusInvalid: false,
            rules: {
                'groupId': {
                    required: true
                },
                'groupPrivilegeMenu': {
                    required : true
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

function removeDisabled(divId, currentCheckBoxboxId){
        if($("#"+currentCheckBoxboxId).is(":checked")){
              $("#"+divId+" input:checkbox").each(function () {
                    $(this).attr("disabled", false);
              });
        }else{
             $("#"+divId+" input:checkbox").each(function () {
                    $(this).attr("disabled", true);
             });
        }
}

function deleteUserRole(groupId, deletedUser){
        swal({
                title: "Are you sure?",
                text: "Once deleted, you will not be able to recover this record!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
            .then((willDelete) => {
                if (willDelete) {
                     deleteRecords(groupId, deletedUser);
                }
      });
}

function deleteRecords(groupId, deletedUser){
      var url = basepath+"/user/role/delete";
      var jsonParam = [];
      jsonParam.push("groupId");
      jsonParam.push("updatedBy");
      var jsonParamValue = [];
      jsonParamValue.push(groupId);
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


function updateUserRole(groupId, updatedby){
            $("#updatedBy").val(updatedby);
            var url = basepath+"/user/role/update";
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

function getGroupInfo(groupId, accordionId){
        var groupValue = $("#"+groupId).val();
        if(groupValue != undefined && groupValue != ""){
              $.ajax({
                type: "POST",
                url: basepath+"/rest/ajax/getGroupInfo/"+groupValue,
                success: function (data) {
                         var groupType = data.groupType;
                         $("#"+accordionId).find("input[type=checkbox]").each(function() {
                              var id = $(this).attr("id");
                              if(groupType != undefined && groupType == "maker"){
                                    if(id.indexOf("isAdd") != -1){
                                           $("."+id).addClass("d-inline").show();
                                    }else if(id.indexOf("isUpdate") != -1){
                                           $("."+id).removeClass("d-inline").hide();
                                    }else if(id.indexOf("isDelete") != -1){
                                          $("."+id).removeClass("d-inline").hide();
                                    }
                              }else if(groupType != undefined && groupType == "checker"){
                                    if(id.indexOf("isAdd") != -1){
                                            $("."+id).removeClass("d-inline").hide();
                                    }else if(id.indexOf("isUpdate") != -1){
                                            $("."+id).removeClass("d-inline").hide();
                                    }else if(id.indexOf("isDelete") != -1){
                                            $("."+id).removeClass("d-inline").hide();
                                    }
                              }else if(groupType != undefined && groupType == "admin"){
                                    if(id.indexOf("isAdd") != -1){
                                           $("."+id).addClass("d-inline").show();
                                    }else if(id.indexOf("isUpdate") != -1){
                                           $("."+id).addClass("d-inline").show();
                                    }else if(id.indexOf("isDelete") != -1){
                                           $("."+id).addClass("d-inline").show();
                                    }
                              }
                        }); 

                    }
              });
        }
}
