foo
<@upper>
    bar
    <#-- 这里允许使用所有的FTL -->
    <#list ["red", "green", "blue"] as color>
        ${color}
    </#list>
</@upper>
wombat