package com.example.exwar.proxy;

import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TestProfile {

    private static final Set<String> activeProfiles = new LinkedHashSet();
    private static final MutablePropertySources propertySources = new MutablePropertySources();
    private static final ConfigurablePropertyResolver propertyResolver = new PropertySourcesPropertyResolver(propertySources);

    public static void main(String[] args) {
        Set<String> strings = doGetActiveProfiles();
        System.out.println("strings = " + strings);
    }


    protected static Set<String> doGetActiveProfiles() {
        synchronized(activeProfiles) {
            if (activeProfiles.isEmpty()) {
                String profiles = getProperty("spring.profiles.active");
                if (StringUtils.hasText(profiles)) {
                    setActiveProfiles(StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(profiles)));
                }
            }

            return activeProfiles;
        }
    }

    public static void setActiveProfiles(String... profiles) {
        Assert.notNull(profiles, "Profile array must not be null");


        synchronized(activeProfiles) {
            activeProfiles.clear();
            String[] var3 = profiles;
            int var4 = profiles.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String profile = var3[var5];
                validateProfile(profile);
                activeProfiles.add(profile);
            }

        }
    }

    public static String getProperty(String key) {
        return propertyResolver.getProperty(key);
    }

    protected static void validateProfile(String profile) {
        if (!StringUtils.hasText(profile)) {
            throw new IllegalArgumentException("Invalid profile [" + profile + "]: must contain text");
        } else if (profile.charAt(0) == '!') {
            throw new IllegalArgumentException("Invalid profile [" + profile + "]: must not begin with ! operator");
        }
    }
}
