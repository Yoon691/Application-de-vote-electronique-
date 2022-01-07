let idCandidat = null

function displayNavbarConnection() {

    $('#menu1').on("click", function() {
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
    switch (hash) {
        case "#monCompte":
            if (token != null) {
                let userId = getLoginUser(token);
                getProfileUser(userId);
                $('#target-output').on('click', function() {
                    $('#nom').attr('contenteditable', 'true');
                });
                $('#target-output').on('keypress', function(e) {
                    if (e.which == 13) {
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
        case "#index":
            setTimeout(function() {
                if (window.location.hash === '#index') {
                    getResultats();
                }
            }, 5000);
            break;
        case "#vote":
            if (token != null) {
                getListCandidats();
            } else {
                show("#index")
            }

            break;
        case "#ballot":
            if (token != null) {
                getBallot();
            } else {
                show("#index")
            }
            break;
        case "#candidat":
            getCandidat(idCandidat);
            break;
        default:
            console.log("DEFAULT");
            break;
    }

}


$(document).ready(function() {

    window.addEventListener('hashchange', () => {
        console.log("hash document ready: " + window.location.hash.split("/")[0]);
        if (window.location.hash.split("/")[0] === "#candidat") {
            console.log("J'y suis");
            idCandidat = window.location.hash.split("/")[1]
            show(window.location.hash.split("/")[0]);
        } else {
            show(window.location.hash);
        }
    });

    showMenuConnecte();
    getResultats();

    window.displayNavbarConnection();

});

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
