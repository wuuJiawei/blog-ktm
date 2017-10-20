/**
 * Created by 武佳伟丶 on 2017/10/8 0008.
 */
'use strict';

var root = document.querySelector(':root');
window.addEventListener('mousemove', function (e) {
    var x = e.pageX / window.innerWidth * 2 - 1;
    var y = e.pageY / window.innerHeight * 2 - 1;
    root.style.setProperty('--x', x);
    root.style.setProperty('--y', y);
});