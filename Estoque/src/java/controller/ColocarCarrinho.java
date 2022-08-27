
package controller;

import aplicacao.Produto;
import model.EstoqueDAO;
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

/**
 *
 * @author ygorf
 */
@WebServlet(name = "ColocarCarrinho", urlPatterns = {"/ColocarCarrinho"})
public class ColocarCarrinho extends HttpServlet {

   
   

    
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    
        HttpSession session= request.getSession();
        ArrayList <Produto> itens_compras=(ArrayList<Produto>) session.getAttribute("carrinho");
        
        int id_item= Integer.parseInt(request.getParameter("id"));
        EstoqueDAO estoqueDAO= new EstoqueDAO();
        Produto produto= new Produto();
     
        
        produto= estoqueDAO.getProdutoPorID(id_item);
        itens_compras.add(produto);
        session.setAttribute("carrinho", itens_compras);
        
        RequestDispatcher resposta= request.getRequestDispatcher("NovaVenda.jsp");
        resposta.forward(request,response);
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
