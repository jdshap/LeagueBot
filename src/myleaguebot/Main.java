package myleaguebot;

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
    public static void main(String[] args) 
    {
        ApiContextInitializer.init();
        
        TelegramBotsApi botsApi = new TelegramBotsApi();
        
        try 
        {
            botsApi.registerBot(new MyLeagueBot());
        } catch (TelegramApiException e) 
        {
            e.printStackTrace();
        }
    }
}
