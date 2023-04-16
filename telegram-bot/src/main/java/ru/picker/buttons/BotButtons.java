package ru.picker.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class BotButtons {

    private static ReplyKeyboardMarkup replyKeyboardMarkupMain;

    static {
        replyKeyboardMarkupMain = new ReplyKeyboardMarkup();
        replyKeyboardMarkupMain.setSelective(true);
        replyKeyboardMarkupMain.setResizeKeyboard(true);
        replyKeyboardMarkupMain.setOneTimeKeyboard(false);

    }

    public static ReplyKeyboardMarkup getRoleButtons() {
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Бармен");
        firstRow.add("Заказывающий");
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.add(firstRow);
        replyKeyboardMarkupMain.setKeyboard(keyboardRowList);
        return replyKeyboardMarkupMain;
    }

}
