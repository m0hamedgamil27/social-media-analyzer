package mo;
public class App 
{
    public static void main( String[] args )
    {
        String accessToken = "EAAIXaqZA8lDIBOZBZClCK0Umrc7rsFAQq9w7ZCfZAhII2ZADV6ZCaFb05yLRZC9bzNLlbrODewc3eC76DbJMxWWNACOg5rf6kaHBAFsEEExHqemEau27AygRlnQ0vkqVSl1ZCVG8TEAZByFCglEeUTyx3BMhZBU7NQ6FJ2qwiQVPxNiwxckRjHOTuuUr9hdGJHVWOjb2UGC8ZBsett9rnPuN1dIRbIzILZA4BKKDDp4YOmTz4CU7357vUw6v0";
        String userId = "1602960493917937";

        SocialMediaPlatform facebook = new FacebookPlatform(accessToken);

        System.out.println("Fetching User Details:");
        facebook.fetchUserDetails(userId);

        System.out.println("\nFetching User Posts:");
        facebook.fetchPosts(userId);
        
    }
}
