package com.common.packets.com.server.handler;

import com.common.packets.com.common.ShowcasePacket;
import com.common.packets.com.common.ShowcaseSessionContext;
import com.common.packets.com.common.Type;
import com.common.packets.com.common.intf.AbsShowcaseBsHandler;
import com.common.packets.com.common.packets.JoinGroupRespBody;
import com.common.packets.com.common.packets.LoginReqBody;
import com.common.packets.com.common.packets.LoginRespBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tanyaowu
 * 2017年3月27日 下午9:51:28
 */
public class LoginReqHandler extends AbsShowcaseBsHandler<LoginReqBody> {
	private static Logger log = LoggerFactory.getLogger(LoginReqHandler.class);

	/**
	 * @param args
	 * @author tanyaowu
	 */
	public static void main(String[] args) {

	}

	AtomicLong tokenSeq = new AtomicLong();

	/**
	 *
	 * @author tanyaowu
	 */
	public LoginReqHandler() {
	}

	/**
	 * @return
	 * @author tanyaowu
	 */
	@Override
	public Class<LoginReqBody> bodyClass() {
		return LoginReqBody.class;
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
	public Object handler(ShowcasePacket packet, LoginReqBody bsBody, ChannelContext channelContext) throws Exception {
		log.info("收到登录请求消息:{}", Json.toJson(bsBody));
		LoginRespBody loginRespBody = new LoginRespBody();
		loginRespBody.setCode(JoinGroupRespBody.Code.SUCCESS);
		loginRespBody.setToken(newToken());

		String userid = bsBody.getLoginname();
		Aio.bindUser(channelContext, userid);

		ShowcaseSessionContext showcaseSessionContext = (ShowcaseSessionContext) channelContext.getAttribute();
		showcaseSessionContext.setUserid(userid);

		ShowcasePacket respPacket = new ShowcasePacket();
		respPacket.setType(Type.LOGIN_RESP);
		respPacket.setBody(Json.toJson(loginRespBody).getBytes(ShowcasePacket.CHARSET));
		Aio.send(channelContext, respPacket);

		return null;
	}

	private String newToken() {
		return System.currentTimeMillis() + "_" + tokenSeq.incrementAndGet();
	}
}
