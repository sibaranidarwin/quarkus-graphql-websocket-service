package org.acme.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/ws/books")
@ApplicationScoped
public class BookWebSocket {

    private final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        broadcast("A new user connected. Total: " + sessions.size());
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);

        broadcast("A user disconnected. Total: " + sessions.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        broadcast("Message: " + message);
    }

    public void broadcast(String message) {
        sessions.forEach(s -> {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
