package ru.cocktails;

import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingCommandBot {

    private final String token;
    private final String name;



    public Bot(String token,
               String name) {
        super();
        this.name = name;
        this.token = token;

    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @SneakyThrows
    @Override
    public void processNonCommandUpdate(Update update) {
        SendMessage message = new SendMessage();
        System.out.println(update);
        message.setChatId(update.getMessage().getChatId());
        message.setText("@standaloneee");
        execute(message);
    }



}
