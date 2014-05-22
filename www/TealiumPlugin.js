// Tealium Phonegap Plugin
(function(cordova) {
    function Tealium() {
        return {
            EVENT_NAME_LINK: "link",
            EVENT_NAME_VIEW: "view",
           
            track: function(parameters, eventType, successCallback, failureCallback) {
                return cordova.exec(successCallback, failureCallback, 'TealiumPlugin', 'track', [parameters, eventType]);
            },
            addGlobalCustomData: function(parameters, successCallback, failureCallback) {
                return cordova.exec(successCallback, failureCallback, 'TealiumPlugin', 'addGlobalCustomData', [parameters]);
            },
            addCustomData: function(parameters, successCallback, failureCallback) {
                return cordova.exec(successCallback, failureCallback, 'TealiumPlugin', 'addCustomData', [parameters]);
            }
        };
    }

    Tealium.install = function() {
        if (!window.plugins) {
            window.plugins = {};
        }
        window.plugins.Tealium = new Tealium();
    };
    cordova.addConstructor(Tealium.install);
})(window.cordova || window.Cordova || window.PhoneGap);