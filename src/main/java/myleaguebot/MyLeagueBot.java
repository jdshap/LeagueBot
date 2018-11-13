package myleaguebot;

import java.io.IOException;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * 
 * 
 * @author Stefan Cornelius, Joseph Shapiro
 */
public class MyLeagueBot extends TelegramLongPollingBot
{
    @Override
    public void onUpdateReceived(Update update)
    {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) 
        {
            // Set variables
            long chat_id = update.getMessage().getChatId();
            String messageUpdate = update.getMessage().getText().toLowerCase();
            
            if(messageUpdate.contains("hey angryleaguebot"))
            {
                sendMessage("Fuck off.", chat_id);
            }
            else if(messageUpdate.contains("runes"))
            {
                sendMessage(getRunePhrase(), chat_id);
            }
            else if(messageUpdate.contains("tilt"))
            {
                sendMessage(getTiltPhrase(), chat_id);
            }
            else if(messageUpdate.contains("remove"))
            {
                sendMessage("Yeah, you're wrong. IMHO, " + getRandomChamp() + " should be removed from the game. "
                        + "They aren't fun to play against and therefore all who main them can suck it.", chat_id);
            }
            else if((messageUpdate.contains("league") && update.getMessage().getText().contains("?"))
                  || (messageUpdate.contains("play") && (update.getMessage().getText().contains("?")
                    || messageUpdate.contains("want") || messageUpdate.contains("wanna"))))
            {
                sendMessage(getPlayPhrase(), chat_id);
            }
            else if((messageUpdate.contains("rotation") || messageUpdate.contains("free")) && (messageUpdate.contains("who") || messageUpdate.contains("which"))) {
            	Document freeRotation = null;
				try {
					freeRotation = Jsoup.connect("http://leagueoflegends.wikia.com/wiki/Free_champion_rotation#Classic").get();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
            	String champions = "";
            	
            	for(int i = 0; i < 13; i++)
            	{
            	champions += freeRotation.body().select(".free_champion_rotation li").get(i).child(0).attr("data-champion");
            	champions += ", ";
            	}
            	champions += " and " + freeRotation.body().select(".free_champion_rotation li").get(13).child(0).attr("data-champion");
            	
            	sendMessage("The current free champions are " + champions + ".", chat_id);
            }
            
        }
    }

    @Override
    public String getBotUsername() 
    {
        // TODO
        return "AngryLeagueBot";
    }

    @Override
    public String getBotToken() 
    {
        // TODO
        return "451123861:AAFrBnHq2NHzKdD7lrt4ZS11PeVD3rpuBQE";
    }
    
    public void sendMessage(String message_text, long chat_id)
    {
        SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(message_text);
                try 
                {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) 
                {
                    e.printStackTrace();
                }
                System.out.println("Sent a message");
    }
    
    public String getRunePhrase()
    {
        String[] phrase = {"New runes are overpowered. I can't wait "
                        + "for Riot to revert to how they were before.", "Yo, runes? "
                + "I've got to show you my Klepto Gangplank. He's fucking out of this world! I'm full build in like 5 minutes!!"};
        
        String randPhrase = null;
        Random rand = new Random();
        randPhrase = phrase[rand.nextInt(2)];
        
        return randPhrase;
    }
    
    public String getPlayPhrase()
    {
        String[] phrase = {"OH SHIT BOYS I'LL BE RIGHT ON!", "Nah, miss me with that league shit.", "I've been "
                + "waiting for you to ask! Count me in!", "League? Again? Do you even have a life? Do something productive for once."};
        
        String randPhrase = null;
        Random rand = new Random();
        randPhrase = phrase[rand.nextInt(4)];
        
        return randPhrase;
    }
    
    public String getTiltPhrase()
    {
        String[] phrase = {"You don't know tilted until you've fought " + getRandomChamp() + " as "
                        + getRandomChamp() + " in " + getRandomPos() + " without " + getRandomItem() + ".", "I was so "
                + "fucking tilted the other night. This asshat " + getRandomChamp() + " hard countered me on " + 
                getRandomChamp() + " in " + getRandomPos() + " and I had to build " + getRandomItem() + " just to stop from feeding."};
        
        String randPhrase = null;
        Random rand = new Random();
        randPhrase = phrase[rand.nextInt(2)];
        
        return randPhrase;
    }
    
    public String getChatId()
    {
        long chat_id = -206255098;
        
        return Long.toString(chat_id);
    }
    
    public String getRandomChamp()
    {
        String[] champs = {"Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie", "Ashe", "Aurelion Sol", "Azir",
                           "Bard", "Blitzcrank", "Brand", "Braum", "Caitlyn", "Camille", "Cassiopeia", "Cho'Gath", "Corki",
                           "Darius", "Diana", "Dr. Mundo", "Draven", "Ekko", "Elise", "Evelynn", "Ezreal", "Fiddlesticks",
                           "Fiora", "Fizz", "Galio", "Gangplank", "Garen", "Gnar", "Gragas", "Graves", "Hecarim", "Heimerdinger",
                           "Illaoi", "Irelia", "Ivern", "Janna", "Jarvan IV", "Jax", "Jayce", "Jhin", "Jinx", "Kai'Sa", "Kalista", "Karma",
                           "Karthus", "Kassadin", "Katarina", "Kayle", "Kayn", "Kennen", "Kha'Zix", "Kindred", "Kled", "Kog'Maw",
                           "LeBlanc", "Lee Sin", "Leona", "Lissandra", "Lucian", "Lulu", "Lux", "Malphite", "Malzahar", "Maokai",
                           "Master Yi", "Miss Fortune", "Mordekaiser", "Morgana", "Nami", "Nasus", "Nautilus", "Nidalee", 
                           "Nocturne", "Nunu", "Olaf", "Orianna", "Ornn", "Pantheon", "Poppy", "Pyke", "Quinn", "Rakan", "Rammus",
                           "Rek'Sai", "Renekton", "Rengar", "Riven", "Rumble", "Ryze", "Sejuani", "Shaco", "Shen", "Shyvana",
                           "Singed", "Sion", "Sivir", "Skarner", "Sona", "Soraka", "Swain", "Syndra", "Tahm Kench", "Taliyah",
                           "Talon", "Taric", "Teemo", "Thresh", "Tristana", "Trundle", "Tryndamere", "Twisted Fate", "Twitch",
                           "Udyr", "Urgot", "Varus", "Vayne", "Veigar", "Vel'Koz", "Vi", "Viktor", "Vladimir", "Volibear",
                           "Warwick", "Wukong", "Xayah", "Xerath", "Xin Zhao", "Yasuo", "Yorick", "Zac", "Zed", "Ziggs", "Zilean",
                           "Zoe", "Zyra"};
        String randChamp = null;

        Random rand = new Random();
        randChamp = champs[rand.nextInt(141)];
        
        return randChamp;
    }
    
    public String getRandomPos()
    {
        String[] positions = {"Top", "Jungle", "Mid", "ADC", "Support"};
        String randPos = null;
        
        Random rand = new Random();
        randPos = positions[rand.nextInt(5)];
        return randPos;
    }
    
    public String getRandomItem()
    {
        String[] items = {"Abyssal Mask", "Adaptive Helm", "Archangel's Staff", "Ardent Censer", "Athene's Unholy Grail", 
                          "Banner of Command", "Banshee's Veil", "Blade of the Ruined King", "Dead Man's Plate", "Death's Dance",
                          "Duskblade of Draktharr", "Edge of Night", "Essence Reaver", "Eye of the Equinox", "Eye of the Oasis",
                          "Eye of the Watchers", "Face of the Mountain", "Frost Queen's Claim", "Frozen Heart", "Frozen Mallet",
                          "Gargoyle Stoneplate", "Guardian Angel", "Guinsoo's Rageblade", "Hextech GLP-800", "Hextech Gunblade",
                          "Hextech Protobelt-01", "Iceborn Gauntlet", "Infinity Edge", "Knight's Vow", "Liandry's Torment",
                          "Lich Bane", "Locket of the Iron Solari", "Lord Dominik's Regards", "Luden's Echo", "Manamune",
                          "Maw of Malmortius", "Mejai's Soulstealer", "Mercurial Scimitar", "Mikael's Crucible", 
                          "Moonflair Spellblade", "Morellonomicon", "Mortal Reminder", "Nashor's Tooth", "Ohmwrecker",
                          "Phantom Dancer", "Rabadon's Deathcap", "Randuin's Omen", "Rapid Firecannon", "Ravenous Hydra",
                          "Redemption", "Righteous Glory", "Rod of Ages", "Ruby Sightstone", "Runaan's Hurricane", 
                          "Rylai's Crystal Scepter", "Spirit Visage", "Statikk Shiv", "Sterak's Gage", "Sunfire Cape",
                          "Talisman of Ascension", "The Black Cleaver", "The Bloodthirster", "Thornmail", "Titanic Hydra",
                          "Trinity Force", "Void Staff", "Warmog's Armor", "Wit's End", "Wooglet's Witchcap", 
                          "Youmuu's Ghostblade", "Zeke's Convergence", "Zhonya's Hourglass", "Zz'Rot Portal"};
        String randItem = null;
        
        Random rand = new Random();
        randItem = items[rand.nextInt(73)];
        return randItem;
    }
}
