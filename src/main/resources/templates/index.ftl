<!DOCTYPE html>
<html>
<body>
    <div id="main">${content}</div>
    <script src="client.js?rnd=${rnd}"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            renderClient();
        });
    </script>
</body>
</html>