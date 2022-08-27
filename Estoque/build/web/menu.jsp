  <%@include file="Cabecalho.html" %>
<nav class="navbar navbar-expand-sm  navbar-dark bg-dark">
  <a class="navbar-brand" href="index.jsp">Início</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#conteudoNavbarSuportado" aria-controls="conteudoNavbarSuportado" aria-expanded="false" aria-label="Alterna navegação">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="ProdutoController?acao=mostrar">Produtos</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="LoginForm.jsp">Fazer Login</a>
      </li>
    </ul>
  </div>
</nav>
<%@include file="Scriptsbasicos.html" %>