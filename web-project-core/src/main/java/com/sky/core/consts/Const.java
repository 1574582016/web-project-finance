package com.sky.core.consts;


public enum Const {

    /**
     * 系统设定
     */
    DEV(18096, "development"),
    PRO(8080, "product"),
    APP(1, "APP"),
    WEB(2, "WEB"),
    PAD(3, "PAD"),

    /**
     * 系统模块
     */
    HCXZ(1, "鸿诚祥兆管理系统担保"),
    QDFK(2, "乔迪集团风控"),
    HCKF(3, "鸿诚祥兆管理系统客服"),


    /**
     * 客户端请求，返回编码
     */
    OK(HttpConst.OK, "服务器成功返回用户请求的数据"),
    ERROR(HttpConst.SERVERERROE, "操作数据失败"),

    PWD(HttpConst.PWD, "错误的密码或登录帐号，请检查"),
    PWDSIMPLE(HttpConst.PWD, "密码太简单，请重新设置"),
    PHONEUNFOND(HttpConst.PWD, "手机号未录入，请完善个人信息"),
    SHIROUSERNOTFOUND(HttpConst.PWD, "用户不存在,请联系管理员"),
    DEPTNOTFOUND(HttpConst.PWD, "操作部门无效,请联系管理员"),
    CREATED(HttpConst.CREATED, "用户新建数据成功"),
    UPDATED(HttpConst.CREATED, "用户修改数据成功"),
    ACCEPTED(HttpConst.ACCEPTED, "受认可的资源"),
    CREATEFTD(HttpConst.CREATEFTD, "客户端创建数据失败"),
    UPDATEFTD(HttpConst.CREATEFTD, "客户端修改数据失败"),
    NOCONTENT(HttpConst.NOCONTENT, "用户删除数据成功"),
    DELETED(HttpConst.OK, "客户端删除数据成功"),
    DELETEFTD(HttpConst.DELETEFTD, "客户端删除数据失败"),
    BADREQUEST(HttpConst.BADREQUEST, "用户发出的请求有错误"),
    UNAUTHORIZED(HttpConst.UNAUTHORIZED, "客户端没有授权的请求"),
    FORBIDDEN(HttpConst.FORBIDDEN, "客户端被禁止的请求"),
    NOTFOUND(HttpConst.NOTFOUND, "未知的资源响应"),
    NOTALLOWED(HttpConst.NOTALLOWED, "客户端被拒绝的请求"),
    SERVERERROE(HttpConst.SERVERERROE, "服务器发送未知的错误"),
    SERVERUNAVAILABLE(HttpConst.SERVERUNAVAILABLE, "服务器当前不能处理请求"),

    /**
     * 异常处理编码
     */
    IPEXCEPTION(HttpConst.NOTALLOWED, "IP访问异常"),
    TOKENEXCEPTION(HttpConst.NOTALLOWED, "Token认证异常"),
    XSSSQLEXCEPTION(HttpConst.NOTALLOWED, "XSS-SQL验证异常"),
    PERMISSIONEXCEPTION(HttpConst.NOTALLOWED, "权限验证异常"),

    NULLPOINTEREXCEPTION(20101, "空指针引用异常"),
    CLASSCASTEXCEPTION(20102, "类型强制转换异常"),
    ILLEGALARGUMENTEXCEPTION(20103, "传递非法参数异常"),
    ARITHMETICEXCEPTION(20104, "算术运算异常"),
    ARRAYSTOREEXCEPTION(20105, "向数组中存放与声明类型不兼容对象异常"),
    INDEXOUTOFBOUNDSEXCEPTION(20106, "下标越界异常"),
    NEGATIVEARRAYSIZEEXCEPTION(20107, "创建一个大小为负数的数组错误异常"),
    NUMBERFORMATEXCEPTION(20108, "数字格式异常"),
    SECURITYEXCEPTION(20109, "安全异常"),
    UNSUPPORTEDOPERATIONEXCEPTION(20110, "不支持的操作异常"),
    SQLEXCEPTION(20111, "SQL执行异常"),
    IOEXCEPTION(20112, "IO流(输入输出)异常"),
    NOSUCHMETHODEXCEPTION(20113, "方法未找到异常"),
    DATETIMEEXCEPTION(20114, "日期时间解析异常"),
    RSPONSECOMMONERROR(20115, "少见异常的异常"),


    /**
     * 部门类型
     */
    BUSINESSDEPT(1, "业务部门"),
    MANAGEMENTDEPT(2, "业务管理部"),
    BANKDEPT(3, "银行业务发展部"),
    BANKDEPTCM(4, "银行业务发展部CM"),
    BANKDEPTC(5, "银行业务发展部C"),
    BANKDEPTM(6, "银行业务发展部M"),
    SHORTTERMDEPT(7, "短融业务发展部"),
    GENERALMANAGERDEPT(8, "总经办"),
    FINANCE(9, "财务部"),
    CEO(10, "总裁"),

    /**
     * 合同类型
     */
    CREDIT(1, "信用贷"),
    MORTGAGE(2, "抵押贷"),
    PLEDGE(3, "质押贷"),

    /**
     * 流程设定
     */
    CONTRACTAPPLY(1, "合同申请流程"),
    CONTRACTREPORT(2, "合同报备流程"),
    CONTRACTCMRETURN(3, "信用/抵押无点位返合同流程"),
    CONTRACTCMRETURNPOINT(4, "信用/抵押有点位返合同流程"),
    CONTRACTPRETURN(5, "质押返合同流程"),
    INTERVIEW(6, "面谈流程"),
    CONTRACTREVOKE(7, "合同撤单流程"),
    CONTRACTCANCEL(8, "合同作废流程"),
    RETURNCM(9, "信贷/抵押回款流程"),
    RETUENP(10, "质押回款流程"),
    TRANSFER(11, "业绩调拨流程"),
    EXPENSESCM(12, "信贷/抵押支出流程"),
    EXPENSESP(13, "质押支出流程"),
    DREVERSE(14, "定金转业绩流程"),
    MREVERSE(15, "材料费转业绩流程"),

    /**
     * TOKEN设定
     */
    TOKENSECRET(1, "X#$%(!QOODIT"),
    TOKENALG(2, "HS256"),
    TOKENTYP(3, "JWT"),
    TOKENEXPTIME(4, "3600");


    /**
     * 返回编码
     */
    Integer code;

    /**
     * 消息描述
     */
    String message;

    Const(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getValue() {
        return message;
    }
}
