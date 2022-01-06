
let idCandiat = null
function displayNavbarConnection() {

    $('#menu1').on("click", function () {
        $('#menuA').slideToggle('blink');
    });
    $('#nom').attr('contentEditable', 'true')

}
function showMenuConnecte() {

    if (currentUser.login !== "") {
        $('#menuConnecte').show();
        $('#menuConnexion').hide();
    } else {
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
            if (token != null){
                let userId = getUserLogin(token);
                getUserProfil(userId);
                $('#target-output').on('click', function (){
                    $('#nom').attr('contenteditable', 'true');
                });
                $('#target-output').on('keypress',function (e){
                    if(e.which == 13) {
                        putNom("nom");
                    }
                });

             } else {
                show("#index");
            }

            break;
        case "#candidats":
            getListCandidats();
            break;
        case "#index" :
            setTimeout( function (){
                if (window.location.hash === '#index'){
                    getResultats();
                }},5000);
            break;
        case "#vote" :
            if (token !=null){
                getListCandidats();
            }else {
                show("#index")
            }
            break;
        case "#ballot" :
            if (token !=null) {
                getBallot();
            }else {
                show("#index")
            }
            break;
        case "#candidat":
            getCandidat(idCandiat);
            break;
        default:
            console.log("DEFAULT");
            break;
    }

}


$(document).ready(function() {

    window.addEventListener('hashchange', () => {
        if (window.location.hash.split("/")[0] === "#candidat"){
            idCandiat = window.location.hash.split("/")[1]
            show(window.location.hash.split("/")[0]);
        } else {
            show(window.location.hash);
        }
    });
    showMenuConnecte();
    getResultats();

    window.displayNavbarConnection();

});

