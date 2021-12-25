let baseURL = "https://192.168.75.56/api/v3";
let currentUser={
    login : "",
    nom : "",
    admin : false
};
let ballot = {
        votant:"",
            id:""
};
let listCandidats = [];
let resultats = [];
let token;




function login() {
    console.log("Hello word");
    if ($("#loginForm").val() !== "") {
        const userData = {
            "login": $("#loginForm").val(),
            "nom": $("#nomForm").val(),
            "admin": $("#adminForm").get(0).checked
        };
        fetch(baseURL + '/users/login', {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(userData),
            credentials: "same-origin",
            mode: "cors"
        })
            .then(response => {
                 token = response.headers.get("Authorization")
                 console.log("reponse :  "+ token);
                 console.log("userId: " + getUserId(token) ) ;
                getUserInfos(getUserId(token));
                window.location.hash = "#monCompte";
                $("#connecte").hide();

            })
            .catch(error => console.log(error));
    }
    else {
        alert("Vous devrez remplir tous les champs obligatoires (*)");
    }
}

function putNom(balise){
    console.log("token put: " + balise);
    let userId = getUserId(token);
    let userNom;
    if (balise === "nom"){
        userNom = {
            "nom" : $("#" + balise).text(),
        };
    } else if (balise === "nomPut"){
        userNom = {
            "nom" : $("#" + balise).val(),
        };
    }

    console.log("newNom: " + JSON.stringify(userNom) );
    fetch(baseURL + '/users/' + userId + '/nom', {
        method: "PUT",
        headers : {
                'Authorization': token,
                'content-type' : "application/json",
            },
        body : JSON.stringify(userNom),
        credentials : 'same-origin',
        mode : 'cors'
    })
        .then(response => {
            if (response.ok){
                currentUser.nom = userNom.nom;
                getUserInfos(userId);
            }

        })
        .catch(error => {console.log(error);})
}
function getUserInfos(login) {
    console.log("jsuis dans getUserInfo");
    fetch(baseURL + '/users/' + login, {
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
            console.log("currentUser" + JSON.stringify(data));
            showMenuConnecte();
            showTemplateData('mustacheTempalte_a',currentUser, 'target-output');
        })
        .catch(error => console.error(error));
}

function deco(){
    console.log("debut deco");
    fetch(baseURL + '/users/logout', {
        method : "POST",
        headers : {'Authorization' : token,
                   'Content-Type': 'application/json',
        },
        credentials : 'same-origin',
        mode : 'cors'
    })
        .then(response => {
            console.log("FIN DE DECONNEXION");
            token = null;
            currentUser = {
                login : "",
                nom : "",
                admin : false
            };
            ballot = {
                votant:"",
                id:""
            };
            window.location.hash = "#index";
            showMenuConnecte();

            }
        )
        .catch(error =>{ console.log("catch");
            console.log(error)} );
}

function getCandidat(candidatId){
    console.log("HALLOOOOOOOOOOOOOO");

    console.log("candidatSelectioner: " + candidatId)
    fetch(baseURL + '/election/candidats/' + candidatId, {
        method: "GET",
        headers: {
            'Authorization': token,
            'Accept': "application/json",
        },
        credentials: "same-origin",
        mode: "cors"
    })
        .then(response => {
            if (response.ok){
                return response.json();
            }
        })
        .then(data => {
            if (data == null){
                const message = {
                    nom : "les informations des candidats ",
                    prenom : "Vous devez connectez pour voir"
                };
                console.log(JSON.stringify(message))
                showTemplateData('mustacheTempalte_candidat_info',message, 'target-output-candidat-info');
                // alert("Vous devez connectez pour voir les information des candidats");
            } else {
                console.log("affiche candidat : " + JSON.stringify(data)  ) ;
                showTemplateData('mustacheTempalte_candidat_info', data , 'target-output-candidat-info');

            }

        })
        .catch(error => console.error(error));
}

function getListCandidats(){
    fetch(baseURL + '/election/candidats/noms', {
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
            if (window.location.hash === "#candidats"){
                console.log("candidats if : " + listCandidats)
                showTemplateData('mustacheTempalte_candidats', listCandidats, 'target-output-candidats');
            } else {
                console.log("candidats else : " + listCandidats)
                showTemplateData('mustacheTempalte_candidat', listCandidats, 'target-output-candidat');
            }

        })
        .catch(error => {console.error(error)});
}
function getResultats(){
    fetch(baseURL + '/election/resultats', {
        method : "GET",
        headers : {
            'Accept' : "application/json",

        },
        mode : "cors",
        credentials : "same-origin"

    })
        .then(response => {
             return  response.json();
        })
        .then(data => {
            resultats = data;
            console.log("resultats : " + JSON.parse(JSON.stringify(resultats)));
             window.location.hash = "#index";
            // showResultats();
            showTemplateData('mustacheTempalte_resultats', resultats, 'target-output-resultats')
            show('#index');
        })
        .catch(error => {console.error(error);} )
}

function vote(){
    const nomCandidat = {
        "nomCandidat": $('#candidat-select option:selected').text()
    }
    console.log("nomCandidatchoisi: " + nomCandidat.nomCandidat)
    fetch(baseURL + '/election/ballots', {
        method : 'POST',
        headers : {
            'content-type' : 'application/json',
            'Authorization' : token,
        },
        body : JSON.stringify(nomCandidat),
        credentials : "same-origin",
        mode : "cors"
    })
        .then(response =>
            {
                if (response.ok){
                    alert("Vous avez bien votez");
                } else {
                    alert("Votre vote n'est pas passée");
                }
            }

        )
        .catch(error => console.error(error));
}

function getBallot(){
    console.log("userId getBallot: " + getUserId(token));
    let userId = getUserId(token);
    // let ballot = {"votant":"",
    //                 "id":""}
    fetch(baseURL + '/election/ballots/byUser/' + userId, {
        method : 'GET',
        headers : {
                    'Authorization' : token,
                    'Accept' : 'application/json'
        },
        credentials : "same-origin",
        mode : "cors"

    })
        .then(response =>{
            if (response.status === 200){
                return response.json();
            }})
        .then(data =>{
                console.log("DATA : " + JSON.stringify(data));
                if (data == null){
                    showTemplateData('mustacheTempalte_ballot', "vous n'avez pas encore voté", 'target-output-ballot');
                    alert("Vous n'avez pas encore voté , Votez pour accéder a votre vote");
                } else {
                    ballot = data;
                    console.log("data ballot : " + ballot.id + " / " +ballot.votant );
                    showTemplateData('mustacheTempalte_ballot', ballot.id.split("/ballots/")[1], 'target-output-ballot');
                    return ballot;
                }

        })
        .catch(error => console.error(error));
}

function deleteVote(){
    let ballotId = ballot.id;
    console.log("ballotId: " + ballotId);
    fetch(baseURL + '/election/ballots/' + ballotId.split('/ballots/')[1], {
        method : "DELETE",
        headers : {
                'Authorization' : token,
        },
        credentials : "same-origin",
        mode : "cors"
    } )
        .then(response => {
                if (response.status === 204){
                    alert("Votre vote est bien supprimer");
                    showTemplateData('mustacheTempalte_ballot', "vote supprimer", 'target-output-ballot');

                } else if (response.status === 404){
                    alert("Action interdit : vous n'avez pas encore voté");
                }
        })
        .catch(error => console.log(error));

}
function jwtDecode(t) {
    let token = {};
    token.raw = t;
    token.header = JSON.parse(window.atob(t.split('.')[0]));
    token.payload = JSON.parse(window.atob(t.split('.')[1]));
    return (token)
}

function getIdFromUri(uri) {
    let splitedUri = uri.split("/");
    return splitedUri[splitedUri.length - 1];
}

function getUserId(token) {
    let tokenM = token.replace("Bearer ", "");
    let sub = jwtDecode(tokenM).payload.sub;
    let userLogin = getIdFromUri(sub);
    console.log("USER ID : " + userLogin);
    return userLogin;
}


