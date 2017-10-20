var basicUtil = {};
basicUtil.url = {
    /**
     * Get protocol of query.
     * @returns {string}  http/https
     */
    getProtocol: function () {
        return window.location.href.split('://', 1)[0];
    },
    /**
     * Get domain of host
     * @returns {string}
     */
    getDomain: function () {
        return window.location.href.split('://')[1].split(/[/]+/, 1)[0];
    },
    /**
     * Get dir of query
     * @returns {string} with start slash, with no end slash(/)
     */
    getQueryDir: function () {
        return window.location.href.split('://')[1].split('?', 1)[0].replace(this.getDomain(), '').split(/\/$/, 1)[0];
    },
    /**
     * Get value of query key
     * @param {string} key
     * @returns {string}
     */
    getQueryKey: function (key) {
        var splitByKey = window.location.href.split('#', 1)[0].replace(window.location.href.split('?', 1) + '?', '').split(key + '=');
        if (splitByKey.length === 1) {
            return null;
        } else {
            return decodeURI(splitByKey[1].split('&', 1)[0]);
        }
    },
    /**
     * Get fragment id
     * @returns {string}
     */
    getFragment: function () {
        var fragment = window.location.href.replace(window.location.href.split('#', 1)[0] + '#', '');
        return fragment;
    }
};

layui.config({
    version: "2.0.0"
    , base: '/public/js/'
}).extend({
    fly: 'index'
}).use('fly', function () {
    var fly = layui.fly, layer = layui.layer, util = layui.util;
    $('.detail-body').each(function () {
        var othis = $(this), html = othis.html();
        othis.html(fly.content(html));
    });
    $('time').each(function (index, item) {
        $(item).text(util.timeAgo($(item).text()));
    });
    layer.photos({
        photos: '.photos'
        , zIndex: 9999999999
        , anim: -1
    });
    var settings = {
        progressbarWidth: "100%",
        progressbarHeight: "3px",
        progressbarColor: "#22ccff",
        progressbarBGColor: "#eeeeee",
        defaultVolume: 0.8
    };
    $(".playerd").player(settings);
});