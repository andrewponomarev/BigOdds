const betAjaxUrl = "ajax/profile/bets/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: betAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(betAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: betAjaxUrl,
        datatableOpts: {
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (date, type, row) {
                        if (type === 'display') {
                            return date.replace('T', ' ').substr(0, 16);
                        }
                        return date;
                    }
                },
                {
                    "data": "event"
                },
                {
                    "data": "coefficient"
                },
                {
                    "render": renderEditBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    "render": renderDeleteBtn,
                    "defaultContent": "",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                $(row).attr("data-mealExcess", data.excess);
            },
        },
        updateTable: updateFilteredTable
    });
});