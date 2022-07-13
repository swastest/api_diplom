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
        PreRequestGetTokens token = new PreRequestGetTokens();
        return Stream.of
                (Arguments.of(token.getTokenAdmin(), ConfigCenter.configAdm.idAdminUser(), "Админ"),
                        Arguments.of(token.getTokenManager(), ConfigCenter.configMng.idManagerUser(), "Менеджер")
                );
    }

    static Stream<Arguments> preRequestParamTokenAdminManagerTech() {
        PreRequestGetTokens token = new PreRequestGetTokens();
        return Stream.of
                (Arguments.of(token.getTokenAdmin(), "Админ"),
                        Arguments.of(token.getTokenManager(), "Менеджер"),
                        Arguments.of(token.getTokenTech(), "Техник")
                );
    }
}
