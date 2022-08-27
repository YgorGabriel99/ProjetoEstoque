/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aplicacao.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ygorf
 */
@WebServlet(name = "ClienteDAO", urlPatterns = {"/ClienteDAO"})
public class ClienteDAO extends HttpServlet {

    private Connection conexao;
    
    public ClienteDAO(){
   
        try{
               conexao= Conexao.criaConexao();
        
        }
        catch(Exception e) {
             System.out.println("Erro da conexão DAO");
              System.out.println(e);
        }
        
    }
    public ArrayList<Cliente> getLista(){
        
        ArrayList<Cliente> resultado  = new ArrayList<>();
        
        try{
            Statement stmt= conexao.createStatement();
            ResultSet rs= stmt.executeQuery("select * from clientes");
           
            while(rs.next()){
                Cliente cliente= new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                resultado.add(cliente);
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
            
        } 
          return resultado;
    }
    
    public Cliente getClientePorID(int codigo){
        
        Cliente cliente =new Cliente();
        
        try{
            String sql="SELECT * FROM clientes WHERE id = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
               
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
             
            }  
        } catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        }
        return cliente;
        
    }
    
    public boolean gravar (Cliente cliente){
        
        try{
            String sql;
                if(cliente.getId()==0){
                    sql="INSERT INTO clientes (nome, cpf , endereco, bairro, cidade, uf, cep, telefone, email) VALUES(?,?,?,?,?,?,?,?,?)";   
                }else{
                    sql="UPDATE clientes SET nome= ?, cpf=?, endereco=?, bairro=?, cidade=?, uf=?, cep=?, telefone=?, email=? WHERE id=?";
                    
                }
            
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setString(1,cliente.getNome());
            ps.setString(2,cliente.getCpf());
            ps.setString(3,cliente.getEndereco());
            ps.setString(4,cliente.getBairro());
            ps.setString(5,cliente.getCidade());
            ps.setString(6,cliente.getUf());
            ps.setString(7,cliente.getCep());
            ps.setString(8,cliente.getTelefone());
            ps.setString(9,cliente.getEmail());
           
            if(cliente.getId()>0){
               ps.setInt(10,cliente.getId());
            }
            ps.execute();
            
            return true;
            
        }catch(SQLException e){
             System.out.println("Erro de SQL" + e.getMessage());
             return false;
        }
    }
    
    public boolean excluir(int id){
        
        try{
            String sql="DELETE FROM clientes WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch(SQLException e){
             System.out.println("Erro de SQL" + e.getMessage());
             return false;
        }
    }
    public Cliente getClientePorCPF(String cpf){
        
        Cliente cliente =new Cliente();
        
        try{
            String sql="SELECT * FROM clientes WHERE cpf = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
               
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
             
            }  
        } catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        }
        return cliente;
    }
}