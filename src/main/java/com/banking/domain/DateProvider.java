package com.banking.domain;
import java.time.LocalDate;

public interface DateProvider {
    LocalDate today();
}