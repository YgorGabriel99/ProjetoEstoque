
<%@page import="aplicacao.*"%>
<%@page import="controller.FornecedorController"%>
<%@page import="model.FornecedorDAO"%>
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
           
                Fornecedor aux = (Fornecedor)session.getAttribute("fornecedor");
               
           %>
           <div class="col-6 mt-5">
               <h4>Criar novo Fornecedor</h4>
               <form id="meuForm" method="POST"  onsubmit="return validarCNPJ()" action="FornecedorController" >
                   
                   <input type="hidden" class="form-control" name="id" value="<%= aux.getId()%>">
                   <div class="form-group"> 
                       <label for="Nome"> Razão Social</label>
                       <input type="text" class="form-control" name="razaosocial"  required size="30" placeholder="Razão Social do Fornecedor">
                    </div>
                     <div class="form-group"> 
                       <label for="CPF"> CNPJ</label>
                       <input type="text" class="form-control cnpj"  id="cnpj" name="cnpj" required size="30" placeholder="CNPJ">
                    </div>
                     <div class="form-group"> 
                       <label for="Endereco">Endereço</label>
                       <input type="text" class="form-control" name="endereco" required size="15" placeholder="Seu endereço">
                    </div>
                    <div class="form-group"> 
                       <label for="Bairro">Bairro</label>
                       <input type="text" class="form-control" name="bairro" required size="15" placeholder="Seu bairro">
                    </div>
                  
                    <div class="form-group"> 
                       <label for="Cidade">Cidade</label>
                       <input type="text" class="form-control" name="cidade" required size="15" placeholder="Sua cidade">
                    </div>
                    <div class="form-group"> 
                       <label for="UF">UF</label>
                       <input type="text" class="form-control" name="uf" required size="15" placeholder="Sua UF">
                    </div>
                    <div class="form-group"> 
                       <label for="CEP">CEP</label>
                       <input type="text" class="form-control cep" name="cep" required size="15" placeholder="Seu CEP">
                    </div>
                    <div class="form-group"> 
                       <label for="Telefone">Telefone</label>
                       <input type="text" class="form-control telefone" name="telefone" required size="15" placeholder="Seu telefone">
                    </div>
                    <div class="form-group"> 
                       <label for="Email">Email</label>
                       <input type="text" class="form-control" name="email" required size="30" placeholder="meuemail@server.com">
                    </div>
                    <button type="submit" class="btn btn-primary"> Enviar</button>
                    <a href="AreaRestrita.jsp" class="btn btn-outline-danger">Retornar</a>
               </form>
           </div>
  
          <%@include file="Scriptsbasicos.html" %> 
          <jsp:include page="footer.jsp"/>
          <script>
           
            
            function validarCNPJ() {
                cnpj=document.getElementById("cnpj").value ;
                cnpj = cnpj.replace(/[^\d]+/g,'');

                if(cnpj == ''){
                alert("CNPJ inválido");    
                return false;}

                if (cnpj.length != 14){
                    alert("CNPJ inválido"); 
                    return false;}

                // Elimina CNPJs invalidos conhecidos
                if (cnpj == "00000000000000" || 
                    cnpj == "11111111111111" || 
                    cnpj == "22222222222222" || 
                    cnpj == "33333333333333" || 
                    cnpj == "44444444444444" || 
                    cnpj == "55555555555555" || 
                    cnpj == "66666666666666" || 
                    cnpj == "77777777777777" || 
                    cnpj == "88888888888888" || 
                    cnpj == "99999999999999"){
                    alert("CNPJ inválido"); 
                    return false;}

                
                tamanho = cnpj.length - 2
                numeros = cnpj.substring(0,tamanho);
                digitos = cnpj.substring(tamanho);
                soma = 0;
                pos = tamanho - 7;
                for (i = tamanho; i >= 1; i--) {
                  soma += numeros.charAt(tamanho - i) * pos--;
                  if (pos < 2)
                        pos = 9;
                }
                resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
                if (resultado != digitos.charAt(0)){
                    alert("CNPJ inválido"); 
                    return false;}

                tamanho = tamanho + 1;
                numeros = cnpj.substring(0,tamanho);
                soma = 0;
                pos = tamanho - 7;
                for (i = tamanho; i >= 1; i--) {
                  soma += numeros.charAt(tamanho - i) * pos--;
                  if (pos < 2)
                        pos = 9;
                }
                resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
                if (resultado != digitos.charAt(1)){
                    alert("CNPJ inválido");  
                    return false;}

                return true;

            }
            
          </script>
          
         </div>       
          
          
    </body>
</html>
