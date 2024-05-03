/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;

public class ProdutosDAO {
    private Connection conn;
    private PreparedStatement prep;
    private ResultSet resultset;

    public void cadastrarProduto(ProdutosDTO produto) {
    try {
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());

        prep.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
    }
}


    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        try {
            conn = new conectaDAO().connectDB();
            String sql = "SELECT * FROM produtos";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        }
        return listagem;
    }
}
