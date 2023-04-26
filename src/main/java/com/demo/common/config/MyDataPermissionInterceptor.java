package com.demo.common.config;

import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Q1sj
 * @date 2023.4.25 16:18
 */
@Slf4j
public class MyDataPermissionInterceptor extends DataPermissionInterceptor {
    private final TablesNamesFinder tablesNamesFinder;

    public MyDataPermissionInterceptor(DataPermissionHandler dataPermissionHandler) {
        super(dataPermissionHandler);
        this.tablesNamesFinder = new TablesNamesFinder();
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (InterceptorIgnoreHelper.willIgnoreDataPermission(ms.getId())) {
            return;
        }
        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
        if (ignoreTable(mpBs.sql())) {
            return;
        }
        mpBs.sql(parserSingle(mpBs.sql(), ms.getId()));
    }

    private boolean ignoreTable(String sql) {
        try {
            List<String> tableList = tablesNamesFinder.getTableList(CCJSqlParserUtil.parse(sql));
            return tableList == null || !tableList.contains("test_config");
        } catch (JSQLParserException e) {
            log.warn(e.getMessage(), e);
            return false;
        }
    }
}
