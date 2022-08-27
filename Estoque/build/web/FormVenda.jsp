<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.ClienteDAO"%>
<%@page import="model.EstoqueDAO"%>
<%@page import="aplicacao.*"%>
<%@page import="controller.VendaController"%>
<%@page import="java.sql.Date"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Cabecalho.html" %>
        <title>Formulário de Venda</title>
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
       
            
             <%
                EstoqueDAO estoquedao =new EstoqueDAO();
                ClienteDAO clientedao= new ClienteDAO();
                ArrayList<Cliente> clientes = clientedao.getLista();
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                Venda aux = (Venda)session.getAttribute("venda");
                Produto item = new Produto();
                item=(Produto)session.getAttribute("produto");
                if(aux.getId()== 0){
                       java.util.Date dataUtil = new java.util.Date();
                       java.sql.Date data = new java.sql.Date(dataUtil.getTime());
                       aux.setDataVenda(data);
                }
                %>
           <div class="col-6 mt-5">
               <h4>Finalizar Compra</h4>
               <form id="formVenda"  method="POST" onsubmit="return validarDesconto()" action="VendaController" >
                   
                   <p>Produto:<%=item.getNome()%></p>
                   
                 <label for="Cliente"> Cliente</label>
                 <select  id="cliente" class="form-control" name="cliente">
                       <% for(int i=0; i<clientes.size();i++){ 
                       
                       Cliente cli= clientes.get(i);
                       %>
                          
                        <option value="<%=cli.getId()%>"> <%=cli.getNome()%> </option>
                       <%}%>
                 </select> 
                 
                 <label for="Quantidade"> Quantidade</label>
                <select  id="quantidade" class="form-control" name="quantidade">
                       <% for(int i=1; i<item.getQuantidade();i++){ %>
                          
                        <option value="<%=i%>"> <%=i%> </option>
                       <%}%>
               </select>    
                <div class="form-group">
                    <label for="Valor"> Valor </label>
                    <input type="text" class="form-control" name="valor" id="valor" required size="30" placeholder="<%=item.getPrecoVenda()%>">
                </div>    
                    <input type="hidden" class="form-control" name="id" value="<%= aux.getId()%>">  
                    <input type="hidden" class="form-control" name="data" value="<%=aux.getDataVenda()%>">
                                                                                         
                    <input type="hidden" class="form-control" name="produto" value="<%=item.getId()%>">
                    <input type="hidden" class="form-control" name="vendedor"  value="<%=usuario.getId()%>">
                    <button type="submit" class="btn btn-primary"> Enviar</button>
                    <a href="OlharCarrinho.jsp" class="btn btn-outline-danger">Retornar</a>
                   
               </form>
           </div>
          <%@include file="footer.jsp" %>
          <%@include file="Scriptsbasicos.html" %>    
         
            <script>
            function validarDesconto() {
                
                
                
                var    valorc =document.getElementById("valor").value ;
                var    pCompra= <%=item.getPrecoCompra()%>;
                var    pDesconto= pCompra + (pCompra*0,1);
                       
                        if(valorc < pDesconto){
                       
                        alert("Desconto inferior ao mínimo permitido");	
                        return false;
                        }                                                                         
                return true;
            
            };
          </script>
       
       
       
       </div>       
          
          
    </body>
    
</html>
