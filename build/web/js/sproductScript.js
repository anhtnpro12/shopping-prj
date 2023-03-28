const mainImg = document.querySelector('#main-img')
const smallImgs = document.querySelectorAll('.small-img')

if (smallImgs) {
    smallImgs.forEach(function (img) {
        img.addEventListener('click', function(e) {
            mainImg.setAttribute('src', e.target.getAttribute('src'))
        })
    })
}