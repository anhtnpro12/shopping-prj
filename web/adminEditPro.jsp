<%-- 
    Document   : AdminEditPro
    Created on : Nov 6, 2022, 5:58:21 PM
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
                <li class="hovered">
                    <a href="admin-products">
                        <span class="icon"><ion-icon name="bag-handle-outline"></ion-icon></span>
                        <span class="title">Products</span>
                    </a>
                </li> 
                <li>
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
            <div class="editUser">
                <form action="admin-editpro" method="post" enctype="multipart/form-data">
                    <input type="text" name="imgs" value="${p.getTextImages()}" style="display: none">
                    <input type="text" name="id" value="${p.getProductID()}" style="display: none">
                    <table border="0">
                        <tr>
                            <td>
                                <h3>Name:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="name" value="${p.getProductName()}" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Name</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Category:</h3>
                            </td>
                            <td>                                     
                                <div class="form__group field">
                                    <select name="cate" id="cate">
                                        <c:forEach items="${clist}" var="c">
                                            <option value="${c.getCategoryID()}" 
                                                <c:if test="${c.getCategoryID() == thisc}">selected</c:if>>${c.getCategoryName()}</option>                                                                                       
                                        </c:forEach>
                                    </select>
                                    <label class="form__label" for="name">Category:</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Quantity per unit:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="qpu" value="${p.getQuantityPerUnit()}" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Quantity per unit</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Unit price:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="unitpr" value="${p.getUnitPrice()}" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Unit price</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Units in stock:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="uis" value="${p.getUnitInStock()}" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Units in stock</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Sell:</h3>
                            </td>
                            <td>
                                <div class="subcontainer">
                                    <label>
                                        <input type="radio" name="conti" value="1" ${p.isDiscontinued()?"":"checked"}>
                                        Yes                                                                                                           
                                    </label>
                                    <label>
                                        <input type="radio" name="conti" value="0" ${p.isDiscontinued()?"checked":""}>
                                        No                                                                                                           
                                    </label>                                    
                                </div>
                            </td>
                        </tr>                        
                        <tr>
                            <td>
                                <h3>Description:</h3>
                            </td>
                            <td>
                                <div class="subcontainer">
                                    <textarea name="desc" id="" cols="40" rows="5" style="resize: none">${p.getDescription()}</textarea>                               
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Type:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="type" value="${p.getTextType()}" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Type</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Images:</h3>
                            </td>
                            <td>
                                <div class="images">
                                    <c:forEach items="${p.getImages()}" var="img">
                                        <div class="image-box">
                                            <img src="./img/UploadImgs/${p.getUserID()}/${img}" alt="">
                                        </div>                                                                            
                                    </c:forEach>                                                                                                    
                                </div>
                                <input type="file" name="file">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h3>Sell Quantity:</h3>
                            </td>
                            <td>
                                <div class="form__group field">
                                    <input name="sellquan" value="${p.getSellQuantity()}" required="" placeholder="Name" class="form__field" type="text">
                                    <label class="form__label" for="name">Sell Quantity</label>
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

    </div>

    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

    <script>
        // menu toggle
        let toggle = document.querySelector('.toggle')
        let navigation = document.querySelector('.navigation')
        let main = document.querySelector('.main')

        toggle.addEventListener('click', function () {
            navigation.classList.toggle('active')
            main.classList.toggle('active')
        })

    </script>
</body>

</html>
