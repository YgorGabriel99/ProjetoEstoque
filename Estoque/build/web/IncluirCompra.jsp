
<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@page import="aplicacao.*"%>
<%@page import="controller.CompraController"%>
<%@page import="model.CompraDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Cabecalho.html" %>
        <title>Criar Compra</title>
    </head>
    <body>
       <div class="container mt-2">
           <jsp:include page="menu.jsp"/>
           
           
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
           
           
           <%
           
                Compra aux = (Compra)session.getAttribute("compra");
                EstoqueDAO estoquedao= new EstoqueDAO();
                ArrayList <Produto> produtos=  estoquedao.getLista();
                FornecedorDAO fornecedordao= new FornecedorDAO();
                ArrayList <Fornecedor> fornecedores= fornecedordao.getLista();
                 if(aux.getId()== 0){
                       java.util.Date dataUtil = new java.util.Date();
                       java.sql.Date data = new java.sql.Date(dataUtil.getTime());
                       aux.setDataCompra(data);
                }
               
           %>
           <div class="col-6 mt-5">
               <h4>Registrar Nova Compra</h4>
               <form id="meuForm" method="POST"  action="CompraController" >
                   
                   <input type="hidden" class="form-control" name="id" value="<%= aux.getId()%>">
                   <input type="hidden" class="form-control" name="data" value="<%= aux.getDataCompra()%>">
                   <input type="hidden" class="form-control" name="id_comprador" value="<%=usuario.getId()%>">
                   

                   <div class="form-group"> 
                       <label for="Quantidade"> Quantidade Comprada</label>
                       <input type="text" class="form-control" name="quantidade"  required size="30" placeholder="Quantos itens?">
                    </div>
                     
                   <div class="form-group"> 
                       <label for="Valor"> Valor da compra</label>
                       <input type="text" class="form-control" name="valor"  required size="30" placeholder="R$?????">
                    </div>
                   
                   
                   <label for="Produto"> Produto </label>
                   <select  id="produto" class="form-control" name="id_produto">
                       <% for(int i=0; i<produtos.size();i++){
                           Produto prod= produtos.get(i);
                       %>
                          
                        <option value="<%=prod.getId()%>"> <%=prod.getNome()%> </option>
                       <%}%>
                    </select>
                    
                     <label for="Fornecedor"> Fornecedor</label>
                     <select  id="fornecedor" class="form-control" name="id_fornecedor">
                       <% for(int i=0; i<fornecedores.size();i++){
                           Fornecedor forn= fornecedores.get(i);
                       %>
                          
                        <option value="<%=forn.getId()%>"> <%=forn.getRazaoSocial()%> </option>
                       <%}%>
                    </select>
                    <br>
                    
                    <button type="submit" class="btn btn-primary"> Enviar</button>
                    <a href="AreaRestrita.jsp" class="btn btn-outline-danger">Retornar</a>
               </form>
           </div>
  
          <%@include file="Scriptsbasicos.html" %> 
          <jsp:include page="footer.jsp"/>
          
          
         </div>       
          
          
    </body>
</html>
