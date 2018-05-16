//package twitterprimenumbot;

/**
 *
 * @author Bhanuka
 */

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Level;
import twitter4j.*;

public class TwitterPrimeNumBot {
    private static PrintStream consolePrint;
    
    public static void main(String[] args) throws TwitterException, IOException {
        //get Twitter API Token at https://apps.twitter.com and update twitter4j.propeties
        Twitterer tw = new Twitterer(consolePrint);  
        tw.replyWithPrime("#CLCPrimeBot");   //tag need to be find: tweet as #CLCPrimeBot number eg: #CLCPrimeBot 23
        //System.out.println("Sucessfully Replied to all tweets");
    }
    
}

class Twitterer{
    private Twitter twitter;
    private PrintStream consolePrint;
    private List<Status> statuses;


    public Twitterer(PrintStream console)
    {
        twitter = TwitterFactory.getSingleton(); 
        consolePrint = console;
    }

    public void replyWithPrime(String tag)
    {
        Query query = new Query(tag);
        query.setCount(100);
        try {
            QueryResult res = twitter.search(query);
            int counter = 0;
            for(Status tweet : res.getTweets()){
                counter++;
                int num = Integer.parseInt(tweet.getText().substring(tweet.getText().indexOf("#CLCPrimeBot")+13));

                try{
                    StatusUpdate statusUpdate = new StatusUpdate(getPrime(num));
                    statusUpdate.inReplyToStatusId(tweet.getId());
                    Status status = twitter.updateStatus(statusUpdate);
                    System.out.println("Replied to count #"+counter+" @"+tweet.getUser().getName()+": "+ tweet.getText());
                } catch(TwitterException e){ }
            }
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(Twitterer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getPrime(int num){
        if(num<2)
            return "No Primes";
        StringBuilder sb = new StringBuilder();
        boolean[] prime = new boolean[num]; 
        prime[0] = true; 
        prime[1] = true; 
        for(int i=2; i<num; i++){ 
            if(prime[i]==false) 
                for(int j=i+1; j<num; j++){ 
                    if(j % i == 0){ 
                        prime[j] = true; 
                    } 
                } 
        } 

        for(int i=2;i<num;i++) 
        if(prime[i]==false){ 
            sb.append(i);
            sb.append(", ");
        }
        sb.replace(sb.length()-2,sb.length()-1, ".");
        return sb.toString();
    }
}  
