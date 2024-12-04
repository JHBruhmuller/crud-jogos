package crud;

import java.util.List;

public interface DesenvolvedoraServiceInterface {
    void adicionarDesenvolvedora(Desenvolvedora desenvolvedora);
    Desenvolvedora buscarDesenvolvedora(int id);
    void atualizarDesenvolvedora(Desenvolvedora desenvolvedora);
    void removerDesenvolvedora(int id);
    List<Desenvolvedora> listarDesenvolvedoras();
}
