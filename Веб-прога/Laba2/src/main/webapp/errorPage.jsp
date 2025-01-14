<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: vim15
  Date: 12.11.2024
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ошибка запроса</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body, html {
            height: 100%;
            font-family: Arial, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #f2f2f2;
            color: #333;
        }

        .error-container {
            text-align: center;
            padding: 40px;
            background-color: #ffdddd;
            border: 2px solid #ff6b6b;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
        }

        .error-title {
            font-size: 24px;
            font-weight: bold;
            color: #cc0000;
            margin-bottom: 15px;
        }

        .error-message {
            font-size: 18px;
            color: #333;
            margin-bottom: 20px;
        }

        .error-link {
            display: inline-block;
            font-size: 16px;
            color: #0056b3;
            text-decoration: none;
            border-bottom: 1px solid transparent;
            transition: color 0.2s, border-bottom 0.2s;
        }

        .error-link:hover {
            color: #003580;
            border-bottom: 1px solid #003580;
        }
    </style>
</head>
<body>
<div class="error-container">
    <div class="error-title">Ошибка</div>
    <p class="error-message">Неправильный формат запроса</p>
    <a href="index.jsp" class="error-link">Вернуться на главную страницу</a>
</div>
</body>
</html>