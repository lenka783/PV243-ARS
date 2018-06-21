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
                    <input type="button" id="Send" onclick="sendWebSocketMessage();" value="Send"/>
                </div>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>


<%--
  Created by IntelliJ IDEA.
  User: jsmolar
  Date: 6/20/18
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<html>--%>
<%--<head>--%>
<%--<title>WebSocket: Say Hello</title>--%>
<%--<script type="text/javascript">--%>
<%--var websocket = null;--%>
<%--function connect() {--%>
<%--var wsProtocol = window.location.protocol == "https:" ? "wss" : "ws";--%>
<%--var wsURI = wsProtocol + '://' + window.location.host + '/ARS-persistence/offer/comments';--%>
<%--websocket = new WebSocket(wsURI);--%>

<%--websocket.onopen = function() {--%>
<%--displayStatus('Open');--%>
<%--document.getElementById('Send').disabled = false;--%>
<%--};--%>
<%--websocket.onmessage = function(event) {--%>
<%--document.getElementById('hellomessage').value = event.data;--%>
<%--addComment(event.data)--%>
<%--};--%>
<%--websocket.onerror = function(event) {--%>

<%--};--%>
<%--websocket.onclose = function() {--%>
<%--displayStatus('Closed');--%>
<%--document.getElementById('sayHello').disabled = true;--%>
<%--};--%>
<%--}--%>

<%--function disconnect() {--%>
<%--if (websocket !== null) {--%>
<%--websocket.close();--%>
<%--websocket = null;--%>
<%--}--%>
<%--displayStatus("Closed")--%>
<%--}--%>

<%--function sendMessage() {--%>
<%--if (websocket !== null) {--%>
<%--var content = document.getElementById('comment').value;--%>
<%--websocket.send(content);--%>
<%--} else {--%>
<%--displayStatus("Closed")--%>
<%--}--%>
<%--}--%>

<%--function displayStatus(status) {--%>
<%--var currentStatus = document.getElementById('currentstatus');--%>
<%--currentStatus.value = status;--%>
<%--}--%>

<%--function addComment(data) {--%>
<%--var display = document.getElementById('commentsArea')--%>
<%--display.value = data;--%>
<%--}--%>

<%--</script>--%>
<%--</head>--%>
<%--<body>--%>

<%--<div>--%>
<%--<div id="connect-container">--%>
<%--<div>--%>
<%--<fieldset style="width: 20%">--%>
<%--<legend>User comments about offer</legend>--%>
<%--<div>--%>
<%--<%--%>
<%--String name="Comments are empty. Feel free to add some!";--%>
<%--%>--%>
<%--<textarea readonly name="commentsArea" style="width:100%" rows="20">--%>
<%--<%=name%>--%>
<%--</textarea>--%>
<%--</div>--%>
<%--<div>--%>
<%--<input id="comment" type="text" size="40" style="width: 100%"/>--%>
<%--<input type="button" id="Send" onclick="sendMessage();" value="Send" disabled="disabled"/>--%>
<%--</div>--%>
<%--</fieldset>--%>
<%--</div>--%>
<%--<div>--%>
<%--<fieldset>--%>
<%--<legend>Connect or disconnect using websocket :</legend>--%>
<%--<input type="button" id="connect" onclick="connect();" value="Open Connection" />--%>
<%--<input type="button" id="disconnect" onclick="disconnect();" value="Close Connection" />--%>
<%--</fieldset>--%>
<%--</div>--%>
<%--<div>Current WebSocket Connection Status: <output id="currentstatus" class="message">Closed</output></div>--%>
<%--<div>--%>
<%--<output id="hellomessage" />--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>