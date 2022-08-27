package controller;

import aplicacao.Fornecedor;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FornecedorDAO;




/**
 *
 * @author ygorf
 */
@WebServlet(name = "FornecedorController", urlPatterns = {"/FornecedorController"})
public class FornecedorController extends HttpServlet {

     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
   
    FornecedorDAO fornecedorDAO= new FornecedorDAO();
    String acao =(String) request.getParameter("acao");
    int id;
    String cnpj;
    ArrayList<Fornecedor> fornecedores;
    Fornecedor fornecedor = new Fornecedor();
    HttpSession session;
    switch (acao){
    
     case "mostrar":
           
           session= request.getSession();
           fornecedores = fornecedorDAO.getLista();
           session.setAttribute("fornecedores", fornecedores);
           RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaFornecedores.jsp");
           mostrar.forward(request,response);
           break;
     case "incluir":
           
           session= request.getSession();
           fornecedor.setId(0);
           
           
           session.setAttribute("fornecedor", fornecedor);
           RequestDispatcher incluir = getServletContext().getRequestDispatcher("/IncluirFornecedor.jsp");
           incluir.forward(request, response);
           break;
     case "editar":
         
         session= request.getSession();
         fornecedor= (Fornecedor)session.getAttribute("fornecedor");
         
         if(fornecedor.getId()>0){
             session.setAttribute("fornecedor",fornecedor);
             RequestDispatcher rs = getServletContext().getRequestDispatcher("/IncluirFornecedor.jsp");
             rs.forward(request,response);   
         }else{
             String mensagem = "Erro na ediçao de fornecedor!";
             session.setAttribute("mensagem", mensagem);
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
             rd.forward(request, response);
         }
         break;
         
     case "excluir":
           
           session= request.getSession();
           fornecedor= (Fornecedor)session.getAttribute("fornecedor"); 
           id= fornecedor.getId();
           fornecedorDAO.excluir(id);

           fornecedores= fornecedorDAO.getLista();
           session.setAttribute("fornecedores", fornecedores);
           RequestDispatcher exclusao = getServletContext().getRequestDispatcher("/ListaFornecedores.jsp");
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
            Fornecedor fornecedor = new Fornecedor();
            
            fornecedor.setId(Integer.parseInt(request.getParameter("id")));  
            fornecedor.setRazaoSocial(request.getParameter("razaosocial"));
            fornecedor.setCnpj(request.getParameter("cnpj"));
            fornecedor.setEndereco(request.getParameter("endereco"));
            fornecedor.setBairro(request.getParameter("bairro"));
            fornecedor.setCidade(request.getParameter("cidade"));
            fornecedor.setUf(request.getParameter("uf"));
            fornecedor.setCep(request.getParameter("cep"));
            fornecedor.setTelefone(request.getParameter("telefone"));
            fornecedor.setEmail(request.getParameter("email"));
            
            String CNPJ= fornecedor.getCnpj();
            VerCPF vercnpj= new VerCPF();
            boolean verificou = vercnpj.verificaCNPJ(CNPJ);
            if(verificou == true){
                FornecedorDAO fornecedordao = new FornecedorDAO();

                    if(fornecedordao.gravar(fornecedor)){
                        mensagem = "Fornecedor registrado com sucesso!";
                        System.out.println("Fornecedor registrado");
                     }else{
                        mensagem = "Erro ao gravar fornecedor!";
                        System.out.println("erro para cadastrar fornecedor, tentativa feita");
                    }
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                rd.forward(request, response); 
              }else{
                        mensagem = "Erro ao gravar fornecedor, cnpj inválido!";
                        System.out.println("erro para cadastrar fornecedor, cnpj inválido!");
                        request.setAttribute("mensagem", mensagem);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                        rd.forward(request, response); 
                }
            }catch(Exception e){
            mensagem  = "Erro ao gravar o fornecedor";
            System.out.println("erro para cadastrar fornecedor " + e);
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
