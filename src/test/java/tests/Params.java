package tests;

import config.AdminPropInterface;
import config.ManagerPropInterface;
import config.TechPropInterface;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.params.provider.Arguments;
import preRequests.PreRequestGetTokens;

import java.util.stream.Stream;

public class Params {
    static AdminPropInterface configAdm = ConfigFactory.create(AdminPropInterface.class);
    static TechPropInterface configTech = ConfigFactory.create(TechPropInterface.class);
    static ManagerPropInterface confMng = ConfigFactory.create(ManagerPropInterface.class);

    static Stream<Arguments> LoginPasswordParamsAdminManagerTech() {
        return Stream.of
                (Arguments.of(configAdm.passwordAdmin(), configAdm.emailAdmin(), "Админ"),
                        Arguments.of(confMng.passwordManager(), confMng.emailManager(), "Менеджер"),
                        Arguments.of(configTech.passwordTech(), configTech.emailTech(), "Техник")
                );
    }

    static Stream<Arguments> preRequestParamTokenAdminManagerAndUserId() {
        return Stream.of
                (Arguments.of( PreRequestGetTokens.getTokenAdmin(), configAdm.idAdminUser(), "Админ"),
                        Arguments.of( PreRequestGetTokens.getTokenManager(), confMng.idManagerUser(), "Менеджер")
                );
    }

    static Stream<Arguments> preRequestParamTokenAdminManagerTech() {
        return Stream.of
                (Arguments.of(PreRequestGetTokens.getTokenAdmin(), "Админ"),
                        Arguments.of(PreRequestGetTokens.getTokenManager(), "Менеджер"),
                        Arguments.of(PreRequestGetTokens.getTokenTech(), "Техник")
                );
    }
}
