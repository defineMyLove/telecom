/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : company

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2014-07-16 12:31:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `chanpin_fenlei`
-- ----------------------------
DROP TABLE IF EXISTS `chanpin_fenlei`;
CREATE TABLE `chanpin_fenlei` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `up_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chanpin_fenlei
-- ----------------------------
INSERT INTO `chanpin_fenlei` VALUES ('0', '产品树', null, '1', '-1');
INSERT INTO `chanpin_fenlei` VALUES ('1', '演播室摄像机', '1403620084223', '2', '0');
INSERT INTO `chanpin_fenlei` VALUES ('2', 'SONY', '1403620121815', '3', '1');
INSERT INTO `chanpin_fenlei` VALUES ('23', '测试树', '1404915278744', null, '0');
INSERT INTO `chanpin_fenlei` VALUES ('45', '测试节点', '1404915410534', null, '23');

-- ----------------------------
-- Table structure for `chanpin_file`
-- ----------------------------
DROP TABLE IF EXISTS `chanpin_file`;
CREATE TABLE `chanpin_file` (
  `id` varchar(32) NOT NULL,
  `path` varchar(200) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `chanpin_id` varchar(32) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of chanpin_file
-- ----------------------------
INSERT INTO `chanpin_file` VALUES ('4a6212fd97d54927b9f424712a9682fa', '/resources/uploadfile/20140710/20140710202533_342.ico', 'gmail.com.ico', '65218e05fb384f06ae8c4da938983981', '0');
INSERT INTO `chanpin_file` VALUES ('527655b4592540b0b2b4482f29d39f53', '/resources/uploadfile/20140710/20140710205314_434.ico', 'gmail.com.ico', 'a0aebad0f20c4a20bb03193a1ff5bcaa', null);
INSERT INTO `chanpin_file` VALUES ('5b64e71bcdb84b15b2a951a362855a86', '/resources/uploadfile/20140710/20140710204036_199.ico', 'gmail.com.ico', 'cc9655f27c374d1b88ee7c6df234fca7', null);
INSERT INTO `chanpin_file` VALUES ('7e3d2cae809349f49a9717b72cd901e4', '/resources/uploadfile/20140716/20140716101251_73.png', 'QQ截图20140715115940.png', 'd4652dd4eddc42db8e0ba68419d6ea07', '0');
INSERT INTO `chanpin_file` VALUES ('9c2918f998f54b11b29ae26384936cc8', '/resources/uploadfile/20140709/20140709190525_848.txt', '注册.txt', '5f1bb3ce42df451c8fba01b69ce467ca', null);

-- ----------------------------
-- Table structure for `chanpin_info`
-- ----------------------------
DROP TABLE IF EXISTS `chanpin_info`;
CREATE TABLE `chanpin_info` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) DEFAULT NULL,
  `atta_name` varchar(50) DEFAULT NULL,
  `atta_path` varchar(200) DEFAULT NULL,
  `des` varchar(32) DEFAULT NULL,
  `fenlei_id` varchar(32) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chanpin_info
-- ----------------------------
INSERT INTO `chanpin_info` VALUES ('65218e05fb384f06ae8c4da938983981', '标题', '注册.txt', '/resources/uploadfile/20140710/20140710202533_954.txt', '内容', '2', null, '1404995133468', null);
INSERT INTO `chanpin_info` VALUES ('a0aebad0f20c4a20bb03193a1ff5bcaa', '标题题目', 'foxmail.ico', '/resources/uploadfile/20140710/20140710205314_870.ico', '佳节', '2', null, '1404996794243', '内容<br />');
INSERT INTO `chanpin_info` VALUES ('cc9655f27c374d1b88ee7c6df234fca7', '内容测试', '163.com.ico', '/resources/uploadfile/20140710/20140710204036_231.ico', '简介', '2', null, '1404996036053', null);
INSERT INTO `chanpin_info` VALUES ('d4652dd4eddc42db8e0ba68419d6ea07', '产品测试工工', 'UpLoadController.java', '/resources/uploadfile/20140716/20140716101251_682.java', '产品测试工工', '48b4b01a633749f1ac170c4d72f2f51d', null, '1405476770896', '产品测试工工<br />');

-- ----------------------------
-- Table structure for `fangan_fenlei`
-- ----------------------------
DROP TABLE IF EXISTS `fangan_fenlei`;
CREATE TABLE `fangan_fenlei` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fangan_fenlei
-- ----------------------------
INSERT INTO `fangan_fenlei` VALUES ('0a12390e95a64c688da8ffa85b0907f2', '信号传输系统', '1404055710888', '6');
INSERT INTO `fangan_fenlei` VALUES ('290199fb0ade40f087b454534e8d7240', '矩阵及信号调度系统', '1404055468897', '4');
INSERT INTO `fangan_fenlei` VALUES ('51b77cb9f6e14b388a30830b89d08a06', '数字电视前端DVB系统', '1404055693349', '5');
INSERT INTO `fangan_fenlei` VALUES ('59a788aa9f374fbfbd757f6b21d02bff', '演播室视音频系统', '1403537783868', '1');
INSERT INTO `fangan_fenlei` VALUES ('9654e4a395de432cbfbfb113471754db', '转播车系统', '1403537801759', '2');
INSERT INTO `fangan_fenlei` VALUES ('a97fad9a64ce48489525825b0a612a1c', '播出及总控系统', '1404055415131', '3');

-- ----------------------------
-- Table structure for `fangan_info`
-- ----------------------------
DROP TABLE IF EXISTS `fangan_info`;
CREATE TABLE `fangan_info` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) DEFAULT NULL,
  `pic_path` varchar(200) DEFAULT NULL,
  `content` text,
  `fenlei_id` varchar(32) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fangan_info
-- ----------------------------
INSERT INTO `fangan_info` VALUES ('67a1c14276d44d0fb120895e169737f9', ' 转播车系统', '/resources/uploadfile/20140629/20140629234554_116.jpg', '<a href=\"http://ldt.cn/sshow.asp?id=384\" target=\"_blank\">转播车系统</a>', '9654e4a395de432cbfbfb113471754db', null, '1404056754928');
INSERT INTO `fangan_info` VALUES ('8e6dd35f342b410da0d016099ccb7fbd', '演播室视音频系统', '/resources/uploadfile/20140704/20140704002406_83.png', '<a href=\"http://ldt.cn/sshow.asp?id=383\" target=\"_blank\">演播室视音频系统</a>', '59a788aa9f374fbfbd757f6b21d02bff', null, '1404055886279');
INSERT INTO `fangan_info` VALUES ('d22c2f1448f94c588a85e2b69625c475', '演播室201视', '/resources/uploadfile/20140704/20140704002558_613.jpg', '演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视演播室201视', '59a788aa9f374fbfbd757f6b21d02bff', null, '1404404758519');
INSERT INTO `fangan_info` VALUES ('d5e2e47774d0480487e550be37dbfdc2', '演播室222视听', '/resources/uploadfile/20140704/20140704002634_716.png', '演播室222视听演播室222视听演播室222视听演播室222视听<br />', '59a788aa9f374fbfbd757f6b21d02bff', null, '1404404794362');

-- ----------------------------
-- Table structure for `mes_board`
-- ----------------------------
DROP TABLE IF EXISTS `mes_board`;
CREATE TABLE `mes_board` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `user_name` varchar(32) DEFAULT NULL,
  `work_name` varchar(50) DEFAULT NULL,
  `company_nam` varchar(100) DEFAULT NULL,
  `company_address` varchar(200) DEFAULT NULL,
  `company_youbian` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `company_tel` varchar(20) DEFAULT NULL,
  `company_chuanzhen` varchar(50) DEFAULT NULL,
  `content` text,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mes_board
-- ----------------------------
INSERT INTO `mes_board` VALUES ('9a88ef1ff6b941c88b8017cea5d12517', '您的姓名', '您的职务', '公司名称', '公司地址', '公司邮编', '电子邮件', '公司电话', '电子传真', '么帮助', null);

-- ----------------------------
-- Table structure for `new_info`
-- ----------------------------
DROP TABLE IF EXISTS `new_info`;
CREATE TABLE `new_info` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `query_count` int(11) DEFAULT NULL COMMENT '浏览次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of new_info
-- ----------------------------
INSERT INTO `new_info` VALUES ('34190e8601e54189a57ef193e5d0756b', '李克强分别会见缅甸总统吴登盛和印度副总统安萨里', '&nbsp;新华网北京6月28日电（记者郝亚琳）国务院总理李克强28日下午在人民大会堂分别会见缅甸总统吴登盛和印度副总统安萨里，欢迎他们访华并出席和平共处五项原则发表60周年纪念活动。<p>&nbsp;&nbsp;&nbsp;&nbsp;在会见吴登盛时，李克强表示，中缅友谊源远流长，在和平共处五项原则基础上，两国睦邻友好关系深入发展。昨天习近平主席同总统先生举行了友好和富有成果的会谈。中方愿同缅方巩固政治互信，密切高层交往，结合两国发展战略和产业规划，发挥资源、资金、市场等互补优势，拓展合作领域，提升合作水平。双方要确保油气管道、矿业开发、港口建设等重大合作项目顺利实施和安全运营。中方愿同各方推进孟中印缅经济走廊建设，在丝绸之路经济带和21世纪海上丝绸之路框架下加强区域互联互通，实现互利共赢。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;李克强指出，东盟是中国周边外交的优先方向。缅甸今年担任东盟轮值主席国，中方将积极支持和配合缅方办好东亚领导人系列会议和中国－东盟文化交流年等活动，打造中国－东盟命运共同体，落实中国－东盟达成的两点政治共识和七个重要领域合作，推动双方关系持续健康平稳发展。希望缅方为此发挥积极作用。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;吴登盛表示，中国是缅甸的友好邻邦和可靠朋友，长期以来给予缅甸宝贵支持。近年来，两国睦邻友好关系不断深化，有力促进了缅甸经济社会发展。中国的发展为缅甸和亚洲带来了新的机遇。缅方愿与中方一道，继承和发扬和平共处五项原则，落实好两国合作协议，扩大经贸、能源、基础设施等领域合作。缅方将积极参与并推进孟中印缅经济走廊建设，惠及四国和本地区。缅方愿为东盟－中国关系发展发挥建设性作用。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;在会见安萨里时，李克强表示，上个月我同贵国新任总理莫迪通电话时，双方一致同意携手推动中印战略合作伙伴关系深入发展。中印作为当今世界两个最具活力的新兴市场国家，发展战略契合，合作潜力巨大。我们愿同印方一道，将中印两大市场更好对接，抓住彼此发展带来的重要机遇，不仅助推两国经济发展，而且形成以中印为双引擎的经济增长区，促进地区经济蓬勃有力发展。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;李克强指出，中国的发展需要和平稳定的地区和国际环境。中方愿同印方通过和平谈判妥善解决边界问题，共同维护边境地区的和平与安宁。新形势下，中方愿同包括印度、缅甸在内的各方弘扬并发展和平共处五项原则，共同促进地区乃至世界的和平发展与繁荣稳定。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;安萨里表示，印中是好朋友、好邻居，作为两大发展中国家，两国有着相近的历史文化和共同的发展目标。双方加强合作不仅有利于两国，也有利于亚洲和世界。印度新一届政府高度重视并坚定致力于发展稳定、强劲的印中关系，愿同中方在和平共处五项原则基础上扩大政治、经贸、人文等领域交流与合作，照顾彼此重大关切，推动两国关系不断迈上新台阶。</p>', '1404053681752', null);
INSERT INTO `new_info` VALUES ('3dfe2039a0224d0183b28f29591b4209', '外媒：习近平称中国人无称王称霸的基因', '<p class=\"cont-detail fz-14 cor-333\">                <span style=\"display:block;text-indent:2em;\" class=\"\">核心提示：习近平说：“中国不认同‘国强必霸论’，中国人的血脉中没有称王称霸、穷兵黩武的基因。”</span>               </p>                                                                                                                   <p style=\"text-align:center;\"><a href=\"http://china.cankaoxiaoxi.com/2014/0629/407885_2.shtml\" target=\"_self\"><img src=\"http://upload.cankaoxiaoxi.com/2014/0629/1404010586331.jpg\" /></a></p><p style=\"text-align:center;font-size:12px;line-height:12px;padding-bottom:0;\"><a href=\"http://china.cankaoxiaoxi.com/2014/0629/407885_2.shtml\" target=\"_self\" style=\"color:#f00;\">点击图片进入下一页</a></p><p style=\"font-size:12px;text-indent:0px;text-align:left;\">&nbsp; &nbsp; 6月28日，国家主席习近平在北京人民大会堂出席和平共处五项原则发表60周年纪念大会并发表主旨讲话。新华社记者 李学仁 摄</p><p><strong>参考消息网6月29日报道</strong> 外媒称，中国国家主席习近平表示，无论变得多么强大，中国永远都不会称霸。</p><p>据美联社6月28日报道，习近平是在接待来访的印度和缅甸领导人时讲这番话的。近来，中国陷入与包括印度在内的多个邻国的领土争端，而且正在对美国的地区实力发起挑战。</p><p>报道称，28日，习近平和缅甸总统吴登盛、印度副总统哈米德·安萨里一道举行活动，纪念三国共同发表和平共处五项原则60周年。</p><p>报道说，习近平同时呼吁建设“亚太安全合作新架构”，他此前曾表示这样的安全架构将吸纳俄罗斯和伊朗。但美国会被排除在这一架构外。</p><p>另据英国广播公司网站6月28日报道，<strong>中国国家主席习近平28日表示，在当今国际关系中，公平正义“还远远没有实现”，并称无论中国多么强大，也“永远不称霸”。</strong></p><p><strong>习近平28日傍晚在和平共处五项原则发表六十周年纪念大会上说：“中国不认同‘国强必霸论’，中国人的血脉中没有称王称霸、穷兵黩武的基因。”</strong></p><p style=\"text-align:center;\"><a href=\"http://china.cankaoxiaoxi.com/2014/0629/407885_2.shtml\" target=\"_self\"><img src=\"http://upload.cankaoxiaoxi.com/2014/0629/1404010756796.jpg\" /></a></p><p style=\"text-align:center;font-size:12px;line-height:12px;padding-bottom:0;\"><a href=\"http://china.cankaoxiaoxi.com/2014/0629/407885_2.shtml\" target=\"_self\" style=\"color:#f00;\">点击图片进入下一页</a></p><p style=\"font-size:12px;text-indent:0px;text-align:left;\">&nbsp; &nbsp; 6月28日，国家主席习近平在北京人民大会堂出席和平共处五项原则发表60周年纪念大会并发表主旨讲话。图为习近平与缅甸总统吴登盛、印度副总统安萨里共同会见出席大会的中缅印三国部分代表。新华社记者 李学仁 摄</p><p>印度副总统安萨里与缅甸总统吴登盛也在当天的活动中发表讲话，强调了中印缅三国的热爱和平的共同价值观。</p><p>报道称，中国政府还在此次活动期间宣布设立“和平共处五项原则友谊奖”与“和平共处五项原则卓越奖学金”。</p><p>前一日，习近平在北京会见军方代表时，要求军方加强边防安全，保卫中国的领土与水域。</p>', '1404053550973', null);
INSERT INTO `new_info` VALUES ('626255e3dbea4eea9e61d81aa4d2d7e1', '入', '入<br />', '1404999105078', null);

-- ----------------------------
-- Table structure for `shipin_info`
-- ----------------------------
DROP TABLE IF EXISTS `shipin_info`;
CREATE TABLE `shipin_info` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `des` text COMMENT '内容',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `query_count` int(11) DEFAULT NULL COMMENT '浏览次数',
  `file_path` varchar(200) DEFAULT NULL,
  `pic_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shipin_info
-- ----------------------------
INSERT INTO `shipin_info` VALUES ('4aa01ff9344c407eb1cf8991a39a6cf2', '视频测试', null, '1404999886984', null, '/resources/uploadfile/20140710/20140710214447_73.flv', '/resources/uploadfile/20140710/20140710214447_283.ico');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) DEFAULT NULL,
  `pwd` varchar(32) DEFAULT NULL,
  `userId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '管理员', 'admin', 'admin');

-- ----------------------------
-- Table structure for `tech_zhishi`
-- ----------------------------
DROP TABLE IF EXISTS `tech_zhishi`;
CREATE TABLE `tech_zhishi` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `query_count` int(11) DEFAULT NULL COMMENT '浏览次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tech_zhishi
-- ----------------------------
INSERT INTO `tech_zhishi` VALUES ('30be25af15484ad2849d262caf4d42ab', '9090', '909090<br />', '1405400566611', null);
INSERT INTO `tech_zhishi` VALUES ('39db81e67183405ea6f843c94bb66abc', '78787', '78787<br />', '1405400550315', null);
INSERT INTO `tech_zhishi` VALUES ('465c8ed9be1b4808a4fd8af08a5c0cf2', '5656', '56565<br />', '1405400522295', null);
INSERT INTO `tech_zhishi` VALUES ('71c971cdcb3940c6abf4fccc35b05a56', 'uiuiuiuiu', 'uiuiuiu<br />', '1405400581107', null);
INSERT INTO `tech_zhishi` VALUES ('7eacdb7ccc9348a28e4f51c9fa7a87d3', 'uiuiu', 'iuiuiuiu<br />', '1405400593174', null);
INSERT INTO `tech_zhishi` VALUES ('83064f02d0e742329e015c5cde14d483', 'dfdf', 'dfdf<br />', '1403531392358', null);
INSERT INTO `tech_zhishi` VALUES ('8fc3da676b5545978f596b93395b2d96', 'dfdf', 'dfdfdf<br />', '1403531690981', null);
INSERT INTO `tech_zhishi` VALUES ('b55eb04aaf494c6fab5197ffa31acc53', 'sdsd', 'sdsdsd<br />', '1403435428049', null);
INSERT INTO `tech_zhishi` VALUES ('bdb8c4fa7bac4b38a49bda814ac2e44c', '知识库标题', '内容<br />', '1404968047320', null);
INSERT INTO `tech_zhishi` VALUES ('dbc7cc7950104f2eabd49870942ce949', 'uiuiu', 'iuiuiuiu<br />', '1405400593633', null);
INSERT INTO `tech_zhishi` VALUES ('e4f89d867c674e0b919c9116ec4f272f', 'dfdf', 'dfdfdfdfdfdfd<br />', '1403531315930', null);
INSERT INTO `tech_zhishi` VALUES ('f66a5923773343c4880f6123f80ff00b', '56565', '56565<br />', '1405400529519', null);

-- ----------------------------
-- Table structure for `tech_zhuanlan`
-- ----------------------------
DROP TABLE IF EXISTS `tech_zhuanlan`;
CREATE TABLE `tech_zhuanlan` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `query_count` int(11) DEFAULT NULL COMMENT '浏览次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tech_zhuanlan
-- ----------------------------
INSERT INTO `tech_zhuanlan` VALUES ('cd4c547f383442d1b2f3c3818aea4c30', '论文专栏专栏', '论文专栏专栏<br />', '1404914443242', null);

-- ----------------------------
-- Table structure for `work_info`
-- ----------------------------
DROP TABLE IF EXISTS `work_info`;
CREATE TABLE `work_info` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `query_count` int(11) DEFAULT NULL COMMENT '浏览次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_info
-- ----------------------------
INSERT INTO `work_info` VALUES ('2cd303841b8c4ef994b41f09a41313a4', '机会测试', '机会<br />', '1403450178311', null);
INSERT INTO `work_info` VALUES ('2fe8a6a4cedc4b82bfe17a6a05299735', '机会测试2', '机会测试3<br />', '1404056787191', null);

-- ----------------------------
-- Table structure for `yeji_fenlei`
-- ----------------------------
DROP TABLE IF EXISTS `yeji_fenlei`;
CREATE TABLE `yeji_fenlei` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `type` smallint(6) DEFAULT NULL COMMENT '(1:文字2:图片)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yeji_fenlei
-- ----------------------------
INSERT INTO `yeji_fenlei` VALUES ('3e7684d1df6d4fe8a00686f21c879b51', '数字电视前端DVB系统', '1404050406832', '3', '1');
INSERT INTO `yeji_fenlei` VALUES ('98391404ca3946e083c85abba86f1898', '典型工程案例', '1404050430790', '4', '2');
INSERT INTO `yeji_fenlei` VALUES ('995cbe92398147ecb94235afbfdc61c6', '广播电视播出总控系统', '1404050259052', '2', '1');
INSERT INTO `yeji_fenlei` VALUES ('e0e6694a415f41149debdb8ced08a128', '演播室及转播车系统', '1403528551132', '1', '1');

-- ----------------------------
-- Table structure for `yeji_file`
-- ----------------------------
DROP TABLE IF EXISTS `yeji_file`;
CREATE TABLE `yeji_file` (
  `id` varchar(32) NOT NULL,
  `path` varchar(200) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `yeji_id` varchar(32) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '[0:首页图片]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of yeji_file
-- ----------------------------
INSERT INTO `yeji_file` VALUES ('cff98963d430422bae5eb43f37b06852', '/resources/uploadfile/20140714/20140714140708_914.ico', '126.com.ico', '8d4f4881b3cc44059b9aa2c746b8d0f1', '0');
INSERT INTO `yeji_file` VALUES ('ef5fb292e6de461c8814cbec3783a661', '/resources/uploadfile/20140714/20140714140708_501.ico', 'gmail.com.ico', '8d4f4881b3cc44059b9aa2c746b8d0f1', '1');

-- ----------------------------
-- Table structure for `yeji_info`
-- ----------------------------
DROP TABLE IF EXISTS `yeji_info`;
CREATE TABLE `yeji_info` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) DEFAULT NULL,
  `content` text,
  `fenlei_id` varchar(32) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yeji_info
-- ----------------------------
INSERT INTO `yeji_info` VALUES ('0a9a1a9b355e465abb5db3c30b60c663', '远景东方4迅道演播室', '远景东方4迅道演播室', 'e0e6694a415f41149debdb8ced08a128', null, '1404050984171');
INSERT INTO `yeji_info` VALUES ('0b4f32e7177f4b3f90e62e90ee077aa1', '厦门广播电视集团80平米虚拟演播室', '厦门广播电视集团80平米虚拟演播室', 'e0e6694a415f41149debdb8ced08a128', null, '1404051157676');
INSERT INTO `yeji_info` VALUES ('1e5578d2be204c6e99ebdd2052e36767', '福建电视台7频道主备切换台播出系统', '福建电视台7频道主备切换台播出系统', '995cbe92398147ecb94235afbfdc61c6', null, '1404052727681');
INSERT INTO `yeji_info` VALUES ('2b27f0ac5c314baf9527fe6642febc2d', '江苏省广播电视总台新大楼13频道播出系统及总控系统', '江苏省广播电视总台新大楼13频道播出系统及总控系统', '995cbe92398147ecb94235afbfdc61c6', null, '1404053388200');
INSERT INTO `yeji_info` VALUES ('2dda7a9bbce64f04abf7c26482e094f2', '山东电视台齐鲁频道800平米9讯道高清演播室', '山东电视台齐鲁频道800平米9讯道高清演播室', 'e0e6694a415f41149debdb8ced08a128', null, '1404050957154');
INSERT INTO `yeji_info` VALUES ('3facf3621358476b9593e8b891c79147', '宁波电视台5频道主备切换台播出系统', '宁波电视台5频道主备切换台播出系统', '995cbe92398147ecb94235afbfdc61c6', null, '1404052836113');
INSERT INTO `yeji_info` VALUES ('455455566ca04b8bb55ffebd9d18cf4b', '泉州电视台200平4讯道演播室系统', '泉州电视台200平4讯道演播室系统', 'e0e6694a415f41149debdb8ced08a128', null, '1404051171773');
INSERT INTO `yeji_info` VALUES ('8d4f4881b3cc44059b9aa2c746b8d0f1', '湖北电视经济频道数字卫星转播车卫星系统、视频系统设备及集成', '湖北电视经济频道数字卫星转播车卫星系统、视频系统设备及集成', 'e0e6694a415f41149debdb8ced08a128', null, '1404051270386');
INSERT INTO `yeji_info` VALUES ('c9fe56ae47f94e988a0a443cab37e3f0', '湖北省广播电视总台400平米5讯道综艺演播室', '湖北省广播电视总台400平米5讯道综艺演播室', 'e0e6694a415f41149debdb8ced08a128', null, '1404050941682');
INSERT INTO `yeji_info` VALUES ('e1e6e60add554ba7a49039b00ca06ad7', '北京电视台新大楼建设测试监视仪器采购项目', '北京电视台新大楼建设测试监视仪器采购项目', 'e0e6694a415f41149debdb8ced08a128', null, '1404051024961');
INSERT INTO `yeji_info` VALUES ('e6ca36518fb84f80b48280ee59e2f5ab', '山东电视台齐鲁频道2个100平米新闻演播室', '山东电视台齐鲁频道2个100平米新闻演播室', 'e0e6694a415f41149debdb8ced08a128', null, '1404050970799');
INSERT INTO `yeji_info` VALUES ('efc7baa9e84449c488415ab2286650f0', '湖北电视台经视频道DSNG转播车', '湖北电视台经视频道DSNG转播车', 'e0e6694a415f41149debdb8ced08a128', null, '1404051256234');
INSERT INTO `yeji_info` VALUES ('fab01e5d8bf7416a8df1fdf51ccdae2b', '贵州电视台家有购物频道四讯道数字演播室', '贵州电视台家有购物频道四讯道数字演播室', 'e0e6694a415f41149debdb8ced08a128', null, '1404051011195');
INSERT INTO `yeji_info` VALUES ('fb5ebfddba9d40bfbf4161925ccdcdaa', '山东电视台250平米6讯道综艺演播室', '山东电视台250平米6讯道综艺演播室', 'e0e6694a415f41149debdb8ced08a128', null, '1404051121682');
