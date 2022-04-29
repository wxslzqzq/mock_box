package com.zhtty.mock.box.model.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserInfoResponse {
    private List<String> roles;
    private String name;
    private String avatar;//头像

}
