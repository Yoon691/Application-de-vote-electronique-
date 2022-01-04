function showTemplateData(templateID, data, idRes) {
    let template = $('#' + templateID).html();
    Mustache.parse(template);
    let html = Mustache.render(template, data);
    $('#' + idRes).html(html);
    console.log("fin de template");
}

function showTemplateModal(templateID, data, idRes, idSection, bool, hash) {
    if (bool){
        let template = $('#' + templateID).html();
        Mustache.parse(template);
        let html = Mustache.render(template, data);
        $('#' + idRes).html(html);
        $("#" + idSection).attr("data-toggle", "modal");
        $("#" + idSection).attr("data-target", "#exampleModal");
        console.log("fin de template");
        console.log("html : " + $("#" + hash).html());
    } else {
        $("#" + idSection).removeAttr("data-toggle", "modal");
        $("#" + idSection).removeAttr("data-target", "#exampleModal");
        console.log("html : " + $("#" + hash).html());
         //show(hash);
    }

}
