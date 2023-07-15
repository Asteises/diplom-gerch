package ru.picker.commands;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.UUID;
import ru.picker.core.entity.Customer;
import ru.picker.core.service.impl.CustomerServiceImpl;

@Slf4j
public class StartCommand extends ServiceCommand {

    private final CustomerServiceImpl customerServiceImpl;

    public StartCommand(String commandIdentifier, String description, CustomerServiceImpl customerServiceImpl) {
        super(commandIdentifier, description);
        this.customerServiceImpl = customerServiceImpl;
    }

    @SneakyThrows
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(false);
        message.setChatId(String.valueOf(chat.getId()));
        message.setText("Добро пожаловать в Language Picker! Выберите тему для изучения");
        registerCustomer(user, chat);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            log.error("Cannot send message with such parameters chatId: {}, userName: {}",
                chat.getId(), chat.getUserName());
            throw new TelegramApiException(e);
        }

    }

    private void registerCustomer(User user, Chat chat) {
        if (customerServiceImpl.findById(chat.getId()) == null) {
            Customer customer = new Customer();
            customer.setChatId(chat.getId());
            customer.setName(user.getUserName());
            customer.setId(UUID.randomUUID());
            customerServiceImpl.save(customer);
        }
    }

}
