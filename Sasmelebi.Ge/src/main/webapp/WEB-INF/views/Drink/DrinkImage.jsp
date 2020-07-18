<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sasmelebi.ge</title>
    <style>
        .img-container {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="img-container">
    <img src=<%= request.getParameter("imagePath")%>  width=400 height=500/>
</div>
</body>
</html>