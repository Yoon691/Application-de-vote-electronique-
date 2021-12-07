let baseURL = "https://192.168.75.56/api/v3";
let currentUser=null;
let token;
function login() {
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
                token = response.headers.get("Authorization");
                console.log("TOKEN :  "+ token);
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
            getCurentVotes(currentUser.login);
            showMenuConnecte();
        })
        .catch(error => console.error(error));
}

function getIdFromUri(uri) {
    let splitedUri = uri.split("/");
    return splitedUri[splitedUri.length - 1];
}

function getUserId(token) {
    let tokenM = token.replace("Bearer ", "");
    let sub = jwtDecode(tokenM).payload.sub;
    let userLogin = getIdFromUri(sub);
    console.log("USER ID " + userLogin);
    return userLogin;
}


