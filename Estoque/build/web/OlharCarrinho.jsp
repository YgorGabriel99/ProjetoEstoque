<%@page import="aplicacao.Usuario"%>
<%@page import="aplicacao.*"%>
<%@page import="controller.ProdutoController"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <%@include file="Cabecalho.html" %>
        <title>Itens no Carrinho</title>
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
           
         <%@include file="menu.jsp" %>
        <h1>Loja Compra Certa </h1>
        <h3>Melhores preços só Aqui!</h3>
        
        <%
                ArrayList<Produto> itens_compras= (ArrayList<Produto>) session.getAttribute("carrinho");  
                CategoriaDAO categoriadao= new CategoriaDAO();
                ArrayList<Categoria> categorias= categoriadao.getLista();
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                
        %>
  
        
        <p><a class="btn btn-primary" href="NovaVenda.jsp">Retornar as compras</a></p>
        
        <div class="card mt-5 border-0" style="width: 4rem;">
            <a href="#"><img class="card-img-top" src="assets/img/carrinho.jpg" alt="carrinho"></a>
        </div>
       
            
        <h5 class="card-title"> Conteúdo do Carrinho</h5>
        <p> Total de itens: <%=itens_compras.size()%></p> 
        <p> Vendedor: <%=usuario.getNome()%></p>
         
        <%
            for(int i=0;i<itens_compras.size();i++){
                Produto aux = itens_compras.get(i);
                Categoria cat= categoriadao.getCategoriaPorID(aux.getCategoria());
                session.setAttribute("produto", aux);
                
        %>
            <div class="card" style="width: 16rem; margin-bottom: 10px;">    
                
                <img class="card-img-top" src="assets/img/produtoid<%=aux.getId()%>.jpg" alt="imagem produto">
                <div class="card-body">
                   
                    <h5 class="card title"><%=aux.getNome()%></h5> 
                    <p class="card text"><%=aux.getDescricao()%></p>
                    <p class="card text"><%=aux.getPrecoVenda()%></p>
                    <p class="card text"><%=cat.getNome()%></p>
                 </div>
                 <div class="card-footer">
                     <a href="ExcluirCarrinho?id=<%=aux.getId()%>" class="btn btn-primary" style=" margin-bottom:10px;">Excluir</a>
                     <a  href="VendaController?acao=incluir" class="btn btn-primary">Finalizar Compra do Item</a>
                 </div>
            </div>
                     
            <% }
             %>
        <%@include file="footer.jsp" %>
        <%@include file="Scriptsbasicos.html" %>
       </div>
    </body>
</html>
