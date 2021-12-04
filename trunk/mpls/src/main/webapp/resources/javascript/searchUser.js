'use strict';
addPageScript(function(){

    showSearchParameter()

    $(function() {
        $('#blockUnblock-form').validate({
            ignore: '.ignore, .select2-input',
            focusInvalid: false,
            rules: {
                'searchValue': {
                    required: true,
                },
                merchantSearchBy: {required: '#merchant:checked'},
                customerSearchBy: {required: '#customer:checked'},
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


function updateCustomer(id, deletedUser,isActive, status){
	    swal({
                title: "Are you sure?",
                text: status +" this Customer",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
            .then((willDelete) => {
                if (willDelete) {
                	updateCustomerRecord(id, deletedUser,isActive, status);
                }
      });
}

function updateMerchant(id, deletedUser,isActive, status){
    swal({
            title: "Are you sure?",
            text: status +" this Merchant",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
        .then((willDelete) => {
            if (willDelete) {
            	updateMerchantRecord(id, deletedUser, isActive, status);
            }
  });
}

function unLockMerchant(id, deletedUser,isActive, status){
    swal({
            title: "Are you sure?",
            text: status +" this Merchant",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
        .then((willDelete) => {
            if (willDelete) {
            	unlockMerchantUser(id, deletedUser, isActive, status);
            }
  });
}

function unLockCustomer(id, deletedUser,isActive, status){
    swal({
            title: "Are you sure?",
            text: status +" this Customer",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
        .then((willDelete) => {
            if (willDelete) {
            	unlockCustomerUser(id, deletedUser, isActive, status);
            }
  });
}

function updateMerchantRecord(id, deletedUser, isActive, status){
      var url = basepath+"/searchUser/updateMerchant";
      var jsonParam = [];
      jsonParam.push("id");
      jsonParam.push("updatedby");
      jsonParam.push("isActive");
      jsonParam.push("operation");
      var jsonParamValue = [];
      jsonParamValue.push(id);
      jsonParamValue.push(deletedUser);
      jsonParamValue.push(isActive);
      jsonParamValue.push(status);
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

function updateCustomerRecord(id, deletedUser, isActive, status){
    var url = basepath+"/searchUser/updateCustomer";
    var jsonParam = [];
    jsonParam.push("id");
    jsonParam.push("updatedby");
    jsonParam.push("isActive");
    jsonParam.push("operation");
    var jsonParamValue = [];
    jsonParamValue.push(id);
    jsonParamValue.push(deletedUser);
    jsonParamValue.push(isActive);
    jsonParamValue.push(status);
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

function unlockMerchantUser(id, deletedUser, isActive, status){
    var url = basepath+"/searchUser/unlockMerchantUser";
    var jsonParam = [];
    jsonParam.push("id");
    jsonParam.push("updatedby");
    jsonParam.push("isActive");
    jsonParam.push("operation");
    var jsonParamValue = [];
    jsonParamValue.push(id);
    jsonParamValue.push(deletedUser);
    jsonParamValue.push(isActive);
    jsonParamValue.push(status);
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

function unlockCustomerUser(id, deletedUser, isActive, status){
    var url = basepath+"/searchUser/unlockCustomerUser";
    var jsonParam = [];
    jsonParam.push("id");
    jsonParam.push("updatedby");
    jsonParam.push("isActive");
    jsonParam.push("operation");
    var jsonParamValue = [];
    jsonParamValue.push(id);
    jsonParamValue.push(deletedUser);
    jsonParamValue.push(isActive);
    jsonParamValue.push(status);
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


function showSearchParameter(){
    	var value = $("input[name=userType]:checked").val();
        if(value =="merchant") {
            $(".merchant").removeClass("DispNone");
            $(".customer").addClass("DispNone");
        }else {
        	 $(".merchant").addClass("DispNone");
             $(".customer").removeClass("DispNone");
        } 
}
function myFunction(){
	 var x = document.getElementById("costPerUnit").value;

//	var y = document.getElementById("totalCost").value;
	
	 var z = document.getElementById("quantityOfCards").value;
	if(x>0 && z>0)
	{
	       var p =x*z;
	       document.getElementById("totalCost").value=p;
	 var x = document.getElementById("costPerUnit").value;
	}
//	if(y>0 && z>0)
//	{
//	var q=y/z;
//	document.getElementById("costPerUnit").value=q;
//	var y = document.getElementById("totalCost").value;
//	}
}
function paymentModeValue(){
	var x = document.getElementById("paymentMode").value;
	if(x=="Bank Transfer" || x=="UPI"){
		
		document.getElementById("refNumber").style.display = 'block';
	}
	if( x=="Cash"){
		document.getElementById("refNumber").style.display = 'none';
	}
	
}