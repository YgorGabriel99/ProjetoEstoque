/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import aplicacao.Usuario;
import model.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ValidarLogin", urlPatterns = {"/ValidarLogin"})
public class ValidarLogin extends HttpServlet {

   
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String login= request.getParameter("login");
        String senha= request.getParameter("senha");
        String mensagem= "";
        UsuarioDAO usuarioDAO= new UsuarioDAO();
        Usuario usuario= new Usuario();
       
         String CPF= request.getParameter("login");
         VerCPF vercpf= new VerCPF();
         boolean verificou = vercpf.verificaCPF(CPF);
         if(verificou == true){
        
            if((usuarioDAO.verificarUsuario(login))==true)
            {
                if(usuarioDAO.verificarSenha(login, senha)==true){

                  usuario= usuarioDAO.getUsuarioPorCpf(login);
                  HttpSession session = request.getSession();
                  session.setAttribute("usuario", usuario);
                  session.setAttribute("logado", "ok");
                  RequestDispatcher rd= request.getRequestDispatcher("/AreaRestrita.jsp");
                  rd.forward(request,response);
                }else{
                    request.setAttribute("mensagem","Senha incorreta");
                    RequestDispatcher rd= request.getRequestDispatcher("/LoginForm.jsp");
                    rd.forward(request,response);
                }

             }else{

                request.setAttribute("mensagem","Usuário não identificado");
                RequestDispatcher rd= request.getRequestDispatcher("/LoginForm.jsp");
                rd.forward(request,response);

                }          
         }else{
                request.setAttribute("mensagem", "Usuário não identificado, cpf inválido");
                RequestDispatcher rd= request.getRequestDispatcher("/LoginForm.jsp");
                rd.forward(request,response);
         }
        
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
