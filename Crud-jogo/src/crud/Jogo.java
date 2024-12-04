package crud;

import java.time.LocalDate;

public class Jogo {
    private int id;
    private String nome;
    private LocalDate dataLancamento;
    private double preco;
    private String genero;
    private int idDesenvolvedora;

    public Jogo(int id, String nome, LocalDate dataLancamento, double preco, String genero, int idDesenvolvedora) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
        if (genero == null || genero.isEmpty()) {
            throw new IllegalArgumentException("O gênero do jogo deve ser definido.");
        }
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.preco = preco;
        this.genero = genero;
        this.idDesenvolvedora = idDesenvolvedora;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public double getPreco() {
        return preco;
    }

    public String getGenero() {
        return genero;
    }

    public int getIdDesenvolvedora() {
        return idDesenvolvedora;
    }

	@Override
	public String toString() {
		return "\nJogo Id: " + id + "\n Nome: " + nome + "\n Data de Lancamento: " + dataLancamento + "\n Preco: " + preco
				+ "\n Genero: " + genero + "\n Id da Desenvolvedora: " + idDesenvolvedora;
	}
}
