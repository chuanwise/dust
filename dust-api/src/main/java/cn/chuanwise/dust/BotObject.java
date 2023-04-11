package cn.chuanwise.dust;

/**
 * <h1>机器人对象</h1>
 *
 * <p>机器人对象是隶属于一个机器人的对象</p>
 *
 * @author Chuanwise
 */
public interface BotObject {
    
    /**
     * 获取对象所属的机器人
     *
     * @return 对象所属的机器人
     */
    Bot getBot();
}
