package br.cicerosnt.project3.error;

public class ValidatorErrorDetails extends ErrorDetails {
    private String field;
    private String fildMsg;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFildMsg() {
        return fildMsg;
    }

    public void setFildMsg(String fildMsg) {
        this.fildMsg = fildMsg;
    }

    public ValidatorErrorDetails(Builder builder) {
        this.setTitle(builder.title);
        this.setStatus(builder.status);
        this.setDetail(builder.detail);
        this.setDetail(builder.field);
        this.setDetail(builder.fieldMsg);
        this.setDeveloperMessage(builder.developerMessage);
        this.setTimestamp(builder.timestamp);
    }

    public static final class Builder {
        private String title;
        private String status;
        private String detail;
        private String developerMessage;
        private String field;
        private String fieldMsg;
        private long timestamp;

        private Builder(){
        }

        public static Builder newBuilder(){
            return new Builder();
        }

        public Builder setField(String field) {
            this.field = field;
            return this;
        }

        public Builder setFieldMsg(String fieldMsg) {
            this.fieldMsg = fieldMsg;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ValidatorErrorDetails builder() {
            return new ValidatorErrorDetails(this);
        }
    }

}
