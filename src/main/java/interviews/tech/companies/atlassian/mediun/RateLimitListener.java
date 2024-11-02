package interviews.tech.companies.atlassian.mediun;

/**
 *
 * Interface to be implemented by the application to receive
 * notifications whenever the rate limit is breached.
 * Future behavior can be added to this interface.
 *
 */
public interface RateLimitListener {
    void rateLimitThresholdBreached();
}
