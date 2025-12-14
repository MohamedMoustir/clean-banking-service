package com.banking.infrastructure;

import com.banking.domain.DateProvider;
import java.time.LocalDate;

public class SystemDateProvider implements DateProvider {
    @Override
    public LocalDate today() {
        return LocalDate.now();
    }
}