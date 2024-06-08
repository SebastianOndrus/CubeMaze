import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getAuth, signOut } from "firebase/auth";
import { app } from "./firebaseConfig";

function Logout() {
    const navigate = useNavigate();

    useEffect(() => {
        const handleLogout = async () => {
            const auth = getAuth(app);
            await signOut(auth);
            navigate("/login");
        };
        handleLogout();
    }, [navigate]);

    return null;
}

export default Logout;
