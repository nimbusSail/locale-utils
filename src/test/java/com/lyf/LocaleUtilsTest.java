package com.lyf;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class LocaleUtilsTest {
    private static final String EMPTY = "";
    private LocaleUtils localeUtils;

    @Before
    public void setUp() {
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

    @Test
    public void should_get_empty_locale_when_input_is_empty() {
        //given
        String input = EMPTY;
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(EMPTY, EMPTY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_input_contains_number_sign() {
        //given
        String input = "#1";
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_input_length_is_shorter_than_two() {
        //given
        String input = "1";
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_first_is_underscore_and_length_is_shorter_than_three() {
        //given
        String input = "_2";
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_first_is_underscore_and_second_is_lower_case() {
        //given
        String input = "_cN";
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_first_is_underscore_and_third_is_lower_case() {
        //given
        String input = "_Cn";
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_first_is_underscore_and_both_second_and_third_are_lower_case() {
        //given
        String input = "_cn";
        //when
        localeUtils.toLocale(input);
    }

    @Test
    public void should_to_locale_when_first_is_underscore_and_length_is_three() {
        //given
        String input = "_CN";
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(EMPTY, input.substring(1, 3)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_first_is_underscore_and_length_is_not_three_but_shorter_than_five() {
        //given
        String input = "_CNN";
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_first_is_underscore_and_third_is_not_underscore() {
        //given
        String input = "_CNNN";
        //when
        localeUtils.toLocale(input);
    }

    @Test
    public void should_to_locale_when_first_is_underscore_and_third_is_underscore() {
        //given
        String input = "_CN_xx";
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(EMPTY, input.substring(1, 3), input.substring(4)));
    }

    @Test
    public void should_to_locale_when_isISO_and_length_is_two() {
        //given
        String input = "zh";
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(input));
    }

    @Test
    public void should_to_locale_when_isISO_and_length_is_three() {
        //given
        String input = "xxx";
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(input));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_length_is_lower_case_and_longer_than_three() {
        //given
        String input = "xxxx";
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_split_to_more_than_three_parts() {
        //given
        String input = "zh_CN_dd_dd";
        //when
        localeUtils.toLocale(input);
    }

    @Test
    public void should_to_locale_when_is_iso_lang_and_iso_country() {
        //given
        String lang = "zh";
        String country = "CN";
        String input = lang + "_" + country;
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(lang, country));
    }

    @Test
    public void should_to_locale_when_is_iso_lang_and_num_country() {
        //given
        String lang = "zh";
        String country = "234";
        String input = lang + "_" + country;
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(lang, country));
    }

    @Test
    public void should_to_locale_when_isISO_lang_and_country_and_variant_and_country_length_is_zero() {
        //given
        String lang = "zh";
        String country = "";
        String variant = "va";
        String input = lang + "_" + country + "_" + variant;
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(lang, country, variant));
    }

    @Test
    public void should_to_locale_when_isISO_lang_and_country_and_variant_and_is_iso_country_code() {
        //given
        String lang = "zh";
        String country = "CN";
        String variant = "va";
        String input = lang + "_" + country + "_" + variant;
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(lang, country, variant));
    }

    @Test
    public void should_to_locale_when_isISO_lang_and_country_and_variant_and_is_numeric_country_code() {
        //given
        String lang = "zh";
        String country = "234";
        String variant = "va";
        String input = lang + "_" + country + "_" + variant;
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(lang, country, variant));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_isISO_lang_and_country_and_variant_and_is_not_numeric_country_code() {
        //given
        String lang = "zh";
        String country = "23d";
        String variant = "va";
        String input = lang + "_" + country + "_" + variant;
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_isISO_lang_and_empty_country() {
        //given
        String lang = "zh";
        String country = "";
        String input = lang + "_" + country;
        //when
        localeUtils.toLocale(input);
    }

    @Test
    public void should_get_exception_when_isISO_lang_and_country_and_variant_and_is_empty_country() {
        //given
        String lang = "zh";
        String country = "";
        String variant = "va";
        String input = lang + "_" + country + "_" + variant;
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(lang, country, variant));
    }

    @Test
    public void should_to_locale_when_is_not_iso_lang_and_num_country() {
        //given
        String lang = "Zh";
        String country = "234";
        String input = lang + "_" + country;
        //when
        Locale result = localeUtils.toLocale(input);
        //then
        assertThat(result).isEqualTo(new Locale(lang, country));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_is_not_iso_lang_and_country_and_variant() {
        //given
        String lang = "zH";
        String country = "CN";
        String variant = "va";
        String input = lang + "_" + country + "_" + variant;
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_is_iso_lang_and_country_and_empty_variant() {
        //given
        String lang = "zh";
        String country = "CN";
        String variant = "";
        String input = lang + "_" + country + "_" + variant;
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_is_not_iso_lang_and_num_country_length_is_not_three() {
        //given
        String lang = "zH";
        String country = "12";
        String input = lang + "_" + country;
        //when
        localeUtils.toLocale(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_exception_when_is_not_iso_lang_and_iso_country_length_is_not_two() {
        //given
        String lang = "zh";
        String country = "CNN";
        String input = lang + "_" + country;
        //when
        localeUtils.toLocale(input);
    }
}