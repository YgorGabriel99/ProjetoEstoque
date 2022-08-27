/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aplicacao.Compra;
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


@WebServlet(name = "CompraDAO", urlPatterns = {"/CompraDAO"})
public class CompraDAO extends HttpServlet {

 private Connection conexao;
    
    public CompraDAO(){
   
        try{
               conexao= Conexao.criaConexao();
        
        }
        catch(Exception e) {
             System.out.println("Erro da conexão DAO");
              System.out.println(e);
        }
        
    }
    public ArrayList<Compra> getLista(){
        
        ArrayList<Compra> resultado  = new ArrayList<>();
        
        try{
            Statement stmt= conexao.createStatement();
            ResultSet rs= stmt.executeQuery("select * from compras");
           
            while(rs.next()){
                Compra compra= new Compra();
                
                compra.setId(rs.getInt("id"));
                compra.setQuantidade(rs.getInt("quantidade_compra"));
                compra.setDataCompra(rs.getDate("data_compra"));
                compra.setValorCompra(rs.getInt("valor_compra"));
                compra.setIdFornecedor(rs.getInt("id_fornecedor"));
                compra.setIdProduto(rs.getInt("id_produto"));
                compra.setIdComprador(rs.getInt("id_comprador"));
                resultado.add(compra);
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
            
        } 
          return resultado;
    }
    
    public Compra getCompraPorID(int codigo){
        
        Compra compra =new Compra();
        
        try{
            String sql="SELECT * FROM compras WHERE id = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
               
                compra.setId(rs.getInt("id"));
                compra.setQuantidade(rs.getInt("quantidade_compra"));
                compra.setDataCompra(rs.getDate("data_compra"));
                compra.setValorCompra(rs.getInt("valor_compra"));
                compra.setIdFornecedor(rs.getInt("id_fornecedor"));
                compra.setIdProduto(rs.getInt("id_produto"));
                compra.setIdComprador(rs.getInt("id_comprador"));
             
            }  
        } catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        }
        return compra;
        
    }
    
    public boolean gravar (Compra compra){
        
        try{
            String sql;
                if(compra.getId()==0){
                    sql="INSERT INTO compras (quantidade_compra, data_compra , valor_compra, id_fornecedor, id_produto, id_comprador) VALUES(?,?,?,?,?,?)";   
                }else{
                    sql="UPDATE compras SET quantidade_compra= ?, data_compra=?, valor_compra=?, id_fornecedor=?, id_produto=?, id_comprador=? WHERE id=?";
                    
                }
            
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setInt(1,compra.getQuantidade());
            ps.setDate(2,compra.getDataCompra());
            ps.setInt(3,compra.getValorCompra());
            ps.setInt(4,compra.getIdFornecedor());
            ps.setInt(5,compra.getIdProduto());
            ps.setInt(6,compra.getIdComprador());
           
            if(compra.getId()>0){
               ps.setInt(7,compra.getId());
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
            String sql="DELETE FROM compras WHERE id=?";
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