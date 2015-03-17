package com.github.lookout.whoas


import io.dropwizard.Configuration;

public interface WhoasConfiguration<T extends Configuration> {
    WhoasFactory getWhoasFactory(T configuration);
}