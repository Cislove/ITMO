let form = document.getElementById('check-form');
let resultsTable = document.getElementById('results')
    .getElementsByTagName('tbody')[0];
let rButtons = document.querySelectorAll('.r-button');
let rValueInput = document.getElementById('r-value');
let checkboxes = document.querySelectorAll('#x-checkboxes input[type="checkbox"]');
document.addEventListener('DOMContentLoaded', main);


function main(){
    console.log("Работаем")
    setupXCheckboxes();
    setupRButtons(rButtons);
    setupSubmitForm(form);
}

function setupXCheckboxes(){
    checkboxes.forEach(cb => cb.addEventListener('change',
        function(){
            handleXCheckboxChange(this);
        }
    ));
}

function handleXCheckboxChange(checkbox){
    checkboxes.forEach(ch => ch.checked = false);
    checkbox.checked = true;
}

function setupRButtons(){
    let value = document.getElementById('r-value');
    rButtons.forEach(bt => bt.addEventListener('click',
        function(){
            handlerRButtonClick(this);
        }
    )); 
}

function handlerRButtonClick(button){
    rValueInput.value = button.value;
    rButtons.forEach(bt => bt.classList.remove('active'));
    button.classList.add('active');
}

function setupSubmitForm(){
    form.addEventListener('submit', function(event){
        event.preventDefault();
        handleSubmitFrom();
    })
}

function getXValue(){
    let result = null;
    checkboxes.forEach(cb => {
        if(cb.checked){
            result = cb.value;
        }
    });
    return result;
}

function handleSubmitFrom(){
    let xCord = getXValue();
    let yCord = document.getElementById('y').value;
    let rCord = rValueInput.value;

    // console.log(xCord);
    // console.log(yCord);
    // console.log(rCord);

    if(validateForm(xCord, yCord, rCord)){
        sendRequest(xCord, yCord, rCord);
    }
    else{
        alert('Заполните все поля правильно!');
    }
}

function validateForm(x, y, r){
    console.log(x == null, !validateY(y), r === '');
    if(x == null || !validateY(y) || r === ''){
        return false;
    }
    return true;
}

function validateY(y){
    let value = y.parseFloat;
    let regex = /^-?\d+(\.\d{0,5})?$/;
    console.log(y);
    if(Number.isNaN(value)) return false;
    console.log(regex.test(y));
    return value >= -3 || value <=3 || regex.test(y);
}

function sendRequest(x, y, r){
    let request = new URLSearchParams({
        x: x,
        y: y,
        r: r
    });
    //console.log(JSON.stringify(request));
    fetch(`http://localhost:8080/fcgi-bin/Java.jar?${request.toString()}`, {
        method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            document.getElementById('error-message').style.display = 'block';
            document.getElementById('error-message').innerText = 'Ошибка: ' + response.status + "\n" + response.statusText;
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json(); 
    })
    .then(data => {
        updateResultTable(data);
        document.getElementById('error-message').style.display = 'none';
    })
    .catch(processError)
}

function processError(error){
    document.getElementById('error-message').style.display = 'block';
    //document.getElementById('error-message').innerText = 'Ошибка: ' + 'сервер не отвечает';
    console.error('There was a problem with the fetch operation:', error);
    console.log("Ошибка");

}

function processLoad(event){
    if(event.lengthComputable){
        //            
    }
    else{
        //
    }
}

function updateResultTable(data){
    // let resultsTable = document.getElementById('results').getElementsByTagName('tbody')[0];
    console.log(data.x);
    let newRow = document.createElement('tr');
    let tableRow = '<th>' + data.x + '</th>' + 
        '<th>' + data.y + '</th>' +
        '<th>' + data.r + '</th>' +
        '<th>' + data.result + '</th>' + 
        '<th>' + data.requestTime + '</th>' +
        '<th>' + data.processingTime + '</th>';
    newRow.innerHTML= tableRow;
    resultsTable.insertBefore(newRow, resultsTable.firstChild);
}
