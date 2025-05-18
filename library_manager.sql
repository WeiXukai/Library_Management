/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : library_manager

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 17/05/2025 18:14:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `isbn` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ISBN(唯一)',
  `title` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书名',
  `author` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者',
  `publisher` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出版社',
  `publish_date` date NULL DEFAULT NULL COMMENT '出版日期',
  `category` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类',
  `cover_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面 URL',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '简介',
  `borrow_count` int NOT NULL DEFAULT 0 COMMENT '被借次数',
  `rating_count` int NOT NULL DEFAULT 0 COMMENT '评分次数',
  `rating_sum` int NOT NULL DEFAULT 0 COMMENT '评分总和',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_book_isbn`(`isbn` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '书目信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '9780307465351', 'Clean Code', 'Robert C. Martin', 'Prentice Hall', '2008-08-11', '计算机', 'https://example.com/covers/cleancode.jpg', '软件质量圣经，讲述如何编写可维护代码。', 170, 230, 995, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (2, '9780132350884', 'The Pragmatic Programmer', 'Andrew Hunt', 'Addison-Wesley', '1999-10-20', '计算机', 'https://example.com/covers/pragmatic.jpg', '程序员必读的思维与实践指导。', 152, 198, 878, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (3, '9780262033848', 'Introduction to Algorithms', 'Thomas H. Cormen', 'MIT Press', '2009-07-31', '算法', 'https://example.com/covers/clrs.jpg', '算法领域权威教材，简称 CLRS。', 94, 140, 615, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (4, '9781491950357', 'Designing Data-Intensive Applications', 'Martin Kleppmann', 'O’Reilly Media', '2017-03-16', '分布式', 'https://example.com/covers/ddia.jpg', '大规模数据系统架构必读。', 81, 120, 566, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (5, '9780134685991', 'Effective Java (3rd)', 'Joshua Bloch', 'Addison-Wesley', '2018-01-06', 'Java', 'https://example.com/covers/ejava.jpg', 'Java 最佳实践条目合集。', 219, 260, 1124, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (6, '9780596007126', 'Head First Design Patterns', 'Eric Freeman', 'O’Reilly Media', '2004-10-25', '设计模式', 'https://example.com/covers/hfdp.jpg', '以图像化方式讲解经典设计模式。', 186, 205, 922, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (7, '9787111128069', '深入理解计算机系统', 'Randal E. Bryant', '机械工业出版社', '2021-06-01', '系统', 'https://example.com/covers/csapp.jpg', 'CSAPP 中文第三版。', 135, 176, 789, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (8, '9787115477317', '计算机网络：自顶向下方法', 'James F. Kurose', '机械工业出版社', '2020-03-01', '网络', 'https://example.com/covers/kurose.jpg', '计算机网络教材经典之作。', 97, 140, 630, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (9, '9781491901632', 'Fluent Python', 'Luciano Ramalho', 'O’Reilly Media', '2015-07-30', 'Python', 'https://example.com/covers/fluentpy.jpg', '深入 Pythonic 思维模式和高级特性。', 121, 158, 690, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (10, '9780131103627', 'The C Programming Language', 'Brian W. Kernighan', 'Prentice Hall', '1988-04-01', 'C 语言', 'https://example.com/covers/kr.jpg', 'C 语言 “K&R” 圣经。', 166, 210, 965, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (11, '9781617292231', 'Spring in Action (5th)', 'Craig Walls', 'Manning', '2018-10-01', 'Java', 'https://example.com/covers/sia5.jpg', 'Spring 框架权威实践指南。', 141, 175, 780, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (12, '9780596517748', 'JavaScript: The Good Parts', 'Douglas Crockford', 'O’Reilly Media', '2008-05-15', '前端', 'https://example.com/covers/js_good_parts.jpg', '精讲 JavaScript 语言精华。', 195, 240, 1015, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (13, '9787115473395', '深入理解 Java 虚拟机', '周志明', '机械工业出版社', '2021-05-01', 'Java', 'https://example.com/covers/jvm.jpg', 'JVM 原理权威中文著作。', 208, 260, 1192, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (14, '9781617294945', 'Kubernetes in Action', 'Marko Luksa', 'Manning', '2017-12-03', '云原生', 'https://example.com/covers/k8s.jpg', 'Kubernetes 实战指南。', 86, 122, 560, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (15, '9781492043454', 'Learning GraphQL', 'Eve Porcello', 'O’Reilly Media', '2018-11-08', 'Web', 'https://example.com/covers/graphql.jpg', 'GraphQL 入门与实践。', 59, 93, 400, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (16, '9787508672069', '引爆点', '马尔科姆·格拉德威尔', '中信出版社', '2018-09-01', '社科', 'https://example.com/covers/tipping.jpg', '社会流行理论经典读物。', 44, 70, 315, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (17, '111000', '我们台湾这些年', '廖信忠', '高宝书版', '2017-06-01', '历史', 'https://example.com/covers/taiwan.jpg', '台湾社会变迁口述史。', 23, 38, 162, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (18, '9787302553410', '人工智能：现代方法', 'Stuart Russell', '清华大学出版社', '2021-08-01', 'AI', 'https://example.com/covers/aima.jpg', 'AI 领域权威教材第三版。', 76, 110, 518, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (19, '9787513314230', '岛上书店', '加布瑞艾拉·扎文', '北京联合出版公司', '2015-01-01', '小说', 'https://example.com/covers/bookstore.jpg', '温暖治愈系畅销小说。', 101, 140, 650, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (20, '9787530216804', '解忧杂货店', '东野圭吾', '南海出版公司', '2014-05-01', '小说', 'https://example.com/covers/jiayou.jpg', '东野圭吾代表作之一。', 137, 190, 890, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (21, '9787508647081', '乌合之众', '古斯塔夫·勒庞', '中信出版社', '2020-04-01', '心理学', 'https://example.com/covers/crowd.jpg', '群体心理学经典。', 89, 115, 533, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (22, '9787108038288', '苏东坡传', '林语堂', '生活·读书·新知三联书店', '2015-03-01', '传记', 'https://example.com/covers/sudongpo.jpg', '文人林语堂写苏轼。', 77, 98, 455, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (23, '9789862138575', '人类简史', '尤瓦尔·赫拉利', '中信出版社', '2014-11-01', '历史', 'https://example.com/covers/sapiens.jpg', '从生物学视角梳理人类史。', 206, 250, 1160, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (24, '9787506394338', '大数据时代', '维克托·迈尔-舍恩伯格', '浙江人民出版社', '2013-06-01', '大数据', 'https://example.com/covers/bigdata.jpg', '探讨大数据社会趋势。', 83, 112, 505, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (25, '9787302513780', '数据结构与算法分析', 'Mark Allen Weiss', '清华大学出版社', '2020-02-01', '算法', 'https://example.com/covers/dsa.jpg', '经典数据结构教材（C++版）。', 64, 90, 423, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (26, '9787111535172', 'Python 编程：从入门到实践', 'Eric Matthes', '人民邮电出版社', '2018-01-01', 'Python', 'https://example.com/covers/python_crash.jpg', '零基础入门经典。', 148, 198, 924, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (27, '9787302479062', '机器学习', '周志华', '清华大学出版社', '2016-03-01', 'AI', 'https://example.com/covers/ml_zhou.jpg', '周志华老师机器学习教材。', 132, 175, 820, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (28, '9780135957059', 'Refactoring (2nd)', 'Martin Fowler', 'Addison-Wesley', '2018-11-19', '重构', 'https://example.com/covers/refactoring.jpg', '重构第二版，JavaScript 示例。', 111, 150, 700, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (29, '9787111479544', '代码整洁之道：JavaScript 实践', '凯尔·辛普森', '人民邮电出版社', '2020-05-01', '前端', 'https://example.com/covers/cleanjs.jpg', 'Clean Code 在 JS 场景的实践版。', 72, 88, 390, '2025-05-10 13:24:06');
INSERT INTO `book` VALUES (30, '9787020002207', '红楼梦', '曹雪芹', '人民文学出版社', '1975-01-01', '古典文学', NULL, '中国古典四大名著之一。', 8, 142, 426, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (31, '9787020009930', '三国演义', '罗贯中', '人民文学出版社', '1972-01-01', '古典文学', NULL, '中国古典四大名著之一。', 130, 216, 864, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (32, '9787020012565', '水浒传', '施耐庵', '人民文学出版社', '1975-01-01', '古典文学', NULL, '中国古典四大名著之一。', 16, 220, 1100, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (33, '9787020008025', '西游记', '吴承恩', '人民文学出版社', '1980-01-01', '古典文学', NULL, '中国古典四大名著之一。', 157, 81, 243, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (34, '9787506365437', '活着', '余华', '作家出版社', '1993-04-01', '当代文学', NULL, '余华经典代表作，关注生死与尊严。', 99, 54, 216, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (35, '9787530209774', '平凡的世界', '路遥', '北京十月文艺出版社', '1986-01-01', '当代文学', NULL, '路遥现实主义长篇小说。', 23, 73, 365, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (36, '9781594631931', 'The Kite Runner', 'Khaled Hosseini', 'Riverhead Books', '2003-05-29', 'Literature', NULL, 'Story of friendship and redemption in Afghanistan.', 85, 68, 204, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (37, '9780307474728', 'One Hundred Years of Solitude', 'Gabriel García Márquez', 'Harper Perennial', '1967-05-30', 'Magic Realism', NULL, 'Masterpiece of magical realism.', 37, 51, 255, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (38, '9780743273565', 'The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', '1925-04-10', 'Classic', NULL, 'American classic exploring the Jazz Age.', 153, 210, 1050, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (39, '9780451524935', '1984', 'George Orwell', 'Signet Classics', '1949-06-08', 'Dystopian', NULL, 'Totalitarian dystopia classic.', 173, 125, 500, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (40, '9780061120084', 'To Kill a Mockingbird', 'Harper Lee', 'Harper Perennial', '1960-07-11', 'Classic', NULL, 'Novel on racial injustice in America.', 28, 32, 128, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (41, '9780141439518', 'Pride and Prejudice', 'Jane Austen', 'Penguin Classics', '1813-01-28', 'Classic', NULL, 'Romantic fiction with social commentary.', 82, 173, 865, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (42, '9780142437247', 'Moby-Dick', 'Herman Melville', 'Penguin Classics', '1851-10-18', 'Classic', NULL, 'Epic tale of obsession and revenge.', 178, 1, 5, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (43, '9780199232765', 'War and Peace', 'Leo Tolstoy', 'Oxford University Press', '1869-01-01', 'Classic', NULL, 'Historical novel set during Napoleonic Wars.', 150, 252, 1008, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (44, '9780451419439', 'Les Misérables', 'Victor Hugo', 'Signet', '1862-01-01', 'Classic', NULL, 'Sweeping story of justice and redemption.', 157, 110, 440, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (45, '9780143058144', 'Crime and Punishment', 'Fyodor Dostoevsky', 'Penguin Classics', '1866-01-01', 'Classic', NULL, 'Psychological novel about morality.', 66, 219, 1095, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (46, '9780316769488', 'The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', '1951-07-16', 'Classic', NULL, 'Coming-of-age novel.', 99, 167, 501, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (47, '9780060850524', 'Brave New World', 'Aldous Huxley', 'Harper Perennial', '1932-01-01', 'Dystopian', NULL, 'Dystopian vision of future society.', 71, 289, 867, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (48, '9780679723165', 'Lolita', 'Vladimir Nabokov', 'Vintage', '1955-09-15', 'Classic', NULL, 'Controversial novel exploring obsession.', 147, 64, 256, '2025-05-14 21:00:00');
INSERT INTO `book` VALUES (49, '9780547928227', 'The Hobbit', 'J.R.R. Tolkien', 'Mariner Books', '1937-09-21', 'Fantasy', NULL, 'Fantasy adventure preceding LOTR.', 108, 146, 584, '2025-05-14 21:00:00');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `book_id` bigint NOT NULL COMMENT '图书ID',
  `borrow_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '借出时间',
  `due_time` datetime NULL DEFAULT NULL COMMENT '应还时间',
  `return_time` datetime NULL DEFAULT NULL COMMENT '归还时间',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '0 借出 1 归还',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_borrow_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_borrow_book`(`book_id` ASC) USING BTREE,
  CONSTRAINT `fk_borrow_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_borrow_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1923635828624449539 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '借阅流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (1, 49, 7, '2025-04-25 16:41:00', '2025-05-25 16:41:00', NULL, 0);
INSERT INTO `borrow` VALUES (2, 20, 3, '2025-03-14 22:00:00', '2025-03-28 22:00:00', NULL, 0);
INSERT INTO `borrow` VALUES (3, 38, 35, '2025-05-07 14:10:00', '2025-05-21 14:10:00', NULL, 0);
INSERT INTO `borrow` VALUES (4, 32, 21, '2025-03-10 01:50:00', '2025-03-24 01:50:00', NULL, 0);
INSERT INTO `borrow` VALUES (5, 27, 9, '2025-04-11 09:30:00', '2025-05-11 09:30:00', '2025-04-28 11:05:00', 1);
INSERT INTO `borrow` VALUES (1923614485602283522, 51, 5, '2025-05-17 13:40:07', '2025-06-17 13:40:07', '2025-05-17 14:31:59', 1);
INSERT INTO `borrow` VALUES (1923616040585965570, 51, 13, '2025-05-17 13:46:18', NULL, '2025-05-17 14:36:52', 1);
INSERT INTO `borrow` VALUES (1923617092903284737, 51, 1, '2025-05-17 13:50:29', NULL, '2025-05-17 14:36:54', 1);
INSERT INTO `borrow` VALUES (1923626385761312769, 51, 6, '2025-05-17 14:27:24', '2025-06-17 14:27:24', '2025-05-17 14:36:55', 1);
INSERT INTO `borrow` VALUES (1923628731744591873, 51, 23, '2025-05-17 14:36:44', '2025-06-17 14:36:44', '2025-05-17 14:36:49', 1);
INSERT INTO `borrow` VALUES (1923628802326339586, 51, 6, '2025-05-17 14:37:00', '2025-06-17 14:37:00', '2025-05-17 14:37:03', 1);
INSERT INTO `borrow` VALUES (1923629811790458882, 51, 12, '2025-05-17 14:41:01', '2025-06-17 14:41:01', '2025-05-17 14:52:50', 1);
INSERT INTO `borrow` VALUES (1923633568850841601, 51, 42, '2025-05-17 14:55:57', '2025-06-17 14:55:57', '2025-05-17 14:56:04', 1);
INSERT INTO `borrow` VALUES (1923634466222182402, 51, 10, '2025-05-17 14:59:31', '2025-06-17 14:59:31', '2025-05-17 15:00:06', 1);
INSERT INTO `borrow` VALUES (1923634469925752834, 51, 1, '2025-05-17 14:59:32', '2025-06-17 14:59:32', '2025-05-17 14:59:35', 1);
INSERT INTO `borrow` VALUES (1923635819703164930, 51, 5, '2025-05-17 15:04:53', '2025-06-17 15:04:53', NULL, 0);
INSERT INTO `borrow` VALUES (1923635825185120257, 51, 12, '2025-05-17 15:04:55', '2025-06-17 15:04:55', NULL, 0);
INSERT INTO `borrow` VALUES (1923635826871230466, 51, 6, '2025-05-17 15:04:55', '2025-06-17 15:04:55', NULL, 0);
INSERT INTO `borrow` VALUES (1923635828624449538, 51, 42, '2025-05-17 15:04:56', '2025-06-17 15:04:56', '2025-05-17 15:05:00', 1);

-- ----------------------------
-- Table structure for rating
-- ----------------------------
DROP TABLE IF EXISTS `rating`;
CREATE TABLE `rating`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `book_id` bigint NOT NULL COMMENT '图书ID',
  `score` tinyint UNSIGNED NOT NULL COMMENT '评分 1-5',
  `rate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评分时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_rating_ub`(`user_id` ASC, `book_id` ASC) USING BTREE,
  INDEX `fk_rating_book`(`book_id` ASC) USING BTREE,
  CONSTRAINT `fk_rating_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_rating_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `rating_chk_1` CHECK (`score` between 1 and 5)
) ENGINE = InnoDB AUTO_INCREMENT = 1923635857510621187 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户评分表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rating
-- ----------------------------
INSERT INTO `rating` VALUES (201, 21, 16, 4, '2025-04-04 07:02:08');
INSERT INTO `rating` VALUES (202, 42, 8, 5, '2025-03-10 12:58:17');
INSERT INTO `rating` VALUES (203, 5, 2, 2, '2025-03-01 07:19:28');
INSERT INTO `rating` VALUES (204, 49, 16, 1, '2025-05-04 11:39:08');
INSERT INTO `rating` VALUES (205, 37, 10, 3, '2025-03-23 21:16:36');
INSERT INTO `rating` VALUES (206, 42, 6, 1, '2025-03-04 18:47:09');
INSERT INTO `rating` VALUES (207, 45, 9, 2, '2025-04-25 00:50:46');
INSERT INTO `rating` VALUES (208, 1, 6, 2, '2025-03-20 10:51:24');
INSERT INTO `rating` VALUES (209, 1, 19, 4, '2025-05-13 21:11:35');
INSERT INTO `rating` VALUES (210, 27, 8, 5, '2025-03-09 01:44:11');
INSERT INTO `rating` VALUES (211, 10, 12, 1, '2025-05-01 00:44:55');
INSERT INTO `rating` VALUES (212, 40, 3, 5, '2025-04-08 01:38:04');
INSERT INTO `rating` VALUES (213, 8, 11, 2, '2025-04-23 21:43:03');
INSERT INTO `rating` VALUES (214, 45, 3, 3, '2025-03-25 19:16:54');
INSERT INTO `rating` VALUES (215, 21, 2, 3, '2025-03-24 03:57:05');
INSERT INTO `rating` VALUES (216, 38, 14, 4, '2025-03-12 22:32:33');
INSERT INTO `rating` VALUES (217, 48, 20, 1, '2025-04-30 19:28:38');
INSERT INTO `rating` VALUES (218, 17, 5, 5, '2025-02-23 07:55:34');
INSERT INTO `rating` VALUES (219, 7, 12, 2, '2025-04-19 18:50:08');
INSERT INTO `rating` VALUES (220, 3, 20, 1, '2025-05-02 01:06:22');
INSERT INTO `rating` VALUES (221, 1, 7, 3, '2025-02-25 09:19:44');
INSERT INTO `rating` VALUES (222, 23, 5, 1, '2025-02-19 10:49:12');
INSERT INTO `rating` VALUES (223, 17, 11, 5, '2025-04-29 23:18:21');
INSERT INTO `rating` VALUES (224, 38, 10, 5, '2025-04-25 03:23:25');
INSERT INTO `rating` VALUES (225, 9, 3, 2, '2025-03-23 02:12:05');
INSERT INTO `rating` VALUES (226, 6, 18, 3, '2025-05-08 13:07:47');
INSERT INTO `rating` VALUES (227, 3, 10, 5, '2025-02-22 12:52:27');
INSERT INTO `rating` VALUES (228, 17, 16, 4, '2025-04-24 08:13:35');
INSERT INTO `rating` VALUES (229, 38, 3, 5, '2025-03-20 15:45:12');
INSERT INTO `rating` VALUES (230, 34, 17, 3, '2025-03-01 02:04:57');
INSERT INTO `rating` VALUES (231, 10, 11, 1, '2025-03-31 20:58:21');
INSERT INTO `rating` VALUES (232, 27, 11, 4, '2025-03-20 08:35:13');
INSERT INTO `rating` VALUES (233, 10, 14, 2, '2025-03-03 10:38:08');
INSERT INTO `rating` VALUES (234, 22, 7, 2, '2025-04-11 06:58:31');
INSERT INTO `rating` VALUES (235, 47, 10, 2, '2025-03-28 15:18:44');
INSERT INTO `rating` VALUES (236, 5, 18, 1, '2025-02-12 15:20:32');
INSERT INTO `rating` VALUES (237, 42, 20, 4, '2025-03-02 18:31:59');
INSERT INTO `rating` VALUES (238, 48, 9, 4, '2025-03-03 15:40:41');
INSERT INTO `rating` VALUES (239, 44, 5, 2, '2025-04-24 18:25:21');
INSERT INTO `rating` VALUES (240, 21, 11, 1, '2025-03-16 10:54:30');
INSERT INTO `rating` VALUES (241, 47, 6, 4, '2025-03-21 11:20:24');
INSERT INTO `rating` VALUES (242, 43, 20, 2, '2025-03-19 13:46:01');
INSERT INTO `rating` VALUES (243, 49, 17, 5, '2025-02-22 19:08:31');
INSERT INTO `rating` VALUES (244, 15, 20, 5, '2025-02-21 02:35:34');
INSERT INTO `rating` VALUES (245, 21, 12, 4, '2025-03-30 00:20:51');
INSERT INTO `rating` VALUES (246, 36, 2, 2, '2025-04-02 16:53:06');
INSERT INTO `rating` VALUES (247, 49, 2, 3, '2025-04-18 13:43:57');
INSERT INTO `rating` VALUES (249, 38, 11, 5, '2025-04-06 17:37:23');
INSERT INTO `rating` VALUES (250, 35, 19, 2, '2025-05-03 05:01:51');
INSERT INTO `rating` VALUES (251, 26, 15, 1, '2025-05-12 02:04:32');
INSERT INTO `rating` VALUES (252, 10, 1, 2, '2025-02-18 07:32:34');
INSERT INTO `rating` VALUES (253, 20, 16, 1, '2025-04-17 09:11:31');
INSERT INTO `rating` VALUES (254, 47, 14, 5, '2025-04-11 06:15:33');
INSERT INTO `rating` VALUES (255, 42, 16, 1, '2025-03-17 09:41:38');
INSERT INTO `rating` VALUES (256, 50, 9, 3, '2025-03-29 12:20:15');
INSERT INTO `rating` VALUES (257, 18, 6, 3, '2025-04-30 08:25:16');
INSERT INTO `rating` VALUES (258, 14, 10, 5, '2025-04-05 06:11:52');
INSERT INTO `rating` VALUES (259, 27, 15, 1, '2025-04-01 10:28:59');
INSERT INTO `rating` VALUES (260, 16, 4, 1, '2025-04-09 21:33:45');
INSERT INTO `rating` VALUES (262, 17, 2, 3, '2025-04-30 21:44:08');
INSERT INTO `rating` VALUES (263, 44, 18, 4, '2025-05-04 17:46:56');
INSERT INTO `rating` VALUES (264, 19, 15, 3, '2025-03-12 00:44:51');
INSERT INTO `rating` VALUES (265, 27, 13, 3, '2025-05-12 01:26:57');
INSERT INTO `rating` VALUES (266, 16, 8, 1, '2025-03-20 17:32:49');
INSERT INTO `rating` VALUES (267, 41, 1, 4, '2025-03-25 10:04:39');
INSERT INTO `rating` VALUES (268, 7, 15, 1, '2025-03-03 20:45:12');
INSERT INTO `rating` VALUES (269, 12, 15, 5, '2025-02-23 23:19:20');
INSERT INTO `rating` VALUES (270, 5, 19, 1, '2025-05-11 00:59:00');
INSERT INTO `rating` VALUES (271, 6, 12, 1, '2025-02-23 14:30:11');
INSERT INTO `rating` VALUES (272, 3, 19, 5, '2025-03-02 14:39:32');
INSERT INTO `rating` VALUES (273, 46, 19, 5, '2025-03-31 23:09:29');
INSERT INTO `rating` VALUES (274, 45, 15, 5, '2025-04-28 07:20:55');
INSERT INTO `rating` VALUES (275, 49, 10, 3, '2025-02-26 20:24:09');
INSERT INTO `rating` VALUES (276, 14, 12, 5, '2025-04-12 10:29:06');
INSERT INTO `rating` VALUES (278, 33, 13, 4, '2025-03-24 21:49:13');
INSERT INTO `rating` VALUES (279, 35, 17, 1, '2025-03-16 01:57:16');
INSERT INTO `rating` VALUES (280, 36, 1, 1, '2025-03-06 16:52:04');
INSERT INTO `rating` VALUES (281, 37, 6, 1, '2025-04-27 00:35:46');
INSERT INTO `rating` VALUES (282, 27, 14, 1, '2025-03-09 12:56:56');
INSERT INTO `rating` VALUES (283, 43, 4, 2, '2025-04-25 01:20:09');
INSERT INTO `rating` VALUES (284, 31, 3, 3, '2025-03-01 11:33:26');
INSERT INTO `rating` VALUES (285, 14, 9, 1, '2025-02-24 05:56:25');
INSERT INTO `rating` VALUES (286, 41, 3, 2, '2025-05-10 10:36:03');
INSERT INTO `rating` VALUES (287, 20, 15, 1, '2025-04-15 12:52:58');
INSERT INTO `rating` VALUES (288, 29, 5, 3, '2025-05-12 13:18:06');
INSERT INTO `rating` VALUES (289, 50, 7, 3, '2025-02-20 00:11:42');
INSERT INTO `rating` VALUES (290, 18, 15, 2, '2025-05-13 12:23:12');
INSERT INTO `rating` VALUES (291, 42, 17, 2, '2025-03-25 14:28:40');
INSERT INTO `rating` VALUES (292, 30, 20, 4, '2025-02-26 21:49:13');
INSERT INTO `rating` VALUES (293, 24, 16, 4, '2025-03-29 03:23:28');
INSERT INTO `rating` VALUES (294, 21, 10, 3, '2025-04-30 14:57:42');
INSERT INTO `rating` VALUES (295, 41, 6, 2, '2025-05-12 06:55:24');
INSERT INTO `rating` VALUES (296, 49, 6, 3, '2025-03-28 04:27:59');
INSERT INTO `rating` VALUES (297, 17, 10, 2, '2025-02-24 12:56:26');
INSERT INTO `rating` VALUES (298, 30, 17, 1, '2025-02-25 09:29:49');
INSERT INTO `rating` VALUES (299, 20, 9, 2, '2025-03-06 01:36:29');
INSERT INTO `rating` VALUES (300, 24, 6, 5, '2025-04-04 21:44:43');
INSERT INTO `rating` VALUES (301, 3, 9, 5, '2025-04-08 02:44:13');
INSERT INTO `rating` VALUES (302, 30, 6, 4, '2025-03-05 05:36:47');
INSERT INTO `rating` VALUES (303, 12, 14, 5, '2025-03-11 06:05:10');
INSERT INTO `rating` VALUES (304, 26, 8, 2, '2025-04-17 01:13:00');
INSERT INTO `rating` VALUES (305, 18, 7, 5, '2025-05-12 21:16:14');
INSERT INTO `rating` VALUES (306, 25, 11, 5, '2025-04-30 16:18:03');
INSERT INTO `rating` VALUES (307, 32, 11, 4, '2025-05-11 08:33:53');
INSERT INTO `rating` VALUES (308, 26, 11, 2, '2025-02-28 11:10:35');
INSERT INTO `rating` VALUES (309, 31, 17, 1, '2025-03-19 18:52:28');
INSERT INTO `rating` VALUES (310, 34, 16, 5, '2025-04-01 05:25:41');
INSERT INTO `rating` VALUES (311, 25, 7, 1, '2025-03-09 19:10:14');
INSERT INTO `rating` VALUES (312, 23, 10, 4, '2025-04-02 23:45:45');
INSERT INTO `rating` VALUES (313, 30, 16, 5, '2025-04-10 23:06:05');
INSERT INTO `rating` VALUES (314, 20, 3, 5, '2025-04-06 10:21:17');
INSERT INTO `rating` VALUES (315, 16, 13, 4, '2025-04-28 20:27:47');
INSERT INTO `rating` VALUES (316, 20, 6, 5, '2025-04-02 12:56:27');
INSERT INTO `rating` VALUES (317, 50, 12, 4, '2025-03-12 11:21:05');
INSERT INTO `rating` VALUES (318, 12, 6, 1, '2025-03-18 14:46:33');
INSERT INTO `rating` VALUES (319, 35, 16, 1, '2025-04-02 06:40:37');
INSERT INTO `rating` VALUES (320, 9, 8, 1, '2025-04-02 03:54:37');
INSERT INTO `rating` VALUES (321, 45, 17, 5, '2025-05-04 12:31:21');
INSERT INTO `rating` VALUES (322, 26, 5, 5, '2025-03-09 02:41:32');
INSERT INTO `rating` VALUES (323, 9, 4, 2, '2025-04-10 10:16:16');
INSERT INTO `rating` VALUES (324, 6, 2, 1, '2025-04-24 15:39:12');
INSERT INTO `rating` VALUES (325, 6, 10, 4, '2025-05-11 12:33:43');
INSERT INTO `rating` VALUES (326, 23, 13, 5, '2025-04-29 22:18:06');
INSERT INTO `rating` VALUES (327, 13, 6, 5, '2025-03-02 16:11:39');
INSERT INTO `rating` VALUES (328, 24, 1, 1, '2025-04-19 20:44:00');
INSERT INTO `rating` VALUES (329, 39, 20, 2, '2025-04-16 10:11:41');
INSERT INTO `rating` VALUES (330, 21, 14, 3, '2025-04-20 18:05:17');
INSERT INTO `rating` VALUES (331, 8, 1, 4, '2025-03-01 01:11:59');
INSERT INTO `rating` VALUES (332, 36, 12, 5, '2025-05-11 01:07:43');
INSERT INTO `rating` VALUES (333, 18, 4, 3, '2025-05-06 09:35:45');
INSERT INTO `rating` VALUES (334, 25, 15, 3, '2025-04-25 06:46:34');
INSERT INTO `rating` VALUES (335, 43, 18, 5, '2025-03-26 22:53:14');
INSERT INTO `rating` VALUES (336, 12, 4, 1, '2025-05-13 10:37:19');
INSERT INTO `rating` VALUES (337, 9, 10, 3, '2025-03-15 14:47:45');
INSERT INTO `rating` VALUES (338, 1, 14, 2, '2025-04-04 10:24:22');
INSERT INTO `rating` VALUES (339, 5, 3, 4, '2025-04-28 04:00:26');
INSERT INTO `rating` VALUES (340, 50, 1, 1, '2025-05-05 20:48:37');
INSERT INTO `rating` VALUES (341, 7, 17, 3, '2025-02-22 12:36:01');
INSERT INTO `rating` VALUES (342, 3, 15, 3, '2025-04-21 06:06:39');
INSERT INTO `rating` VALUES (343, 19, 12, 3, '2025-02-26 23:38:09');
INSERT INTO `rating` VALUES (344, 8, 18, 3, '2025-05-04 08:49:13');
INSERT INTO `rating` VALUES (345, 33, 20, 4, '2025-05-08 00:27:29');
INSERT INTO `rating` VALUES (346, 16, 3, 1, '2025-04-20 11:40:39');
INSERT INTO `rating` VALUES (347, 7, 19, 2, '2025-03-04 15:56:27');
INSERT INTO `rating` VALUES (348, 18, 10, 2, '2025-03-13 02:36:38');
INSERT INTO `rating` VALUES (349, 33, 4, 2, '2025-02-20 07:59:22');
INSERT INTO `rating` VALUES (350, 47, 11, 5, '2025-05-02 02:41:16');
INSERT INTO `rating` VALUES (351, 47, 2, 5, '2025-02-16 12:36:58');
INSERT INTO `rating` VALUES (352, 37, 18, 4, '2025-03-05 01:09:47');
INSERT INTO `rating` VALUES (353, 39, 18, 3, '2025-03-13 16:08:20');
INSERT INTO `rating` VALUES (354, 46, 13, 1, '2025-02-15 12:41:01');
INSERT INTO `rating` VALUES (355, 38, 2, 5, '2025-04-02 10:35:20');
INSERT INTO `rating` VALUES (356, 20, 7, 2, '2025-02-26 13:00:13');
INSERT INTO `rating` VALUES (357, 5, 17, 4, '2025-05-12 01:43:53');
INSERT INTO `rating` VALUES (358, 48, 10, 4, '2025-04-04 13:30:56');
INSERT INTO `rating` VALUES (359, 24, 19, 5, '2025-03-18 23:48:55');
INSERT INTO `rating` VALUES (360, 38, 13, 4, '2025-04-20 05:34:07');
INSERT INTO `rating` VALUES (361, 20, 4, 2, '2025-05-09 13:09:17');
INSERT INTO `rating` VALUES (362, 6, 5, 3, '2025-03-17 10:58:08');
INSERT INTO `rating` VALUES (363, 36, 10, 5, '2025-02-26 20:28:51');
INSERT INTO `rating` VALUES (364, 15, 4, 1, '2025-03-10 00:24:19');
INSERT INTO `rating` VALUES (365, 46, 15, 3, '2025-05-14 00:00:05');
INSERT INTO `rating` VALUES (366, 41, 17, 2, '2025-02-19 17:49:23');
INSERT INTO `rating` VALUES (367, 30, 18, 5, '2025-02-25 18:22:33');
INSERT INTO `rating` VALUES (368, 42, 1, 5, '2025-04-29 17:48:50');
INSERT INTO `rating` VALUES (369, 39, 16, 2, '2025-04-07 07:03:01');
INSERT INTO `rating` VALUES (370, 28, 11, 4, '2025-02-24 20:25:08');
INSERT INTO `rating` VALUES (371, 20, 19, 3, '2025-04-11 01:45:20');
INSERT INTO `rating` VALUES (372, 33, 14, 4, '2025-04-11 06:30:23');
INSERT INTO `rating` VALUES (373, 28, 1, 1, '2025-03-13 21:39:17');
INSERT INTO `rating` VALUES (374, 34, 13, 4, '2025-04-09 01:37:02');
INSERT INTO `rating` VALUES (375, 38, 17, 4, '2025-02-22 02:33:21');
INSERT INTO `rating` VALUES (376, 35, 9, 1, '2025-03-11 18:05:17');
INSERT INTO `rating` VALUES (377, 23, 12, 5, '2025-03-26 12:15:36');
INSERT INTO `rating` VALUES (378, 26, 4, 2, '2025-03-02 07:06:09');
INSERT INTO `rating` VALUES (379, 41, 19, 5, '2025-03-14 08:02:29');
INSERT INTO `rating` VALUES (380, 46, 7, 5, '2025-04-06 23:29:35');
INSERT INTO `rating` VALUES (381, 23, 14, 4, '2025-02-26 17:09:03');
INSERT INTO `rating` VALUES (382, 19, 9, 3, '2025-02-19 19:54:06');
INSERT INTO `rating` VALUES (383, 48, 15, 3, '2025-03-17 00:07:09');
INSERT INTO `rating` VALUES (384, 22, 6, 4, '2025-03-24 09:47:10');
INSERT INTO `rating` VALUES (385, 50, 20, 2, '2025-05-01 18:09:30');
INSERT INTO `rating` VALUES (386, 29, 4, 3, '2025-03-13 14:41:53');
INSERT INTO `rating` VALUES (387, 43, 12, 1, '2025-04-18 19:44:59');
INSERT INTO `rating` VALUES (388, 4, 4, 5, '2025-02-21 20:52:32');
INSERT INTO `rating` VALUES (389, 40, 17, 3, '2025-03-29 09:11:16');
INSERT INTO `rating` VALUES (390, 13, 5, 2, '2025-05-07 21:15:43');
INSERT INTO `rating` VALUES (391, 19, 13, 5, '2025-02-13 13:58:22');
INSERT INTO `rating` VALUES (392, 17, 17, 2, '2025-04-15 23:05:47');
INSERT INTO `rating` VALUES (393, 26, 2, 3, '2025-03-04 01:02:30');
INSERT INTO `rating` VALUES (394, 44, 1, 2, '2025-02-18 22:14:28');
INSERT INTO `rating` VALUES (395, 19, 11, 4, '2025-04-09 06:37:02');
INSERT INTO `rating` VALUES (396, 49, 4, 3, '2025-02-21 17:32:21');
INSERT INTO `rating` VALUES (397, 4, 10, 1, '2025-02-19 10:34:22');
INSERT INTO `rating` VALUES (398, 23, 2, 1, '2025-05-11 05:19:21');
INSERT INTO `rating` VALUES (399, 15, 5, 4, '2025-04-14 15:27:30');
INSERT INTO `rating` VALUES (400, 45, 5, 3, '2025-03-02 00:19:48');
INSERT INTO `rating` VALUES (1923635857510621186, 51, 42, 5, '2025-05-17 15:05:02');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名(唯一)',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱(唯一)',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Bcrypt 密码',
  `role` tinyint NOT NULL DEFAULT 2 COMMENT '1=管理员 2=普通',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '1 正常 0 禁用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `uk_user_email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'user001@example.com', 'admin', 1, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (3, 'user003', 'user003@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (4, 'user004', 'user004@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (5, 'user005', 'user005@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (6, 'user006', 'user006@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (7, 'user007', 'user007@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (8, 'user008', 'user008@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (9, 'user009', 'user009@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (10, 'user010', 'user010@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (11, 'user011', 'user011@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (12, 'user012', 'user012@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (13, 'user013', 'user013@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (14, 'user014', 'user014@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (15, 'user015', 'user015@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (16, 'user016', 'user016@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (17, 'user017', 'user017@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (18, 'user018', 'user018@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (19, 'user019', 'user019@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (20, 'user020', 'user020@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (21, 'user021', 'user021@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (22, 'user022', 'user022@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (23, 'user023', 'user023@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (24, 'user024', 'user024@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (25, 'user025', 'user025@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (26, 'user026', 'user026@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (27, 'user027', 'user027@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (28, 'user028', 'user028@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (29, 'user029', 'user029@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (30, 'user030', 'user030@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (31, 'user031', 'user031@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (32, 'user032', 'user032@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (33, 'user033', 'user033@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (34, 'user034', 'user034@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (35, 'user035', 'user035@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (36, 'user036', 'user036@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (37, 'user037', 'user037@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (38, 'user038', 'user038@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (39, 'user039', 'user039@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (40, 'user040', 'user040@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (41, 'user041', 'user041@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (42, 'user042', 'user042@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (43, 'user043', 'user043@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (44, 'user044', 'user044@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (45, 'user045', 'user045@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (46, 'user046', 'user046@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (47, 'user047', 'user047@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (48, 'user048', 'user048@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (49, 'user049', 'user049@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (50, 'user050', 'user050@example.com', '$2a$10$abcdefghijklmnopqrstuv', 2, 1, '2025-05-14 20:00:00');
INSERT INTO `user` VALUES (51, 'wxk', '2561199460@qq.com', 'wxk', 2, 1, '2025-05-16 20:36:13');

SET FOREIGN_KEY_CHECKS = 1;
