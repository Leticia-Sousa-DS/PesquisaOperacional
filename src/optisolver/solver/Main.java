package optisolver.solver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de variáveis: ");
        int numVariaveis = scanner.nextInt();

        System.out.print("Digite o número de restrições: ");
        int numRestricoes = scanner.nextInt();

        // Criar um problema de otimização com base na entrada do usuário
        ProblemaOtimizacao problema = criarProblemaOtimizacao(scanner, numVariaveis, numRestricoes);

        // Resolver o problema utilizando SimplexSolver
        SimplexSolver simplexSolver = new SimplexSolver(problema);
        simplexSolver.resolver();

        // Exibir a solução ótima
        simplexSolver.exibirSolucao();
    }

    private static ProblemaOtimizacao criarProblemaOtimizacao(Scanner scanner, int numVariaveis, int numRestricoes) {
        ProblemaOtimizacao problema = new ProblemaOtimizacao();

        // Adicionar variáveis
        for (int i = 1; i <= numVariaveis; i++) {
            System.out.print("Digite o coeficiente da variável x" + i + " na função objetivo: ");
            double coeficienteObjetivo = scanner.nextDouble();

            double[] coeficientesRestricoes = new double[numRestricoes];
            for (int j = 1; j <= numRestricoes; j++) {
                System.out.print("Digite o coeficiente da variável x" + i + " na restrição " + j + ": ");
                coeficientesRestricoes[j - 1] = scanner.nextDouble();
            }

            Variavel variavel = new Variavel(coeficienteObjetivo, coeficientesRestricoes);
            problema.adicionarVariavel(variavel);
        }

        // Adicionar restrições
        for (int i = 1; i <= numRestricoes; i++) {
            double[] coeficientesVariaveis = new double[numVariaveis];

            for (int j = 1; j <= numVariaveis; j++) {
                System.out.print("Digite o coeficiente da variável x" + j + " na restrição " + i + ": ");
                coeficientesVariaveis[j - 1] = scanner.nextDouble();
            }

            System.out.print("Digite o limite da restrição " + i + ": ");
            double limite = scanner.nextDouble();

            Restricao restricao = new Restricao(coeficientesVariaveis, limite);
            problema.adicionarRestricao(restricao);
        }

        return problema;
    }
}
