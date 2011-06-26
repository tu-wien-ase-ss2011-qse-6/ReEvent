newLocation = {}

$(function() {
    var map = null;
    var geocoder = null;
    var marker;

    var $lat = $('.newEventForm .latitude')
    var $lng = $('.newEventForm .longitude')

    function readMarker() {
        marker.openInfoWindowHtml(marker.getLatLng().toUrlValue(6));
        $lat.val(marker.getLatLng().lat())
        $lng.val(marker.getLatLng().lng())
    }

    function replaceMarker(point) {
        if (!point) {
            alert(address + " not found");
        } else {
            if (marker != null) {
                map.removeOverlay(marker);
            }
            map.setCenter(point, 15);
            marker = new GMarker(point, {draggable: true});
            map.addOverlay(marker);
            GEvent.addListener(marker, "dragend", readMarker);
            GEvent.addListener(marker, "click", readMarker);
            readMarker();
        }
    }

    function showAddress() {
        var address = $('.locationAddress').val()
        if (geocoder) {
            geocoder.getLatLng(address, replaceMarker);
        }
    }
    newLocation.showAddress = showAddress


    if (GBrowserIsCompatible()) {
        console.info("initialising myGoogleMaps");
        map = new GMap2(document.getElementById("map_canvas"));
        console.debug(map);

        var lat = Number($lat.val())
        var lng = Number($lng.val())

        map.setUIToDefault();
        if (lat && lng) {
            var point = new GLatLng(lat, lng)
            map.setCenter(point, 1)
            replaceMarker(point)
        } else {
            map.setCenter(new GLatLng(37.4419, -122.1419), 1);
        }
        
        geocoder = new GClientGeocoder();
    }

})
