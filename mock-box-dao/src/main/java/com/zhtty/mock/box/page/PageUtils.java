package com.zhtty.mock.box.page;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author A8142
 */
public class PageUtils {

    private static final ThreadLocal<Page> THREAD_LOCAL  = new ThreadLocal();

    public static void startPage(PageQuery pageQuery) {
        if (!pageQuery.isPaged()) {
            return;
        }
        THREAD_LOCAL.set(PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize()));
    }

    public static Page getPage() {
        return THREAD_LOCAL.get();
    }

    public static void clearPage() {
        PageHelper.clearPage();
        THREAD_LOCAL.remove();
    }
}