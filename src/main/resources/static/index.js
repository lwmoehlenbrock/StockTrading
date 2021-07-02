function createNewAccount(e){
    e.preventDefault();
    let user = {
        userName: document.getElementById("Username").value,
        password: document.getElementById("password").value,
        date: new Date()
        cash: 0
    }


    fetch("http://localhost:8080/login",
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json',},
            body: JSON.stringify(user),
        }
    ).then(()=>window.location.reload(true))

}

function login(e){
    e.preventDefault();
    let user = {
        userName: document.getElementById("Username").value,
        password: document.getElementById("password").value,
        date: new Date()
        cash: 0
    }


    fetch("http://localhost:8080/login",
        {
            method: 'GET',
            headers: {'Content-Type': 'application/json',},
            body: JSON.stringify(user),
        }
    ).then(()=>window.location.reload(true))

}

function deleteAll(e){
    e.preventDefault();
    fetch("http://localhost:8080/expenses", 
    {
        method: 'DELETE',
        headers: {'Content-Type': 'application/json',},
    }
).then(()=>window.location.reload(true))
}

async function getAllExpenses(){

    let response = await fetch("http://localhost:8080/expenses");
    let body = await response.json();

    let expenses = body.map(expense => {
        let date = new Date(expense.date)
        console.log(date)
        date = new Date(date.getTime()  + date.getTimezoneOffset() * 60 * 1000)
        console.log(date)
        return (
            `<li class="list-group-item expense">
                <p>${date.getMonth() +1}/${date.getDate()}/${date.getFullYear()}</p>
                <p>${expense.description}</p>
                <p>$${expense.amount}</p>
            </li>`
        );
    }).join("");

    document.getElementById("expenses").innerHTML = expenses;

}

getAllExpenses();