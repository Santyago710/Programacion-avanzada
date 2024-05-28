document.addEventListener("DOMContentLoaded", function() {
    var addIncomeButton = document.getElementById("add-income");
    var incomeTable = document.getElementById("income-table");

    addIncomeButton.addEventListener("click", function() {
        var incomeAmount = document.getElementById("income-amount").value;
        var incomeType = document.getElementById("income-type").value;
        var incomeDate = document.getElementById("income-date").value;

        if (incomeAmount && incomeDate) {
            var newRow = incomeTable.insertRow(1); // Inserta una nueva fila en la tabla

            // Crea celdas para cada dato
            var cell1 = newRow.insertCell(0); // Celda para el monto
            var cell2 = newRow.insertCell(1); // Celda para el tipo de ingreso
            var cell3 = newRow.insertCell(2); // Celda para la fecha

            // Agrega los datos a las celdas
            cell1.innerHTML = incomeAmount;
            cell2.innerHTML = incomeType;
            cell3.innerHTML = incomeDate;

            // Limpia los campos después de agregar el ingreso
            document.getElementById("income-amount").value = "";
            document.getElementById("income-type").value = "salario";
            document.getElementById("income-date").value = "";
        }
    });
});