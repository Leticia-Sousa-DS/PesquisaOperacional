package optisolver.solver;

public class Restricao {
    private double[] coeficientesVariaveis;
    private double limite;

    public Restricao(double[] coeficientesVariaveis, double limite) {
        this.coeficientesVariaveis = coeficientesVariaveis;
        this.limite = limite;
    }

    public double[] getCoeficientesVariaveis() {
        return coeficientesVariaveis;
    }

    public double getLimite() {
        return limite;
    }
}
