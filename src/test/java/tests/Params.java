package tests;

import config.AdminPropInterface;
import config.ManagerPropInterface;
import config.TechPropInterface;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Params {
    static AdminPropInterface configAdm = ConfigFactory.create(AdminPropInterface.class);
    static TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class);
    static ManagerPropInterface confMng = ConfigFactory.create(ManagerPropInterface.class);

    static Stream<Arguments> LoginPasswordParamsAdminManagerTech(){
        return Stream.of
                (Arguments.of(configAdm.passwordAdmin(), configAdm.emailAdmin(), "Админ"),
                        Arguments.of(confMng.passwordManager(),confMng.emailManager(), "Менеджер"),
                        Arguments.of(configTech.passwordTech(), configTech.emailTech(), "Техник")
                );
    }
}
