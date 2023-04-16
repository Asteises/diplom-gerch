package ru.picker;

import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.picker.commands.CommandMap;
import ru.picker.commands.StartCommand;
import ru.picker.core.entity.Chapter;
import ru.picker.core.service.ChapterService;
import ru.picker.core.service.CustomerService;
import ru.picker.core.service.SubChapterService;
import ru.picker.utils.TeleDto;

public class Bot extends TelegramLongPollingCommandBot {

    private final String token;
    private final String name;

    private final ChapterService chapterService;
    private final CommandMap commandMap;

    public Bot(String token,
               String name,
               CustomerService customerService,
               ChapterService chapterService,
               SubChapterService subChapterService) {
        super();
        register(new StartCommand("start", "Start command", customerService));
        this.name = name;
        this.token = token;
        this.chapterService = chapterService;
        commandMap = new CommandMap(chapterService, subChapterService);
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
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();

            System.out.println(callbackQuery.getData());
            message.setChatId(callbackQuery.getMessage().getChatId());
            message.setText("Выберите раздел");
            String[] data = callbackQuery.getData().split(";");
            TeleDto teleDto = commandMap.get(data[1]).execute(data[0]);
            message.setReplyMarkup(getButtons(teleDto.getList(), teleDto.getCallBack()));

            execute(getDeleteMessage(callbackQuery));
            execute(message);
        } else {
            message.setText("Выберите раздел");
            message.setChatId(update.getMessage().getChatId());
            message.setReplyMarkup(
                getButtons(chapterService.getAllChapters().stream().map(Chapter::getName).toList(), "subChapter"));
            execute(message);
        }
    }


    private InlineKeyboardMarkup getButtons(List<String> names, String callBack) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        for (String name : names) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(name);
            inlineKeyboardButton.setCallbackData(name + ";" + callBack);
            buttons.add(List.of(inlineKeyboardButton));
        }
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Назад");
        inlineKeyboardButton.setCallbackData("back;back");
        buttons.add(List.of(inlineKeyboardButton));
        keyboardMarkup.setKeyboard(buttons);
        return keyboardMarkup;
    }

    private DeleteMessage getDeleteMessage(CallbackQuery callbackQuery) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(callbackQuery.getMessage().getChatId());
        deleteMessage.setMessageId(callbackQuery.getMessage().getMessageId());
        return deleteMessage;
    }

}
