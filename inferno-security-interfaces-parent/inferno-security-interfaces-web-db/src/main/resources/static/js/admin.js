/**
 * Project: inferno-security-interfaces-web-db Package: File: admin.js Type:
 * admin Created Date: 2018-03-27 23:43:35 Created By: Łukasz Przesmycki
 * (lukasz.przesmycki@gmail.com)
 */
class Admin {
    init() {
	$( '.select-element' ).prop('checked', false);	
	$( '#checkAll' ).on('click', function() {
	    if ($('#checkAll').is(':checked')) {
		$( '.select-element' ).prop('checked', true);
	    } else {
		$( '.select-element' ).prop('checked', false);
	    }	    
	});
	$('.select-element').on('change', function() {

	    $('#checkAll').prop('checked', false);
        });
	var $form = $('#userListForm');
	var action = 'list';
	$form.submit(function(event) {
	    console.log('Submit EVENT: ' + event.target.id);
	    var val = $("input[type=submit][clicked=true]").val();
	    var $button = $(event.target);
	    console.log('Submit button caused action: ' + val);
	    var $idField = $button.parent('div').find('input[class="user-id"]');
	    console.log('Submitted ID: ' + $idField.val());
	    console.log("Action URL: " + $form.attr('action'));
	    $form.attr('action', $form.attr('action') + '&id=' + $idField.val() + '&action=' + val);
	    return;
        });
	$("form input[type=submit]").click(function() {
	        $("input[type=submit]", $(this).parents("form")).removeAttr("clicked");
	        $(this).attr("clicked", "true");
	    });
	var $roleForm = $('#rolesListForm');
	$('#rolesListForm').submit(function(e) {
	  // prevent Default functionality
// e.preventDefault();
	    var hidden_fields = $( this ).find( 'input:hidden' );
	    console.log('Hidden val: ' + hidden_fields.val());

	        // get the action-url of the form
	        var actionurl = e.currentTarget.action;
	        console.log('ActionURL: ' + actionurl);
	        var val = $("button[type=submit][clicked=true]").val();
	        console.log('Submit button value: ' + val);

	    var $button = $(e.target);
	    console.log('Submit button caused action: ' + $button.val());
	    var $idField = $button.parent('div').find('input[class="role-id"]');
	    console.log('Submitted ID: ' + $idField.val());
	    console.log('th:action::before = ' + $roleForm.attr('action'));
	    $roleForm.attr('action', $roleForm.attr('action') + '&id=' + $idField.val());
	    console.log('th:action::after = ' + $roleForm.attr('action'));
	    return;
        });
// $('button[type="submit"][value="remove"]').on('click', function(event) {
// $('#').on('click', function(event) {
// var $idField = $(this).parent('div').find('input');
// console.log('th:action::before = ' + $form.attr('action'));
// $form.attr('action', $form.attr('action') + '&id=' + $idField.val());
// console.log('th:action::after = ' + $form.attr('action'));
// return;
// });
	    
    }
}

let admin = new Admin();
admin.init();