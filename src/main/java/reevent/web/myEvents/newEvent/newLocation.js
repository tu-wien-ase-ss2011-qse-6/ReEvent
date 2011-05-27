var map = null;
var geocoder = null;

newLocation = {
    initialize: function initialize() {
        if (GBrowserIsCompatible()) {
            map = new GMap2(document.getElementById("map_canvas"));
            map.setCenter(new GLatLng(37.4419, -122.1419), 1);
            map.setUIToDefault();
            geocoder = new GClientGeocoder();
        }
    },

    showAddress: function showAddress(address) {
        if (geocoder) {
            geocoder.getLatLng(
                    address,
                    function(point) {
                        if (!point) {
                            alert(address + " not found");
                        } else {
                            map.setCenter(point, 15);
                            var marker = new GMarker(point, {draggable: true});
                            map.addOverlay(marker);
                            GEvent.addListener(marker, "dragend", function() {
                                marker.openInfoWindowHtml(marker.getLatLng().toUrlValue(6));
                            });
                            GEvent.addListener(marker, "click", function() {
                                marker.openInfoWindowHtml(marker.getLatLng().toUrlValue(6));
                            });
                            GEvent.trigger(marker, "click");
                        }
                    }
                    );
        }
    }

}

console.log("initialising myGoogleMaps");
$(newLocation.initialize);
