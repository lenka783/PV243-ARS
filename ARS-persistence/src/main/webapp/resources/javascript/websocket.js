var websocket;
$(document).ready(openWebSocket);

function openWebSocket() {
    var wsProtocol = window.location.protocol == "https:" ? "wss" : "ws";
    var wsURI = wsProtocol + '://' + window.location.host + '/ARS-persistence/comments/' + getUrlVars()['offer_id'];
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

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
