document.addEventListener("DOMContentLoaded", () => {
    const menuButton = document.getElementById("menuButton");
    const dropdownMenu = document.getElementById("dropdownMenu");
    const chevronIcon = document.getElementById("chevronIcon");

    const userMenuBtn = document.getElementById("userMenuBtn");
    const userDropdown = document.getElementById("userDropdown");

    if (menuButton && dropdownMenu && chevronIcon) {
        menuButton.addEventListener("click", (e) => {
            e.stopPropagation();

            if (userDropdown && userDropdown.classList.contains("opacity-100")) {
                userDropdown.classList.add("opacity-0", "scale-y-0");
                userDropdown.classList.remove("opacity-100", "scale-y-100");
            }

            const isHidden = dropdownMenu.classList.contains("opacity-0");

            if (isHidden) {
                dropdownMenu.classList.remove("opacity-0", "scale-y-0");
                dropdownMenu.classList.add("opacity-100", "scale-y-100");
                chevronIcon.classList.add("rotate-180");
            } else {
                dropdownMenu.classList.add("opacity-0", "scale-y-0");
                dropdownMenu.classList.remove("opacity-100", "scale-y-100");
                chevronIcon.classList.remove("rotate-180");
            }
        });
    }

    if (userMenuBtn && userDropdown) {
        userMenuBtn.addEventListener("click", (e) => {
            e.stopPropagation();

            if (dropdownMenu && dropdownMenu.classList.contains("opacity-100")) {
                dropdownMenu.classList.add("opacity-0", "scale-y-0");
                dropdownMenu.classList.remove("opacity-100", "scale-y-100");
                if (chevronIcon) chevronIcon.classList.remove("rotate-180");
            }

            const isHidden = userDropdown.classList.contains("opacity-0");

            if (isHidden) {
                userDropdown.classList.remove("opacity-0", "scale-y-0");
                userDropdown.classList.add("opacity-100", "scale-y-100");
            } else {
                userDropdown.classList.add("opacity-0", "scale-y-0");
                userDropdown.classList.remove("opacity-100", "scale-y-100");
            }
        });
    }

    window.addEventListener("click", (e) => {
        if (dropdownMenu && !menuButton.contains(e.target) && !dropdownMenu.contains(e.target)) {
            dropdownMenu.classList.add("opacity-0", "scale-y-0");
            dropdownMenu.classList.remove("opacity-100", "scale-y-100");
            if (chevronIcon) chevronIcon.classList.remove("rotate-180");
        }

        if (userDropdown && !userMenuBtn.contains(e.target) && !userDropdown.contains(e.target)) {
            userDropdown.classList.add("opacity-0", "scale-y-0");
            userDropdown.classList.remove("opacity-100", "scale-y-100");
        }
    });
});
