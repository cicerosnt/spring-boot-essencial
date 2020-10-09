package br.cicerosnt.project3.error;

public class ResourceNotFoundTatails extends ErrorDetails {




    public ResourceNotFoundTatails(Builder builder) {
        this.setTitle(builder.title);
        this.setStatus(builder.status);
        this.setDetail(builder.detail);
        this.setDeveloperMessage(builder.developerMessage);
        this.setTimestamp(builder.timestamp);
    }

    public static final class Builder {
        private String title;
        private String status;
        private String detail;
        private String developerMessage;
        private long timestamp;

        private Builder(){
        }

        public static Builder newBuilder(){
            return new Builder();
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

        public ResourceNotFoundTatails builder() {
            return new ResourceNotFoundTatails(this);
        }
    }
}
