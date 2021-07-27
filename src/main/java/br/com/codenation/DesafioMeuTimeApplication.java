package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private Repositorio repositorio;
	
	public DesafioMeuTimeApplication() {
		repositorio = new Repositorio();
	}
	
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		repositorio.incluirTime(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		repositorio.incluirJogador(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	public void definirCapitao(Long idJogador) {
		repositorio.definirCapitao(idJogador);
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		return repositorio.buscarCapitao(idTime);
	}

	public String buscarNomeJogador(Long idJogador) {
		return repositorio.buscarNomeJogador(idJogador);
	}

	public String buscarNomeTime(Long idTime) {
		return repositorio.buscarNomeTime(idTime);
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return repositorio.buscarJogadoresDoTime(idTime);
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		return repositorio.buscarMelhorJogadorDoTime(idTime);
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		return repositorio.buscarJogadorMaisVelho(idTime);
	}

	public List<Long> buscarTimes() {
		return repositorio.buscarTimes();
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		return repositorio.buscarJogadorMaiorSalario(idTime);
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return repositorio.buscarSalarioDoJogador(idJogador);
	}

	public List<Long> buscarTopJogadores(Integer top) {
		return repositorio.buscarTopJogadores(top);
	}

}
