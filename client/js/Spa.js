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
function showMenuConnecte() {

    if (currentUser.login !== "") {
        console.log("je suis co");
        $('#menuConnecte').show();
    } else {
        console.log("je suis pas co");
        $('#menuConnecte').hide();
    }

}
$(document).ready(function() {
    window.addEventListener('hashchange', () => { show(window.location.hash); });
    showMenuConnecte();
    getResultats();
    function show(hash) {
        $('.active').removeClass('active').addClass('inactive');
        $(hash).removeClass('inactive').addClass('active');
        switch (hash) {
            case "#monCompte":
                showUserCurrent();
                break;
            case "#candidats":
                getListCandidats();
                break;
            default:

                break;
        }

    }


    window.displayNavbarConnection();
});

