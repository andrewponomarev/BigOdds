function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: "ajax/profile/bets/filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: "ajax/profile/bets/",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "event"
                },
                {
                    "data": "coefficient"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        }),
        updateTable: updateFilteredTable
    });
});