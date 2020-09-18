Filevalidation = () => {
    var allowedExtensions =
        /(\.jpg|\.jpeg|\.png|\.gif)$/i;
    const image = document.getElementById('file');
    // Check if any file is uploaded
    if (image.files.length > 0) {
        for (let i = 0; i <= image.files.length - 1; i++) {
            const fsize = image.files.item(i).size;
            const file = Math.round((fsize / 1024));
            // Check the size of the file in KB
            if (file >= 500) {
                alert(
                    "File too Big, please select a file less than 500kb");
                image.value = '';
            }
            // Check if file type is allowed
            else if (!allowedExtensions.exec(image.files.item(i).name)) {
                alert('Invalid file type');
                image.value = '';
            }
        }
    }
}