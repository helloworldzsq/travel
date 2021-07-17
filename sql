/*
 Navicat Premium Data Transfer

 Source Server         : mybatis
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : travel

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 17/07/2021 15:31:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for budget
-- ----------------------------
DROP TABLE IF EXISTS `budget`;
CREATE TABLE `budget`  (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` date NOT NULL,
  `cost` int(255) NOT NULL,
  `people` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` bigint(100) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `budget_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `program` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of budget
-- ----------------------------
INSERT INTO `budget` VALUES (8, '麻辣烫', '2021-07-06', 100, 'zsq,abc', 7);
INSERT INTO `budget` VALUES (9, '麻辣烫', '2021-07-08', 200, 'zsq,曹鹏', 23);
INSERT INTO `budget` VALUES (10, '螺蛳粉', '2021-07-08', 200, '曹鹏,张召旺', 23);
INSERT INTO `budget` VALUES (11, '烤鱼', '2021-07-09', 100, 'zsq,曹鹏', 7);
INSERT INTO `budget` VALUES (12, '烧烤', '2021-07-05', 200, 'zsq,曹鹏', 7);
INSERT INTO `budget` VALUES (15, '苏州之行', '2021-07-13', 300, 'zsq,曹鹏,dz', 22);
INSERT INTO `budget` VALUES (16, '苏中中学', '2021-07-13', 2000, 'zsq,dz', 22);
INSERT INTO `budget` VALUES (17, '金鸡湖', '2021-07-05', 300, '曹鹏,dz', 22);
INSERT INTO `budget` VALUES (18, '苏州中心', '2021-07-14', 200, '曹鹏,abc', 7);
INSERT INTO `budget` VALUES (19, '金鸡湖', '2021-07-14', 400, 'zsq,曹鹏', 7);

/*
 Navicat Premium Data Transfer

 Source Server         : mybatis
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : travel

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 17/07/2021 15:31:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for program
-- ----------------------------
DROP TABLE IF EXISTS `program`;
CREATE TABLE `program`  (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` datetime(0) NULL,
  `end_time` datetime(0) NULL,
  `budget` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_num` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `picture_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `joined` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wanted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `refused` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `uid` bigint(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `program_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of program
-- ----------------------------
INSERT INTO `program` VALUES (7, 'suzhou1', '苏州', '2021-08-02 16:00:00', '2021-08-11 16:00:00', '300', '8', 'image/7月/pg-5d981bc4a3f94266a88c405c364ca718.jpg', 0000000001, '1 4 5 ', '4 5 6 ', '6', '这次在苏州玩了***三天两夜\\***。苏州的大部分景点都集中在姑苏老城区，我住在临顿路的地铁口附近，所以差不多的景区都超级近的。但是苏州正好在***修地铁，\\***所以有些些不方便。应该像杭州一样还得修好几年。\r\n\r\n> **出行攻略**\r\n\r\n建议出行采用**公共交通**。**地铁**和**公交车**非常方便。景点之间**离得非常近**，我在临顿路的地铁口附近，过去拙政园那边坐地铁大概是2块钱。值得提醒的是苏州地铁***不可以\\***刷支付宝，所以要备好零钱，也可以下载一个苏州地铁专门的APP叫做**苏e行，**绑定银行卡就可以扫码坐地铁了。公交车的话可以在**支付宝**领取公交卡，非常方便，一般是去**虎丘**这个景点的时候，会做公交车过去，因为虎丘离扎堆的这些景点比较远。\r\n\r\n**姑苏城区不建议打车，**因为他们在修地铁并且姑苏城区是保留了原来的巷子和弄堂的，打车非常非常不方便，有时候等司机的时间和堵车的时间都已经超过了我们步行过去的时间。不过司机师傅都很贴心，他们会尽量从弄堂绕，尽快把我们送到地点。大部分时间都是得停在好远的地方，然后步行进去。**堵车**是真的非常非常严重。\r\n\r\n苏州有两个站，一个是**苏州站**，一个是**苏州北站，**苏州北是高铁站，离主城区会比较远，苏州站在城区，是老火车站，两个站都可以由**地铁**到达，所以非常方便。要是从苏州北出发，记得要提早去，从地铁站往上走之后记得要出房子之后，在往外进高铁站，他们中间的门是**隔开的**，但是在地铁出站后他们就标注了是苏州北站，其实是应该要出去重新进另一扇门，并且安检的。苏州北是个比较小的站，总共四条铁路，所以进站后就不必着急了。苏州站是汽车站和老火车站，因为没有过去那边转车，所以就不叙述了。\r\n\r\n> **住宿攻略**\r\n\r\n我是住在**临顿路的地铁口**附近，这边离**观前街、平江路**都非常近，然后出行也很方便，住的是快捷酒店，**111RMB/晚**，**两张床**，是普通的酒店布置，房间非常小，是私人房子改造的酒店，房间**不是特别干净**那种，因为环境比较差，所以没有拍照啦。但是地理位置是真的不错，价格也可以接受，像我和朋友一起出去，平均下来一晚也才55.5RMB，我觉得跟青年旅社的一张床50RMB相比，和朋友一个房间已经非常划算了。所以还是建议**自带一次性拖鞋和一些小的洗漱用品。**当然啦，如果订了个五星级酒店，就不用担心这些了。然后我的酒店边上，有一个小店，其貌不扬，但是什么都有，所以有时候如果到了之后觉得一些东西（像拖鞋或者洗漱用品之类）不够满意，找一找**小店铺**，可能都能解决哦，那时候我先去了苏宁小店，反而一次性拖鞋也没有，一小包的沐浴露也没有，隔壁小店竟然都有。\r\n\r\n当然也可以选择住在离景区更近的酒店，那边的房价可能会更贵一些。不过景区扎堆，这样玩起来也会比较方便的。我们住的稍稍有些路，所以就是天天坐地铁过去。不过也就不到2km的路 。\r\n\r\n> **门票攻略**\r\n\r\n现在苏州基本上的景区都采取**网上预约**的模式。在**微信**上关注这个公众号 **苏州旅游总入口**\r\n\r\n![img](https://pic2.zhimg.com/80/v2-3216c27363d720cd1abee31dcbe5f7f1_720w.jpg)\r\n\r\n就可以预约了，苏州门票价格在旺季和淡季是**不同的**，大概差10-20RMB左右。\r\n\r\n像拙政园、狮子林、苏州博物馆这些比较热门的景点，一般是**没有预约不能前往**的，尤其是**苏州博物馆**，一定要提前两天预约，到了之后约，基本上能约上都是两三天后了，除非是漏下的一两个名额。拙政园和狮子林这些还可以到场再进行买票。\r\n\r\n微信上买了票之后是可以直接**刷身份证**入园的。并且也是可以买**半价学生票**的，非常方便。去苏州博物馆一定要带上**身份证，**苏博**免门票**，也是预约了就可以刷身份证入园。\r\n\r\n苏州评弹的表演也是可以在这个公众号预约的，还有夜游金鸡湖什么的，余下的就考大家自己去发掘了。\r\n\r\n> **景点攻略**\r\n\r\n1. **拙政园**是苏州园林之母，还是比较值得参观的。然后里面的亭子都可以走进去。\r\n\r\n![img](https://pic4.zhimg.com/80/v2-1be2afa3129508e3fc882d5bae6b5a27_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic2.zhimg.com/80/v2-cd67c3535b4b9942fdeae57878f14515_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic2.zhimg.com/80/v2-1266534f5ae379be7e72695368a5f5a9_720w.jpg)\r\n\r\n**2.观前街**\r\n\r\n一条比较现代的商业街，有超级多的美食可以发掘，我们在苏州吃了6顿，有5顿在观前街，还有很多小吃店，在下面的美食攻略里，我要吹爆观前街。\r\n\r\n**3.平江路历史街区**\r\n\r\n晚上去的，给我的感觉是非常像西塘夜晚的街道，然后有很多手工艺店和小吃店。在平江路买了很多小玩意 ，晚上会有摆夜市的。\r\n\r\n![img](https://pic4.zhimg.com/80/v2-f621ed5d38b07a5a363f6adbc39f2397_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic3.zhimg.com/80/v2-e793742261be2d965c3ea3ea5d4b7cfa_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic4.zhimg.com/80/v2-9dad59cd57d0d6fa286aa875cafe17f7_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic1.zhimg.com/80/v2-0a699d46b9d224590187870f2ce246ac_720w.jpg)\r\n\r\n**4.狮子林**\r\n\r\n玩的应该是假山，超级大一片，都可以上去，绕来绕去，然后夏天有超大的荷花池呀\r\n\r\n![img](https://pic2.zhimg.com/80/v2-610bab2f18796339586904bb7556cfd1_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic4.zhimg.com/80/v2-47db425c364877c923f1db02b63941df_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic1.zhimg.com/80/v2-ee88d7370329ba231a982ad0078a88c8_720w.jpg)\r\n\r\n**5.苏州博物馆**\r\n\r\n 有超级多美美的历史文物，还可以在门口领取讲解的手机或者是关注微信公众号听讲解。苏博的文创产品，每一样，我！都！可！以！实在是太棒了。\r\n\r\n下面放的是我超喜欢的两件。\r\n\r\n![img](https://pic1.zhimg.com/80/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n**6.虎丘**\r\n\r\n这个景点离扎堆的景点有一点点远，但是坐公交车超级方便。是一个小平丘，上面有一座塔，有很多历史祠堂可以看。记得应该也是5a景区。这边可以坐船，还可以坐马拉车。\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n**7.寒山寺**\r\n\r\n姑苏城外寒山寺，夜半钟声到客船。\r\n\r\n千古名寺，不是特别有意思，但是素斋很不错。\r\n\r\n**8.山塘街**\r\n\r\n个人感觉比平江路要美，但是跟西塘超级像也是看夜景。\r\n\r\n> **美食攻略**\r\n\r\n首推**哑巴生煎（观前街、临顿路）**\r\n\r\n超级超级超级超级好吃！汤汁特别特别足，肉馅也超鲜。16RMB/笼，值得一提的是我们去的时候店里只卖**生煎**、**牛肉粉丝汤**和**荠菜馄饨**。然后这一家店晚上关门的非常早哦，而且经常会排超级长的队，所以尽量**早点去吃，避开高峰期**。我们那时候恰好躲过高峰期（饿的太早了），我们吃完出店门的时候，队伍已经排到了店外。他们家的牛肉粉丝汤也特别好喝，超满足。荠菜馄饨的话，作为一个浙江人，平时在家常吃，就没有点，没有试过的朋友也可以试一试，荠菜的味道还是别有风味的。\r\n\r\n![img](https://pic3.zhimg.com/80/v2-e4f91cd4ba975dd40eb5affcbafedea6_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic2.zhimg.com/80/v2-d6c26a38bae86d251ecfd8c09133de01_720w.jpg)\r\n\r\n**暖食**\r\n\r\n大众点评种草的必吃的一家店，在临顿路地铁口附近。做意面、蛋包饭、咖喱饭的。吃了招牌意面，可能不是我的菜吧。但是店主非常好，会提前告知出餐时间，离开的时候会问饭菜是否合胃口。\r\n\r\n![img](https://pic3.zhimg.com/80/v2-181b22eb11327ff043b052c020536422_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic3.zhimg.com/80/v2-fb6dbfd9338aea174d871e4137234122_720w.jpg)\r\n\r\n朱鸿兴面馆（观前街）\r\n\r\n面超级细呀\r\n\r\n![img](https://pic2.zhimg.com/80/v2-36d0e2719d1851e719957a725b8c8e75_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic1.zhimg.com/80/v2-51e7428872a92897a3f9f88f12e22c4c_720w.jpg)\r\n\r\n宽城子市井火锅  的冰粉\r\n\r\n冰粉超级超级好吃，有山楂片、葡萄干、红豆、坚果碎，冰冰凉凉，超级喜欢。\r\n\r\n这家火锅没有纯清汤，不能吃辣的小伙伴注意了。他们家辣锅超级辣（来自一个不太能吃辣的人）\r\n\r\n![img](https://pic4.zhimg.com/80/v2-3f5114c7e14e32f578135c2812f3100f_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic1.zhimg.com/80/v2-24056f65c57da7ce5d943bb43285585c_720w.jpg)\r\n\r\n奈雪の茶、星角冰锅还有酸小七的鱼都推荐呀（都在观前街）\r\n\r\n奈雪の茶一口下去满满的果粒，除了大杯喝不完之外，没有其他任何缺点了\r\n\r\n星角冰锅超级好次，颜值也超级高\r\n\r\n酸小七的番茄鱼真的超治愈，感觉瞬间能再走几百回合\r\n\r\n![img](https://pic1.zhimg.com/80/v2-2065f432197bdf4d2a2b60624316e5b0_720w.jpg)\r\n\r\n> **纪念品攻略**\r\n\r\n苏州最多的是**丝绸**，街上都是卖丝巾的店还有苏绣的扇子，记得货比三家，不同的店家定价一下就差好多。\r\n\r\n管装苏州拼图\r\n\r\n![img](https://pic4.zhimg.com/80/v2-c018fd9131bd5f306a4a05a92be8dff3_720w.jpg)\r\n\r\n小宫灯模型 （苏博文创产品）\r\n\r\n![img](https://pic2.zhimg.com/80/v2-c1b817675f5b8074a12ad8b47049d381_720w.jpg)\r\n\r\n跟我一起去的女孩子一下子就买了苏博两个镯子（可见苏博的产品颜值有多高）\r\n\r\n平江路的一些竹编和木质产品，最推荐的是苏博出口的店子，好多原创的文创产品，种类也很齐，但是价格偏贵。（一些文创产品在苏博淘宝店上也有售哦）', 1);
INSERT INTO `program` VALUES (21, '无锡之行', '无锡', '2021-07-08 16:00:00', '2021-07-13 16:00:00', '1000', '4', 'image/7月/pg-23440d8394474f9b91020111f7dad4f8.jpg', 0000000001, '1 5 6 ', '5 6 ', ' ', '#无锡之行\r\n1.第一天\r\n爬山\r\n2.第二天\r\n逛古镇', 1);
INSERT INTO `program` VALUES (22, '苏州', '苏州', '2021-07-22 16:00:00', '2021-08-18 16:00:00', '1000', '3', 'image/7月/pg-5ac5f37651034e5188369de843250754.jpg', 0000000001, '', '4 3 ', '', '这次在苏州玩了***三天两夜\\***。苏州的大部分景点都集中在姑苏老城区，我住在临顿路的地铁口附近，所以差不多的景区都超级近的。但是苏州正好在***修地铁，\\***所以有些些不方便。应该像杭州一样还得修好几年。\r\n\r\n> **出行攻略**\r\n\r\n建议出行采用**公共交通**。**地铁**和**公交车**非常方便。景点之间**离得非常近**，我在临顿路的地铁口附近，过去拙政园那边坐地铁大概是2块钱。值得提醒的是苏州地铁***不可以\\***刷支付宝，所以要备好零钱，也可以下载一个苏州地铁专门的APP叫做**苏e行，**绑定银行卡就可以扫码坐地铁了。公交车的话可以在**支付宝**领取公交卡，非常方便，一般是去**虎丘**这个景点的时候，会做公交车过去，因为虎丘离扎堆的这些景点比较远。\r\n\r\n**姑苏城区不建议打车，**因为他们在修地铁并且姑苏城区是保留了原来的巷子和弄堂的，打车非常非常不方便，有时候等司机的时间和堵车的时间都已经超过了我们步行过去的时间。不过司机师傅都很贴心，他们会尽量从弄堂绕，尽快把我们送到地点。大部分时间都是得停在好远的地方，然后步行进去。**堵车**是真的非常非常严重。\r\n\r\n苏州有两个站，一个是**苏州站**，一个是**苏州北站，**苏州北是高铁站，离主城区会比较远，苏州站在城区，是老火车站，两个站都可以由**地铁**到达，所以非常方便。要是从苏州北出发，记得要提早去，从地铁站往上走之后记得要出房子之后，在往外进高铁站，他们中间的门是**隔开的**，但是在地铁出站后他们就标注了是苏州北站，其实是应该要出去重新进另一扇门，并且安检的。苏州北是个比较小的站，总共四条铁路，所以进站后就不必着急了。苏州站是汽车站和老火车站，因为没有过去那边转车，所以就不叙述了。\r\n\r\n> **住宿攻略**\r\n\r\n我是住在**临顿路的地铁口**附近，这边离**观前街、平江路**都非常近，然后出行也很方便，住的是快捷酒店，**111RMB/晚**，**两张床**，是普通的酒店布置，房间非常小，是私人房子改造的酒店，房间**不是特别干净**那种，因为环境比较差，所以没有拍照啦。但是地理位置是真的不错，价格也可以接受，像我和朋友一起出去，平均下来一晚也才55.5RMB，我觉得跟青年旅社的一张床50RMB相比，和朋友一个房间已经非常划算了。所以还是建议**自带一次性拖鞋和一些小的洗漱用品。**当然啦，如果订了个五星级酒店，就不用担心这些了。然后我的酒店边上，有一个小店，其貌不扬，但是什么都有，所以有时候如果到了之后觉得一些东西（像拖鞋或者洗漱用品之类）不够满意，找一找**小店铺**，可能都能解决哦，那时候我先去了苏宁小店，反而一次性拖鞋也没有，一小包的沐浴露也没有，隔壁小店竟然都有。\r\n\r\n当然也可以选择住在离景区更近的酒店，那边的房价可能会更贵一些。不过景区扎堆，这样玩起来也会比较方便的。我们住的稍稍有些路，所以就是天天坐地铁过去。不过也就不到2km的路 。\r\n\r\n> **门票攻略**\r\n\r\n现在苏州基本上的景区都采取**网上预约**的模式。在**微信**上关注这个公众号 **苏州旅游总入口**\r\n\r\n![img](https://pic2.zhimg.com/80/v2-3216c27363d720cd1abee31dcbe5f7f1_720w.jpg)\r\n\r\n就可以预约了，苏州门票价格在旺季和淡季是**不同的**，大概差10-20RMB左右。\r\n\r\n像拙政园、狮子林、苏州博物馆这些比较热门的景点，一般是**没有预约不能前往**的，尤其是**苏州博物馆**，一定要提前两天预约，到了之后约，基本上能约上都是两三天后了，除非是漏下的一两个名额。拙政园和狮子林这些还可以到场再进行买票。\r\n\r\n微信上买了票之后是可以直接**刷身份证**入园的。并且也是可以买**半价学生票**的，非常方便。去苏州博物馆一定要带上**身份证，**苏博**免门票**，也是预约了就可以刷身份证入园。\r\n\r\n苏州评弹的表演也是可以在这个公众号预约的，还有夜游金鸡湖什么的，余下的就考大家自己去发掘了。\r\n\r\n> **景点攻略**\r\n\r\n1. **拙政园**是苏州园林之母，还是比较值得参观的。然后里面的亭子都可以走进去。\r\n\r\n![img](https://pic4.zhimg.com/80/v2-1be2afa3129508e3fc882d5bae6b5a27_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic2.zhimg.com/80/v2-cd67c3535b4b9942fdeae57878f14515_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic2.zhimg.com/80/v2-1266534f5ae379be7e72695368a5f5a9_720w.jpg)\r\n\r\n**2.观前街**\r\n\r\n一条比较现代的商业街，有超级多的美食可以发掘，我们在苏州吃了6顿，有5顿在观前街，还有很多小吃店，在下面的美食攻略里，我要吹爆观前街。\r\n\r\n**3.平江路历史街区**\r\n\r\n晚上去的，给我的感觉是非常像西塘夜晚的街道，然后有很多手工艺店和小吃店。在平江路买了很多小玩意 ，晚上会有摆夜市的。\r\n\r\n![img](https://pic4.zhimg.com/80/v2-f621ed5d38b07a5a363f6adbc39f2397_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic3.zhimg.com/80/v2-e793742261be2d965c3ea3ea5d4b7cfa_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic4.zhimg.com/80/v2-9dad59cd57d0d6fa286aa875cafe17f7_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic1.zhimg.com/80/v2-0a699d46b9d224590187870f2ce246ac_720w.jpg)\r\n\r\n**4.狮子林**\r\n\r\n玩的应该是假山，超级大一片，都可以上去，绕来绕去，然后夏天有超大的荷花池呀\r\n\r\n![img](https://pic2.zhimg.com/80/v2-610bab2f18796339586904bb7556cfd1_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic4.zhimg.com/80/v2-47db425c364877c923f1db02b63941df_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic1.zhimg.com/80/v2-ee88d7370329ba231a982ad0078a88c8_720w.jpg)\r\n\r\n**5.苏州博物馆**\r\n\r\n 有超级多美美的历史文物，还可以在门口领取讲解的手机或者是关注微信公众号听讲解。苏博的文创产品，每一样，我！都！可！以！实在是太棒了。\r\n\r\n下面放的是我超喜欢的两件。\r\n\r\n![img](https://pic1.zhimg.com/80/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n**6.虎丘**\r\n\r\n这个景点离扎堆的景点有一点点远，但是坐公交车超级方便。是一个小平丘，上面有一座塔，有很多历史祠堂可以看。记得应该也是5a景区。这边可以坐船，还可以坐马拉车。\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n\r\n\r\n![img](https://gitee.com/infiniteStars/picgoimages/raw/master/v2-fa3af7414bacf6122aa978cf0634bec4_720w.jpg)\r\n\r\n**7.寒山寺**\r\n\r\n姑苏城外寒山寺，夜半钟声到客船。\r\n\r\n千古名寺，不是特别有意思，但是素斋很不错。\r\n\r\n**8.山塘街**\r\n\r\n个人感觉比平江路要美，但是跟西塘超级像也是看夜景。\r\n\r\n> **美食攻略**\r\n\r\n首推**哑巴生煎（观前街、临顿路）**\r\n\r\n超级超级超级超级好吃！汤汁特别特别足，肉馅也超鲜。16RMB/笼，值得一提的是我们去的时候店里只卖**生煎**、**牛肉粉丝汤**和**荠菜馄饨**。然后这一家店晚上关门的非常早哦，而且经常会排超级长的队，所以尽量**早点去吃，避开高峰期**。我们那时候恰好躲过高峰期（饿的太早了），我们吃完出店门的时候，队伍已经排到了店外。他们家的牛肉粉丝汤也特别好喝，超满足。荠菜馄饨的话，作为一个浙江人，平时在家常吃，就没有点，没有试过的朋友也可以试一试，荠菜的味道还是别有风味的。\r\n\r\n![img](https://pic3.zhimg.com/80/v2-e4f91cd4ba975dd40eb5affcbafedea6_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic2.zhimg.com/80/v2-d6c26a38bae86d251ecfd8c09133de01_720w.jpg)\r\n\r\n**暖食**\r\n\r\n大众点评种草的必吃的一家店，在临顿路地铁口附近。做意面、蛋包饭、咖喱饭的。吃了招牌意面，可能不是我的菜吧。但是店主非常好，会提前告知出餐时间，离开的时候会问饭菜是否合胃口。\r\n\r\n![img](https://pic3.zhimg.com/80/v2-181b22eb11327ff043b052c020536422_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic3.zhimg.com/80/v2-fb6dbfd9338aea174d871e4137234122_720w.jpg)\r\n\r\n朱鸿兴面馆（观前街）\r\n\r\n面超级细呀\r\n\r\n![img](https://pic2.zhimg.com/80/v2-36d0e2719d1851e719957a725b8c8e75_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic1.zhimg.com/80/v2-51e7428872a92897a3f9f88f12e22c4c_720w.jpg)\r\n\r\n宽城子市井火锅  的冰粉\r\n\r\n冰粉超级超级好吃，有山楂片、葡萄干、红豆、坚果碎，冰冰凉凉，超级喜欢。\r\n\r\n这家火锅没有纯清汤，不能吃辣的小伙伴注意了。他们家辣锅超级辣（来自一个不太能吃辣的人）\r\n\r\n![img](https://pic4.zhimg.com/80/v2-3f5114c7e14e32f578135c2812f3100f_720w.jpg)\r\n\r\n\r\n\r\n![img](https://pic1.zhimg.com/80/v2-24056f65c57da7ce5d943bb43285585c_720w.jpg)\r\n\r\n奈雪の茶、星角冰锅还有酸小七的鱼都推荐呀（都在观前街）\r\n\r\n奈雪の茶一口下去满满的果粒，除了大杯喝不完之外，没有其他任何缺点了\r\n\r\n星角冰锅超级好次，颜值也超级高\r\n\r\n酸小七的番茄鱼真的超治愈，感觉瞬间能再走几百回合\r\n\r\n![img](https://pic1.zhimg.com/80/v2-2065f432197bdf4d2a2b60624316e5b0_720w.jpg)\r\n\r\n> **纪念品攻略**\r\n\r\n苏州最多的是**丝绸**，街上都是卖丝巾的店还有苏绣的扇子，记得货比三家，不同的店家定价一下就差好多。\r\n\r\n管装苏州拼图\r\n\r\n![img](https://pic4.zhimg.com/80/v2-c018fd9131bd5f306a4a05a92be8dff3_720w.jpg)\r\n\r\n小宫灯模型 （苏博文创产品）\r\n\r\n![img](https://pic2.zhimg.com/80/v2-c1b817675f5b8074a12ad8b47049d381_720w.jpg)\r\n\r\n跟我一起去的女孩子一下子就买了苏博两个镯子（可见苏博的产品颜值有多高）\r\n\r\n平江路的一些竹编和木质产品，最推荐的是苏博出口的店子，好多原创的文创产品，种类也很齐，但是价格偏贵。（一些文创产品在苏博淘宝店上也有售哦）', 1);
INSERT INTO `program` VALUES (23, '南阳', '南阳', '2021-07-08 16:00:00', '2021-07-12 16:00:00', '1000', '2', 'image/7月/pg-4d1be0254c6441f69745ab77e9cc6486.jpg', 0000000001, '1 4 6 ', '4 5 6 ', '5 ', '123', 1);
INSERT INTO `program` VALUES (24, '日本', '日本', '2021-07-08 16:00:00', '2021-07-20 16:00:00', '200', '4', 'image/7月/pg-85d92d40e2f146e8a72d282394797866.jpg', 0000000001, '1 5  ', '5 ', NULL, '日本', 1);

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : mybatis
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : travel

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 17/07/2021 15:31:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', 'zsq', '2748255621@qq.com', '15506175937');
INSERT INTO `user` VALUES (3, 'dz', '123456', 'dz', '2748255621@qq.com', '15506175937');
INSERT INTO `user` VALUES (4, '曹鹏', 'CP123', '曹鹏', '123456789@qq.com', '123456789');
INSERT INTO `user` VALUES (5, 'abc', 'abc', 'abc', '1234567@qq.com', '12345678');
INSERT INTO `user` VALUES (6, 'zzw', '123456', '张召旺', '1234567@qq.com', '12345678');

SET FOREIGN_KEY_CHECKS = 1;
