package com.cisco.appointmentservice.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class StringUtilTest  {

    @Test
    public void testValidateEmail() {
        assertThat("abc.xyz@pqr.com is valid", StringUtil.validateEmail("abc.xyz@pqr.com"), equalTo(true));
        assertThat("abc+xyz@pqr.com is not valid", StringUtil.validateEmail("abc+xyz@pqr.com"), equalTo(false));
        assertThat("abc.xyz@pqr.123 is not valid", StringUtil.validateEmail("abc.xyz@pqr.123"), equalTo(false));
    }
}
