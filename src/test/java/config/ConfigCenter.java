package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigCenter {
    public static AdminPropInterface configAdm = ConfigFactory.create(AdminPropInterface.class,System.getProperties());
    public static ClientPropInterface configClient = ConfigFactory.create(ClientPropInterface.class,System.getProperties());
    public static LinkPropInterface configLink = ConfigFactory.create(LinkPropInterface.class,System.getProperties());
    public static ManagerPropInterface configMng = ConfigFactory.create(ManagerPropInterface.class,System.getProperties());
    public static TeamPropInterface configTeam = ConfigFactory.create(TeamPropInterface.class, System.getProperties());
    public static TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class,System.getProperties());
}
