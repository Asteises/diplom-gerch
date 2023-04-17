package ru.picker.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.picker.Bot;
import ru.picker.core.service.ChapterService;
import ru.picker.core.service.CustomerService;
import ru.picker.core.service.SubChapterService;
import ru.picker.core.service.TaskService;

@Configuration
public class TelegramBotConfiguration {

    @Bean
    public Bot telegramBot(
        @Value("${bot.name}") String name,
        @Value("${bot.token}") String token,
        CustomerService customerService,
        ChapterService chapterService,
        SubChapterService subChapterService,
        TaskService taskService) {
        return new Bot(token, name, customerService, chapterService, subChapterService, taskService);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(Bot bot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return telegramBotsApi;
    }

}
