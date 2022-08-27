/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "LiberarProd", urlPatterns = {"/LiberarProd"})
public class LiberarProd extends HttpServlet {

  

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        HttpSession session= request.getSession();
        ArrayList <Produto> produtos=(ArrayList<Produto>) session.getAttribute("produtos");
        int id= Integer.parseInt(request.getParameter("id")); 
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        Produto produto= new Produto();
        produto= estoqueDAO.getProdutoPorID(id);
        estoqueDAO.liberar(produto);
        produtos= estoqueDAO.getLista();
        session.setAttribute("produtos", produtos);
        RequestDispatcher resposta= request.getRequestDispatcher("LiberarProdutos.jsp");
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
