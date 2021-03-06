<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 04/11/2021
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="page_type" content="np-template-header-footer-from-plugin">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/nicepage.css" media="screen">
<script class="u-script" type="text/javascript" src="${pageContext.request.contextPath}js/jquery.js" defer=""></script>
<script class="u-script" type="text/javascript" src="${pageContext.request.contextPath}/js/nicepage.js"
        defer=""></script>
<meta name="generator" content="Nicepage 3.29.1, nicepage.com">
<link id="u-theme-google-font" rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">

<%--<meta name="theme-color" content="#478ac9">--%>
<%--<meta property="og:description" content="">--%>
<%--<meta property="og:type" content="website">--%>
<%--<meta charset="utf-8">--%>
<%--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>

<%--<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">--%>
<%--<link rel="stylesheet" href="/resources/demos/style.css">--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
<%--      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">--%>
<%--<script src="https://code.jquery.com/jquery-3.6.0.js"></script>--%>
<%--<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>--%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<%--<script src="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"--%>
<%--        crossorigin="anonymous"></script>--%>

<meta name="theme-color" content="#478ac9">
<meta property="og:description" content="">
<meta property="og:type" content="website">
<script type="text/javascript" src="//cdn.jsdelivr.net/jquery/1/jquery.min.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap/3/css/bootstrap.css"/>

<script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css"/>

<script type="text/javascript">
    $.noConflict();
    jQuery(document).ready(function ($) {

        $('input[name="datefilter"]').daterangepicker({
            autoUpdateInput: false,
            locale: {
                cancelLabel: 'Clear'
            },
            'opens': 'center'
        });

        $('input[name="datefilter"]').on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('YYYY-MM-DD') + ' : ' + picker.endDate.format('YYYY-MM-DD'));
        });

        $('input[name="datefilter"]').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });

    });
</script>

<script>
    $(document).ready(function () {
        var multipleCancelButton = new Choices('#choices-multiple-remove-button', {
            removeItemButton: true,
            maxItemCount: 5,
            searchResultLimit: 5,
            renderChoiceLimit: 5
        });
    });
</script>

