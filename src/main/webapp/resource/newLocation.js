var map = null;
var geocoder = null;
var marker;

newLocation = {
    initialize: function initialize() {
        if (GBrowserIsCompatible()) {
            console.info("initialising myGoogleMaps");
            map = new GMap2(document.getElementById("map_canvas"));
            console.debug(map);
            map.setCenter(new GLatLng(37.4419, -122.1419), 1);
            map.setUIToDefault();
            geocoder = new GClientGeocoder();
        }
    },

    showAddress: function showAddress() {
        var address = $('.locationAddress').val()
        if (geocoder) {
            geocoder.getLatLng(
                    address,
                    function(point) {
                        if (!point) {
                            alert(address + " not found");
                        } else {
                            map.setCenter(point, 15);
                            marker = new GMarker(point, {draggable: true});
                            map.addOverlay(marker);
                            GEvent.addListener(marker, "dragend", function() {
                                marker.openInfoWindowHtml(marker.getLatLng().toUrlValue(6));
                            });
                            GEvent.addListener(marker, "click", function() {
                                marker.openInfoWindowHtml(marker.getLatLng().toUrlValue(6));
                            });
                            GEvent.trigger(marker, "click");
                            $('.newEventForm .latitude').val(point.lat())
                            $('.newEventForm .longitude').val(point.lng())
                        }
                    }
                    );
        }
    }

}

$(newLocation.initialize);
