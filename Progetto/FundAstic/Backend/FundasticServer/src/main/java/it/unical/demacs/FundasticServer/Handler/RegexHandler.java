package it.unical.demacs.FundasticServer.Handler;

public class RegexHandler {
    private static final RegexHandler instance = new RegexHandler();
    public static RegexHandler getInstance() {
        return instance;
    }
    private RegexHandler() {}

    public final String regexEmail =
            "^[a-zA-Z0-9.!#$%&’*+/=?^_{|}~-]+@(?:gmail\\.com|yahoo\\.com|hotmail\\.com|libero\\.it|icloud\\.com|gmx\\.com|aol\\.com)";
    public final String regexAmount = "\\d+";

    public final String regexPassword =
            "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@!%~&£°#'?*=.])[a-zA-Z0-9@!%&£°#'?*=.]{8,}";

    public final String regexFirstLast =
            "^(?=.{2,20}$)[a-zA-Z]+(?:[-'\\s][a-zA-Z]+)*$";
}
