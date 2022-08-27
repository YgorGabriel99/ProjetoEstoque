<%-- 
    Document   : LoginForm
    Created on : 04/03/2021, 17:48:10
    Author     : ygorf
--%>
<%@page import="aplicacao.Usuario"%>
<%@page import="controller.ValidarLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%@include file="Cabecalho.html" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div class="container mt-2">
           
         <%@include file="menu.jsp" %>
        <h1>Loja Compra Certa </h1>
      
        <%
          Usuario usuario = (Usuario) request.getAttribute("usuario");
        %>
        <% 
           if((request.getAttribute("mensagem")) != null){
            out.println(request.getAttribute("mensagem"));
           }
        %>
        
        
        <div class="col-6 mt-5">
            
            
            <form method="POST" action="ValidarLogin"  onsubmit="return validarCPF()" >
                <input type="hidden" name="id" >
                <div class="form-group">
                    <label for="Login">Login</label>
                    <input type="text" class="form-control" name="login" id="login" required size="30" placeholder="seu CPF">
                </div>
                <div class="form-group">
                    <label for="Senha">Senha</label>
                    <input type="password" class="form-control" name="senha" required size="30" placeholder="Sua senha">
                </div>
                <button type="submit" class="btn btn-primary"> Entrar</button>
            </form>
        
        </div>
  
        
        
         <%@include file="footer.jsp" %>
        <%@include file="Scriptsbasicos.html" %>
         <script>
            
            $(document).ready(function(){
                $('#login').mask('000.000.000-00', {reverse: true});
            });
         </script>
         <script>
            function validarCPF() {
                cpf=document.getElementById("login").value ;
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
