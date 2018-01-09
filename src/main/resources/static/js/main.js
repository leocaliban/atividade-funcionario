/* inicializando o jQuery*/
jQuery(document).ready(function(){

	$('[rel="tooltip"]').tooltip();
	
	$('.js-currency').maskMoney({decimal:',',thousands:'.',allowZero: true});
	
	$('.js-atualizar-status').on('click',function(event){
		event.preventDefault();
		var botao = $(event.currentTarget);
		var urlStatus = botao.attr('href');
		
		var response = $.ajax({url: urlStatus,type: 'PUT'});
		response.done(function(e){
			var id = botao.data('id');
			var status = botao.data('status');
			if(!status.localeCompare('Ativo')){
				$('[data-role='+id+']').html('<span class="label label-danger">'+e+'</span>');
				$('[data-s='+id+']').html('<span class="glyphicon glyphicon-ok">'+e+'</span>');

			}
			else if(status.localeCompare('Ativo')){
			$('[data-role='+id+']').html('<span class="label label-success">'+e+'</span>');

			}
		
		});
		response.fail(function(e){
			console.log(e);
			alert('Erro Ao Mudar Status');
		});
		
	});
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
