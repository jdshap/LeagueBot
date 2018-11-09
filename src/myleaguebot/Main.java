package myleaguebot;

import java.io.IOException;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * 
 * 
 * @author Stefan Cornelius
 */
public class Main 
{
    public static void main(String[] args) throws IOException
    {
        ApiContextInitializer.init();
        
        TelegramBotsApi botsApi = new TelegramBotsApi();
        MyLeagueBot bot = new MyLeagueBot();
        
        System.out.println(bot.getBotUsername() + " initialized with token " + bot.getBotToken());
        
        try 
        {
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) 
        {
            e.printStackTrace();
        }
        
        System.out.println(bot.getBotUsername() + " registered successfully");
    }
}
