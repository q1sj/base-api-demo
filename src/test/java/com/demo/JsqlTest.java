package com.demo;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;

/**
 * @author Q1sj
 * @date 2023.4.25 8:34
 */
public class JsqlTest {
    public static void main(String[] args) throws JSQLParserException {
        parseDelete();
        parseSelect();
    }

    private static void editeTableName(){

    }

    private static void parseSelect() throws JSQLParserException {
        String sql = "select * from user where id = 1";
        Select statement = (Select) CCJSqlParserUtil.parse(sql);
        PlainSelect selectBody = (PlainSelect) statement.getSelectBody();
        Expression where = selectBody.getWhere();
        Expression isDeleteExpression = CCJSqlParserUtil.parseCondExpression("is_delete=0");
        if (where == null) {
            selectBody.setWhere(isDeleteExpression);
        } else {
            selectBody.setWhere(new AndExpression(where, isDeleteExpression));
        }
        String newSql = statement.toString();
    }

    private static void parseDelete() throws JSQLParserException {
        String sql = "DELETE FROM repair_apply WHERE id=1 AND repair_number IN ('BX20230413083041','BX20230413082828','BX20230410040301','BX20230406085731');";
        Delete statement = (Delete) CCJSqlParserUtil.parse(sql);
        System.out.println(statement);
        AndExpression andExpression = new AndExpression(statement.getWhere(), CCJSqlParserUtil.parseCondExpression("is_delete=0"));
        statement.setWhere(andExpression);
    }
}
