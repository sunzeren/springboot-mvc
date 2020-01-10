package com.sun.demo.gitLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Author by Sun, Date on 2020/1/10.
 * PS: Not easy to write code, please indicate.
 * 生成周报
 */
public class GenerateWeekReport {

    //TODO Sun 2020/1/10 此处的文本可执行git命令,再从输入流中获取,待优化
    private static String gitLog =
            "优化文章工具栏显示\n" +
                    "of\n" +
                    "修改首页文章排序问题\n" +
                    "2020/1/9\n" +
                    "文章统计整合\n" +
                    "of\n" +
                    "of\n" +
                    "文章列表增加\n" +
                    "增加栏目搜索,去除关键字,栏目展示\n" +
                    "文章接口,增加过滤仅查询上线文章\n" +
                    "修改爬虫页面入库操作时,按钮变为发布\n" +
                    "文章列表,新增上下线操作\n" +
                    "2020/1/9\n" +
                    "of\n" +
                    "修改缓存配置,默认Cache\n" +
                    "修改配置文件为UTF-8格式编码\n" +
                    "of\n" +
                    "文章摘要不必填\n" +
                    "文章摘要\n" +
                    "解决文章搜索按钮换行显示问题\n" +
                    "2020/1/8\n" +
                    "修改展示类型为单选框,模板默认选中最新模板\n" +
                    "提取裁剪文章所需js,css至fragment,文章模板编辑器增加裁剪按钮\n" +
                    "2020/1/8\n" +
                    "修改文章管理下拉框样式\n" +
                    "增加超级管理员初始化sql\n" +
                    "增加缓存配置,缓存首页条目分页(两小时过期)及,接口注释\n" +
                    "首页栏目分类,增加缓存\n" +
                    "增加缓存首页广告(主图,介绍)\n" +
                    "修改公用删除提示为message字段\n" +
                    "删除标签判断是否存在关联文章\n" +
                    "修改角色为权限组\n" +
                    "上传图片全路径-\n" +
                    "2020/1/8\n" +
                    "首页导航重构,更改icon,所有选项卡更改为一级导航\n" +
                    "恢复登录背景图片\n" +
                    "初步优化富文本顶部工具栏,(注释表格相关操作,获取当前日期,生成涂鸦等)\n" +
                    "优化裁剪图片布局排版\n" +
                    "优化裁剪图片UI\n" +
                    "增加裁剪图片Icon\n" +
                    "修改文章,图片存储为全路径\n" +
                    "修改广告,接口图片回显改为全路径\n" +
                    "修改广告,图片回显改为全路径\n" +
                    "调整裁剪长宽比例\n" +
                    "新增,编辑文章,富文本编辑器增加图片裁剪功能\n" +
                    "裁剪图片初版\n" +
                    "2020/1/6\n" +
                    "'dev_hwd'\n" +
                    "首页广告\n" +
                    "2020/1/6\n" +
                    "文章增加字段,作者,来源\n" +
                    "增加搜索栏\n" +
                    "修改广告存储图片地址为绝对路径\n" +
                    "2020/1/6\n" +
                    "修改存储图片时为绝对路径\n" +
                    "文章管理绑定模板关系\n" +
                    "of\n" +
                    "of\n" +
                    "优化排版\n" +
                    "文章管理绑定栏目关系\n" +
                    "后台文章增加绑定标签关系\n" +
                    "后台文章管理模块-base\n" +
                    "2020/1/6\n" +
                    "增加注释\n" +
                    "增加获取所有栏目接口\n" +
                    "增加文章分页接口,文章增加字段文章展示类型,文章关键字,点赞数,阅读数\n" +
                    "web接口增加前缀,/web\n" +
                    "优化文章工具栏显示  2020/1/10 9:35\n" +
                    "   of gitlab.com:mubiaoconfig/chunyun into szr  2020/1/10 9:14\n" +
                    "修改首页文章排序问题  2020/1/9 16:34\n" +
                    "  2020/1/9 14:48\n" +
                    "文章统计整合  2020/1/9 14:37\n" +
                    "   of gitlab.com:mubiaoconfig/chunyun into szr  2020/1/9 14:27\n" +
                    "   of gitlab.com:mubiaoconfig/chunyun into szr  2020/1/9 14:26\n" +
                    "文章列表增加 所属显示字段栏目  2020/1/9 14:23\n" +
                    "增加栏目搜索,去除关键字,栏目展示  2020/1/9 14:07\n" +
                    "文章接口,增加过滤仅查询上线文章  2020/1/9 11:42\n" +
                    "修改爬虫页面入库操作时,按钮变为发布  2020/1/9 11:30\n" +
                    "文章列表,新增上下线操作  2020/1/9 11:19\n" +
                    "  2020/1/9 10:26\n" +
                    "   of gitlab.com:mubiaoconfig/chunyun into szr  2020/1/9 10:22\n" +
                    "修改缓存配置,默认Cache 1秒过期(无缓存)  2020/1/9 10:20\n" +
                    "修改配置文件为UTF-8格式编码  2020/1/9 10:18\n" +
                    "   of gitlab.com:mubiaoconfig/chunyun into szr  2020/1/9 10:02\n" +
                    "文章摘要不必填  2020/1/9 9:39\n" +
                    "文章摘要  2020/1/9 9:32\n" +
                    "解决文章搜索按钮换行显示问题  2020/1/8 18:54\n" +
                    "  2020/1/8 18:01\n" +
                    "修改展示类型为单选框,模板默认选中最新模板  2020/1/8 17:55\n" +
                    "提取裁剪文章所需js,css至fragment,文章模板编辑器增加裁剪按钮  2020/1/8 17:13\n" +
                    "  2020/1/8 16:50\n" +
                    "修改文章管理下拉框样式  2020/1/8 14:57\n" +
                    "增加超级管理员初始化sql  2020/1/8 14:25\n" +
                    "增加缓存配置,缓存首页条目分页(两小时过期)及,接口注释  2020/1/8 14:18\n" +
                    "首页栏目分类,增加缓存  2020/1/8 11:11\n" +
                    "增加缓存首页广告(主图,介绍)  2020/1/8 10:58\n" +
                    "修改公用删除提示为message字段  2020/1/8 10:41\n" +
                    "删除标签判断是否存在关联文章  2020/1/8 10:40\n" +
                    "修改角色为权限组  2020/1/8 9:54\n" +
                    "上传图片全路径-  2020/1/8 9:54\n" +
                    "  2020/1/8 9:42\n" +
                    "首页导航重构,更改icon,所有选项卡更改为一级导航  2020/1/7 18:40\n" +
                    "恢复登录背景图片  2020/1/7";

    public static void main(String[] args) {
        String[] split = gitLog.split("\n");

        ArrayList<String> list = new ArrayList<>(Arrays.asList(split));
        Collections.reverse(list);
        list.forEach(System.out::println);
    }

}
