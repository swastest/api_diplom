package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigCenter {
    static public AdminPropInterface configAdm = ConfigFactory.create(AdminPropInterface.class,System.getProperties());
    static public ClientPropInterface configClient = ConfigFactory.create(ClientPropInterface.class,System.getProperties());
    static public LinkPropInterface configLink = ConfigFactory.create(LinkPropInterface.class,System.getProperties());
    static public ManagerPropInterface configMng = ConfigFactory.create(ManagerPropInterface.class,System.getProperties());
    static public TeamPropInterface configTeam = ConfigFactory.create(TeamPropInterface.class, System.getProperties());
    static public TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class,System.getProperties());
}
