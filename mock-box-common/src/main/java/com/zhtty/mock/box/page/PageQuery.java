package com.zhtty.mock.box.page;

import lombok.Data;

/**
 * @author A8142
 */
@Data
public class PageQuery {

    public final static int PAGE = 1;

    public final static int PAGE_SIZE = 10;

    protected int page = PAGE;

    protected int pageSize = PAGE_SIZE;

    protected boolean paged = true;

    public void notPage() {
        paged = false;
    }

}