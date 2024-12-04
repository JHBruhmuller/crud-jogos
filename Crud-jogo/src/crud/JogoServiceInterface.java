package crud;

import java.util.List;

public interface JogoServiceInterface {
    void adicionarJogo(Jogo jogo);
    Jogo buscarJogo(int id);
    void atualizarJogo(Jogo jogo);
    void removerJogo(int id);
    List<Jogo> listarJogos();
}
