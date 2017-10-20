$.fn.player = function (settings) {
    var config = {
        progressbarWidth: "200px",
        progressbarHeight: "3px",
        progressbarColor: "#22ccff",
        progressbarBGColor: "#eeeeee",
        defaultVolume: 0.8
    };
    if (settings) {
        $.extend(config, settings)
    }
    var playControl = '<span class="simpleplayer-play-control"></span>';
    var stopControl = '<span class="simpleplayer-stop-control"></span>';
    this.each(function () {
        $(this).before('<div class="simple-player-container">');
        $(this).after("</div>");
        $(this).parent().find(".simple-player-container").prepend('<div><ul><li style="position:absolute; top: 12px;left: 12px;z-index: 999;"><i style="text-decoration: none;" class="start-button" href="javascript:void(0)">' + playControl + '</i></li><li class="progressbar-wrapper" style="background-color: ' + config.progressbarBGColor + "; cursor: pointer; width:" + config.progressbarWidth + ';"><span style="display: block; background-color: ' + config.progressbarBGColor + '; width: 100%; "><span class="progressbar" style="display: block;float: left; background-color: ' + config.progressbarColor + "; height: " + config.progressbarHeight + '; width: 0%; "></span></span></li></ul></div>');
        var simplePlayer = $(this).get(0);
        var button = $(this).parent().find(".start-button");
        var progressbarWrapper = $(this).parent().find(".progressbar-wrapper");
        var progressbar = $(this).parent().find(".progressbar");
        simplePlayer.volume = config.defaultVolume;
        $("#radio4wp .playing,#radio4wp #playlist li").on('click',
                function () {
                    simplePlayer.pause();
                    $('.simpleplayer-stop-control').addClass('simpleplayer-play-control').removeClass('simpleplayer-stop-control');
                    $("#playericon-play").removeClass("playericon-playing");
                    $("#playericon-play").addClass("playericon-play")
                });
        button.click(function () {
            if (simplePlayer.paused) {
                $.each($("audio"),
                        function () {
                            this.pause();
                            $(this).parent().find(".simpleplayer-stop-control").addClass("simpleplayer-play-control").removeClass("simpleplayer-stop-control")
                        });
                simplePlayer.play();
                $("#pic").addClass("rotate");
                $("#playericon-play").removeClass("playericon-play");
                $("#playericon-play").addClass("playericon-playing");
                $(this).find(".simpleplayer-play-control").addClass("simpleplayer-stop-control").removeClass("simpleplayer-play-control")
            } else {
                simplePlayer.pause();
                $("#pic").removeClass("rotate");
                $("#playericon-play").removeClass("playericon-playing");
                $("#playericon-play").addClass("playericon-play");
                $(this).find(".simpleplayer-stop-control").addClass("simpleplayer-play-control").removeClass("simpleplayer-stop-control")
            }
        });
        progressbarWrapper.click(function (e) {
            if (simplePlayer.duration != 0) {
                left = $(this).offset().left;
                offset = e.pageX - left;
                percent = offset / progressbarWrapper.width();
                duration_seek = percent * simplePlayer.duration;
                simplePlayer.currentTime = duration_seek
            }
        });
        $(simplePlayer).bind("ended",
                function (evt) {
                    $("#pic").removeClass("rotate");
                    $("#playericon-play").removeClass("playericon-playing");
                    $("#playericon-play").addClass("playericon-play")
                    button.find(".simpleplayer-stop-control").addClass("simpleplayer-play-control").removeClass("simpleplayer-stop-control");
                    progressbar.css("width", "0%")
                });
        $(simplePlayer).bind("timeupdate",
                function (e) {
                    duration = this.duration;
                    time = this.currentTime;
                    fraction = time / duration;
                    percent = fraction * 100;
                    if (percent) {
                        progressbar.css("width", percent + "%")
                    }
                });
        if (simplePlayer.duration > 0) {
            $(this).parent().css("display", "block")
        }
        if ($(this).attr("autoplay") == "autoplay") {
            button.click()
        }
    });
    return this
}
