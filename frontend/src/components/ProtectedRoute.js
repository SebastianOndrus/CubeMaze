import {Route, useNavigate} from "react-router-dom";
import {useEffect} from "react";
import {getAuth, onAuthStateChanged} from "firebase/auth";
import {app} from "./firebaseConfig";


function ProtectedRoute({ element, ...rest }) {
    const navigate = useNavigate();

    useEffect(() => {
        const auth = getAuth(app);
        const unsubscribe = onAuthStateChanged(auth, (user) => {
            if (!user) {
                navigate("/login");
            }
        });
        return () => {
            unsubscribe();
        }
    }, [navigate]);

    return <Route {...rest} element={element} />;

}

export default ProtectedRoute;