package cn.chuanwise.dust.api;

import cn.chuanwise.dust.annotation.DustInternalAPI;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

/**
 * <h1>Dust API 工厂</h1>
 *
 * @author Chuanwise
 */
@DustInternalAPI
public class APIFactory {
    private APIFactory() {
        throw new NoSuchElementException("No " + APIFactory.class.getName() + " instances for you!");
    }
    
    private static volatile API api;
    
    /**
     * 获取 Dust API 实例
     *
     * @return Dust API 实例
     */
    public static API getInstance() {
        if (api == null) {
            synchronized (APIFactory.class) {
                if (api == null) {
                    final ServiceLoader<API> serviceLoader = ServiceLoader.load(API.class);
                    final Iterator<API> iterator = serviceLoader.iterator();
                    if (iterator.hasNext()) {
                        APIFactory.api = iterator.next();
                    } else {
                        throw new NoSuchElementException("No dust core present!");
                    }
                }
            }
        }
        return api;
    }
}
