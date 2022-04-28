package com.zhtty.mock.box.model.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String userNo;
    private String username;
    private String token;
    private boolean flag;
}
