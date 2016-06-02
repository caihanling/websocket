package zx.soft.web.websocket;

import java.io.IOException;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.json.JsonUtils;
import zx.soft.web.impl.DetailImpl;
import zx.soft.web.model.Detail;

@ServerEndpoint(value = "/websocket")
public class WebsocketTest {

	private static Logger logger = LoggerFactory.getLogger(WebsocketTest.class);

	@OnMessage
	public void onMessage(String message, Session session) {
		try {

			// Send 100 messages to the client every 0.1 seconds
			int sentMessages = 0;
			DetailImpl detailImpl = new DetailImpl();
			List<Detail> list = detailImpl.readFile();
			while (sentMessages < 200) {

				for (Detail detail : list) {
					Thread.sleep(250);
					session.getBasicRemote().sendText(JsonUtils.toJsonWithoutPretty(detail));
					sentMessages++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("e :", e.getMessage());
		}

	}

	@OnOpen
	public void onOpen() {
		System.out.println("Client connected");
	}

	@OnClose
	public void onClose() {
		System.out.println("Connection closed");
	}

}
