console.log("Hello World");

let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', function() {
    changeTheme();
})
function changeTheme() {
    changePageTheme(currentTheme, currentTheme)
    document.querySelector('html').classList.add(currentTheme);
    
    const changeThemeButton = document.querySelector('#theme_change_button');
    //changeThemeButton.querySelector('span').textContent = currentTheme == 'light' ? 'Dark' : 'Light';
    const oldTheme = currentTheme;
    changeThemeButton.addEventListener('click', (event)=>{
        
        console.log("button clicked");
        if(currentTheme === "dark"){    
            currentTheme = "light";
        }else{
            currentTheme = "dark";
        }
        changePageTheme(currentTheme, oldTheme);
    });

}
function setTheme(theme) {
 localStorage.setItem("theme",theme);   
}
function getTheme(theme) {
 let themes = localStorage.getItem("theme"); 
 return themes ? themes : "light"  
}

function changePageTheme(theme, oldTheme) {
    setTheme(currentTheme);
    document.querySelector('html').classList.remove(oldTheme);
    document.querySelector('html').classList.add(currentTheme);
    document.querySelector('#theme_change_button').querySelector("span").textContent = theme == 'light' ? 'Dark' : 'Light';
}
