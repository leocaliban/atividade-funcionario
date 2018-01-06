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
$('#modalExcluirEstagio').on('show.bs.modal',function(event){
	var button = $(event.relatedTarget);
	
	var codigoEstagio = button.data('codigo');
	var nomeAluno = button.data('nomealuno');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')){
		action += '/';
	}
	form.attr('action', action + codigoEstagio);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o estágio do aluno <strong>'+nomeAluno+'</strong>?');
	
});

/*função do modal editar*/
$('#modalVisualizar').on('show.bs.modal',function(event){
	var button = $(event.relatedTarget);
	
	var nomeAluno = button.data('nomealuno');
	var matricula = button.data('matricula');
	var nota = button.data('nota');
	var nomeOrientador = button.data('nomeorientador');
	var curso = button.data('curso');
	var localEstagio = button.data('local');
	var dataInicioEstagio = button.data('datainicio');
	var dataFimEstagio = button.data('datafinal');
	var numProcessoInicial = button.data('numprocessoinicio');
	var numProcessoFinal = button.data('numprocessofinal');
	var dataProcessoFim = button.data('dataprocessofinal');

	var modal = $(this);
	modal.find('.nomealuno p').html(nomeAluno);
	modal.find('.matricula p').html(matricula);
	modal.find('.nota p').html(nota);
	modal.find('.nomeorientador p').html(nomeOrientador);
	modal.find('.curso p').html(curso);
	
	modal.find('.localestagio p').html(localEstagio);
	modal.find('.datafimestagio p').html(dataFimEstagio);
	modal.find('.datainicioestagio p').html(dataInicioEstagio);
	modal.find('.numprocessoinicial p').html(numProcessoInicial);
	modal.find('.numprocessofinal p').html(numProcessoFinal);
	modal.find('.dataprocessofim p').html(dataProcessoFim);
});