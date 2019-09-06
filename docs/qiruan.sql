-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1
-- 生成日期： 2019-09-06 16:57:49
-- 服务器版本： 8.0.17
-- PHP 版本： 7.2.19-0ubuntu0.18.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `qiruan`
--

-- --------------------------------------------------------

--
-- 表的结构 `file`
--

CREATE TABLE `file` (
  `id` bigint(20) NOT NULL COMMENT '自动编号',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名',
  `type` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件后缀名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用来存储文件' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `filerel`
--

CREATE TABLE `filerel` (
  `id` bigint(20) NOT NULL COMMENT '自动编号',
  `fid` bigint(20) DEFAULT NULL COMMENT '与文件id对应',
  `itemname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '与文件对应的项目的名字'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存储文件与项目（如页面）之间的关系' ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `job`
--

CREATE TABLE `job` (
  `id` int(11) NOT NULL COMMENT '招聘编号',
  `time` datetime NOT NULL COMMENT '发布时间',
  `uid` int(11) NOT NULL COMMENT '用户编号（与用户表对应）',
  `phone` varchar(23) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT ' 手机号',
  `qq` varchar(23) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'QQ',
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `salary` int(11) DEFAULT NULL COMMENT '工资',
  `detail` varchar(2047) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '具体描述',
  `check` int(11) DEFAULT NULL COMMENT '是否已经审核'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `jobseek`
--

CREATE TABLE `jobseek` (
  `id` int(11) NOT NULL COMMENT '求职编号',
  `time` datetime NOT NULL COMMENT '发布时间',
  `uid` int(11) NOT NULL COMMENT '用户编号（和用户表对应）',
  `school` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '学校',
  `year` int(11) DEFAULT NULL COMMENT '入学年份',
  `major` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '专业',
  `phone` varchar(23) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `qq` varchar(23) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'QQ号',
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `sub1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '学科一',
  `sub2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sub3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `money` int(11) DEFAULT NULL COMMENT '薪水',
  `introduce` varchar(2047) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '个人介绍',
  `check` int(11) DEFAULT NULL COMMENT '是否已经审核（0未提交1审核2未审核）',
  `vcr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'vcr'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `labor`
--

CREATE TABLE `labor` (
  `id` int(11) NOT NULL COMMENT '合作编号',
  `studentid` int(11) NOT NULL COMMENT '学生编号',
  `agencyid` int(11) NOT NULL COMMENT '家长编号',
  `guarantee` int(11) NOT NULL COMMENT '保证金金额',
  `contract` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '合同'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `letter`
--

CREATE TABLE `letter` (
  `id` bigint(20) NOT NULL COMMENT '站内信编号',
  `time` datetime NOT NULL COMMENT '留言时间',
  `senduid` int(11) NOT NULL COMMENT '发送者编号',
  `recieveuid` int(11) NOT NULL COMMENT '接受者编号',
  `content` varchar(2047) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '站内信内容',
  `state` int(11) DEFAULT NULL COMMENT '是否已读（1已读2未读）',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标题'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL COMMENT '留言编号',
  `time` datetime NOT NULL COMMENT '留言时间',
  `belong` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '留言从属',
  `uid` int(11) NOT NULL COMMENT '留言用户编号',
  `content` varchar(2047) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '留言内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `persistent_logins`
--

CREATE TABLE `persistent_logins` (
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `last_used` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT '用户编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `privilege` int(11) DEFAULT NULL COMMENT '权限（1学生2家长3管理员）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- 表的结构 `userinfo`
--

CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL COMMENT '用户信息编号',
  `uid` int(11) NOT NULL COMMENT '与用户编号对应',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `sex` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `cardid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
  `introduce` varchar(2047) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '个人介绍',
  `status` int(11) DEFAULT NULL COMMENT '审核状态（1已审核2未审核）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

--
-- 转储表的索引
--

--
-- 表的索引 `file`
--
ALTER TABLE `file`
  ADD PRIMARY KEY (`id`,`filename`) USING BTREE;

--
-- 表的索引 `filerel`
--
ALTER TABLE `filerel`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `jobseek`
--
ALTER TABLE `jobseek`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `labor`
--
ALTER TABLE `labor`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `letter`
--
ALTER TABLE `letter`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `persistent_logins`
--
ALTER TABLE `persistent_logins`
  ADD PRIMARY KEY (`series`) USING BTREE;

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`,`name`) USING BTREE,
  ADD UNIQUE KEY `name` (`name`) USING BTREE,
  ADD UNIQUE KEY `id` (`id`) USING BTREE;

--
-- 表的索引 `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`id`,`uid`) USING BTREE,
  ADD UNIQUE KEY `id` (`id`) USING BTREE,
  ADD UNIQUE KEY `uid` (`uid`) USING BTREE;

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `file`
--
ALTER TABLE `file`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自动编号';

--
-- 使用表AUTO_INCREMENT `filerel`
--
ALTER TABLE `filerel`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自动编号';

--
-- 使用表AUTO_INCREMENT `job`
--
ALTER TABLE `job`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '招聘编号';

--
-- 使用表AUTO_INCREMENT `jobseek`
--
ALTER TABLE `jobseek`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '求职编号';

--
-- 使用表AUTO_INCREMENT `letter`
--
ALTER TABLE `letter`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '站内信编号';

--
-- 使用表AUTO_INCREMENT `message`
--
ALTER TABLE `message`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言编号';

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号';

--
-- 使用表AUTO_INCREMENT `userinfo`
--
ALTER TABLE `userinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户信息编号';
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;