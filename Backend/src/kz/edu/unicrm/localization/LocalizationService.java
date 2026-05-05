package kz.edu.unicrm.localization;
import kz.edu.unicrm.domain.Language;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
public class LocalizationService {
    private static LocalizationService instance;
    private final Map<Language, MessageBundle> bundles = new EnumMap<>(Language.class);
    private Language currentLanguage = Language.RU;
    private LocalizationService() {
        register(new RussianMessageBundle());
        register(new EnglishMessageBundle());
        register(new KazakhMessageBundle());
    }
    public static LocalizationService getInstance() {
        if (instance == null) {
            instance = new LocalizationService();
        }
        return instance;
    }
    public void setLanguage(Language language) {
        if (language == null || language == Language.KZ) {
            currentLanguage = Language.RU;
            return;
        }
        currentLanguage = language;
    }
    public Language getCurrentLanguage() {
        return currentLanguage;
    }
    public String get(LocalizationKey key) {
        return bundles.getOrDefault(currentLanguage, bundles.get(Language.RU)).get(key);
    }
    public String format(LocalizationKey key, Object... args) {
        return String.format(Locale.ROOT, get(key), args);
    }
    private void register(MessageBundle bundle) {
        bundles.put(bundle.language(), bundle);
    }
}
