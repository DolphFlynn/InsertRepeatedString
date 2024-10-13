package burp;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class InsertRepeatedStringExtension implements BurpExtension {
    public static final String NAME = "Insert Repeated String";

    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName(NAME);

        api.userInterface().registerContextMenuItemsProvider(
                new InsertRepeatedStringContextMenuItemsProvider(api.userInterface().swingUtils())
        );
    }
}
