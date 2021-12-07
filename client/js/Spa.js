// window.addEventListener(
//     'hashchange',
//     () => { show(window.location.hash); }
// );
function displayNavbarConnection() {

    $('#menu1').on("click", function () {
        console.log("BOUTTON MENU FONCTIONELLE");
        $('#menuA').slideToggle('blink');
    });
    $('#nom').attr('contentEditable', 'true')

}
$(document).ready(function() {
    window.addEventListener('hashchange', () => { show(window.location.hash); });
    function show(hash) {
        $('.active').removeClass('active').addClass('inactive');
        $(hash).removeClass('inactive').addClass('active');
        switch (hash) {
            case "#monCompte":
                DOJOB("profile-template", currentUser, 'compteList');
                break;


            default:

                break;
        }

    }


    window.displayNavbarConnection();
});

