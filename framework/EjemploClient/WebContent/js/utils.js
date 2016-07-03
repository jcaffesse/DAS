jUtils = {
    
    executing: function(divId) {
        $('#' + divId).html("<img src=\"./img/iloader.gif\" border=\"0\"/>").show();
    },
    
    showing: function(divId, html) {
        $('#' + divId).html(html !== null ? html : '').show();
    },
    
    hiding: function(divId, clean) {
        clean = (clean === false ? false : true);
        $('#' + divId).hide();
        if(clean) {
            $('#' + divId).html('&nbsp;');
        }    
    },

    changeLang: function(filename, lang) {
        jQuery.i18n.properties({
            name: filename, 
            path: './js/properties/', 
            mode: 'map',
            language: lang
        });
    }    
};


