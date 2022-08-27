package controller;

import aplicacao.Produto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EstoqueDAO;

/**
 *
 * @author ygorf
 */
@WebServlet(name = "ProdutoController", urlPatterns = {"/ProdutoController"})
public class ProdutoController extends HttpServlet {

     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
   
    EstoqueDAO estoqueDAO= new EstoqueDAO();
    String acao =(String) request.getParameter("acao");
    int id;
    ArrayList<Produto> produtos;
    Produto produto = new Produto();
    
    switch (acao){
    
     case "mostrar":
           produtos = estoqueDAO.getLista();
           
           for(int i=0; i<produtos.size(); i++){
                        
                Produto aux= produtos.get(i);
                if(aux.getQuantidade()<1) {
                  produtos.remove(i); 
                }
                if(aux.getLiberado().equals("N")){
                         produtos.remove(i);
                }                 
            }
           
           request.setAttribute("produtos", produtos); 
           RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaProdutos.jsp");
           mostrar.forward(request,response);
           break;
     case "incluir":
           produto.setId(0);
           
           
           request.setAttribute("produto", produto);
           RequestDispatcher incluir = getServletContext().getRequestDispatcher("/FormProduto.jsp");
           incluir.forward(request, response);
           break;
     case "editar":
         id= Integer.parseInt(request.getParameter("id"));
         
         produto= estoqueDAO.getProdutoPorID(id);
         
         if(produto.getId()>0){
             request.setAttribute("produto",produto);
             RequestDispatcher rs = getServletContext().getRequestDispatcher("/FormProduto.jsp");
             rs.forward(request,response);   
         }else{
             String mensagem = "Erro na ediçao!";
             request.setAttribute("mensagem", mensagem);
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
             rd.forward(request, response);
         }
         break;
         
     case "excluir":
           id= Integer.parseInt(request.getParameter("id"));
           estoqueDAO.excluir(id);

           produtos=estoqueDAO.getLista();
           request.setAttribute("produtos", produtos);
           RequestDispatcher exclusao = getServletContext().getRequestDispatcher("/ListaProdutos.jsp");
           exclusao.forward(request, response);
           break;
        case "venda":
           
           HttpSession session= request.getSession();
           produtos = estoqueDAO.getLista();
           ArrayList<Produto> itens_compras=  new ArrayList();
            
           for(int i=0; i<produtos.size(); i++){
                        
                Produto aux= produtos.get(i);
                if(aux.getQuantidade()<1) {
                  produtos.remove(i); 
                }
                if(aux.getLiberado().equals("N")){
                         produtos.remove(i);
                }                 
            }
           session.setAttribute("produtos", produtos);
           session.setAttribute("carrinho", itens_compras);
           RequestDispatcher venda = getServletContext().getRequestDispatcher("/NovaVenda.jsp");
           venda.forward(request,response);
           break;
        
        case "liberar":
            HttpSession sessao= request.getSession();
            produtos= estoqueDAO.getLista();
            sessao.setAttribute("produtos", produtos);
            
            RequestDispatcher liberar= getServletContext().getRequestDispatcher("/LiberarProdutos.jsp");
            liberar.forward(request, response);
            break;
    }
   } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
       request.setCharacterEncoding("UTF-8");
       String mensagem;  
         try{   
            Produto produto = new Produto();
            
            produto.setId(Integer.parseInt(request.getParameter("id")));  
            produto.setNome(request.getParameter("nome"));
            produto.setDescricao(request.getParameter("descricao"));
            produto.setPrecoCompra(Double.parseDouble(request.getParameter("precoCompra")));
            produto.setPrecoVenda(Double.parseDouble(request.getParameter("precoVenda")));
            produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            produto.setCategoria(Integer.parseInt(request.getParameter("categoria")));

            EstoqueDAO estoquedao = new EstoqueDAO();
           
                if(estoquedao.gravar(produto)){
                    mensagem = "Produto registrado com sucesso!";
                    System.out.println("produto registrado");
                 }else{
                    mensagem = "Erro ao gravar produto!";
                    System.out.println("erro para cadastrar produto, tentativa feita");
                }
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
            }catch(Exception e){
             mensagem  = "Erro ao gravar o produto";
            System.out.println("erro para cadastrar produto" + e);
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
         }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

 

}
