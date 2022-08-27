
<%@page import="aplicacao.*"%>
<%@page import="controller.ClienteController"%>
<%@page import="model.ClienteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Cabecalho.html" %>
        <title>Criar Cliente</title>
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
           
                Cliente aux = (Cliente)request.getAttribute("cliente");
               
           %>
           <div class="col-6 mt-5">
               <h4>Criar novo Cliente</h4>
               <form id="meuForm" method="POST"  onsubmit="return validarCPF()" action="ClienteController" >
                   
                   <input type="hidden" class="form-control" name="id" value="<%= aux.getId()%>">
                   <div class="form-group"> 
                       <label for="Nome"> Nome</label>
                       <input type="text" class="form-control" name="nome"  required size="30" placeholder="Seu nome">
                    </div>
                     <div class="form-group"> 
                       <label for="CPF"> CPF</label>
                       <input type="text" class="form-control cpf"  id="cpf" name="cpf" required size="30" placeholder="CPF">
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
           
            
            function validarCPF() {
                cpf=document.getElementById("cpf").value ;
                cpf = cpf.replace(/[^\d]+/g,'');	
                if(cpf == '') return false;	
                // Elimina CPFs invalidos conhecidos	
                if (cpf.length != 11 || 
                        cpf == "00000000000" || 
                        cpf == "11111111111" || 
                        cpf == "22222222222" || 
                        cpf == "33333333333" || 
                        cpf == "44444444444" || 
                        cpf == "55555555555" || 
                        cpf == "66666666666" || 
                        cpf == "77777777777" || 
                        cpf == "88888888888" || 
                        cpf == "99999999999"){
                        
                        alert("CPF inválido");        
                        return false;}		
                // Valida 1o digito	
                add = 0;	
                for (i=0; i < 9; i ++)		
                        add += parseInt(cpf.charAt(i)) * (10 - i);	
                        rev = 11 - (add % 11);	
                        if (rev == 10 || rev == 11){		
                                rev = 0;}	
                        if (rev != parseInt(cpf.charAt(9))){		
                            alert("CPF inválido");   
                            return false;}		
                // Valida 2o digito	
                add = 0;	
                for (i = 0; i < 10; i ++)		
                        add += parseInt(cpf.charAt(i)) * (11 - i);	
                rev = 11 - (add % 11);	
                if (rev == 10 || rev == 11){	
                        rev = 0;}	
                if (rev != parseInt(cpf.charAt(10))){
                        alert("CPF inválido");
                        return false;}		
                return true;   
            }
            
          </script>
          
         </div>       
          
          
    </body>
</html>
