package com.demo.common.config;

import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.xsy.security.user.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;

/**
 * @author Q1sj
 * @date 2023.4.25 13:30
 */
@Slf4j
public class MyDataPermissionHandler implements DataPermissionHandler {
    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        Long userId = SecurityUser.getUserId();
        if (userId == null) {
            return where;
        }
        Expression expression = new EqualsTo(new Column("user_id"), new LongValue(userId));
        if (where == null) {
            return expression;
        } else {
            return new AndExpression(where, expression);
        }
    }


}
