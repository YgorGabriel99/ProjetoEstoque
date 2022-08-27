<%-- 
    Document   : Carrinho
    Created on : 11/03/2021, 16:14:06
    Author     : ygorf
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,aplicacao.*"%>
<%@page import="controller.*"%>
<%@page import="model.*"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <%@include file="Cabecalho.html" %>
        <title>Nova Venda</title>
        <style>
           
            #prod{
                display: flex;
                flex-wrap: wrap;
                align-items: stretch;
                align: center;
            }
        </style>
    </head>
    <body>
      <% if(session == null){ %>
            <jsp:forward page="LoginForm.jsp" />    
        <% }else{
                if(session.getAttribute("usuario")==null){ %>
                <jsp:forward page="LoginForm.jsp" />
         <%       }  
               } %>
            
        <div class="container mt-2">
           
         <%@include file="menu.jsp"%>  <a class="btn btn-danger float-right" href="index.jsp">Log out</a>
        <h1>Loja Compra Certa </h1>
        <h3>Melhores preços só Aqui!</h3>
        
        <div class="card" style="width: 10rem;">
            <a href="#"><img class="img-fluid" src="assets/img/carrinho.jpg" alt="carrinho"></a>
        
      
           <% ArrayList<Produto> itens_compras=(ArrayList<Produto>) session.getAttribute("carrinho");
           %>
           <p>Itens no carrinho:<%out.println(itens_compras.size()); %></p>
           <p><a class="btn btn-dark" href="OlharCarrinho.jsp"> Olhar carrinho</a></p>
        </div>    
          
           
        <div class="row">
            <div id="prod" class="container-fluid">
              
        <%      
                
                ArrayList <Produto> produto = (ArrayList<Produto>) session.getAttribute("produtos");
                CategoriaDAO categoriadao=new CategoriaDAO();   
                   for(int i=0; i<produto.size(); i++){
                        
                        Produto aux= produto.get(i);
                        Categoria cat= categoriadao.getCategoriaPorID(aux.getCategoria());
                        if(aux.getQuantidade()>0) {
                            if(aux.getLiberado().equals("S")){
                %>  
          
         
                 <div class="card" style="width: 30rem; margin:10px; align: center;">    

                 <img class="card-img-top img-fluid" style="max-width:300px; max-height:300px" src="assets/img/produtoid<%=aux.getId()%>.jpg" alt="imagem produto">
                 <div class="card-body" > 
                    
                    <h5 class="card title"><%=aux.getNome()%></h5>
                    <p class="card text">Descrição: <%=aux.getDescricao()%></p>
                    <p class="card text">R$ <%=aux.getPrecoVenda()%></p>
                    <p class="card text">Categoria: <%=cat.getNome()%> </p>
                 </div>
                 <div class="card-footer">
                     <a href="ColocarCarrinho?id=<%=aux.getId()%>" class="btn btn-primary">Colocar no Carrinho</a>
                 </div>
                     
                 </div>
                 
                 
             <%     } 
                }   
              }   
             %>
            </div>  
        </div>
        </div> 
        
        <%@include file="Scriptsbasicos.html" %>
            
        
    </body>
</html>
