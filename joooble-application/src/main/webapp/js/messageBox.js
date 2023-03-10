// Get the modal
let modal = document.getElementById("myModal");

// Get the <span> element that closes the modal
let span = document.getElementsByClassName("close")[0];

document.addEventListener('DOMContentLoaded', () => {
    if (modal !== undefined) {
        if (modal.childNodes.length !== 0) {
            modal.style.display = "block";
        }
        // When the user clicks on <span> (x), close the modal
        span.addEventListener('click', () => {
            modal.style.display = "none";
        })

        // When the user clicks anywhere outside of the modal, close it
        window.addEventListener('click', (e) => {
            if (e.target === modal) {
                modal.style.display = "none";
            }
        })
    }
})

