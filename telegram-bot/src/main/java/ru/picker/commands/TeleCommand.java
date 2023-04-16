package ru.picker.commands;

import java.util.List;
import ru.picker.utils.TeleDto;

public interface TeleCommand {

    TeleDto execute(String theme);

}
