jQuery(($) => {

    // menu
    const menu = $('.content');
    const menuBtn = $('.main-myButton');

    $(document).mouseup(function (e) {
        console.log(e.target)
        if (e.target.className === 'main-myButton') {
            menuBtn.addClass('is-active');
            menu.stop().slideDown();
        } else if (e.target.className === 'main-myButton is-active') {
            menuBtn.removeClass('is-active');
            menu.stop().slideUp();
        } else if ((menu.has(e.target).length === 0) && (menuBtn.has(e.target).length === 0)) {
            // if ($(window).width() < 768) {
            menuBtn.removeClass('is-active');
            menu.stop().slideUp();
            // }
        }
    });

});