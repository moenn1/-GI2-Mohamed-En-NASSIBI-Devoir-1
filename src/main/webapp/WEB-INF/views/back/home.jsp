<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../header.jsp" %>


<%@page import="com.pojos.GameState"%>
<%@ page language="java"
         pageEncoding="windows-1256"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type"
        content="text/html; charset=windows-1256">
  <title>Dice game</title>
</head>
<body class="dark:bg-gray-900 content-center">
<div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
<div class="max-w-2xl p-6 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 text-white">



  <h1>Bonjour, <c:out value="${sessionScope.gameState.user.name}" /></h1><br>

  <a href="${pageContext.request.contextPath}/back/ReinitGameServlet" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">Restart Game</a>

  <a href="${pageContext.request.contextPath}/logoutServlet" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">Logout</a>


  <br><br>

  <%
    GameState gameState = (GameState) request.getSession().getAttribute("gameState");
  %>


  <h1>My High Score</h1>
  <c:choose>
    <c:when test="${sessionScope.gameState.user.bestScore > sessionScope.gameState.user.score}">
      ${sessionScope.gameState.user.bestScore}
    </c:when>

    <c:otherwise>
      ${sessionScope.gameState.user.score}
    </c:otherwise>
  </c:choose>


  <h1>Dice game</h1>


  <form action="${pageContext.request.contextPath}/back/gameServlet">

    <select id="diceNumber" name="diceNumber" class="block py-2.5 px-0 w-full text-sm text-gray-500 bg-transparent border-0 border-b-2 border-gray-200 appearance-none dark:text-gray-400 dark:border-gray-700 focus:outline-none focus:ring-0 focus:border-gray-200 peer">
      <option selected>Choose a dice</option>
      <option value="1">Dice 1</option>
      <option value="2">Dice 2</option>
      <option value="3">Dice 3</option>
    </select>


    <input type="submit" value="Lancer le dé" class="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"/>
  </form>




  <%
    out.print(gameState);
  %>
</div>
</div>

</body>
</html>