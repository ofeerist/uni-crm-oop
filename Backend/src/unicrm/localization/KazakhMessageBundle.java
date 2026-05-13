package unicrm.localization;
import unicrm.domain.Language;
import java.util.EnumMap;
import java.util.Map;
public class KazakhMessageBundle implements MessageBundle {
    private final Map<LocalizationKey, String> messages = new EnumMap<>(LocalizationKey.class);
    public KazakhMessageBundle() {
    }
    @Override
    public Language language() {
        return Language.KZ;
    }
    @Override
    public String get(LocalizationKey key) {
        return messages.getOrDefault(key, key.name());
    }
}
