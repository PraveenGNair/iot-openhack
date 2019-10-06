var stompClient = null;

function connect() {
    var socket = new SockJS('/iot');
    stompClient = Stomp.over(socket);
    stompClient.debug = null;
    stompClient.connect({}, function (frame) {

        stompClient.subscribe('/topic/vehicle', function (vehicle) {

            var dataList = vehicle.body;
            var resp=jQuery.parseJSON(dataList);

            var totalOutput='';
            var tdTotalOutput_1=''; var tdTotalOutput_2=''; var tdTotalOutput_3=''; var tdTotalOutput_4=''; var tdTotalOutput_5='';

            jQuery.each(resp, function(i,vh) {
                    totalOutput += "<tr class='clicked-tr'>" +
                        "<td>" + vh.vehicleId + "</td>" +
                        "<td>" + vh.vehicleType + "</td>" +
                        "<td>" + vh.routeId + "</td>" +
                        "<td>" + vh.latitude + "</td>" +
                        "<td>" + vh.longitude + "</td>" +
                        "<td  class='hide_td'>" + vh.temperature + "</td>" +
                        "<td  class='hide_td'>" + vh.assetCount + "</td>" +
                        "<td  class='hide_td'>" + vh.assetDetails + "</td>" +
                        "<td  class='hide_td'>" + vh.timestamp + "</td>" +
                        "<td  class='hide_td'>" + vh.speed + "</td>" +
                        "<td  class='hide_td'>" + vh.fuelLevel + "</td>" +
                        "<td>" + vh.startLocation + "</td>" +
                        "<td>" + vh.endLocation + "</td></tr>";

            });

            //$("#vehicle_1").html(tdTotalOutput);
            $("#vehicle_body").append(totalOutput);


        });
    });
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }

    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'vehicleId': $("#iotVehicleId").val()}));
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

        $("#vehicle_body").html("");
        $( "#connect" ).click(function() {
            connect();
            sendName();
        });

    $(document).on("click", ".clicked-tr" , function() {
        var $tds = $(this).find("td");    // Find the row

        var parsedAssets = $tds.eq(0).text();
        $("#vehicle_id").html("");
        $("#vehicle_id").html("<b>Vehicle ID:</b> "+ parsedAssets);

        var parsedAssets = $tds.eq(1).text();
        $("#type_id").html("");
        $("#type_id").html("<b>Vehicle Type:</b> "+ parsedAssets);

        var parsedAssets = $tds.eq(2).text();
        $("#route_id").html("");
        $("#route_id").html("<b>Route: </b>" + parsedAssets);

        var parsedAssets = $tds.eq(3).text();
        $("#lat_id").html("");
        $("#lat_id").html("<b>Latitude: </b>" + parsedAssets);

        var parsedAssets = $tds.eq(4).text();
        $("#lang_id").html("");
        $("#lang_id").html("<b>Longitude: </b>" + parsedAssets);

        var parsedAssets = $tds.eq(5).text();
        $("#temp_id").html("");
        $("#temp_id").html("<b>Temperature:</b>" + parsedAssets);

        var parsedAssets = $tds.eq(6).text();
        $("#count_id").html("");
        $("#count_id").html("<b>Asset Count: </b>" + parsedAssets);

        var parsedAssets = $tds.eq(7).text();
        $("#asset_details").html("");
        $("#asset_details").html(parsedAssets);

        var parsedAssets = $tds.eq(8).text();
        $("#time_id").html("");
        $("#time_id").html("<b>Timestamp:</b>" + parsedAssets);

        var parsedAssets = $tds.eq(9).text();
        $("#speed_id").html("");
        $("#speed_id").html("<b>Speed: </b>" + parsedAssets);

        var parsedAssets = $tds.eq(10).text();
        $("#fuel_id").html("");
        $("#fuel_id").html("<b>Fuel Level:</b> " + parsedAssets);

        var parsedAssets = $tds.eq(11).text();
        $("#start_id").html("");
        $("#start_id").html("<b>Start Location:</b> " + parsedAssets);

        var parsedAssets = $tds.eq(12).text();
        $("#end_id").html("");
        $("#end_id").html("<b>End Location:</b>" + parsedAssets);
    });

});

