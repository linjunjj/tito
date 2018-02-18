package com.client;

import java.util.HashMap;
import java.util.Map;

import com.client.handler.GroupMsgRespHandler;
import com.client.handler.JoinGroupRespHandler;
import com.client.handler.LoginRespHandler;
import com.client.handler.P2PRespHandler;
import com.common.ShowcaseAbsAioHandler;
import com.common.ShowcasePacket;
import com.common.Type;
import com.common.intf.AbsShowcaseBsHandler;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;


/**
 *
 * @author tanyaowu
 * 2017年3月27日 上午12:18:11
 */
public class ShowcaseClientAioHandler extends ShowcaseAbsAioHandler implements ClientAioHandler {

	private static Map<Byte, AbsShowcaseBsHandler<?>> handlerMap = new HashMap<>();
	static {
		handlerMap.put(Type.GROUP_MSG_RESP, new GroupMsgRespHandler());
		handlerMap.put(Type.JOIN_GROUP_RESP, new JoinGroupRespHandler());
		handlerMap.put(Type.LOGIN_RESP, new LoginRespHandler());
		handlerMap.put(Type.P2P_RESP, new P2PRespHandler());
	}

	private static ShowcasePacket heartbeatPacket = new ShowcasePacket(Type.HEART_BEAT_REQ, null);

	/**
	 * 处理消息
	 */
	@Override
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		ShowcasePacket showcasePacket = (ShowcasePacket) packet;
		Byte type = showcasePacket.getType();
		AbsShowcaseBsHandler<?> showcaseBsHandler = handlerMap.get(type);
		showcaseBsHandler.handler(showcasePacket, channelContext);
		return;
	}

	/**
	 * 此方法如果返回null，框架层面则不会发心跳；如果返回非null，框架层面会定时发本方法返回的消息包
	 */
	@Override
	public ShowcasePacket heartbeatPacket() {
		return heartbeatPacket;
	}
}
