import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class WebSocketAuthenticationInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
		WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		// Extract and validate the JWT token from the request headers
		String token = getTokenFromRequest(request);
		// Verify the token with Keycloak or local JWT parsing
		return validateToken(token);
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
		WebSocketHandler wsHandler, Exception exception) {
		// No action needed after handshake
	}

	private String getTokenFromRequest(ServerHttpRequest request) {
		if (!request.getHeaders().containsKey("Authorization")){
			throw new RuntimeException();
		}
		return request.getHeaders().get("Authorization").get(0);
	}

	private boolean validateToken(String token) {
		// Validate the token using Keycloak's public key or introspect endpoint
		// ...
		return true;
	}
}
