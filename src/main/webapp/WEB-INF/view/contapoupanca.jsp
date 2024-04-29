<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
    href='<c:url value ="./resources/css/styles.css"/>'>
<title>Conta Poupanca</title>
</head>
<body>
    <div>
        <jsp:include page="menu.jsp" />
    </div>
    <br />
    <div align="center" class="container">
        <form action="contapoupanca" method="post">
            <p class="title">
                <b>Conta Poupanca</b>
            </p>
            <table>
                <tr>
                    <td colspan="3">
                    <input class="input_data_id" type="number"
                        id="num_conta" name="num_conta" placeholder="Numero conta" 
                        value='<c:out value="${contapoupanca.num_conta}"/>'>
                        </td>
                    <td>
                    <input type="submit" id="botao" name="botao"
                        value="Buscar">
                        </td>
                </tr>
                <tr>
                    <td colspan="4">
                    <input class="input_data" type="number"
                        id="dia_rendimento" name="dia_rendimento" placeholder="Dia Rendimento"
                        value='<c:out value="${contapoupanca.dia_rendimento}"/>'>
                        </td>
                </tr>
                <tr>
                    <td colspan="4"><input class="input_data" type="number"
                        id="taxa_rendimento" name="taxa_rendimento" placeholder="Taxa Rendimento"
                        value='<c:out value="${contapoupanca.taxa_rendimento}"/>'>
                        
                        </td>
                </tr>
                <tr>
                    <td><input type="submit" id="botao" name="botao"
                        value="Cadastrar"></td>
                        <td><input type="submit" id="botao" name="botao"
						value="Alterar"></td>
                    <td><input type="submit" id="botao" name="botao"
                        value="Listar"></td>
                </tr>
            </table>
        </form>
    </div>
    <br />
    <div align="center">
        <c:if test="${not empty erro}">
            <H2>
                <b><c:out value="${erro}"/></b>
            </H2>
        </c:if>
    </div>
    <div align="center">
        <c:if test="${not empty saida}">
            <H2>
                <b><c:out value="${saida}"/></b>
            </H2>
        </c:if>
    </div>
    <br/>
    <div align="center">
    <c:if test="${not empty contaspoupanca}">
    <table border="1">
          <thead>
              <tr>
                  <th>Número da conta</th>
                  <th>Dia Rendimento</th>
                  <th>Taxa Rendimento</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach var="p" items="${contaspoupanca}">
          <tr>
              <td><c:out value="${p.num_conta}"/></td>
              <td><c:out value="${p.dia_rendimento}"/></td>
              <td><c:out value="${p.taxa_rendimento}"/></td>
              <td><a href="contapoupanca?cmd=alterar&codigo=${p.num_conta}">ALTERAR</a></td>
               <td><a href="contapoupanca?cmd=excluir&codigo=${p.num_conta}">EXCLUIR</a></td>
          </tr>
          </c:forEach>
          </tbody>
    </table>
    </c:if>
    </div>
</body>
</html> 