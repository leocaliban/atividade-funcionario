package com.leocaliban.empresa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome obrigatório!")
	@Size(max = 40, message = "Nome muito longo, Máximo 40 caracteres.")
	private String nome;
	
	@NotEmpty(message = "CPF obrigatório!")
	@Size(max = 11,min = 11, message = "CPF deve conter 11 caracteres.")
	private String cpf;
	
	@NotNull (message = "A Data É Obrigatória!")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@DecimalMin (value = "954.00", message = "Salário Não Pode Ser Menor Que R$ 954.00!")
	@DecimalMax (value = "999999.99", message = "Salário Não Pode Ser Maior Que R$ 999.999,99")
	@NotNull(message = "O Salário É Obrigatório!")
	@NumberFormat(pattern = "#,##0.00")
	private Double salario;
	
	@NotEmpty(message = "Cargo obrigatório!")
	@Size(max = 40, message = "Nome do cargo muito longo, Máximo 40 caracteres.")
	private String cargo;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Funcionario() {
		this.status = Status.ATIVO;
	}

	public Funcionario(Long id, String nome, String cpf, Date dataNascimento, Double salario, String cargo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
		this.cargo = cargo;
	}
	
	public boolean isAtivo() {
		return Status.ATIVO.equals(this.status);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
