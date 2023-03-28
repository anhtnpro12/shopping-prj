<%-- 
    Document   : profile
    Created on : Nov 7, 2022, 1:36:58 AM
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
    <link rel="stylesheet" href="./css/editUserStyle.css">

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

    <section class="profile">
        <div class="wrapper">
            <div class="editUser" style="padding: 20px 500px">
                <span style="text-align: center">${mess}</span>
                <form action="profile" method="post">
                    <input style="display:none" type="text" name="aid" value="${a.getAccountId()}" />
                    <input style="display:none" type="text" name="uid" value="${a.getUser().getUserID()}" />
                    <table border="0">
                        <tr>
                            <td>
                                <h3>Name:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="name" value="${a.getUser().getName()}" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Name</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Email:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="email" value="${a.getEmail()}" required="" placeholder="Name" class="form__field" type="email">
                                    <label class="form__label" for="name">Email</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Password:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="pass" value="${a.getPassword()}" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Password</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Phone:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="phone" value="${a.getPhone()}" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Phone</label>
                                </div>
                            </td>
                        </tr>                        
                        <tr>
                            <td>
                                <h3>Gender:</h3>
                            </td>
                            <td>
                                <div class="subcontainer">
                                    <label>
                                        <input required type="radio" name="gender" value="Male" ${a.getUser().getGender()=="Male"?"checked":""}>
                                        Male                                                                                                           
                                    </label>
                                    <label>
                                        <input required type="radio" name="gender" value="Female" ${a.getUser().getGender()=="Female"?"checked":""}>
                                        Female                                                                                                           
                                    </label>                                    
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Birth date:</h3>
                            </td>
                            <td>
                                <div class="subcontainer">
                                    <input name="dob" type="date" value="${a.getUser().getBirthDate()}" required>                                   
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Address:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="address" value="${a.getUser().getAddress()}" required="" placeholder="Address" class="form__field" type="text">
                                    <label class="form__label" for="name">Address</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Save">
                            </td>                            
                        </tr>
                    </table>
                </form>
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


    <script src="./js/script.js"></script>
</body>

</html>
