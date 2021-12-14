function showUserCurrent(){
    console.log("templeting : " + "nom: " + currentUser.nom + "login: " + currentUser.login + "admin: " + currentUser.admin )
    let shows = { "nom": currentUser.nom, "login": currentUser.login, "admin": currentUser.admin };
    let template = $('#mustacheTempalte_a').html();
    let html = Mustache.render(template, shows);
    $('#target-output').html(html);

}

function showCandidatsList(){
    console.log("templeting listCanddiat : " + listCandidats );
    let template = $('#mustacheTempalte_candidat').html();
    let html = Mustache.render(template, listCandidats);
    $('#target-output-candidat').html(html);

}

function showResultats(){

    console.log("templeting resultats : " + resultats.keys());
    let template = $('#mustacheTempalte_resultats').html();
    let html = Mustache.render(template, JSON.parse(JSON.stringify(resultats)));
    $('#target-output-resultats').html(html);

}

function DOJOB(templateID, data, idRes) {
    console.log("appelle a dojob");
    let template = $('#' + templateID).html();
    Mustache.parse(template);
    let html = Mustache.render(template, data);
    $('#' + idRes).html(html);
    console.log("fin appele a do job pour " + idRes);
}
