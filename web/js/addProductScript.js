const typeName = document.querySelector('#type-name')
const typeGroup = document.querySelector('.type-group')

typeName.addEventListener('input', (e) => {
    if (e.target.value === '') {
        typeGroup.style.display = 'none'
    } else {
        typeGroup.style.display = 'flex'
    }
})