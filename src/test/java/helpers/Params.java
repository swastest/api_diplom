package helpers;

import config.ConfigCenter;
import org.junit.jupiter.params.provider.Arguments;
import preRequests.PreRequestGetTokens;

import java.util.stream.Stream;

public class Params {
    static Stream<Arguments> loginPasswordParamsAdminManagerTech() {
        return Stream.of
                (Arguments.of(ConfigCenter.configAdm.passwordAdmin(), ConfigCenter.configAdm.emailAdmin(), "Админ"),
                        Arguments.of(ConfigCenter.configMng.passwordManager(), ConfigCenter.configMng.emailManager(), "Менеджер"),
                        Arguments.of(ConfigCenter.configTech.passwordTech(), ConfigCenter.configTech.emailTech(), "Техник")
                );
    }

    static Stream<Arguments> preRequestParamTokenAdminManagerAndUserId() {
        return Stream.of
                (Arguments.of( PreRequestGetTokens.getTokenAdmin(), ConfigCenter.configAdm.idAdminUser(), "Админ"),
                        Arguments.of( PreRequestGetTokens.getTokenManager(), ConfigCenter.configMng.idManagerUser(), "Менеджер")
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
