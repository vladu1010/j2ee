package net.homecredit.comp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDto implements Serializable {

    private String code;
    private String value;

    public RoleDto() {
    }

    public RoleDto(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Cannot convert a null role code");
        }
        this.code = code;
    }

}

