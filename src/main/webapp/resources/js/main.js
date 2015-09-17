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

function validateForm(xhr, status, args, form, panel) 
{    
    if(args.validationFailed || !args.success) 
    {
        PF(panel).jq.effect("shake", {times:5}, 100);
    } 
    else 
    {
    	PF(panel).hide();
    }  
}  
