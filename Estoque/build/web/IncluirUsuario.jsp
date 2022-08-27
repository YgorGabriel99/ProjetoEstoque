
<%@page import="aplicacao.Usuario"%>
<%@page import="controller.UsuarioController"%>
<%@page import="model.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Cabecalho.html" %>
        <title>Criar Usuário</title>
    </head>
    <body>
       <div class="container mt-2">
           <jsp:include page="menu.jsp"/>
           
           
           <%
           
                Usuario aux = (Usuario)request.getAttribute("usuario");
               
           %>
           <div class="col-6 mt-5">
               <h4>Criar novo Usuário</h4>
               <form method="POST" action="UsuarioController" >
                   
                   <input type="hidden" class="form-control" name="id" value="<%= aux.getId()%>">
                   <div class="form-group"> 
                       <label for="Nome"> Nome</label>
                       <input type="text" class="form-control" name="nome"  required size="30" placeholder="Seu nome">
                    </div>
                     <div class="form-group"> 
                       <label for="CPF"> CPF</label>
                       <input type="text" class="form-control cpf" name="cpf" required size="30" placeholder="CPF">
                    </div>
                     <div class="form-group"> 
                       <label for="Senha">Senha</label>
                       <input type="text" class="form-control" required size="15" placeholder="Digite a senha">
                    </div>
                    <div class="form-group"> 
                       <label for="RepSenha">Repita a senha</label>
                       <input type="text" class="form-control" name="senha" required size="15" placeholder="Confirmar senha">
                    </div>
                  
                   <div class="form-group"> 
                       <label for="Tipo"> Qual será o perfil de Usuário?</label>
                     <select  id="tipo" class="form-control" name="tipo">
                       <option value="0">Administrados</option>
                       <option value="1">Vendedor</option>
                       <option value="2">Comprador</option>
                    </select>
                     
                   </div>
                    <button type="submit" class="btn btn-primary"> Enviar</button>
                    <a href="UsuarioController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
               </form>
           </div>
  
            <%@include file="footer.jsp" %>       
            <%@include file="Scriptsbasicos.html" %>    
          </div>       
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
          
    </body>
</html>
