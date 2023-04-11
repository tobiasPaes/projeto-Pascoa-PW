package com.example.proj_pascoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CarroDAO {
    public void cadastrarCarro(Carro c){
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement("insert into carros(placa, ano, cor, marca_modelo) values (?, ?, ?, ?)");
            stmt.setString(1, c.getPlaca());
            stmt.setInt(2, c.getAno());
            stmt.setString(3, c.getCor());
            stmt.setString(4, c.getMarcaModelo());
            stmt.execute();

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Carro buscarCarro(String placa){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Carro c = null;

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement("select * from carros where placa = ?");
            stmt.setString(1, placa);
            rs = stmt.executeQuery();

            if(rs.next()){
                c = new Carro(rs.getString("placa"), rs.getInt("ano"), rs.getString("cor"), rs.getString("marca_modelo"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }

    public ArrayList<Carro> listarCarros(){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Carro> lista = new ArrayList<>();
        Carro c = null;

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement("select * from carros");
            rs = stmt.executeQuery();

            while(rs.next()){
                c = new Carro(rs.getString("placa"), rs.getInt("ano"), rs.getString("cor"), rs.getString("marca_modelo"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);        
        }
        return lista;
    }

    public void alterarCarro(Carro c){
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement("update carros set ano = ?, cor = ?, marca_modelo = ? where placa = ?");
            stmt.setInt(1, c.getAno());
            stmt.setString(2, c.getCor());
            stmt.setString(3, c.getMarcaModelo());
            stmt.setString(4, c.getPlaca());
            stmt.execute();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void excluirCarro(String placa){
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = Conexao.getConnection();
            stmt = con.prepareStatement("delete from carros where placa = ?");
            stmt.setString(1, placa);
            stmt.execute();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
