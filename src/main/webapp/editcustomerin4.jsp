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
                                        <tr>
                                            <th>Order</th>
                                            <th>Date</th>
                                            <%--                                            <th>Shipped to</th>--%>
                                            <%--                                            <th>Address</th>--%>
                                            <th>Order Price</th>
                                            <th>Payment status</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td colspan="6"><p style="color: #666666">There are no order.</p></td>
                                        </tr>
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
                        <p><i class="fa fa-home font-some" aria-hidden="true"></i>  <span>Address: </span></p>
                        <p><i class="fa fa-mobile font-some" aria-hidden="true"></i> <span>Phone numbers: </span> </p>
                        <p><i class="fa fa-map-marker font-some" aria-hidden="true"></i> <span> Address 2: </span></p>
                        <p><i class="fa fa-yelp font-some" aria-hidden="true"></i> <span> Company: </span></p>
                        <p><i class="fa fa-plane font-some" aria-hidden="true"></i> <span> Country :</span></p>
                        <p><i class="fa fa-code font-some" aria-hidden="true"></i> <span> Zip code: </span></p>
                        <p style="margin-top:20px;"><a href="/account/addresses" class="btn btn-bg full-width btn-lg btn-style article-readmore">Edit</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>