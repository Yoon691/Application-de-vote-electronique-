
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
            console.log("case \"#monCompte\"");
            if (token != null){
                let userId = getUserId(token);
                getUserInfos(userId);
                $('#target-output').on('click', function (){
                    console.log("click1");
                    $('#nom').attr('contenteditable', 'true');
                    console.log("click2");
                });
                $('#target-output').on('keypress',function (e){
                    if(e.which == 13) {
                        console.log('You pressed enter!');
                        console.log("Blurrr1");
                        putNom("nom");
                        console.log("Blurrr2");
                    }
                });

             } else {
                show("#index");
            }

            break;
        // case "#connect" :
        //     // showTemplateModal('mustacheTempalte_fenetre_modale', "", 'target-output-modal',"connexion", false,"#connect");
        //     break;
        case "#candidats":
            getListCandidats();
            break;
        case "#index" :
            console.log("Tic");
            setTimeout( function (){
                if (window.location.hash === '#index'){
                    getResultats();
                }},5000);
            console.log("Toc");
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
            // console.log("token: " + token);
            // if (token == null){
            //     console.log("ballotid: " + idCandiat);
            //     console.log("If #candidat ");
            //     const modale = {
            //         titre: "les informations des candidats ",
            //         msg: "Vous devez connectez pour voir"
            //     };
            //     showTemplateModal('mustacheTempalte_fenetre_modale', modale, 'target-output-modal',"mustacheTempalte_candidats", true,"candidats");
            // } else {
            //     showTemplateModal('mustacheTempalte_fenetre_modale', "", 'target-output-modal',"target-output-candidat-info", false,"candidat");
                console.log("ballotid: " + idCandiat);
                getCandidat(idCandiat);

            break;
        default:
            console.log("DEFAULT");
            break;
    }

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

    window.displayNavbarConnection();

});

