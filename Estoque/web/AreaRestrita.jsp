<%-- 
    Document   : AreaRestrita
    Created on : 04/03/2021, 21:07:38
    Author     : ygorf
--%>

<%@page import="java.util.*,aplicacao.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%@include file="Cabecalho.html" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Area Restrita</title>
    </head>
    <body>
       
     <div class="container mt-2 ">
           
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
            
         <div class="container">
	<p></p>
	<!-- Conteúdo aqui -->
         <div class="col-6 mt-5">
		<div class="card" style="width: 18rem;">
		     <div class="card-header">
                         <p>Bem vindo <%=usuario.getNome()%> !</p> 
			</div>
                        <img class="card-img-top" src="assets/img/usuarioId<%=usuario.getId()%>.jpg" alt="Imagem do Usuário">
			<div class="card-body">
				<p class="card-text"> O usuário logado possui o CPF <%=usuario.getCpf()%></p>
                                <a class="btn btn-danger" href="index.jsp">Log out</a>
                                
			</div>			
		</div>
                <div class="container" style="margin-top: 10px;" >       
                <% if(Integer.parseInt(usuario.getTipo()) == 1)  {   %>            
                       
                 <a class=" btn btn-light"  href="ClienteController?acao=incluir"> Adicionar Cliente</a>
                 <a class="btn btn-light" href="ProdutoController?acao=venda">Nova Venda</a>
                 <a class="btn btn-light" href="VendaController?acao=mostrar"> Consultas Vendas</a>
                
                <% }%>  
                <% if(Integer.parseInt(usuario.getTipo()) == 2)  {   %>            
                           
                 <a class=" btn btn-light"  href="FornecedorController?acao=mostrar"> Consultar Fornecedores </a>
                 <a class="btn btn-light"    href="FornecedorController?acao=incluir"> Novo Fornecedor</a>
                 <a class="btn btn-light" href="CategoriaController?acao=mostrar">Consultar Categorias</a> 
                 <a class="btn btn-light" href="CategoriaController?acao=incluir">Nova Categoria</a> 
                 <a class="btn btn-light" href="ProdutoController?acao=incluir">Registar Novo Produto</a>
                 <a class="btn btn-light" href="CompraController?acao=incluir">Registar Compras</a>
                 <a class="btn btn-light" href="CompraController?acao=mostrar">Consultar Compras</a>                
               
                 
                <% }%>  
                <% if(Integer.parseInt(usuario.getTipo()) == 2 || Integer.parseInt(usuario.getTipo()) == 0)  {   %>     
                <a class="btn btn-light" href="ProdutoController?acao=liberar"> Liberar Produtos </a>
                 <% }%>  
                </div>
         </div>
         </div>
        <%@include file="footer.jsp" %>
        <%@include file="Scriptsbasicos.html" %>
         
       </div> 
     </body>
</html>
