let baseURL = "https://192.168.75.56/api/v3";
let currentUser={
    login : "",
    nom : "",
    admin : false
};
let listCandidats = [];
let resultats = [];
let token;




function login() {
    console.log("Hello word");
    if ($("#loginForm").val() !== "") {
        console.log("login :" + $("#loginForm").val() +
            "nom : " + $("#nomForm").val() +
            "admin: " + $("#adminForm").get(0).checked)
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

                // console.log("Hello : " + JSON.stringify(response.headers.values()).toString());
                 token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmb28iLCJhdWQiOiJodHRwOi8vMTI3LjAuMC4xOjgwODAvdjMiLCJpc3MiOiJFbGVjdGlvbiBNMUlGMDMiLCJhZG1pbiI6ZmFsc2UsImV4cCI6MTYzOTIzNDMxNn0.npd_UYstgsDz-ovd_wBb5cJA8gf276Cupr_fbklAoI4";
                 console.log("reponse :  "+ token);
                 getUserId(token);
                getUserInfos(getUserId(token));
                window.location.hash = "#index";
                $("#connecte").hide();
                $("#monCompte").show();
            })
            .catch(error => console.log(error));
    }
    else {
        alert("Vous devrez remplir tous les champs obligatoires (*)");
    }
}

function getUserInfos(login) {
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
            showMenuConnecte();
            showUserCurrent();
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
            console.log("candidats : " + listCandidats)
            showCandidatsList();
        })
        .catch(error => console.error(error));
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
            showResultats();
        })
        .catch(error => {console.error(error);} )
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


