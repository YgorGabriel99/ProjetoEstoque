package controller;

import aplicacao.Cliente;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClienteDAO;
import controller.VerCPF;



/**
 *
 * @author ygorf
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {

     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
   
    ClienteDAO clienteDAO= new ClienteDAO();
    String acao =(String) request.getParameter("acao");
    int id;
    ArrayList<Cliente> clientes;
    Cliente cliente = new Cliente();
    
    switch (acao){
    
     case "mostrar":
           clientes = clienteDAO.getLista();
           request.setAttribute("clientes", clientes);
           RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaClientes.jsp");
           mostrar.forward(request,response);
           break;
     case "incluir":
           cliente.setId(0);
           
           
           request.setAttribute("cliente", cliente);
           RequestDispatcher incluir = getServletContext().getRequestDispatcher("/IncluirCliente.jsp");
           incluir.forward(request, response);
           break;
     case "editar":
         id= Integer.parseInt(request.getParameter("id"));
         
         cliente= clienteDAO.getClientePorID(id);
         
         if(cliente.getId()>0){
             request.setAttribute("cliente",cliente);
             RequestDispatcher rs = getServletContext().getRequestDispatcher("/IncluirCliente.jsp");
             rs.forward(request,response);   
         }else{
             String mensagem = "Erro na ediçao de cliente!";
             request.setAttribute("mensagem", mensagem);
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
             rd.forward(request, response);
         }
         break;
         
     case "excluir":
           id= Integer.parseInt(request.getParameter("id"));
           clienteDAO.excluir(id);

           clientes= clienteDAO.getLista();
           request.setAttribute("clientes", clientes);
           RequestDispatcher exclusao = getServletContext().getRequestDispatcher("/ListaClientes.jsp");
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
            Cliente cliente = new Cliente();
            
            cliente.setId(Integer.parseInt(request.getParameter("id")));  
            cliente.setNome(request.getParameter("nome"));
            cliente.setCpf(request.getParameter("cpf"));
            cliente.setEndereco(request.getParameter("endereco"));
            cliente.setBairro(request.getParameter("bairro"));
            cliente.setCidade(request.getParameter("cidade"));
            cliente.setUf(request.getParameter("uf"));
            cliente.setCep(request.getParameter("cep"));
            cliente.setTelefone(request.getParameter("telefone"));
            cliente.setEmail(request.getParameter("email"));
            
            String CPF= cliente.getCpf();
            VerCPF vercpf= new VerCPF();
            boolean verificou = vercpf.verificaCPF(CPF);
            if(verificou == true){
                ClienteDAO clientedao = new ClienteDAO();

                    if(clientedao.gravar(cliente)){
                        mensagem = "Cliente registrado com sucesso!";
                        System.out.println("Cliente registrado");
                     }else{
                        mensagem = "Erro ao gravar cliente!";
                        System.out.println("erro para cadastrar cliente, tentativa feita");
                    }
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                rd.forward(request, response); 
              }else{
                        mensagem = "Erro ao gravar cliente, cpf inválido!";
                        System.out.println("erro para cadastrar cliente, cpf inválido!");
                        request.setAttribute("mensagem", mensagem);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                        rd.forward(request, response); 
                }
            }catch(Exception e){
            mensagem  = "Erro ao gravar o cliente";
            System.out.println("erro para cadastrar cliente " + e);
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
