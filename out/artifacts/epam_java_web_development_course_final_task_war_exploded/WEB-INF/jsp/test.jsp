<!DOCTYPE html>
<html style="font-size: 16px;">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="page_type" content="np-template-header-footer-from-plugin">
	<title>Home</title>
	<link rel="stylesheet" href="nicepage.css" media="screen">
	<link rel="stylesheet" href="Home.css" media="screen">
	<script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
	<script class="u-script" type="text/javascript" src="nicepage.js" defer=""></script>
	<meta name="generator" content="Nicepage 3.30.0, nicepage.com">
	<link id="u-theme-google-font" rel="stylesheet"
	      href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
	
	
	<script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "Site1"
	}</script>
	<meta name="theme-color" content="#478ac9">
	<meta property="og:title" content="Home">
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
                $(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
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
</head>
<body data-home-page="Home.html" data-home-page-title="Home" class="u-body">
<header class="u-clearfix u-grey-60 u-header" id="sec-d985" data-animation-name="" data-animation-duration="0"
        data-animation-delay="0" data-animation-direction="">
	<div class="u-clearfix u-sheet u-sheet-1">
		<nav class="u-menu u-menu-dropdown u-offcanvas u-menu-1" data-responsive-from="XL">
			<div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px; font-weight: 700;">
				<a class="u-button-style u-custom-active-border-color u-custom-active-color u-custom-border u-custom-border-color u-custom-borders u-custom-hover-border-color u-custom-hover-color u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-text-active-color u-custom-text-color u-custom-text-hover-color u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
				   href="#"
				   style="font-size: calc(1em + 8px); height: 24px; color: rgb(255, 255, 255) !important; padding: 4px 0px;">
					<svg class="u-svg-link" preserveAspectRatio="xMidYMin slice" viewBox="0 0 50 50" style="">
						<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg-ca5a"></use>
					</svg>
					<svg class="u-svg-content" viewBox="0 0 50 50" x="0px" y="0px" id="svg-ca5a"
					     style="enable-background:new 0 0 50 50;">
						<g>
							<rect y="3" width="50" height="2"></rect>
							<rect y="17" width="50" height="2"></rect>
							<rect y="31" width="50" height="2"></rect>
							<rect y="45" width="50" height="2"></rect>
						</g>
					</svg>
				</a>
			</div>
			<div class="u-custom-menu u-nav-container">
				<ul class="u-nav u-spacing-20 u-unstyled u-nav-1">
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							href="Home.html" style="padding: 10px;">Home</a>
					</li>
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							href="Rooms-List.html" style="padding: 10px;">Rooms List</a>
					</li>
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							href="Log-in.html" style="padding: 10px;">Log-in</a>
					</li>
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							href="Sign-up.html" style="padding: 10px;">Sign-up</a>
					</li>
				</ul>
			</div>
			<div class="u-custom-menu u-nav-container-collapse">
				<div class="u-container-style u-grey-60 u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
					<div class="u-inner-container-layout u-sidenav-overflow">
						<div class="u-menu-close"></div>
						<ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2">
							<li class="u-nav-item"><a class="u-button-style u-nav-link" href="Home.html"
							                          style="padding: 10px;">Home</a>
							</li>
							<li class="u-nav-item"><a class="u-button-style u-nav-link" href="Rooms-List.html"
							                          style="padding: 10px;">Rooms List</a>
							</li>
							<li class="u-nav-item"><a class="u-button-style u-nav-link" href="Log-in.html"
							                          style="padding: 10px;">Log-in</a>
							</li>
							<li class="u-nav-item"><a class="u-button-style u-nav-link" href="Sign-up.html"
							                          style="padding: 10px;">Sign-up</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="u-grey-25 u-menu-overlay u-opacity u-opacity-70"></div>
			</div>
		</nav>
		<h3 class="u-custom-font u-font-georgia u-text u-text-default u-text-white u-text-1">Hotel Grodno Inn</h3>
		<nav class="u-menu u-menu-dropdown u-offcanvas u-menu-2" data-responsive-from="XL">
			<div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px; font-weight: 700;">
				<a class="u-button-style u-custom-active-border-color u-custom-active-color u-custom-border u-custom-border-color u-custom-borders u-custom-hover-border-color u-custom-hover-color u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-text-active-color u-custom-text-color u-custom-text-hover-color u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
				   href="#" style="font-size: calc(1em + 8px); height: 24px; color: rgb(255, 255, 255) !important;">
					<svg class="u-svg-link" preserveAspectRatio="xMidYMin slice" viewBox="0 0 50 50" style="">
						<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg-3b6e"></use>
					</svg>
					<svg class="u-svg-content" viewBox="0 0 50 50" x="0px" y="0px" id="svg-3b6e"
					     style="enable-background:new 0 0 50 50;">
						<g>
							<rect y="3" width="50" height="2"></rect>
							<rect y="17" width="50" height="2"></rect>
							<rect y="31" width="50" height="2"></rect>
							<rect y="45" width="50" height="2"></rect>
						</g>
					</svg>
				</a>
			</div>
			<div class="u-custom-menu u-nav-container">
				<ul class="u-nav u-spacing-20 u-unstyled u-nav-3">
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							href="Home.html" style="padding: 10px;">Home</a>
					</li>
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							href="Rooms-List.html" style="padding: 10px;">Rooms List</a>
					</li>
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							href="Admin-Cabinet.html" style="padding: 10px;">Admin Cabinet</a>
					</li>
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							style="padding: 10px;">Log-off</a>
					</li>
				</ul>
			</div>
			<div class="u-custom-menu u-nav-container-collapse">
				<div class="u-container-style u-grey-60 u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
					<div class="u-inner-container-layout u-sidenav-overflow">
						<div class="u-menu-close"></div>
						<ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-4">
							<li class="u-nav-item"><a class="u-button-style u-nav-link" href="Home.html"
							                          style="padding: 10px;">Home</a>
							</li>
							<li class="u-nav-item"><a class="u-button-style u-nav-link" href="Rooms-List.html"
							                          style="padding: 10px;">Rooms List</a>
							</li>
							<li class="u-nav-item"><a class="u-button-style u-nav-link" href="Admin-Cabinet.html"
							                          style="padding: 10px;">Admin Cabinet</a>
							</li>
							<li class="u-nav-item"><a class="u-button-style u-nav-link"
							                          style="padding: 10px;">Log-off</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="u-grey-25 u-menu-overlay u-opacity u-opacity-70"></div>
			</div>
		</nav>
		<nav class="u-menu u-menu-dropdown u-offcanvas u-menu-3" data-responsive-from="XL">
			<div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px; font-weight: 700;">
				<a class="u-button-style u-custom-active-border-color u-custom-active-color u-custom-border u-custom-border-color u-custom-borders u-custom-hover-border-color u-custom-hover-color u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-text-active-color u-custom-text-color u-custom-text-hover-color u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
				   href="#" style="font-size: calc(1em + 8px); height: 24px; color: rgb(255, 255, 255) !important;">
					<svg class="u-svg-link" preserveAspectRatio="xMidYMin slice" viewBox="0 0 50 50" style="">
						<use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg-4193"></use>
					</svg>
					<svg class="u-svg-content" viewBox="0 0 50 50" x="0px" y="0px" id="svg-4193"
					     style="enable-background:new 0 0 50 50;">
						<g>
							<rect y="3" width="50" height="2"></rect>
							<rect y="17" width="50" height="2"></rect>
							<rect y="31" width="50" height="2"></rect>
							<rect y="45" width="50" height="2"></rect>
						</g>
					</svg>
				</a>
			</div>
			<div class="u-custom-menu u-nav-container">
				<ul class="u-nav u-spacing-20 u-unstyled u-nav-5">
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							href="Home.html" style="padding: 10px;">Home</a>
					</li>
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							href="Rooms-List.html" style="padding: 10px;">Rooms List</a>
					</li>
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							href="Client-Cabinet.html" style="padding: 10px;">Client Cabinet</a>
					</li>
					<li class="u-nav-item"><a
							class="u-border-active-palette-1-base u-border-hover-palette-1-base u-button-style u-nav-link u-text-active-palette-1-base u-text-grey-90 u-text-hover-palette-2-base"
							style="padding: 10px;">Log-off</a>
					</li>
				</ul>
			</div>
			<div class="u-custom-menu u-nav-container-collapse">
				<div class="u-container-style u-grey-60 u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
					<div class="u-inner-container-layout u-sidenav-overflow">
						<div class="u-menu-close"></div>
						<ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-6">
							<li class="u-nav-item"><a class="u-button-style u-nav-link" href="Home.html"
							                          style="padding: 10px;">Home</a>
							</li>
							<li class="u-nav-item"><a class="u-button-style u-nav-link" href="Rooms-List.html"
							                          style="padding: 10px;">Rooms List</a>
							</li>
							<li class="u-nav-item"><a class="u-button-style u-nav-link" href="Client-Cabinet.html"
							                          style="padding: 10px;">Client Cabinet</a>
							</li>
							<li class="u-nav-item"><a class="u-button-style u-nav-link"
							                          style="padding: 10px;">Log-off</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="u-grey-25 u-menu-overlay u-opacity u-opacity-70"></div>
			</div>
		</nav>
	</div>
</header>
<section class="u-clearfix u-image u-section-1" id="carousel_b7ce" data-image-width="1280" data-image-height="839">
	<div class="u-align-left u-clearfix u-sheet u-sheet-1">
		<h6 class="u-custom-font u-font-georgia u-text u-text-body-alt-color u-text-default u-text-1">Hello Guest and
			welcome to our Hotel</h6>
		<div class="u-align-center u-clearfix u-custom-html u-expanded-width u-custom-html-1">
			<input type="text" name="datefilter" value="">
		</div>
		<a href="https://nicepage.com/k/children-website-templates"
		   class="u-border-2 u-border-white u-btn u-button-style u-hover-grey-50 u-none u-text-body-alt-color u-text-hover-white u-btn-1">
			CHECK AVAILABILITY</a>
	</div>
</section>


<footer class="u-align-center u-clearfix u-footer u-grey-60 u-footer" id="sec-ac13">
	<div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
		<p class="u-align-center u-custom-font u-font-georgia u-small-text u-text u-text-variant u-text-1">Design and
			Development by<br>Igor Taren
		</p>
	</div>
</footer>
<section class="u-backlink u-clearfix u-grey-80">
	<a class="u-link" href="https://nicepage.com/html-templates" target="_blank">
		<span>HTML Templates</span>
	</a>
	<p class="u-text">
		<span>created with</span>
	</p>
	<a class="u-link" href="https://nicepage.one" target="_blank">
		<span>Website Builder</span>
	</a>.
</section>
<section class="u-align-center u-clearfix u-cookies-consent u-grey-80 u-cookies-consent" id="sec-b928">
	<div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
		<div class="u-clearfix u-expanded-width u-layout-wrap u-layout-wrap-1">
			<div class="u-gutter-0 u-layout">
				<div class="u-layout-row">
					<div class="u-container-style u-layout-cell u-left-cell u-size-43-md u-size-43-sm u-size-43-xs u-size-46-lg u-size-46-xl u-layout-cell-1">
						<div class="u-container-layout u-valign-middle u-container-layout-1">
							<h3 class="u-text u-text-default u-text-1">Cookies &amp; Privacy</h3>
							<p class="u-text u-text-default u-text-2">This website uses cookies to ensure you get the
								best experience on our website.</p>
						</div>
					</div>
					<div class="u-align-left u-container-style u-layout-cell u-right-cell u-size-14-lg u-size-14-xl u-size-17-md u-size-17-sm u-size-17-xs u-layout-cell-2">
						<div class="u-container-layout u-valign-middle-lg u-valign-middle-md u-valign-middle-xl u-valign-top-sm u-valign-top-xs u-container-layout-2">
							<a href="###"
							   class="u-btn u-button-confirm u-button-style u-palette-1-base u-btn-1">Confirm</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<style> .u-cookies-consent {
		background-image: none;
	}
	
	.u-cookies-consent .u-sheet-1 {
		min-height: 212px;
	}
	
	.u-cookies-consent .u-layout-wrap-1 {
		margin-top: 30px;
		margin-bottom: 30px;
	}
	
	.u-cookies-consent .u-layout-cell-1 {
		min-height: 152px;
	}
	
	.u-cookies-consent .u-container-layout-1 {
		padding: 30px 60px;
	}
	
	.u-cookies-consent .u-text-1 {
		margin-top: 0;
		margin-right: 20px;
		margin-bottom: 0;
	}
	
	.u-cookies-consent .u-text-2 {
		margin: 8px 20px 0 0;
	}
	
	.u-cookies-consent .u-layout-cell-2 {
		min-height: 152px;
	}
	
	.u-cookies-consent .u-container-layout-2 {
		padding: 30px;
	}
	
	.u-cookies-consent .u-btn-1 {
		margin: 0 auto 0 0;
	}
	
	@media (max-width: 1199px) {
		.u-cookies-consent .u-sheet-1 {
			min-height: 131px;
		}
		
		.u-cookies-consent .u-layout-cell-1 {
			min-height: 125px;
		}
		
		.u-cookies-consent .u-text-1 {
			margin-right: 0;
		}
		
		.u-cookies-consent .u-text-2 {
			margin-right: 0;
		}
		
		.u-cookies-consent .u-layout-cell-2 {
			min-height: 125px;
		}
	}
	
	@media (max-width: 991px) {
		.u-cookies-consent .u-sheet-1 {
			min-height: 106px;
		}
		
		.u-cookies-consent .u-layout-cell-1 {
			min-height: 100px;
		}
		
		.u-cookies-consent .u-container-layout-1 {
			padding-left: 30px;
			padding-right: 30px;
		}
		
		.u-cookies-consent .u-layout-cell-2 {
			min-height: 100px;
		}
	}
	
	@media (max-width: 767px) {
		.u-cookies-consent .u-sheet-1 {
			min-height: 225px;
		}
		
		.u-cookies-consent .u-layout-cell-1 {
			min-height: 154px;
		}
		
		.u-cookies-consent .u-container-layout-1 {
			padding-left: 10px;
			padding-right: 10px;
			padding-bottom: 20px;
		}
		
		.u-cookies-consent .u-layout-cell-2 {
			min-height: 65px;
		}
		
		.u-cookies-consent .u-container-layout-2 {
			padding: 10px;
		}
	}
	
	@media (max-width: 575px) {
		.u-cookies-consent .u-sheet-1 {
			min-height: 121px;
		}
		
		.u-cookies-consent .u-layout-cell-1 {
			min-height: 100px;
		}
		
		.u-cookies-consent .u-layout-cell-2 {
			min-height: 15px;
		}
	}</style>
</section>
</body>
</html>