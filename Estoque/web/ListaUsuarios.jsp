
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,aplicacao.*"%>
<%@page import="controller.UsuarioController"%>
<%@page import="model.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Lista de Usuários</title>
         <%@include file="Cabecalho.html" %>
        
    </head>
    <body>
        <div class="container mt-2"> 
         <%@include file="menu.jsp" %>
         
         <h1>Veja aqui os usuários cadastrados!</h1>
         <div class="table-responsive">
             <table class="table table-striped">
                 <thead>
                    <th scope="col">Id</th>
                    <th scope="col">Nome</th>
                    <th scope="col">CPF</th>
                    <th scope="col">Senha</th>                     
                    <th scope="col">Tipo</th>
                      
                 </thead>
                 <tbody>
                     <% 
                          ArrayList<Usuario> ListaUsuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
                         for (int i = 0 ; i < ListaUsuarios.size(); i++){
                         Usuario aux= ListaUsuarios.get(i);
                         String link_editar = "UsuarioController?acao=editar&id="+aux.getId();
                         String link_excluir = "UsuarioController?acao=excluir&id="+aux.getId();
                     
                     %>
                     <tr>
                         <td><%=aux.getId() %> </td> 
                         <td><%=aux.getNome() %> </td>
                         <td><%=aux.getCpf() %> </td>
                         <td><%=aux.getSenha() %> </td>
                         <td><%=aux.getTipo() %> </td>
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
