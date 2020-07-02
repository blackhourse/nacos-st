/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : menu_auth_db

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 02/07/2020 17:58:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `menu_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单编码',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父节点',
  `node_type` tinyint(0) NOT NULL DEFAULT 1 COMMENT '节点类型，1文件夹，2页面，3按钮',
  `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '图标地址',
  `sort` int(0) NOT NULL DEFAULT 1 COMMENT '排序号',
  `link_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '页面对应的地址',
  `level` int(0) NOT NULL DEFAULT 0 COMMENT '层次',
  `path` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '树id的路径 整个层次上的路径id，逗号分隔，想要找父节点特别快',
  `is_delete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除 1：已删除；0：未删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1, '系统配置', 'systemMgr', 0, 1, '1', 1, '1', 1, '1', 0);
INSERT INTO `tb_menu` VALUES (2, '用户管理', 'userMgr', 1, 2, '1', 1, '1', 2, '1', 0);
INSERT INTO `tb_menu` VALUES (3, '角色管理', 'roleMgr', 1, 2, '1', 2, '1', 2, '1', 0);
INSERT INTO `tb_menu` VALUES (4, '菜单管理', 'menuMgr', 1, 12, '1', 3, '1', 2, '1', 0);
INSERT INTO `tb_menu` VALUES (5, '角色查询', 'roleMgr:list', 3, 3, '1', 1, '1', 3, '1,3', 0);
INSERT INTO `tb_menu` VALUES (6, '角色新增', 'roleMgr:add', 3, 3, '1', 2, '1', 3, '1,3', 0);
INSERT INTO `tb_menu` VALUES (7, '角色删除', 'roleMgr:delete', 3, 3, '1', 3, '1', 3, '1,3', 0);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '编码',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `is_delete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除 1：已删除；0：未删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_code`(`code`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 'system', '管理员', 0);
INSERT INTO `tb_role` VALUES (2, 'user', '用户', 0);

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu`  (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES (1, 1, 1);
INSERT INTO `tb_role_menu` VALUES (2, 1, 2);
INSERT INTO `tb_role_menu` VALUES (3, 1, 3);
INSERT INTO `tb_role_menu` VALUES (4, 1, 4);
INSERT INTO `tb_role_menu` VALUES (5, 1, 5);
INSERT INTO `tb_role_menu` VALUES (6, 1, 6);
INSERT INTO `tb_role_menu` VALUES (7, 1, 7);
INSERT INTO `tb_role_menu` VALUES (8, 2, 4);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint unsigned NOT NULL COMMENT '消息给过来的ID',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `is_delete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除 1：已删除；0：未删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE,
  INDEX `idx_mobile`(`mobile`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '15136212594', 'mht', '123456', 0);
INSERT INTO `tb_user` VALUES (2, '123', 'tom', '123456', 0);

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `id` bigint unsigned NOT NULL COMMENT '主键',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (1, 1, 1);
INSERT INTO `tb_user_role` VALUES (2, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
