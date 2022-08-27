/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import aplicacao.*;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;


 /**
  * 
 * @author ygorf
 */
@WebServlet(name = "VendaController", urlPatterns = {"/VendaController"})
public class VendaController extends HttpServlet {
    
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
   
    VendaDAO vendaDAO= new VendaDAO();
    String acao =(String) request.getParameter("acao");
    int id;
    ArrayList<Venda> vendas;
    Venda venda = new Venda();
    HttpSession session;
    switch (acao){
    
     case "mostrar":
           
           session= request.getSession();
           Usuario usuario= (Usuario)session.getAttribute("usuario");  
           vendas = vendaDAO.getLista();
           int idUsuario= usuario.getId();
           
           for(int i=0;i< vendas.size();i++){
               venda= vendas.get(i);
               
               if((venda.getIdVendedor())!= idUsuario){
                   vendas.remove(i);
               }
           }
           session.setAttribute("vendas", vendas);
           RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaVendas.jsp");
           mostrar.forward(request,response);
           break;
     case "incluir":
           
           session= request.getSession();
           
           venda.setId(0);
           Produto produto= (Produto) session.getAttribute("produto");  
           ArrayList<Produto> Carrinho = (ArrayList <Produto>) session.getAttribute("carrinho");
           Carrinho.remove(produto);
           session.setAttribute("venda", venda);
           session.setAttribute("produto", produto);
           session.setAttribute("carrinho", Carrinho);
           RequestDispatcher incluir = getServletContext().getRequestDispatcher("/FormVenda.jsp");
           incluir.forward(request, response);
           break;
     case "editar":
        
         session= request.getSession();
         
         
         venda= (Venda)session.getAttribute("venda");
         
         if(venda.getId()>0){
             session.setAttribute("venda",venda);
             RequestDispatcher rs = getServletContext().getRequestDispatcher("/FormVenda.jsp");
             rs.forward(request,response);   
         }else{
             String mensagem = "Erro na ediçao da venda!";
             session.setAttribute("mensagem", mensagem);
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
             rd.forward(request, response);
         }
         break;
         
     case "excluir":
           
           session= request.getSession();
           venda= (Venda)session.getAttribute("venda");
           id= venda.getId();
           vendaDAO.excluir(id);

           vendas= vendaDAO.getLista();
           session.setAttribute("vendas", vendas);
           RequestDispatcher exclusao = getServletContext().getRequestDispatcher("/ListaVendas.jsp");
           exclusao.forward(request, response);
           break;
        }
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
       request.setCharacterEncoding("UTF-8");
       String mensagem;  
         try{   
            
            Venda venda = new Venda();
            venda.setId(Integer.parseInt(request.getParameter("id")));
            venda.setDataVenda(java.sql.Date.valueOf(request.getParameter("data"))); 
            
            ClienteDAO clientedao= new ClienteDAO();
            if((clientedao.getClientePorID(Integer.parseInt(request.getParameter("cliente"))))!= null){
            venda.setIdCliente(Integer.parseInt(request.getParameter("cliente")));
            }
            EstoqueDAO estoquedao= new EstoqueDAO();
            Produto prod= estoquedao.getProdutoPorID(Integer.parseInt(request.getParameter("produto")));
            Double valorMinimo = prod.getPrecoCompra()*0.1 + prod.getPrecoCompra();
            if((Double.parseDouble(request.getParameter("valor")))>= valorMinimo){
                Double valorProduto=(Double.parseDouble(request.getParameter("valor")));              
                venda.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                venda.setValorVenda(valorProduto * venda.getQuantidade());
                venda.setIdProduto(Integer.parseInt(request.getParameter("produto"))); 
            }
            venda.setIdVendedor(Integer.parseInt(request.getParameter("vendedor")));
            
            
           
            VendaDAO vendadao = new VendaDAO();
            
                if(vendadao.gravar(venda)){
                    mensagem = "Venda registrada com sucesso!";
                    System.out.println("venda registrada");
                    EstoqueDAO estoqueDAO= new EstoqueDAO();
                    
                    Produto produto= estoqueDAO.getProdutoPorID(venda.getIdProduto());
                    int novaQuantidade= (produto.getQuantidade())- (venda.getQuantidade());
                    produto.setQuantidade(novaQuantidade);
                    estoqueDAO.gravar(produto);
                    
                 }else{
                    mensagem = "Erro ao gravar venda!";
                    System.out.println("erro para cadastrar venda, tentativa feita");
                }
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
            
            }catch(Exception e){
            mensagem  = "Erro ao gravar a venda";
            System.out.println("erro para cadastrar venda" + e);
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
         }

    }
}