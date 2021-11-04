package com.epam.hotel;

import org.junit.Test;

public class ForTest {
    @Test
    public void justTest() {
        String name = "redirectLoginPage";
        System.out.println(String.format("/WEB-INF/jsp/%s.jsp", name.replace("redirect", "")));
    }
}
