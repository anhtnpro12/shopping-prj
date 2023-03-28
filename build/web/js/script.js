const bar = document.getElementById('bar')
const myClose = document.getElementById('close')
const nav = document.getElementById('navbar')
const dropdown = document.querySelector('.dropdown__select')
const dropdownList = document.querySelector('.dropdown__list')
const cartIcons = document.querySelectorAll('#product1 .pro a')

if (bar) {
    bar.addEventListener('click', () => {
        nav.classList.toggle('active')
    })
}

if (myClose) {
    myClose.addEventListener('click', () => {
        nav.classList.toggle('active')
    })
}

if (dropdown) {
    dropdown.addEventListener('click', () => {
        dropdownList.classList.toggle('active')
    })
}

console.log(cartIcons);
cartIcons.forEach(function(cart) {
    cart.addEventListener('click', (e) => {
        e.stopPropagation();
    })
})