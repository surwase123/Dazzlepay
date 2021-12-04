'use strict';
addPageScript(function(){
    $(function() {
        $('#category-form').validate({
            ignore: '.ignore, .select2-input',
            focusInvalid: false,
            rules: {
                'categoryCode': {
                    required: true,
                    minlength: 4,
                    maxlength: 10,
                    numbers:  true
                },
                'categoryName': {
                    required: true,
                    minlength: 3,
                    maxlength: 200,
                    nameRegexPercentage:true,
                },
                'description': {
                    required: true,
                    minlength: 5,
                    maxlength: 500,
                    nameRegexPercentage: true,
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


function deleteCategory(id, deletedUser){
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
      var url = basepath+"/category/delete";
      var jsonParam = [];
      jsonParam.push("id");
      jsonParam.push("updatedby");
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


function updateCategory(id, updatedby){
            $("#updatedBy").val(updatedby);
            var url = basepath+"/category/update";
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
