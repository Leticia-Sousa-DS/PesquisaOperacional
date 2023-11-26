package optisolver.solver;

public class Variavel {
    private double coeficienteFuncaoObjetivo;
    private double[] coeficientesRestricoes;

    public Variavel(double coeficienteFuncaoObjetivo, double[] coeficientesRestricoes) {
        this.coeficienteFuncaoObjetivo = coeficienteFuncaoObjetivo;
        this.coeficientesRestricoes = coeficientesRestricoes;
    }

    public double getCoeficienteFuncaoObjetivo() {
        return coeficienteFuncaoObjetivo;
    }

    public double[] getCoeficientesRestricoes() {
        return coeficientesRestricoes;
    }
}
