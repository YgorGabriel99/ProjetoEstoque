
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,aplicacao.*"%>
<%@page import="controller.CategoriaController"%>
<%@page import="model.CategoriaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Todos as Categorias</title>
         <%@include file="Cabecalho.html" %>
        
    </head>
    <body>
        <div class="container mt-2"> 
         <%@include file="menu.jsp" %>
         <% if(session == null){ %>
            <jsp:forward page="LoginForm.jsp" />    
        <% }else{
                if(session.getAttribute("usuario")==null){ %>
                <jsp:forward page="LoginForm.jsp" />
         <%       }  
               }%>
               <h1>Loja Compra Certa </h1>
         <%  Usuario usuario = (Usuario) session.getAttribute("usuario");
            
            
            %>
           
         
         <h1>Veja aqui as Categorias</h1>
         <a class="btn btn-outline-danger" href="AreaRestrita.jsp">Retornar</a>
         <div class="table-responsive">
             <table class="table table-striped">
                 <thead>
                    <th scope="col">Id</th>
                    <th scope="col">Nome</th>
                    
                      
                 </thead>
                 <tbody>
                     <% 
                          ArrayList<Categoria> ListaCategorias = (ArrayList<Categoria>) session.getAttribute("categorias");
                         for (int i = 0 ; i < ListaCategorias.size(); i++){
                         Categoria aux= ListaCategorias.get(i);
                         String link_editar = "CategoriaController?acao=editar&id="+aux.getId();
                         String link_excluir = "CategoriaController?acao=excluir&id="+aux.getId();
                         session.setAttribute("categoria", aux);
                     %>
                     <tr>
                         <td><%=aux.getId() %> </td> 
                         <td><%=aux.getNome() %> </td>
                         
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
