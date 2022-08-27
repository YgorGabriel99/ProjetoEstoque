/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aplicacao.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UsuarioDAO", urlPatterns = {"/UsuarioDAO"})
public class UsuarioDAO extends HttpServlet {

    private Connection conexao;
    
    public UsuarioDAO(){
   
        try{
               conexao= Conexao.criaConexao();
        
        }
        catch(Exception e) {
             System.out.println("Erro da conexão DAO");
              System.out.println(e);
        }
        
    }
    public ArrayList<Usuario> getLista(){
        
        ArrayList<Usuario> resultado  = new ArrayList<>();
        
        try{
            Statement stmt= conexao.createStatement();
            ResultSet rs= stmt.executeQuery("select * from usuarios");
           
            while(rs.next()){
                Usuario usuario= new Usuario();
                
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                resultado.add(usuario);
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
            
        } 
          return resultado;
    }
    
    public Usuario getUsuarioPorID(int codigo){
        
        Usuario usuario=new Usuario();
        
        try{
            String sql="SELECT * FROM usuarios WHERE id = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
             
            }  
        } catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        }
        return usuario;
        
    }
    
    public boolean gravar (Usuario usuario){
        
        try{
            String sql;
                if(usuario.getId()==0){
                    sql="INSERT INTO usuarios (nome, cpf , senha, tipo) VALUES(?,?,?,?)";   
                }else{
                    sql="UPDATE usuarios SET nome= ?, cpf=?, senha=?, tipo=? WHERE id=?";
                    
                }
            
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setString(1,usuario.getNome());
            ps.setString(2,usuario.getCpf());
            ps.setString(3,usuario.getSenha());
            ps.setString(4,usuario.getTipo());
            
            
            if(usuario.getId()>0){
               ps.setInt(5,usuario.getId());
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
            String sql="DELETE FROM usuarios WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch(SQLException e){
             System.out.println("Erro de SQL" + e.getMessage());
             return false;
        }
    }
    public Usuario getUsuarioPorCpf(String cpf){
       
       Usuario usuario =new Usuario();
        
        try{
            String sql="SELECT * FROM usuarios WHERE cpf = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
               
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
               
                
             
            }  
        } catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        }
        return usuario;
        
    }
    public boolean verificarUsuario(String cpf){
        
       try{ 
            String sql="SELECT * FROM usuarios WHERE cpf = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
            return true;
            }else{
                return false;
            }
       }catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        return false;
        
       }
       
    }
    
    public boolean verificarSenha(String cpf, String senha){
         try{ 
            String sql="SELECT * FROM usuarios WHERE cpf = ? and senha = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, senha);
            System.out.println("verificou a senha");
           
            
            ResultSet rs= ps.executeQuery();
             if(rs.next()){
                return true;
             }else{
                 return false;
             }
            
            
            }catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
            return false;
        
             }
       
    }
            
}