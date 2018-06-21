var websocket;
$(document).ready(openWebSocket);

function openWebSocket() {
    var wsProtocol = window.location.protocol == "https:" ? "wss" : "ws";
    var wsURI = wsProtocol + '://' + window.location.host + '/ARS-persistence/offer/comments';
    websocket = new WebSocket(wsURI);
    
    websocket.onmessage = function (event) {
        addComment(event.data);
    }

    websocket.onerror = function(event) {
        console.error(event);
    }

    waitForSocketConnection(websocket)
}

function waitForSocketConnection(socket) {
    setTimeout(function() {
        if (socket.readyState === 1) {
            document.getElementById('hellomessage').value = "connection is seccessful";
            return;
        } else {
            waitForSocketConnection(socket);
        }
    }, 5);
};

function sendWebSocketMessage() {
    // var msg = {
    //     sender: "METMP",
    //     comment: document.getElementById("comment").value
    // };

    msg = document.getElementById("comment").value;

    websocket.send(msg);

}

function addComment(comment) {
    document.getElementById('commentsArea').value += comment + '\n';
}
