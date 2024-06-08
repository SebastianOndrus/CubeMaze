import React, { useState } from "react";
import { Button, Modal } from "react-bootstrap";
import ReactStars from "react-rating-stars-component/dist/react-stars";
import Stars from "./Stars";

function Rating({ rating, averageRating, player, onSendRating }) {
    const [show, setShow] = useState(false);
    const [selectedRating, setSelectedRating] = useState(0);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const handleRatingSelect = (rating) => {
        setSelectedRating(rating);
    };

    const handleRatingSubmit = () => {
        onSendRating(selectedRating);
        handleClose();
    };

    if (player === null) return (
        <div style={{paddingTop: 100}}>
            <Stars rating={averageRating} />
            <h4 className="rating-heads">Average rating</h4>
            <p className="no_logged_user" style={{paddingTop: 30}}>Log in to submit rating</p>
        </div>
    );

    return (
        <div className="container">
            <div className="centered">

                <div style={{paddingTop: 100}}>
                    <Stars rating={averageRating} />
                    <h4 className="rating-heads">Average rating</h4>

                    <Stars rating={rating} />
                    <h4 className="rating-heads">Your rating</h4>
                </div>
                <Button variant="primary" onClick={handleShow} style={{backgroundColor: "#F9D335", fontFamily: "Lobster", fontWeight: "normal", color: "#4d4a4a"}}>
                    Add rating
                </Button>


                <Modal show={show} onHide={handleClose}>
                    <Modal.Body>
                        <div className="text-center">
                            <h4 className="text-center" style={{fontFamily: "Lobster", fontWeight: "normal", color: "#4d4a4a", fontSize: 30}}>Add rating</h4>
                            {Array.from({ length: 5 }, (_, i) => (
                                <Button
                                    key={i + 1}
                                    variant={selectedRating === i + 1 ? "warning" : "light"}
                                    onClick={() => handleRatingSelect(i + 1)}
                                    style={{margin: 5, fontFamily: "Lobster", fontWeight: "bold", color: "#4d4a4a"}}
                                >
                                    {i + 1}
                                </Button>
                            ))}
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose} style={{marginRight: 301, fontFamily: "Lobster", fontWeight: "bold", color: "#4d4a4a", backgroundColor: "#ececec" }}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={handleRatingSubmit} style={{marginRight: 5, fontFamily: "Lobster", fontWeight: "bold", color: "#4d4a4a", backgroundColor: "#F9D335" }}>
                            Save Rating
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>
        </div>

    );
}

export default Rating;
