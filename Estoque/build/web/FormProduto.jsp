<%-- 
    Document   : FormProduto
    Created on : 03/03/2021, 15:05:35
    Author     : ygorf
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacao.*"%>
<%@page import="controller.ProdutoController"%>
<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Cabecalho.html" %>
        <title>Formulário de Produto</title>
    </head>
    <body>
        <% if(session == null){ %>
            <jsp:forward page="LoginForm.jsp" />    
        <% }else{
                if(session.getAttribute("usuario")==null){ %>
                <jsp:forward page="LoginForm.jsp" />
         <%       }  
               }%>
       <div class="container mt-2">
           <jsp:include page="menu.jsp"/>
           
           
           <%
                CategoriaDAO categoriadao= new CategoriaDAO();
                ArrayList <Categoria> categorias= categoriadao.getLista();
                Produto aux = (Produto)request.getAttribute("produto");
               
           %>
           <div class="col-6 mt-5">
               <h4>Incluir Produto</h4>
               <form method="POST" action="ProdutoController" >
                   
                   <input type="hidden" class="form-control" name="id" value="<%= aux.getId()%>">
                   <div class="form-group"> 
                       <label for="Nome"> Nome</label>
                       <input type="text" class="form-control" name="nome"  required size="30" placeholder="Nome do Produto">
                    </div>
                     <div class="form-group"> 
                       <label for="Descricao"> Descrição</label>
                       <input type="text" class="form-control" name="descricao" required size="30" placeholder="Descriçao do Produto">
                    </div>
                     <div class="form-group"> 
                       <label for="PrecoCompra">Preço de Compra</label>
                       <input type="text" class="form-control" name="precoCompra" required size="30" placeholder="0.00">
                    </div>
                    <div class="form-group"> 
                       <label for="PrecoVenda">Preço de Venda</label>
                       <input type="text" class="form-control" name="precoVenda" required size="30" placeholder="0.00">
                    </div>
                   <div class="form-group"> 
                       <label for="Quantidade"> Quantidade</label>
                       <input type="text" class="form-control" name="quantidade"  required size="30">
                    </div>
                   <div class="form-group"> 
                   <select  id="categoria" class="form-control" name="categoria">
                       <% for(int i=0; i<categorias.size();i++){
                          Categoria cat= categorias.get(i); %>
                        <option value="<%=cat.getId()%>"><%=cat.getNome()%></option>
                       <%}%>
                    </select>
                     
                   </div>
                    <button type="submit" class="btn btn-primary"> Enviar</button>
                    <a href="AreaRestrita.jsp" class="btn btn-outline-danger">Retornar</a>
               </form>
           </div>
  
          <%@include file="Scriptsbasicos.html" %>    
          </div>       
          
          
    </body>
</html>
