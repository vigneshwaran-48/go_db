let buttonsList = document.querySelectorAll(".template-button");
let textAreaTag = document.querySelector("textarea");
let consoleArea = document.querySelector(".output-area");

textAreaTag.focus();

if(localStorage.history){
	consoleArea.innerHTML = localStorage.history + consoleArea.innerHTML;
}
consoleArea.scrollTop = consoleArea.scrollHeight;

let appendText = function(event){
  if(!textAreaTag.value.includes(";")){
    textAreaTag.value += event.target.innerText + " ";
  }
  textAreaTag.focus();
}
let codeAreaCheck = function(event){
  if(textAreaTag.value.includes(";")){
    if(event.key != "Backspace" && event.key != "Enter"){
		alert("Can't type after typing ';'");
      event.preventDefault();
    }
    else if(event.key == "Enter"){
	  localStorage.history = consoleArea.innerHTML + "<p class='command-prompt-style'>GO DB>" + textAreaTag.value + "</p>\n";
      document.querySelector("form").submit();
    }
  }
  else{
	  if(event.key == "Enter" && textAreaTag.value == "System clear"){
		  localStorage.clear();
		  consoleArea.innerHTML = "";
		  textAreaTag.value = "";
	  }
  }
}
for(let i = 0;i < buttonsList.length;i++){
  buttonsList[i].addEventListener("click", appendText);
}
textAreaTag.addEventListener("keydown", codeAreaCheck);