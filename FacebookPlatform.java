package mo;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
class FacebookPlatform extends SocialMediaPlatform {

    public FacebookPlatform(String accessToken) {
        super(accessToken);
    }

    public void fetchPosts(String userId) {
        String endpoint = "https://graph.facebook.com/v12.0/" + userId +
            "/posts?fields=message,reactions.summary(true),comments.summary(true)&access_token=" + accessToken;
        String response = sendRequest(endpoint);
        parseAndPrintPosts(response, "User Name Placeholder", "User Email Placeholder");
    }

    public void fetchUserDetails(String userId) {
        String endpoint = "https://graph.facebook.com/v12.0/" + userId +
            "?fields=name,email&access_token=" + accessToken;
        String response = sendRequest(endpoint);
        parseAndPrintUserDetails(response);
    }

    private void parseAndPrintUserDetails(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String name = jsonObject.optString("name", "No name");
        String email = jsonObject.optString("email", "No email");

        System.out.println("User Name: " + name);
        System.out.println("User Email: " + email);
    }

    private String sendRequest(String endpoint) {
        System.out.println("Requesting URL: " + endpoint);
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    private void parseAndPrintPosts(String jsonResponse, String userName, String userEmail) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray posts = jsonObject.optJSONArray("data");
        int totalLikes = 0;
        int totalComments = 0;
        int postCount = 0;
        System.out.println("\nFetching User Posts:");
        if (posts != null && posts.length() > 0) {
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.getJSONObject(i);
                String message = post.optString("message", "No message");
                int postLikes = 0;
                if (post.has("reactions")) {
                    JSONObject reactions = post.getJSONObject("reactions").getJSONObject("summary");
                    postLikes = reactions.getInt("total_count");
                    totalLikes += postLikes;
                }
                int postComments = 0;
                if (post.has("comments")) {
                    JSONObject comments = post.getJSONObject("comments").getJSONObject("summary");
                    postComments = comments.getInt("total_count");
                    totalComments += postComments;
                }
                System.out.println("Post Message: " + message);
                System.out.println("Likes: " + postLikes);
                System.out.println("Comments: " + postComments);
                System.out.println("---------------------------------");
                postCount++;
            }
            System.out.println("Total Posts: " + postCount);
            System.out.println("Total Likes: " + totalLikes);
            System.out.println("Total Comments: " + totalComments);
        } else {
            System.out.println("No posts found or insufficient permissions.");
        }
    }
}