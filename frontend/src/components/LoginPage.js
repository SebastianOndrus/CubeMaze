import {useState} from "react";
import React, { Component } from 'react'
import {useNavigate} from "react-router-dom";
import { app } from "./firebaseConfig";
import { getAuth, signInWithEmailAndPassword,signInWithPopup, GoogleAuthProvider, signInAnonymously } from "firebase/auth";
import Google from "@rsuite/icons/legacy/Google";
import {IconButton} from "rsuite";


function LoginPage ( ) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();



    const handleSubmit = async (e) => {
      e.preventDefault();
      const auth = getAuth(app);
        try {
            await signInWithEmailAndPassword(auth, email, password);
            navigate("/");
        } catch (error) {
            setError(error.message);
        }
    };

    const handleGoogleLogin = async () => {
        const auth = getAuth(app);
        const provider = new GoogleAuthProvider();
        try {
            await signInWithPopup(auth, provider);
            navigate("/");
        } catch (error) {
            setError(error.message);
        }
    }


    const ButtonStyle = { margin: "0px 10px"};
    return (
        <div className="container">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card p-4 shadow-sm mt-3">
                        <h1 className="text-center" style={{fontFamily: 'Lobster', fontWeight: "bold", fontSize: 40, color: '#4d4a4a'}}>Log in</h1>
                        <form onSubmit={handleSubmit}>
                            <div className="form-group mt-3">
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
                            <div className="form-group mt-3">
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
                            {error && <p className="text-danger">{error}</p>}
                            <br/>
                            <button type="submit" style={{backgroundColor: '#efe100',height:45, alignItems: "center", }} className="btn btn-primary w-100"><p style={{fontFamily: 'Lobster', fontSize:20,color: '#4d4a4a',fontWeight: 'normal'}}>Log in</p></button>
                        </form>
                        <div className="text-center mt-3">
                            <p style={{fontFamily: 'Lobster', fontSize:15,color: '#4d4a4a',fontWeight: 'normal',marginBottom:1}}>or continue with</p>
                            <IconButton icon={<Google  color="white"/>} style={{backgroundColor: "darkgray", borderColor: 'gray', borderRadius: 15, width: 60, height: 40}}  onClick={handleGoogleLogin} size="lg"/>
                        </div>

                        <div className="text-center mt-3">
                            <p>
                                Don`t have a account? <a href="#" onClick={() => navigate('/register')}>Sign up!</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );


}

export default LoginPage;