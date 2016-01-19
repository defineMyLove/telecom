package com.company.electriccar.common.syscontext;

/**
 * 常量表
 * 
 * @author gengzi
 * 
 */
public interface Const {


    String USER_SESSION_ID = "LOGIN_USER";
    /**  短信验证码**/
    String SMS_ID = "SMS_ID";
    int USER_TYPE_PUTONG = 1;
    int USER_TYPE_GONGAN = 2;
    int USER_TYPE_ADMIN =3;
    /**
     * 树形结构的顶级id，如顶级行政区划code，顶级菜单id
     */
    Integer TOP_ID=0;
    /** 行政区划级别1、省级2、市级3、区县级*/
    int xzqh_level1=1,xzqh_level2=2,xzqh_level3=3;

    /** 车辆类型1、电动车2、自行车*/
    int car_type_ddc=1,car_type_zxc=2;
   
    /** 车贴 1:使用 2:废弃 **/
    Integer CHETIE_STATE_USE = 1;
    Integer CHETIE_STATE_NOUSE = 2;

    /** 状态：无效 */
    int STATE_INVALID = 0;
    /** 状态：有效 */
    int STATE_VALID = 1;

    /**表示数据库中该字段未空值：便于设计非空字段或查询该字段为空的数据*/
    String null_data_column_val="-";
    /**电动车登记状态：1、持有2、丢失3、被盗抢*/
    int ddc_state1=1,ddc_state2=2,ddc_state3=3;

    /**点子身份证申请状态：1、已申请2、已审核3、审核未通过4、已制作完毕5、已领取*/
    int ddc_id_req_state1=1,ddc_id_req_state2=2,ddc_id_req_state3=3,
            ddc_id_req_state4=4,ddc_id_req_state5=5;

    /** 订单状态 0:未支付 1:已支付**/
    Integer PAY_ORDER_NO =0;
    Integer PAY_ORDER_YES =1;

    /** 字典分组**/
    String GROUP_BRAND="brand";
    String  ORDER_AMOUNT ="0.1";
}
