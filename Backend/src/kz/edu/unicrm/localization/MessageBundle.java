package kz.edu.unicrm.localization;
import kz.edu.unicrm.domain.Language;
public interface MessageBundle {
    Language language();
    String get(LocalizationKey key);
}
