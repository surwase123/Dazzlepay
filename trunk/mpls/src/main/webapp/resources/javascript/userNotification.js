addPageScript(function(){
	if(recordId != "" && tableName != "" && id != "" && menuName != "" && creatorId != "" && recordId != 0){
		 approveUserNotification(recordId, tableName, menuName, id, creatorId);
	 }
});

function approveRecord(requestType, id, comments,tableName,menuName,recordId,creatorId){
		var id = $('#'+id).val();
		var comments = $('#'+comments).val();
		var tableName = $('#'+tableName).val();
		var menuName = $('#'+menuName).val();
		var recordId = $('#'+recordId).val();
		var creatorId = $('#'+creatorId).val();
		if(comments != ""){
		    var response = $.ajax({type: 'POST', url: basepath+"/add/user/approveNotificationRecord?requestType="+requestType+"&id="+id+"&comments="+comments+"&tableName="+tableName+"&menuName="+menuName+"&recordId="+recordId+"&creatorId="+creatorId, async: false}).responseText;
		    if(response == 'approve'){
		    	 $(".msgResponse").addClass("alert-success");
		         $(".msgResponse").html("<strong>Well done!</strong> User Notification Request is Approved!!");
		         $(".msgResponse").show();
		    }else if(response == 'reject'){
		    	 $(".msgResponse").addClass("alert-success");
		         $(".msgResponse").html("<strong>Well done!</strong> User Notification Request is Rejected!!");
		         $(".msgResponse").show();
		    }else{
		    	 $(".msgResponse").addClass("alert-danger");
		         $(".msgResponse").html("<strong>Oh snap!</strong> Error in "+requestType+" User Notitification!!");
		         $(".msgResponse").show();
		    }
		    setTimeout(function(){$(".msgResponse").hide();}, 1500);
		    setTimeout(function(){ window.location.href= basepath+"/add/user/notification"; }, 2000);
		}else{
			 $(".msgResponse").addClass("alert-danger");
	         $(".msgResponse").html("Please Add Comments Before Submit!!");
	         $(".msgResponse").show();
		}
}

function trashNotification(requestType, id, tableName, menuName, recordId, creatorId){
        swal({
                title: "Are you sure?",
                text: "Once deleted, you will not be able to recover this record!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
            .then((willDelete) => {
                if (willDelete) {
                     deleteRecords(requestType, id, tableName, menuName, recordId, creatorId);
                }
      });
}

function deleteRecords(requestType, id, tableName, menuName, recordId, creatorId){
	     var comments = "Trash Notification"
	     var response = $.ajax({type: 'POST', url: basepath+"/add/user/approveNotificationRecord?comments="+comments+"&requestType="+requestType+"&id="+id+"&tableName="+tableName+"&menuName="+menuName+"&recordId="+recordId+"&creatorId="+creatorId, async: false}).responseText;
         window.location.href= basepath+"/add/user/notification";
}

function updateUserProfile(userId, groupType){
		var url = basepath+"/customer/update-profile";
	    var jsonParam = [];
	    jsonParam.push("userId");
	    jsonParam.push("groupType");
	    var jsonParamValue = [];
	    jsonParamValue.push(userId);
	    jsonParamValue.push(groupType);
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
