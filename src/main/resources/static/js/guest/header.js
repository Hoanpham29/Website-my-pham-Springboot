document.addEventListener("DOMContentLoaded", () => {
            const loginModal = document.getElementById("loginModal");
            const registerModal = document.getElementById("registerModal");
            const loginBtn = document.getElementById("loginBtn");
            const closeLoginModal = document.getElementById("closeModal");
            const closeRegisterModal = document.getElementById("closeRegisterModal");
            const showRegisterModal = document.getElementById("showRegisterModal");
            const showLoginModal = document.getElementById("showLoginModal");

            const sidebar = document.getElementById("sidebar");
            const overlay = document.getElementById("overlay");
            const openSidebar = document.getElementById("openSidebar");
            const closeSidebar = document.getElementById("closeSidebar");

            // Đăng nhập
            loginBtn.onclick = () => loginModal.classList.remove("hidden");
            closeLoginModal.onclick = () => loginModal.classList.add("hidden");

            // Đăng ký
            showRegisterModal.onclick = () => {
                loginModal.classList.add("hidden");
                registerModal.classList.remove("hidden");
            };
            closeRegisterModal.onclick = () => registerModal.classList.add("hidden");
            showLoginModal.onclick = () => {
                registerModal.classList.add("hidden");
                loginModal.classList.remove("hidden");
            };

            // Sidebar
            const menuButton = document.getElementById('menuButton');
            const dropdownMenu = document.getElementById('dropdownMenu');
            const chevronIcon = document.getElementById('chevronIcon');

            menuButton.addEventListener('click', () => {
                const isHidden = dropdownMenu.classList.contains('opacity-0');

                // Toggle hiển thị với animation
                if (isHidden) {
                    dropdownMenu.classList.remove('opacity-0', 'scale-y-0');
                    dropdownMenu.classList.add('opacity-100', 'scale-y-100');
                    chevronIcon.classList.add('rotate-180');
                } else {
                    dropdownMenu.classList.add('opacity-0', 'scale-y-0');
                    dropdownMenu.classList.remove('opacity-100', 'scale-y-100');
                    chevronIcon.classList.remove('rotate-180');
                }
            });

            // Ẩn khi click ra ngoài
            window.addEventListener('click', (e) => {
                if (!menuButton.contains(e.target) && !dropdownMenu.contains(e.target)) {
                    dropdownMenu.classList.add('opacity-0', 'scale-y-0');
                    dropdownMenu.classList.remove('opacity-100', 'scale-y-100');
                    chevronIcon.classList.remove('rotate-180');
                }
            });

        });

