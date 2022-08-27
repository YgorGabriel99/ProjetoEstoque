/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aplicacao.Fornecedor;
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


@WebServlet(name = "FornecedorDAO", urlPatterns = {"/FornecedorDAO"})
public class FornecedorDAO extends HttpServlet {

 private Connection conexao;
    
    public FornecedorDAO(){
   
        try{
               conexao= Conexao.criaConexao();
        
        }
        catch(Exception e) {
             System.out.println("Erro da conexão DAO");
              System.out.println(e);
        }
        
    }
    public ArrayList<Fornecedor> getLista(){
        
        ArrayList<Fornecedor> resultado  = new ArrayList<>();
        
        try{
            Statement stmt= conexao.createStatement();
            ResultSet rs= stmt.executeQuery("select * from fornecedores");
           
            while(rs.next()){
                Fornecedor fornecedor= new Fornecedor();
                
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setRazaoSocial(rs.getString("razao_social"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                resultado.add(fornecedor);
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
            
        } 
          return resultado;
    }
    
    public Fornecedor getFornecedorPorID(int codigo){
        
        Fornecedor fornecedor =new Fornecedor();
        
        try{
            String sql="SELECT * FROM fornecedores WHERE id = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
               
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setRazaoSocial(rs.getString("razao_social"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
             
            }  
        } catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        }
        return fornecedor;
        
    }
    
    public boolean gravar (Fornecedor fornecedor){
        
        try{
            String sql;
                if(fornecedor.getId()==0){
                    sql="INSERT INTO fornecedores (razao_social, cnpj , endereco, bairro, cidade, uf, cep, telefone, email) VALUES(?,?,?,?,?,?,?,?,?)";   
                }else{
                    sql="UPDATE fornecedores SET razao_social= ?, cnpj=?, endereco=?, bairro=?, cidade=?, uf=?, cep=?, telefone=?, email=? WHERE id=?";
                    
                }
            
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setString(1,fornecedor.getRazaoSocial());
            ps.setString(2,fornecedor.getCnpj());
            ps.setString(3,fornecedor.getEndereco());
            ps.setString(4,fornecedor.getBairro());
            ps.setString(5,fornecedor.getCidade());
            ps.setString(6,fornecedor.getUf());
            ps.setString(7,fornecedor.getCep());
            ps.setString(8,fornecedor.getTelefone());
            ps.setString(9,fornecedor.getEmail());
           
            if(fornecedor.getId()>0){
               ps.setInt(10,fornecedor.getId());
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
            String sql="DELETE FROM fornecedores WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch(SQLException e){
             System.out.println("Erro de SQL" + e.getMessage());
             return false;
        }
    }
    public Fornecedor getFornecedorPorCnpj(String cnpj){
        
        Fornecedor fornecedor =new Fornecedor();
        
        try{
            String sql="SELECT * FROM fornecedores WHERE cnpj = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setString(1, cnpj);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
               
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setRazaoSocial(rs.getString("razao_social"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
             
            }  
        } catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        }
        return fornecedor;
    }
}