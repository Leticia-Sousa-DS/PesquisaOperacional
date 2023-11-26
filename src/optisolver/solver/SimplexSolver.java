package optisolver.solver;

import java.util.Arrays;

public class SimplexSolver {
    private double[][] tableau;
    private int linhas;
    private int colunas;

    public SimplexSolver(ProblemaOtimizacao problema) {
        inicializarTableau(problema);
    }

    public void resolver() {
        while (existeColunaPivot()) {
            int colunaPivot = encontrarColunaPivot();
            int linhaPivot = encontrarLinhaPivot(colunaPivot);
            fazerPivotamento(colunaPivot, linhaPivot);
        }
    }

    private void inicializarTableau(ProblemaOtimizacao problema) {
        linhas = problema.getRestricoes().size() + 1;
        colunas = problema.getVariaveis().size() + 2;  // Uma coluna para variáveis e outra para b
        tableau = new double[linhas][colunas];

        // Preenche a linha da função objetivo
        tableau[0][0] = 1;  // Z
        for (int j = 1; j < colunas - 1; j++) {
            tableau[0][j] = -problema.getVariaveis().get(j - 1).getCoeficienteFuncaoObjetivo();
        }

        // Preenche as linhas das restrições
        for (int i = 1; i < linhas; i++) {
            tableau[i][0] = 0;  // Variável de folga
            for (int j = 1; j < colunas - 1; j++) {
                tableau[i][j] = -problema.getVariaveis().get(j - 1).getCoeficientesRestricoes()[i - 1];
            }
            tableau[i][colunas - 1] = problema.getRestricoes().get(i - 1).getLimite();
        }
    }

    private boolean existeColunaPivot() {
        for (int j = 1; j < colunas - 1; j++) {
            if (tableau[0][j] < 0) {
                return true;
            }
        }
        return false;
    }

    private int encontrarColunaPivot() {
        int colunaPivot = 1;
        for (int j = 2; j < colunas - 1; j++) {
            if (tableau[0][j] < tableau[0][colunaPivot]) {
                colunaPivot = j;
            }
        }
        return colunaPivot;
    }

    private int encontrarLinhaPivot(int colunaPivot) {
        int linhaPivot = -1;
        for (int i = 1; i < linhas; i++) {
            if (tableau[i][colunaPivot] > 0) {
                if (linhaPivot == -1 || (tableau[i][colunas - 1] / tableau[i][colunaPivot]) <
                        (tableau[linhaPivot][colunas - 1] / tableau[linhaPivot][colunaPivot])) {
                    linhaPivot = i;
                }
            }
        }
        return linhaPivot;
    }

    private void fazerPivotamento(int colunaPivot, int linhaPivot) {
        double elementoPivot = tableau[linhaPivot][colunaPivot];
        for (int j = 0; j < colunas; j++) {
            tableau[linhaPivot][j] /= elementoPivot;
        }
        for (int i = 0; i < linhas; i++) {
            if (i != linhaPivot) {
                double fator = tableau[i][colunaPivot];
                for (int j = 0; j < colunas; j++) {
                    tableau[i][j] -= fator * tableau[linhaPivot][j];
                }
            }
        }
    }

    public void exibirSolucao() {
        System.out.println("Solução Ótima:");
        System.out.println("Z = " + (-tableau[0][colunas - 1]));

        for (int j = 1; j < colunas - 1; j++) {
            int variavelIndex = j;
            System.out.println("X" + variavelIndex + " = " + tableau[variavelIndex][colunas - 1]);
        }
    }
}
