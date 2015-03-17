package com.github.lookout.whoas


import io.dropwizard.Configuration

/**
 * The interface to create the whoas factory based on configuration
 * @param < T > Configuration class that extends dropwizard configuration
 */
public interface WhoasConfiguration<T extends Configuration> {
    WhoasFactory getWhoasFactory(T configuration);
}