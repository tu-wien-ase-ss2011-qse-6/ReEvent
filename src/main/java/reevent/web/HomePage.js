jQuery(function($) {
    function find_near(lat, lng) {
        $nearMe.find(':input.lat').val(lat)
        $nearMe.find(':input.lng').val(lng)
        $nearMe.find('form')[0].submit()
    }

    function on_geolocate(pos) {
        find_near(pos.coords.latitude, pos.coords.longitude)
    }

    function on_geolocate_error(err) {
        console.warn("Couldn't determine location")
        console.warn(err);
        alert("Couldn't determine your location.")
    }

    function on_geocode(results, status) {
        if (status != google.maps.GeocoderStatus.OK) {
            console.warn("Couldn't determine location")
            console.warn(results)
            console.warn(status)
        } else {
            var loc = results[0].geometry.location
            find_near(loc.lat(), loc.lng())
        }
    }

    var $nearMe = $('#near-me')

    if (Modernizr.geolocation) {
        $nearMe.find('a.geolocate').click(function() {
            navigator.geolocation.getCurrentPosition(on_geolocate, on_geolocate_error)
        })
    } else {
        $nearMe.find('span.geolocate').hide()
        $nearMe.find('span.or').hide()
    }

    if (window.google && google.maps) {
        var geocoder = new google.maps.Geocoder()
        $nearMe.find('span.geocode form').submit(function(evt) {
            var address = $nearMe.find('input.address').val()
            geocoder.geocode({
                    address: address
                }, on_geocode)
            evt.preventDefault()
        })
    } else {
        $nearMe.find('span.geocode').hide()
        $nearMe.find('span.or').hide()
    }

    if (!(Modernizr.geolocation || window.google || google.maps)) {
        $nearMe.hide();
    }
})