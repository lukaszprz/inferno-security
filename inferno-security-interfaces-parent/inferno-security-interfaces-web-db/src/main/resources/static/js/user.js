/**
 * Project: inferno-security-interfaces-web-db Package: File: User.js Type: User
 * Created Date: 2018-03-24 01:27:46 Created By: Łukasz Przesmycki
 * (lukasz.przesmycki@gmail.com)
 */
class User {
    init() {
	$("#personAddressModal").on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var objectId = button.data('id');
		var form = $('#personAddressForm');
		var formUrl = form.attr('action');
		var formType = formUrl + '&type=' + objectId;
		var title = objectId.toLowerCase() + ' address type:';
		$('#personAddressModalTitle').text(title.charAt(0).toUpperCase() + title.slice(1));
		form.attr('action', formType);	
		var fields = [
		    {
			key: 'id',
			value: $('#idVal_' + objectId).text()
		    },
		    {
			key: 'street', 
			value: $('#streetVal_' + objectId).text()
		    }, 
		    {
			key: 'buildingNumber', 
			value: $('#buildingNumberVal_' + objectId).text()
		    }, 
		    {
			key: 'appartment',
			value: $('#appartmentVal_' + objectId).text()
		    }, 
		    {
			key: 'postCode', 
			value: $('#postCodeVal_' + objectId).text()
		    }, 
		    {
			key: 'city',
			value: $('#cityVal_' + objectId).text()
		    }, 
		    {
			key: 'district', 
			value:$('#districtVal_' + objectId).text()
		    }
		];
		$.map(fields, function(field) {
	           return field.value; 
                });	       
	       
	       $.each(fields, function(i, field) {
		   console.log(field);
		   $('#' + field.key).val(field.value);
	       });
	});
	$("#personAddressModal").on('hide.bs.modal', function(event) {
	    var form = $('#personAddressForm');		
	    form.attr('action', '/user/address?action=edit');		
	});
	$("#deleteAddressModal").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data('id');
	    var type = button.data('type');	 
	    console.log('ID: ' + id + ', type: ' + type);
	    
	    var form = $('#deleteAddressForm');
	    var formUrl = form.attr('action');
	    var formType = formUrl + '&id=' + id;

	    form.attr('action', formType);
	    var fields = [
		    {
			key: 'addressId',
			value: $('#idVal_' + type).text()
		    },
		    {
			key: 'addressType',
			value: $('#typeVal_' + type).text()
		    }
		    ];
	    $.map(fields, function(field) {
	           return field.value; 
            });	       
	       
	    $.each(fields, function(i, field) {
		   console.log(field);
		   $('#' + field.key).val(field.value);
	    });
	});
	$("#deleteAddressModal").on('hide.bs.modal', function(event) {
	    var form = $('#deleteAddressForm');		
	    form.attr('action', '/user/address?action=delete');		
	});
	
    }
}
    
let user = new User();
user.init();
