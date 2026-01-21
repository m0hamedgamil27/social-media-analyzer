package mo;
public class App 
{
    public static void main( String[] args )
    {
        String accessToken = "";
        String userId = "";

        SocialMediaPlatform facebook = new FacebookPlatform(accessToken);

        System.out.println("Fetching User Details:");
        facebook.fetchUserDetails(userId);

        System.out.println("\nFetching User Posts:");
        facebook.fetchPosts(userId);
        
    }
}

