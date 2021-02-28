$('#close-todo-button').click(function () {
    var todoId = $(this).parents('div').find('input[type="hidden"]').val();

    $.ajax({
       url: '/todo/' + todoId,
       type: 'PUT',
       async: false,
       success: function (result) {
           window.location.replace("/todo");
       },
        error: function (error) {
           alert('Wystąpił nieoczekiwany błąd')
        }
    });
});