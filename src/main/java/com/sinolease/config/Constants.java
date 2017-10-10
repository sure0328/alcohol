package com.sinolease.config;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlbertLy on 2016/11/22.
 */
public class Constants {

    // 上传文件 路径
    public static final String UPLOAD_PATH = "java.io.tmpdir";
    public static final String VIDEO_UPLOAD_PATH = "/templatefiles/video/tmpdir";

    public static final String DOWNLOAD_PATH = "java.io.tmpdir/profit/down";
    // 考核利润 路径
    public static final String PROFIT_UPLOAD_PATH = UPLOAD_PATH + "/profit";

    // 反洗钱客户信息表 路径
    public static final String AML_CUSTOMER_UPLOAD_PATH = UPLOAD_PATH + "/AML";
    // 应收租赁款统计报表 路径
    public static final String CI_PAYMENTCOUNT_UPLOAD_PATH = UPLOAD_PATH + "/payment";

    // Double 0.0
    public static final Double ZERO = 0.00;
    public static final Double ONE = 1.00;

    // 一个季度3个月
    public static final Integer MONTH_OF_SEASON = 3;

    // 用于大多数实体的表
    // 无效状态
    public static Integer INVALID = 0;
    // 有效状态
    public static Integer VALID = 1;

    // 未归档状态
    public static Integer UNDOCUMENTED = 1;
    // 归档状态
    public static Integer DOCUMENTED = 2;


    //FTP类型常量
    public static final int FTP_TYPE = 1;
    //拨备类型常量
    public static final int PROVISION_TYPE = 2;
    //税率类型常量
    public static final int RATE_TYPE = 3;

    //角色
    public static final String ROLE_TYPE_U = "1";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_TYPE_A = "2";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_TYPE_F = "3";
    public static final String ROLE_FINANCE = "FINANCE";
    public static final String ROLE_TYPE_M = "4";
    public static final String ROLE_MARKET = "MARKET";
    public static final String ROLE_TYPE_Z = "5";
    public static final String ROLE_ZONGHE = "ZONGHE";
    public static final String ROLE_TYPE_FW = "6";
    public static final String ROLE_FAWU = "FAWU";

    public static final Integer ADMIN_ID = 0;


    //部门属性---前中后台
    public static final int ALL_OFFICE=0;
    public static final int BACK_OFFICE=1;
    public static final int MIDDLE_OFFICE=2;
    public static final int FRONT_OFFICE=3;

    //部门级别
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;

    //打分级别
    public static final int SCORE_BIG_LEADER = 1;
    public static final int SCORE_LEADER = 2;
    public static final int SCORE_NORMAL = 3;

    //打分权重比例
    public static final int SCORE_BIG_LEADER_WEIGHT = 15;
    public static final int SCORE_LEADER_WEIGHT = 15;
    public static final int SCORE_NORMAL_WEIGHT = 70;
    public static final int SCORE_SPECIAL_WEIGHT = 85;

    //租赁业务类型-动产售后直租
    public static final int RENT_TYPE_MPDR = 1;
    //租赁业务类型-动产售后回租
    public static final int RENT_TYPE_MPRR = 2;
    //租赁业务类型-不动产售后直租
    public static final int RENT_TYPE_REDR = 3;
    //租赁业务类型-不动产售后回租
    public static final int RENT_TYPE_RERR = 4;

    //企业类型
    public static final String COMPANY_TYPE_1 = "私有企业";
    public static final String COMPANY_TYPE_2 = "国有企业";
    public static final String COMPANY_TYPE_3 = "上市公司";

    //业务来源
    public static final String CHANNEL_1 = "曾与公司有业务合作";
    public static final String CHANNEL_2 = "第三方业务合作或居间介绍";
    public static final String CHANNEL_3 = "二级居间介绍";
    public static final String CHANNEL_4 = "其他";

    //行业
    public static final String INDUSTRY_1 = "政府机关、事业单位、房地产、制造业、中介机构";
    public static final String INDUSTRY_2 = "上市公司、大型国有独资或控股企业、资信良好的其他企业";
    public static final String INDUSTRY_3 = "其他";

    //
    public static final String LICIENSE_TYPE_1 = "执照";
    public static final String LICIENSE_TYPE_2 = "证件";
    public static final String LICIENSE_TYPE_3 = "文件";


    public static final String STAKEHOLDER_TYPE_1 = "股东";
    public static final String STAKEHOLDER_TYPE_2 = "法人";
    public static final String STAKEHOLDER_TYPE_3 = "负责人";
    public static final String STAKEHOLDER_TYPE_4 = "授权办理业务人员";

    public static final Map<Integer, String> RENT_TYPE_MAP = new LinkedHashMap<Integer, String>() {{
        put(1, "动产直租");
        put(2, "动产回租");
        put(3, "不动产直租");
        put(4, "不动产回租");
    }};
    public static final Map<Integer, String> DEPT_CLASS_MAP = new LinkedHashMap<Integer, String>() {{
        put(1, "创利部门");
        put(2, "管理部门");
        put(3, "支持部门");
        put(0, "高级管理人员");
    }};
    public static final Map<Integer, String> DEPT_CLASS_INDEX_MAP = new LinkedHashMap<Integer, String>() {{
        put(1, "创利系数");
        put(2, "管理系数");
        put(3, "支持系数");
        put(0, "高管系数");
    }};
    public static final Map<Integer, String> QUARTER_MAP = new LinkedHashMap<Integer, String>() {{
        put(1, "第一季度");
        put(2, "第二季度");
        put(3, "第三季度");
        put(4, "第四季度");
    }};
    public static final Map<Integer, String> YEAR_MAP = new LinkedHashMap<Integer, String>() {{
        put(2013, "2013");
        put(2014, "2014");
        put(2015, "2015");
        put(2016, "2016");
        put(2017, "2017");
        put(2018, "2018");
        put(2019, "2019");
        put(2020, "2020");
    }};
//    public static final Map<String, String> AUTHORITY_TYPE_MAP = new LinkedHashMap<String, String>() {{
//        put("ADMIN","超级用户");
//        put("contract","偿还计划管理");
//        put("paymentReport","应收租赁款明细");
//        put("paymentReportCount","应收租赁款统计");
//        put("profit","考核利润");
//        put("profit/profitReport","考核利润报表");
//        put("newKPI","考评打分");
//        put("KPIResult","打分考评结果");
//        put("AMLCustomer","反洗钱客户维护");
//        put("AMLScore","反洗钱客户考评打分");
//
//        put("CONFIG","参数维护");
//        put("DETAIL","信息维护");
//    }};

    public static final Map<String, Integer> REGION_MAP = new LinkedHashMap<String, Integer>() {{
        put("全部", 0);
        put("华南", 1);
        put("华北", 2);
        put("华西", 3);
        put("华东", 4);
        put("华中", 5);
        put("东北", 6);
        put("东南", 7);
        put("西南", 8);
        put("西北", 9);
        put("未知", 10);
    }};

    public static final String ITEM_TYPE_1_NAME="手续费";
    public static final String ITEM_TYPE_2_NAME="宽限息";
    public static final String ITEM_TYPE_3_NAME="租金";
    public static final String ITEM_TYPE_4_NAME="留购价";

    public static final Map<Integer, String> ITEM_TYPE_MAP = new LinkedHashMap<Integer, String>() {{
        put(1, "手续费");
        put(2, "宽限息");
        put(3, "租金");
        put(4, "留购价");
    }};
    public static final int ITEM_TYPE_1_VALUE=1;
    public static final int ITEM_TYPE_2_VALUE=2;
    public static final int ITEM_TYPE_3_VALUE=3;
    public static final int ITEM_TYPE_4_VALUE=4;

    //租金收取方式-先付
    public static final int PAY_TYPE_PRE = 1;
    //租金收取方式-后付
    public static final int PAY_TYPE_POST = 2;

    public static final Map<Integer, String> PAY_TYPE_MAP = new LinkedHashMap<Integer, String>() {{
        put(1, "先付");
        put(2, "后付");
    }};

    public static final List<String> PROFIT_COMMON_INFO = Arrays.asList(
            "租赁合同编号",
            "客户名称",
            "主办",
            "行业",
            "业务类型",
            "租金收取方式",
            "投放本金",
            "剩余预收租金",
            "起租日",
            "印花税率",
            "增值税率",
            "合同金额"
    );


    public enum PROFIT_COMMON_INFO_NAME {
        CONTRACT_ID("租赁合同编号"),
        CUSTOMER_NAME("客户名称"),
        MAIN_OPERATOR("主办"),
        INDUSTRY("行业"),
        SERVICE_TYPE("业务类型"),
        PAY_TYPE("租金收取方式"),
        FUNDS("投放本金"),
        REMAIN_RENT("剩余预收租金"),
        START_TIME("起租日"),
        STAMP_TAX("印花税率"),
        VALUEADDED_TAX("增值税率"),
        CONTRACT_PRICE("合同金额");
        private final String name;

        PROFIT_COMMON_INFO_NAME(final String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        public static PROFIT_COMMON_INFO_NAME getByValue(String value) {
            for (PROFIT_COMMON_INFO_NAME item : values()) {
                if (item.getName().equals(value)) {
                    return item;
                }
            }
            return null;
        }
    }



    // 导入的时候用的
    public static final List<String> PROFIT_LIST_HEADER = Arrays.asList(
            "承付日期",
            "期项",
            "剩余本金",
            "本金",
            "利息",
            "手续费",
            "违约金",
            "冲回拨备",
            "计提拨备",
            "应扣减成本",
            "实收本金",
            "实收利息"
    );

    // 导入的时候用的
    public enum PROFIT_LIST_HEADER_NAME {
        PAY_DATE("承付日期"),
        SEQUENCE("期项"),
        REMAIN_FUNDS("剩余本金"),
        FUNDS("本金"),
        INTEREST("利息"),
        FEE("手续费"),
        OTHER_FEE("违约金"),
        PROVISION_FEEDBACK("冲回拨备"),
        PROVISION_CUT("计提拨备"),
        FUNDS_CUT("应扣减成本"),
        RECEIPT_FUND("实收本金"),
        RECEIPT_INTEREST("实收利息");
        private final String name;

        PROFIT_LIST_HEADER_NAME(final String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        public static PROFIT_LIST_HEADER_NAME getByValue(String value) {
            for (PROFIT_LIST_HEADER_NAME item : values()) {
                if (item.getName().equals(value)) {
                    return item;
                }
            }
            return null;
        }
    }

    // 导出的时候用的
    public static final List<String> PROFIT_LIST_EXPORT_HEADER = Arrays.asList(
            "承付日期",
            "时间范围",
            "剩余本金",
            "本金",
            "利息",
            "手续费",
            "违约金",
            "冲回拨备",
            "计提拨备",
            "应扣减成本",
            "实收本金",
            "实收利息"
    );

    public static final List<String> PROFIT_CALCULATE_LIST_HEADER = Arrays.asList(
            "收入",
            "按日计息收入",
            "实际确认利息收入",
            "其他收入",
//            "资金成本",
            "实际资金成本",
            "印花税",
            "增值税",
            "考核利润"
    );

    public static final List<String> AML_CUSTOMER_LIST_HEADER = Arrays.asList(
            "序号",
            "客户名称",
            "反洗钱客户评级分数",
            "我公司向客户投放融资租赁款的时间（如分次放款的，填写第一次放款的时间）",
            "租赁期限（年）",
            "是否有担保",
            "担保是否已办理登记并将担保登记文件存于公司（有担保的业务填写）",
            "是否为关联交易",
            "如为关联交易是否报备（关联交易的业务填写）",
            "年龄（客户为自然人时填写）",
            "自然人客户所持身份证明文件（1.身份证；2.护照或港澳台居民身份证件)",
            "公司成立时间（法人客户时适用，按营业执照上的公司成立日期填写）",
            "股东数量（法人客户时适用）",
            "股东名称（法人客户时适用）",
            "企业类型（1.私营企业；2.国有企业；3.上市公司）",
            "业务来源(1.曾与公司有业务合作；2.第三方业务合作或居间介绍；3.二级居间介绍；4.其他）",
            "行业（1.政府机关、事业单位、房地产、制造业、中介机构；2.上市公司、大型国有独资或控股企业、资信良好的其他企业3.其他）",
            "自上次填写至今前述全部客户信息是否有变更，如有，变更内容（如股东变更、客户名称变更等情况，如有，请一一列明）"
    );
    public static final List<String>CI_PAYMENTCOUNT_LIST_HEADER = Arrays.asList(

            "序号",
            "部门",
            "收款月份",
            "应收笔数",
            "应收租赁款",
            "本金",
            "利息",
            "已还款金额"
    );
    public static final List<String>CI_PAYMENT_LIST_HEADER = Arrays.asList(
            "序号",
            "公司名称",
            "部门",
            "应收租赁款",
            "本金",
            "利息",
            "收款日期",
            "已还款金额",
            "还款标志"
    );

    public static final List<String> PAYMENT_PLAN_COMMON_INFO = Arrays.asList(
            "租赁合同编号",
            "客户名称",
            "主办",
            "行业",
            "业务类型",
            "租金收取方式",
            "税率",
            "内部收益率",
            "起租日",
            "投放日",
            "地区",
            "投放金额"
//            "合同金额"
    );

    public enum PAYMENT_PLAN_COMMON_INFO_NAME {
        CONTRACT_ID("租赁合同编号"),
        CUSTOMER_NAME("客户名称"),
        MAIN_OPERATOR("主办"),
        INDUSTRY("行业"),
        SERVICE_TYPE("业务类型"),
        PAY_TYPE("租金收取方式"),
        START_TIME("起租日"),
        FUND_TIME("投放日"),
        REGION("地区"),
        FUNDS("投放金额"),
        TAXRATE("税率"),
        IRR("内部收益率");
        private final String name;

        PAYMENT_PLAN_COMMON_INFO_NAME(final String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        public static PAYMENT_PLAN_COMMON_INFO_NAME getByValue(String value) {
            for (PAYMENT_PLAN_COMMON_INFO_NAME item : values()) {
                if (item.getName().equals(value)) {
                    return item;
                }
            }
            return null;
        }
    }

    // 导入的时候用的
    public static final List<String> PAYMENT_PLAN_LIST_HEADER = Arrays.asList(
            "期数",
            "租金收款日期",
            "项目",
            "金额",
            "利息合计",
            "利息（不含税）",
            "税金",
            "本金"
    );

    // 导入的时候用的
    public enum PAYMENT_PLAN_LIST_HEADER_NAME {
        RENT_DATE("租金收款日期"),
        SEQUENCE("期数"),
        INTEREST_TOTAL("利息合计"),
        FUND("本金"),
        INTEREST("利息（不含税）"),
        FEE("金额"),
        FEE_ITEM("项目"),
        TAX("税金");
        private final String name;

        PAYMENT_PLAN_LIST_HEADER_NAME(final String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        public static PAYMENT_PLAN_LIST_HEADER_NAME getByValue(String value) {
            for (PAYMENT_PLAN_LIST_HEADER_NAME item : values()) {
                if (item.getName().equals(value)) {
                    return item;
                }
            }
            return null;
        }
    }


}
