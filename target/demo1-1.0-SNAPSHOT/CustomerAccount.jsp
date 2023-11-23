<%@ page import="java.util.List" %>
<%@ page import="entity.OrdersEntity" %>
<%@ page import="org.hibernate.criterion.Order" %>
<%@ include file="includes/header.jsp" %>

<!-- AccountInformation -->
<section class="sec-product bg0 p-t-100 p-b-50">
    <div class="container">
        <div id="parent" class="row">
            <div id="a" class="col-xs-12 col-sm-12 col-lg-9 col-left-account">
                <div class="title_head margin-bottom-20 m992">
                    <h1 class="title_center_page left">
                        <div id="padding__">Customer Account</div>
                    </h1>
                </div>
                <div class="form-signup name-account m992">
                    <p><strong>Anhonn, <a href="/account/addresses" style="color:#ad8610;">Quang Tran</a>&nbsp;!</strong></p>
                </div>
                <div class="col-xs-12 col-sm-12 col-lg-12 no-padding">
                    <div class="my-account">
                        <div class="dashboard">
                            <div class="recent-orders">
                                <div class="table-responsive tab-all" style="overflow-x:auto;">
                                    <table class="table table-cart" id="my-orders-table">
                                        <thead class="thead-default">
                                        <tr class="table_head">
                                            <th class="column-0">Order</th>
                                            <th class="column-1">Date</th>
                                            <th class="column-2">Order Price</th>
                                            <th class="column-3">Payment status</th>
                                            <th class="column-4"></th>
                                        </tr>
                                        </thead>

                                        <% List<OrdersEntity> customerOrders = (List<OrdersEntity>) session.getAttribute("customerOrders"); %>
                                        <c:if test="${customerOrders != null}">
                                            <% for (OrdersEntity order : customerOrders) { %>
                                            <tr class="table_row">
                                                <td class="column-0">
                                                    <button type="button" data-bs-toggle="modal" data-bs-target="#largeModal" style="color: #9a6e3a">
                                                    <%=order.getOrderId()%>
                                                    </button>
<%--                                                    Edit Order in POPUP--%>
                                                    <div class="modal fade" id="largeModal" tabindex="-1" aria-hidden="true" style="display: none; position: fixed; top: 90px; left: 0; width: 100%; height: 80vh;">
                                                        <div class="modal-dialog modal-lg " style="min-height: 100vh; width: 50%;">
                                                            <div class="modal-content" style="background-color: #f0f0f0fa">
                                                                <div class="letterhead"></div>
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title">Order Information</h5>
                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form>
                                                                        <div class="mb-3">
                                                                            <label style="font-size: larger"><box-icon type='solid' name='location-plus'></box-icon>Address:</label>
                                                                            <label style="font-weight: 700;color: #222;">Tran Duc Quang (+84) 355305120
                                                                            </label>
                                                                            <label>528/21 Le Van Viet Street, Long Thanh My Ward, Thu Duc City, City. Ho Chi Minh</label>
                                                                        </div>
                                                                        <div class="mb-3">
                                                                            <label style="font-weight: 700;color: #222;">Date: 2023-11-21 13:08:25.0 </label>
                                                                                <div class="col-md-12 col-sm-12 col-xs-12 content-page customer-table-wrap detail-table-order">
                                                                                <div class="customer-table-bg">
                                                                                    <p classs="title-detail">Order details	</p>
                                                                                    <div class="table-responsive">
                                                                                        <table id="order_details" class="table tableOrder">
                                                                                            <tbody><tr height="40px">
                                                                                                <th class="">Product</th>
                                                                                                <th class="text-center">Product id</th>
                                                                                                <th class="text-center">Price</th>
                                                                                                <th class="text-center">Quantity</th>
                                                                                                <th class="total text-right">Total</th>
                                                                                            </tr>

                                                                                            <tr height="40px" id="1196577302" class="odd">
                                                                                                <td class="" style="max-width:300px">
                                                                                                    <a href="productDetail?id=2" title="">SIMPLE TIEDYE TEE</a> <br>
                                                                                                    <span class="variant_acc">SIZE L</span>

                                                                                                </td>
                                                                                                <td class="sku text-center">STDT003</td>
                                                                                                <td class="money text-center">320,000</td>
                                                                                                <td class="quantity center text-center">1</td>
                                                                                                <td class="total money text-right">320,000</td>
                                                                                            </tr>

                                                                                            <tr height="40px" class="order_summary">
                                                                                                <td class="text-right" colspan="4"><b>Discount</b></td>
                                                                                                <td class="total money text-right"><b>20,000</b></td>
                                                                                            </tr>

                                                                                            <tr height="40px" class="order_summary order_total">
                                                                                                <td class="text-right" colspan="4"><b>Total Price</b></td>
                                                                                                <td class="total money text-right"><b>300,000</b></td>
                                                                                            </tr>
                                                                                            </tbody></table>
                                                                                    </div>
                                                                                </div>

                                                                            </div>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
<%--                                                                    <button type="button" class="btn btn-primary">Save Changes</button>--%>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="column-1">
                                                    <%=order.getDate()%>
                                                </td>
                                                <td class="column-2"><%= order.getTotalAmount() %></td>
                                                <td class="column-3"> <%= order.getStatus() %></td>
                                                <td class="column-4"><button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#verticalycentered">Cancel</button>
                                                <div class="modal fade" id="verticalycentered" tabindex="-1" aria-hidden="true" style="display: none; position: fixed; top: 150px; left: 0; width: 100%; height: 80vh;">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title">CANCEL ORDER</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                Are you sure you want to cancel this order? please check carefully brfore taking this action.
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Yes, Cancel this order</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                </td>

                                            </tr>
                                            <% } %>
                                        </c:if>
                                        <tbody>
                                        <c:if test="${customerOrders == null}">
                                        <tr>
                                            <td colspan="6"><p style="color: #666666">There are no order.</p></td>
                                        </tr>
                                        </c:if>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="text-xs-right">
                                </div>
                            </div>
                            <div class="paginate-pages pull-right page-account">
                                <nav>
                                    <ul class="pagination clearfix">
                                        <li class="page-item disabled"><a class="page-link" href="#"><</a></li>
                                        <li class="page-item disabled"><a class="page-link" href="#">></a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="b" class="col-xs-12 col-sm-12 col-lg-3 col-right-account">
                <div class="block-account">
                    <div class="block-title-account"><h5 style="color: #323c3f; font-size: 20px">My Account</h5></div>
                    <div class="block-content form-signup">
                        <p>Account Name: <strong style="color:#ad8610; line-height: 20px;"> Quang Tran!</strong></p>
                        <p><i class="fa fa-home font-some" aria-hidden="true"></i>  <span>Address: 568/21 Le Van Viet Street, Q9, HCM</span></p>
                        <p><i class="fa fa-mobile font-some" aria-hidden="true"></i> <span>Phone numbers: 0355305120</span> </p>
                        <p><i class="fa fa-map-marker font-some" aria-hidden="true"></i> <span> Level: </span></p>
                        <p><i class="fa fa-yelp font-some" aria-hidden="true"></i> <span> Total Payment: </span></p>
                        <p style="margin-top:20px;"><a href="editcustomerin4.jsp" class="btn btn-bg full-width btn-lg btn-style article-readmore">Edit</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<%@ include file="includes/footer.jsp" %>
</body>
</html>
