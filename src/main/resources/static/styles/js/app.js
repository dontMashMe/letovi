$( document ).ready(function() {
    $('.spinner-border').hide();
    $('#submitFormBtn').click(function (){
        if(validateForm()){
            $('.spinner-border').show();
        }
    })
});

function validateForm(){
    let valid = true;
    $(':required').each(function (){
        //console.log($(this));
        if($(this).val() === ''){
            valid = false;
            return false;
        }
    })
    return valid;
}
