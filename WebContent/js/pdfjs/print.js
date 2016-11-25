var doc = new jsPDF('l', 'mm', [500, 300]);
doc.setFontSize(10);
var specialElementHandlers = {
    '#editor': function (element, renderer) {
        return true;
    }
};


$('#cmd').click(function () {
    doc.fromHTML($('#content').html(), 10,10 , {
        'width': 150,
            'elementHandlers': specialElementHandlers
    });
    doc.save('tabela.pdf');
});

