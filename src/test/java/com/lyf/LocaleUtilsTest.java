package com.lyf;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class LocaleUtilsTest {
    private LocaleUtils localeUtils;

    @Before
    public void setUp() throws Exception {
        localeUtils = new LocaleUtils();
    }

    @Test
    public void should_get_null_when_input_is_null() {
        //given
        String input = null;
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isNull();
    }
}