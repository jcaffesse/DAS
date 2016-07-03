$(function($){
	var monthNames      = $.i18n.prop('meses').split(',');
	var monthNamesShort = $.i18n.prop('meses3l').split(','); 
	var dayNames        = $.i18n.prop('dias').split(',');
	var dayNamesShort   = $.i18n.prop('dias3l').split(',');
	var dayNamesMin     = $.i18n.prop('dias2l').split(',');
	
    $.datepicker.regional['es'] = {
        closeText: $.i18n.prop('cerrar'),
        prevText: $.i18n.prop('textoPrev'),
        nextText: $.i18n.prop('textoSig'),
        currentText: $.i18n.prop('hoy'),
        monthNames: monthNames,
        monthNamesShort: monthNamesShort,
        dayNames: dayNames,
        dayNamesShort: dayNamesShort,
        dayNamesMin: dayNamesMin,
        weekHeader: 'Sm',
        dateFormat: 'dd/mm/yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''
    };
});