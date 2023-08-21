package com.websockets.wspoc;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Add the session to a data structure based on the tenant ID
        String tenantId = extractTenantIdFromSession(session);
        // Add session to tenant-specific session storage
        // ...
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming messages from clients
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Remove the session from the data structure
        String tenantId = extractTenantIdFromSession(session);
        // Remove session from tenant-specific session storage
        // ...
    }

    private String extractTenantIdFromSession(WebSocketSession session) {
        // Extract tenant ID from the session's attributes
        // For example: return session.getAttributes().get("tenantId");
        return null;
    }
}
