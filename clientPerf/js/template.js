function showTemplateData(templateID, data, idRes) {
    let template = $('#' + templateID).html();
    Mustache.parse(template);
    let html = Mustache.render(template, data);
    $('#' + idRes).html(html);
    console.log("fin de template");
}
