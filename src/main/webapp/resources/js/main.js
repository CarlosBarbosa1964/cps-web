function disableButtons() {
	updateButton.disable();
	deleteButton.disable();
}

function enableButtons() {
	updateButton.enable();
	deleteButton.enable();
}

function unselectRows() {
	disableButtons();
	dataTable.unselectAllRows();
}

function show(elementId) {
	document.getElementById(elementId).style.display = 'block';
}

function hide(elementId) {
	document.getElementById(elementId).style.display = 'none';
}
