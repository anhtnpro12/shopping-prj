<%-- 
    Document   : adminCates
    Created on : Nov 7, 2022, 12:23:13 AM
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
    <title>Admin</title>
    <link rel="stylesheet" href="./css/adminStyle.css">
    <link rel="stylesheet" href="./css/adminUsersStyle.css">
    <link rel="stylesheet" href="./css/editUserStyle.css">
</head>

<body>
    <div class="container">
        <div class="navigation">
            <ul>
                <li>
                    <a href="#">
                        <span class="icon"><ion-icon name="hammer-outline"></ion-icon></span>
                        <span class="title">ADMIN</span>
                    </a>
                </li>
                <li>
                    <a href="admin-dashboard">
                        <span class="icon"><ion-icon name="home-outline"></ion-icon></span>
                        <span class="title">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="admin-users">
                        <span class="icon"><ion-icon name="people-outline"></ion-icon></span>
                        <span class="title">Customers</span>
                    </a>
                </li>
                <li>
                    <a href="admin-products">
                        <span class="icon"><ion-icon name="bag-handle-outline"></ion-icon></span>
                        <span class="title">Products</span>
                    </a>
                </li>                
                <li class="hovered">
                    <a href="admin-cates">
                        <span class="icon"><ion-icon name="file-tray-stacked-outline"></ion-icon></span>
                        <span class="title">Categories</span>
                    </a>
                </li>                
                <li>
                    <a href="index">
                        <span class="icon"><ion-icon name="log-out-outline"></ion-icon></span>
                        <span class="title">Back to Shop</span>
                    </a>
                </li>                
            </ul>
        </div>
    </div>

    <!-- main -->
    <div class="main">
        <div class="topbar">
            <div class="toggle">
                <ion-icon name="menu-outline"></ion-icon>
            </div>
            <!-- search -->
            <div class="search">
                <label>
                    <input type="text" placeholder="Search here">
                    <ion-icon name="search-outline"></ion-icon>
                </label>
            </div>
            <!-- UserImg -->
            <div class="user">
                <!-- <img src="./img/people/3.png" alt=""> -->
            </div>
        </div>

        <div class="wrapper">
            <div class="editUser" style="min-height: 200px">
                <form action="admin-cates" method="post">
                    <table border="0">
                        <tr>
                            <td>
                                <h3>Category Name:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="name" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Category Name</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Description:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="desc" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Description</label>
                                </div>
                            </td>
                        </tr>
                        
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Add">
                            </td>                            
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <!-- Category table -->
        <div class="wrapper">
            <div class="userTable">
                <div class="cardHeader">
                    <h2>Categories</h2>
                </div>

                <table>
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Name</td>
                            <td>Description</td>                            
                            <td>Edit</td>
                            <td>Delete</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="c">
                            <tr>
                                <td>${c.getCategoryID()}</td>                                                           
                                <td>${c.getCategoryName()}</td>                                                           
                                <td>${c.getDescription()}</td>                                                           
                                <td><a href="admin-editcate?id=${c.getCategoryID()}"><ion-icon name="create-outline"></ion-icon></a></td>
                                <td><a href="admin-delcate?id=${c.getCategoryID()}"><ion-icon name="trash-outline"></ion-icon></a></td>
                            </tr>                                                    
                        </c:forEach>
                    </tbody>
                </table>

                <section id="pagination" class="section-p1">
                    <c:if test="${page!=1}">
                        <a href="admin-cates?page=${page-1}"><ion-icon name="arrow-back-outline"></ion-icon></a>            
                    </c:if>
                    <c:forEach var="i" begin="1" end="${size}">
                        <a href="admin-cates?page=${i}" <c:if test="${i==page}">class="active"</c:if>>${i}</a>
                    </c:forEach>
                    <c:if test="${page!=size}">
                        <a href="admin-cates?page=${page+1}"><ion-icon name="arrow-forward-outline"></ion-icon></a>            
                    </c:if>
                </section>
            </div>
        </div>

    </div>

    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

    <script>
        // menu toggle
        let toggle = document.querySelector('.toggle')
        let navigation = document.querySelector('.navigation')
        let main = document.querySelector('.main')

        toggle.addEventListener('click', function() {
            navigation.classList.toggle('active')
            main.classList.toggle('active')
        })
        
    </script>
</body>

</html>
