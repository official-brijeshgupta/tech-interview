package interviews.tech.atlassian.ratelimit;

import lombok.Value;

@Value
public class RateLimiterConfig {
    int maxCallsAllowed;
    int windowSizeInSeconds;
}
