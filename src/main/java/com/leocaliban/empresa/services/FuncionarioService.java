package com.leocaliban.empresa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.leocaliban.empresa.domain.Funcionario;
import com.leocaliban.empresa.domain.Status;
import com.leocaliban.empresa.repositories.FuncionarioRepository;
import com.leocaliban.empresa.repositories.filters.FuncionarioFilter;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository repository;
	
	public List<Funcionario> listarTodos(){
		return repository.findAll();
	}
	
	public void salvar(Funcionario funcionario) {
		try {
			repository.save(funcionario);
		}
		catch(DataIntegrityViolationException e) {
			 throw new IllegalArgumentException("Formato De Data Inválido!");
		}
	}
	
	public Funcionario editar(Long id) {
		Funcionario funcionario = repository.findOne(id);
		return funcionario;
	}
	
	public void excluir(Long id) {
		repository.delete(id);
	}
	
	public String mudarStatus(Long id) {
		Funcionario funcionario = editar(id);
		if(funcionario.getStatus() == Status.ATIVO) {
			funcionario.setStatus(Status.INATIVO);
			repository.save(funcionario);
			return Status.INATIVO.getDescricao();
		}
		else {
			funcionario.setStatus(Status.ATIVO);
			repository.save(funcionario);
			return Status.ATIVO.getDescricao();
		}
	}
	
	public List<Funcionario>filtrar(FuncionarioFilter filtro){
		String nome = filtro.getNome()==null ? "%" : filtro.getNome();
		return repository.findByNomeContaining(nome);
	}

}
