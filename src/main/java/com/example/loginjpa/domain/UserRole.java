package com.example.loginjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN, USER
}
