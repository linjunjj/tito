package com.client.handler;

import com.common.ShowcasePacket;
import com.common.intf.AbsShowcaseBsHandler;
import com.common.packets.GroupMsgRespBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

import org.tio.utils.json.Json;

/**
 * @author tanyaowu
 * 2017年3月27日 下午9:51:28
 */
public class GroupMsgRespHandler extends AbsShowcaseBsHandler<GroupMsgRespBody> {
	private static Logger log = LoggerFactory.getLogger(GroupMsgRespHandler.class);

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
	public GroupMsgRespHandler() {
	}

	/**
	 * @return
	 * @author tanyaowu
	 */
	@Override
	public Class<GroupMsgRespBody> bodyClass() {
		return GroupMsgRespBody.class;
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
	public Object handler(ShowcasePacket packet, GroupMsgRespBody bsBody, ChannelContext channelContext) throws Exception {
		System.out.println("收到群组消息:" + Json.toJson(bsBody));
		return null;
	}
}
