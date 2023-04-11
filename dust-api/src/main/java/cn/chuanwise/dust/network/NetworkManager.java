package cn.chuanwise.dust.network;

/**
 * <h1>网络管理器</h1>
 *
 * @author Chuanwise
 */
public interface NetworkManager {
    
    /**
     * 获取网络配置
     *
     * @return 网络配置
     */
    NetworkConfiguration getConfiguration();
}
