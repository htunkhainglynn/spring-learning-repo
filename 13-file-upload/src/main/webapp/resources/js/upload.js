document.addEventListener("DOMContentLoaded", () => {
	// find hide upload button
	document.getElementById('uploadBtn').addEventListener('click', () => {
		document.getElementById('uploadInput').click();
	})

	// submit form when upload input changes
	document.getElementById('uploadBtn').addEventListener('change', () => {
		document.getElementById('uploadForm').submit();
	})
});