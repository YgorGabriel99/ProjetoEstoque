
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,aplicacao.*"%>
<%@page import="controller.ClienteController"%>
<%@page import="model.ClienteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Todos os clientes</title>
         <%@include file="Cabecalho.html" %>
        
    </head>
    <body>
        <div class="container mt-2"> 
         <%@include file="menu.jsp" %>
         
         <h1>Veja aqui os Clientes</h1>
         <div class="table-responsive">
             <table class="table table-striped">
                 <thead>
                    <th scope="col">Id</th>
                    <th scope="col">Nome</th>
                    <th scope="col">CPF</th>
                    <th scope="col">Endereço</th>
                    <th scope="col">Bairro</th>
                    <th scope="col">Cidade</th>     
                    <th scope="col">UF</th>   
                    <th scope="col">CEP</th>
                    <th scope="col">Telefone</th>
                    <th scope="col">Email</th>
                      
                 </thead>
                 <tbody>
                     <% 
                          ArrayList<Cliente> ListaCliente = (ArrayList<Cliente>) request.getAttribute("clientes");
                         for (int i = 0 ; i < ListaCliente.size(); i++){
                         Cliente aux= ListaCliente.get(i);
                         String link_editar = "ClienteController?acao=editar&id="+aux.getId();
                         String link_excluir = "ClienteController?acao=excluir&id="+aux.getId();
                     
                     %>
                     <tr>
                         <td><%=aux.getId() %> </td> 
                         <td><%=aux.getNome() %> </td>
                         <td><%=aux.getCpf() %> </td>
                         <td><%=aux.getEndereco() %> </td>
                         <td><%=aux.getBairro() %> </td>
                         <td><%=aux.getCidade() %> </td>
                         <td><%=aux.getUf() %> </td> 
                         <td><%=aux.getCep() %> </td>
                         <td><%=aux.getTelefone() %> </td>
                         <td><%=aux.getEmail() %> </td>
                        <td><a href="<%=link_editar%>" class="btn btn-outline-dark float-right"> Editar </a> </td>
                        <td><a href="<%=link_excluir %>" class="btn btn-outline-danger float-right"> Excluir </a> </td>
                         
                     </tr>
                     <%
                         }
                     %>
                 </tbody>
             </table>
         </div>
        </div>
        <%@include file="footer.jsp" %>  
        <%@include file="Scriptsbasicos.html"  %>
    </body>
</html>
