<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${path }/static/css/common.css"/>
    <style type="text/css">
        .div_mainbody_style {
            background-color: #FFFFFF;
            border-color: #89C1E0;
            border-style: solid;
            border-width: 1px;
            margin: 26px 10px 10px 10px;
            padding-bottom: 5px;
            padding-top: 5px;
        }

        .detail_body {
            padding-bottom: 10px;
            padding-top: 10px;
            vertical-align: baseline;
            width: 95%;
        }

        .detail_title {
            color: #000066;
            font-family: '黑体';
            font-size: 26px;
            padding-bottom: 10px;
            padding-top: 10px;
            text-align: center;
        }

        .detail_rec {
            padding-top: 10px;
            text-align: center;
        }

        .detail_nr {
            white-space: normal;
            width: 750px;
            word-wrap: break-word;
        }

        .detail_bottom {
            padding-top: 10px;
            text-align: left;
        }

        .content {
            text-align: left;
        }
    </style>
</head>
<body >
<div  id="main"  align="center">
    <div class="detail_body">
        <div class="detail_title"><label id="_id2">${notice.title}</label>
        </div>
        <div class="detail_rec">
            <p>
                <strong>发布人：</strong><label id="_id4">${LOGIN_USER.name}</label>&nbsp;&nbsp;
                <strong>发布时间：</strong><label id="_id6"><fmt:formatDate value="${notice.createTime}"
                                                                       pattern="yyyy-MM-dd"/></label>&nbsp;&nbsp;
                <dic:getDictionary var="dictionary" groupCode="noticeLevel" dicCode="${notice.level}"/>&nbsp;&nbsp;
                <strong>等级：</strong><label id="_id4">${dictionary.dtName}</label>
            </p>
            <hr style="color: #CCCCCC">
        </div>
        <div class="content">
            ${notice.content}
        </div>
        <div class="detail_bottom">
            <table border="0" cellpadding="0" cellspacing="0">
                <c:forEach items="${iDList}" var="noticeFile" varStatus="s">
                    <c:choose>
                        <c:when test="${s.index == 0}">
                            <tr>
                                <td style="font:italic bold 12px/30px arial,sans-serif; line-height: 20px;">
                                    附件:&nbsp;</td>
                                <td align="left"><a
                                        href="${path}/download/noticeFile/${noticeFile.fileId}">${noticeFile.fileName}</a>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td style="font:italic bold 12px/30px arial,sans-serif; line-height: 20px;">
                                    &nbsp;</td>
                                <td align="left"><a
                                        href="${path}/download/noticeFile/${noticeFile.fileId}">${noticeFile.fileName}</a>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>