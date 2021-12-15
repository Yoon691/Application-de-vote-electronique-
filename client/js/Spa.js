// window.addEventListener(
//     'hashchange',
//     () => { show(window.location.hash); }
// );

let idCandiat = null
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
    console.log("hash: " + hash);
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
        case "#ballot" :
            getBallot();
            break;
        case "#candidat":
            console.log("ballotid: " + idCandiat);
            getCandidat(idCandiat);
        default:
            // window.onload = timedRefresh(5000);
            console.log("DEFAULT");
            break;
    }

}

function timedRefresh(timeoutPeriod) {
    console.log("refresh 1");
    setTimeout("window.location.reload();",timeoutPeriod);
    console.log("refresh 2");
}

$(document).ready(function() {

    window.addEventListener('hashchange', () => {
        console.log("hash document ready: " + window.location.hash.split("/")[0]);
        if (window.location.hash.split("/")[0] === "#candidat"){
            console.log("J'y suis");
            idCandiat = window.location.hash.split("/")[1]
            show(window.location.hash.split("/")[0]);
        } else {
            show(window.location.hash);
        }
    });
    showMenuConnecte();
    getResultats();

    // $('#candidat-select').on('click', function (){
    //     getListCandidats();
    // })

    window.displayNavbarConnection();

});

