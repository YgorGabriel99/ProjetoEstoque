
<%@page import="aplicacao.*"%>
<%@page import="controller.CategoriaController"%>
<%@page import="model.CategoriaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Cabecalho.html" %>
        <title>Criar Fornecedor</title>
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
           
                Categoria aux = (Categoria)session.getAttribute("categoria");
               
           %>
           <div class="col-6 mt-5">
               <h4>Criar nova Categoria</h4>
               <form id="meuForm" method="POST"  action="CategoriaController" >
                   
                   <input type="hidden" class="form-control" name="id" value="<%= aux.getId()%>">
                   <div class="form-group"> 
                       <label for="Nome">Nome da categoria</label>
                       <input type="text" class="form-control" name="nome"  required size="30" placeholder="Nomeie a categoria">
                    </div>
                     
                    <button type="submit" class="btn btn-primary"> Enviar</button>
                    <a href="AreaRestrita.jsp" class="btn btn-outline-danger">Retornar</a>
               </form>
           </div>
  
          <%@include file="Scriptsbasicos.html" %> 
          <jsp:include page="footer.jsp"/>
          
          
         </div>       
          
          
    </body>
</html>
