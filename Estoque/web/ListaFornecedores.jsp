
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,aplicacao.*"%>
<%@page import="controller.FornecedorController"%>
<%@page import="model.FornecedorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Todos os Fornecedores</title>
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
           
         
         <h1>Veja aqui os Fornecedores</h1>
         <a class="btn btn-outline-danger" href="AreaRestrita.jsp">Retornar</a>
         <div class="table-responsive">
             <table class="table table-striped">
                 <thead>
                    <th scope="col">Id</th>
                    <th scope="col">Razão Social</th>
                    <th scope="col">CNPJ</th>
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
                          ArrayList<Fornecedor> ListaFornecedores = (ArrayList<Fornecedor>) session.getAttribute("fornecedores");
                         for (int i = 0 ; i < ListaFornecedores.size(); i++){
                         Fornecedor aux= ListaFornecedores.get(i);
                         String link_editar = "FornecedorController?acao=editar&id="+aux.getId();
                         String link_excluir = "FornecedorController?acao=excluir&id="+aux.getId();
                         session.setAttribute("fornecedor", aux);
                     %>
                     <tr>
                         <td><%=aux.getId() %> </td> 
                         <td><%=aux.getRazaoSocial() %> </td>
                         <td><%=aux.getCnpj() %> </td>
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
