package com.github.bakery.oven;

import org.junit.jupiter.api.Test;

class BaseServiceTest {

    @Test
    void service() {
        // given
        final BaseService baseService = new BaseService();

        // when
        baseService.service();;

        // no assert
    }
}