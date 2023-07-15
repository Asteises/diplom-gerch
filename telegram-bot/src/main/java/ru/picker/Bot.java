package ru.picker;

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
import ru.picker.core.entity.Task;
import ru.picker.core.model.ChapterDisplayDto;
import ru.picker.core.service.impl.ChapterServiceImpl;
import ru.picker.core.service.impl.CustomerServiceImpl;
import ru.picker.core.service.impl.SubChapterServiceImpl;
import ru.picker.core.service.impl.TaskServiceImpl;
import ru.picker.utils.TeleDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Bot extends TelegramLongPollingCommandBot {

    private final String token;
    private final String name;

    private final ChapterServiceImpl chapterServiceImpl;
    private final CommandMap commandMap;
    private final TaskServiceImpl taskServiceImpl;

    private final HashMap<Long, String> taskMap = new HashMap<>();

    public Bot(String token,
               String name,
               CustomerServiceImpl customerServiceImpl,
               ChapterServiceImpl chapterServiceImpl,
               SubChapterServiceImpl subChapterServiceImpl,
               TaskServiceImpl taskServiceImpl) {
        super();
        register(new StartCommand("start", "Start command", customerServiceImpl));
        this.name = name;
        this.token = token;
        this.chapterServiceImpl = chapterServiceImpl;
        this.taskServiceImpl = taskServiceImpl;
        commandMap = new CommandMap(chapterServiceImpl, subChapterServiceImpl, taskServiceImpl);
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
            String[] data = callbackQuery.getData().split(";");
            if (!data[1].contains("internalTask")) {
                TeleDto teleDto = commandMap.get(data[1]).execute(data[0]);
                message.setReplyMarkup(getButtons(teleDto.getList(), teleDto.getCallBack()));
                message.setText("Выберите раздел");
            } else {
                //String taskName = data[1].split("-")[1];
                taskMap.put(callbackQuery.getMessage().getChatId(), data[0]);
                Task task = taskServiceImpl.getTaskByName(data[0]);
                message.setText("Вместо многоточия укажите ваши ответы." +
                        " Каждый ответ на новой строке в соответствии с заданием\n\n" + task.getTest());

            }

            execute(getDeleteMessage(callbackQuery));
            execute(message);
        } else if (taskMap.get(update.getMessage().getChatId()) != null) {
            message.setChatId(update.getMessage().getChatId());
            Task task = taskServiceImpl.getTaskByName(taskMap.get(update.getMessage().getChatId()));
            if (checkAnswers(task, update.getMessage().getText())) {
                message.setText("Задание выполнено верно!\uD83E\uDD73");
            } else {
                message.setText("Задание выполнено с ошибками!\uD83D\uDE14");
            }
            message.setReplyMarkup(getButtons(chapterServiceImpl.getAllChapters().stream()
                            .map(ChapterDisplayDto::getName)
                            .collect(Collectors.toList()),
                    "subChapter"));
            taskMap.put(update.getMessage().getChatId(), null);
            execute(message);
        } else {
            message.setText("Выберите раздел");
            message.setChatId(update.getMessage().getChatId());
            message.setReplyMarkup(
                    getButtons(chapterServiceImpl.getAllChapters().stream()
                                    .map(ChapterDisplayDto::getName)
                                    .collect(Collectors.toList()),
                            "subChapter"));
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

    private Boolean checkAnswers(Task task, String answers) {
        String actualAnswers = task.getAnswers();
        if (actualAnswers.equals(answers.toLowerCase(Locale.ROOT))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

}
