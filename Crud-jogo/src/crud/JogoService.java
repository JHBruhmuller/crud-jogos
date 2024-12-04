package crud;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JogoService implements JogoServiceInterface {

	@Override
	public void adicionarJogo(Jogo jogo) {
	    String sqlDesenvolvedora = "SELECT dataFundacao FROM desenvolvedoras WHERE id = ?";
	    try (Connection conn = ConexaoMySQL.getConexaoMySQL();
	         PreparedStatement stmtDesenvolvedora = conn.prepareStatement(sqlDesenvolvedora)) {

	    	
	        stmtDesenvolvedora.setInt(1, jogo.getIdDesenvolvedora());
	        ResultSet rs = stmtDesenvolvedora.executeQuery();
	        if (rs.next()) {
	            LocalDate dataFundacao = rs.getDate("dataFundacao").toLocalDate();
	            if (jogo.getDataLancamento().isBefore(dataFundacao)) {
	                throw new IllegalArgumentException("O jogo não pode ser lançado antes da data de fundação da desenvolvedora.");
	            }
	        } else {
	            throw new IllegalArgumentException("Desenvolvedora não encontrada.");
	        }

	        
	        String sql = "INSERT INTO jogos (id, nome, dataLancamento, preco, genero, idDesenvolvedora) VALUES (?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, jogo.getId());
	            stmt.setString(2, jogo.getNome());
	            stmt.setDate(3, Date.valueOf(jogo.getDataLancamento()));
	            stmt.setDouble(4, jogo.getPreco());
	            stmt.setString(5, jogo.getGenero());
	            stmt.setInt(6, jogo.getIdDesenvolvedora());
	            stmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

    @Override
    public Jogo buscarJogo(int id) {
        String sql = "SELECT * FROM jogos WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                LocalDate dataLancamento = rs.getDate("dataLancamento").toLocalDate();
                double preco = rs.getDouble("preco");
                String genero = rs.getString("genero");
                int idDesenvolvedora = rs.getInt("idDesenvolvedora");
                return new Jogo(id, nome, dataLancamento, preco, genero, idDesenvolvedora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void atualizarJogo(Jogo jogo) {
        String sql = "UPDATE jogos SET nome = ?, dataLancamento = ?, preco = ?, genero = ?, idDesenvolvedora = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogo.getNome());
            stmt.setDate(2, Date.valueOf(jogo.getDataLancamento()));
            stmt.setDouble(3, jogo.getPreco());
            stmt.setString(4, jogo.getGenero());
            stmt.setInt(5, jogo.getIdDesenvolvedora());
            stmt.setInt(6, jogo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerJogo(int id) {
        String sql = "DELETE FROM jogos WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Jogo> listarJogos() {
        List<Jogo> jogos = new ArrayList<>();
        String sql = "SELECT * FROM jogos";
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                LocalDate dataLancamento = rs.getDate("dataLancamento").toLocalDate();
                double preco = rs.getDouble("preco");
                String genero = rs.getString("genero");
                int idDesenvolvedora = rs.getInt("idDesenvolvedora");
                jogos.add(new Jogo(id, nome, dataLancamento, preco, genero, idDesenvolvedora));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jogos;
    }
}
