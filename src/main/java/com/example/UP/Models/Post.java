package com.example.UP.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Post implements GrantedAuthority {
    Нет, Администратор, Закупщик, Кладовщик, Кассир, Логист, Курьер;

    @Override
    public String getAuthority() { return name(); }
}