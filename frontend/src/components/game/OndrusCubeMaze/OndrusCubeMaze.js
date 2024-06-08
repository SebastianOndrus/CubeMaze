import './OndrusCubeMaze.css';
import React, { useEffect, useState } from "react";
import fieldService from "./_api/cubeMazeFieldService";
import Field from "./Field";
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import Spinner from 'react-bootstrap/Spinner';
import {Button, Modal, ModalFooter} from "react-bootstrap";
import {useNavigate} from "react-router-dom";

function OndrusCubeMaze({onSendScore, player}) {
    const [show, setShow] = useState(false);
    const [selectedRating, setSelectedRating] = useState(0);
    const [currentLevel, setCurrentLevel] = useState(1);
    const navigate = useNavigate();

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [field, setField] = useState(null);

    useEffect(() => {
        fieldService.fetchField().then(response => {
            setField(response.data);
        }).catch(error => {
            console.error(error);
        });
    }, []);

    const handleMoveCube = (row, col) => {
        fieldService.moveCube(row, col).then(response => {
            setField(response.data);
        }).catch(error => {
            console.error(error);
        });
    }

    const handleNewGame = (level) => {
        handleClose();
        setCurrentLevel(level);
        fieldService.newGame(level).then(response => {
            setField(response.data);
        }).catch(error => {
            console.error(error);
        });
    }

    const handleSendScore = () => {
        if (player) {
            onSendScore(field.points, currentLevel);
        }
    }

    const handleLevelSelect = (level) => {
        setCurrentLevel(level);
    };

    const handleQuit = () => {
        handleSendScore()
        handleClose();
        handleNewGame(currentLevel);
        navigate('/');
    }

    const handleContinue = () => {
        handleSendScore()
        handleClose();
        handleNewGame(currentLevel)
    }

    if ( field?.state === "WIN" ) {
        return (
            <div className="text-center">

                <Modal show={true} onHide={handleClose} centered={true}>

                    <Modal.Body>
                        <div className="text-center">
                            <h4 className="game-win">You have won!</h4>
                            <h4 className="game-score">Your score is: {field?.points}</h4>
                        </div>

                    </Modal.Body>
                    <ModalFooter >
                        <div  className="d-flex justify-content-center">
                            <ButtonGroup className="" >
                                <Button variant={"secondary"} onClick={handleQuit} style={{backgroundColor: "grey", fontFamily: "Lobster", fontWeight: "normal"}} >
                                    Quit
                                </Button>
                                <h4 className={"game-score"} style={{paddingRight:0}}></h4>
                                <Button variant="primary" onClick={handleContinue} style={{ fontFamily: "Lobster", fontWeight: "normal"}}>
                                    New Game
                                </Button>
                            </ButtonGroup>
                        </div>
                    </ModalFooter>
                </Modal>
            </div>
        )

    }

    return (
        <div className="game-container">
            <div>
                <div className="score-status" style={{paddingBottom:40}}>
                    <ButtonGroup>
                        <Button variant="primary" onClick={handleShow} style={{backgroundColor: "#F9D335", fontFamily: "Lobster", fontWeight: "normal", color: "#4d4a4a"}}>
                            Change Level
                        </Button>
                        <Button variant="primary" onClick={() => handleNewGame(currentLevel)} style={{backgroundColor: "#4d4a4a", fontFamily: "Lobster", fontWeight: "normal", color: "#F9D335"}}>
                            New Game
                        </Button>
                    </ButtonGroup>
                </div>

                <Modal show={show} onHide={handleClose} animation={false}>
                    <Modal.Body>
                        <div className="text-center">
                            <h4 className="text-center" style={{fontFamily: "Lobster", fontWeight: "normal", color: "#4d4a4a", fontSize: 30}}>Pick level</h4>
                            {Array.from({ length: 8 }, (_, i) => (
                                <Button
                                    key={i + 1}
                                    variant={currentLevel === i + 1 ? "warning" : "light"}
                                    onClick={() => handleLevelSelect(i + 1)}
                                    style={{margin: 2, fontFamily: "Lobster", fontWeight: "bold", color: "#4d4a4a"}}
                                >
                                    {i + 1}
                                </Button>
                            ))}
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose} style={{marginRight: 345, fontFamily: "Lobster", fontWeight: "bold", color: "#4d4a4a", backgroundColor: "#ececec" }}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={() => handleNewGame(currentLevel)} style={{marginRight: 5, fontFamily: "Lobster", fontWeight: "bold", color: "#4d4a4a", backgroundColor: "#F9D335" }}>
                            Start
                        </Button>
                    </Modal.Footer>
                </Modal>
                {field ?
                    <Field tiles={field.tiles} onMoveCube={(row, col) => handleMoveCube(row, col)} cube={field.cube} />
                    :
                    <div className="loading-spinner">
                        <Spinner animation="border" role="status">
                            <span className="visually-hidden">Loading...</span>
                        </Spinner>
                    </div>
                }
            </div>
        </div>
    )
}

export default OndrusCubeMaze;
