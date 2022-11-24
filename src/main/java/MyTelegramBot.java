import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class MyTelegramBot extends TelegramLongPollingBot {
    public static final String BOT_TOKKEN = "5721389141:AAE3hEZfKPk5NsfbG-oDnIQF4XDYLH41IM8";
    public static final String BOT_USERNAME = "slava1981_bot";
    public static final String CHAT_ID = "1749655472";
    public static final String URL = "https://api.nasa.gov/planetary/apod?api_key=JSc1DBFdGEGdjI68a3K5jD4BqbaI5qaF1Ix90ZQr";

    public MyTelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            switch (update.getMessage().getText()){
                case "/help":
                    sendMessage("Привет, я бот Nasa. Я высылаю ссылки на картинки" +
                            "Напоминаю, что картинки обнавляются раз в сутки /give");
                    break;
                case "/give":
                    try {
                        sendMessage(Utils.getUrl(URL));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    }

    private void sendMessage(String messageText){
        SendMessage message = new SendMessage();
        message.setChatId(CHAT_ID);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
