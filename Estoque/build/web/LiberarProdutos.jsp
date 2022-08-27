
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,aplicacao.*"%>
<%@page import="controller.ProdutoController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
         <%@include file="Cabecalho.html" %>
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
            
         <h1>Veja aqui os produtos</h1>
         <div class="row col-6-sm"> 
             <div id="prod" class="container-fluid">
              
        <%      
                
            ArrayList <Produto> produto = (ArrayList<Produto>) session.getAttribute("produtos");               
                    for(int i=0; i<produto.size(); i++){
                        
                        Produto aux= produto.get(i);
                       
                        
                %>  
          
         
                 <div class="card" style="max-width: 30rem; margin:10px; align: center;">    

                 <img class="item card-img-top img-fluid" src="assets/img/produtoid<%=aux.getId()%>.jpg" alt="imagem produto">
                 <div class="card-body" > 
                    
                    <h5 class="card title"><%=aux.getNome()%></h5>
                    <p class="card text">Descrição: <%=aux.getDescricao()%></p>
                    <p class="card text">Preço de Compra R$ <%=aux.getPrecoCompra()%></p>
                    <p class="card text">Preço de Venda R$ <%=aux.getPrecoVenda() %> </p>
                    <p class="card text"> Quantidade Disponível: <%=aux.getQuantidade() %> </p>
                    <p class="card text">Liberado pra venda? <%=aux.getLiberado() %></p>
                    <p class="card text">Categoria: <% if((aux.getCategoria())== 1){
                         out.println("Eletrodoméstico");
                    }else out.println("TV e Áudio");
                         %> </p>
                 </div> 
                
                 <div class="card-footer">
                     <% if(aux.getLiberado().equals("S")){ %>
                     <a href="LiberarProd?id=<%=aux.getId()%>" class="btn btn-danger"> Não-Liberar</a>
              <%   }else{ %>
                 <a href="LiberarProd?id=<%=aux.getId()%>" class="btn btn-primary"> Liberar</a>
                 <% } %>
                 </div>
                 </div>
                <%  
                     }   
                 %>
             </div>
             
            </div>  
         </div>
        
          <%@include file="footer.jsp" %>
          <%@include file="Scriptsbasicos.html"  %>
    
    </body>
</html>
