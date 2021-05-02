package cf.beishan.microposts.common;

public enum ServiceResultEnum {

    ERROR("error"),

    SUCCESS("success"),

    LOGIN_NAME_NULL("登錄名爲空！"),

    LOGIN_EMAIL_NULL("郵箱爲空！"),

    LOGIN_PASSWORD_NULL("密碼爲空！"),

    SAME_LOGIN_EMAIL_EXITS("郵箱已存在！"),

    LOGIN_ERROR("登錄失敗！"),

    SAME_LOGIN_NAME_EXITS("用戶名已存在！"),

    ADD_MP_ERROR("添加博文失败");

    private String result;

    ServiceResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
