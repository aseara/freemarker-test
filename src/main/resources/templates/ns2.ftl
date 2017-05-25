<#import "ns1.ftl" as ns>
<html>
    <head>
        <title>Welcome!</title>
    </head>
    <body>
        <h1>Welcome ${user}!</h1>
        <p>
        <@ns.greet person="${user}" />
        </p>
    </body>
</html>