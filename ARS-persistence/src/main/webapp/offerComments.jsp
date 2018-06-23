<%--
  Created by IntelliJ IDEA.
  User: jsmolar
  Date: 6/20/18
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>WebSocket: Say Hello</title>
    <script type="text/javascript" src="resources/javascript/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="resources/javascript/websocket.js"></script>
</head>
<body>
<div>
    <div id="connect-container">
        <div>
            <fieldset style="width: 20%">
                <legend>User comments about offer</legend>
                <div>
                    <textarea readonly id="commentsArea" style="width:100%; align-content: flex-start" rows="20">
                    </textarea>
                </div>
                <div>
                    <input id="comment" type="text" size="40" style="width: 100%"/>
                    <input type="button" id="Send" onclick="sendWebSocketMessage()" value="Send"/>
                </div>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>
