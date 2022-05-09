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
public class PermissionResponse {
    @JsonProperty("roleId")
    private String roleName;
    private String permissionId;
    private String permissionName;
    /**
     * action描述
     */
    private List<ActionResponse> actionEntitySet;
}
