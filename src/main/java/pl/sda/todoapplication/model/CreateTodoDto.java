package pl.sda.todoapplication.model;

import javax.validation.constraints.*;

public class CreateTodoDto {

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 255)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
