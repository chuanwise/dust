package cn.chuanwise.dust.network.http;

import com.google.common.base.Preconditions;

import java.net.InetSocketAddress;

public class HttpNetworkConfigurationImpl
    implements HttpNetworkConfiguration {
    
    private final InetSocketAddress serverAddress;
    private final String accessToken;
    private final boolean eventEnabled;
    private final int eventBufferSize;
    
    public HttpNetworkConfigurationImpl(InetSocketAddress serverAddress, String accessToken, boolean eventEnabled, int eventBufferSize) {
        Preconditions.checkNotNull(serverAddress, "Server address is null!");
        Preconditions.checkArgument(eventBufferSize >= 0, "Event buffer size must be greater than or equals to 0!");
      
        this.serverAddress = serverAddress;
        this.accessToken = accessToken;
        this.eventEnabled = eventEnabled;
        this.eventBufferSize = eventBufferSize;
    }
    
    @Override
    public InetSocketAddress getServerAddress() {
        return serverAddress;
    }
    
    @Override
    public boolean isEventEnabled() {
        return eventEnabled;
    }
    
    @Override
    public int getEventBufferSize() {
        return eventBufferSize;
    }
    
    @Override
    public String getAccessToken() {
        return accessToken;
    }
}
