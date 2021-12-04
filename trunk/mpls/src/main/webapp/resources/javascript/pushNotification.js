
function approveRecord(id, cId, mId, customerNotificationId, notificationType,transactionType){
		if(notificationType != "" && cId != "" && mId != "" && customerNotificationId != "" && transactionType != "") {
		    var response = $.ajax({type: 'POST', url: basepath+"/add/user/approvePushNotification?id="+id+"&cId="+cId+"&mId="+mId+"&customerNotificationId="+customerNotificationId+"&notificationType="+notificationType+"&transactionType="+transactionType, async: false}).responseText;
		    if(response == 'approve'){
		         var message = "User Notification Request is Approved!!";
		         successMessage(message);
		    }else if(response == 'reject'){
		    	 var message = "User Notification Request is Rejected!!";
		    	 successMessage(message);
		    }else if(response == 'insufficient'){
		    	 var message = "InSufficient Funds in Wallet!!";
		    	 errorMessage(message);
		    }else{
		    	 var message = "Error in Update User Notitification!!";
		    	 errorMessage(message);
		    }
		}else{
			var message = "Error in Update User Notitification!!";
			errorMessage(message);
		}
}

function successMessage(message){
    swal({
            title: "Good job!",
            text: message,
            icon: "success",
            dangerMode: true,
        })
        .then((willDelete) => {
            if (willDelete) {
            	location.href = basepath+"/add/user/pushNotification";
            }
  });
}

function errorMessage(message){
        swal({
                title: "Oh snap!",
                text: message,
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

