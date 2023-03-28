<%-- 
    Document   : login
    Created on : Oct 26, 2022, 5:13:40 PM
    Author     : TNA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>TNA</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/loginStyle.css">
        <link rel="stylesheet" href="./css/responsive.css">

    </head>

    <body>
        <section id="header">
        <a href="index"><img src="./img/logo_TNA.png" class="logo" alt=""></a>

        <div>
            <ul id="navbar">
                <li><a href="index">Home</a></li>
                <li><a href="shop">Shop</a></li>                
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>                
                <li id="lg-bag"><a href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i></a></li>
                <c:if test="${sessionScope.account == null}">
                    <li><a class="active" href="login">Login</a></li>                    
                </c:if>
                <c:if test="${sessionScope.account != null}">
                    <c:if test="${sessionScope.account.getRole() <= 1}">
                        <li><a href="add-product"><i class="fa-solid fa-circle-plus"></i></a></li>                    
                    </c:if>
                    <li>
                        <div class="dropdown">
                            <div class="dropdown__select">
                                <i class="fa-solid fa-circle-user"></i>                                                             
                            </div>

                            <ul class="dropdown__list">
                                <a href="profile" class="dropdown__item">
                                    <span class="dropdown__text">
                                        Profile
                                    </span>
                                </a>                            
                                <c:if test="${sessionScope.account.getRole() <= 1}">
                                    <a href="admin-dashboard" class="dropdown__item">
                                        <span class="dropdown__text">
                                            Admin
                                        </span>
                                    </a>                                                                
                                </c:if>                           
                                <a href="logout" class="dropdown__item">
                                    <span class="dropdown__text">
                                        Log Out
                                    </span>
                                </a>                                                                                   
                            </ul>
                        </div>
                    </li>                  
                </c:if>
                <div id="close"><i class="fa-solid fa-xmark"></i></div>
            </ul>
        </div>

        <div id="mobile">
            <a href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i></a>
            <i id="bar" class="fas fa-outdent"></i>
        </div>
    </section>

        <section id="login">
            <div class="container ${active}">
                <div class="forms">
                    <!-- Login Form -->
                    <div class="form login">
                        <span class="title">Login</span>
                        <span class="error">${error}</span>
                        <form action="login" method="post">
                            <div class="input-field">
                                <input type="email" name="email" value="${cookie.email.value}" placeholder="Enter your email" required>
                                <i class="material-symbols-outlined icon">mail</i>
                            </div>
                            <div class="input-field">
                                <input type="password" name="pass" value="${cookie.pass.value}" class="password" 
                                       placeholder="Enter your password" required>
                                <i class="material-symbols-outlined icon">password</i>
                                <i class="material-symbols-outlined showHidePw">visibility_off</i>
                            </div>

                            <div class="checkbox-text">     
                                <div class="checkbox-content">
                                    <input name="rem" value="on" type="checkbox" <c:if test="${cookie.rem.value!=null}">checked</c:if>
                                           id="logCheck">
                                    <label for="logCheck" class="text">Remember me</label>
                                </div> 
                                
                                <a href="#" class="text">Forgot password?</a>
                            </div>

                            <div class="input-field button">
                                <input type="submit" value="Login Now">
                            </div>
                        </form>

                        <div class="login-signup">
                            <span>Don't have an account?
                                <a href="#" class="text signup-link">Signup Now</a>
                            </span>
                        </div>
                    </div>

                    <!-- Register Form -->
                    <div class="form signup">
                        <span class="title">Register</span>
                        <span class="error">${error2}</span>
                        <form action="register" method="post">
                            <div class="input-field">
                                <input type="text" name="name" value="${name}" placeholder="Enter your Name" required>
                                <i class="material-symbols-outlined icon">person</i>
                            </div>
                            <div class="input-field">
                                <input type="email" name="email" value="${email}" placeholder="Enter your email" required>
                                <i class="material-symbols-outlined icon">mail</i>
                            </div>
                            <div class="input-field">
                                <input type="text" name="phone" value="${phone}" placeholder="Enter your phone number"
                                       pattern="[0-9]{10,11}" required
                                       oninput="this.setCustomValidity('')"
                                       oninvalid="this.setCustomValidity('Enter Phone number Here')">
                                <i class="material-symbols-outlined icon">call</i>
                            </div>
                            <div class="input-field">
                                <input type="password" name="pass" value="${pass}" class="password" placeholder="Create a password" required>
                                <i class="material-symbols-outlined icon">password</i>
                            </div>                            
                            <div class="input-field">
                                <input type="password" name="repass" value="${repass}" class="password" placeholder="Confirm a password" required>
                                <i class="material-symbols-outlined icon">password</i>
                                <i class="material-symbols-outlined showHidePw">visibility_off</i>
                            </div>

                            <div class="checkbox-text">                            
                                <a href="#" class="text">Forgot password?</a>
                            </div>

                            <div class="input-field button">
                                <input type="submit" value="Signup">
                            </div>
                        </form>

                        <div class="login-signup">
                            <span>Already have an account?
                                <a href="#" class="text login-link">Login Now</a>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="news-letter" class="section-p1 section-m1">
            <div class="news-text">
                <h4>Sign up For Newsletters</h4>
                <p>Get E-mail updates about our latest shop and <span>special offers.</span></p>
            </div>

            <form action="">
                <input type="text" placeholder="Your email address">
                <input type="submit" class="normal">
            </form>
        </section>

        <footer class="section-p1">
            <div class="col">
                <img class="logo" src="./img/logo_TNA.png" alt="logo">
                <h4>Contact</h4>
                <p><strong>Address:</strong> Ha Noi</p>
                <p><strong>Phone:</strong> +84 123456789</p>
                <p><strong>Hours:</strong> 06:00 - 18:00. Mon - Sat</p>

                <div class="follow">
                    <h4>Follow us</h4>
                    <div class="icon">
                        <i class="fab fa-facebook-f"></i>
                        <i class="fab fa-twitter"></i>
                        <i class="fab fa-instagram"></i>
                        <i class="fab fa-pinterest-p"></i>
                        <i class="fab fa-youtube"></i>
                    </div>
                </div>
            </div>

            <div class="col">
                <h4>About</h4>
                <a href="#">About Us</a>
                <a href="#">Delivery Information</a>
                <a href="#">Privacy Policy</a>
                <a href="#">Terms & Conditions</a>
                <a href="#">Contact Us</a>
            </div>

            <div class="col">
                <h4>My Account</h4>
                <a href="#">Sign In</a>
                <a href="#">View Cart</a>
                <a href="#">My Wishlist</a>
                <a href="#">Track My Order</a>
                <a href="#">Help</a>
            </div>

            <div class="col install">
                <h4>Install App</h4>
                <p>From App Store or Google Play</p>
                <div class="row">
                    <img src="./img/pay/app.jpg" alt="">
                    <img src="./img/pay/play.jpg" alt="">
                </div>
                <p>Secured Payment Gateways</p>
                <img src="./img/pay/pay.png" alt="">
            </div>

            <div class="copyright">
                <p>© Copyright 2022, TNA</p>
            </div>
        </footer>

        <script src="./js/loginScript.js"></script>
        <script src="./js/script.js"></script>
    </body>

</html>
