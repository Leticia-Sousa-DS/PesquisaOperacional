package optisolver.solver;

import java.util.ArrayList;
import java.util.List;

public class ProblemaOtimizacao {
    private List<Variavel> variaveis;
    private List<Restricao> restricoes;

    public ProblemaOtimizacao() {
        this.variaveis = new ArrayList<>();
        this.restricoes = new ArrayList<>();
    }

    public void adicionarVariavel(Variavel variavel) {
        variaveis.add(variavel);
    }

    public void adicionarRestricao(Restricao restricao) {
        restricoes.add(restricao);
    }

    public List<Variavel> getVariaveis() {
        return variaveis;
    }

    public List<Restricao> getRestricoes() {
        return restricoes;
    }
}
