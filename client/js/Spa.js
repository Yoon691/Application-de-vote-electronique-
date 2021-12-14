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
        console.log("je suis conncter");
        $('#menuConnecte').show();
        $('#menuConnexion').hide();
    } else {
        console.log("je ne suis pas connecter");
        $('#menuConnecte').hide();
        $('#menuConnexion').show();

    }

}
function show(hash) {
    $('.active').removeClass('active').addClass('inactive');
    $(hash).removeClass('inactive').addClass('active');
    switch (hash) {
        case "#monCompte":
            console.log("case \"#monCompte\"")
            showUserCurrent();
            break;
        case "#candidats":
            getListCandidats();
            break;
        case "#index" :
            // window.onload = timedRefresh(5000);
            break;
        case "#vote" :
            getListCandidats();
            break;
        default:
            // window.onload = timedRefresh(5000);
            break;
    }

}

function timedRefresh(timeoutPeriod) {
    console.log("refresh 1");
    setTimeout("window.location.reload();",timeoutPeriod);
    console.log("refresh 2");
}

$(document).ready(function() {
    window.addEventListener('hashchange', () => { show(window.location.hash); });
    showMenuConnecte();
    getResultats();

    // $('#candidat-select').on('click', function (){
    //     getListCandidats();
    // })

    window.displayNavbarConnection();

});

