<%@ include file="includes/header.jsp" %>

<section class="address margin-top-20">
    <div class="container page_address">
        <div class="row">
            <div class="col-xs-12 col-lg-12 adr_title">
                <h1 class="title-head">Edit Account Information  <a class="f-right back_account" href="login?action=CheckUser" alt=""><i class="fa fa-arrow-left" aria-hidden="true"></i>&nbsp;Back to account pages</a></h1>
            </div>
            <div class="col-sm-12 col-lg-12 col-address">
                <c:if test="${message != null}">
                    <p style="border: #1c1e28; text-align: center; color: #ea1717"><i>${message}</i></p>
                </c:if>
                <div class="row">
                    <div id="add_address" class="col-md-12 form-list" style="margin-top: 30px;">
                        <form accept-charset="UTF-8" name="Form" action="login" onsubmit="return validateForm()" id="customer_address" method="post">
                            <input name="FormType" type="hidden" value="customer_address">
                            <input name="utf8" type="hidden" value="true">
                            <p id="errorFillsx" style="margin-bottom: 10px; color: red; display: none;"></p>
                            <div class="clearfix row">
                                <input type="hidden" name="action" value="edit">
                                <div class="col-xs-12 col-lg-12">
                                    <fieldset class="form-group">
                                        <label>Name<span>*</span></label>
                                        <input type="text" class="form-control req" required requiredmsg="Please fill this information!" name="firstName" placeholder="Input your name" value="Tran">
                                    </fieldset>
                                </div>
                                <div class="col-xs-12 col-lg-12">
                                    <fieldset class="form-group">
                                        <label>Surname<span>*</span></label>
                                        <input type="text" class="form-control req" required requiredmsg="Please fill this information!" name="lastName" placeholder="Input your surname" value="Quang">
                                    </fieldset>
                                </div>
                                <div class="col-xs-12 col-lg-12">
                                    <fieldset class="form-group">
                                        <label>City<span>*</span></label>
                                        <input type="text" class="form-control req" name="address" placeholder="Input your city" value="">
                                    </fieldset>
                                </div>
                                <div class="col-xs-12 col-lg-12">
                                    <fieldset class="form-group">
                                        <label>District<span>*</span></label>
                                        <input type="text" class="form-control req" name="address" placeholder="Input your district" value="">
                                    </fieldset>
                                </div>
                                <div class="col-xs-12 col-lg-12">
                                    <fieldset class="form-group">
                                        <label>Street<span>*</span></label>
                                        <input type="text" class="form-control req" name="address" placeholder="Input your street" value="">
                                    </fieldset>
                                </div>
                                <div class="col-xs-12 col-lg-12">
                                    <fieldset class="form-group">
                                        <label>Phone number<span>*</span></label>
                                        <input type="number" class="form-control req " name="phonenumber" placeholder="Input your phone number" value="">
                                    </fieldset>
                                </div>

                                <div class="form-group clearfix col-xs-12">
                                    <div class="col-sm-12" style="margin: 0px 15px;">
                                        <div class="row">
                                            <button class="btn-bg-addresss btn btn-lg btn-primary" type="submit" id="click_accept"><span>Save</span></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-sm-12 col-lg-12">
                <div class="row total_address">

                </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>