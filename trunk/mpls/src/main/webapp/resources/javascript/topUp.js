'use strict';
addPageScript(function(){

    $(function() {
        	
        $("#topUp-form").validate({
        	 ignore: '.ignore, .select2-input',
             focusInvalid: false,
             rules: {
                 'amount': {
                    required: true,
                    minlength: 1,
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