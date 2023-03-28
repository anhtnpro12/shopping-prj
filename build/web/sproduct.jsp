<%-- 
    Document   : sproduct
    Created on : Oct 26, 2022, 5:15:24 PM
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
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/responsive.css">

</head>

<body>
    <section id="header">
        <a href="index"><img src="./img/logo_TNA.png" class="logo" alt=""></a>

        <div>
            <ul id="navbar">
                <li><a href="index">Home</a></li>
                <li><a class="active" href="shop">Shop</a></li>                
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>                
                <li id="lg-bag"><a href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i></a></li>
                <c:if test="${sessionScope.account == null}">
                    <li><a href="login">Login</a></li>                    
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

    <section id="prodetails" class="section-p1">
        <div class="single-pro-image">            
            <img src="${product.getImages()[0]}" width="100%" id="main-img" alt="">

            <div class="small-img-group">
                <c:forEach items="${product.getImages()}" var="img">
                    <div class="small-img-col">
                        <img src="${img}" width="100%" class="small-img" alt="">
                    </div>
                </c:forEach>                
            </div>
        </div>

        <div class="single-pro-details">
            <h6>Home / ${cate}</h6>            
            <h6>Views: ${count}</h6>            
            <h4>${product.getProductName()}</h4>
            <h2>$${product.getUnitPrice()}</h2>
            <select>
                <c:forEach items="${product.getType()}" var="type">
                    <option>${type}</option>                    
                </c:forEach>                
            </select>
            <input type="number" value="1" min="1" max="${product.getUnitInStock()-product.getSellQuantity()}">            
            <button class="normal">Add To Cart</button> <br>
            <span style="color:red">${product.getUnitInStock()-product.getSellQuantity()} products left</span>
            <h4>Product Details</h4>
            <span>${product.getDescription()}</span>
        </div>
    </section>

    <section id="product1" class="section-p1">
        <h2>New products</h2>
        <p>Summer Collection New Modern Design</p>
        <div class="pro-container">
            <c:forEach items="${list}" var="p">                
                <div class="pro" onclick="window.location.href='sproduct?proID=${p.getProductID()}'">
                    <img src="${p.getImages()[0]}" alt="">
                    <div class="des">
                        <span>${cdao.getCategoryByID(clist, p.getCategoryID()).getCategoryName()}</span>
                        <h5>${p.getProductName()}</h5>
                        <div class="star">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                        </div>
                        <h4>$${p.getUnitPrice()}</h4>
                    </div>
                    <a href="#"><i class="fa-solid fa-cart-plus cart"></i></i></a>
                </div>
            </c:forEach>                      
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

    <script src="./js/sproductScript.js"></script>
    <script src="./js/script.js"></script>
</body>

</html>
