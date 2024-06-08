import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {getAuth, createUserWithEmailAndPassword, updateProfile, signInWithPopup, GoogleAuthProvider, signInAnonymously} from "firebase/auth";
import {IconButton} from "rsuite";
import Google from "@rsuite/icons/legacy/Google";

function RegisterPage() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [password2, setPassword2] = useState("");
    const [name, setName] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (password !== password2) {
            setError("Passwords do not match");
            return;
        }
        const auth = getAuth();
        try {
            const userCredential = await createUserWithEmailAndPassword(auth, email, password);
            await updateProfile(userCredential.user, {displayName: name});
            navigate("/");
        } catch (error) {
            setError(error.message);
        }
    };

    const handleGoogleSignup = async () => {
        const auth = getAuth();
        const provider = new GoogleAuthProvider();
        try {
            await signInWithPopup(auth, provider);
            navigate("/");
        } catch (error) {
            setError(error.message);
        }
    };

    const ButtonStyle = { margin: "0px 10px"};

    return (
        <div className="container">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card p-4 shadow-sm mt-3">
                        <h1 className="text-center" style={{fontFamily: 'Lobster', fontWeight: "bold", fontSize: 40, color: '#4d4a4a'}}>Sign up</h1>
                        <form onSubmit={handleSubmit}>
                            <div className="form-group">
                                <label className="left" style={{fontFamily: 'Lobster'}}>Name</label>
                                <input
                                    type="text"
                                    id="name"
                                    className="form-control"
                                    autoComplete={"off"}
                                    value={name}
                                    onChange={(e) => setName(e.target.value)}
                                    required={true}
                                />
                            </div>
                            <div className="form-group">
                                <label className="left" style={{fontFamily: 'Lobster'}}>Email</label>
                                <input
                                    type="email"
                                    id="email"
                                    className="form-control"
                                    autoComplete={"off"}
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required={true}
                                />
                            </div>
                            <div className="form-group">
                                <label className="left" style={{fontFamily: 'Lobster'}}>Password</label>
                                <input
                                    type="password"
                                    id="password"
                                    className="form-control"
                                    autoComplete={"off"}
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required={true}
                                />
                            </div>
                            <div className="form-group">
                                <label className="left" style={{fontFamily: 'Lobster'}}>Confirm Password</label>
                                <input
                                    type="password"
                                    id="password"
                                    className="form-control"
                                    autoComplete={"off"}
                                    value={password2}
                                    onChange={(e) => setPassword2(e.target.value)}
                                    required={true}
                                />
                            </div>
                            {error && <p className="text-danger">{error}</p>}
                            <br/>
                            <button type="submit" style={{backgroundColor: '#efe100',height:45, alignItems: "center", }} className="btn btn-primary w-100"><p style={{fontFamily: 'Lobster', fontSize:20,color: '#4d4a4a',fontWeight: 'normal'}}>Sign up</p></button>
                        </form>
                        <div className="text-center mt-3">
                            <p style={{fontFamily: 'Lobster', fontSize:15,color: '#4d4a4a',fontWeight: 'normal',marginBottom:1}}>or continue with</p>
                            <IconButton icon={<Google  color="white"/>} style={{backgroundColor: "darkgray", borderColor: 'gray', borderRadius: 15, width: 60, height: 40}}  onClick={handleGoogleSignup} size="lg"/>
                        </div>
                        <div className="text-center mt-3">
                            <p>
                                Old friend? <a href="#" onClick={() => navigate('/login')}>Log in!</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default RegisterPage;