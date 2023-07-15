package ru.picker.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.picker.Bot;
import ru.picker.core.service.impl.ChapterServiceImpl;
import ru.picker.core.service.impl.CustomerServiceImpl;
import ru.picker.core.service.impl.SubChapterServiceImpl;
import ru.picker.core.service.impl.TaskServiceImpl;

@Configuration
public class TelegramBotConfiguration {

    @Bean
    public Bot telegramBot(
        @Value("${bot.name}") String name,
        @Value("${bot.token}") String token,
        CustomerServiceImpl customerServiceImpl,
        ChapterServiceImpl chapterServiceImpl,
        SubChapterServiceImpl subChapterServiceImpl,
        TaskServiceImpl taskServiceImpl) {
        return new Bot(token, name, customerServiceImpl, chapterServiceImpl, subChapterServiceImpl, taskServiceImpl);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(Bot bot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return telegramBotsApi;
    }

}
