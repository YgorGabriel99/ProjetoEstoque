/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aplicacao.Categoria;
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


@WebServlet(name = "CategoriaDAO", urlPatterns = {"/CategoriaDAO"})
public class CategoriaDAO extends HttpServlet {

 private Connection conexao;
    
    public CategoriaDAO(){
   
        try{
               conexao= Conexao.criaConexao();
        
        }
        catch(Exception e) {
             System.out.println("Erro da conexão DAO");
              System.out.println(e);
        }
        
    }
    public ArrayList<Categoria> getLista(){
        
        ArrayList<Categoria> resultado  = new ArrayList<>();
        
        try{
            Statement stmt= conexao.createStatement();
            ResultSet rs= stmt.executeQuery("select * from categorias");
           
            while(rs.next()){
                Categoria categoria= new Categoria();
                
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome_categoria"));
                
                resultado.add(categoria);
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
            
        } 
          return resultado;
    }
    
    public Categoria getCategoriaPorID(int codigo){
        
        Categoria categoria =new Categoria();
        
        try{
            String sql="SELECT * FROM categorias WHERE id = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
               
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome_categoria"));
                
            }  
        } catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        }
        return categoria;
        
    }
    
    public boolean gravar (Categoria categoria){
        
        try{
            String sql;
                if(categoria.getId()==0){
                    sql="INSERT INTO categorias (nome_categoria) VALUES(?)";   
                }else{
                    sql="UPDATE categorias SET nome_categoria= ? WHERE id=?";
                    
                }
            
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setString(1,categoria.getNome());
            
           
            if(categoria.getId()>0){
               ps.setInt(2,categoria.getId());
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
            String sql="DELETE FROM categorias WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch(SQLException e){
             System.out.println("Erro de SQL" + e.getMessage());
             return false;
        }
    }
   
}