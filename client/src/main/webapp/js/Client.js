let apiURL = "https://192.168.75.70/api/v3";
let currentUser = {
    login: "",
    nom: "",
    admin: false
};
let ballot = {
    votant: "",
    id: ""
};
let listCandidats = [];
let resultats = [];
let token;




function login() {

    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    } else {
                        event.preventDefault()
                        event.stopPropagation()
                        userData = {
                            "login": $("#loginForm").val(),
                            "nom": $("#nomForm").val(),
                            "admin": $("#adminForm").get(0).checked
                        };
                        fetch(apiURL + '/users/login', {
                            method: "POST",
                            headers: { 'Content-Type': 'application/json' },
                            body: JSON.stringify(userData),
                            credentials: "same-origin",
                            mode: "cors"
                        })
                            .then(response => {
                                token = response.headers.get("Authorization")
                                console.log("reponse :  " + token);
                                console.log("userId: " + getUserLogin(token));
                                getUserProfil(getUserLogin(token));
                                window.location.hash = "#monCompte";
                                $("#connecte").hide();

                            })
                            .catch(error => console.log(error));


                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })()


    let userData;
}


function putNom(balise) {
    let userId = getUserLogin(token);
    let userNom;
    if (balise === "nom") {
        userNom = {
            "nom": $("#" + balise).text(),
        };
    } else if (balise === "nomPut") {
        userNom = {
            "nom": $("#" + balise).val(),
        };
    }
    fetch(apiURL + '/users/' + userId + '/nom', {
            method: "PUT",
            headers: {
                'Authorization': token,
                'content-type': "application/json",
            },
            body: JSON.stringify(userNom),
            credentials: 'same-origin',
            mode: 'cors'
        })
        .then(response => {
            if (response.ok) {
                $("#nomPut").val('');
                currentUser.nom = userNom.nom;
                getUserProfil(userId);
            }

        })
        .catch(error => { console.log(error); })
}

function getUserProfil(login) {
    fetch(apiURL + '/users/' + login, {
            method: "GET",
            headers: {
                'Authorization': token,
                'Accept': "application/json",
            },
            credentials: "same-origin",
            mode: "cors"
        })
        .then(response => {
            return response.json();
        })
        .then(data => {
            currentUser = data;
            showMenuConnecte();
            showTemplateData('mustacheTempalte_a', currentUser, 'target-output');
        })
        .catch(error => console.error(error));
}

function logout() {
    fetch(apiURL + '/users/logout', {
            method: "POST",
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json',
            },
            credentials: 'same-origin',
            mode: 'cors'
        })
        .then(response => {
            if (response.ok){
                $("#loginForm").val('') ;
                $("#nomForm").val('') ;
                token = null;
                currentUser = {
                    login: "",
                    nom: "",
                    admin: false
                };
                ballot = {
                    votant: "",
                    id: ""
                };
                window.location.hash = "#index";
                showMenuConnecte();
            }

        })
        .catch(error => {
            console.log(error)
        });
}

function getCandidat(candidatId) {
    fetch(apiURL + '/election/candidats/' + candidatId, {
            method: "GET",
            headers: {
                'Authorization': token,
                'Accept': "application/json",
            },
            credentials: "same-origin",
            mode: "cors"
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
        })
        .then(data => {
            if (data == null) {
                const message = {
                    msg: "Vous devez connectez pour voir les informations de ce candidat !",
                };
                $('#target-output-candidat-info').hide();
                $('#target-output-candidat-non-connect').show();
                showTemplateData('mustacheTempalte_candidat-non-connect', message, 'target-output-candidat-non-connect');

            } else {

                $('#target-output-candidat-non-connect').hide();
                $('#target-output-candidat-info').show();
                showTemplateData('mustacheTempalte_candidat_info', data, 'target-output-candidat-info');

            }

        })
        .catch(error => console.error(error));
}

function getListCandidats() {
    fetch(apiURL + '/election/candidats/noms', {
        method: "GET",
        headers: {
            'Accept': "application/json",
        },
        credentials: "same-origin",
        mode: "cors"
    })

    .then(response => {
            return response.json();
        })
        .then(data => {
            listCandidats = data;
            if (window.location.hash === "#candidats") {
                showTemplateData('mustacheTempalte_candidats', listCandidats, 'target-output-candidats');
            } else {
                showTemplateData('mustacheTempalte_candidat', listCandidats, 'target-output-candidat');
            }

        })
        .catch(error => { console.error(error) });
}

function getResultats() {
    fetch(apiURL + '/election/resultats', {
            method: "GET",
            headers: {
                'Accept': "application/json",

            },
            mode: "cors",
            credentials: "same-origin"

        })
        .then(response => {
            return response.json();
        })
        .then(data => {
            resultats = data;
            window.location.hash = "#index";
            showTemplateData('mustacheTempalte_resultats', resultats, 'target-output-resultats')
            show('#index');
        })
        .catch(error => { console.error(error); })
}

function vote() {
    const nomCandidat = {
        "nomCandidat": $('#candidat-select option:selected').text()
    }
    fetch(apiURL + '/election/ballots', {
            method: 'POST',
            headers: {
                'content-type': 'application/json',
                'Authorization': token,
            },
            body: JSON.stringify(nomCandidat),
            credentials: "same-origin",
            mode: "cors"
        })
        .then(response => {
                if (response.ok) {
                    let modale = {
                        titre: "Informations vote ",
                        msg: "Vous avez bien voté "

                    }
                    showTemplateData('mustacheTempalte_fenetre_modale', modale, 'target-output-modal')

                } else {
                    let modale = {
                        titre: "Informations vote ",
                        msg: "Votre vote n'est pas passée"

                    }
                    showTemplateData('mustacheTempalte_fenetre_modale', modale, 'target-output-modal')

                }
            }

        )
        .catch(error => console.error(error));
}

function getBallot() {
    let userId = getUserLogin(token);
    fetch(apiURL + '/election/ballots/byUser/' + userId, {
            method: 'GET',
            headers: {
                'Authorization': token,
                'Accept': 'application/json'
            },
            credentials: "same-origin",
            mode: "cors"

        })
        .then(response => {
            if (response.status === 200) {
                return response.json();
            }
        })
        .then(data => {
            if (data == null) {
                let modale = {
                    msg: "Vous n'avez pas encore voté "
                };
                $('#target-output-ballot').hide();
                $('#target-output-ballot-non-vote').show();
                showTemplateData('mustacheTempalte_ballot-non-vote', modale, 'target-output-ballot-non-vote');
            } else {
                ballot = data;
                $('#target-output-ballot-non-vote').hide();
                $('#target-output-ballot').show();
                showTemplateData('mustacheTempalte_ballot', ballot.id.split("/ballots/")[1], 'target-output-ballot');
                return ballot;
            }

        })
        .catch(error => console.error(error));
}

function deleteVote() {
    let ballotId = ballot.id;
    fetch(apiURL + '/election/ballots/' + ballotId.split('/ballots/')[1], {
            method: "DELETE",
            headers: {
                'Authorization': token,
            },
            credentials: "same-origin",
            mode: "cors"
        })
        .then(response => {
            if (response.status === 204) {
                let modale = {
                    titre: "Informations vote ",
                    msg: "Votre vote a bien été supprimé "

                }
                showTemplateData('mustacheTempalte_fenetre_modale', modale, 'target-output-modal');
                show("#ballot");
            } else if (response.status === 404) {
                let modale = {
                    titre: "Informations vote ",
                    msg: "Vous n'avez pas encore voté "

                }
                showTemplateData('mustacheTempalte_fenetre_modale', modale, 'target-output-modal');
            }
        })
        .catch(error => console.log(error));

}

function splitToken(t) {
    let token = {};
    token.raw = t;
    token.header = JSON.parse(window.atob(t.split('.')[0]));
    token.payload = JSON.parse(window.atob(t.split('.')[1]));
    return (token)
}

function getLoginUri(uri) {
    let splitedUri = uri.split("/");
    return splitedUri[splitedUri.length - 1];
}

function getUserLogin(token) {
    let tokenM = token.replace("Bearer ", "");
    let sub = splitToken(tokenM).payload.sub;
    let userLogin = getLoginUri(sub);
    console.log("USER ID : " + userLogin);
    return userLogin;
}

document.addEventListener("DOMContentLoaded", function(event) {

    const showNavbar = (toggleId, navId, bodyId, headerId) => {
        const toggle = document.getElementById(toggleId),
            nav = document.getElementById(navId),
            bodypd = document.getElementById(bodyId),
            headerpd = document.getElementById(headerId)

        // Validate that all variables exist
        if (toggle && nav && bodypd && headerpd) {
            toggle.addEventListener('click', () => {
                // show navbar
                nav.classList.toggle('show')
                    // change icon
                toggle.classList.toggle('bx-x')
                    // add padding to body
                bodypd.classList.toggle('body-pd')
                    // add padding to header
                headerpd.classList.toggle('body-pd')
            })
        }
    }

    showNavbar('header-toggle', 'nav-bar', 'body-pd', 'header')

    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link')

    function colorLink() {
        if (linkColor) {
            linkColor.forEach(l => l.classList.remove('active'))
            this.classList.add('active')
        }
    }
    linkColor.forEach(l => l.addEventListener('click', colorLink))
});
