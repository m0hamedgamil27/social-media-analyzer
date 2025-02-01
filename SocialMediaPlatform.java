package mo;
abstract  public class SocialMediaPlatform {
    protected String accessToken;
    public SocialMediaPlatform(String accessToken) {
        this.accessToken = accessToken;
    }
    public abstract void fetchUserDetails(String userId);
    public abstract void fetchPosts(String userId);
}

