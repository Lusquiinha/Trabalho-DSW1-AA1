// Este código será executado assim que a página terminar de carregar
$(document).ready(function(){
    // Aplica a máscara de CPF a qualquer campo com a classe 'cpf-mask'
    $('.cpf-mask').mask('000.000.000-00', {reverse: true});

    // Aplica a máscara de CNPJ a qualquer campo com a classe 'cnpj-mask'
    $('.cnpj-mask').mask('00.000.000/0000-00', {reverse: true});

    // Aplica uma máscara dinâmica para telefone (serve para 8 ou 9 dígitos)
    var phoneMask = function (val) {
        return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
    },
    options = {onKeyPress: function(val, e, field, options) {
            field.mask(phoneMask.apply({}, arguments), options);
        }
    };
    $('.phone-mask').mask(phoneMask, options);
});