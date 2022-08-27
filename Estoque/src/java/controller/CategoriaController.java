package controller;

import aplicacao.Categoria;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CategoriaDAO;




/**
 *
 * @author ygorf
 */
@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
   
    CategoriaDAO categoriaDAO= new CategoriaDAO();
    String acao =(String) request.getParameter("acao");
    int id;
    
    ArrayList<Categoria> categorias;
    Categoria categoria = new Categoria();
    HttpSession session;
    switch (acao){
    
     case "mostrar":
           
           session= request.getSession();
           categorias = categoriaDAO.getLista();
           session.setAttribute("categorias", categorias);
           RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaCategorias.jsp");
           mostrar.forward(request,response);
           break;
     case "incluir":
           
           session= request.getSession();
           categoria.setId(0);
           
           
           session.setAttribute("categoria", categoria);
           RequestDispatcher incluir = getServletContext().getRequestDispatcher("/IncluirCategoria.jsp");
           incluir.forward(request, response);
           break;
     case "editar":
         
         session= request.getSession();
         categoria= (Categoria)session.getAttribute("categoria");
         
         if(categoria.getId()>0){
             session.setAttribute("categoria",categoria);
             RequestDispatcher rs = getServletContext().getRequestDispatcher("/IncluirCategoria.jsp");
             rs.forward(request,response);   
         }else{
             String mensagem = "Erro na ediçao de Categoria!";
             session.setAttribute("mensagem", mensagem);
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
             rd.forward(request, response);
         }
         break;
         
     case "excluir":
           
           session= request.getSession();
           categoria= (Categoria)session.getAttribute("categoria"); 
           id= categoria.getId();
           categoriaDAO.excluir(id);

           categorias= categoriaDAO.getLista();
           session.setAttribute("categorias", categorias);
           RequestDispatcher exclusao = getServletContext().getRequestDispatcher("/ListaCategorias.jsp");
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
            Categoria categoria = new Categoria();
            
            categoria.setId(Integer.parseInt(request.getParameter("id")));  
            categoria.setNome(request.getParameter("nome"));
            
            CategoriaDAO categoriadao = new CategoriaDAO();

                    if(categoriadao.gravar(categoria)){
                        mensagem = "Categoria registrada com sucesso!";
                        System.out.println("Categoria registrada");
                     }else{
                        mensagem = "Erro ao gravar categoria!";
                        System.out.println("erro para cadastrar categoria, tentativa feita");
                    }
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                rd.forward(request, response); 
           
            }catch(Exception e){
            mensagem  = "Erro ao gravar o categoria";
            System.out.println("erro para cadastrar categoria " + e);
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
