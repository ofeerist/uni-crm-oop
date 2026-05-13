package unicrm.localization;
import unicrm.domain.Language;
public interface MessageBundle {
    Language language();
    String get(LocalizationKey key);
}
