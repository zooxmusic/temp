<!-- JSP ERROR LOGIN -->
<html>

<head>
    <title>NJCEP</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	
	<META Http-Equiv="Cache-Control" Content="no-cache">
	<META Http-Equiv="Pragma" Content="no-cache">
	<META Http-Equiv="Expires" Content="0">	
	
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/ims_static/1.login/lib/bootstrap-3.3.6/css/bootstrap.min.css">
    <!-- Custom CSS-->
    <link rel="stylesheet" href="/ims_static/1.login/css/style.css">
    <!-- Bootstrap table css -->
    <link rel="stylesheet" href="/ims_static/1.login/lib/bootstrap-table/bootstrap-table.min.css">
    <!--Font Awesome here-->
    <link rel="stylesheet" href="/ims_static/1.login/lib/font-awesome-4.5.0/css/font-awesome.min.css">
    <!--Applied energy - Branding theme-->
    <link rel="stylesheet" href="/ims_static/1.login/css/themes/basic.css">
    <!-- jquery-->
    <script src="/ims_static/1.login/js/jquery-1.11.3.min.js"></script>
    <!-- Bootstrap JS-->
    <script src="/ims_static/1.login/lib/bootstrap-3.3.6/js/bootstrap.min.js"></script>
    <!-- Form validator javascript-->
    <script src="/ims_static/1.login/lib/bootstrap-validator/validator.min.js"></script>
    <!-- Bootstrap-Table javascript-->
    <script src="/ims_static/1.login/lib/bootstrap-table/bootstrap-table.min.js"></script>
	
	<script language="javascript">
     
		function aeghome() {
			var myWindowHandle = window.open("http://www.aegonline.com", "aeg_home");
			myWindowHandle.focus();
		}

		function njcepabout() {
			var myWindowHandle = window.open("http://www.njcleanenergy.com/main/about-njcep/about-njcep", "njcep_about");
			myWindowHandle.focus();
		}

		function njcepcontact() {
			var myWindowHandle = window.open("http://www.njcleanenergy.com/misc/about-njcep/contact-us", "njcep_contact");
			myWindowHandle.focus();
		}

	  	function SetLoginEntries() {		
			var theForm = document.forms[0];
			theForm.j_username.value = "<%=request.getRemoteAddr()%>" + "#" + theForm.username.value.toLowerCase();
	  	}
		
		function ResetPasswordRequestSubmit() {
		
			$.post("/ims/imsWeb/GetUserPassword.action?action=GetPassword", {
				'emailaddress': document.id_password_form.emailaddress.value			
			}, function(data, status) {
				if (status=="success") {
					if(data.validationResult=="Success") {
						$("#id_pass_error_alert").removeClass('show');
						$("#id_pass_error_alert").addClass('hide');
						$("#id_pass_success_alert").addClass('show');
						$("#id_pass_success_alert").removeClass('hide');
						
						$(".alert-success-span").text(data.validationMessage);
												
					}
					else {
					    $("#id_pass_error_alert").removeClass('hide');
						$("#id_pass_error_alert").addClass('show');
						$("#id_pass_success_alert").addClass('hide');
						$("#id_pass_success_alert").removeClass('show');
						
						$(".alert-error-span").text(data.validationMessage);
					}	
				}
			}, 'json');	
		}	
		
	</script>	
	
</head>

<body>
    <header>
        <div role="navigation" id="id_primary_menu" class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div id="id_navbar_logo" class="navbar-collapse collapse navbar-left">
                    <ul class="nav navbar-nav">
                        <img class="logo" height="76px" src="/ims_static/1.login/images/aeg-logo.jpg" />
                    </ul>
                </div>
                <div id="id_navbar" class="navbar-collapse collapse navbar-right">
                    <ul class="nav navbar-nav">
                        <li><a href="#">About us</a></li>
                        <li><a href="#about">Contact us</a></li>
                        <li class="dropdown show">
                            <button href="#" class="nav-btn-normal btn-sm" data-toggle="modal" data-target="#id_login_modal" role="button">MEMBER LOGIN</button>
                        </li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
    </header>
    <div class="carousel-wrapper">
        <div id="myCarousel" class="carousel slide">
            <!-- Carousel items -->
            <div class="carousel-inner">
                <div class="item active ad1">
                    <div class="carousel-caption carousel-caption-top-right">
                        <div class="carousel-caption-wrap">
                            <h4>First Thumbnail label</h4>
                            <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                        </div>
                    </div>
                </div>
                <div class="item ad2">
                    <div class="carousel-caption carousel-caption-top-left">
                        <div class="carousel-caption-wrap">
                            <h4>Second Thumbnail label</h4>
                            <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                        </div>
                    </div>
                </div>
                <div class="item ad3">
                    <div class="carousel-caption carousel-caption-top-right">
                        <div class="carousel-caption-wrap">
                            <h4>Third Thumbnail label</h4>
                            <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Carousel nav -->
            <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
            <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
        </div>
    </div>
    <!-- Modals -->
    <!-- Login Modal dialog-->
    <div class="modal-wrapper">
        <div class="modal fade" id="id_login_modal" role="dialog">
            <div class="modal-dialog login-modal">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">MEMBER LOGIN</h4>
                    </div>
                    <div class="modal-body">
                        <form class="switch-form form" data-toggle="validator" id="id_login_form" action="j_security_check" method="post">
                            <fieldset class="form-group">
                                <label for="username">User name</label>
                                <input type="text" required name="username" id="username" class="form-control" data-error="User name required">
								<input type="hidden"  id="j_username"  name="j_username">
                                <div class="help-block with-errors"></div>
                            </fieldset>
                            <fieldset class="form-group">
                                <label for="j_password">Password</label>
                                <input type="password" required name="j_password" id="j_password" class="form-control" data-error="Password required">
                                <div class="help-block with-errors"></div>
                            </fieldset>
                            <fieldset class="form-group">
                                <div class="row">
									<div class="col-sm-12">
                                        <a class="pull-right forgot-password" data-dismiss="modal" data-toggle="modal" data-target="#id_forgot_pass_modal">Forgot Password</a>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="login-error">
                                            <p class="text-error login-error-text">Invalid user name or password</p>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <div class="submit-wrapper text-center">
                                <button type="submit" class="btn btn-md login-btn" onclick="javascript:SetLoginEntries()">LOGIN</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Ends - Login Modal dialog-->
    <!-- Forgot password Modal dialog-->
    <div class="modal-wrapper">
        <div class="modal fade" id="id_forgot_pass_modal" role="dialog">
            <div class="modal-dialog password-modal">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Forgot Password</h4>
                    </div>
                    <div class="modal-body">
						<div class="alert alert-success hide" id="id_pass_success_alert" role="alert">
							<span class="alert-success-span">Password changed successfully!</span>
						</div>
						<div class="alert alert-danger hide" id="id_pass_error_alert" role="alert">
							<span class="alert-error-span">Sorry please try again!</span>
						</div>						
                        <form class="switch-form" data-toggle="validator" name="id_password_form" id="id_password_form">
                            <p class="modal-info"></p>
                            <fieldset class="form-group">
                                <label for="emailaddress">Email <span class="required">*</span></label>
                                <input type="email" name="emailaddress" id="emailaddress" data-error="Please enter valid email address" class="form-control">
                                <div class="help-block with-errors"></div>
                            </fieldset>
                            <fieldset class="form-group">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <a class="pull-left forgot-password" data-target="#id_login_modal" data-toggle="modal" data-dismiss="modal">
                                            <i class="fa fa-angle-left fa-sm"></i>Back to Login</a>
                                    </div>
                                    <div class="col-sm-6">
                                        <button type="button" id="id_forgot_pass_submit" class="btn action-btn btn-md pull-right" onclick="javascript:ResetPasswordRequestSubmit()">SUBMIT</button>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Ends forgot password -->
    <footer>
        <!--Navbar footer-->
        <nav class="navbar navbar-default navbar-inverse navbar-fixed-bottom">
            <div class="container">
                <ul class="nav navbar-nav navbar-left">
                    <li class="credits"><a href="#">NJCEP Program Coordinator Applied Energy Group, Inc @ 2015 | All Rights Reserved</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="credits"><a href="#">Network Solutions</a></li>
                </ul>
            </div>
        </nav>
        <script src="/ims_static/1.login/js/common.js"></script>
<script>
$(document).ready(function(){
    $("#id_login_modal").modal('show');
});

</script>		
    </footer>
</body>

</html>