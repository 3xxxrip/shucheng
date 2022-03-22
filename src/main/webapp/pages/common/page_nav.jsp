<%--
  Created by IntelliJ IDEA.
  User: adimn
  Date: 2022/1/14
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <%--		如果是第一页就--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <%--页码输出开始--%>
    <c:choose>
        <%--		页码在5页以内--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${requestScope.page.pageNo==i}">
                    [${requestScope.page.pageNo}]
                </c:if>
                <c:if test="${requestScope.page.pageNo!=i}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <%--页码在5页以上--%>
        <c:when test="${requestScope.page.pageTotal>5}">

            <%--			第一种情况,在页码不在前面3个以及后面3个之中--%>
<%--            <c:if test="${requestScope.page.pageNo>3}&&${requestScope.page.pageNo<requestScope.page.pageTotal-2}">--%>
<%--                <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">--%>
<%--                    <c:if test="${requestScope.page.pageNo==i}">--%>
<%--                        [${requestScope.page.pageNo}]--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${requestScope.page.pageNo!=i}">--%>
<%--                        <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>--%>
<%--                    </c:if>--%>

<%--                </c:forEach>--%>
<%--            </c:if>--%>
            <c:choose>
            <%--			第二种情况,当前页码在前面3个--%>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${requestScope.page.pageNo==i}">
                            [${requestScope.page.pageNo}]
                        </c:if>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>

                <%--			第三种情况,在后面三个--%>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${requestScope.page.pageNo==i}">
                            [${requestScope.page.pageNo}]
                        </c:if>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                        <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                            <c:if test="${requestScope.page.pageNo==i}">
                                [${requestScope.page.pageNo}]
                            </c:if>
                            <c:if test="${requestScope.page.pageNo!=i}">
                                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                            </c:if>
                        </c:forEach>
                </c:otherwise>

            </c:choose>
        </c:when>
    </c:choose>

    <%--		<a href="#">3</a>--%>
    <%--		【${requestScope.page.pageNo}】--%>
    <%--		<a href="#">5</a>--%>

    <%--	静态包含分页调--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}"  name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="confirm">
</div>