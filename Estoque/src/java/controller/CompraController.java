package controller;

import aplicacao.*;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
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
@WebServlet(name = "CompraController", urlPatterns = {"/CompraController"})
public class CompraController extends HttpServlet {

     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
   
    CompraDAO compraDAO= new CompraDAO();
    String acao =(String) request.getParameter("acao");
    int id;
    
    ArrayList<Compra> compras;
    Compra compra = new Compra();
    HttpSession session;
    switch (acao){
    
     case "mostrar":
           
           session= request.getSession();
           Usuario usuario= (Usuario)session.getAttribute("usuario");    
           int idUsuario= usuario.getId();
           compras = compraDAO.getLista();
           for(int i=0;i< compras.size();i++){
               compra= compras.get(i);
               
               if((compra.getIdComprador())!= idUsuario){
                   compras.remove(i);
               }
           }
           
           session.setAttribute("compras", compras);
           RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaCompras.jsp");
           mostrar.forward(request,response);
           break;
     case "incluir":
           
           session= request.getSession();
           compra.setId(0);
           
           
           session.setAttribute("compra", compra);
           RequestDispatcher incluir = getServletContext().getRequestDispatcher("/IncluirCompra.jsp");
           incluir.forward(request, response);
           break;
     case "editar":
         
         session= request.getSession();
         compra= (Compra)session.getAttribute("compra");
         
         if(compra.getId()>0){
             session.setAttribute("compra",compra);
             RequestDispatcher rs = getServletContext().getRequestDispatcher("/IncluirCompra.jsp");
             rs.forward(request,response);   
         }else{
             String mensagem = "Erro na ediçao da Compra!";
             session.setAttribute("mensagem", mensagem);
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
             rd.forward(request, response);
         }
         break;
         
     case "excluir":
           
           session= request.getSession();
           compra= (Compra)session.getAttribute("compra"); 
           id= compra.getId();
           compraDAO.excluir(id);

           compras= compraDAO.getLista();
           session.setAttribute("compras", compras);
           RequestDispatcher exclusao = getServletContext().getRequestDispatcher("/ListaCompras.jsp");
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
            Compra compra = new Compra();
    
            compra.setId(Integer.parseInt(request.getParameter("id")));  
            compra.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            compra.setDataCompra(java.sql.Date.valueOf(request.getParameter("data")));
            compra.setValorCompra(Integer.parseInt(request.getParameter("valor")));
            
            FornecedorDAO fornecedordao= new FornecedorDAO();
            if((fornecedordao.getFornecedorPorID(Integer.parseInt(request.getParameter("id_fornecedor"))))!= null){
            compra.setIdFornecedor(Integer.parseInt(request.getParameter("id_fornecedor")));
            }
            
            compra.setIdProduto(Integer.parseInt(request.getParameter("id_produto")));
            compra.setIdComprador(Integer.parseInt(request.getParameter("id_comprador")));
            
                    
            CompraDAO compradao = new CompraDAO();

                    if(compradao.gravar(compra)){
                        
                            EstoqueDAO estoquedao= new EstoqueDAO();
                            ArrayList<Produto> produtos= estoquedao.getLista();
                            for(int i=0; i<produtos.size(); i++){
                            
                            Produto aux= produtos.get(i);
                            if(aux.getId()==compra.getIdProduto()){
                                
                                aux.setPrecoCompra(compra.getValorCompra());
                                int qtd= (compra.getQuantidade()) + (aux.getQuantidade());
                                aux.setQuantidade(qtd);
                                estoquedao.gravar(aux);
                            }
                        }
                        
                        mensagem = "Compra registrada com sucesso!";
                        System.out.println("Compra registrada");
                     }else{
                        mensagem = "Erro ao gravar compra!";
                        System.out.println("erro para cadastrar compra, tentativa feita");
                    }
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                rd.forward(request, response); 
           
            }catch(Exception e){
            mensagem  = "Erro ao gravar a compra";
            System.out.println("erro para cadastrar compra " + e);
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
         }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Date Date(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

}
