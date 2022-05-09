package com.zhtty.mock.box.model.out.upms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GetUserInfoResponse {
    private Long id;
    @JsonProperty("role")
    private UserRoleResponse roles;
    private String name;
    private String avatar;//头像

}
