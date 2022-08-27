
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,aplicacao.*"%>
<%@page import="controller.VendaController"%>
<%@page import="model.VendaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Lista de Vendas</title>
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
           
         
         <h1>Veja aqui as Vendas!</h1>
         <div class="table-responsive">
             <table class="table table-striped">
                 <thead>
                    <th scope="col">Id</th>
                    <th scope="col">Quantidade Vendida</th>
                    <th scope="col">Data</th>
                    <th scope="col">Valor</th>                     
                    <th scope="col">Cliente ID</th>
                    <th scope="col">Produto ID</th>
                    <th scope="col">Vendedor ID</th>
                      
                 </thead>
                 <tbody>
                     <% 
                        ArrayList<Venda> ListaVendas = (ArrayList<Venda>) session.getAttribute("vendas");
                         for (int i = 0 ; i < ListaVendas.size(); i++){
                         
                         Venda aux= ListaVendas.get(i);
                         if(aux.getIdVendedor()== usuario.getId()){
                         String link_editar = "VendaController?acao=editar&id="+aux.getId();
                         String link_excluir = "VendaController?acao=excluir&id="+aux.getId();
                         session.setAttribute("venda", aux);
                     %>
                     <tr>
                         <td><%=aux.getId() %> </td> 
                         <td><%=aux.getQuantidade() %> </td>
                         <td><%=aux.getDataVenda() %> </td>
                         <td><%=aux.getValorVenda() %> </td>
                         <td><%=aux.getIdCliente() %> </td>
                         <td><%=aux.getIdProduto() %> </td>
                         <td><%=aux.getIdVendedor() %> </td>
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
