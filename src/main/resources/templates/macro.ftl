<#macro greet person>
<span style="font-size: large">Hello ${person}!</span>
</#macro>
<html>
    <head>
        <title>Welcome!</title>
    </head>
    <body>
        <h1>Welcome ${user}!</h1>
        <p>
            <@greet person="${user}" />
        </p>
    </body>
</html>