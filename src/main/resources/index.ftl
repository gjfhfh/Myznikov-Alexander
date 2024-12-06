<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/gh/yegor256/tacit@gh-pages/tacit-css-1.6.0.min.css"/>
</head>

<body>

<h1>List of articles</h1>
<table>
    <tr>
        <th>Name</th>
        <th>Number of comments</th>
    </tr>
    <#list articles as article>
        <tr>
            <td>${article.title}</td>
            <td>${article.number}</td>
        </tr>
    </#list>
</table>

</body>

</html>
