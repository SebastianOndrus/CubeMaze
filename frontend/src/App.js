import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import React from "react";
import {useEffect, useState} from "react";
import {addComment, fetchComments} from "./_api/comment.service";
import Comments from './components/Comments';
import CommentForm from "./components/CommentForm";
import OndrusCubeMaze from "./components/game/OndrusCubeMaze/OndrusCubeMaze";
import Menu from "./components/Menu";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Scores from "./components/Scores";
import {addScore, fetchScore} from "./_api/score.service";
import { getAuth, onAuthStateChanged} from 'firebase/auth';
import LoginPage from "./components/LoginPage";
import RegisterPage from "./components/RegisterPage";
import Logout from "./components/Logout";
import {addRating, fetchAverageRating, fetchRating} from "./_api/rating.service";
import Rating from "./components/Rating";



function App() {
  const selectedGame = 'cubeMaze';
  const [comments,setComments] = useState([]);
  const [scores,setScores] = useState([]);
  const [rating,setRating] = useState([]);
  const [averageRating,setAverageRating] = useState(0);
  const [user, setUser] = useState(null);

  const auth = getAuth();

  useEffect(() => {
      const unsubscribe = onAuthStateChanged(auth, (user) => {
          if (user) {
                setUser(user);
          } else {
                setUser(null);
          }
      });
      return () => {
          unsubscribe();
      }
  }, []);


  const fetchData= () => {
      fetchComments(selectedGame).then(response => {
          setComments(response.data);
      });
      fetchScore(selectedGame).then(response => {
          setScores(response.data);
      });
      fetchAverageRating(selectedGame).then(response => {
          setAverageRating(response.data);
      });
      if (user) {
            fetchRating(selectedGame,user.email).then(response => {
                setRating(response.data);
            });
      }
  }

  useEffect(() => {
    fetchData();
  }, []);

  const handleSendComment = (comment) => {
      if (user) {
          addComment(selectedGame,user.email,comment ).then(response => {
              fetchData();
          });
      }
  }
  const handleSendRating = (rating) => {
        if (user) {
            addRating(selectedGame,user.email,rating ).then(response => {
                fetchData();
            });
        }
  }

  const handleSendScore = (points,level) => {
        if (user) {
            addScore(selectedGame,user.email,level,points).then(response => {
                fetchData();
            });
        }
  }

    return (
    <div className="App mt-4 mb-4">
        <Menu user={user}/>
        <div className="container index-container">
            <Routes>
                <Route index element={
                    <React.Fragment>
                        <div className="text-center">
                            <h1 className={'game-name1'}>Welcome to CubeMaze</h1>
                        </div>

                    </React.Fragment>
                }/>
                <Route path={"scores"} element={
                    <React.Fragment>
                        <h2 style={{fontFamily: "Lobster", fontSize: 40,marginBottom:30, color: '#4d4a4a',fontWeight: "bold", textAlign:"center"}}>Leaderboard</h2>

                        <div style={{marginTop: '50px'}}>
                            <Scores scores={scores}/>
                        </div>
                    </React.Fragment>
                }/>

                <Route path={"comments"} element={
                    <React.Fragment>
                        <h2 style={{fontFamily: "Lobster", fontSize: 40,marginBottom:30, color: '#4d4a4a',fontWeight: "bold", textAlign:"center"}}>Comments</h2>

                        <CommentForm onSendComment={handleSendComment} player={user} />
                        <Comments comments={comments}/>
                    </React.Fragment>
                }/>

                <Route path={"ratings"} element={
                    <React.Fragment>
                        <div className="text-center">
                        <Rating rating={rating} averageRating={averageRating} player={user} onSendRating={handleSendRating}/>
                        </div>
                    </React.Fragment>
                }/>

                <Route path={"game"} element={
                    <React.Fragment>
                        <div className="container-fluid">
                            <OndrusCubeMaze onSendScore={handleSendScore} player={user}/>
                        </div>
                    </React.Fragment>
                }/>

                <Route path={"login"} element={
                    <React.Fragment>
                        <div className="container-fluid">
                            <LoginPage/>
                        </div>
                    </React.Fragment>
                }/>

                <Route path={"register"} element={
                    <React.Fragment>
                        <div className="container-fluid">
                            <RegisterPage/>
                        </div>
                    </React.Fragment>
                }/>

                <Route path={"logout"} element={
                    <React.Fragment>
                        <div className="container-fluid">
                            <Logout/>
                        </div>
                    </React.Fragment>
                }/>
                }

            </Routes>
        </div>



      {/*<h2>Comments</h2>*/}
      {/*<Comments comments={comments}/>*/}


    </div>

  );
}

export default App;
