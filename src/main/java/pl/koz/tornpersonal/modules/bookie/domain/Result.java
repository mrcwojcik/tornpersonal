package pl.koz.tornpersonal.modules.bookie.domain;

public enum Result {

    WIN (1),
    LOSE (0);

    private final int resultCode;
    private Result(int resultCode){
        this.resultCode = resultCode;
    }

}
