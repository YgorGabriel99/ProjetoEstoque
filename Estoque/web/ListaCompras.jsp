
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,aplicacao.*"%>
<%@page import="controller.CompraController"%>
<%@page import="model.CompraDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Todas as Compras</title>
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
           
         
         <h1>Veja aqui as compras realizadas</h1>
         <a class="btn btn-outline-danger" href="AreaRestrita.jsp">Retornar</a>
         <div class="table-responsive">
             <table class="table table-striped">
                 <thead>
                    <th scope="col">Id</th>
                    <th scope="col">Quantidade Comprada</th>
                    <th scope="col">Data da Compra</th>
                    <th scope="col">Valor</th>
                    <th scope="col">Fornecedor</th>
                    <th scope="col">Produto</th> 
                    <th scope="col">Comprador</th>
                 </thead>
                 <tbody>
                     <% 
                         ArrayList<Compra> ListaCompras = (ArrayList<Compra>) session.getAttribute("compras");
                         for (int i = 0 ; i < ListaCompras.size(); i++){
                         
                         Compra aux= ListaCompras.get(i);
                         if(aux.getIdComprador()== usuario.getId()){
                         String link_editar = "CompraController?acao=editar&id="+aux.getId();
                         String link_excluir = "CompraController?acao=excluir&id="+aux.getId();
                         session.setAttribute("compra", aux);
                     %>
                     <tr>
                         <td><%=aux.getId() %> </td> 
                         <td><%=aux.getQuantidade() %> </td>
                         <td><%=aux.getDataCompra() %> </td> 
                         <td><%=aux.getValorCompra() %> </td> 
                         <td><%=aux.getIdFornecedor() %> </td> 
                         <td><%=aux.getIdProduto() %> </td> 
                         <td><%=aux.getIdComprador() %> </td> 
                        <td><a href="<%=link_editar%>" class="btn btn-outline-dark float-right"> Editar </a> </td>
                        <td><a href="<%=link_excluir %>" class="btn btn-outline-danger float-right"> Excluir </a> </td>
                         
                     </tr>
                     <%
                          }
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
