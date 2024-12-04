package crud;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Desenvolvedora {
    private int id;
    private String nome;
    private LocalDate dataFundacao;
    private List<Jogo> jogos;

    public Desenvolvedora(int id, String nome, LocalDate dataFundacao) {
        if (dataFundacao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de fundação não pode ser no futuro.");
        }
        this.id = id;
        this.nome = nome;
        this.dataFundacao = dataFundacao;
        this.jogos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void adicionarJogo(Jogo jogo) {
        jogos.add(jogo);
    }

	@Override
	public String toString() {
		return "Desenvolvedora Id: " + id + ", Nome: " + nome + ", Data de Fundacao: " + dataFundacao + ", Jogos: " + jogos;
	}
}
