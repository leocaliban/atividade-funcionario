/* inicializando o jQuery*/
jQuery(document).ready(function(){
	/* seletor tipo css, pegando todo elemento data-toggle que é um tooltip faça a chamada*/
	$('[data-toggle="tooltip"').tooltip();
});

/*tooltip*/
$(function(){
	$('[rel="tooltip"]').tooltip();
});

/*função do modal excluir*/
$('#modalExcluir').on('show.bs.modal',function(event){
	var button = $(event.relatedTarget);
	
	var idFuncionario = button.data('id');
	var nome = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')){
		action += '/';
	}
	form.attr('action', action + idFuncionario);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o funcionário <strong>'+nome+'</strong>?');
	
});
