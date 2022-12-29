let nameField;
let passField;

let formButtons = document.querySelectorAll(".sign-in-button-form");

for(let i = 0;i < formButtons.length;i++){
  formButtons[i].addEventListener("click", function(event){
  
    if((nameField.value.length >= 5 && nameField.value.length < 20) && (passField.value.length >= 5 && passField.value.length < 20)){
      this.parentElement.submit();
    }
    else{
      alert("Field should contain a minimum of 5 letters and maximum of 19 letters");
    }
  });
}

let getForm = function(signUpOrIn){
  let confirmPage = document.querySelector(".confirm-page");
  
  if(signUpOrIn.target.classList[1] == "sign-in-button"){
    document.querySelector(".sign-in-page-section").style.display = "flex";
    confirmPage.style.display = "none";
    nameField = document.querySelector(".sign-in-page-section  .name-input");
    passField = document.querySelector(".sign-in-page-section  .pass-input");
  }
  else if(signUpOrIn.target.classList[1] == "sign-up-button"){
    document.querySelector(".sign-up-page-section").style.display = "flex";
    confirmPage.style.display = "none";
    nameField = document.querySelector(".sign-up-page-section  .name-input");
    passField = document.querySelector(".sign-up-page-section  .pass-input");
  }
  else {
    console.log("Problem");
  }
  
}
let signButtons = document.querySelectorAll("button");

for(let i = 0;i < 2;i++){
  signButtons[i].addEventListener("click", getForm);
}