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

function putNom(){
    console.log("token put: " + token);
    let userId = getUserId(token);
    const userNom = {
            "nom" : $("#nomPut").val(),
    };
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
            getUserInfos(userId);

        })
        .catch(error => {console.log(error);})
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
            window.location.hash = "#index";
            showMenuConnecte();

            }
        )
        .catch(error =>{ console.log("catch");
            console.log(error)} );
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
                // showCandidatsList();
                DOJOB('mustacheTempalte_candidats', listCandidats, 'target-output-candidats');
            } else {
                console.log("candidats else : " + listCandidats)
                // showCandidatsList();
                DOJOB('mustacheTempalte_candidat', listCandidats, 'target-output-candidat');
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


