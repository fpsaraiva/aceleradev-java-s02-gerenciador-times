package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

public class Repositorio {

	private List<Time> times;
	private List<Jogador> jogadores;
	
	public Repositorio() {
		times = new ArrayList<>();
		jogadores = new ArrayList<>(); 
	}
	
	public void incluirTime(Time time) {
		
		if(existeTime(time.getId())) {
			throw new IdentificadorUtilizadoException();
		}
		
		times.add(time);
	}
	
	public void incluirJogador(Jogador jogador) {
		
		if (!existeTime(jogador.getIdTime())) {
			throw new TimeNaoEncontradoException();
		}
		
		if (existeJogador(jogador.getId())) {
            throw new IdentificadorUtilizadoException();
		}
		
		jogadores.add(jogador);
	}
	
	public void definirCapitao(Long idJogador) {
		
		Jogador jogador = buscarJogador(idJogador);
		
		if(jogador != null) {
			Long idTime = jogador.getIdTime();
			buscarTime(idTime).setIdCapitao(idJogador);

		} else {
			throw new JogadorNaoEncontradoException();
		}
	}
	
	public Long buscarCapitao(Long idTime) {
		
		if (!existeTime(idTime)) {
			throw new TimeNaoEncontradoException();
		}
		
		Long idCapitao = buscarTime(idTime).getIdCapitao();
		
		if(idCapitao == -1L) {
			throw new CapitaoNaoInformadoException();
		}
		
		return idCapitao;
	}
	
	public String buscarNomeJogador(Long idJogador) {
		
		for(int i = 0; i < jogadores.size(); i++) {
			if(jogadores.get(i).getId().equals(idJogador)) {
				return jogadores.get(i).getNome();

			}
		}
		throw new JogadorNaoEncontradoException();
	}
	
	public String buscarNomeTime(Long idTime) {
		
		for(int i = 0; i < times.size(); i++) {
			if(times.get(i).getId().equals(idTime)) {
				return times.get(i).getNome();
			}
		}
		throw new TimeNaoEncontradoException();
	}
	
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		
		if(times.stream().noneMatch(time ->time.getId().equals(idTime))) {
			throw new TimeNaoEncontradoException();
		}

		return jogadores.stream()
			.filter(buscarjogadores -> buscarjogadores.getIdTime().equals(idTime))
			.map(Jogador :: getId)
			.collect(Collectors.toList());
	}
	
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		
		if(times.stream().noneMatch(T ->T.getId().equals(idTime))) {
			throw new TimeNaoEncontradoException();
		}

		Integer nivelHabilidade = 0;
		Jogador melhorjogador = null;
		
		for(Jogador joga : jogadores) {
			if(joga.getNivelHabilidade() > nivelHabilidade) {
				nivelHabilidade = joga.getNivelHabilidade();
				melhorjogador = joga;
			}
		}

		return melhorjogador.getId();
	}
	
	public Long buscarJogadorMaisVelho(Long idTime) {
		
		if(times.stream().noneMatch(T ->T.getId().equals(idTime))) {
			throw new TimeNaoEncontradoException();
		}

		LocalDate dataNascimento = LocalDate.now();
		Jogador maisVelho = null;
		
		for(Jogador joga : jogadores) {
			if(joga.getIdTime() == idTime && joga.getDataNascimento().isBefore(dataNascimento)) {
				dataNascimento = joga.getDataNascimento();
				maisVelho = joga;

			}
		}

		return maisVelho.getId();
	}

	public List<Long> buscarTimes() {
		
		List<Long> listaDeIds = new ArrayList<>();
		
		for (Time t:times) {
			listaDeIds.add(t.getId());
		}
		
		if(times == null) {
			return null;
		}
		
		Collections.sort(listaDeIds);
		return listaDeIds;

	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		
		if(times.stream().noneMatch(T ->T.getId().equals(idTime))) {
			throw new TimeNaoEncontradoException();
		}

		BigDecimal salario = BigDecimal.ZERO;
		Jogador salariomaior = null;
		
		for(Jogador joga : jogadores ){
			if(joga.getIdTime() == idTime && joga.getSalario().compareTo(salario)>0) {
				salario = joga.getSalario();
				salariomaior = joga;
			}
		}

		return salariomaior.getId();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {

		for(int i = 0; i < jogadores.size(); i++){
			if(jogadores.get(i).getId().equals(idJogador)) {
				return jogadores.get(i).getSalario();
			}
		}
		throw new JogadorNaoEncontradoException();
	}

	public List<Long> buscarTopJogadores(Integer top) {

		Collections.sort(jogadores, new JogadorComparator());

		List<Long> idTopJogadores = new ArrayList<>();
		for(int i = 0; i < top && i < jogadores.size(); i++) {
			idTopJogadores.add(jogadores.get(i).getId());
		}
		return idTopJogadores;
	}
	
	//metodos auxiliares
	
	public boolean existeTime(Long idTime) {
		return times.stream().anyMatch(time -> time.getId() == idTime);
	}
	
	public boolean existeJogador(Long idJogador) {
		return jogadores.stream().anyMatch(jogador -> jogador.getId() == idJogador);
	}
	
	public Jogador buscarJogador(Long idJogador) {
		
		for(int i = 0; i < jogadores.size(); i++) {
			if(jogadores.get(i).getId().equals(idJogador)) {
				return  jogadores.get(i);
			}
		}
		return null;
	}
	
	public Time buscarTime(Long idTime) {
		
		for(int i = 0; i < times.size(); i++) {
			if(times.get(i).getId().equals(idTime)) {
				return  times.get(i);
			}
		}
		return null;
	}
	
}
