package stylepatrick.api.core.recommendation;

public class Recommendation {

    private int productId;
    private int recommendationId;
    private String author;
    private int rate;
    private String content;
    private String serviceAddress;

    public Recommendation(int productId, int recommendationId,  String author, String content, int rate, String serviceAddress) {
        this.productId = productId;
        this.recommendationId = recommendationId;
        this.content = content;
        this.rate = rate;
        this.author = author;
        this.serviceAddress = serviceAddress;
    }

    public Recommendation() {
    }

    public int getProductId() {
        return productId;
    }

    public int getRecommendationId() {
        return recommendationId;
    }

    public String getAuthor() {
        return author;
    }

    public int getRate() {
        return rate;
    }

    public String getContent() {
        return content;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

}
