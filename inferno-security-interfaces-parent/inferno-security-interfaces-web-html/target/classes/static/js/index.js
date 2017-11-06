/**
 * Scripts for index.html
 */
$(document).ready(function() {
	$.getJSON('http://localhost:8001/api/user/name/inferno-admin', function(jd) {
        $('#content').html('<p> Name: ' + jd.username + '</p>');
//        $('#content').append('<p>Age : ' + jd.age + '</p>');
//        $('#content').append('<p> Sex: ' + jd.sex+ '</p>');
     });
});
