package org.jetlinks.gateway.session;

import org.jetlinks.core.message.DeviceMessageReply;
import org.jetlinks.core.message.Headers;
import org.jetlinks.core.message.codec.Transport;

/**
 * 设备会话管理器,用于管理所有设备连接会话
 *
 * @author zhouhao
 * @since 1.0.0
 */
public interface DeviceSessionManager {

    /**
     * 根据设备ID或者会话ID获取设备会话
     *
     * @param idOrDeviceId 设备ID或者会话ID
     * @return 设备会话, 不存在则返回<code>null</code>
     */
    DeviceSession getSession(String idOrDeviceId);

    /**
     * 注册新到设备会话,如果已经存在相同设备ID到会话,将注销旧的会话.
     *
     * @param session 新的设备会话
     * @return 旧的设备会话, 不存在则返回<code>null</code>
     */
    DeviceSession register(DeviceSession session);

    /**
     * 使用会话ID或者设备ID注销设备会话
     *
     * @param idOrDeviceId 设备ID或者会话ID
     * @return 被注销的会话, 不存在则返回<code>null</code>
     */
    DeviceSession unregister(String idOrDeviceId);

    /**
     * 处理设备消息回复,当有设备上报了消息后,将调用此方法处理同步消息回复。
     *
     * @param session 设备会话
     * @param reply   上报的消息
     * @see Headers#async
     * @see Headers#asyncSupport
     */
    void handleDeviceMessageReply(DeviceSession session, DeviceMessageReply reply);

    /**
     * 指定的协议是否已经超过了最大连接数量
     *
     * @param transport 协议
     * @return 是否超过
     */
    boolean isOutOfMaximumConnectionLimit(Transport transport);

    /**
     * 获取指定协议的最大连接数量
     *
     * @param transport 协议
     * @return 最大连接数量
     */
    long getMaximumConnection(Transport transport);

    /**
     * 获取指定协议的当前连接数量
     *
     * @param transport 协议
     * @return 当前连接数量
     */
    long getCurrentConnection(Transport transport);

}
