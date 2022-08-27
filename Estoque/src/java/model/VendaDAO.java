/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aplicacao.Venda;
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
@WebServlet(name = "VendaDAO", urlPatterns = {"/VendaDAO"})
public class VendaDAO extends HttpServlet {

     private Connection conexao;
    
    public VendaDAO(){
   
        try{
               conexao= Conexao.criaConexao();
        
        }
        catch(Exception e) {
             System.out.println("Erro da conexão DAO");
              System.out.println(e);
        }
        
    }
    public ArrayList<Venda> getLista(){
        
        ArrayList<Venda> resultado  = new ArrayList<>();
        
        try{
            Statement stmt= conexao.createStatement();
            ResultSet rs= stmt.executeQuery("select * from vendas");
           
            while(rs.next()){
                Venda venda= new Venda();
                
                venda.setId(rs.getInt("id"));
                venda.setQuantidade(rs.getInt("quantidade_venda"));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setValorVenda(rs.getDouble("valor_venda"));
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setIdProduto(rs.getInt("id_produto"));
                venda.setIdVendedor(rs.getInt("id_vendedor"));
                resultado.add(venda);
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
            
        } 
          return resultado;
    }
    
    public Venda getVendaPorID(int codigo){
        
        Venda venda =new Venda();
        
        try{
            String sql="SELECT * FROM vendas WHERE id = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
               
                venda.setId(rs.getInt("id"));
                venda.setQuantidade(rs.getInt("quantidade_venda"));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setValorVenda(rs.getDouble("valor_venda"));
                venda.setIdCliente(rs.getInt("id_cliente"));
                venda.setIdProduto(rs.getInt("id_produto"));
                venda.setIdVendedor(rs.getInt("id_vendedor"));
                
            }  
        } catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        }
        return venda;
        
    }
    
    public boolean gravar (Venda venda){
        
        try{
            String sql;
                if(venda.getId()==0){
                    sql="INSERT INTO vendas (quantidade_venda, data_venda , valor_venda, id_cliente, id_produto, id_vendedor) VALUES(?,?,?,?,?,?)";   
                }else{
                    sql="UPDATE vendas SET quantidade_venda= ?, data_venda=?, valor_venda=?, id_cliente=?, id_produto=?, id_vendedor=? WHERE id=?";
                    
                }
            
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setInt(1,venda.getQuantidade());
            ps.setDate(2,venda.getDataVenda());
            ps.setDouble(3,venda.getValorVenda());
            ps.setInt(4,venda.getIdCliente());
            ps.setInt(5,venda.getIdProduto());
            ps.setInt(6,venda.getIdVendedor());
           
            if(venda.getId()>0){
               ps.setInt(7,venda.getId());
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
            String sql="DELETE FROM vendas WHERE id=?";
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