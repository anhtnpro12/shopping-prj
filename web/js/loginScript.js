const container = document.querySelector('.container')
const pwShowHides = document.querySelectorAll('.showHidePw')
const pwFields = document.querySelectorAll('.password')
const signup = document.querySelector('.signup-link')
const login = document.querySelector('.login-link')

pwShowHides.forEach(eyeIcon => {
    eyeIcon.addEventListener('click', () => {
        pwFields.forEach(pw => {
            if (pw.type === 'password') {
                pw.type = 'text';
                pwShowHides.forEach(icon => {
                    icon.innerHTML = 'visibility';
                })
            } else {
                pw.type = 'password'
                pwShowHides.forEach(icon => {
                    icon.innerHTML = 'visibility_off';
                })
            }
        })
    })
})

signup.addEventListener('click', () => {
    container.classList.add('active')
})
login.addEventListener('click', () => {
    container.classList.remove('active')
})