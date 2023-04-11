package cn.chuanwise.dust.network.http;

import cn.chuanwise.dust.network.NetworkConfiguration;

import java.net.InetSocketAddress;

/**
 * <h1>Http 网络配置</h1>
 *
 * @author Chuanwise
 */
public interface HttpNetworkConfiguration
    extends NetworkConfiguration {
    
    /**
     * 获取服务器地址
     *
     * @return 服务器地址
     */
    InetSocketAddress getServerAddress();
    
    /**
     * 是否启用 {@code get_latest_events} 元动作
     *
     * @return 是否启用 {@code get_latest_events} 元动作
     */
    boolean isEventEnabled();
    
    /**
     * 获取访问 Token
     *
     * @return 访问 Token
     */
    String getAccessToken();
    
    /**
     * 获取事件缓冲区大小
     *
     * @return 事件缓冲区大小，或 0
     */
    int getEventBufferSize();
}
