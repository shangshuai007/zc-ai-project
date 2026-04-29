
# swagger

访问地址：http://localhost:40000/doc.html

# 用户、组织、角色转换

系统库(base开头的)和业务库(biz开头的)基础数据id不一致解决：

> 用户表(base_user)、组织表(base_dept)、用户组织关联关系表(base_user_dept)里的用户id、组织id和统一门户信息对照；
>
> 角色表(base_role)、用户角色关联关系表(base_user_role)里的角色id和PMS对照，用户id和统一门户对照；
>
> 系统。

通过kpi-biz-auth-adapter模块将当前登录用户id及关联部门id替换成PMS对应的id，并封装成KpiUserInfoVo以便业务系统使用，具体请看LoginUserServiceAdapter类。


RoleEnum：定义角色信息（统一门户id对照）
DeptConstant：定义交付部门 && 业务部门一级部门部门标识（统一门户id对照）
PmsIdMapper：统一门户id与PMS的id转换

