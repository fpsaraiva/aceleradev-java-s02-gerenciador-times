package br.com.codenation;

import java.util.Comparator;

public class JogadorComparator implements Comparator<Jogador> {
    @Override
    public int compare(Jogador j1, Jogador j2) {
        if(j1.getNivelHabilidade() > j2.getNivelHabilidade()) {
            return -1;

        } if(j2.getNivelHabilidade() > j1.getNivelHabilidade()){
            return 1;

        } else if(j1.getId() < j2.getId()){
            return -1;

        } else if(j2.getId() < j1.getId()){
            return 1;

        }
        return 0;
    }
}
