var map = null;
var geocoder = null;
var marker;

detailLocation = {
    initialize: function initialize() {
        if (GBrowserIsCompatible()) {
            console.info("initialising myGoogleMaps");
            map = new GMap2(document.getElementById("map_canvas"));
            console.debug(map);
            
            pointOnMap = new GLatLng($('.event-location').data('lat'), $('.event-location').data('lng'), true);
            map.setCenter(pointOnMap, 15);
            marker = new GMarker(pointOnMap, {draggable: false});
            map.addOverlay(marker);
            
            
        }
    }

   

}

$(detailLocation.initialize);
