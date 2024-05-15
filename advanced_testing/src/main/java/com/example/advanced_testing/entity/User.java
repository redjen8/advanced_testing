package com.example.advanced_testing.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User extends BaseEntity{

    private String name;
    private Integer age;
    private UserStatus status;
}
