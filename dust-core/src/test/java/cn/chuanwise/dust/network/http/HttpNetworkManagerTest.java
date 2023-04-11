package cn.chuanwise.dust.network.http;

import cn.chuanwise.dust.network.HttpNetworkManagerImpl;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

public class HttpNetworkManagerTest {
    
    @Test
    public void testBind() throws InterruptedException {
        final HttpNetworkConfigurationImpl configuration = new HttpNetworkConfigurationImpl(new InetSocketAddress(8081), null, true, 0);
        final HttpNetworkManagerImpl networkManager = new HttpNetworkManagerImpl(configuration);
        networkManager.bind();
    }
}
