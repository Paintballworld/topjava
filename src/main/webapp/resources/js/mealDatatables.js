var ajaxUrl = 'ajax/profile/meals/';
var mealDatatableApi;

$(document).ready(function () {
// $(function () {
    mealDatatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "data": "exceeded"
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
                0   ,
                "asc"
            ]
        ]
    });
    makeEditable();
});

