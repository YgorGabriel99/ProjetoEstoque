
package model;


import aplicacao.Produto;
import model.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author ygorf
 */
@WebServlet(name = "EstoqueDAO", urlPatterns = {"/EstoqueDAO"})
public class EstoqueDAO extends HttpServlet {

    private Connection conexao;
    
    public EstoqueDAO(){
   
        try{
               conexao= Conexao.criaConexao();
        
        }
        catch(Exception e) {
             System.out.println("Erro da conexão DAO");
              System.out.println(e);
        }
        
    }
    public ArrayList<Produto> getLista(){
        
        ArrayList<Produto> resultado  = new ArrayList<>();
        
        try{
            Statement stmt= conexao.createStatement();
            ResultSet rs= stmt.executeQuery("select * from produtos");
           
            while(rs.next()){
                Produto produto= new Produto();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidade(rs.getInt("quantidade_disponível"));
                produto.setLiberado(rs.getString("liberado_venda"));
                produto.setCategoria(rs.getInt("id_categoria"));
                resultado.add(produto);
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
            
        } 
          return resultado;
    }
    
    public Produto getProdutoPorID(int codigo){
        
        Produto produto=new Produto();
        
        try{
            String sql="SELECT * FROM produtos WHERE id = ? ";
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCompra(rs.getDouble("preco_compra"));
                produto.setPrecoVenda(rs.getDouble("preco_venda"));
                produto.setQuantidade(rs.getInt("quantidade_disponível"));
                produto.setLiberado(rs.getString("liberado_venda"));
                produto.setCategoria(rs.getInt("id_categoria"));
            }  
        } catch(SQLException e){
            System.out.println("Erro de SQL" + e.getMessage());
        }
        return produto;
        
    }
    
    public boolean gravar (Produto produto){
        
        try{
            String sql;
                if(produto.getId()==0){
                    sql="INSERT INTO produtos (nome_produto,descricao, preco_compra, preco_venda, quantidade_disponível, id_categoria) VALUES(?,?,?,?,?,?)";   
                }else{
                    sql="UPDATE produtos SET nome_produto= ?, descricao=?, preco_compra=?, preco_venda=?, quantidade_disponível=?, id_categoria=? WHERE id=?";
                    
                }
            
            PreparedStatement ps= conexao.prepareStatement(sql);
            ps.setString(1,produto.getNome());
            ps.setString(2,produto.getDescricao());
            ps.setDouble(3,produto.getPrecoCompra());
            ps.setDouble(4,produto.getPrecoVenda());
            ps.setInt(5,produto.getQuantidade());
            ps.setInt(6,produto.getCategoria());
            
            if(produto.getId()>0){
               ps.setInt(7,produto.getId());
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
            String sql="DELETE FROM produtos WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch(SQLException e){
             System.out.println("Erro de SQL" + e.getMessage());
             return false;
        }
    }
     public boolean liberar(Produto produto){
         
         try{
           String sql= "UPDATE produtos SET liberado_venda=? WHERE id=?";
           PreparedStatement ps= conexao.prepareStatement(sql);      
           String liberado = produto.getLiberado();
           if(liberado.equals("S")){
                ps.setString(1,"N");
           }else if(liberado.equals("N")){
                ps.setString(1, "S");
            }
             ps.setInt(2,produto.getId());
             ps.execute();
             return true;
             
         }catch(SQLException e){
             System.out.println("Erro de SQL" + e.getMessage());
             return false;
         }
     }
   
    
    
    
  /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
*/
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
