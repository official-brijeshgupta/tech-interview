package interviews.tech.companies.atlassian.ratelimit;

import lombok.Value;

@Value
public class RateLimiterConfig {
    int maxCallsAllowed;
    int windowSizeInSeconds;
}
