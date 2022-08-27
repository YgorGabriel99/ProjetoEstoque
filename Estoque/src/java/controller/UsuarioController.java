package controller;

import aplicacao.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UsuarioDAO;

/**
 *
 * @author ygorf
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
   
    UsuarioDAO usuarioDAO= new UsuarioDAO();
    String acao =(String) request.getParameter("acao");
    int id;
    ArrayList<Usuario> usuarios;
    Usuario usuario = new Usuario();
    
    switch (acao){
    
     case "mostrar":
           usuarios = usuarioDAO.getLista();
           request.setAttribute("usuarios", usuarios);
           RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaUsuarios.jsp");
           mostrar.forward(request,response);
           break;
     case "incluir":
           usuario.setId(0);
           
           
           request.setAttribute("usuario", usuario);
           RequestDispatcher incluir = getServletContext().getRequestDispatcher("/IncluirUsuario.jsp");
           incluir.forward(request, response);
           break;
     case "editar":
         id= Integer.parseInt(request.getParameter("id"));
         
         usuario= usuarioDAO.getUsuarioPorID(id);
         
         if(usuario.getId()>0){
             request.setAttribute("usuario",usuario);
             RequestDispatcher rs = getServletContext().getRequestDispatcher("/IncluirUsuario.jsp");
             rs.forward(request,response);   
         }else{
             String mensagem = "Erro na ediçao de usuário!";
             request.setAttribute("mensagem", mensagem);
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
             rd.forward(request, response);
         }
         break;
         
     case "excluir":
           id= Integer.parseInt(request.getParameter("id"));
           usuarioDAO.excluir(id);

           usuarios= usuarioDAO.getLista();
           request.setAttribute("usuarios", usuarios);
           RequestDispatcher exclusao = getServletContext().getRequestDispatcher("/ListaUsuarios.jsp");
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
            Usuario usuario = new Usuario();
            
            usuario.setId(Integer.parseInt(request.getParameter("id")));  
            usuario.setNome(request.getParameter("nome"));
            usuario.setCpf(request.getParameter("cpf"));
            usuario.setSenha(request.getParameter("senha"));
            usuario.setTipo(request.getParameter("tipo"));
           

            UsuarioDAO usuariodao = new UsuarioDAO();
           
                if(usuariodao.gravar(usuario)){
                    mensagem = "Usuario registrado com sucesso!";
                    System.out.println("Usuario registrado");
                 }else{
                    mensagem = "Erro ao gravar usuário!";
                    System.out.println("erro para cadastrar usuario, tentativa feita");
                }
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
            
            }catch(Exception e){
             mensagem  = "Erro ao gravar o usuario";
            System.out.println("erro para cadastrar usuario " + e);
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
