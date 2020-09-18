Filevalidation = () => {
    const fi = document.getElementById('file');
    // Check if any file is selected.
    if (fi.files.length > 0) {
        for (let i = 0; i <= fi.files.length - 1; i++) {
            const fsize = fi.files.item(i).size;
            const file = Math.round((fsize / 1024));
    // The size of the file.
    if (file >= 0.48828125) {
    alert(
    "File too Big, please select a file less than 4mb");
            }
        }
    }
}
