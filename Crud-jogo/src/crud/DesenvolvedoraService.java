package crud;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DesenvolvedoraService implements DesenvolvedoraServiceInterface {

	@Override
	public void adicionarDesenvolvedora(Desenvolvedora desenvolvedora) {
	    String sqlVerificarNome = "SELECT COUNT(*) FROM desenvolvedoras WHERE nome = ?";
	    try (Connection conn = ConexaoMySQL.getConexaoMySQL();
	         PreparedStatement stmtVerificar = conn.prepareStatement(sqlVerificarNome)) {

	       
	        stmtVerificar.setString(1, desenvolvedora.getNome());
	        ResultSet rs = stmtVerificar.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            throw new IllegalArgumentException("Já existe uma desenvolvedora com esse nome.");
	        }

	        
	        String sql = "INSERT INTO desenvolvedoras (id, nome, dataFundacao) VALUES (?, ?, ?)";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, desenvolvedora.getId());
	            stmt.setString(2, desenvolvedora.getNome());
	            stmt.setDate(3, Date.valueOf(desenvolvedora.getDataFundacao()));
	            stmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


    @Override
    public Desenvolvedora buscarDesenvolvedora(int id) {
        String sqlDesenvolvedora = "SELECT * FROM desenvolvedoras WHERE id = ?";
        String sqlJogos = "SELECT * FROM jogos WHERE idDesenvolvedora = ?";
        Desenvolvedora desenvolvedora = null;

        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement stmtDesenvolvedora = conn.prepareStatement(sqlDesenvolvedora);
             PreparedStatement stmtJogos = conn.prepareStatement(sqlJogos)) {

            stmtDesenvolvedora.setInt(1, id);
            ResultSet rsDesenvolvedora = stmtDesenvolvedora.executeQuery();
            if (rsDesenvolvedora.next()) {
                String nome = rsDesenvolvedora.getString("nome");
                LocalDate dataFundacao = rsDesenvolvedora.getDate("dataFundacao").toLocalDate();
                desenvolvedora = new Desenvolvedora(id, nome, dataFundacao);

                stmtJogos.setInt(1, id);
                ResultSet rsJogos = stmtJogos.executeQuery();
                while (rsJogos.next()) {
                    int idJogo = rsJogos.getInt("id");
                    String nomeJogo = rsJogos.getString("nome");
                    LocalDate dataLancamento = rsJogos.getDate("dataLancamento").toLocalDate();
                    double preco = rsJogos.getDouble("preco");
                    String genero = rsJogos.getString("genero");
                    int idDesenvolvedora = rsJogos.getInt("idDesenvolvedora");

                    Jogo jogo = new Jogo(idJogo, nomeJogo, dataLancamento, preco, genero, idDesenvolvedora);
                    desenvolvedora.adicionarJogo(jogo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return desenvolvedora;
    }


    @Override
    public void atualizarDesenvolvedora(Desenvolvedora desenvolvedora) {
        String sql = "UPDATE desenvolvedoras SET nome = ?, dataFundacao = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, desenvolvedora.getNome());
            stmt.setDate(2, Date.valueOf(desenvolvedora.getDataFundacao()));
            stmt.setInt(3, desenvolvedora.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerDesenvolvedora(int id) {
        String sqlVerificarJogos = "SELECT COUNT(*) FROM jogos WHERE idDesenvolvedora = ?";
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement stmtVerificar = conn.prepareStatement(sqlVerificarJogos)) {

            stmtVerificar.setInt(1, id);
            ResultSet rs = stmtVerificar.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new IllegalArgumentException("Não é possível remover a desenvolvedora porque existem jogos associados.");
            }

            String sql = "DELETE FROM desenvolvedoras WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Desenvolvedora> listarDesenvolvedoras() {
        List<Desenvolvedora> desenvolvedoras = new ArrayList<>();
        String sql = "SELECT * FROM desenvolvedoras";
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                LocalDate dataFundacao = rs.getDate("dataFundacao").toLocalDate();
                desenvolvedoras.add(new Desenvolvedora(id, nome, dataFundacao));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return desenvolvedoras;
    }
}
