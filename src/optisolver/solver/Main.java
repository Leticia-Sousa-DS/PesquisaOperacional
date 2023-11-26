package optisolver.solver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o n�mero de vari�veis: ");
        int numVariaveis = scanner.nextInt();

        System.out.print("Digite o n�mero de restri��es: ");
        int numRestricoes = scanner.nextInt();

        // Criar um problema de otimiza��o com base na entrada do usu�rio
        ProblemaOtimizacao problema = criarProblemaOtimizacao(scanner, numVariaveis, numRestricoes);

        // Resolver o problema utilizando SimplexSolver
        SimplexSolver simplexSolver = new SimplexSolver(problema);
        simplexSolver.resolver();

        // Exibir a solu��o �tima
        simplexSolver.exibirSolucao();
    }

    private static ProblemaOtimizacao criarProblemaOtimizacao(Scanner scanner, int numVariaveis, int numRestricoes) {
        ProblemaOtimizacao problema = new ProblemaOtimizacao();

        // Adicionar vari�veis
        for (int i = 1; i <= numVariaveis; i++) {
            System.out.print("Digite o coeficiente da vari�vel x" + i + " na fun��o objetivo: ");
            double coeficienteObjetivo = scanner.nextDouble();

            double[] coeficientesRestricoes = new double[numRestricoes];
            for (int j = 1; j <= numRestricoes; j++) {
                System.out.print("Digite o coeficiente da vari�vel x" + i + " na restri��o " + j + ": ");
                coeficientesRestricoes[j - 1] = scanner.nextDouble();
            }

            Variavel variavel = new Variavel(coeficienteObjetivo, coeficientesRestricoes);
            problema.adicionarVariavel(variavel);
        }

        // Adicionar restri��es
        for (int i = 1; i <= numRestricoes; i++) {
            double[] coeficientesVariaveis = new double[numVariaveis];

            for (int j = 1; j <= numVariaveis; j++) {
                System.out.print("Digite o coeficiente da vari�vel x" + j + " na restri��o " + i + ": ");
                coeficientesVariaveis[j - 1] = scanner.nextDouble();
            }

            System.out.print("Digite o limite da restri��o " + i + ": ");
            double limite = scanner.nextDouble();

            Restricao restricao = new Restricao(coeficientesVariaveis, limite);
            problema.adicionarRestricao(restricao);
        }

        return problema;
    }
}
