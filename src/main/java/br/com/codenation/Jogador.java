package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {

	private Long id;
	private Long idTime;
	private String nome;
	private LocalDate dataNascimento;
	private Integer nivelHabilidade;
	private BigDecimal salario;
	private boolean capitao;
	
	public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		this.id = id;
		this.idTime = idTime;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nivelHabilidade = nivelHabilidade;
		this.salario = salario;
	}
	
	public Long getId() {
        return id;
    }
	
    public Long getIdTime() {
        return idTime;
    }
    
    public String getNome() {
        return nome;
    }
    
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

    public int getNivelHabilidade() {
        return nivelHabilidade;
    }
	
    public BigDecimal getSalario() {
        return salario;
    }

    //metodos auxiliares
    
    public void setCapitao(boolean capitao) {
        this.capitao = capitao;
    }
    
    public int getIdade() {
        int idade = LocalDate.now().getYear() - dataNascimento.getYear();
        if (LocalDate.now().getDayOfYear() <= dataNascimento.getDayOfYear()) {
            idade -= 1;
        }
        return idade;
    }

}
