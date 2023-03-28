<%-- 
    Document   : adminEditUser
    Created on : Nov 6, 2022, 3:57:31 PM
    Author     : TNA
--%>

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
                <li class="hovered">
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
                <form action="adedit" method="post">
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
                                <h3>Role:</h3>
                            </td>
                            <td>
                                <div class="subcontainer">
                                    <label>
                                        <input type="radio" name="role" value="1" ${a.getRole()>=2?"":"checked"}>
                                        Admin                                                                                                           
                                    </label>
                                    <label>
                                        <input type="radio" name="role" value="2" ${a.getRole()<2?"":"checked"}>
                                        Customer                                                                                                           
                                    </label>                                    
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