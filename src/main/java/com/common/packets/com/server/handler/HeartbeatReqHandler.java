package com.common.packets.com.server.handler;

import com.common.packets.com.common.ShowcasePacket;
import com.common.packets.com.common.intf.AbsShowcaseBsHandler;
import com.common.packets.com.common.packets.GroupMsgReqBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

/**
 * 心跳处理
 * @author tanyaowu
 * 2017年3月27日 下午9:51:28
 */
public class HeartbeatReqHandler extends AbsShowcaseBsHandler<GroupMsgReqBody> {
	private static Logger log = LoggerFactory.getLogger(HeartbeatReqHandler.class);

	/**
	 * @param args
	 * @author tanyaowu
	 */
	public static void main(String[] args) {

	}

	/**
	 *
	 * @author tanyaowu
	 */
	public HeartbeatReqHandler() {
	}

	/**
	 * @return
	 * @author tanyaowu
	 */
	@Override
	public Class<GroupMsgReqBody> bodyClass() {
		return GroupMsgReqBody.class;
	}

	/**
	 * @param packet
	 * @param bsBody
	 * @param channelContext
	 * @return
	 * @throws Exception
	 * @author tanyaowu
	 */
	@Override
	public Object handler(ShowcasePacket packet, GroupMsgReqBody bsBody, ChannelContext channelContext) throws Exception {
		//心跳消息,啥也不用做
		return null;
	}
}
