package cn.goldlone.car.model;

/**
 * 长短链接映射
 * @author Created by CN on 2018/6/26 9:41 .
 */
public class ShortUrlMapping {
    // 原始链接
    private String originUrl;
    // 短链接
    private String shortUrl;

    public ShortUrlMapping() {
    }

    public ShortUrlMapping(String originUrl, String shortUrl) {
        this.originUrl = originUrl;
        this.shortUrl = shortUrl;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
