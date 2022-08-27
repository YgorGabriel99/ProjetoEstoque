
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*,aplicacao.*"%>
<%@page import="controller.ProdutoController"%>
<%@page import="model.*"%>
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
         
         <h1>Veja aqui os produtos</h1>
         <div class="row"> 
             <div id="prod" class="container-fluid">
              
        <%      
                
                ArrayList <Produto> produto = (ArrayList<Produto>) request.getAttribute("produtos");
                CategoriaDAO categoriadao=new CategoriaDAO();
                    for(int i=0; i<produto.size(); i++){
                        
                        Produto aux= produto.get(i);
                        Categoria cat= categoriadao.getCategoriaPorID(aux.getCategoria());
                        if(aux.getQuantidade()>0){
                            if(aux.getLiberado().equals("S")){
                %>  
          
         
                 <div class="card" style="max-width: 30rem; margin:10px; align: center;">    

                 <img class="item card-img-top img-fluid" src="assets/img/produtoid<%=aux.getId()%>.jpg" alt="imagem produto">
                 <div class="card-body" > 
                    
                    <h5 class="card title"><%=aux.getNome()%></h5>
                    <p class="card text">Descrição: <%=aux.getDescricao()%></p>
                    <p class="card text">R$ <%=aux.getPrecoVenda()%></p>
                    <p class="card text">Categoria: <%=cat.getNome()%> </p>
                 </div>   
                 </div>
             <%     }
                } 
              }   
             %>
            </div>  
         </div>
        </div>
          <%@include file="footer.jsp" %>
          <%@include file="Scriptsbasicos.html"  %>
    
    </body>
</html>
