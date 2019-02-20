package org.gt.shipping.cargo.filter;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Setter
public class UserContext {
    private UUID requestId;
}
