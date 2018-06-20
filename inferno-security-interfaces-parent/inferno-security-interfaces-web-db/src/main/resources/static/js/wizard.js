/**
 * Project: inferno-security-interfaces-web-db Package: File: wizard.js Type:
 * wizard Created Date: 2018-04-03 17:29:10 Created By: Łukasz Przesmycki
 * (lukasz.przesmycki@gmail.com)
 */
class Wizard {
    init() {
	$('.validFrom').datetimepicker({
            locale: 'pl',
            format: 'YYYY-MM-DD HH:mm:ss.SSS'
        });
	$('.validTo').datetimepicker({
            locale: 'pl',
            format: 'YYYY-MM-DD HH:mm:ss.SSS'
        });
    }
}
let wizard = new Wizard();
wizard.init();