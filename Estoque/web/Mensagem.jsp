<%-- 
    Document   : Mensagem
    Created on : 03/03/2021, 18:06:33
    Author     : ygorf
--%>
<%@page import="aplicacao.Produto"%>
<%@page import="controller.ProdutoController"%>
<%@page import="model.EstoqueDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado</title>
        <%@include file="Cabecalho.html" %>
    </head>
    <body>
       <div class="container mt-2">
        <jsp:include page="menu.jsp"/>
        <h1><%
           
                 out.println(request.getAttribute("mensagem"));
            %></h1>
       </div>
            <%@include file="footer.jsp" %>
            <%@include file="Scriptsbasicos.html"  %>
    </body>
</html>
