// dashboard.js

// Submit the expense form to add a new expense
document.getElementById('expense-form').addEventListener('submit', function(event) {
    event.preventDefault();

    // Gather data from the form fields
    const expense = {
        description: document.getElementById('description').value,
        amount: document.getElementById('amount').value,
        category: document.getElementById('category').value,
        date: document.getElementById('date').value,
    };

    // Fetch the 'Add Expense' API
    fetch('/api/expenses/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}` // Send the JWT token in the header
        },
        body: JSON.stringify(expense)
    })
    .then(response => response.json())
    .then(data => {
        alert('Expense Added');
        fetchExpenseSummary();  // Refresh expense summary
        fetchExpenseList();     // Refresh expense list
    })
    .catch(error => {
        console.error('Error while adding expense:', error);
        alert('An error occurred while adding expense.');
    });
});

// Fetch and display the expense summary (monthly, quarterly, yearly)
function fetchExpenseSummary() {
    fetch('/api/expenses/summary', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('token')}` // Send JWT token for authentication
        }
    })
    .then(response => response.json())
    .then(data => {
        // Display summary data in the HTML
        document.getElementById('monthly-summary').textContent = `Monthly Total: ${data["Monthly Total"]}`;
        document.getElementById('quarterly-summary').textContent = `Quarterly Total: ${data["Quarterly Total"]}`;
        document.getElementById('yearly-summary').textContent = `Yearly Total: ${data["Yearly Total"]}`;
    })
    .catch(error => {
        console.error('Error while fetching expense summary:', error);
        alert('An error occurred while fetching expense summary.');
    });
}

// Fetch and display the list of all expenses
function fetchExpenseList() {
    fetch('/api/expenses/list', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('token')}` // Send JWT token for authentication
        }
    })
    .then(response => response.json())
    .then(data => {
        const expenseList = document.getElementById('expense-list');
        expenseList.innerHTML = '';  // Clear current list

        // Loop through all expenses and display them in the list
        data.forEach(expense => {
            const li = document.createElement('li');
            li.textContent = `${expense.description} - ${expense.amount} (${expense.category}) on ${expense.date}`;
            expenseList.appendChild(li);
        });
    })
    .catch(error => {
        console.error('Error while fetching expense list:', error);
        alert('An error occurred while fetching expense list.');
    });
}

// Fetch data on page load
fetchExpenseSummary();
fetchExpenseList();
