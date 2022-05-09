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
public class UserRoleResponse {
    @JsonProperty("id")
    private String roleName;
    @JsonProperty("name")
    private String alias;
    private String describe;
    private List<PermissionResponse> permissions;
}
