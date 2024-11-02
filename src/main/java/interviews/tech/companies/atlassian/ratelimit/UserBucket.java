package interviews.tech.companies.atlassian.ratelimit;

public class UserBucket extends GenericBucket<Long> {

    public UserBucket(Long key, RateLimiterConfig config) {
        super(key, config);
    }
}
