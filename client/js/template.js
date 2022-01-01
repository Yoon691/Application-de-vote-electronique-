function showTemplateData(templateID, data, idRes) {
    let template = $('#' + templateID).html();
    Mustache.parse(template);
    let html = Mustache.render(template, data);
    $('#' + idRes).html(html);
    console.log("fin de template");
}

function showTemplateModal(templateID, data, idRes) {

    let template = $('#' + templateID).html();
    Mustache.parse(template);
    let html = Mustache.render(template, data);
    $('#' + idRes).html(html);
    $("#bak").attr("data-toggle", "modal");
    $("#bak").attr("data-target", "#exampleModal");
    console.log("fin de template");
    console.log("html : " + $("#menuConnecte").html());
    // data-toggle="modal" data-target="#exampleModal"
}
