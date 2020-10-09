package br.cicerosnt.project3.error;

public class CustomErrorType {
    private String errorMsg;

    public CustomErrorType(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
