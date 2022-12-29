let textAreaRef = document.querySelector("textarea");

document.querySelector("button").addEventListener("click", function(){
  if(textAreaRef.value.length >= 1 && textAreaRef.value.length <= 254){
    this.parentElement.submit();
  }
  else{
    alert("The minimum number of characters of the post must be 1 and maximum must be 254");
  }
});