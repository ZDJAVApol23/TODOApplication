package pl.sda.todoapplication.model;

import javax.validation.constraints.*;

public class CreateTodoDto {

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 255)
    private String text;

    private long userId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
