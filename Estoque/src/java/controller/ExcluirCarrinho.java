
package controller;

import aplicacao.Produto;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ExcluirCarrinho", urlPatterns = {"/ExcluirCarrinho"})
public class ExcluirCarrinho extends HttpServlet {

 
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    
        HttpSession session= request.getSession();
        ArrayList <Produto> itens_compras=(ArrayList<Produto>) session.getAttribute("carrinho");
        
        int id_item= Integer.parseInt(request.getParameter("id"));
        EstoqueDAO estoqueDAO= new EstoqueDAO();
        Produto produto= new Produto();
     
        
        produto= estoqueDAO.getProdutoPorID(id_item);
        for(int i=0; i<itens_compras.size();i++){
            Produto aux=itens_compras.get(i);
            if(aux.getId()== produto.getId()){
            itens_compras.remove(i);
            }
        }
        
        session.setAttribute("carrinho", itens_compras);
        
        RequestDispatcher resposta= request.getRequestDispatcher("OlharCarrinho.jsp");
        resposta.forward(request,response);
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
