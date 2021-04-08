const inputs = document.querySelectorAll(".input");


function addcl() {
    let parent = this.parentNode.parentNode;
    parent.classList.add("focus");
}

function remcl() {
    let parent = this.parentNode.parentNode;
    if (this.value === "") {
        parent.classList.remove("focus");
    }
}


inputs.forEach(input => {
    input.addEventListener("focus", addcl);
    input.addEventListener("blur", remcl);
});

function checkEqualString(questionId) {
    let contentA = document.getElementById("optionA-" + questionId).value;
    let contentB = document.getElementById("optionB-" + questionId).value;
    let contentC = document.getElementById("optionC-" + questionId).value;
    let contentD = document.getElementById("optionD-" + questionId).value;
    
    if (contentA === contentB || contentA === contentC || contentA === contentD) {
        alert("Option A content is same of option B or C or D!!");
        return false;
    }

    if (contentB === contentC || contentB === contentD) {
        alert("Option B content is same of option C or D!!");
        return false;
    }

    if (contentC === contentD) {
        alert("option D content is same of option D!!");
        return false;
    }

    return true;
}